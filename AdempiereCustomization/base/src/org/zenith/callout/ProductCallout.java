package org.zenith.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_M_AttributeSetInstance;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.model.MTax;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class ProductCallout extends CalloutEngine
{
	/** Debug Steps */
	private boolean steps = false;

	/*************************************************************************
	 * Order Line - Product. - reset C_Charge_ID / M_AttributeSetInstance_ID -
	 * PriceList, PriceStd, PriceLimit, C_Currency_ID, EnforcePriceLimit - UOM
	 * Calls Tax
	 *
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            current Window No
	 * @param mTab
	 *            Grid Tab
	 * @param mField
	 *            Grid Field
	 * @param value
	 *            New Value
	 * @return null or error message
	 */
	public String product(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_Product_ID = (Integer) value;
		Integer M_AttributeSetInstance_ID = 0;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
		{
			return "";
		}
		if (steps)
			log.warning("init");
		// hms_prescription_line_ID
		// int hms_prescription_line_ID = (int)
		// mTab.getValue("hms_prescription_line_ID");
		// System.out.println("hms_prescription_line_ID=" +
		// hms_prescription_line_ID);
		// String sql1 = "SELECT hms_prescription_ID FROM hms_prescription_line
		// WHERE hms_prescription_line_ID="
		// + hms_prescription_line_ID;
		// hms_prescription_ID = DB.getSQLValue("", sql1);
		//System.out.println("hms_prescription_ID=" + hms_prescription_ID);

		MProduct product = MProduct.get(ctx, M_Product_ID.intValue());
		System.out.println(product.getName());
		I_M_AttributeSetInstance asi = product.getM_AttributeSetInstance();
		MProduct m_product = MProduct.get(Env.getCtx(), M_Product_ID);
		mTab.setValue("M_AttributeSetInstance_ID", m_product.getEnvAttributeSetInstance(ctx, WindowNo));
		if (Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
				&& Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID") != 0)
			mTab.setValue("M_AttributeSetInstance_ID",
					Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID"));
		else
		{
			mTab.setValue("M_AttributeSetInstance_ID", asi.getM_AttributeSetInstance_ID());
		}

		if (Env.isSOTrx(ctx, WindowNo))
		{
			if (product.isStocked())
			{
				BigDecimal QtyOrdered = (BigDecimal) mTab.getValue("dosage");
				int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo, "M_Warehouse_ID");
				M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, WindowNo, "M_AttributeSetInstance_ID");
				BigDecimal available = MStorage.getQtyAvailable(M_Warehouse_ID, M_Product_ID.intValue(),
						M_AttributeSetInstance_ID, null);
				if (available == null)
					available = Env.ZERO;
				if (available.signum() == 0)
					mTab.fireDataStatusEEvent("NoQtyAvailable", "0", false);
				else if (available.compareTo(QtyOrdered) < 0)
					mTab.fireDataStatusEEvent("InsufficientQtyAvailable", available.toString(), false);
				else
				{
					System.out.println("deleted stuff");
				}
			}
		}

		return "";
	} // product

	/**
	 * Order Line - Tax. - basis: Product, Charge, BPartner Location - sets
	 * C_Tax_ID Calles Amount
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            current Window No
	 * @param mTab
	 *            Grid Tab
	 * @param mField
	 *            Grid Field
	 * @param value
	 *            New Value
	 * @return null or error message
	 */

	public String tax2(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		String column = mField.getColumnName();
		if (value == null)
			return "";
		if (steps)
			log.warning("init");

		// Check Product
		int M_Product_ID = 0;
		if (column.equals("M_Product_ID"))
			M_Product_ID = ((Integer) value).intValue();
		else
			M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		log.fine("Product=" + M_Product_ID);
		if (M_Product_ID == 0)
			return amt(ctx, WindowNo, mTab, mField, value); //
		return amt(ctx, WindowNo, mTab, mField, value);
	} // tax

	/**
	 * Order Line - Amount. - called from QtyOrdered, Discount and PriceActual -
	 * calculates Discount or Actual Amount - calculates LineNetAmt - enforces
	 * PriceLimit
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            current Window No
	 * @param mTab
	 *            Grid Tab
	 * @param mField
	 *            Grid Field
	 * @param value
	 *            New Value
	 * @return null or error message
	 */

	public String amt(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";

		if (steps)
			log.warning("init");
		int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID");
		BigDecimal QtyEntered = (BigDecimal) mTab.getValue("dosage");
		// QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
		//
		BigDecimal PriceEntered = (BigDecimal) mTab.getValue("Price");

		// Qty changed - recalc price
		if ((mField.getColumnName().equals("dosage") || mField.getColumnName().equals("M_Product_ID")))

		{
			int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
			boolean IsSOTrx = true;
			MProductPricing pp = new MProductPricing(M_Product_ID, C_BPartner_ID, QtyEntered, IsSOTrx);
			pp.setM_PriceList_ID(M_PriceList_ID);
			int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
			pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
			Timestamp date = Env.getContextAsDate(ctx, "Date");
			pp.setPriceDate(date);
			//
			PriceEntered = MUOMConversion.convertProductFrom(ctx, M_Product_ID, C_UOM_To_ID, pp.getPriceStd());
			if (PriceEntered == null)
				PriceEntered = pp.getPriceStd();
			//
			mTab.setValue("Price", pp.getPriceStd());
		} else if (mField.getColumnName().equals("Price"))
		{
			PriceEntered = (BigDecimal) value;
			PriceEntered = MUOMConversion.convertProductFrom(ctx, M_Product_ID, C_UOM_To_ID, PriceEntered);
			mTab.setValue("Price", PriceEntered);
		}
		// Line Net Amt
		BigDecimal LineNetAmt = QtyEntered.multiply(PriceEntered);
		log.info("LineAmt=" + LineNetAmt);
		mTab.setValue("LineAmt", LineNetAmt);
		//
		return "";
	} // amt

	/**
	 * Order Line - Quantity. - called from C_UOM_ID, QtyEntered, QtyOrdered -
	 * enforces qty UOM relationship
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            current Window No
	 * @param mTab
	 *            Grid Tab
	 * @param mField
	 *            Grid Field
	 * @param value
	 *            New Value
	 * @return null or error message
	 */
	public String qty(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps)
			log.warning("init - M_Product_ID=" + M_Product_ID + " - ");
		BigDecimal QtyEntered = Env.ZERO;

		// No Product
		if (M_Product_ID == 0)
		{
			QtyEntered = (BigDecimal) mTab.getValue("dosage");
		}

		// QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("dosage"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal) value;
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), BigDecimal.ROUND_HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID + "; QtyEntered=" + QtyEntered + "->"
						+ QtyEntered1);
				QtyEntered = QtyEntered1;
				mTab.setValue("dosage", QtyEntered);
			}
			QtyEntered = MUOMConversion.convertProductFrom(ctx, M_Product_ID, C_UOM_To_ID, QtyEntered);
		}

		return "";
	} // qty
}
