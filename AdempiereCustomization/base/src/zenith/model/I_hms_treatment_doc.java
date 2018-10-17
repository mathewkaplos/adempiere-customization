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

/** Generated Interface for hms_treatment_doc
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_treatment_doc 
{

    /** TableName=hms_treatment_doc */
    public static final String Table_Name = "hms_treatment_doc";

    /** AD_Table_ID=1000009 */
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

    /** Column name admitted */
    public static final String COLUMNNAME_admitted = "admitted";

	/** Set Admitted	  */
	public void setadmitted (boolean admitted);

	/** Get Admitted	  */
	public boolean isadmitted();

    /** Column name admitted_date */
    public static final String COLUMNNAME_admitted_date = "admitted_date";

	/** Set Admitted Date	  */
	public void setadmitted_date (Timestamp admitted_date);

	/** Get Admitted Date	  */
	public Timestamp getadmitted_date();

    /** Column name Age */
    public static final String COLUMNNAME_Age = "Age";

	/** Set Age.
	  * Age of a person
	  */
	public void setAge (String Age);

	/** Get Age.
	  * Age of a person
	  */
	public String getAge();

    /** Column name attend */
    public static final String COLUMNNAME_attend = "attend";

	/** Set Attend	  */
	public void setattend (boolean attend);

	/** Get Attend	  */
	public boolean isattend();

    /** Column name attended_by_ID */
    public static final String COLUMNNAME_attended_by_ID = "attended_by_ID";

	/** Set Attended By	  */
	public void setattended_by_ID (int attended_by_ID);

	/** Get Attended By	  */
	public int getattended_by_ID();

	public org.compiere.model.I_AD_User getattended_by() throws RuntimeException;

    /** Column name bed_price_per_day */
    public static final String COLUMNNAME_bed_price_per_day = "bed_price_per_day";

	/** Set Bed Price Per Day	  */
	public void setbed_price_per_day (BigDecimal bed_price_per_day);

	/** Get Bed Price Per Day	  */
	public BigDecimal getbed_price_per_day();

    /** Column name booking_date */
    public static final String COLUMNNAME_booking_date = "booking_date";

	/** Set Book Date	  */
	public void setbooking_date (Timestamp booking_date);

	/** Get Book Date	  */
	public Timestamp getbooking_date();

    /** Column name btn_add_bill */
    public static final String COLUMNNAME_btn_add_bill = "btn_add_bill";

	/** Set btn_add_bill	  */
	public void setbtn_add_bill (boolean btn_add_bill);

	/** Get btn_add_bill	  */
	public boolean isbtn_add_bill();

    /** Column name btn_add_vital */
    public static final String COLUMNNAME_btn_add_vital = "btn_add_vital";

	/** Set btn_add_vital	  */
	public void setbtn_add_vital (boolean btn_add_vital);

	/** Get btn_add_vital	  */
	public boolean isbtn_add_vital();

    /** Column name btn_adjust_treatment_terms */
    public static final String COLUMNNAME_btn_adjust_treatment_terms = "btn_adjust_treatment_terms";

	/** Set ADJUDT TREATMENT TERMS	  */
	public void setbtn_adjust_treatment_terms (String btn_adjust_treatment_terms);

	/** Get ADJUDT TREATMENT TERMS	  */
	public String getbtn_adjust_treatment_terms();

    /** Column name btn_admit */
    public static final String COLUMNNAME_btn_admit = "btn_admit";

	/** Set ADMIT	  */
	public void setbtn_admit (String btn_admit);

	/** Get ADMIT	  */
	public String getbtn_admit();

    /** Column name btn_bed_transfer */
    public static final String COLUMNNAME_btn_bed_transfer = "btn_bed_transfer";

	/** Set BED TRANSFER	  */
	public void setbtn_bed_transfer (String btn_bed_transfer);

	/** Get BED TRANSFER	  */
	public String getbtn_bed_transfer();

    /** Column name btn_bill_insuarance */
    public static final String COLUMNNAME_btn_bill_insuarance = "btn_bill_insuarance";

	/** Set btn_bill_insuarance	  */
	public void setbtn_bill_insuarance (String btn_bill_insuarance);

	/** Get btn_bill_insuarance	  */
	public String getbtn_bill_insuarance();

    /** Column name btn_bill_nhif */
    public static final String COLUMNNAME_btn_bill_nhif = "btn_bill_nhif";

	/** Set NHIF INVOICE	  */
	public void setbtn_bill_nhif (String btn_bill_nhif);

	/** Get NHIF INVOICE	  */
	public String getbtn_bill_nhif();

    /** Column name btn_bill_to_insuarance */
    public static final String COLUMNNAME_btn_bill_to_insuarance = "btn_bill_to_insuarance";

	/** Set INVOICE TO INSURANCE	  */
	public void setbtn_bill_to_insuarance (String btn_bill_to_insuarance);

	/** Get INVOICE TO INSURANCE	  */
	public String getbtn_bill_to_insuarance();

    /** Column name btn_consolidate_invoice */
    public static final String COLUMNNAME_btn_consolidate_invoice = "btn_consolidate_invoice";

	/** Set GENERATE INVOICE.
	  * CONSOLIDATE BILLS ALL THE BILLS TO ONE INVOICE
	  */
	public void setbtn_consolidate_invoice (String btn_consolidate_invoice);

	/** Get GENERATE INVOICE.
	  * CONSOLIDATE BILLS ALL THE BILLS TO ONE INVOICE
	  */
	public String getbtn_consolidate_invoice();

    /** Column name btn_discharge */
    public static final String COLUMNNAME_btn_discharge = "btn_discharge";

	/** Set DISCHAGE	  */
	public void setbtn_discharge (String btn_discharge);

	/** Get DISCHAGE	  */
	public String getbtn_discharge();

    /** Column name btn_enter_departmental_results */
    public static final String COLUMNNAME_btn_enter_departmental_results = "btn_enter_departmental_results";

	/** Set ENTER RESULTS	  */
	public void setbtn_enter_departmental_results (String btn_enter_departmental_results);

	/** Get ENTER RESULTS	  */
	public String getbtn_enter_departmental_results();

    /** Column name btn_exit */
    public static final String COLUMNNAME_btn_exit = "btn_exit";

	/** Set btn_exit	  */
	public void setbtn_exit (String btn_exit);

	/** Get btn_exit	  */
	public String getbtn_exit();

    /** Column name btn_lab_done */
    public static final String COLUMNNAME_btn_lab_done = "btn_lab_done";

	/** Set LAB DONE	  */
	public void setbtn_lab_done (String btn_lab_done);

	/** Get LAB DONE	  */
	public String getbtn_lab_done();

    /** Column name btn_othercharges */
    public static final String COLUMNNAME_btn_othercharges = "btn_othercharges";

	/** Set btn_othercharges	  */
	public void setbtn_othercharges (String btn_othercharges);

	/** Get btn_othercharges	  */
	public String getbtn_othercharges();

    /** Column name btn_preview_invoice */
    public static final String COLUMNNAME_btn_preview_invoice = "btn_preview_invoice";

	/** Set PREVIEW INVOICE	  */
	public void setbtn_preview_invoice (String btn_preview_invoice);

	/** Get PREVIEW INVOICE	  */
	public String getbtn_preview_invoice();

    /** Column name btn_preview_satement */
    public static final String COLUMNNAME_btn_preview_satement = "btn_preview_satement";

	/** Set PREVIEW STATEMENT	  */
	public void setbtn_preview_satement (String btn_preview_satement);

	/** Get PREVIEW STATEMENT	  */
	public String getbtn_preview_satement();

    /** Column name btn_print_ultrasound */
    public static final String COLUMNNAME_btn_print_ultrasound = "btn_print_ultrasound";

	/** Set PRINT ULTRASOUND	  */
	public void setbtn_print_ultrasound (String btn_print_ultrasound);

	/** Get PRINT ULTRASOUND	  */
	public String getbtn_print_ultrasound();

    /** Column name btn_readmit */
    public static final String COLUMNNAME_btn_readmit = "btn_readmit";

	/** Set RE-ADMIT	  */
	public void setbtn_readmit (String btn_readmit);

	/** Get RE-ADMIT	  */
	public String getbtn_readmit();

    /** Column name btn_treatment_done */
    public static final String COLUMNNAME_btn_treatment_done = "btn_treatment_done";

	/** Set TREATMENT DONE	  */
	public void setbtn_treatment_done (String btn_treatment_done);

	/** Get TREATMENT DONE	  */
	public String getbtn_treatment_done();

    /** Column name btn_triage_done */
    public static final String COLUMNNAME_btn_triage_done = "btn_triage_done";

	/** Set TRIAGE DONE	  */
	public void setbtn_triage_done (String btn_triage_done);

	/** Get TRIAGE DONE	  */
	public String getbtn_triage_done();

    /** Column name btn_ward_transfer */
    public static final String COLUMNNAME_btn_ward_transfer = "btn_ward_transfer";

	/** Set WARD TRANSFER	  */
	public void setbtn_ward_transfer (String btn_ward_transfer);

	/** Get WARD TRANSFER	  */
	public String getbtn_ward_transfer();

    /** Column name btn_zoom */
    public static final String COLUMNNAME_btn_zoom = "btn_zoom";

	/** Set VITAL SIGNS	  */
	public void setbtn_zoom (String btn_zoom);

	/** Get VITAL SIGNS	  */
	public String getbtn_zoom();

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Patient Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Patient Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Patient Name .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Patient Name .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name casier_orders */
    public static final String COLUMNNAME_casier_orders = "casier_orders";

	/** Set casier_orders	  */
	public void setcasier_orders (BigDecimal casier_orders);

	/** Get casier_orders	  */
	public BigDecimal getcasier_orders();

    /** Column name casier_orders_amt */
    public static final String COLUMNNAME_casier_orders_amt = "casier_orders_amt";

	/** Set casier_orders_amt	  */
	public void setcasier_orders_amt (int casier_orders_amt);

	/** Get casier_orders_amt	  */
	public int getcasier_orders_amt();

	public I_C_ValidCombination getcasier_orders_() throws RuntimeException;

    /** Column name change_doctor */
    public static final String COLUMNNAME_change_doctor = "change_doctor";

	/** Set change_doctor.
	  * @admitted@='Y' OR @treatment_done@='Y' OR @attended@='Y'
	  */
	public void setchange_doctor (String change_doctor);

	/** Get change_doctor.
	  * @admitted@='Y' OR @treatment_done@='Y' OR @attended@='Y'
	  */
	public String getchange_doctor();

    /** Column name check_in */
    public static final String COLUMNNAME_check_in = "check_in";

	/** Set Check In.
	  * Check In
	  */
	public void setcheck_in (boolean check_in);

	/** Get Check In.
	  * Check In
	  */
	public boolean ischeck_in();

    /** Column name check_in_date */
    public static final String COLUMNNAME_check_in_date = "check_in_date";

	/** Set Check In Date	  */
	public void setcheck_in_date (Timestamp check_in_date);

	/** Get Check In Date	  */
	public Timestamp getcheck_in_date();

    /** Column name check_out */
    public static final String COLUMNNAME_check_out = "check_out";

	/** Set Check Out	  */
	public void setcheck_out (boolean check_out);

	/** Get Check Out	  */
	public boolean ischeck_out();

    /** Column name check_out_date */
    public static final String COLUMNNAME_check_out_date = "check_out_date";

	/** Set Check Out Date	  */
	public void setcheck_out_date (Timestamp check_out_date);

	/** Get Check Out Date	  */
	public Timestamp getcheck_out_date();

    /** Column name copay */
    public static final String COLUMNNAME_copay = "copay";

	/** Set Copay Amount	  */
	public void setcopay (BigDecimal copay);

	/** Get Copay Amount	  */
	public BigDecimal getcopay();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name created_time */
    public static final String COLUMNNAME_created_time = "created_time";

	/** Set Time	  */
	public void setcreated_time (String created_time);

	/** Get Time	  */
	public String getcreated_time();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name days_admitted */
    public static final String COLUMNNAME_days_admitted = "days_admitted";

	/** Set Days Admitted	  */
	public void setdays_admitted (String days_admitted);

	/** Get Days Admitted	  */
	public String getdays_admitted();

    /** Column name departmentCode */
    public static final String COLUMNNAME_departmentCode = "departmentCode";

	/** Set Departments Requested	  */
	public void setdepartmentCode (String departmentCode);

	/** Get Departments Requested	  */
	public String getdepartmentCode();

    /** Column name diagnosis */
    public static final String COLUMNNAME_diagnosis = "diagnosis";

	/** Set Diagnosis	  */
	public void setdiagnosis (String diagnosis);

	/** Get Diagnosis	  */
	public String getdiagnosis();

    /** Column name discharge_date */
    public static final String COLUMNNAME_discharge_date = "discharge_date";

	/** Set Discharge Date	  */
	public void setdischarge_date (Timestamp discharge_date);

	/** Get Discharge Date	  */
	public Timestamp getdischarge_date();

    /** Column name discharged */
    public static final String COLUMNNAME_discharged = "discharged";

	/** Set Discharged	  */
	public void setdischarged (boolean discharged);

	/** Get Discharged	  */
	public boolean isdischarged();

    /** Column name disp_allergies */
    public static final String COLUMNNAME_disp_allergies = "disp_allergies";

	/** Set disp_allergies	  */
	public void setdisp_allergies (boolean disp_allergies);

	/** Get disp_allergies	  */
	public boolean isdisp_allergies();

    /** Column name disp_diagnosis */
    public static final String COLUMNNAME_disp_diagnosis = "disp_diagnosis";

	/** Set disp_diagnosis	  */
	public void setdisp_diagnosis (boolean disp_diagnosis);

	/** Get disp_diagnosis	  */
	public boolean isdisp_diagnosis();

    /** Column name disp_inpatient_charges */
    public static final String COLUMNNAME_disp_inpatient_charges = "disp_inpatient_charges";

	/** Set disp_inpatient_charges	  */
	public void setdisp_inpatient_charges (boolean disp_inpatient_charges);

	/** Get disp_inpatient_charges	  */
	public boolean isdisp_inpatient_charges();

    /** Column name disp_lab_results */
    public static final String COLUMNNAME_disp_lab_results = "disp_lab_results";

	/** Set disp_lab_results	  */
	public void setdisp_lab_results (boolean disp_lab_results);

	/** Get disp_lab_results	  */
	public boolean isdisp_lab_results();

    /** Column name disp_notes */
    public static final String COLUMNNAME_disp_notes = "disp_notes";

	/** Set disp_notes	  */
	public void setdisp_notes (boolean disp_notes);

	/** Get disp_notes	  */
	public boolean isdisp_notes();

    /** Column name disp_quiz */
    public static final String COLUMNNAME_disp_quiz = "disp_quiz";

	/** Set disp_quiz	  */
	public void setdisp_quiz (boolean disp_quiz);

	/** Get disp_quiz	  */
	public boolean isdisp_quiz();

    /** Column name disp_vitals */
    public static final String COLUMNNAME_disp_vitals = "disp_vitals";

	/** Set disp_vitals	  */
	public void setdisp_vitals (boolean disp_vitals);

	/** Get disp_vitals	  */
	public boolean isdisp_vitals();

    /** Column name doctor_ID */
    public static final String COLUMNNAME_doctor_ID = "doctor_ID";

	/** Set doctor_ID	  */
	public void setdoctor_ID (int doctor_ID);

	/** Get doctor_ID	  */
	public int getdoctor_ID();

	public org.compiere.model.I_AD_User getdoctor() throws RuntimeException;

    /** Column name drugs_issued */
    public static final String COLUMNNAME_drugs_issued = "drugs_issued";

	/** Set drugs_issued	  */
	public void setdrugs_issued (int drugs_issued);

	/** Get drugs_issued	  */
	public int getdrugs_issued();

    /** Column name drugs_not_issued */
    public static final String COLUMNNAME_drugs_not_issued = "drugs_not_issued";

	/** Set drugs_not_issued	  */
	public void setdrugs_not_issued (int drugs_not_issued);

	/** Get drugs_not_issued	  */
	public int getdrugs_not_issued();

    /** Column name drugs_ordered */
    public static final String COLUMNNAME_drugs_ordered = "drugs_ordered";

	/** Set drugs_ordered	  */
	public void setdrugs_ordered (int drugs_ordered);

	/** Get drugs_ordered	  */
	public int getdrugs_ordered();

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name hms_diagnosis_ID */
    public static final String COLUMNNAME_hms_diagnosis_ID = "hms_diagnosis_ID";

	/** Set Diagnosis	  */
	public void sethms_diagnosis_ID (int hms_diagnosis_ID);

	/** Get Diagnosis	  */
	public int gethms_diagnosis_ID();

    /** Column name hms_insco_ID */
    public static final String COLUMNNAME_hms_insco_ID = "hms_insco_ID";

	/** Set Insurance Company	  */
	public void sethms_insco_ID (int hms_insco_ID);

	/** Get Insurance Company	  */
	public int gethms_insco_ID();

	public I_hms_insco gethms_insco() throws RuntimeException;

    /** Column name hms_insuredco_ID */
    public static final String COLUMNNAME_hms_insuredco_ID = "hms_insuredco_ID";

	/** Set Insured Company	  */
	public void sethms_insuredco_ID (int hms_insuredco_ID);

	/** Get Insured Company	  */
	public int gethms_insuredco_ID();

	public I_hms_insuredco gethms_insuredco() throws RuntimeException;

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

    /** Column name hms_ward_bed1_ID */
    public static final String COLUMNNAME_hms_ward_bed1_ID = "hms_ward_bed1_ID";

	/** Set Bed	  */
	public void sethms_ward_bed1_ID (int hms_ward_bed1_ID);

	/** Get Bed	  */
	public int gethms_ward_bed1_ID();

	public I_hms_ward_bed1 gethms_ward_bed1() throws RuntimeException;

    /** Column name hms_ward_management1_ID */
    public static final String COLUMNNAME_hms_ward_management1_ID = "hms_ward_management1_ID";

	/** Set hms_ward_management1 ID	  */
	public void sethms_ward_management1_ID (int hms_ward_management1_ID);

	/** Get hms_ward_management1 ID	  */
	public int gethms_ward_management1_ID();

	public I_hms_ward_management1 gethms_ward_management1() throws RuntimeException;

    /** Column name hms_ward1_ID */
    public static final String COLUMNNAME_hms_ward1_ID = "hms_ward1_ID";

	/** Set Ward	  */
	public void sethms_ward1_ID (int hms_ward1_ID);

	/** Get Ward	  */
	public int gethms_ward1_ID();

	public I_hms_ward1 gethms_ward1() throws RuntimeException;

    /** Column name inpatient_no */
    public static final String COLUMNNAME_inpatient_no = "inpatient_no";

	/** Set Inpatient Number	  */
	public void setinpatient_no (String inpatient_no);

	/** Get Inpatient Number	  */
	public String getinpatient_no();

    /** Column name instance_type */
    public static final String COLUMNNAME_instance_type = "instance_type";

	/** Set Instance Type	  */
	public void setinstance_type (String instance_type);

	/** Get Instance Type	  */
	public String getinstance_type();

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

    /** Column name lab_done */
    public static final String COLUMNNAME_lab_done = "lab_done";

	/** Set Lab Done	  */
	public void setlab_done (boolean lab_done);

	/** Get Lab Done	  */
	public boolean islab_done();

    /** Column name lab_results_seen */
    public static final String COLUMNNAME_lab_results_seen = "lab_results_seen";

	/** Set Lab Results Seen	  */
	public void setlab_results_seen (boolean lab_results_seen);

	/** Get Lab Results Seen	  */
	public boolean islab_results_seen();

    /** Column name lab_self_request */
    public static final String COLUMNNAME_lab_self_request = "lab_self_request";

	/** Set Lab Self Request	  */
	public void setlab_self_request (boolean lab_self_request);

	/** Get Lab Self Request	  */
	public boolean islab_self_request();

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

    /** Column name open_balance */
    public static final String COLUMNNAME_open_balance = "open_balance";

	/** Set Open Balance for this instance	  */
	public void setopen_balance (BigDecimal open_balance);

	/** Get Open Balance for this instance	  */
	public BigDecimal getopen_balance();

    /** Column name orders_not_issued */
    public static final String COLUMNNAME_orders_not_issued = "orders_not_issued";

	/** Set orders_not_issued	  */
	public void setorders_not_issued (String orders_not_issued);

	/** Get orders_not_issued	  */
	public String getorders_not_issued();

    /** Column name outpatient_no */
    public static final String COLUMNNAME_outpatient_no = "outpatient_no";

	/** Set Outpatient Number	  */
	public void setoutpatient_no (String outpatient_no);

	/** Get Outpatient Number	  */
	public String getoutpatient_no();

    /** Column name patient_location */
    public static final String COLUMNNAME_patient_location = "patient_location";

	/** Set Patient Location	  */
	public void setpatient_location (String patient_location);

	/** Get Patient Location	  */
	public String getpatient_location();

    /** Column name patient_status */
    public static final String COLUMNNAME_patient_status = "patient_status";

	/** Set Patient Status	  */
	public void setpatient_status (String patient_status);

	/** Get Patient Status	  */
	public String getpatient_status();

    /** Column name pharm_done */
    public static final String COLUMNNAME_pharm_done = "pharm_done";

	/** Set pharm_done	  */
	public void setpharm_done (boolean pharm_done);

	/** Get pharm_done	  */
	public boolean ispharm_done();

    /** Column name pharmacy_orders */
    public static final String COLUMNNAME_pharmacy_orders = "pharmacy_orders";

	/** Set pharmacy_orders	  */
	public void setpharmacy_orders (String pharmacy_orders);

	/** Get pharmacy_orders	  */
	public String getpharmacy_orders();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (BigDecimal Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public BigDecimal getPriority();

    /** Column name rebate_amount */
    public static final String COLUMNNAME_rebate_amount = "rebate_amount";

	/** Set Rebate Amount	  */
	public void setrebate_amount (BigDecimal rebate_amount);

	/** Get Rebate Amount	  */
	public BigDecimal getrebate_amount();

    /** Column name referred_from */
    public static final String COLUMNNAME_referred_from = "referred_from";

	/** Set Referred From	  */
	public void setreferred_from (String referred_from);

	/** Get Referred From	  */
	public String getreferred_from();

    /** Column name referred_in */
    public static final String COLUMNNAME_referred_in = "referred_in";

	/** Set Referred In	  */
	public void setreferred_in (boolean referred_in);

	/** Get Referred In	  */
	public boolean isreferred_in();

    /** Column name referred_out */
    public static final String COLUMNNAME_referred_out = "referred_out";

	/** Set Referred Out	  */
	public void setreferred_out (boolean referred_out);

	/** Get Referred Out	  */
	public boolean isreferred_out();

    /** Column name referred_to */
    public static final String COLUMNNAME_referred_to = "referred_to";

	/** Set Referred To	  */
	public void setreferred_to (String referred_to);

	/** Get Referred To	  */
	public String getreferred_to();

    /** Column name state */
    public static final String COLUMNNAME_state = "state";

	/** Set state	  */
	public void setstate (String state);

	/** Get state	  */
	public String getstate();

    /** Column name state2 */
    public static final String COLUMNNAME_state2 = "state2";

	/** Set state2	  */
	public void setstate2 (String state2);

	/** Get state2	  */
	public String getstate2();

    /** Column name to_cashier */
    public static final String COLUMNNAME_to_cashier = "to_cashier";

	/** Set to_cashier	  */
	public void setto_cashier (boolean to_cashier);

	/** Get to_cashier	  */
	public boolean isto_cashier();

    /** Column name to_dental */
    public static final String COLUMNNAME_to_dental = "to_dental";

	/** Set to_dental	  */
	public void setto_dental (boolean to_dental);

	/** Get to_dental	  */
	public boolean isto_dental();

    /** Column name to_doctor */
    public static final String COLUMNNAME_to_doctor = "to_doctor";

	/** Set to_doctor	  */
	public void setto_doctor (boolean to_doctor);

	/** Get to_doctor	  */
	public boolean isto_doctor();

    /** Column name to_eye */
    public static final String COLUMNNAME_to_eye = "to_eye";

	/** Set to_eye	  */
	public void setto_eye (boolean to_eye);

	/** Get to_eye	  */
	public boolean isto_eye();

    /** Column name to_lab */
    public static final String COLUMNNAME_to_lab = "to_lab";

	/** Set to_lab	  */
	public void setto_lab (boolean to_lab);

	/** Get to_lab	  */
	public boolean isto_lab();

    /** Column name to_pharm */
    public static final String COLUMNNAME_to_pharm = "to_pharm";

	/** Set to_pharm	  */
	public void setto_pharm (boolean to_pharm);

	/** Get to_pharm	  */
	public boolean isto_pharm();

    /** Column name to_theatre */
    public static final String COLUMNNAME_to_theatre = "to_theatre";

	/** Set to_theatre	  */
	public void setto_theatre (boolean to_theatre);

	/** Get to_theatre	  */
	public boolean isto_theatre();

    /** Column name to_triage */
    public static final String COLUMNNAME_to_triage = "to_triage";

	/** Set to_triage	  */
	public void setto_triage (boolean to_triage);

	/** Get to_triage	  */
	public boolean isto_triage();

    /** Column name to_ultrasound */
    public static final String COLUMNNAME_to_ultrasound = "to_ultrasound";

	/** Set to_ultrasound	  */
	public void setto_ultrasound (boolean to_ultrasound);

	/** Get to_ultrasound	  */
	public boolean isto_ultrasound();

    /** Column name to_ward */
    public static final String COLUMNNAME_to_ward = "to_ward";

	/** Set to_ward	  */
	public void setto_ward (String to_ward);

	/** Get to_ward	  */
	public String getto_ward();

    /** Column name to_xray */
    public static final String COLUMNNAME_to_xray = "to_xray";

	/** Set to_xray	  */
	public void setto_xray (boolean to_xray);

	/** Get to_xray	  */
	public boolean isto_xray();

    /** Column name TotalOpenBalance */
    public static final String COLUMNNAME_TotalOpenBalance = "TotalOpenBalance";

	/** Set Open Balance.
	  * Total Open Balance Amount in primary Accounting Currency
	  */
	public void setTotalOpenBalance (BigDecimal TotalOpenBalance);

	/** Get Open Balance.
	  * Total Open Balance Amount in primary Accounting Currency
	  */
	public BigDecimal getTotalOpenBalance();

    /** Column name treat_ref_ID */
    public static final String COLUMNNAME_treat_ref_ID = "treat_ref_ID";

	/** Set Instance ID	  */
	public void settreat_ref_ID (int treat_ref_ID);

	/** Get Instance ID	  */
	public int gettreat_ref_ID();

    /** Column name treatment_done */
    public static final String COLUMNNAME_treatment_done = "treatment_done";

	/** Set Treatment Done	  */
	public void settreatment_done (boolean treatment_done);

	/** Get Treatment Done	  */
	public boolean istreatment_done();

    /** Column name treatment_done_date */
    public static final String COLUMNNAME_treatment_done_date = "treatment_done_date";

	/** Set treatment_done_date	  */
	public void settreatment_done_date (Timestamp treatment_done_date);

	/** Get treatment_done_date	  */
	public Timestamp gettreatment_done_date();

    /** Column name triage_done */
    public static final String COLUMNNAME_triage_done = "triage_done";

	/** Set Triage Done	  */
	public void settriage_done (boolean triage_done);

	/** Get Triage Done	  */
	public boolean istriage_done();

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

    /** Column name visit_type */
    public static final String COLUMNNAME_visit_type = "visit_type";

	/** Set Visit Type	  */
	public void setvisit_type (String visit_type);

	/** Get Visit Type	  */
	public String getvisit_type();

    /** Column name vitals_done */
    public static final String COLUMNNAME_vitals_done = "vitals_done";

	/** Set vitals_done	  */
	public void setvitals_done (boolean vitals_done);

	/** Get vitals_done	  */
	public boolean isvitals_done();

    /** Column name vitals_not_available */
    public static final String COLUMNNAME_vitals_not_available = "vitals_not_available";

	/** Set Vitals Not Available	  */
	public void setvitals_not_available (boolean vitals_not_available);

	/** Get Vitals Not Available	  */
	public boolean isvitals_not_available();

    /** Column name zoom_allergies */
    public static final String COLUMNNAME_zoom_allergies = "zoom_allergies";

	/** Set ALLERGIES	  */
	public void setzoom_allergies (String zoom_allergies);

	/** Get ALLERGIES	  */
	public String getzoom_allergies();

    /** Column name zoom_billing */
    public static final String COLUMNNAME_zoom_billing = "zoom_billing";

	/** Set DRUG ORDERS	  */
	public void setzoom_billing (String zoom_billing);

	/** Get DRUG ORDERS	  */
	public String getzoom_billing();

    /** Column name zoom_departmental_request */
    public static final String COLUMNNAME_zoom_departmental_request = "zoom_departmental_request";

	/** Set DEPARTMENTAL REQUEST	  */
	public void setzoom_departmental_request (String zoom_departmental_request);

	/** Get DEPARTMENTAL REQUEST	  */
	public String getzoom_departmental_request();

    /** Column name zoom_diagnosis */
    public static final String COLUMNNAME_zoom_diagnosis = "zoom_diagnosis";

	/** Set DIAGNOSIS	  */
	public void setzoom_diagnosis (String zoom_diagnosis);

	/** Get DIAGNOSIS	  */
	public String getzoom_diagnosis();

    /** Column name zoom_discharge_drug */
    public static final String COLUMNNAME_zoom_discharge_drug = "zoom_discharge_drug";

	/** Set DISCHARGE DRUGS	  */
	public void setzoom_discharge_drug (String zoom_discharge_drug);

	/** Get DISCHARGE DRUGS	  */
	public String getzoom_discharge_drug();

    /** Column name zoom_notes */
    public static final String COLUMNNAME_zoom_notes = "zoom_notes";

	/** Set DOCTOR NOTES	  */
	public void setzoom_notes (String zoom_notes);

	/** Get DOCTOR NOTES	  */
	public String getzoom_notes();

    /** Column name zoom_othercharges */
    public static final String COLUMNNAME_zoom_othercharges = "zoom_othercharges";

	/** Set OTHER CHARGES	  */
	public void setzoom_othercharges (String zoom_othercharges);

	/** Get OTHER CHARGES	  */
	public String getzoom_othercharges();
}
