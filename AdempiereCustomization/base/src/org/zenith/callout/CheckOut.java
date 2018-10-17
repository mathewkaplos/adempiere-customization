package org.zenith.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CheckOut extends CalloutEngine
{
	public String checkOut(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		mTab.setValue("check_out_date", new Timestamp(System.currentTimeMillis()));

		return "";
	}
}
