package org.zenith.util;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import zenith.model.MInsuranceCompany;
import zenith.model.MSetup;

public final class HmsSetup
{
	static int setup_ID = 1000001;

	// allow_billing_after_service
	public static boolean billingAfterService(int AD_Client_ID, String trxName)
	{
		String sql = "SELECT allow_billing_after_service from adempiere.hms_setup where AD_Client_ID =" + AD_Client_ID;
		String value = DB.getSQLValueString(trxName, sql);
		return "Y".equals(value);
	}

	// triage_before_consoltation
	public static boolean triageBeforeConsultation(int AD_Client_ID, String trxName)
	{
		String sql = "SELECT triage_before_consoltation from adempiere.hms_setup where AD_Client_ID =" + AD_Client_ID;
		String value = DB.getSQLValueString(trxName, sql);
		return "Y".equals(value);
	}

	// allow_negative_stock
	public static boolean allowNegativeStock()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.isallow_negative_stock();
	}

	// allow_billing_after_service
	public static boolean allowBillingAfterService()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.isallow_billing_after_service();
	}

	// Overwrite Inpatient Number
	public static boolean overwriteInpatientNumber()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.isoverwrite_inpatient_no();
	}

	public static BigDecimal getInpatientNHIFRebateAmt()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.getnhif_rebate_inpatient();
	}

	public static BigDecimal getOutpatientNHIFRebateAmt()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.getnhif_rebate_outpatient();
	}

	public static BigDecimal getRegistrationFee()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.getregistration_fee();
	}

	// M_P
	public static int getM_Product_ID(int AD_Client_ID, String trxName)
	{
		int value = 0;
		String sql = "SELECT M_Product_ID from adempiere.hms_setup where AD_Client_ID =" + AD_Client_ID;
		value = DB.getSQLValue(trxName, sql);
		if (value == 0)
		{
			String m_processMsg = "Other charges is not defined in the Hospital Setup .Please contact the Adminstrator";
			throw new AdempiereException(m_processMsg);
		}
		return value;
	}

	public static MInsuranceCompany getInsuranceCompany(int hms_insco_ID)
	{
		if (!(hms_insco_ID > 0))
			return null;
		MInsuranceCompany insco = new MInsuranceCompany(Env.getCtx(), hms_insco_ID, null);
		return insco;

	}

	public static boolean maximizeForms()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.ismaximize_forms();
	}

	public static boolean allowBalance()
	{
		MSetup setup = new MSetup(Env.getCtx(), setup_ID, null);
		return setup.isallow_balance();
	}

	public static MSetup getSetup()
	{
		return new MSetup(Env.getCtx(), setup_ID, null);
	}
}
