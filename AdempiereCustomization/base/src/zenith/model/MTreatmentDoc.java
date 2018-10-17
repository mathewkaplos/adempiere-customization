package zenith.model;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

//import zenith.process.BookPatient;

public class MTreatmentDoc extends X_hms_treatment_doc
{
	/**
	 * 
	 */
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MTreatmentDoc.class);

	private static final long serialVersionUID = 1L;

	public MTreatmentDoc(Properties ctx, int hms_treatment_doc_ID, String trxName)
	{
		super(ctx, hms_treatment_doc_ID, trxName);
		// set BP defaults
		setBPartner();
	}

	public MTreatmentDoc(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	int C_BP_Group_ID = 0;
	// public MProduct product;
	public MBPartner partner;

	public static MTreatmentDoc get(Properties ctx, int C_BPartner_ID, String trx)
	{
		// String whereClause = "C_BPartner_ID=? AND AD_Client_ID=?";
		MTreatmentDoc retValue = new MTreatmentDoc(ctx, C_BPartner_ID, trx);

		return retValue;
	}

	// set business partner defaults
	private void setBPartner()
	{
		MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());

		partner = bp;
		if (bp.getC_BP_Group_ID() > 0)
		{
			C_BP_Group_ID = bp.getC_BP_Group_ID();
		}
		// 1000072
		// default to cash
		else
		{
			C_BP_Group_ID = 1000072;
		}
	}

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		System.out.println("Treatment Doc BeforeSave 04/10/2017");
		System.out.println("before save.....");
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{

		System.out.println("after save.....yessss");
		return success;
	}

	public void createOder(int M_Product_ID, BigDecimal bd)
	{
		// System.out.println("this is it..");
		Properties ctx = getCtx();
		try
		{
			MOrder order = new MOrder(getCtx(), 0, get_TrxName());
			// order.setDescription(name + " Test Fee ");
			order.setC_DocTypeTarget_ID(getPrepayDoctype(getCtx(), get_TrxName()));
			order.setC_BPartner_ID(getC_BPartner_ID());

			/// order.setC_Currency_ID(266);// KES
			// C_PaymentTerm_ID
			order.setC_PaymentTerm_ID(Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID"));
			// M_Warehouse_ID
			/// if (product.getm_warehouse_id() > 0)
			/// order.setM_Warehouse_ID(product.getm_warehouse_id());
			// else
			order.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));
			// M_PriceList_ID
			// order.setM_PriceList_ID(otherCharges.getM_PriceList_ID());

			order.setDocStatus(MOrder.DOCSTATUS_Drafted);
			order.setDocAction(MOrder.DOCACTION_Complete);
			order.setC_DocType_ID(getPrepayDoctype(getCtx(), get_TrxName())); // Pre-pay
																				// Order
			order.setInvoiceRule("I");
			order.setDeliveryRule("R");
			order.setFreightCostRule("I");
			order.setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
			order.setCopyFrom("N");
			// C_ConversionType_ID
			order.setC_ConversionType_ID(Env.getContextAsInt(getCtx(), "#C_ConversionType_ID"));
			// SalesRep_ID
			order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#SalesRep_ID"));
			// order.setTotalLines(bd);
			// order.setGrandTotal(bd);
			order.setPaymentRule("B");
			order.setTreatmentDocID(this.gethms_treatment_doc_ID());
			order.save();

			// line
			MOrderLine line = new MOrderLine(order);

			line.setM_Product_ID(M_Product_ID);
			line.setLineNetAmt(bd);
			line.setPriceActual(bd);
			line.setPrice(bd);
			line.setPriceLimit(bd);
			line.setPriceList(bd);
			line.setPriceEntered(bd);
			line.setRoundedLineamt(bd);

			line.setQty(Env.ONE);
			line.setDescription(("Consultation"));
			line.setIspay("Y");
			line.save();
			line.setitem_type("CON");
			line.setPriceActual(bd);
			line.setRoundedLineamt(line.getLineNetAmt());
			line.save();

			order.setDescription("Consultation");
			order.save();

			executeEmergency();
			executeNextLocation(2);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void createInvoice(int M_Product_ID, BigDecimal bd)
	{
		MInvoice i = new MInvoice(getCtx(), 0, get_TrxName());
		i.setC_BPartner_ID(getC_BPartner_ID());
		i.setC_Currency_ID(266);// KES
		i.setC_DocTypeTarget_ID(getARInvoiceDoctype(getCtx(), get_TrxName()));
		i.setC_DocType_ID(getARInvoiceDoctype(getCtx(), get_TrxName()));
		i.setC_PaymentTerm_ID(getPaymentTermID(getCtx(), get_TrxName()));
		i.setDateAcct(new Timestamp(System.currentTimeMillis()));
		i.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		i.setDocStatus(MOrder.DOCSTATUS_Drafted);
		i.setDocAction(MOrder.DOCACTION_Prepare);
		i.setIsApproved(false);
		i.setIsDiscountPrinted(false);
		i.setIsInDispute(false);
		i.setIsPaid(false);
		i.setIsPayScheduleValid(false);
		i.setIsPrinted(false);
		i.setIsSOTrx(true);
		i.setIsSelfService(false);
		i.setIsTaxIncluded(false);
		i.setIsTransferred(false);
		// i.setM_PriceList_ID(Env.getContextAsInt(ctx, "#M_PriceList_ID"));
		i.setPaymentRule("P");
		i.setPosted(false);
		i.setProcessed(false);
		i.setSendEMail(false);

		i.setGrandTotal(bd);
		i.setTreatmentDocID(this.get_ID());
		i.save();

		MInvoiceLine line = new MInvoiceLine(i);
		line.setM_Product_ID(M_Product_ID);
		line.setLineNetAmt(bd);
		line.setLineNetAmt(bd);
		line.setPrice(bd);
		line.setPriceLimit(bd);
		line.setPriceList(bd);
		line.setPriceEntered(bd);
		line.setQty(1);
		// line.setC_Tax_ID(1000000);
		line.save();
		BigDecimal invAmt = i.getGrandTotal();
		i.completeIt();
		i.setDocStatus(MInvoice.DOCSTATUS_Completed);
		i.save();

		executeEmergency();
		executeNextLocation(1);
		// ReserveStock.reserveStock(ctx, order.getLines());
	}

	// Product Pricelist......
	public static BigDecimal getProductPrice123(int M_Product_ID, int M_Pricelist_version_ID, String trxName)
	{
		BigDecimal priceList = Env.ZERO;
		String sql = "SELECT pricelist FROM adempiere.M_Productprice WHERE M_Product_ID= ? AND  M_Pricelist_version_ID = ?";
		priceList = DB.getSQLValueBD(trxName, sql, M_Product_ID, M_Pricelist_version_ID);
		return priceList;
	}

	// / Patient Emergency ...For priority in the queue
	private void executeEmergency()
	{
		if (partner.getpatient_priority() != null)
			if (partner.getpatient_priority().equalsIgnoreCase("EM"))
				setPriority(BigDecimal.valueOf(10));
		save(get_TrxName());

	}

	// Patient next location
	/**
	 * param billingType -- either invoice(1) or order (2)
	 */
	private void executeNextLocation(int billingType)
	{
		boolean isTriage_first = HmsSetup.triageBeforeConsultation(Env.getContextAsInt(getCtx(), "#AD_Client_ID"),
				get_TrxName());
		if (billingType == 1)
		{
			if (isTriage_first)
				setpatient_location("Triage");
			else
				setpatient_location("Consultation");
		} else if (billingType == 2)
		{
			if (isTriage_first)
				setpatient_location("Triage");
			else
				setpatient_location("Cashier");
		}
		save(get_TrxName());
	}

	// DocSubTypeSO_Prepay ID
	public static int getPrepayDoctype(Properties ctx, String trxName)
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_Prepay + "'";
		docTypeID = DB.getSQLValue(trxName, sql);
		return docTypeID;
	}

	// ARI AR Invoice
	private static int getARInvoiceDoctype(Properties ctx, String trxName)
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND name ='"
				+ "AR Invoice" + "'";
		docTypeID = DB.getSQLValue(trxName, sql);
		// 1000002 default for ARI AR Invoice ..don't use this
		if (docTypeID == 0)
			return 1000002;
		return docTypeID;
	}

	// Payment term ......immediate
	private static int getPaymentTermID(Properties ctx, String trxName)
	{
		int PaymentTermID = 0;
		// PaymentTermID
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT c_paymentterm_id FROM c_paymentterm WHERE AD_Client_ID=" + AD_Client_ID + " AND name ='"
				+ "Immediate" + "'";
		PaymentTermID = DB.getSQLValue(trxName, sql);
		// 1000000 default for immediate ..don't use this
		if (PaymentTermID == 0)
			return 1000000;
		return PaymentTermID;
	}

	// next_location
	/**
	 * Get All bills
	 */
	public MBilling[] getBills()
	{
		StringBuilder select = new StringBuilder();
		select.append("SELECT * FROM   hms_billing  WHERE  hms_treatment_doc_id = " + this.gethms_treatment_doc_ID());
		// StringBuilder select = "SELECT * FROM hms_billing WHERE
		// hms_treatment_doc_id =" + treatID + " AND pay ='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MBilling> list = new ArrayList<MBilling>();
		try
		{
			pstmt = DB.prepareStatement(select.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, get_TrxName());
				list.add(bill);
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				pstmt.close();
				rs.close();
				pstmt = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		MBilling[] billArray = new MBilling[list.size()];
		return list.toArray(billArray);

	}

	public void updateDrugStatus()
	{
		updateDrugStatus(this);
	}

	public void updateDrugStatus(MTreatmentDoc doc)
	{
		int treatID = doc.gethms_treatment_doc_ID();
		doc.setdrugs_ordered(getAllDrugs(treatID));
		doc.setdrugs_issued(getIssuedDrugs(treatID));
		doc.setdrugs_not_issued(getUnIssuedDrugs(treatID));
		doc.save();

	}

	private int getAllDrugs(int treatID)
	{
		int count = 0;
		String sql = " SELECT COALESCE(count(bill.hms_billing_ID),0) FROM adempiere.hms_billing bill "
				+ " INNER JOIN adempiere.m_product pro ON pro.M_Product_ID = bill.M_Product_ID"
				+ " WHERE   bill.hms_treatment_doc_ID = " + treatID + "  AND bill.is_prescription ='Y' "
				+ " AND pro.producttype='I' ";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
		return count;
	}

	private int getIssuedDrugs(int treatID)
	{
		int count = 0;
		String sql = " SELECT COALESCE(count(bill.hms_billing_ID),0) FROM adempiere.hms_billing bill "
				+ " WHERE   bill.hms_treatment_doc_ID = " + treatID
				+ "  AND bill.is_prescription ='Y' AND bill.issued ='Y'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
		return count;
	}

	private int getUnIssuedDrugs(int treatID)
	{
		int count = 0;
		String sql = " SELECT COALESCE(count(bill.hms_billing_ID),0) FROM adempiere.hms_billing bill "
				+ " INNER JOIN adempiere.M_Product pro ON pro.M_Product_ID = bill.M_Product_ID  "
				+ " WHERE   bill.hms_treatment_doc_ID = " + treatID
				+ "  AND pro.producttype ='I' AND bill.is_prescription ='Y' AND bill.issued ='N'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
		return count;
	}

	public void updateDiagnosis()
	{
		updateDiagnosis(this);
	}

	public void updateDiagnosis(MTreatmentDoc doc)
	{
		String diagnosis = "";
		int id = 0;
		String sql = "SELECT hms_pddiagnosis_ID, diag.name FROM adempiere.hms_pddiagnosis pd "
				+ " INNER JOIN adempiere.hms_diagnosis diag ON diag.hms_diagnosis_ID = pd.hms_diagnosis_ID"
				+ " WHERE hms_treatment_doc_ID= " + doc.gethms_treatment_doc_ID() + "  ORDER BY pd.created ";

		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			if (rs.next())
			{
				id = rs.getInt(1);
				diagnosis = rs.getString(2);

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		doc.sethms_diagnosis_ID(id);
		doc.setdiagnosis(diagnosis);
		doc.save();
	}

	public void exit()
	{
		deleteBills(gethms_treatment_doc_ID());
		deleteInvoices(gethms_treatment_doc_ID());
		deleteTests(gethms_treatment_doc_ID());
		this.delete(true);
	}

	public void deleteBills(int treatID)
	{
		String sql = "SELECT * FROM adempiere.hms_billing WHERE hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, null);
				bill.delete(true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void deleteInvoices(int treatID)
	{
		String sql = "SELECT * FROM adempiere.hms_invoice WHERE hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			while (rs.next())
			{
				HmsInvoice inv = new HmsInvoice(getCtx(), rs, null);
				inv.delete(true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void deleteTests(int treatID)
	{
		String sql = "SELECT * FROM adempiere.hms_specimen_requests WHERE hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			while (rs.next())
			{
				MSpecimenRequest req = new MSpecimenRequest(getCtx(), rs, null);
				deleteParameters(req.get_ID());
				req.delete(true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void deleteParameters(int hms_specimen_requests_id)
	{
		ArrayList<Integer> requestLineIDs = new ArrayList<>();
		ArrayList<Integer> resultIDs = new ArrayList<>();

		String sql1 = "select hms_specimen_r_line_id from adempiere.hms_specimen_r_line where hms_specimen_requests_id ="
				+ hms_specimen_requests_id;
		PreparedStatement stm1 = null;
		try
		{
			stm1 = DB.prepareStatement(sql1, null);
			ResultSet rs = stm1.executeQuery();
			while (rs.next())
			{
				int lineID = rs.getInt(1);
				requestLineIDs.add(lineID);
				String sql2 = "select hms_lab_results_id from adempiere.hms_lab_results  where hms_specimen_r_line_id ="
						+ lineID;
				PreparedStatement stm2 = null;
				stm2 = DB.prepareStatement(sql2, null);
				ResultSet rs2 = stm2.executeQuery();
				try
				{
					while (rs2.next())
					{
						int hms_lab_results_id = rs2.getInt(1);
						resultIDs.add(hms_lab_results_id);
					}
				} catch (Exception ex)
				{

				} finally
				{
					try
					{
						stm2.close();
						stm2 = null;
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		} catch (Exception ex)
		{

		} finally
		{
			try
			{
				stm1.close();
				stm1 = null;
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		for (Integer resultID : resultIDs)
		{
			MResult r = new MResult(getCtx(), resultID, null);
			r.delete(true);
		}
		for (Integer requestLineID : requestLineIDs)
		{
			MSpecimenRequestLine line = new MSpecimenRequestLine(getCtx(), requestLineID, null);
			line.delete(true);
		}
	}

	public void updateTotalOpenBalance(BigDecimal amt)
	{
		this.setopen_balance(this.getopen_balance().add(amt));
	}
}
