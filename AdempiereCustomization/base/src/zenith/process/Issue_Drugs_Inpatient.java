package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MInpatient_Orders;

public class Issue_Drugs_Inpatient extends SvrProcess
{
	private int wardManagementID = 0;
	private Integer[] orderIDs = null;
	Integer[] docIDs = null;

	@Override
	protected void prepare()
	{
		wardManagementID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		getDetails();// this should be called first
		System.out.println(1);
		if (docIDs != null)
		{
			if (docIDs.length > 0)
			{
				System.out.println(2);
				for (int i = 0; i < docIDs.length; i++)
				{
					// int bpID = getBPartnerID(docIDs[i].intValue());
					// returns lines specific to one patient
					OrderLineInpatient[] lines = getLines(wardManagementID, docIDs[i]);
					try
					{
						createOder(getCtx(), lines);

					} catch (Exception e)
					{
						StackTraceElement[] st = e.getStackTrace();
						for (int i1 = 0; i1 < st.length; i1++)
						{
							System.out.println(st[i1].getClassName() + " - " + st[i1].getMethodName() + " - "
									+ st[i1].getLineNumber());
						}
					}

				}
			}
		} else
		{
			System.out.println("treatment doc is null");
		}
		updateOrder();
		return null;
	}

	private void updateOrder()
	{ // set issued to true

		if (orderIDs.length > 0)
		{
			// set order to issued
			for (int i = 0; i < orderIDs.length; i++)
			{
				MInpatient_Orders io = new MInpatient_Orders(getCtx(), orderIDs[i].intValue(), get_TrxName());
				io.setorder_issued(true);
				io.saveEx();
			}
		}
	}

	private void getDetails()
	{ // set/assaign DocIds
		System.out.println("hey1");
		List<Integer> orderIdList = new ArrayList<Integer>();
		List<Integer> docIdList = new ArrayList<Integer>();
		PreparedStatement stm = null;
		String sql = "SELECT io.hms_inpatient_orders_id ,io.hms_treatment_doc_id from "
				+ "adempiere.hms_inpatient_orders io" + " INNER JOIN adempiere.hms_treatment_doc  doc "
				+ "ON doc.hms_treatment_doc_id =io.hms_treatment_doc_id "
				+ "INNER JOIN adempiere.hms_ward_management1 wm"
				+ " ON wm.hms_ward_management1_id = doc.hms_ward_management1_id" + " WHERE wm.hms_ward_management1_id ="
				+ wardManagementID + " AND io.order_sent='Y' AND io.order_recieved='Y'"
				+ " AND io.order_issued='N' AND io.order_nullified='N'" + " AND io.order_closed='N'";
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int hms_inpatient_orders_id = rs.getInt(1);
				orderIdList.add(hms_inpatient_orders_id);
				int hms_treatment_doc_id = rs.getInt(2);
				if (hms_treatment_doc_id > 0)
				{
					docIdList.add(hms_treatment_doc_id);
				}
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(stm);
			stm = null;
		}
		orderIDs = orderIdList.toArray(new Integer[orderIdList.size()]);
		// get only unique IDs
		Set<Integer> uniqueIDs = new HashSet<Integer>(docIdList);
		docIDs = uniqueIDs.toArray(new Integer[uniqueIDs.size()]);

