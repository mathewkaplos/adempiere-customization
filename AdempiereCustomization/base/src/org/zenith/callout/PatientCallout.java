package org.zenith.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.MTreatmentDoc;

public class PatientCallout extends CalloutEngine
{
	public String book(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Object obj = mTab.getValue("C_BPartner_ID");
		System.out.println(obj);
		if (obj == null)
			return null;
		int C_BPartner_ID = (int) obj;
		boolean no_consultation_fee = false;
		String visit_type = "N";
		// org.zenith.callout.PatientCallout.book
		MBPartner bp = new MBPartner(Env.getCtx(), C_BPartner_ID, null);
		MTreatmentDoc doc = bp.getLastVisitDoc();
		if (doc == null)
		{
			// new patient
			visit_type = "N";
			// will pay
			no_consultation_fee = false;
		} else
		{
			Timestamp latestTime = doc.getCreated();
			final long REBOOKING_INTERVAL = 168 * 60 * 60 * 1000;

			long lastBookTime = System.currentTimeMillis() - REBOOKING_INTERVAL;
			if (latestTime != null)
			{
				if (latestTime.getTime() < lastBookTime)
				{
					// will pay
					no_consultation_fee = false;
					// re visit
					visit_type = "R";
				} else
				{
					// will not pay
					no_consultation_fee = true;
					// review
					visit_type = "V";
				}
			}
		}
		mTab.setValue("no_consultation_fee", no_consultation_fee);
		mTab.setValue("visit_type", visit_type);
		System.out.println(mTab.getValue("no_consultation_fee"));
		return "";
	}
}
