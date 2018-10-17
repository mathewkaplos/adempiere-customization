package org.zenith.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CheckIn extends CalloutEngine
{
	public String checkIn(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		mTab.setValue("check_in_date", new Timestamp(System.currentTimeMillis()));

		return "";
	}

}