		// return orderIDs;
	}

	class OrderLineInpatient
	{
		private int _C_BPartner_ID = 0;
		private int _productID = 0;
		private BigDecimal _qty = Env.ZERO;
		private int _C_BP_Group_ID = 0;
		private int _docID;

		OrderLineInpatient(int C_BPartner_ID, int productID, BigDecimal qty, int C_BP_Group_ID, int docID)
		{
			this._C_BPartner_ID = C_BPartner_ID;
			this._productID = productID;
			this._qty = qty;
			this._C_BP_Group_ID = C_BP_Group_ID;
			this._docID = docID;
		}

		public int getC_BPartner_ID()
		{
			return _C_BPartner_ID;
		}

		public int getProductID()
		{
			return _productID;
		}

		public BigDecimal getQty()
		{
			return _qty;
		}

		public int getC_BP_Group_ID()
		{
			return _C_BP_Group_ID;
		}

		/**
		 * @return the _docID
		 */
		public int get_docID()
		{
			return _docID;
		}

		/**
		 * @param _docID
		 *            the _docID to set
		 */
		public void set_docID(int docID)
		{
			this._docID = docID;
		}
	}

	private OrderLineInpatient[] getLines(int ward_managementID, int treatment_docID)
	{
		System.out.println(ward_managementID);
		System.out.println(treatment_docID);
		List<OrderLineInpatient> list = new ArrayList<OrderLineInpatient>();
		PreparedStatement stm = null;
		String sql = "SELECT doc.hms_treatment_doc_id, io.m_product_id, ROUND(io.qtyordered,0),doc.c_bp_group_id,"
				+ " io.order_issued,io.hms_inpatient_orders_id,bp.C_BPartner_ID    from adempiere.hms_inpatient_orders io "
				+ " INNER JOIN adempiere.hms_treatment_doc  doc  ON doc.hms_treatment_doc_id =io.hms_treatment_doc_id  "
				+ " INNER JOIN adempiere.hms_ward_management1 wm ON wm.hms_ward_management1_id = doc.hms_ward_management1_id"
				+ " INNER JOIN adempiere.C_BPartner bp ON bp.C_BPartner_ID =doc.C_BPartner_ID "
				+ " WHERE (wm.hms_ward_management1_id = " + ward_managementID + "   AND doc.hms_treatment_doc_id= "
				+ treatment_docID
				+ "  AND io.order_nullified='N' AND io.order_issued='N' AND  io.order_issued='N' AND io.order_recieved='Y'"
				+ " AND io.order_sent='Y')";

		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();

			while (rs.next())
			{
				int hms_treatment_doc_id = rs.getInt(1);
				int m_product_id = rs.getInt(2);
				BigDecimal quantity = rs.getBigDecimal(3);
				int C_BP_Group_ID = rs.getInt(4);
				int C_BPartner_ID = rs.getInt(7);
				OrderLineInpatient line = new OrderLineInpatient(C_BPartner_ID, m_product_id, quantity, C_BP_Group_ID,
						hms_treatment_doc_id);
				list.add(line);
			}
		} catch (Exception e)
		{
			System.out.println("Catch--" + e.getMessage());

		} finally
		{
			DB.close(stm);
			stm = null;
		}

		return list.toArray(new OrderLineInpatient[list.size()]);
	}

	private void createOder(Properties ctx, OrderLineInpatient[] set)
	{
		try
		{
			MOrder order = new MOrder(ctx, 0, get_TrxName());
			order.setDescription("Pharmacy order");
			order.setC_DocTypeTarget_ID(getDOCSUBTYPESO_OnCreditOrder_ID()); // On
																				// Credit
																				// order
			order.setC_BPartner_ID(set[0].getC_BPartner_ID());
			order.setTreatmentDocID(set[0].get_docID());

			order.setSalesRep_ID(Env.getContextAsInt(ctx, "#AD_User_ID")); // GardenAdmin
			// order.setDeliveryRule(MOrder.DELIVERYRULE_Force);
			// C_Currency_ID
			// order.setC_Currency_ID(Env.getContextAsInt(getCtx(),
			// "#C_Currency_ID"));
			order.setC_Currency_ID(266); // KES--to change this
			// C_PaymentTerm_ID
			order.setC_PaymentTerm_ID(Env.getContextAsInt(ctx, "#C_PaymentTerm_ID"));
			// M_Warehouse_ID
			order.setM_Warehouse_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));
			// M_PriceList_ID
			// order.setM_PriceList_ID(Env.getContextAsInt(ctx,
			// "#M_PriceList_ID"));

			order.setDocStatus(MOrder.DOCSTATUS_Drafted);
			order.setDocAction(MOrder.DOCACTION_Complete);
			// order.setC_DocTypeTarget_ID(1000028); // Pre-pay Order
			order.setC_DocType_ID(getDOCSUBTYPESO_OnCreditOrder_ID());
			order.setInvoiceRule("I");
			order.setDeliveryRule("R");
			order.setFreightCostRule("I");
			order.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
			order.setCopyFrom("N");
			// C_ConversionType_ID
			order.setC_ConversionType_ID(Env.getContextAsInt(ctx, "#C_ConversionType_ID"));
			order.setPaymentRule("B");
			
			order.save();

			for (int i = 0; i < set.length; i++)
			{
				MOrderLine line = new MOrderLine(order);
				line.setM_Product_ID(set[i].getProductID());
				BigDecimal Productprice = getPrice(set[i].getC_BP_Group_ID(), set[i].getProductID());

				line.setC_Tax_ID(1000000); // Exempt

				line.setPrice(Productprice);
				line.setQty(set[i].getQty());
				line.setLineNetAmt(set[i].getQty().multiply(Productprice));
				line.setDescription("In Patient Order");
				line.setitem_type("PRO");
				line.setPriceList(Productprice);
				line.setPriceEntered(Productprice);
				line.save();
			}

			order.completeIt();
			order.setDocStatus(MOrder.DOCSTATUS_Completed);
			order.save();

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// DOCSUBTYPESO_OnCreditOrder ID
	private int getDOCSUBTYPESO_OnCreditOrder_ID()
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(getCtx(), "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_OnCredit + "'";
		docTypeID = DB.getSQLValue(get_TrxName(), sql);
		return docTypeID;
	}

	private BigDecimal getPrice(int C_BP_Group_ID, int M_Product_ID)
	{
		int M_Pricelist_ID = 0;
		String sql = "SELECT M_Pricelist_ID from adempiere.C_BP_Group WHERE C_BP_Group_ID=" + C_BP_Group_ID;
		M_Pricelist_ID = DB.getSQLValue(get_TrxName(), sql);
		if (M_Pricelist_ID != 0)
		{
			int M_Pricelist_version_ID = getPriceListVersionID(M_Pricelist_ID);
			if (M_Pricelist_version_ID != 0)
			{
				BigDecimal price = getProductPrice(M_Product_ID, M_Pricelist_version_ID);
				if (price != null && price.compareTo(Env.ZERO) != 0)
				{
					return price;
				} else
				{
					return getAnyPrice(M_Product_ID, "( The Price for the Pricelist Version is ZERO (0). )");
				}

			} else
			{
				return getAnyPrice(M_Product_ID, "( The Pricelist has no active Pricelist Version. )");
			}

		} else
		{
			return getAnyPrice(M_Product_ID, "( The Patient Group has no pricelist defined. )");
		}
	}

	private int getPriceListVersionID(int M_Pricelist_ID)
	{
		int M_Pricelist_version_ID = 0;
		String sql = " SELECT MAX(M_Pricelist_version_ID) from adempiere.M_Pricelist_version" + " WHERE M_Pricelist_ID="
				+ M_Pricelist_ID + " AND isactive='Y'";
		M_Pricelist_version_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_version_ID;

	}

	// Product Pricelist......
	private BigDecimal getProductPrice(int M_Product_ID, int M_Pricelist_version_ID)
	{
		BigDecimal priceList = Env.ZERO;
		String sql = "SELECT pricelist FROM adempiere.M_Productprice WHERE M_Product_ID= ? AND  M_Pricelist_version_ID = ?";
		priceList = DB.getSQLValueBD(get_TrxName(), sql, M_Product_ID, M_Pricelist_version_ID);
		return priceList;
	}
	/*
	 * get any price of product if: 1)Business partner Group has no Price list.
	 * 2) The product has no price list that belongs to the business partner
	 * group (2nd is not applied anyway) .3) The product price list is zero.
	 * 4)The price list has no active price-list-version
	 */

	private BigDecimal getAnyPrice(int M_Product_ID, String failMsg)
	{
		BigDecimal price = Env.ZERO;
		String sql = "SELECT MAX(pricelist) FROM adempiere.M_Productprice WHERE M_Product_ID=" + M_Product_ID;
		price = DB.getSQLValueBD(get_TrxName(), sql);
		if (price == null || price.compareTo(Env.ZERO) == 0)
		{
			String m_processMsg = "Product/Service: " + MProduct.get(getCtx(), M_Product_ID).getName().toUpperCase()
					+ "  is not in valid price list!. " + failMsg;
			throw new AdempiereException(m_processMsg);
		}
		return price.setScale(2, RoundingMode.CEILING);
	}

}
