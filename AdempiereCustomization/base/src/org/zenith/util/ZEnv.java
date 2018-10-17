package org.zenith.util;

public class ZEnv
{
	private static int C_BPartner_ID = 0;
	private static int hms_treatment_doc_ID = 0;

	public static int getC_BPartner_ID()
	{
		return C_BPartner_ID;
	}

	public static int getHms_treatment_doc_ID()
	{
		return hms_treatment_doc_ID;
	}

	public static void setC_BPartner_ID(int c_BPartner_ID)
	{
		C_BPartner_ID = c_BPartner_ID;
	}

	public static void setHms_treatment_doc_ID(int hms_treatment_doc_ID)
	{
		ZEnv.hms_treatment_doc_ID = hms_treatment_doc_ID;
	}

}
