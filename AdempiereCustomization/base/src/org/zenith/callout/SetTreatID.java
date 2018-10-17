package org.zenith.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.zenith.util.ZEnv;

public class SetTreatID extends CalloutEngine
{
	public String treatID(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		int treatID = (Integer) mTab.getValue("hms_treatment_doc_ID");
		int C_BPartner_ID = (Integer) mTab.getValue("C_BPartner_ID");

		ZEnv.setC_BPartner_ID(C_BPartner_ID);
		ZEnv.setHms_treatment_doc_ID(treatID);
		System.out.println(treatID);
		return "";
	}
}


//   org.zenith.callout.SetTreatID.treatID