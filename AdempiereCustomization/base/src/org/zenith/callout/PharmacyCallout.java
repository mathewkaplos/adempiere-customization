package org.zenith.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class PharmacyCallout extends CalloutEngine
{
	public String patientName(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null || (int) value == 0)
		{

			Object oo = mTab.getValue("hms_treatment_doc_id");
			if (oo != null)
			{
				String trx = Trx.createTrxName();
				int docID = (int) oo;
				String sql = "SELECT C_BPartner_ID FROM adempiere.hms_treatment_doc where hms_treatment_doc_id= "
						+ docID;
				int C_BPartner_ID = DB.getSQLValue(trx, sql);
				mTab.setValue("C_BPartner_ID", C_BPartner_ID);

				String sql2 = "SELECT documentno FROM adempiere.C_BPartner WHERE C_BPartner_ID = " + C_BPartner_ID;
				String patientNo = DB.getSQLValueString(trx, sql2);
				mTab.setValue("outpatient_no", patientNo);

				Trx.get(trx, false).close();
			}
		}
		return "";
	}
}
