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

/** Generated Interface for hms_specimen_requests
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_specimen_requests 
{

    /** TableName=hms_specimen_requests */
    public static final String Table_Name = "hms_specimen_requests";

    /** AD_Table_ID=1000015 */
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

    /** Column name bed */
    public static final String COLUMNNAME_bed = "bed";

	/** Set bed	  */
	public void setbed (String bed);

	/** Get bed	  */
	public String getbed();

    /** Column name btn_dn */
    public static final String COLUMNNAME_btn_dn = "btn_dn";

	/** Set DONE	  */
	public void setbtn_dn (String btn_dn);

	/** Get DONE	  */
	public String getbtn_dn();

    /** Column name btn_not_available */
    public static final String COLUMNNAME_btn_not_available = "btn_not_available";

	/** Set btn_not_available	  */
	public void setbtn_not_available (boolean btn_not_available);

	/** Get btn_not_available	  */
	public boolean isbtn_not_available();

    /** Column name btn_submit */
    public static final String COLUMNNAME_btn_submit = "btn_submit";

	/** Set SEND TO DOCTOR	  */
	public void setbtn_submit (String btn_submit);

	/** Get SEND TO DOCTOR	  */
	public String getbtn_submit();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException;

    /** Column name cancelled */
    public static final String COLUMNNAME_cancelled = "cancelled";

	/** Set cancelled	  */
	public void setcancelled (boolean cancelled);

	/** Get cancelled	  */
	public boolean iscancelled();

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

    /** Column name dental_done */
    public static final String COLUMNNAME_dental_done = "dental_done";

	/** Set dental_done	  */
	public void setdental_done (boolean dental_done);

	/** Get dental_done	  */
	public boolean isdental_done();

    /** Column name departmentCode */
    public static final String COLUMNNAME_departmentCode = "departmentCode";

	/** Set Department Code	  */
	public void setdepartmentCode (String departmentCode);

	/** Get Department Code	  */
	public String getdepartmentCode();

    /** Column name disp_consumables */
    public static final String COLUMNNAME_disp_consumables = "disp_consumables";

	/** Set disp_consumables	  */
	public void setdisp_consumables (boolean disp_consumables);

	/** Get disp_consumables	  */
	public boolean isdisp_consumables();

    /** Column name doctor_no */
    public static final String COLUMNNAME_doctor_no = "doctor_no";

	/** Set doctor_no	  */
	public void setdoctor_no (String doctor_no);

	/** Get doctor_no	  */
	public String getdoctor_no();

    /** Column name done */
    public static final String COLUMNNAME_done = "done";

	/** Set Done	  */
	public void setdone (boolean done);

	/** Get Done	  */
	public boolean isdone();

    /** Column name done_date */
    public static final String COLUMNNAME_done_date = "done_date";

	/** Set Done Date.
	  * Done Date
	  */
	public void setdone_date (Timestamp done_date);

	/** Get Done Date.
	  * Done Date
	  */
	public Timestamp getdone_date();

    /** Column name done_time */
    public static final String COLUMNNAME_done_time = "done_time";

	/** Set Done Time.
	  * Done Time
	  */
	public void setdone_time (Timestamp done_time);

	/** Get Done Time.
	  * Done Time
	  */
	public Timestamp getdone_time();

    /** Column name edit */
    public static final String COLUMNNAME_edit = "edit";

	/** Set Edit	  */
	public void setedit (boolean edit);

	/** Get Edit	  */
	public boolean isedit();

    /** Column name eye_done */
    public static final String COLUMNNAME_eye_done = "eye_done";

	/** Set eye_done	  */
	public void seteye_done (boolean eye_done);

	/** Get eye_done	  */
	public boolean iseye_done();

    /** Column name hms_billing_ID */
    public static final String COLUMNNAME_hms_billing_ID = "hms_billing_ID";

	/** Set hms_billing ID	  */
	public void sethms_billing_ID (int hms_billing_ID);

	/** Get hms_billing ID	  */
	public int gethms_billing_ID();

	public I_hms_billing gethms_billing() throws RuntimeException;

    /** Column name hms_department_ID */
    public static final String COLUMNNAME_hms_department_ID = "hms_department_ID";

	/** Set Department	  */
	public void sethms_department_ID (int hms_department_ID);

	/** Get Department	  */
	public int gethms_department_ID();

	public I_hms_department gethms_department() throws RuntimeException;

    /** Column name hms_specimen_requests_ID */
    public static final String COLUMNNAME_hms_specimen_requests_ID = "hms_specimen_requests_ID";

	/** Set hms_specimen_requests ID	  */
	public void sethms_specimen_requests_ID (int hms_specimen_requests_ID);

	/** Get hms_specimen_requests ID	  */
	public int gethms_specimen_requests_ID();

    /** Column name hms_test_ID */
    public static final String COLUMNNAME_hms_test_ID = "hms_test_ID";

	/** Set Test.
	  * Lab Test 
	  */
	public void sethms_test_ID (int hms_test_ID);

	/** Get Test.
	  * Lab Test 
	  */
	public int gethms_test_ID();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name is_dental */
    public static final String COLUMNNAME_is_dental = "is_dental";

	/** Set is_dental	  */
	public void setis_dental (boolean is_dental);

	/** Get is_dental	  */
	public boolean is_dental();

    /** Column name is_eye */
    public static final String COLUMNNAME_is_eye = "is_eye";

	/** Set is_eye	  */
	public void setis_eye (boolean is_eye);

	/** Get is_eye	  */
	public boolean is_eye();

    /** Column name is_lab */
    public static final String COLUMNNAME_is_lab = "is_lab";

	/** Set is_lab	  */
	public void setis_lab (boolean is_lab);

	/** Get is_lab	  */
	public boolean is_lab();

    /** Column name is_theatre */
    public static final String COLUMNNAME_is_theatre = "is_theatre";

	/** Set is_theatre	  */
	public void setis_theatre (boolean is_theatre);

	/** Get is_theatre	  */
	public boolean is_theatre();

    /** Column name is_ultrasound */
    public static final String COLUMNNAME_is_ultrasound = "is_ultrasound";

	/** Set is_ultrasound	  */
	public void setis_ultrasound (boolean is_ultrasound);

	/** Get is_ultrasound	  */
	public boolean is_ultrasound();

    /** Column name is_xray */
    public static final String COLUMNNAME_is_xray = "is_xray";

	/** Set is_xray	  */
	public void setis_xray (boolean is_xray);

	/** Get is_xray	  */
	public boolean is_xray();

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

    /** Column name not_available */
    public static final String COLUMNNAME_not_available = "not_available";

	/** Set not_available	  */
	public void setnot_available (boolean not_available);

	/** Get not_available	  */
	public boolean isnot_available();

    /** Column name paid */
    public static final String COLUMNNAME_paid = "paid";

	/** Set paid	  */
	public void setpaid (boolean paid);

	/** Get paid	  */
	public boolean ispaid();

    /** Column name pay_after */
    public static final String COLUMNNAME_pay_after = "pay_after";

	/** Set pay_after	  */
	public void setpay_after (boolean pay_after);

	/** Get pay_after	  */
	public boolean ispay_after();

    /** Column name percentage_done */
    public static final String COLUMNNAME_percentage_done = "percentage_done";

	/** Set percentage_done	  */
	public void setpercentage_done (BigDecimal percentage_done);

	/** Get percentage_done	  */
	public BigDecimal getpercentage_done();

    /** Column name positive */
    public static final String COLUMNNAME_positive = "positive";

	/** Set Positive	  */
	public void setpositive (boolean positive);

	/** Get Positive	  */
	public boolean ispositive();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name repeat */
    public static final String COLUMNNAME_repeat = "repeat";

	/** Set repeat	  */
	public void setrepeat (boolean repeat);

	/** Get repeat	  */
	public boolean isrepeat();

    /** Column name results */
    public static final String COLUMNNAME_results = "results";

	/** Set Results	  */
	public void setresults (String results);

	/** Get Results	  */
	public String getresults();

    /** Column name send_date */
    public static final String COLUMNNAME_send_date = "send_date";

	/** Set Send Date	  */
	public void setsend_date (Timestamp send_date);

	/** Get Send Date	  */
	public Timestamp getsend_date();

    /** Column name send_time */
    public static final String COLUMNNAME_send_time = "send_time";

	/** Set Send Time	  */
	public void setsend_time (Timestamp send_time);

	/** Get Send Time	  */
	public Timestamp getsend_time();

    /** Column name test */
    public static final String COLUMNNAME_test = "test";

	/** Set test	  */
	public void settest (String test);

	/** Get test	  */
	public String gettest();

    /** Column name theatre_done */
    public static final String COLUMNNAME_theatre_done = "theatre_done";

	/** Set theatre_done	  */
	public void settheatre_done (boolean theatre_done);

	/** Get theatre_done	  */
	public boolean istheatre_done();

    /** Column name ultrasound_done */
    public static final String COLUMNNAME_ultrasound_done = "ultrasound_done";

	/** Set ultrasound_done	  */
	public void setultrasound_done (boolean ultrasound_done);

	/** Get ultrasound_done	  */
	public boolean isultrasound_done();

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

    /** Column name ward */
    public static final String COLUMNNAME_ward = "ward";

	/** Set ward	  */
	public void setward (String ward);

	/** Get ward	  */
	public String getward();

    /** Column name xray_done */
    public static final String COLUMNNAME_xray_done = "xray_done";

	/** Set xray_done	  */
	public void setxray_done (boolean xray_done);

	/** Get xray_done	  */
	public boolean isxray_done();
}
