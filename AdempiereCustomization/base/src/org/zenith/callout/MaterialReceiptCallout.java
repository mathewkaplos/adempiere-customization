package org.zenith.callout;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class MaterialReceiptCallout extends CalloutEngine
{
	MathContext mathContext = new MathContext(2, RoundingMode.HALF_UP);

	public String unitPrice(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		BigDecimal PriceActual = (BigDecimal) value;
		BigDecimal QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
		BigDecimal LineNetAmt = PriceActual.multiply(QtyEntered);
		mTab.setValue("LineNetAmt", LineNetAmt);

		return NO_ERROR;
	}

	// org.zenith.callout.MaterialReceiptCallout.qty
	public String total(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		BigDecimal total = (BigDecimal) value;
		if (total.compareTo(Env.ZERO) > 0)
		{
			BigDecimal QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			BigDecimal PriceActual = total.divide(QtyEntered, 2,RoundingMode.HALF_UP);
			mTab.setValue("PriceActual", PriceActual);

		}

		return NO_ERROR;
	}

	public String qty(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		BigDecimal QtyEntered = (BigDecimal) value;
		if (QtyEntered.compareTo(Env.ZERO) > 0)
		{
			BigDecimal LineNetAmt = (BigDecimal) mTab.getValue("LineNetAmt");
			BigDecimal PriceActual = LineNetAmt.divide(QtyEntered, 2,RoundingMode.HALF_UP);
			mTab.setValue("PriceActual", PriceActual);
		}

		return NO_ERROR;
	}

}
