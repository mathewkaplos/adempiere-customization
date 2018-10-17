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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for hms_treatment_doc
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_treatment_doc extends PO implements I_hms_treatment_doc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181009L;

    /** Standard Constructor */
    public X_hms_treatment_doc (Properties ctx, int hms_treatment_doc_ID, String trxName)
    {
      super (ctx, hms_treatment_doc_ID, trxName);
      /** if (hms_treatment_doc_ID == 0)
        {
			setC_BPartner_ID (0);
			sethms_treatment_doc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_treatment_doc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_treatment_doc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Admitted.
		@param admitted Admitted	  */
	public void setadmitted (boolean admitted)
	{
		set_Value (COLUMNNAME_admitted, Boolean.valueOf(admitted));
	}

	/** Get Admitted.
		@return Admitted	  */
	public boolean isadmitted () 
	{
		Object oo = get_Value(COLUMNNAME_admitted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Admitted Date.
		@param admitted_date Admitted Date	  */
	public void setadmitted_date (Timestamp admitted_date)
	{
		set_Value (COLUMNNAME_admitted_date, admitted_date);
	}

	/** Get Admitted Date.
		@return Admitted Date	  */
	public Timestamp getadmitted_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_admitted_date);
	}

	/** Set Age.
		@param Age 
		Age of a person
	  */
	public void setAge (String Age)
	{
		throw new IllegalArgumentException ("Age is virtual column");	}

	/** Get Age.
		@return Age of a person
	  */
	public String getAge () 
	{
		return (String)get_Value(COLUMNNAME_Age);
	}

	/** Set Attend.
		@param attend Attend	  */
	public void setattend (boolean attend)
	{
		set_Value (COLUMNNAME_attend, Boolean.valueOf(attend));
	}

	/** Get Attend.
		@return Attend	  */
	public boolean isattend () 
	{
		Object oo = get_Value(COLUMNNAME_attend);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_AD_User getattended_by() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getattended_by_ID(), get_TrxName());	}

	/** Set Attended By.
		@param attended_by_ID Attended By	  */
	public void setattended_by_ID (int attended_by_ID)
	{
		if (attended_by_ID < 1) 
			set_Value (COLUMNNAME_attended_by_ID, null);
		else 
			set_Value (COLUMNNAME_attended_by_ID, Integer.valueOf(attended_by_ID));
	}

	/** Get Attended By.
		@return Attended By	  */
	public int getattended_by_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_attended_by_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bed Price Per Day.
		@param bed_price_per_day Bed Price Per Day	  */
	public void setbed_price_per_day (BigDecimal bed_price_per_day)
	{
		set_Value (COLUMNNAME_bed_price_per_day, bed_price_per_day);
	}

	/** Get Bed Price Per Day.
		@return Bed Price Per Day	  */
	public BigDecimal getbed_price_per_day () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_bed_price_per_day);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Book Date.
		@param booking_date Book Date	  */
	public void setbooking_date (Timestamp booking_date)
	{
		set_Value (COLUMNNAME_booking_date, booking_date);
	}

	/** Get Book Date.
		@return Book Date	  */
	public Timestamp getbooking_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_booking_date);
	}

	/** Set btn_add_bill.
		@param btn_add_bill btn_add_bill	  */
	public void setbtn_add_bill (boolean btn_add_bill)
	{
		set_Value (COLUMNNAME_btn_add_bill, Boolean.valueOf(btn_add_bill));
	}

	/** Get btn_add_bill.
		@return btn_add_bill	  */
	public boolean isbtn_add_bill () 
	{
		Object oo = get_Value(COLUMNNAME_btn_add_bill);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set btn_add_vital.
		@param btn_add_vital btn_add_vital	  */
	public void setbtn_add_vital (boolean btn_add_vital)
	{
		set_Value (COLUMNNAME_btn_add_vital, Boolean.valueOf(btn_add_vital));
	}

	/** Get btn_add_vital.
		@return btn_add_vital	  */
	public boolean isbtn_add_vital () 
	{
		Object oo = get_Value(COLUMNNAME_btn_add_vital);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ADJUDT TREATMENT TERMS.
		@param btn_adjust_treatment_terms ADJUDT TREATMENT TERMS	  */
	public void setbtn_adjust_treatment_terms (String btn_adjust_treatment_terms)
	{
		set_Value (COLUMNNAME_btn_adjust_treatment_terms, btn_adjust_treatment_terms);
	}

	/** Get ADJUDT TREATMENT TERMS.
		@return ADJUDT TREATMENT TERMS	  */
	public String getbtn_adjust_treatment_terms () 
	{
		return (String)get_Value(COLUMNNAME_btn_adjust_treatment_terms);
	}

	/** Set ADMIT.
		@param btn_admit ADMIT	  */
	public void setbtn_admit (String btn_admit)
	{
		set_Value (COLUMNNAME_btn_admit, btn_admit);
	}

	/** Get ADMIT.
		@return ADMIT	  */
	public String getbtn_admit () 
	{
		return (String)get_Value(COLUMNNAME_btn_admit);
	}

	/** Set BED TRANSFER.
		@param btn_bed_transfer BED TRANSFER	  */
	public void setbtn_bed_transfer (String btn_bed_transfer)
	{
		set_Value (COLUMNNAME_btn_bed_transfer, btn_bed_transfer);
	}

	/** Get BED TRANSFER.
		@return BED TRANSFER	  */
	public String getbtn_bed_transfer () 
	{
		return (String)get_Value(COLUMNNAME_btn_bed_transfer);
	}

	/** Set btn_bill_insuarance.
		@param btn_bill_insuarance btn_bill_insuarance	  */
	public void setbtn_bill_insuarance (String btn_bill_insuarance)
	{
		set_Value (COLUMNNAME_btn_bill_insuarance, btn_bill_insuarance);
	}

	/** Get btn_bill_insuarance.
		@return btn_bill_insuarance	  */
	public String getbtn_bill_insuarance () 
	{
		return (String)get_Value(COLUMNNAME_btn_bill_insuarance);
	}

	/** Set NHIF INVOICE.
		@param btn_bill_nhif NHIF INVOICE	  */
	public void setbtn_bill_nhif (String btn_bill_nhif)
	{
		set_Value (COLUMNNAME_btn_bill_nhif, btn_bill_nhif);
	}

	/** Get NHIF INVOICE.
		@return NHIF INVOICE	  */
	public String getbtn_bill_nhif () 
	{
		return (String)get_Value(COLUMNNAME_btn_bill_nhif);
	}

	/** Set INVOICE TO INSURANCE.
		@param btn_bill_to_insuarance INVOICE TO INSURANCE	  */
	public void setbtn_bill_to_insuarance (String btn_bill_to_insuarance)
	{
		set_Value (COLUMNNAME_btn_bill_to_insuarance, btn_bill_to_insuarance);
	}

	/** Get INVOICE TO INSURANCE.
		@return INVOICE TO INSURANCE	  */
	public String getbtn_bill_to_insuarance () 
	{
		return (String)get_Value(COLUMNNAME_btn_bill_to_insuarance);
	}

	/** Set GENERATE INVOICE.
		@param btn_consolidate_invoice 
		CONSOLIDATE BILLS ALL THE BILLS TO ONE INVOICE
	  */
	public void setbtn_consolidate_invoice (String btn_consolidate_invoice)
	{
		set_Value (COLUMNNAME_btn_consolidate_invoice, btn_consolidate_invoice);
	}

	/** Get GENERATE INVOICE.
		@return CONSOLIDATE BILLS ALL THE BILLS TO ONE INVOICE
	  */
	public String getbtn_consolidate_invoice () 
	{
		return (String)get_Value(COLUMNNAME_btn_consolidate_invoice);
	}

	/** Set DISCHAGE.
		@param btn_discharge DISCHAGE	  */
	public void setbtn_discharge (String btn_discharge)
	{
		set_Value (COLUMNNAME_btn_discharge, btn_discharge);
	}

	/** Get DISCHAGE.
		@return DISCHAGE	  */
	public String getbtn_discharge () 
	{
		return (String)get_Value(COLUMNNAME_btn_discharge);
	}

	/** Set ENTER RESULTS.
		@param btn_enter_departmental_results ENTER RESULTS	  */
	public void setbtn_enter_departmental_results (String btn_enter_departmental_results)
	{
		set_Value (COLUMNNAME_btn_enter_departmental_results, btn_enter_departmental_results);
	}

	/** Get ENTER RESULTS.
		@return ENTER RESULTS	  */
	public String getbtn_enter_departmental_results () 
	{
		return (String)get_Value(COLUMNNAME_btn_enter_departmental_results);
	}

	/** Set btn_exit.
		@param btn_exit btn_exit	  */
	public void setbtn_exit (String btn_exit)
	{
		set_Value (COLUMNNAME_btn_exit, btn_exit);
	}

	/** Get btn_exit.
		@return btn_exit	  */
	public String getbtn_exit () 
	{
		return (String)get_Value(COLUMNNAME_btn_exit);
	}

	/** Set LAB DONE.
		@param btn_lab_done LAB DONE	  */
	public void setbtn_lab_done (String btn_lab_done)
	{
		set_Value (COLUMNNAME_btn_lab_done, btn_lab_done);
	}

	/** Get LAB DONE.
		@return LAB DONE	  */
	public String getbtn_lab_done () 
	{
		return (String)get_Value(COLUMNNAME_btn_lab_done);
	}

	/** Set btn_othercharges.
		@param btn_othercharges btn_othercharges	  */
	public void setbtn_othercharges (String btn_othercharges)
	{
		set_Value (COLUMNNAME_btn_othercharges, btn_othercharges);
	}

	/** Get btn_othercharges.
		@return btn_othercharges	  */
	public String getbtn_othercharges () 
	{
		return (String)get_Value(COLUMNNAME_btn_othercharges);
	}

	/** Set PREVIEW INVOICE.
		@param btn_preview_invoice PREVIEW INVOICE	  */
	public void setbtn_preview_invoice (String btn_preview_invoice)
	{
		set_Value (COLUMNNAME_btn_preview_invoice, btn_preview_invoice);
	}

	/** Get PREVIEW INVOICE.
		@return PREVIEW INVOICE	  */
	public String getbtn_preview_invoice () 
	{
		return (String)get_Value(COLUMNNAME_btn_preview_invoice);
	}

	/** Set PREVIEW STATEMENT.
		@param btn_preview_satement PREVIEW STATEMENT	  */
	public void setbtn_preview_satement (String btn_preview_satement)
	{
		set_Value (COLUMNNAME_btn_preview_satement, btn_preview_satement);
	}

	/** Get PREVIEW STATEMENT.
		@return PREVIEW STATEMENT	  */
	public String getbtn_preview_satement () 
	{
		return (String)get_Value(COLUMNNAME_btn_preview_satement);
	}

	/** Set PRINT ULTRASOUND.
		@param btn_print_ultrasound PRINT ULTRASOUND	  */
	public void setbtn_print_ultrasound (String btn_print_ultrasound)
	{
		set_Value (COLUMNNAME_btn_print_ultrasound, btn_print_ultrasound);
	}

	/** Get PRINT ULTRASOUND.
		@return PRINT ULTRASOUND	  */
	public String getbtn_print_ultrasound () 
	{
		return (String)get_Value(COLUMNNAME_btn_print_ultrasound);
	}

	/** Set RE-ADMIT.
		@param btn_readmit RE-ADMIT	  */
	public void setbtn_readmit (String btn_readmit)
	{
		set_Value (COLUMNNAME_btn_readmit, btn_readmit);
	}

	/** Get RE-ADMIT.
		@return RE-ADMIT	  */
	public String getbtn_readmit () 
	{
		return (String)get_Value(COLUMNNAME_btn_readmit);
	}

	/** Set TREATMENT DONE.
		@param btn_treatment_done TREATMENT DONE	  */
	public void setbtn_treatment_done (String btn_treatment_done)
	{
		set_Value (COLUMNNAME_btn_treatment_done, btn_treatment_done);
	}

	/** Get TREATMENT DONE.
		@return TREATMENT DONE	  */
	public String getbtn_treatment_done () 
	{
		return (String)get_Value(COLUMNNAME_btn_treatment_done);
	}

	/** Set TRIAGE DONE.
		@param btn_triage_done TRIAGE DONE	  */
	public void setbtn_triage_done (String btn_triage_done)
	{
		set_Value (COLUMNNAME_btn_triage_done, btn_triage_done);
	}

	/** Get TRIAGE DONE.
		@return TRIAGE DONE	  */
	public String getbtn_triage_done () 
	{
		return (String)get_Value(COLUMNNAME_btn_triage_done);
	}

	/** Set WARD TRANSFER.
		@param btn_ward_transfer WARD TRANSFER	  */
	public void setbtn_ward_transfer (String btn_ward_transfer)
	{
		set_Value (COLUMNNAME_btn_ward_transfer, btn_ward_transfer);
	}

	/** Get WARD TRANSFER.
		@return WARD TRANSFER	  */
	public String getbtn_ward_transfer () 
	{
		return (String)get_Value(COLUMNNAME_btn_ward_transfer);
	}

	/** Set VITAL SIGNS.
		@param btn_zoom VITAL SIGNS	  */
	public void setbtn_zoom (String btn_zoom)
	{
		set_Value (COLUMNNAME_btn_zoom, btn_zoom);
	}

	/** Get VITAL SIGNS.
		@return VITAL SIGNS	  */
	public String getbtn_zoom () 
	{
		return (String)get_Value(COLUMNNAME_btn_zoom);
	}

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
			.getPO(getC_BP_Group_ID(), get_TrxName());	}

	/** Set Patient Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Patient Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Patient Name .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Patient Name .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set casier_orders.
		@param casier_orders casier_orders	  */
	public void setcasier_orders (BigDecimal casier_orders)
	{
		set_Value (COLUMNNAME_casier_orders, casier_orders);
	}

	/** Get casier_orders.
		@return casier_orders	  */
	public BigDecimal getcasier_orders () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_casier_orders);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_ValidCombination getcasier_orders_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getcasier_orders_amt(), get_TrxName());	}

	/** Set casier_orders_amt.
		@param casier_orders_amt casier_orders_amt	  */
	public void setcasier_orders_amt (int casier_orders_amt)
	{
		set_Value (COLUMNNAME_casier_orders_amt, Integer.valueOf(casier_orders_amt));
	}

	/** Get casier_orders_amt.
		@return casier_orders_amt	  */
	public int getcasier_orders_amt () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_casier_orders_amt);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set change_doctor.
		@param change_doctor 
		@admitted@='Y' OR @treatment_done@='Y' OR @attended@='Y'
	  */
	public void setchange_doctor (String change_doctor)
	{
		set_Value (COLUMNNAME_change_doctor, change_doctor);
	}

	/** Get change_doctor.
		@return @admitted@='Y' OR @treatment_done@='Y' OR @attended@='Y'
	  */
	public String getchange_doctor () 
	{
		return (String)get_Value(COLUMNNAME_change_doctor);
	}

	/** Set Check In.
		@param check_in 
		Check In
	  */
	public void setcheck_in (boolean check_in)
	{
		set_Value (COLUMNNAME_check_in, Boolean.valueOf(check_in));
	}

	/** Get Check In.
		@return Check In
	  */
	public boolean ischeck_in () 
	{
		Object oo = get_Value(COLUMNNAME_check_in);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Check In Date.
		@param check_in_date Check In Date	  */
	public void setcheck_in_date (Timestamp check_in_date)
	{
		set_Value (COLUMNNAME_check_in_date, check_in_date);
	}

	/** Get Check In Date.
		@return Check In Date	  */
	public Timestamp getcheck_in_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_check_in_date);
	}

	/** Set Check Out.
		@param check_out Check Out	  */
	public void setcheck_out (boolean check_out)
	{
		set_Value (COLUMNNAME_check_out, Boolean.valueOf(check_out));
	}

	/** Get Check Out.
		@return Check Out	  */
	public boolean ischeck_out () 
	{
		Object oo = get_Value(COLUMNNAME_check_out);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Check Out Date.
		@param check_out_date Check Out Date	  */
	public void setcheck_out_date (Timestamp check_out_date)
	{
		set_Value (COLUMNNAME_check_out_date, check_out_date);
	}

	/** Get Check Out Date.
		@return Check Out Date	  */
	public Timestamp getcheck_out_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_check_out_date);
	}

	/** Set Copay Amount.
		@param copay Copay Amount	  */
	public void setcopay (BigDecimal copay)
	{
		set_Value (COLUMNNAME_copay, copay);
	}

	/** Get Copay Amount.
		@return Copay Amount	  */
	public BigDecimal getcopay () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_copay);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time.
		@param created_time Time	  */
	public void setcreated_time (String created_time)
	{
		throw new IllegalArgumentException ("created_time is virtual column");	}

	/** Get Time.
		@return Time	  */
	public String getcreated_time () 
	{
		return (String)get_Value(COLUMNNAME_created_time);
	}

	/** Set Days Admitted.
		@param days_admitted Days Admitted	  */
	public void setdays_admitted (String days_admitted)
	{
		set_Value (COLUMNNAME_days_admitted, days_admitted);
	}

	/** Get Days Admitted.
		@return Days Admitted	  */
	public String getdays_admitted () 
	{
		return (String)get_Value(COLUMNNAME_days_admitted);
	}

	/** Set Departments Requested.
		@param departmentCode Departments Requested	  */
	public void setdepartmentCode (String departmentCode)
	{
		set_Value (COLUMNNAME_departmentCode, departmentCode);
	}

	/** Get Departments Requested.
		@return Departments Requested	  */
	public String getdepartmentCode () 
	{
		return (String)get_Value(COLUMNNAME_departmentCode);
	}

	/** Set Diagnosis.
		@param diagnosis Diagnosis	  */
	public void setdiagnosis (String diagnosis)
	{
		set_Value (COLUMNNAME_diagnosis, diagnosis);
	}

	/** Get Diagnosis.
		@return Diagnosis	  */
	public String getdiagnosis () 
	{
		return (String)get_Value(COLUMNNAME_diagnosis);
	}

	/** Set Discharge Date.
		@param discharge_date Discharge Date	  */
	public void setdischarge_date (Timestamp discharge_date)
	{
		set_Value (COLUMNNAME_discharge_date, discharge_date);
	}

	/** Get Discharge Date.
		@return Discharge Date	  */
	public Timestamp getdischarge_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_discharge_date);
	}

	/** Set Discharged.
		@param discharged Discharged	  */
	public void setdischarged (boolean discharged)
	{
		set_Value (COLUMNNAME_discharged, Boolean.valueOf(discharged));
	}

	/** Get Discharged.
		@return Discharged	  */
	public boolean isdischarged () 
	{
		Object oo = get_Value(COLUMNNAME_discharged);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_allergies.
		@param disp_allergies disp_allergies	  */
	public void setdisp_allergies (boolean disp_allergies)
	{
		set_Value (COLUMNNAME_disp_allergies, Boolean.valueOf(disp_allergies));
	}

	/** Get disp_allergies.
		@return disp_allergies	  */
	public boolean isdisp_allergies () 
	{
		Object oo = get_Value(COLUMNNAME_disp_allergies);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_diagnosis.
		@param disp_diagnosis disp_diagnosis	  */
	public void setdisp_diagnosis (boolean disp_diagnosis)
	{
		set_Value (COLUMNNAME_disp_diagnosis, Boolean.valueOf(disp_diagnosis));
	}

	/** Get disp_diagnosis.
		@return disp_diagnosis	  */
	public boolean isdisp_diagnosis () 
	{
		Object oo = get_Value(COLUMNNAME_disp_diagnosis);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_inpatient_charges.
		@param disp_inpatient_charges disp_inpatient_charges	  */
	public void setdisp_inpatient_charges (boolean disp_inpatient_charges)
	{
		set_Value (COLUMNNAME_disp_inpatient_charges, Boolean.valueOf(disp_inpatient_charges));
	}

	/** Get disp_inpatient_charges.
		@return disp_inpatient_charges	  */
	public boolean isdisp_inpatient_charges () 
	{
		Object oo = get_Value(COLUMNNAME_disp_inpatient_charges);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_lab_results.
		@param disp_lab_results disp_lab_results	  */
	public void setdisp_lab_results (boolean disp_lab_results)
	{
		set_Value (COLUMNNAME_disp_lab_results, Boolean.valueOf(disp_lab_results));
	}

	/** Get disp_lab_results.
		@return disp_lab_results	  */
	public boolean isdisp_lab_results () 
	{
		Object oo = get_Value(COLUMNNAME_disp_lab_results);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_notes.
		@param disp_notes disp_notes	  */
	public void setdisp_notes (boolean disp_notes)
	{
		set_Value (COLUMNNAME_disp_notes, Boolean.valueOf(disp_notes));
	}

	/** Get disp_notes.
		@return disp_notes	  */
	public boolean isdisp_notes () 
	{
		Object oo = get_Value(COLUMNNAME_disp_notes);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_quiz.
		@param disp_quiz disp_quiz	  */
	public void setdisp_quiz (boolean disp_quiz)
	{
		set_Value (COLUMNNAME_disp_quiz, Boolean.valueOf(disp_quiz));
	}

	/** Get disp_quiz.
		@return disp_quiz	  */
	public boolean isdisp_quiz () 
	{
		Object oo = get_Value(COLUMNNAME_disp_quiz);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp_vitals.
		@param disp_vitals disp_vitals	  */
	public void setdisp_vitals (boolean disp_vitals)
	{
		set_Value (COLUMNNAME_disp_vitals, Boolean.valueOf(disp_vitals));
	}

	/** Get disp_vitals.
		@return disp_vitals	  */
	public boolean isdisp_vitals () 
	{
		Object oo = get_Value(COLUMNNAME_disp_vitals);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_AD_User getdoctor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getdoctor_ID(), get_TrxName());	}

	/** Set doctor_ID.
		@param doctor_ID doctor_ID	  */
	public void setdoctor_ID (int doctor_ID)
	{
		if (doctor_ID < 1) 
			set_Value (COLUMNNAME_doctor_ID, null);
		else 
			set_Value (COLUMNNAME_doctor_ID, Integer.valueOf(doctor_ID));
	}

	/** Get doctor_ID.
		@return doctor_ID	  */
	public int getdoctor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_doctor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set drugs_issued.
		@param drugs_issued drugs_issued	  */
	public void setdrugs_issued (int drugs_issued)
	{
		set_Value (COLUMNNAME_drugs_issued, Integer.valueOf(drugs_issued));
	}

	/** Get drugs_issued.
		@return drugs_issued	  */
	public int getdrugs_issued () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_drugs_issued);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set drugs_not_issued.
		@param drugs_not_issued drugs_not_issued	  */
	public void setdrugs_not_issued (int drugs_not_issued)
	{
		set_Value (COLUMNNAME_drugs_not_issued, Integer.valueOf(drugs_not_issued));
	}

	/** Get drugs_not_issued.
		@return drugs_not_issued	  */
	public int getdrugs_not_issued () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_drugs_not_issued);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set drugs_ordered.
		@param drugs_ordered drugs_ordered	  */
	public void setdrugs_ordered (int drugs_ordered)
	{
		set_Value (COLUMNNAME_drugs_ordered, Integer.valueOf(drugs_ordered));
	}

	/** Get drugs_ordered.
		@return drugs_ordered	  */
	public int getdrugs_ordered () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_drugs_ordered);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Gender.
		@param Gender Gender	  */
	public void setGender (String Gender)
	{
		throw new IllegalArgumentException ("Gender is virtual column");	}

	/** Get Gender.
		@return Gender	  */
	public String getGender () 
	{
		return (String)get_Value(COLUMNNAME_Gender);
	}

	/** Set Diagnosis.
		@param hms_diagnosis_ID Diagnosis	  */
	public void sethms_diagnosis_ID (int hms_diagnosis_ID)
	{
		if (hms_diagnosis_ID < 1) 
			set_Value (COLUMNNAME_hms_diagnosis_ID, null);
		else 
			set_Value (COLUMNNAME_hms_diagnosis_ID, Integer.valueOf(hms_diagnosis_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis	  */
	public int gethms_diagnosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_insco gethms_insco() throws RuntimeException
    {
		return (I_hms_insco)MTable.get(getCtx(), I_hms_insco.Table_Name)
			.getPO(gethms_insco_ID(), get_TrxName());	}

	/** Set Insurance Company.
		@param hms_insco_ID Insurance Company	  */
	public void sethms_insco_ID (int hms_insco_ID)
	{
		if (hms_insco_ID < 1) 
			set_Value (COLUMNNAME_hms_insco_ID, null);
		else 
			set_Value (COLUMNNAME_hms_insco_ID, Integer.valueOf(hms_insco_ID));
	}

	/** Get Insurance Company.
		@return Insurance Company	  */
	public int gethms_insco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_insuredco gethms_insuredco() throws RuntimeException
    {
		return (I_hms_insuredco)MTable.get(getCtx(), I_hms_insuredco.Table_Name)
			.getPO(gethms_insuredco_ID(), get_TrxName());	}

	/** Set Insured Company.
		@param hms_insuredco_ID Insured Company	  */
	public void sethms_insuredco_ID (int hms_insuredco_ID)
	{
		if (hms_insuredco_ID < 1) 
			set_Value (COLUMNNAME_hms_insuredco_ID, null);
		else 
			set_Value (COLUMNNAME_hms_insuredco_ID, Integer.valueOf(hms_insuredco_ID));
	}

	/** Get Insured Company.
		@return Insured Company	  */
	public int gethms_insuredco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insuredco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_treatment_doc ID.
		@param hms_treatment_doc_ID hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID)
	{
		if (hms_treatment_doc_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_treatment_doc_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_treatment_doc_ID, Integer.valueOf(hms_treatment_doc_ID));
	}

	/** Get hms_treatment_doc ID.
		@return hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_treatment_doc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_ward_bed1 gethms_ward_bed1() throws RuntimeException
    {
		return (I_hms_ward_bed1)MTable.get(getCtx(), I_hms_ward_bed1.Table_Name)
			.getPO(gethms_ward_bed1_ID(), get_TrxName());	}

	/** Set Bed.
		@param hms_ward_bed1_ID Bed	  */
	public void sethms_ward_bed1_ID (int hms_ward_bed1_ID)
	{
		if (hms_ward_bed1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward_bed1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward_bed1_ID, Integer.valueOf(hms_ward_bed1_ID));
	}

	/** Get Bed.
		@return Bed	  */
	public int gethms_ward_bed1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_bed1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_ward_management1 gethms_ward_management1() throws RuntimeException
    {
		return (I_hms_ward_management1)MTable.get(getCtx(), I_hms_ward_management1.Table_Name)
			.getPO(gethms_ward_management1_ID(), get_TrxName());	}

	/** Set hms_ward_management1 ID.
		@param hms_ward_management1_ID hms_ward_management1 ID	  */
	public void sethms_ward_management1_ID (int hms_ward_management1_ID)
	{
		if (hms_ward_management1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward_management1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward_management1_ID, Integer.valueOf(hms_ward_management1_ID));
	}

	/** Get hms_ward_management1 ID.
		@return hms_ward_management1 ID	  */
	public int gethms_ward_management1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_management1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_ward1 gethms_ward1() throws RuntimeException
    {
		return (I_hms_ward1)MTable.get(getCtx(), I_hms_ward1.Table_Name)
			.getPO(gethms_ward1_ID(), get_TrxName());	}

	/** Set Ward.
		@param hms_ward1_ID Ward	  */
	public void sethms_ward1_ID (int hms_ward1_ID)
	{
		if (hms_ward1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward1_ID, Integer.valueOf(hms_ward1_ID));
	}

	/** Get Ward.
		@return Ward	  */
	public int gethms_ward1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inpatient Number.
		@param inpatient_no Inpatient Number	  */
	public void setinpatient_no (String inpatient_no)
	{
		throw new IllegalArgumentException ("inpatient_no is virtual column");	}

	/** Get Inpatient Number.
		@return Inpatient Number	  */
	public String getinpatient_no () 
	{
		return (String)get_Value(COLUMNNAME_inpatient_no);
	}

	/** Set Instance Type.
		@param instance_type Instance Type	  */
	public void setinstance_type (String instance_type)
	{
		set_Value (COLUMNNAME_instance_type, instance_type);
	}

	/** Get Instance Type.
		@return Instance Type	  */
	public String getinstance_type () 
	{
		return (String)get_Value(COLUMNNAME_instance_type);
	}

	/** Set Lab Done.
		@param lab_done Lab Done	  */
	public void setlab_done (boolean lab_done)
	{
		set_Value (COLUMNNAME_lab_done, Boolean.valueOf(lab_done));
	}

	/** Get Lab Done.
		@return Lab Done	  */
	public boolean islab_done () 
	{
		Object oo = get_Value(COLUMNNAME_lab_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lab Results Seen.
		@param lab_results_seen Lab Results Seen	  */
	public void setlab_results_seen (boolean lab_results_seen)
	{
		set_Value (COLUMNNAME_lab_results_seen, Boolean.valueOf(lab_results_seen));
	}

	/** Get Lab Results Seen.
		@return Lab Results Seen	  */
	public boolean islab_results_seen () 
	{
		Object oo = get_Value(COLUMNNAME_lab_results_seen);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lab Self Request.
		@param lab_self_request Lab Self Request	  */
	public void setlab_self_request (boolean lab_self_request)
	{
		set_Value (COLUMNNAME_lab_self_request, Boolean.valueOf(lab_self_request));
	}

	/** Get Lab Self Request.
		@return Lab Self Request	  */
	public boolean islab_self_request () 
	{
		Object oo = get_Value(COLUMNNAME_lab_self_request);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Open Balance for this instance.
		@param open_balance Open Balance for this instance	  */
	public void setopen_balance (BigDecimal open_balance)
	{
		set_Value (COLUMNNAME_open_balance, open_balance);
	}

	/** Get Open Balance for this instance.
		@return Open Balance for this instance	  */
	public BigDecimal getopen_balance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_open_balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set orders_not_issued.
		@param orders_not_issued orders_not_issued	  */
	public void setorders_not_issued (String orders_not_issued)
	{
		set_Value (COLUMNNAME_orders_not_issued, orders_not_issued);
	}

	/** Get orders_not_issued.
		@return orders_not_issued	  */
	public String getorders_not_issued () 
	{
		return (String)get_Value(COLUMNNAME_orders_not_issued);
	}

	/** Set Outpatient Number.
		@param outpatient_no Outpatient Number	  */
	public void setoutpatient_no (String outpatient_no)
	{
		throw new IllegalArgumentException ("outpatient_no is virtual column");	}

	/** Get Outpatient Number.
		@return Outpatient Number	  */
	public String getoutpatient_no () 
	{
		return (String)get_Value(COLUMNNAME_outpatient_no);
	}

	/** Set Patient Location.
		@param patient_location Patient Location	  */
	public void setpatient_location (String patient_location)
	{
		set_Value (COLUMNNAME_patient_location, patient_location);
	}

	/** Get Patient Location.
		@return Patient Location	  */
	public String getpatient_location () 
	{
		return (String)get_Value(COLUMNNAME_patient_location);
	}

	/** Set Patient Status.
		@param patient_status Patient Status	  */
	public void setpatient_status (String patient_status)
	{
		set_Value (COLUMNNAME_patient_status, patient_status);
	}

	/** Get Patient Status.
		@return Patient Status	  */
	public String getpatient_status () 
	{
		return (String)get_Value(COLUMNNAME_patient_status);
	}

	/** Set pharm_done.
		@param pharm_done pharm_done	  */
	public void setpharm_done (boolean pharm_done)
	{
		set_Value (COLUMNNAME_pharm_done, Boolean.valueOf(pharm_done));
	}

	/** Get pharm_done.
		@return pharm_done	  */
	public boolean ispharm_done () 
	{
		Object oo = get_Value(COLUMNNAME_pharm_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set pharmacy_orders.
		@param pharmacy_orders pharmacy_orders	  */
	public void setpharmacy_orders (String pharmacy_orders)
	{
		set_Value (COLUMNNAME_pharmacy_orders, pharmacy_orders);
	}

	/** Get pharmacy_orders.
		@return pharmacy_orders	  */
	public String getpharmacy_orders () 
	{
		return (String)get_Value(COLUMNNAME_pharmacy_orders);
	}

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (BigDecimal Priority)
	{
		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public BigDecimal getPriority () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Priority);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rebate Amount.
		@param rebate_amount Rebate Amount	  */
	public void setrebate_amount (BigDecimal rebate_amount)
	{
		set_Value (COLUMNNAME_rebate_amount, rebate_amount);
	}

	/** Get Rebate Amount.
		@return Rebate Amount	  */
	public BigDecimal getrebate_amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_rebate_amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Referred From.
		@param referred_from Referred From	  */
	public void setreferred_from (String referred_from)
	{
		set_Value (COLUMNNAME_referred_from, referred_from);
	}

	/** Get Referred From.
		@return Referred From	  */
	public String getreferred_from () 
	{
		return (String)get_Value(COLUMNNAME_referred_from);
	}

	/** Set Referred In.
		@param referred_in Referred In	  */
	public void setreferred_in (boolean referred_in)
	{
		set_Value (COLUMNNAME_referred_in, Boolean.valueOf(referred_in));
	}

	/** Get Referred In.
		@return Referred In	  */
	public boolean isreferred_in () 
	{
		Object oo = get_Value(COLUMNNAME_referred_in);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Referred Out.
		@param referred_out Referred Out	  */
	public void setreferred_out (boolean referred_out)
	{
		set_Value (COLUMNNAME_referred_out, Boolean.valueOf(referred_out));
	}

	/** Get Referred Out.
		@return Referred Out	  */
	public boolean isreferred_out () 
	{
		Object oo = get_Value(COLUMNNAME_referred_out);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Referred To.
		@param referred_to Referred To	  */
	public void setreferred_to (String referred_to)
	{
		set_Value (COLUMNNAME_referred_to, referred_to);
	}

	/** Get Referred To.
		@return Referred To	  */
	public String getreferred_to () 
	{
		return (String)get_Value(COLUMNNAME_referred_to);
	}

	/** Set state.
		@param state state	  */
	public void setstate (String state)
	{
		set_Value (COLUMNNAME_state, state);
	}

	/** Get state.
		@return state	  */
	public String getstate () 
	{
		return (String)get_Value(COLUMNNAME_state);
	}

	/** Set state2.
		@param state2 state2	  */
	public void setstate2 (String state2)
	{
		set_Value (COLUMNNAME_state2, state2);
	}

	/** Get state2.
		@return state2	  */
	public String getstate2 () 
	{
		return (String)get_Value(COLUMNNAME_state2);
	}

	/** Set to_cashier.
		@param to_cashier to_cashier	  */
	public void setto_cashier (boolean to_cashier)
	{
		set_Value (COLUMNNAME_to_cashier, Boolean.valueOf(to_cashier));
	}

	/** Get to_cashier.
		@return to_cashier	  */
	public boolean isto_cashier () 
	{
		Object oo = get_Value(COLUMNNAME_to_cashier);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_dental.
		@param to_dental to_dental	  */
	public void setto_dental (boolean to_dental)
	{
		set_Value (COLUMNNAME_to_dental, Boolean.valueOf(to_dental));
	}

	/** Get to_dental.
		@return to_dental	  */
	public boolean isto_dental () 
	{
		Object oo = get_Value(COLUMNNAME_to_dental);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_doctor.
		@param to_doctor to_doctor	  */
	public void setto_doctor (boolean to_doctor)
	{
		set_Value (COLUMNNAME_to_doctor, Boolean.valueOf(to_doctor));
	}

	/** Get to_doctor.
		@return to_doctor	  */
	public boolean isto_doctor () 
	{
		Object oo = get_Value(COLUMNNAME_to_doctor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_eye.
		@param to_eye to_eye	  */
	public void setto_eye (boolean to_eye)
	{
		set_Value (COLUMNNAME_to_eye, Boolean.valueOf(to_eye));
	}

	/** Get to_eye.
		@return to_eye	  */
	public boolean isto_eye () 
	{
		Object oo = get_Value(COLUMNNAME_to_eye);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_lab.
		@param to_lab to_lab	  */
	public void setto_lab (boolean to_lab)
	{
		set_Value (COLUMNNAME_to_lab, Boolean.valueOf(to_lab));
	}

	/** Get to_lab.
		@return to_lab	  */
	public boolean isto_lab () 
	{
		Object oo = get_Value(COLUMNNAME_to_lab);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_pharm.
		@param to_pharm to_pharm	  */
	public void setto_pharm (boolean to_pharm)
	{
		set_Value (COLUMNNAME_to_pharm, Boolean.valueOf(to_pharm));
	}

	/** Get to_pharm.
		@return to_pharm	  */
	public boolean isto_pharm () 
	{
		Object oo = get_Value(COLUMNNAME_to_pharm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_theatre.
		@param to_theatre to_theatre	  */
	public void setto_theatre (boolean to_theatre)
	{
		set_Value (COLUMNNAME_to_theatre, Boolean.valueOf(to_theatre));
	}

	/** Get to_theatre.
		@return to_theatre	  */
	public boolean isto_theatre () 
	{
		Object oo = get_Value(COLUMNNAME_to_theatre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_triage.
		@param to_triage to_triage	  */
	public void setto_triage (boolean to_triage)
	{
		set_Value (COLUMNNAME_to_triage, Boolean.valueOf(to_triage));
	}

	/** Get to_triage.
		@return to_triage	  */
	public boolean isto_triage () 
	{
		Object oo = get_Value(COLUMNNAME_to_triage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_ultrasound.
		@param to_ultrasound to_ultrasound	  */
	public void setto_ultrasound (boolean to_ultrasound)
	{
		set_Value (COLUMNNAME_to_ultrasound, Boolean.valueOf(to_ultrasound));
	}

	/** Get to_ultrasound.
		@return to_ultrasound	  */
	public boolean isto_ultrasound () 
	{
		Object oo = get_Value(COLUMNNAME_to_ultrasound);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set to_ward.
		@param to_ward to_ward	  */
	public void setto_ward (String to_ward)
	{
		set_Value (COLUMNNAME_to_ward, to_ward);
	}

	/** Get to_ward.
		@return to_ward	  */
	public String getto_ward () 
	{
		return (String)get_Value(COLUMNNAME_to_ward);
	}

	/** Set to_xray.
		@param to_xray to_xray	  */
	public void setto_xray (boolean to_xray)
	{
		set_Value (COLUMNNAME_to_xray, Boolean.valueOf(to_xray));
	}

	/** Get to_xray.
		@return to_xray	  */
	public boolean isto_xray () 
	{
		Object oo = get_Value(COLUMNNAME_to_xray);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Balance.
		@param TotalOpenBalance 
		Total Open Balance Amount in primary Accounting Currency
	  */
	public void setTotalOpenBalance (BigDecimal TotalOpenBalance)
	{
		set_ValueNoCheck (COLUMNNAME_TotalOpenBalance, TotalOpenBalance);
	}

	/** Get Open Balance.
		@return Total Open Balance Amount in primary Accounting Currency
	  */
	public BigDecimal getTotalOpenBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOpenBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Instance ID.
		@param treat_ref_ID Instance ID	  */
	public void settreat_ref_ID (int treat_ref_ID)
	{
		if (treat_ref_ID < 1) 
			set_Value (COLUMNNAME_treat_ref_ID, null);
		else 
			set_Value (COLUMNNAME_treat_ref_ID, Integer.valueOf(treat_ref_ID));
	}

	/** Get Instance ID.
		@return Instance ID	  */
	public int gettreat_ref_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_treat_ref_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment Done.
		@param treatment_done Treatment Done	  */
	public void settreatment_done (boolean treatment_done)
	{
		set_Value (COLUMNNAME_treatment_done, Boolean.valueOf(treatment_done));
	}

	/** Get Treatment Done.
		@return Treatment Done	  */
	public boolean istreatment_done () 
	{
		Object oo = get_Value(COLUMNNAME_treatment_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set treatment_done_date.
		@param treatment_done_date treatment_done_date	  */
	public void settreatment_done_date (Timestamp treatment_done_date)
	{
		set_Value (COLUMNNAME_treatment_done_date, treatment_done_date);
	}

	/** Get treatment_done_date.
		@return treatment_done_date	  */
	public Timestamp gettreatment_done_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_treatment_done_date);
	}

	/** Set Triage Done.
		@param triage_done Triage Done	  */
	public void settriage_done (boolean triage_done)
	{
		set_Value (COLUMNNAME_triage_done, Boolean.valueOf(triage_done));
	}

	/** Get Triage Done.
		@return Triage Done	  */
	public boolean istriage_done () 
	{
		Object oo = get_Value(COLUMNNAME_triage_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Visit Type.
		@param visit_type Visit Type	  */
	public void setvisit_type (String visit_type)
	{
		set_Value (COLUMNNAME_visit_type, visit_type);
	}

	/** Get Visit Type.
		@return Visit Type	  */
	public String getvisit_type () 
	{
		return (String)get_Value(COLUMNNAME_visit_type);
	}

	/** Set vitals_done.
		@param vitals_done vitals_done	  */
	public void setvitals_done (boolean vitals_done)
	{
		set_Value (COLUMNNAME_vitals_done, Boolean.valueOf(vitals_done));
	}

	/** Get vitals_done.
		@return vitals_done	  */
	public boolean isvitals_done () 
	{
		Object oo = get_Value(COLUMNNAME_vitals_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vitals Not Available.
		@param vitals_not_available Vitals Not Available	  */
	public void setvitals_not_available (boolean vitals_not_available)
	{
		set_Value (COLUMNNAME_vitals_not_available, Boolean.valueOf(vitals_not_available));
	}

	/** Get Vitals Not Available.
		@return Vitals Not Available	  */
	public boolean isvitals_not_available () 
	{
		Object oo = get_Value(COLUMNNAME_vitals_not_available);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ALLERGIES.
		@param zoom_allergies ALLERGIES	  */
	public void setzoom_allergies (String zoom_allergies)
	{
		set_Value (COLUMNNAME_zoom_allergies, zoom_allergies);
	}

	/** Get ALLERGIES.
		@return ALLERGIES	  */
	public String getzoom_allergies () 
	{
		return (String)get_Value(COLUMNNAME_zoom_allergies);
	}

	/** Set DRUG ORDERS.
		@param zoom_billing DRUG ORDERS	  */
	public void setzoom_billing (String zoom_billing)
	{
		set_Value (COLUMNNAME_zoom_billing, zoom_billing);
	}

	/** Get DRUG ORDERS.
		@return DRUG ORDERS	  */
	public String getzoom_billing () 
	{
		return (String)get_Value(COLUMNNAME_zoom_billing);
	}

	/** Set DEPARTMENTAL REQUEST.
		@param zoom_departmental_request DEPARTMENTAL REQUEST	  */
	public void setzoom_departmental_request (String zoom_departmental_request)
	{
		set_Value (COLUMNNAME_zoom_departmental_request, zoom_departmental_request);
	}

	/** Get DEPARTMENTAL REQUEST.
		@return DEPARTMENTAL REQUEST	  */
	public String getzoom_departmental_request () 
	{
		return (String)get_Value(COLUMNNAME_zoom_departmental_request);
	}

	/** Set DIAGNOSIS.
		@param zoom_diagnosis DIAGNOSIS	  */
	public void setzoom_diagnosis (String zoom_diagnosis)
	{
		set_Value (COLUMNNAME_zoom_diagnosis, zoom_diagnosis);
	}

	/** Get DIAGNOSIS.
		@return DIAGNOSIS	  */
	public String getzoom_diagnosis () 
	{
		return (String)get_Value(COLUMNNAME_zoom_diagnosis);
	}

	/** Set DISCHARGE DRUGS.
		@param zoom_discharge_drug DISCHARGE DRUGS	  */
	public void setzoom_discharge_drug (String zoom_discharge_drug)
	{
		set_Value (COLUMNNAME_zoom_discharge_drug, zoom_discharge_drug);
	}

	/** Get DISCHARGE DRUGS.
		@return DISCHARGE DRUGS	  */
	public String getzoom_discharge_drug () 
	{
		return (String)get_Value(COLUMNNAME_zoom_discharge_drug);
	}

	/** Set DOCTOR NOTES.
		@param zoom_notes DOCTOR NOTES	  */
	public void setzoom_notes (String zoom_notes)
	{
		set_Value (COLUMNNAME_zoom_notes, zoom_notes);
	}

	/** Get DOCTOR NOTES.
		@return DOCTOR NOTES	  */
	public String getzoom_notes () 
	{
		return (String)get_Value(COLUMNNAME_zoom_notes);
	}

	/** Set OTHER CHARGES.
		@param zoom_othercharges OTHER CHARGES	  */
	public void setzoom_othercharges (String zoom_othercharges)
	{
		set_Value (COLUMNNAME_zoom_othercharges, zoom_othercharges);
	}

	/** Get OTHER CHARGES.
		@return OTHER CHARGES	  */
	public String getzoom_othercharges () 
	{
		return (String)get_Value(COLUMNNAME_zoom_othercharges);
	}
}