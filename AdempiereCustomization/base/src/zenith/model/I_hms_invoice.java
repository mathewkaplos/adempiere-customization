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

/** Generated Interface for hms_invoice
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_invoice 
{

    /** TableName=hms_invoice */
    public static final String Table_Name = "hms_invoice";

    /** AD_Table_ID=1000067 */
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

	/** Set Department.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Department.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Balance */
    public static final String COLUMNNAME_Balance = "Balance";

	/** Set Balance	  */
	public void setBalance (BigDecimal Balance);

	/** Get Balance	  */
	public BigDecimal getBalance();

    /** Column name bill_amount */
    public static final String COLUMNNAME_bill_amount = "bill_amount";

	/** Set Bill Amount	  */
	public void setbill_amount (BigDecimal bill_amount);

	/** Get Bill Amount	  */
	public BigDecimal getbill_amount();

    /** Column name btn_complete */
    public static final String COLUMNNAME_btn_complete = "btn_complete";

	/** Set COMPLETE	  */
	public void setbtn_complete (String btn_complete);

	/** Get COMPLETE	  */
	public String getbtn_complete();

    /** Column name btn_print */
    public static final String COLUMNNAME_btn_print = "btn_print";

	/** Set PRINT	  */
	public void setbtn_print (String btn_print);

	/** Get PRINT	  */
	public String getbtn_print();

    /** Column name btn_print_outpatient_invoice */
    public static final String COLUMNNAME_btn_print_outpatient_invoice = "btn_print_outpatient_invoice";

	/** Set PRINT INVOICE 2.
	  * Insurance Outpatient Invoice
	  */
	public void setbtn_print_outpatient_invoice (String btn_print_outpatient_invoice);

	/** Get PRINT INVOICE 2.
	  * Insurance Outpatient Invoice
	  */
	public String getbtn_print_outpatient_invoice();

    /** Column name btn_reconcile_invoice */
    public static final String COLUMNNAME_btn_reconcile_invoice = "btn_reconcile_invoice";

	/** Set RECONCILE INVOICE	  */
	public void setbtn_reconcile_invoice (String btn_reconcile_invoice);

	/** Get RECONCILE INVOICE	  */
	public String getbtn_reconcile_invoice();

    /** Column name btn_reverse */
    public static final String COLUMNNAME_btn_reverse = "btn_reverse";

	/** Set REVERSE	  */
	public void setbtn_reverse (String btn_reverse);

	/** Get REVERSE	  */
	public String getbtn_reverse();

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

    /** Column name completed */
    public static final String COLUMNNAME_completed = "completed";

	/** Set Completed	  */
	public void setcompleted (boolean completed);

	/** Get Completed	  */
	public boolean iscompleted();

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

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name date_completed */
    public static final String COLUMNNAME_date_completed = "date_completed";

	/** Set Date Completed	  */
	public void setdate_completed (Timestamp date_completed);

	/** Get Date Completed	  */
	public Timestamp getdate_completed();

    /** Column name DateInvoiced */
    public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";

	/** Set Date Invoiced.
	  * Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced);

	/** Get Date Invoiced.
	  * Date printed on Invoice
	  */
	public Timestamp getDateInvoiced();

    /** Column name disp01 */
    public static final String COLUMNNAME_disp01 = "disp01";

	/** Set disp01	  */
	public void setdisp01 (boolean disp01);

	/** Get disp01	  */
	public boolean isdisp01();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name GrandTotal */
    public static final String COLUMNNAME_GrandTotal = "GrandTotal";

	/** Set Grand Total.
	  * Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal);

	/** Get Grand Total.
	  * Total amount of document
	  */
	public BigDecimal getGrandTotal();

    /** Column name hms_insco_ID */
    public static final String COLUMNNAME_hms_insco_ID = "hms_insco_ID";

	/** Set Insurance Company	  */
	public void sethms_insco_ID (int hms_insco_ID);

	/** Get Insurance Company	  */
	public int gethms_insco_ID();

	public I_hms_insco gethms_insco() throws RuntimeException;

    /** Column name hms_insuredco_ID */
    public static final String COLUMNNAME_hms_insuredco_ID = "hms_insuredco_ID";

	/** Set Insured Company.
	  * Insured Company
	  */
	public void sethms_insuredco_ID (int hms_insuredco_ID);

	/** Get Insured Company.
	  * Insured Company
	  */
	public int gethms_insuredco_ID();

	public I_hms_insuredco gethms_insuredco() throws RuntimeException;

    /** Column name hms_invoice_ID */
    public static final String COLUMNNAME_hms_invoice_ID = "hms_invoice_ID";

	/** Set hms_invoice ID	  */
	public void sethms_invoice_ID (int hms_invoice_ID);

	/** Get hms_invoice ID	  */
	public int gethms_invoice_ID();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name id_no */
    public static final String COLUMNNAME_id_no = "id_no";

	/** Set ID Number	  */
	public void setid_no (String id_no);

	/** Get ID Number	  */
	public String getid_no();

    /** Column name inpatient_no */
    public static final String COLUMNNAME_inpatient_no = "inpatient_no";

	/** Set Inpatient Number	  */
	public void setinpatient_no (String inpatient_no);

	/** Get Inpatient Number	  */
	public String getinpatient_no();

    /** Column name invoice_type */
    public static final String COLUMNNAME_invoice_type = "invoice_type";

	/** Set Invoice Type	  */
	public void setinvoice_type (String invoice_type);

	/** Get Invoice Type	  */
	public String getinvoice_type();

    /** Column name is_nhif */
    public static final String COLUMNNAME_is_nhif = "is_nhif";

	/** Set Is NHIF	  */
	public void setis_nhif (boolean is_nhif);

	/** Get Is NHIF	  */
	public boolean is_nhif();

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

    /** Column name member_number */
    public static final String COLUMNNAME_member_number = "member_number";

	/** Set Member/Policy Number	  */
	public void setmember_number (String member_number);

	/** Get Member/Policy Number	  */
	public String getmember_number();

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

    /** Column name nhif_no */
    public static final String COLUMNNAME_nhif_no = "nhif_no";

	/** Set NHIF No	  */
	public void setnhif_no (String nhif_no);

	/** Get NHIF No	  */
	public String getnhif_no();

    /** Column name PaidAmt */
    public static final String COLUMNNAME_PaidAmt = "PaidAmt";

	/** Set Paid Amount	  */
	public void setPaidAmt (BigDecimal PaidAmt);

	/** Get Paid Amount	  */
	public BigDecimal getPaidAmt();

    /** Column name patient_no */
    public static final String COLUMNNAME_patient_no = "patient_no";

	/** Set Patient Number	  */
	public void setpatient_no (String patient_no);

	/** Get Patient Number	  */
	public String getpatient_no();

    /** Column name prepay_amt */
    public static final String COLUMNNAME_prepay_amt = "prepay_amt";

	/** Set Prepayment Amount	  */
	public void setprepay_amt (BigDecimal prepay_amt);

	/** Get Prepayment Amount	  */
	public BigDecimal getprepay_amt();

    /** Column name rebate_amount */
    public static final String COLUMNNAME_rebate_amount = "rebate_amount";

	/** Set Rebate Amount	  */
	public void setrebate_amount (BigDecimal rebate_amount);

	/** Get Rebate Amount	  */
	public BigDecimal getrebate_amount();

    /** Column name reversal_reason */
    public static final String COLUMNNAME_reversal_reason = "reversal_reason";

	/** Set Reversal Reason	  */
	public void setreversal_reason (String reversal_reason);

	/** Get Reversal Reason	  */
	public String getreversal_reason();

    /** Column name reversed */
    public static final String COLUMNNAME_reversed = "reversed";

	/** Set Reversed	  */
	public void setreversed (boolean reversed);

	/** Get Reversed	  */
	public boolean isreversed();

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
}
