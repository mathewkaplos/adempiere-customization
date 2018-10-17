package org.zenith.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.util.Env;
import org.compiere.util.Msg;

//units_per_freq
//freq
//days
//dosage

public class Prescription extends CalloutEngine
{
	// Unit Per Frequency
	public String unitsPerFrequency(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer scaled = (Integer) value;
		// Integer scaled = m_value.setScale(0, Integer.ROUND_HALF_UP);
		if (scaled == null || scaled.intValue() <= 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Value cannot be Zero(0) or less than Zero(0)");
			mTab.setValue("units_per_freq", 1);
		}
		// return null;
		mTab.setValue("units_per_freq", scaled);
		Integer freq = (Integer) mTab.getValue("freq");
		Integer days = (Integer) mTab.getValue("days");
		Integer dosage = scaled * freq * days;
		// mTab.setValue("dosage", dosage);
		mTab.setValue("qty", new BigDecimal(dosage));

		// Description
		String description = String.valueOf(scaled) + " X " + String.valueOf(freq);
		mTab.setValue("Description", description);
		return "";
	}

	// Frequency
	public String frequency(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer scaled = (Integer) value;
		// Integer scaled = m_value.setScale(0, Integer.ROUND_HALF_UP);
		if (scaled == null || scaled.intValue() <= 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Value cannot be Zero(0) or less than Zero(0)");
			mTab.setValue("freq", 1);
		}
		// return null;
		mTab.setValue("freq", scaled);
		Integer units = (Integer) mTab.getValue("units_per_freq");
		Integer days = (Integer) mTab.getValue("days");
		Integer dosage = scaled * units * days;
		// mTab.setValue("dosage", dosage.setScale(0,
		// Integer.ROUND_HALF_UP));
		mTab.setValue("qty", new BigDecimal(dosage));
		// Description
		String description = String.valueOf(units) + " X " + String.valueOf(scaled);
		mTab.setValue("Description", description);
		return "";
	}

	// Days
	public String days(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer scaled = (Integer) value;
		// Integer scaled = m_value.setScale(0, Integer.ROUND_HALF_UP);
		if (scaled == null || scaled.intValue() <= 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Value cannot be Zero(0) or less than Zero(0)");
			mTab.setValue("days", 1);
		}
		// return null;
		mTab.setValue("days", scaled);
		Integer units = (Integer) mTab.getValue("units_per_freq");
		Integer freq = (Integer) mTab.getValue("freq");
		Integer dosage = scaled * units * freq;
		// mTab.setValue("dosage", dosage);
		mTab.setValue("qty", new BigDecimal(dosage));
		// mTab.setValue("QtyEntered", dosage);
		return "";
	}

	// Days
	public String dosage(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		// int M_Product_ID = (int) mTab.getValue("M_Product_ID");
		BigDecimal m_value = (BigDecimal) value;
		BigDecimal scaled = m_value.setScale(0, RoundingMode.HALF_UP);
		if (scaled == null || scaled.intValue() <= 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Please enter a valid value ");
			// mTab.setValue("p_dosage", new Integer(1));
			Integer units = (Integer) mTab.getValue("units_per_freq");
			Integer freq = (Integer) mTab.getValue("freq");
			Integer days = (Integer) mTab.getValue("days");
			Integer dosage = units * freq * days;

			// Integer price = (Integer) mTab.getValue("Price");
			// Integer priceActual = price.multiply(dosage);
			// .setValue("lineamt", priceActual);
			mTab.setValue("dosage", new BigDecimal(dosage));
			// mTab.dataRefresh(row);
			// mField.refreshLookup();
			// mField.
		} else
		{
			Integer units = (Integer) mTab.getValue("units_per_freq");
			Integer freq = (Integer) mTab.getValue("freq");
			Integer days = (Integer) mTab.getValue("days");
			Integer dosage = units * freq * days;

			// Integer price = (Integer) mTab.getValue("Price");
			// priceActual = price.multiply(dosage);
			// .setValue("lineamt", priceActual);
			//// mTab.setValue("dosage", dosage);
			mTab.setValue("qty", new BigDecimal(dosage));
			
			BigDecimal price = (BigDecimal)mTab.getValue("price");
			mTab.setValue("linenetamt", new BigDecimal(dosage).multiply(price));
		}
		/**
		 * // Storage if (M_Product_ID != 0 && scaled.signum() > 0) // no
		 * negative (returns) { MProduct product = MProduct.get(ctx,
		 * M_Product_ID); if (product.isStocked()) { int M_Warehouse_ID =
		 * Env.getContextAsInt(ctx, windowNo, "M_Warehouse_ID"); int
		 * M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, windowNo,
		 * "M_AttributeSetInstance_ID"); Integer available =
		 * MStorage.getQtyAvailable(M_Warehouse_ID, M_Product_ID,
		 * M_AttributeSetInstance_ID, null); if (available == null) available =
		 * Env.ZERO; if (available.signum() == 0)
		 * mTab.fireDataStatusEEvent("NoQtyAvailable", "0", false); else if
		 * (available.compareTo(scaled) < 0)
		 * mTab.fireDataStatusEEvent("InsufficientQtyAvailable",
		 * available.toString(), false); else {
		 * 
		 * } } }
		 **/
		// QtyEntered
		// ..mTab.setValue("QtyEntered", scaled);

		// return null;

		return "";
	}
	/**
	 * public String asi(Properties ctx, int windowNo, GridTab mTab, GridField
	 * mField, Object value) { int M_Product_ID = (int)
	 * mTab.getValue("M_Product_ID"); Integer dosage = (Integer)
	 * mTab.getValue("dosage"); Integer scaled = dosage.setScale(0,
	 * RoundingMode.HALF_UP); // Storage if (M_Product_ID != 0 &&
	 * scaled.signum() > 0) // no negative (returns) { MProduct product =
	 * MProduct.get(ctx, M_Product_ID); if (product.isStocked()) { int
	 * M_Warehouse_ID = Env.getContextAsInt(ctx, windowNo, "M_Warehouse_ID");
	 * int M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, windowNo,
	 * "M_AttributeSetInstance_ID"); Integer available =
	 * MStorage.getQtyAvailable(M_Warehouse_ID, M_Product_ID,
	 * M_AttributeSetInstance_ID, null); if (available == null) available =
	 * Env.ZERO; if (available.signum() == 0)
	 * mTab.fireDataStatusEEvent("NoQtyAvailable", "0", false); else if
	 * (available.compareTo(scaled) < 0)
	 * mTab.fireDataStatusEEvent("InsufficientQtyAvailable",
	 * available.toString(), false); else {
	 * 
	 * } } } return ""; }
	 **/
}
