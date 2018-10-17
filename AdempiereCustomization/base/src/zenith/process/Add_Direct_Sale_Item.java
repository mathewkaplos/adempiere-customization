package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MBilling;

public class Add_Direct_Sale_Item extends SvrProcess
{
	private int M_Product_ID = 0;
	private BigDecimal Qty = Env.ZERO;
	private int hms_payment_ID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
				// charge_per_bed
			} else if (name.equals("Qty"))
			{
				Qty = (BigDecimal) para[i].getParameter();
			} // else
				// log.log(Level.SEVERE, "Unknown Parameter: " + name);
			hms_payment_ID = getRecord_ID();
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		add();
		return null;
	}

	private void add()
	{
		MBilling bill = new MBilling(Env.getCtx(), 0, null);
		// bill.sethms_treatment_doc_ID(hms_treatment_doc_ID);
		bill.setC_BPartner_ID(CreateHospitalDefaults.BUSINESS_PARTNER_DIRECT_SALES_ID);
		bill.setM_Product_ID(M_Product_ID);
		bill.setQty(Qty);
		BigDecimal amt = doIt(M_Product_ID);
		bill.setPrice(amt);
		bill.setPriceActual(amt);
		bill.setLineNetAmt(Qty.multiply(amt));
		bill.setTotalAmt(Qty.multiply(amt));
		bill.setBalance(Qty.multiply(amt));
		bill.setunits_per_freq(1);
		// bill.setfreq(1);
		// bill.setdays(1);
		// bill.setdosage(1);
		// bill.setDescription("");
		// bill.setdosage_description("");

		bill.setis_prescription(true);
		bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		bill.sethms_payment_ID(hms_payment_ID);
		bill.setis_direct_sale(true);
		bill.setpay(true);
		bill.save();

	}

	protected BigDecimal doIt(int M_Product_ID)
	{
		// AddDefaultLocation();

		BigDecimal amount = Env.ZERO;
		int M_Pricelist_ID = getPriceListID(CreateHospitalDefaults.PATIENT_GROUP_ID_CASH);
		if (M_Pricelist_ID != 0)
		{
			int M_Pricelist_version_ID = getPriceListVersionID(M_Pricelist_ID);
			if (M_Pricelist_version_ID != 0)
			{
				BigDecimal price = getProductPrice(M_Product_ID, M_Pricelist_version_ID);
				if (price != null && price.compareTo(Env.ZERO) != 0)
					amount = price;
				else
					amount = getAnyPrice(M_Product_ID, "( The Price for the Pricelist Version is ZERO (0). )");

			} else
			{
				amount = getAnyPrice(M_Product_ID, "( The Pricelist has no active Pricelist Version. )");
			}

		} else
		{
			amount = getAnyPrice(M_Product_ID, "( The Patient Group has no pricelist defined. )");
		}

		return amount;
	}

	private int getPriceListID(int C_BP_Group_ID)
	{
		int M_Pricelist_ID = 0;
		String sql = "SELECT M_Pricelist_ID from adempiere.C_BP_Group WHERE C_BP_Group_ID=" + C_BP_Group_ID;
		M_Pricelist_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_ID;
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
			String m_processMsg = "Product/Service: " + MProduct.get(Env.getCtx(), M_Product_ID).getName().toUpperCase()
					+ "  is not in valid price list!. " + failMsg;
			JOptionPane.showMessageDialog(null, m_processMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
			throw new AdempiereException(m_processMsg);
		}
		return price.setScale(2, RoundingMode.CEILING);
	}
}
