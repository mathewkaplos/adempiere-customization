package org.zenith.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class DOB extends CalloutEngine
{
	public String dob(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Timestamp t = (Timestamp) value;
		System.out.println(t);
		// int AD_User_ID = Env.getContextAsInt(ctx, WindowNo, "AD_User_ID");
		if (t != null && t.compareTo(now) > 0)
		{

			javax.swing.JOptionPane.showMessageDialog(null,
					"The date of birth you entered is invalid. Please enter a valid date.");
			mTab.setValue("dob", null);
		}
		return "";
	}
}
