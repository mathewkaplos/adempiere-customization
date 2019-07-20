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
package zenith.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for hms_setup
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_setup 
{

    /** TableName=hms_setup */
    public static final String Table_Name = "hms_setup";

    /** AD_Table_ID=1000033 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name allow_balance */
    public static final String COLUMNNAME_allow_balance = "allow_balance";

	/** Set Allow Balance	  */
	public void setallow_balance (boolean allow_balance);

	/** Get Allow Balance	  */
	public boolean isallow_balance();

    /** Column name allow_billing_after_service */
    public static final String COLUMNNAME_allow_billing_after_service = "allow_billing_after_service";

	/** Set allow_billing_after_service	  */
	public void setallow_billing_after_service (boolean allow_billing_after_service);

	/** Get allow_billing_after_service	  */
	public boolean isallow_billing_after_service();

    /** Column name allow_negative_stock */
    public static final String COLUMNNAME_allow_negative_stock = "allow_negative_stock";

	/** Set Allow Negative Stock	  */
	public void setallow_negative_stock (boolean allow_negative_stock);

	/** Get Allow Negative Stock	  */
	public boolean isallow_negative_stock();

    /** Column name allow_price_change */
    public static final String COLUMNNAME_allow_price_change = "allow_price_change";

	/** Set allow_price_change	  */
	public void setallow_price_change (boolean allow_price_change);

	/** Get allow_price_change	  */
	public boolean isallow_price_change();

    /** Column name calculate_rebate_automatically */
    public static final String COLUMNNAME_calculate_rebate_automatically = "calculate_rebate_automatically";

	/** Set Calculate Rebate Automatically	  */
	public void setcalculate_rebate_automatically (boolean calculate_rebate_automatically);

	/** Get Calculate Rebate Automatically	  */
	public boolean iscalculate_rebate_automatically();

    /** Column name contact */
    public static final String COLUMNNAME_contact = "contact";

	/** Set contact	  */
	public void setcontact (String contact);

	/** Get contact	  */
	public String getcontact();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name diagnosis_before_prescription */
    public static final String COLUMNNAME_diagnosis_before_prescription = "diagnosis_before_prescription";

	/** Set Diagnosis Before Prescription	  */
	public void setdiagnosis_before_prescription (boolean diagnosis_before_prescription);

	/** Get Diagnosis Before Prescription	  */
	public boolean isdiagnosis_before_prescription();

    /** Column name display_all_patient_treatments */
    public static final String COLUMNNAME_display_all_patient_treatments = "display_all_patient_treatments";

	/** Set Display All Patient Treatments	  */
	public void setdisplay_all_patient_treatments (boolean display_all_patient_treatments);

	/** Get Display All Patient Treatments	  */
	public boolean isdisplay_all_patient_treatments();

    /** Column name drug_issued_once_prescribed */
    public static final String COLUMNNAME_drug_issued_once_prescribed = "drug_issued_once_prescribed";

	/** Set Drug Issued Once Prescribed	  */
	public void setdrug_issued_once_prescribed (boolean drug_issued_once_prescribed);

	/** Get Drug Issued Once Prescribed	  */
	public boolean isdrug_issued_once_prescribed();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name hms_setup_ID */
    public static final String COLUMNNAME_hms_setup_ID = "hms_setup_ID";

	/** Set hms_setup ID	  */
	public void sethms_setup_ID (int hms_setup_ID);

	/** Get hms_setup ID	  */
	public int gethms_setup_ID();

    /** Column name hospital_prefix */
    public static final String COLUMNNAME_hospital_prefix = "hospital_prefix";

	/** Set hospital_prefix	  */
	public void sethospital_prefix (String hospital_prefix);

	/** Get hospital_prefix	  */
	public String gethospital_prefix();

    /** Column name inpatient_realltime */
    public static final String COLUMNNAME_inpatient_realltime = "inpatient_realltime";

	/** Set Inpatient Real Time	  */
	public void setinpatient_realltime (boolean inpatient_realltime);

	/** Get Inpatient Real Time	  */
	public boolean isinpatient_realltime();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name is_get_alerts */
    public static final String COLUMNNAME_is_get_alerts = "is_get_alerts";

	/** Set is_get_alerts	  */
	public void setis_get_alerts (boolean is_get_alerts);

	/** Get is_get_alerts	  */
	public boolean is_get_alerts();

    /** Column name issue_negative */
    public static final String COLUMNNAME_issue_negative = "issue_negative";

	/** Set Issue Negative Stock in Pharmacy	  */
	public void setissue_negative (boolean issue_negative);

	/** Get Issue Negative Stock in Pharmacy	  */
	public boolean issue_negative();

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name maximize_forms */
    public static final String COLUMNNAME_maximize_forms = "maximize_forms";

	/** Set Maximize Forms.
	  * Maximize Forms in treatment windows
	  */
	public void setmaximize_forms (boolean maximize_forms);

	/** Get Maximize Forms.
	  * Maximize Forms in treatment windows
	  */
	public boolean ismaximize_forms();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name nhif_rebate_inpatient */
    public static final String COLUMNNAME_nhif_rebate_inpatient = "nhif_rebate_inpatient";

	/** Set Inpatient Rebate Amount.
	  * NHIF Inpatient Rebate Amount
	  */
	public void setnhif_rebate_inpatient (BigDecimal nhif_rebate_inpatient);

	/** Get Inpatient Rebate Amount.
	  * NHIF Inpatient Rebate Amount
	  */
	public BigDecimal getnhif_rebate_inpatient();

    /** Column name nhif_rebate_outpatient */
    public static final String COLUMNNAME_nhif_rebate_outpatient = "nhif_rebate_outpatient";

	/** Set Outpatient Rebate Amount.
	  * NHIF Outpatient Rebate Amount
	  */
	public void setnhif_rebate_outpatient (BigDecimal nhif_rebate_outpatient);

	/** Get Outpatient Rebate Amount.
	  * NHIF Outpatient Rebate Amount
	  */
	public BigDecimal getnhif_rebate_outpatient();

    /** Column name overwrite_inpatient_no */
    public static final String COLUMNNAME_overwrite_inpatient_no = "overwrite_inpatient_no";

	/** Set Overwrite Inpatient Number.
	  * Whether a new inpatient number is assigned to the patient when he/she is admitted, or retain an existing inpatient number.
	  */
	public void setoverwrite_inpatient_no (boolean overwrite_inpatient_no);

	/** Get Overwrite Inpatient Number.
	  * Whether a new inpatient number is assigned to the patient when he/she is admitted, or retain an existing inpatient number.
	  */
	public boolean isoverwrite_inpatient_no();

    /** Column name pharmacy_combined */
    public static final String COLUMNNAME_pharmacy_combined = "pharmacy_combined";

	/** Set Pharmacy Combined	  */
	public void setpharmacy_combined (boolean pharmacy_combined);

	/** Get Pharmacy Combined	  */
	public boolean ispharmacy_combined();

    /** Column name pharmacy_show_stock */
    public static final String COLUMNNAME_pharmacy_show_stock = "pharmacy_show_stock";

	/** Set Pharmacy Show Stock Available	  */
	public void setpharmacy_show_stock (boolean pharmacy_show_stock);

	/** Get Pharmacy Show Stock Available	  */
	public boolean ispharmacy_show_stock();

    /** Column name postal_address */
    public static final String COLUMNNAME_postal_address = "postal_address";

	/** Set postal_address	  */
	public void setpostal_address (String postal_address);

	/** Get postal_address	  */
	public String getpostal_address();

    /** Column name postal_code */
    public static final String COLUMNNAME_postal_code = "postal_code";

	/** Set postal_code	  */
	public void setpostal_code (String postal_code);

	/** Get postal_code	  */
	public String getpostal_code();

    /** Column name realtime_update_stock */
    public static final String COLUMNNAME_realtime_update_stock = "realtime_update_stock";

	/** Set Realtime Update Stock	  */
	public void setrealtime_update_stock (boolean realtime_update_stock);

	/** Get Realtime Update Stock	  */
	public boolean isrealtime_update_stock();

    /** Column name rebooking_interval */
    public static final String COLUMNNAME_rebooking_interval = "rebooking_interval";

	/** Set Normal Rebooking Interval	  */
	public void setrebooking_interval (int rebooking_interval);

	/** Get Normal Rebooking Interval	  */
	public int getrebooking_interval();

    /** Column name registration_fee */
    public static final String COLUMNNAME_registration_fee = "registration_fee";

	/** Set registration_fee	  */
	public void setregistration_fee (BigDecimal registration_fee);

	/** Get registration_fee	  */
	public BigDecimal getregistration_fee();

    /** Column name request_service_before_done */
    public static final String COLUMNNAME_request_service_before_done = "request_service_before_done";

	/** Set Request Service Before Done	  */
	public void setrequest_service_before_done (boolean request_service_before_done);

	/** Get Request Service Before Done	  */
	public boolean isrequest_service_before_done();

    /** Column name reserve_drugs */
    public static final String COLUMNNAME_reserve_drugs = "reserve_drugs";

	/** Set Reserve Drugs	  */
	public void setreserve_drugs (boolean reserve_drugs);

	/** Get Reserve Drugs	  */
	public boolean isreserve_drugs();

    /** Column name street */
    public static final String COLUMNNAME_street = "street";

	/** Set street	  */
	public void setstreet (String street);

	/** Get street	  */
	public String getstreet();

    /** Column name telephone */
    public static final String COLUMNNAME_telephone = "telephone";

	/** Set telephone	  */
	public void settelephone (String telephone);

	/** Get telephone	  */
	public String gettelephone();

    /** Column name town */
    public static final String COLUMNNAME_town = "town";

	/** Set town	  */
	public void settown (String town);

	/** Get town	  */
	public String gettown();

    /** Column name triage_before_consoltation */
    public static final String COLUMNNAME_triage_before_consoltation = "triage_before_consoltation";

	/** Set triage_before_consoltation	  */
	public void settriage_before_consoltation (boolean triage_before_consoltation);

	/** Get triage_before_consoltation	  */
	public boolean istriage_before_consoltation();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name website */
    public static final String COLUMNNAME_website = "website";

	/** Set Website	  */
	public void setwebsite (String website);

	/** Get Website	  */
	public String getwebsite();

    /** Column name zoom_from_pharmacy */
    public static final String COLUMNNAME_zoom_from_pharmacy = "zoom_from_pharmacy";

	/** Set Zoom From From Pharmacy to Treatment	  */
	public void setzoom_from_pharmacy (boolean zoom_from_pharmacy);

	/** Get Zoom From From Pharmacy to Treatment	  */
	public boolean iszoom_from_pharmacy();
}
