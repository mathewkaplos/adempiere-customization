package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MultiMap;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MInpatient_Orders;
import zenith.model.MTreatmentDoc;

public class Dispense_Drugs_Inpatient extends SvrProcess
{
	private String dispensingTime = "";

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("dispensingTime"))
			{
				dispensingTime = para[i].getParameterAsString();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		String time = "";
		if (dispensingTime.equals("1"))
			time = "morning";
		if (dispensingTime.equals("2"))
			time = "midmorning";
		if (dispensingTime.equals("3"))
			time = "midday";
		if (dispensingTime.equals("4"))
			time = "afternoon";
		if (dispensingTime.equals("5"))
			time = "evenning";
		if (dispensingTime.equals("6"))
			time = "midnight";
		performWork(time);
		return null;
	}
	/*
	 * -> M_Product_ID -> quantity -> C_BPartner_ID -> price -> treatment ID
	 * 
	 */

	void performWork(String time)
	{
		String timeQty = time + "_qty";
		String timeIssued = time + "_issued";
		String whereClause = " WHERE wm.hms_ward_management1_id =" + getRecord_ID() + " and " + timeIssued + " = 'N'";
		PreparedStatement stm = null;
		StringBuffer sb = new StringBuffer(" SELECT io." + timeQty
				+ " ,io.M_Product_ID, io.C_BPartner_ID, io.hms_treatment_doc_id ,io.hms_inpatient_orders_id FROM adempiere.hms_inpatient_orders io "
				+ " INNER JOIN adempiere.hms_treatment_doc doc ON doc.hms_treatment_doc_id =io.hms_treatment_doc_id "
				+ " INNER JOIN adempiere.hms_ward_management1 wm ON wm.hms_ward_management1_id =doc.hms_ward_management1_id ");
		sb.append(whereClause);
		try
		{
			stm = DB.prepareStatement(sb.toString(), get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				BigDecimal qty = rs.getBigDecimal(1);
				int M_Product_ID = rs.getInt(2);
				int C_BPartner_ID = rs.getInt(3);
				int hms_treatment_doc_id = rs.getInt(4);
				int hms_inpatient_orders_id = rs.getInt(5);

				// MInpatient_Orders io = new MInpatient_Orders(getCtx(),
				// hms_inpatient_orders_id, get_TrxName());
				if (qty != null && qty.intValue() > 0)
				{
					createOrder(C_BPartner_ID, M_Product_ID, hms_treatment_doc_id, qty);

					String update = "UPDATE adempiere.hms_inpatient_orders io SET " + time + "_issued"
							+ " = 'Y' WHERE hms_inpatient_orders_id= " + hms_inpatient_orders_id;
					DB.executeUpdate(update, get_TrxName());
				}
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(stm);
			stm = null;
		}
	}

	private void createOrder(int C_BPartner_ID, int M_Product_ID, int docID, BigDecimal qty)
	{
		MOrder order = new MOrder(getCtx(), 0, get_TrxName());
		order.setDescription("Inpatient order");
		order.setC_DocTypeTarget_ID(getDOCSUBTYPESO_OnCreditOrder_ID()); // On
																			// Credit
																			// order
		order.setC_BPartner_ID(C_BPartner_ID);
		order.setTreatmentDocID(docID);

		order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID")); // GardenAdmin
		order.setC_Currency_ID(266); // KES--to change this
		// C_PaymentTerm_ID
		order.setC_PaymentTerm_ID(Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID"));
		// M_Warehouse_ID
		order.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));

		order.setDocStatus(MOrder.DOCSTATUS_Drafted);
		order.setDocAction(MOrder.DOCACTION_Complete);
		order.setC_DocType_ID(getDOCSUBTYPESO_OnCreditOrder_ID());
		order.setInvoiceRule("I");
		order.setDeliveryRule("R");
		order.setFreightCostRule("I");
		order.setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
		order.setCopyFrom("N");
		// C_ConversionType_ID
		order.setC_ConversionType_ID(Env.getContextAsInt(getCtx(), "#C_ConversionType_ID"));
		order.setPaymentRule("B");

		order.save();

		MOrderLine line = new MOrderLine(order);
		line.setM_Product_ID(M_Product_ID);
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), docID, get_TrxName());
		BigDecimal Productprice = getPrice(doc.getC_BP_Group_ID(), M_Product_ID);

		line.setC_Tax_ID(1000000); // Exempt

		line.setPrice(Productprice);
		line.setQty(qty);
		line.setLineNetAmt(qty.multiply(Productprice));
		line.setDescription("In Patient Order");
		line.setitem_type("PRO");
		line.setPriceList(Productprice);
		line.setPriceEntered(Productprice);
		line.save();

		order.completeIt();
		order.setDocStatus(MOrder.DOCSTATUS_Completed);
		order.save();
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
