package org.zenith.callout;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

//import it.businesslogic.ireport.gui.MessageBox;
import zenith.model.LabDocument;
import zenith.model.MTest;
import zenith.model.MTreatmentDoc;

public class LabRequest extends CalloutEngine
{
	static int registration_TabID = 1000082; // requesting from reception
	static int treatment_TabID = 1000026;// Doctor Request

	public String sendRequest(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		// hms_treatment_doc_id
		int hms_treatment_doc_id = Env.getContextAsInt(ctx, windowNo, "hms_treatment_doc_id");
		int C_BPartner_ID = Env.getContextAsInt(ctx, windowNo, "C_BPartner_ID");
		int rowCount = mTab.getRowCount();
		for (int i = 0; i < rowCount; i++)
		{
			if (String.valueOf(mTab.getValue(i, "selected")) == "true")
			{
				// key is the test ID
				int testID = mTab.getKeyID(i);

				// check if test is request and not done yet
				if (LabDocument.checkTest(hms_treatment_doc_id, testID))
				{
					// MessageBox.show("Service already requested and not
					// done...");
					return "";
					// throw new AdempiereException();
				}
				LabDocument ld = new LabDocument(ctx);
				// request from the doctor..treatment document exist
				if (mTab.getAD_Tab_ID() == treatment_TabID)
				{
					ld.newRequest(C_BPartner_ID, testID, hms_treatment_doc_id, true, 1000007, null,null, null);
				}
				// create treatment document first
				else if (mTab.getAD_Tab_ID() == registration_TabID)
				{
					newTreatmentDoc(ctx, C_BPartner_ID);
					ld.newRequest(C_BPartner_ID, testID, hms_treatment_doc_id, false, 1000007, null, null,null);
				}
				// MTest test = new MTest(ctx, key, null);
				// test.setselected(false);
				// test.save();
			}
		}
		return "";
	}

	/**
	 * Create new treatment document
	 */
	private void newTreatmentDoc(Properties ctx, int C_BPartner_ID)
	{
		MTreatmentDoc doc = new MTreatmentDoc(ctx, 0, null);
		doc.setC_BPartner_ID(C_BPartner_ID);
		doc.setlab_self_request(true);
		doc.save();
	}

	public String notAvailable(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		boolean x = (boolean) value;
		if (x)
		{
			mTab.setValue("cancelled", false);
			mTab.setValue("repeat", false);
		}
		return "";
	}

	public String cancelled(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		boolean x = (boolean) value;
		if (x)
		{
			mTab.setValue("not_available", false);
			mTab.setValue("repeat", false);
		}
		return "";
	}

	public String repeat(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		boolean x = (boolean) value;
		if (x)
		{
			mTab.setValue("cancelled", false);
			mTab.setValue("not_available", false);
		}
		return "";
	}
}
