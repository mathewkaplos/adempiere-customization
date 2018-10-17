package org.zenith.callout;

import java.util.Properties;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class Attend extends CalloutEngine
{
	public String attend(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		int AD_User_ID = Env.getContextAsInt(ctx, WindowNo, "AD_User_ID");
		mTab.setValue("attended_by_ID", AD_User_ID);
		mTab.setValue("state", "a");
		return "";
	}

	// org.zenith.callout.lab_seen
	public String lab_seen(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		mTab.setValue("state", "b");
		return "";
	}
}