package org.zenith.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class InpatientOtherCharges extends CalloutEngine
{

	public String amt(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		BigDecimal total = Env.ZERO;
		BigDecimal qty = (BigDecimal) mTab.getValue("qty");
		BigDecimal amt = (BigDecimal) mTab.getValue("Amt");
		total = qty.multiply(amt);
		mTab.setValue("TotalAmt", total);
		// TotalAmt
		return "";
	}
	public String qty(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		BigDecimal total = Env.ZERO;
		BigDecimal qty = (BigDecimal) mTab.getValue("qty");
		BigDecimal amt = (BigDecimal) mTab.getValue("Amt");
		total = qty.multiply(amt);
		mTab.setValue("TotalAmt", total);
		// TotalAmt
		return "";
	}

	public String product(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		int M_Product_ID = (Integer) value;
		int hms_treatment_doc_id = Env.getContextAsInt(ctx, WindowNo, "hms_treatment_doc_ID");
		BigDecimal amt = doIt(M_Product_ID, hms_treatment_doc_id);
		// Amt
		mTab.setValue("Amt", amt);

		// TotalAmt
		BigDecimal total = Env.ZERO;
		BigDecimal qty = (BigDecimal) mTab.getValue("qty");
		total = qty.multiply(amt);
		mTab.setValue("TotalAmt", total);

		System.out.println(amt);
		return "";
	}

	int getC_BP_Group_ID(int hms_treatment_doc_id)
	{

		String sql = "select C_BP_Group_ID FROM hms_treatment_doc WHERE hms_treatment_doc_id =" + hms_treatment_doc_id;
		int C_BP_Group_ID = DB.getSQLValue(get_TrxName(), sql);
		return C_BP_Group_ID;
	}

	protected BigDecimal doIt(int M_Product_ID, int hms_treatment_doc_id)
	{
		// AddDefaultLocation();

		BigDecimal amount = Env.ZERO;
		int M_Pricelist_ID = getPriceListID(getC_BP_Group_ID(hms_treatment_doc_id));
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

	private String get_TrxName()
	{
		return null;
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
			throw new AdempiereException(m_processMsg);
		}
		return price.setScale(2, RoundingMode.CEILING);
	}
}
