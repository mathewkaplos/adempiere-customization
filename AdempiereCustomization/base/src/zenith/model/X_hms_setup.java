/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package zenith.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for hms_setup
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_setup extends PO implements I_hms_setup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190719L;

    /** Standard Constructor */
    public X_hms_setup (Properties ctx, int hms_setup_ID, String trxName)
    {
      super (ctx, hms_setup_ID, trxName);
      /** if (hms_setup_ID == 0)
        {
			setallow_billing_after_service (false);
			setallow_price_change (false);
			setcalculate_rebate_automatically (false);
// N
			setdiagnosis_before_prescription (false);
// N
			setdisplay_all_patient_treatments (false);
// N
			setdrug_issued_once_prescribed (false);
// N
			sethms_setup_ID (0);
			setinpatient_realltime (false);
// N
			setis_get_alerts (false);
			setissue_negative (false);
// N
			setpharmacy_combined (false);
// N
			setpharmacy_show_stock (false);
// N
			setrebooking_interval (0);
// 24
			setrequest_service_before_done (false);
// N
			setreserve_drugs (false);
// N
			settriage_before_consoltation (false);
			setzoom_from_pharmacy (false);
// N
        } */
    }

    /** Load Constructor */
    public X_hms_setup (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_hms_setup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Allow Balance.
		@param allow_balance Allow Balance	  */
	public void setallow_balance (boolean allow_balance)
	{
		set_Value (COLUMNNAME_allow_balance, Boolean.valueOf(allow_balance));
	}

	/** Get Allow Balance.
		@return Allow Balance	  */
	public boolean isallow_balance () 
	{
		Object oo = get_Value(COLUMNNAME_allow_balance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set allow_billing_after_service.
		@param allow_billing_after_service allow_billing_after_service	  */
	public void setallow_billing_after_service (boolean allow_billing_after_service)
	{
		set_Value (COLUMNNAME_allow_billing_after_service, Boolean.valueOf(allow_billing_after_service));
	}

	/** Get allow_billing_after_service.
		@return allow_billing_after_service	  */
	public boolean isallow_billing_after_service () 
	{
		Object oo = get_Value(COLUMNNAME_allow_billing_after_service);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Negative Stock.
		@param allow_negative_stock Allow Negative Stock	  */
	public void setallow_negative_stock (boolean allow_negative_stock)
	{
		set_Value (COLUMNNAME_allow_negative_stock, Boolean.valueOf(allow_negative_stock));
	}

	/** Get Allow Negative Stock.
		@return Allow Negative Stock	  */
	public boolean isallow_negative_stock () 
	{
		Object oo = get_Value(COLUMNNAME_allow_negative_stock);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set allow_price_change.
		@param allow_price_change allow_price_change	  */
	public void setallow_price_change (boolean allow_price_change)
	{
		set_Value (COLUMNNAME_allow_price_change, Boolean.valueOf(allow_price_change));
	}

	/** Get allow_price_change.
		@return allow_price_change	  */
	public boolean isallow_price_change () 
	{
		Object oo = get_Value(COLUMNNAME_allow_price_change);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Calculate Rebate Automatically.
		@param calculate_rebate_automatically Calculate Rebate Automatically	  */
	public void setcalculate_rebate_automatically (boolean calculate_rebate_automatically)
	{
		set_Value (COLUMNNAME_calculate_rebate_automatically, Boolean.valueOf(calculate_rebate_automatically));
	}

	/** Get Calculate Rebate Automatically.
		@return Calculate Rebate Automatically	  */
	public boolean iscalculate_rebate_automatically () 
	{
		Object oo = get_Value(COLUMNNAME_calculate_rebate_automatically);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set contact.
		@param contact contact	  */
	public void setcontact (String contact)
	{
		set_Value (COLUMNNAME_contact, contact);
	}

	/** Get contact.
		@return contact	  */
	public String getcontact () 
	{
		return (String)get_Value(COLUMNNAME_contact);
	}

	/** Set Diagnosis Before Prescription.
		@param diagnosis_before_prescription Diagnosis Before Prescription	  */
	public void setdiagnosis_before_prescription (boolean diagnosis_before_prescription)
	{
		set_Value (COLUMNNAME_diagnosis_before_prescription, Boolean.valueOf(diagnosis_before_prescription));
	}

	/** Get Diagnosis Before Prescription.
		@return Diagnosis Before Prescription	  */
	public boolean isdiagnosis_before_prescription () 
	{
		Object oo = get_Value(COLUMNNAME_diagnosis_before_prescription);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Display All Patient Treatments.
		@param display_all_patient_treatments Display All Patient Treatments	  */
	public void setdisplay_all_patient_treatments (boolean display_all_patient_treatments)
	{
		set_Value (COLUMNNAME_display_all_patient_treatments, Boolean.valueOf(display_all_patient_treatments));
	}

	/** Get Display All Patient Treatments.
		@return Display All Patient Treatments	  */
	public boolean isdisplay_all_patient_treatments () 
	{
		Object oo = get_Value(COLUMNNAME_display_all_patient_treatments);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Drug Issued Once Prescribed.
		@param drug_issued_once_prescribed Drug Issued Once Prescribed	  */
	public void setdrug_issued_once_prescribed (boolean drug_issued_once_prescribed)
	{
		set_Value (COLUMNNAME_drug_issued_once_prescribed, Boolean.valueOf(drug_issued_once_prescribed));
	}

	/** Get Drug Issued Once Prescribed.
		@return Drug Issued Once Prescribed	  */
	public boolean isdrug_issued_once_prescribed () 
	{
		Object oo = get_Value(COLUMNNAME_drug_issued_once_prescribed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Fax.
		@param Fax 
		Facsimile number
	  */
	public void setFax (String Fax)
	{
		set_Value (COLUMNNAME_Fax, Fax);
	}

	/** Get Fax.
		@return Facsimile number
	  */
	public String getFax () 
	{
		return (String)get_Value(COLUMNNAME_Fax);
	}

	/** Set hms_setup ID.
		@param hms_setup_ID hms_setup ID	  */
	public void sethms_setup_ID (int hms_setup_ID)
	{
		if (hms_setup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_setup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_setup_ID, Integer.valueOf(hms_setup_ID));
	}

	/** Get hms_setup ID.
		@return hms_setup ID	  */
	public int gethms_setup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_setup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hospital_prefix.
		@param hospital_prefix hospital_prefix	  */
	public void sethospital_prefix (String hospital_prefix)
	{
		set_Value (COLUMNNAME_hospital_prefix, hospital_prefix);
	}

	/** Get hospital_prefix.
		@return hospital_prefix	  */
	public String gethospital_prefix () 
	{
		return (String)get_Value(COLUMNNAME_hospital_prefix);
	}

	/** Set Inpatient Real Time.
		@param inpatient_realltime Inpatient Real Time	  */
	public void setinpatient_realltime (boolean inpatient_realltime)
	{
		set_Value (COLUMNNAME_inpatient_realltime, Boolean.valueOf(inpatient_realltime));
	}

	/** Get Inpatient Real Time.
		@return Inpatient Real Time	  */
	public boolean isinpatient_realltime () 
	{
		Object oo = get_Value(COLUMNNAME_inpatient_realltime);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_get_alerts.
		@param is_get_alerts is_get_alerts	  */
	public void setis_get_alerts (boolean is_get_alerts)
	{
		set_Value (COLUMNNAME_is_get_alerts, Boolean.valueOf(is_get_alerts));
	}

	/** Get is_get_alerts.
		@return is_get_alerts	  */
	public boolean is_get_alerts () 
	{
		Object oo = get_Value(COLUMNNAME_is_get_alerts);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Issue Negative Stock in Pharmacy.
		@param issue_negative Issue Negative Stock in Pharmacy	  */
	public void setissue_negative (boolean issue_negative)
	{
		set_Value (COLUMNNAME_issue_negative, Boolean.valueOf(issue_negative));
	}

	/** Get Issue Negative Stock in Pharmacy.
		@return Issue Negative Stock in Pharmacy	  */
	public boolean issue_negative () 
	{
		Object oo = get_Value(COLUMNNAME_issue_negative);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Logo.
		@param Logo_ID Logo	  */
	public void setLogo_ID (int Logo_ID)
	{
		if (Logo_ID < 1) 
			set_Value (COLUMNNAME_Logo_ID, null);
		else 
			set_Value (COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
	}

	/** Get Logo.
		@return Logo	  */
	public int getLogo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Logo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximize Forms.
		@param maximize_forms 
		Maximize Forms in treatment windows
	  */
	public void setmaximize_forms (boolean maximize_forms)
	{
		set_Value (COLUMNNAME_maximize_forms, Boolean.valueOf(maximize_forms));
	}

	/** Get Maximize Forms.
		@return Maximize Forms in treatment windows
	  */
	public boolean ismaximize_forms () 
	{
		Object oo = get_Value(COLUMNNAME_maximize_forms);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Inpatient Rebate Amount.
		@param nhif_rebate_inpatient 
		NHIF Inpatient Rebate Amount
	  */
	public void setnhif_rebate_inpatient (BigDecimal nhif_rebate_inpatient)
	{
		set_Value (COLUMNNAME_nhif_rebate_inpatient, nhif_rebate_inpatient);
	}

	/** Get Inpatient Rebate Amount.
		@return NHIF Inpatient Rebate Amount
	  */
	public BigDecimal getnhif_rebate_inpatient () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_nhif_rebate_inpatient);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Outpatient Rebate Amount.
		@param nhif_rebate_outpatient 
		NHIF Outpatient Rebate Amount
	  */
	public void setnhif_rebate_outpatient (BigDecimal nhif_rebate_outpatient)
	{
		set_Value (COLUMNNAME_nhif_rebate_outpatient, nhif_rebate_outpatient);
	}

	/** Get Outpatient Rebate Amount.
		@return NHIF Outpatient Rebate Amount
	  */
	public BigDecimal getnhif_rebate_outpatient () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_nhif_rebate_outpatient);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Overwrite Inpatient Number.
		@param overwrite_inpatient_no 
		Whether a new inpatient number is assigned to the patient when he/she is admitted, or retain an existing inpatient number.
	  */
	public void setoverwrite_inpatient_no (boolean overwrite_inpatient_no)
	{
		set_Value (COLUMNNAME_overwrite_inpatient_no, Boolean.valueOf(overwrite_inpatient_no));
	}

	/** Get Overwrite Inpatient Number.
		@return Whether a new inpatient number is assigned to the patient when he/she is admitted, or retain an existing inpatient number.
	  */
	public boolean isoverwrite_inpatient_no () 
	{
		Object oo = get_Value(COLUMNNAME_overwrite_inpatient_no);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pharmacy Combined.
		@param pharmacy_combined Pharmacy Combined	  */
	public void setpharmacy_combined (boolean pharmacy_combined)
	{
		set_Value (COLUMNNAME_pharmacy_combined, Boolean.valueOf(pharmacy_combined));
	}

	/** Get Pharmacy Combined.
		@return Pharmacy Combined	  */
	public boolean ispharmacy_combined () 
	{
		Object oo = get_Value(COLUMNNAME_pharmacy_combined);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pharmacy Show Stock Available.
		@param pharmacy_show_stock Pharmacy Show Stock Available	  */
	public void setpharmacy_show_stock (boolean pharmacy_show_stock)
	{
		set_Value (COLUMNNAME_pharmacy_show_stock, Boolean.valueOf(pharmacy_show_stock));
	}

	/** Get Pharmacy Show Stock Available.
		@return Pharmacy Show Stock Available	  */
	public boolean ispharmacy_show_stock () 
	{
		Object oo = get_Value(COLUMNNAME_pharmacy_show_stock);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set postal_address.
		@param postal_address postal_address	  */
	public void setpostal_address (String postal_address)
	{
		set_Value (COLUMNNAME_postal_address, postal_address);
	}

	/** Get postal_address.
		@return postal_address	  */
	public String getpostal_address () 
	{
		return (String)get_Value(COLUMNNAME_postal_address);
	}

	/** Set postal_code.
		@param postal_code postal_code	  */
	public void setpostal_code (String postal_code)
	{
		set_Value (COLUMNNAME_postal_code, postal_code);
	}

	/** Get postal_code.
		@return postal_code	  */
	public String getpostal_code () 
	{
		return (String)get_Value(COLUMNNAME_postal_code);
	}

	/** Set Realtime Update Stock.
		@param realtime_update_stock Realtime Update Stock	  */
	public void setrealtime_update_stock (boolean realtime_update_stock)
	{
		set_Value (COLUMNNAME_realtime_update_stock, Boolean.valueOf(realtime_update_stock));
	}

	/** Get Realtime Update Stock.
		@return Realtime Update Stock	  */
	public boolean isrealtime_update_stock () 
	{
		Object oo = get_Value(COLUMNNAME_realtime_update_stock);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Normal Rebooking Interval.
		@param rebooking_interval Normal Rebooking Interval	  */
	public void setrebooking_interval (int rebooking_interval)
	{
		set_Value (COLUMNNAME_rebooking_interval, Integer.valueOf(rebooking_interval));
	}

	/** Get Normal Rebooking Interval.
		@return Normal Rebooking Interval	  */
	public int getrebooking_interval () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_rebooking_interval);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set registration_fee.
		@param registration_fee registration_fee	  */
	public void setregistration_fee (BigDecimal registration_fee)
	{
		set_Value (COLUMNNAME_registration_fee, registration_fee);
	}

	/** Get registration_fee.
		@return registration_fee	  */
	public BigDecimal getregistration_fee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_registration_fee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Request Service Before Done.
		@param request_service_before_done Request Service Before Done	  */
	public void setrequest_service_before_done (boolean request_service_before_done)
	{
		set_Value (COLUMNNAME_request_service_before_done, Boolean.valueOf(request_service_before_done));
	}

	/** Get Request Service Before Done.
		@return Request Service Before Done	  */
	public boolean isrequest_service_before_done () 
	{
		Object oo = get_Value(COLUMNNAME_request_service_before_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Reserve Drugs.
		@param reserve_drugs Reserve Drugs	  */
	public void setreserve_drugs (boolean reserve_drugs)
	{
		set_Value (COLUMNNAME_reserve_drugs, Boolean.valueOf(reserve_drugs));
	}

	/** Get Reserve Drugs.
		@return Reserve Drugs	  */
	public boolean isreserve_drugs () 
	{
		Object oo = get_Value(COLUMNNAME_reserve_drugs);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set street.
		@param street street	  */
	public void setstreet (String street)
	{
		set_Value (COLUMNNAME_street, street);
	}

	/** Get street.
		@return street	  */
	public String getstreet () 
	{
		return (String)get_Value(COLUMNNAME_street);
	}

	/** Set telephone.
		@param telephone telephone	  */
	public void settelephone (String telephone)
	{
		set_Value (COLUMNNAME_telephone, telephone);
	}

	/** Get telephone.
		@return telephone	  */
	public String gettelephone () 
	{
		return (String)get_Value(COLUMNNAME_telephone);
	}

	/** Set town.
		@param town town	  */
	public void settown (String town)
	{
		set_Value (COLUMNNAME_town, town);
	}

	/** Get town.
		@return town	  */
	public String gettown () 
	{
		return (String)get_Value(COLUMNNAME_town);
	}

	/** Set triage_before_consoltation.
		@param triage_before_consoltation triage_before_consoltation	  */
	public void settriage_before_consoltation (boolean triage_before_consoltation)
	{
		set_Value (COLUMNNAME_triage_before_consoltation, Boolean.valueOf(triage_before_consoltation));
	}

	/** Get triage_before_consoltation.
		@return triage_before_consoltation	  */
	public boolean istriage_before_consoltation () 
	{
		Object oo = get_Value(COLUMNNAME_triage_before_consoltation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Website.
		@param website Website	  */
	public void setwebsite (String website)
	{
		set_Value (COLUMNNAME_website, website);
	}

	/** Get Website.
		@return Website	  */
	public String getwebsite () 
	{
		return (String)get_Value(COLUMNNAME_website);
	}

	/** Set Zoom From From Pharmacy to Treatment.
		@param zoom_from_pharmacy Zoom From From Pharmacy to Treatment	  */
	public void setzoom_from_pharmacy (boolean zoom_from_pharmacy)
	{
		set_Value (COLUMNNAME_zoom_from_pharmacy, Boolean.valueOf(zoom_from_pharmacy));
	}

	/** Get Zoom From From Pharmacy to Treatment.
		@return Zoom From From Pharmacy to Treatment	  */
	public boolean iszoom_from_pharmacy () 
	{
		Object oo = get_Value(COLUMNNAME_zoom_from_pharmacy);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}