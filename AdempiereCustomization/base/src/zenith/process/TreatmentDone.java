package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MInOut;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MTreatmentDoc;

public class TreatmentDone extends SvrProcess
{
	int treatmentID = 0;
	private int C_Order_ID = 0;
	private int C_BPartner_ID = 0;
	private MOrder _order = null;

	@Override
	protected void prepare()
	{
		treatmentID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentID, get_TrxName());
		doc.settreatment_done(true);
		doc.settreat_ref_ID(treatmentID);
		doc.settreatment_done_date(new Timestamp(System.currentTimeMillis()));

		if (doc.getattended_by_ID() == 0)
		{
			int AD_User_ID = Env.getContextAsInt(getCtx(), "#AD_User_ID");
			doc.setattended_by_ID(AD_User_ID);
		}
		doc.setattend(true);
        doc.setstate("c");
		doc.save();
		//// createTransactions();

		return null;
	}

	private void createTransactions()
	{
		createOrder();
		if (_order != null)
		{
			createPayment();
		}
		if (C_Order_ID > 0)
		{
			completeShipment(C_Order_ID);
		}
	}

	private void completeShipment(int orderID)
	{
		String sql = "  SELECT M_InOut_ID FROM adempiere.m_inout WHERE c_order_id =" + orderID;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try
		{
			pstmt2 = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt2.executeQuery();
			if (rs.next())
			{
				inOut(rs);
			}
		} catch (Exception e)
		{

		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
	}

	private void inOut(ResultSet rs) throws SQLException
	{
		int M_InOut_ID = rs.getInt(1);
		MInOut io = new MInOut(getCtx(), M_InOut_ID, get_TrxName());

		String status = io.completeIt();
		if (status.equals(MInOut.DOCSTATUS_Completed))
		{
			io.setDocAction(MInOut.DOCACTION_Close);
			io.setDocStatus(MInOut.DOCSTATUS_Completed);
			io.save();
		}
	}

	private void createPayment()
	{
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
		payment.setC_BPartner_ID(C_BPartner_ID);
		payment.setC_DocType_ID(1000008); // AR Receipt payment
		payment.setC_Currency_ID(266); // KES
		payment.setC_CashBook_ID(1000000); // Petty Cash
		payment.setC_BankAccount_ID(1000000); // Some Bank 1000003 Petty Cash

		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		payment.setTrxType(MPayment.TRXTYPE_Sales);
		//
		payment.setR_AvsAddr(MPayment.R_AVSZIP_Unavailable);
		payment.setR_AvsZip(MPayment.R_AVSZIP_Unavailable);
		//
		payment.setIsReceipt(true); // ///
		payment.setIsApproved(false);
		payment.setIsReconciled(false);
		payment.setIsAllocated(false);
		payment.setIsOnline(false);
		payment.setIsSelfService(false);
		payment.setIsDelayedCapture(false);
		payment.setIsPrepayment(false);
		payment.setProcessed(false);
		payment.setProcessing(false);
		payment.setPosted(false);
		// payment.setIsVATWithholding(true);
		// payment.setIsConsultancyWithholding(false);
		//
		payment.setPayAmt(_order.getGrandTotal());
		payment.setDiscountAmt(Env.ZERO);
		payment.setTaxAmt(Env.ZERO);
		payment.setWriteOffAmt(Env.ZERO);
		payment.setIsOverUnderPayment(false);
		payment.setOverUnderAmt(Env.ZERO);
		//
		payment.setDateTrx(new Timestamp(System.currentTimeMillis()));
		payment.setDateAcct(_order.getDateOrdered());

		// set order ID
		payment.setC_Order_ID(C_Order_ID);
		payment.save();
		payment.completeIt();
		payment.setDocStatus(MPayment.DOCSTATUS_Completed);

		// hard codes
		payment.setAD_Org_ID(1000004);

		payment.save();
		payment.completeIt();
		payment.setDocStatus(MPayment.DOCSTATUS_Completed);

		_order.setProcessed(true);
		_order.setPosted(true);
		_order.setC_Payment_ID(payment.get_ID());
		_order.save();
	}

	private void createOrder()
	{
		MOrder order = null;
		String sql = " SELECT M_Product_ID, price, qty,linenetamt,C_BPartner_ID"
				+ " FROM adempiere.hms_billing bill WHERE bill.hms_treatment_doc_ID=" + treatmentID;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try
		{
			pstmt2 = DB.prepareStatement(sql, null);
			rs = pstmt2.executeQuery();
			while (rs.next())
			{
				int M_Product_ID = rs.getInt(1);
				BigDecimal price = rs.getBigDecimal(2);
				BigDecimal qty = rs.getBigDecimal(3);
				BigDecimal linenetamt = rs.getBigDecimal(4);

				if (order == null)
				{
					C_BPartner_ID = rs.getInt(5);
					int docTypeID = getPrepayDoctype(getCtx());
					order = new MOrder(getCtx(), 0, get_TrxName());
					order.setC_DocTypeTarget_ID(docTypeID);
					order.setC_BPartner_ID(C_BPartner_ID);

					order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#SalesRep_ID")); // GardenAdmin
					order.setC_Currency_ID(Env.getContextAsInt(getCtx(), "#C_Currency_ID"));
					order.setC_PaymentTerm_ID(Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID"));
					order.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));
					order.setDocStatus(MOrder.DOCSTATUS_Drafted);
					order.setDocAction(MOrder.DOCACTION_Complete);
					order.setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
					order.setCopyFrom("N");
					order.setC_ConversionType_ID(Env.getContextAsInt(getCtx(), "#C_ConversionType_ID"));
					order.setPaymentRule("B");
					// hard codes
					order.setAD_Org_ID(1000008);
					order.setM_Warehouse_ID(1000004);
					order.save();
					C_Order_ID = order.getC_Order_ID();
				}
				MOrderLine line = new MOrderLine(order);
				// line.setc_orderline_id_source(set[i].getOrderline_id_source());
				line.setM_Product_ID(M_Product_ID);
				// line.setM_AttributeSetInstance_ID(set[i].get_M_AttributeSetInstance_ID());
				line.setM_Warehouse_ID(order.getM_Warehouse_ID());
				line.setLineNetAmt(linenetamt);
				line.setPrice(price);
				line.setPriceList(price);
				line.setPriceEntered(price);
				line.setQty(qty);
				// line.setitem_type(set[i].getItemType());
				line.setIspaid("Y");
				line.setC_Tax_ID(109);// exempt
				// line.setDescription(set[i].getDescription());
				line.save();
			}
			order.completeIt();
			order.setDocStatus(MOrder.DOCSTATUS_WaitingPayment);
			// order.setDescription("Order: " + order.getDocumentNo());
			order.setDescription("iiiiiiiiiiiiiiiiiiiiiiiiiii");
			order.save();

			_order = order;
			// lab test
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
	}

	public static int getPrepayDoctype(Properties ctx)
	{
		String docType = MOrder.DocSubTypeSO_Prepay;
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_Prepay + "'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				docTypeID = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		}
		return docTypeID;
	}
}
