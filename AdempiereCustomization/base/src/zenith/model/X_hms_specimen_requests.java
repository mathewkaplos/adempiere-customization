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

/** Generated Model for hms_specimen_requests
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_specimen_requests extends PO implements I_hms_specimen_requests, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181004L;

    /** Standard Constructor */
    public X_hms_specimen_requests (Properties ctx, int hms_specimen_requests_ID, String trxName)
    {
      super (ctx, hms_specimen_requests_ID, trxName);
      /** if (hms_specimen_requests_ID == 0)
        {
			setC_BPartner_ID (0);
			sethms_specimen_requests_ID (0);
			sethms_test_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_specimen_requests (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_specimen_requests[")
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

	/** Set bed.
		@param bed bed	  */
	public void setbed (String bed)
	{
		throw new IllegalArgumentException ("bed is virtual column");	}

	/** Get bed.
		@return bed	  */
	public String getbed () 
	{
		return (String)get_Value(COLUMNNAME_bed);
	}

	/** Set DONE.
		@param btn_dn DONE	  */
	public void setbtn_dn (String btn_dn)
	{
		set_Value (COLUMNNAME_btn_dn, btn_dn);
	}

	/** Get DONE.
		@return DONE	  */
	public String getbtn_dn () 
	{
		return (String)get_Value(COLUMNNAME_btn_dn);
	}

	/** Set btn_not_available.
		@param btn_not_available btn_not_available	  */
	public void setbtn_not_available (boolean btn_not_available)
	{
		set_Value (COLUMNNAME_btn_not_available, Boolean.valueOf(btn_not_available));
	}

	/** Get btn_not_available.
		@return btn_not_available	  */
	public boolean isbtn_not_available () 
	{
		Object oo = get_Value(COLUMNNAME_btn_not_available);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SEND TO DOCTOR.
		@param btn_submit SEND TO DOCTOR	  */
	public void setbtn_submit (String btn_submit)
	{
		set_Value (COLUMNNAME_btn_submit, btn_submit);
	}

	/** Get SEND TO DOCTOR.
		@return SEND TO DOCTOR	  */
	public String getbtn_submit () 
	{
		return (String)get_Value(COLUMNNAME_btn_submit);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
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

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set cancelled.
		@param cancelled cancelled	  */
	public void setcancelled (boolean cancelled)
	{
		set_Value (COLUMNNAME_cancelled, Boolean.valueOf(cancelled));
	}

	/** Get cancelled.
		@return cancelled	  */
	public boolean iscancelled () 
	{
		Object oo = get_Value(COLUMNNAME_cancelled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set dental_done.
		@param dental_done dental_done	  */
	public void setdental_done (boolean dental_done)
	{
		set_Value (COLUMNNAME_dental_done, Boolean.valueOf(dental_done));
	}

	/** Get dental_done.
		@return dental_done	  */
	public boolean isdental_done () 
	{
		Object oo = get_Value(COLUMNNAME_dental_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Department Code.
		@param departmentCode Department Code	  */
	public void setdepartmentCode (String departmentCode)
	{
		set_Value (COLUMNNAME_departmentCode, departmentCode);
	}

	/** Get Department Code.
		@return Department Code	  */
	public String getdepartmentCode () 
	{
		return (String)get_Value(COLUMNNAME_departmentCode);
	}

	/** Set disp_consumables.
		@param disp_consumables disp_consumables	  */
	public void setdisp_consumables (boolean disp_consumables)
	{
		set_Value (COLUMNNAME_disp_consumables, Boolean.valueOf(disp_consumables));
	}

	/** Get disp_consumables.
		@return disp_consumables	  */
	public boolean isdisp_consumables () 
	{
		Object oo = get_Value(COLUMNNAME_disp_consumables);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set doctor_no.
		@param doctor_no doctor_no	  */
	public void setdoctor_no (String doctor_no)
	{
		set_Value (COLUMNNAME_doctor_no, doctor_no);
	}

	/** Get doctor_no.
		@return doctor_no	  */
	public String getdoctor_no () 
	{
		return (String)get_Value(COLUMNNAME_doctor_no);
	}

	/** Set Done.
		@param done Done	  */
	public void setdone (boolean done)
	{
		set_Value (COLUMNNAME_done, Boolean.valueOf(done));
	}

	/** Get Done.
		@return Done	  */
	public boolean isdone () 
	{
		Object oo = get_Value(COLUMNNAME_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Done Date.
		@param done_date 
		Done Date
	  */
	public void setdone_date (Timestamp done_date)
	{
		set_Value (COLUMNNAME_done_date, done_date);
	}

	/** Get Done Date.
		@return Done Date
	  */
	public Timestamp getdone_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_done_date);
	}

	/** Set Done Time.
		@param done_time 
		Done Time
	  */
	public void setdone_time (Timestamp done_time)
	{
		set_Value (COLUMNNAME_done_time, done_time);
	}

	/** Get Done Time.
		@return Done Time
	  */
	public Timestamp getdone_time () 
	{
		return (Timestamp)get_Value(COLUMNNAME_done_time);
	}

	/** Set Edit.
		@param edit Edit	  */
	public void setedit (boolean edit)
	{
		set_Value (COLUMNNAME_edit, Boolean.valueOf(edit));
	}

	/** Get Edit.
		@return Edit	  */
	public boolean isedit () 
	{
		Object oo = get_Value(COLUMNNAME_edit);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set eye_done.
		@param eye_done eye_done	  */
	public void seteye_done (boolean eye_done)
	{
		set_Value (COLUMNNAME_eye_done, Boolean.valueOf(eye_done));
	}

	/** Get eye_done.
		@return eye_done	  */
	public boolean iseye_done () 
	{
		Object oo = get_Value(COLUMNNAME_eye_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_hms_billing gethms_billing() throws RuntimeException
    {
		return (I_hms_billing)MTable.get(getCtx(), I_hms_billing.Table_Name)
			.getPO(gethms_billing_ID(), get_TrxName());	}

	/** Set hms_billing ID.
		@param hms_billing_ID hms_billing ID	  */
	public void sethms_billing_ID (int hms_billing_ID)
	{
		if (hms_billing_ID < 1) 
			set_Value (COLUMNNAME_hms_billing_ID, null);
		else 
			set_Value (COLUMNNAME_hms_billing_ID, Integer.valueOf(hms_billing_ID));
	}

	/** Get hms_billing ID.
		@return hms_billing ID	  */
	public int gethms_billing_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_billing_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_department gethms_department() throws RuntimeException
    {
		return (I_hms_department)MTable.get(getCtx(), I_hms_department.Table_Name)
			.getPO(gethms_department_ID(), get_TrxName());	}

	/** Set Department.
		@param hms_department_ID Department	  */
	public void sethms_department_ID (int hms_department_ID)
	{
		if (hms_department_ID < 1) 
			set_Value (COLUMNNAME_hms_department_ID, null);
		else 
			set_Value (COLUMNNAME_hms_department_ID, Integer.valueOf(hms_department_ID));
	}

	/** Get Department.
		@return Department	  */
	public int gethms_department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_specimen_requests ID.
		@param hms_specimen_requests_ID hms_specimen_requests ID	  */
	public void sethms_specimen_requests_ID (int hms_specimen_requests_ID)
	{
		if (hms_specimen_requests_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_specimen_requests_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_specimen_requests_ID, Integer.valueOf(hms_specimen_requests_ID));
	}

	/** Get hms_specimen_requests ID.
		@return hms_specimen_requests ID	  */
	public int gethms_specimen_requests_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_specimen_requests_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Test.
		@param hms_test_ID 
		Lab Test 
	  */
	public void sethms_test_ID (int hms_test_ID)
	{
		if (hms_test_ID < 1) 
			set_Value (COLUMNNAME_hms_test_ID, null);
		else 
			set_Value (COLUMNNAME_hms_test_ID, Integer.valueOf(hms_test_ID));
	}

	/** Get Test.
		@return Lab Test 
	  */
	public int gethms_test_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_test_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException
    {
		return (I_hms_treatment_doc)MTable.get(getCtx(), I_hms_treatment_doc.Table_Name)
			.getPO(gethms_treatment_doc_ID(), get_TrxName());	}

	/** Set hms_treatment_doc ID.
		@param hms_treatment_doc_ID hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID)
	{
		if (hms_treatment_doc_ID < 1) 
			set_Value (COLUMNNAME_hms_treatment_doc_ID, null);
		else 
			set_Value (COLUMNNAME_hms_treatment_doc_ID, Integer.valueOf(hms_treatment_doc_ID));
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

	/** Set is_dental.
		@param is_dental is_dental	  */
	public void setis_dental (boolean is_dental)
	{
		set_Value (COLUMNNAME_is_dental, Boolean.valueOf(is_dental));
	}

	/** Get is_dental.
		@return is_dental	  */
	public boolean is_dental () 
	{
		Object oo = get_Value(COLUMNNAME_is_dental);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_eye.
		@param is_eye is_eye	  */
	public void setis_eye (boolean is_eye)
	{
		set_Value (COLUMNNAME_is_eye, Boolean.valueOf(is_eye));
	}

	/** Get is_eye.
		@return is_eye	  */
	public boolean is_eye () 
	{
		Object oo = get_Value(COLUMNNAME_is_eye);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_lab.
		@param is_lab is_lab	  */
	public void setis_lab (boolean is_lab)
	{
		set_Value (COLUMNNAME_is_lab, Boolean.valueOf(is_lab));
	}

	/** Get is_lab.
		@return is_lab	  */
	public boolean is_lab () 
	{
		Object oo = get_Value(COLUMNNAME_is_lab);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_theatre.
		@param is_theatre is_theatre	  */
	public void setis_theatre (boolean is_theatre)
	{
		set_Value (COLUMNNAME_is_theatre, Boolean.valueOf(is_theatre));
	}

	/** Get is_theatre.
		@return is_theatre	  */
	public boolean is_theatre () 
	{
		Object oo = get_Value(COLUMNNAME_is_theatre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_ultrasound.
		@param is_ultrasound is_ultrasound	  */
	public void setis_ultrasound (boolean is_ultrasound)
	{
		set_Value (COLUMNNAME_is_ultrasound, Boolean.valueOf(is_ultrasound));
	}

	/** Get is_ultrasound.
		@return is_ultrasound	  */
	public boolean is_ultrasound () 
	{
		Object oo = get_Value(COLUMNNAME_is_ultrasound);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_xray.
		@param is_xray is_xray	  */
	public void setis_xray (boolean is_xray)
	{
		set_Value (COLUMNNAME_is_xray, Boolean.valueOf(is_xray));
	}

	/** Get is_xray.
		@return is_xray	  */
	public boolean is_xray () 
	{
		Object oo = get_Value(COLUMNNAME_is_xray);
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

	/** Set not_available.
		@param not_available not_available	  */
	public void setnot_available (boolean not_available)
	{
		set_Value (COLUMNNAME_not_available, Boolean.valueOf(not_available));
	}

	/** Get not_available.
		@return not_available	  */
	public boolean isnot_available () 
	{
		Object oo = get_Value(COLUMNNAME_not_available);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set paid.
		@param paid paid	  */
	public void setpaid (boolean paid)
	{
		set_Value (COLUMNNAME_paid, Boolean.valueOf(paid));
	}

	/** Get paid.
		@return paid	  */
	public boolean ispaid () 
	{
		Object oo = get_Value(COLUMNNAME_paid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set pay_after.
		@param pay_after pay_after	  */
	public void setpay_after (boolean pay_after)
	{
		set_Value (COLUMNNAME_pay_after, Boolean.valueOf(pay_after));
	}

	/** Get pay_after.
		@return pay_after	  */
	public boolean ispay_after () 
	{
		Object oo = get_Value(COLUMNNAME_pay_after);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set percentage_done.
		@param percentage_done percentage_done	  */
	public void setpercentage_done (BigDecimal percentage_done)
	{
		set_Value (COLUMNNAME_percentage_done, percentage_done);
	}

	/** Get percentage_done.
		@return percentage_done	  */
	public BigDecimal getpercentage_done () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_percentage_done);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Positive.
		@param positive Positive	  */
	public void setpositive (boolean positive)
	{
		set_Value (COLUMNNAME_positive, Boolean.valueOf(positive));
	}

	/** Get Positive.
		@return Positive	  */
	public boolean ispositive () 
	{
		Object oo = get_Value(COLUMNNAME_positive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set repeat.
		@param repeat repeat	  */
	public void setrepeat (boolean repeat)
	{
		set_Value (COLUMNNAME_repeat, Boolean.valueOf(repeat));
	}

	/** Get repeat.
		@return repeat	  */
	public boolean isrepeat () 
	{
		Object oo = get_Value(COLUMNNAME_repeat);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Results.
		@param results Results	  */
	public void setresults (String results)
	{
		set_Value (COLUMNNAME_results, results);
	}

	/** Get Results.
		@return Results	  */
	public String getresults () 
	{
		return (String)get_Value(COLUMNNAME_results);
	}

	/** Set Send Date.
		@param send_date Send Date	  */
	public void setsend_date (Timestamp send_date)
	{
		set_Value (COLUMNNAME_send_date, send_date);
	}

	/** Get Send Date.
		@return Send Date	  */
	public Timestamp getsend_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_send_date);
	}

	/** Set Send Time.
		@param send_time Send Time	  */
	public void setsend_time (Timestamp send_time)
	{
		set_Value (COLUMNNAME_send_time, send_time);
	}

	/** Get Send Time.
		@return Send Time	  */
	public Timestamp getsend_time () 
	{
		return (Timestamp)get_Value(COLUMNNAME_send_time);
	}

	/** Set test.
		@param test test	  */
	public void settest (String test)
	{
		set_Value (COLUMNNAME_test, test);
	}

	/** Get test.
		@return test	  */
	public String gettest () 
	{
		return (String)get_Value(COLUMNNAME_test);
	}

	/** Set theatre_done.
		@param theatre_done theatre_done	  */
	public void settheatre_done (boolean theatre_done)
	{
		set_Value (COLUMNNAME_theatre_done, Boolean.valueOf(theatre_done));
	}

	/** Get theatre_done.
		@return theatre_done	  */
	public boolean istheatre_done () 
	{
		Object oo = get_Value(COLUMNNAME_theatre_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ultrasound_done.
		@param ultrasound_done ultrasound_done	  */
	public void setultrasound_done (boolean ultrasound_done)
	{
		set_Value (COLUMNNAME_ultrasound_done, Boolean.valueOf(ultrasound_done));
	}

	/** Get ultrasound_done.
		@return ultrasound_done	  */
	public boolean isultrasound_done () 
	{
		Object oo = get_Value(COLUMNNAME_ultrasound_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ward.
		@param ward ward	  */
	public void setward (String ward)
	{
		throw new IllegalArgumentException ("ward is virtual column");	}

	/** Get ward.
		@return ward	  */
	public String getward () 
	{
		return (String)get_Value(COLUMNNAME_ward);
	}

	/** Set xray_done.
		@param xray_done xray_done	  */
	public void setxray_done (boolean xray_done)
	{
		set_Value (COLUMNNAME_xray_done, Boolean.valueOf(xray_done));
	}

	/** Get xray_done.
		@return xray_done	  */
	public boolean isxray_done () 
	{
		Object oo = get_Value(COLUMNNAME_xray_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}