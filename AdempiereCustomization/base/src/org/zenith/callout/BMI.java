package org.zenith.callout;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class BMI extends CalloutEngine
{
	public String weight(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		// initialize to zero
		BigDecimal height = Env.ZERO;
		BigDecimal weight = Env.ZERO;
		BigDecimal bmi = Env.ZERO;

		// get values
		height = (BigDecimal) mTab.getValue("height");
		weight = (BigDecimal) mTab.getValue("weight");

		/*
		 * convert value
		 */
		height = height.divide(BigDecimal.valueOf(100));// Height in meters
		height = height.multiply(height);// square the height

		/*
		 * BMI
		 */
		// check if height is not Zero!
		if (height.compareTo(BigDecimal.ZERO) == 1)
			bmi = weight.divide(height, 2, RoundingMode.HALF_UP);
		// bmi=bmi.setScale(2, BigDecimal.ROUND_HALF_UP);

		// MathContext mc = new MathContext();
		mTab.setValue("bmi", bmi);
		return null;
	}

}
