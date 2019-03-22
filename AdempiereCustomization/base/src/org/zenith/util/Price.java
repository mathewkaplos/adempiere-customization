package org.zenith.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JOptionPane;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MTreatmentDoc;

public class Price
{
	private int M_Product_ID = 0;
	private int hms_treatment_doc_id = 0;
	private int C_BP_Group_ID = 0;

	private String _trxName = null;

	public Price(int M_Product_ID, int hms_treatment_doc_id)
	{
		this.M_Product_ID = M_Product_ID;
		this.hms_treatment_doc_id = hms_treatment_doc_id;
	}

	public Price(int M_Product_ID, int C_BP_Group_ID, String trxName)
	{
		this.M_Product_ID = M_Product_ID;
		this.C_BP_Group_ID = C_BP_Group_ID;
		this._trxName = trxName;
	}

	public BigDecimal getPrice()
	{
		int M_Pricelist_ID = 0;
		BigDecimal amount = Env.ZERO;
		if (C_BP_Group_ID > 0)
			M_Pricelist_ID = getPriceListID(C_BP_Group_ID);
		else
			M_Pricelist_ID = getPriceListID(getC_BP_Group_ID(hms_treatment_doc_id));
		if (M_Pricelist_ID != 0)
		{
			int M_Pricelist_version_ID = getPriceListVersionID(M_Pricelist_ID);
			if (M_Pricelist_version_ID != 0)
			{
				BigDecimal price = getProductPrice(M_Product_ID, M_Pricelist_version_ID);
				if (price == null)
				{
					returnNoPrice("( The Patient Group has no active Price for this product!. )");
				}
				amount = price;

			} else
			{
				// amount = getAnyPrice(M_Product_ID, "( The Pricelist has no
				// active Pricelist Version. )");
				returnNoPrice("( The Pricelist has no active Pricelist Version. )");
				return null;
			}

		} else
		{
			returnNoPrice("( The Patient Group has no pricelist defined.. )");
			// amount = getAnyPrice(M_Product_ID, "( The Patient Group has no
			// pricelist defined. )");
			return null;
		}
		return new BigDecimal(amount.stripTrailingZeros().toPlainString());
	}

	private int getC_BP_Group_ID(int hms_treatment_doc_id)
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), hms_treatment_doc_id, null);
		return doc.getC_BP_Group_ID();
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
		return _trxName;
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
		if (price == null)
		{
			return Env.ZERO;
		}
		return price.setScale(2, RoundingMode.CEILING);
	}

	private void returnNoPrice(String msg)
	{
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.INFORMATION_MESSAGE);
	}

}
