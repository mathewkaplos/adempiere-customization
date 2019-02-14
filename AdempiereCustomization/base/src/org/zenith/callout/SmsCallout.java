package org.zenith.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;

public class SmsCallout extends CalloutEngine
{
	public String patient(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		int C_BPartner_ID = (int) value;
		MBPartner partner = new MBPartner(ctx, C_BPartner_ID, null);
		String tel_no = partner.getTelNo();

		mTab.setValue("tel_no", tel_no);
		return "";
	}
}
