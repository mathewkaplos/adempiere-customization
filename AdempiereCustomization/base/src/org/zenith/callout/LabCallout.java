package org.zenith.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class LabCallout extends CalloutEngine
{
	// org.zenith.callout.LabCallout.numericResult
	public String numericResult(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";

		BigDecimal max = (BigDecimal) mTab.getValue("Level_Max");
		BigDecimal min = (BigDecimal) mTab.getValue("Level_Min");
		if (max.compareTo(Env.ZERO) != 0)
		{
			BigDecimal val = (BigDecimal) value;
			if (val.compareTo(min) == -1)
			{
				mTab.setValue("flag", "L");
			} else if (val.compareTo(max) == 1)
			{
				mTab.setValue("flag", "H");
			} else
			{
				mTab.setValue("flag", "");
			}
		}

		return "";
	}

	// org.zenith.callout.LabCallout.edit
	public String edit(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		mTab.setValue("results", null);

		return "";
	}
}
