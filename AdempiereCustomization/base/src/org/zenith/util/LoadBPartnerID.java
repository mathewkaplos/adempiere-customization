package org.zenith.util;

import org.compiere.model.MBPartner;
import org.compiere.util.Env;

import zenith.model.MTreatmentDoc;

public class LoadBPartnerID
{
	/**
	 * Load the most recent Treatment record ID of the Patient
	 * 
	 * @param BP_ID
	 * @return hms_treatment_doc_id
	 */
	public static int getC_BPartner_ID(int docID)
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), docID, null);
		return doc.getC_BPartner_ID();
	}

	public static String getBPartnerName(int bpID)
	{
		MBPartner bp = new MBPartner(Env.getCtx(), bpID, null);
		return bp.getName();
	}

}
