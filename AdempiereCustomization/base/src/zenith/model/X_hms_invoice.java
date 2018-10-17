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

/** Generated Model for hms_invoice
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_invoice extends PO implements I_hms_invoice, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180320L;

    /** Standard Constructor */
    public X_hms_invoice (Properties ctx, int hms_invoice_ID, String trxName)
    {
      super (ctx, hms_invoice_ID, trxName);
      /** if (hms_invoice_ID == 0)
        {
			sethms_invoice_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_invoice (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_invoice[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		set_Value (COLUMNNAME_Balance, Balance);
	}

	/** Get Balance.
		@return Balance	  */
	public BigDecimal getBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Bill Amount.
		@param bill_amount Bill Amount	  */
	public void setbill_amount (BigDecimal bill_amount)
	{
		set_Value (COLUMNNAME_bill_amount, bill_amount);
	}

	/** Get Bill Amount.
		@return Bill Amount	  */
	public BigDecimal getbill_amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_bill_amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set COMPLETE.
		@param btn_complete COMPLETE	  */
	public void setbtn_complete (String btn_complete)
	{
		set_Value (COLUMNNAME_btn_complete, btn_complete);
	}

	/** Get COMPLETE.
		@return COMPLETE	  */
	public String getbtn_complete () 
	{
		return (String)get_Value(COLUMNNAME_btn_complete);
	}

	/** Set PRINT.
		@param btn_print PRINT	  */
	public void setbtn_print (String btn_print)
	{
		set_Value (COLUMNNAME_btn_print, btn_print);
	}

	/** Get PRINT.
		@return PRINT	  */
	public String getbtn_print () 
	{
		return (String)get_Value(COLUMNNAME_btn_print);
	}

	/** Set PRINT INVOICE 2.
		@param btn_print_outpatient_invoice 
		Insurance Outpatient Invoice
	  */
	public void setbtn_print_outpatient_invoice (String btn_print_outpatient_invoice)
	{
		set_Value (COLUMNNAME_btn_print_outpatient_invoice, btn_print_outpatient_invoice);
	}

	/** Get PRINT INVOICE 2.
		@return Insurance Outpatient Invoice
	  */
	public String getbtn_print_outpatient_invoice () 
	{
		return (String)get_Value(COLUMNNAME_btn_print_outpatient_invoice);
	}

	/** Set RECONCILE INVOICE.
		@param btn_reconcile_invoice RECONCILE INVOICE	  */
	public void setbtn_reconcile_invoice (String btn_reconcile_invoice)
	{
		set_Value (COLUMNNAME_btn_reconcile_invoice, btn_reconcile_invoice);
	}

	/** Get RECONCILE INVOICE.
		@return RECONCILE INVOICE	  */
	public String getbtn_reconcile_invoice () 
	{
		return (String)get_Value(COLUMNNAME_btn_reconcile_invoice);
	}

	/** Set REVERSE.
		@param btn_reverse REVERSE	  */
	public void setbtn_reverse (String btn_reverse)
	{
		set_Value (COLUMNNAME_btn_reverse, btn_reverse);
	}

	/** Get REVERSE.
		@return REVERSE	  */
	public String getbtn_reverse () 
	{
		return (String)get_Value(COLUMNNAME_btn_reverse);
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

	/** Set Completed.
		@param completed Completed	  */
	public void setcompleted (boolean completed)
	{
		set_Value (COLUMNNAME_completed, Boolean.valueOf(completed));
	}

	/** Get Completed.
		@return Completed	  */
	public boolean iscompleted () 
	{
		Object oo = get_Value(COLUMNNAME_completed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Date Completed.
		@param date_completed Date Completed	  */
	public void setdate_completed (Timestamp date_completed)
	{
		set_Value (COLUMNNAME_date_completed, date_completed);
	}

	/** Get Date Completed.
		@return Date Completed	  */
	public Timestamp getdate_completed () 
	{
		return (Timestamp)get_Value(COLUMNNAME_date_completed);
	}

	/** Set Date Invoiced.
		@param DateInvoiced 
		Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced)
	{
		set_Value (COLUMNNAME_DateInvoiced, DateInvoiced);
	}

	/** Get Date Invoiced.
		@return Date printed on Invoice
	  */
	public Timestamp getDateInvoiced () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInvoiced);
	}

	/** Set disp01.
		@param disp01 disp01	  */
	public void setdisp01 (boolean disp01)
	{
		set_Value (COLUMNNAME_disp01, Boolean.valueOf(disp01));
	}

	/** Get disp01.
		@return disp01	  */
	public boolean isdisp01 () 
	{
		Object oo = get_Value(COLUMNNAME_disp01);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		set_Value (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
		@param hms_insuredco_ID 
		Insured Company
	  */
	public void sethms_insuredco_ID (int hms_insuredco_ID)
	{
		if (hms_insuredco_ID < 1) 
			set_Value (COLUMNNAME_hms_insuredco_ID, null);
		else 
			set_Value (COLUMNNAME_hms_insuredco_ID, Integer.valueOf(hms_insuredco_ID));
	}

	/** Get Insured Company.
		@return Insured Company
	  */
	public int gethms_insuredco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insuredco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_invoice ID.
		@param hms_invoice_ID hms_invoice ID	  */
	public void sethms_invoice_ID (int hms_invoice_ID)
	{
		if (hms_invoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_invoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_invoice_ID, Integer.valueOf(hms_invoice_ID));
	}

	/** Get hms_invoice ID.
		@return hms_invoice ID	  */
	public int gethms_invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_invoice_ID);
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

	/** Set ID Number.
		@param id_no ID Number	  */
	public void setid_no (String id_no)
	{
		set_Value (COLUMNNAME_id_no, id_no);
	}

	/** Get ID Number.
		@return ID Number	  */
	public String getid_no () 
	{
		return (String)get_Value(COLUMNNAME_id_no);
	}

	/** Set Inpatient Number.
		@param inpatient_no Inpatient Number	  */
	public void setinpatient_no (String inpatient_no)
	{
		set_Value (COLUMNNAME_inpatient_no, inpatient_no);
	}

	/** Get Inpatient Number.
		@return Inpatient Number	  */
	public String getinpatient_no () 
	{
		return (String)get_Value(COLUMNNAME_inpatient_no);
	}

	/** invoice_type AD_Reference_ID=1000034 */
	public static final int INVOICE_TYPE_AD_Reference_ID=1000034;
	/** Insurance Invoice = I */
	public static final String INVOICE_TYPE_InsuranceInvoice = "I";
	/** NHIF Invoice = N */
	public static final String INVOICE_TYPE_NHIFInvoice = "N";
	/** Patient Invoice = P */
	public static final String INVOICE_TYPE_PatientInvoice = "P";
	/** Set Invoice Type.
		@param invoice_type Invoice Type	  */
	public void setinvoice_type (String invoice_type)
	{

		set_Value (COLUMNNAME_invoice_type, invoice_type);
	}

	/** Get Invoice Type.
		@return Invoice Type	  */
	public String getinvoice_type () 
	{
		return (String)get_Value(COLUMNNAME_invoice_type);
	}

	/** Set Is NHIF.
		@param is_nhif Is NHIF	  */
	public void setis_nhif (boolean is_nhif)
	{
		set_Value (COLUMNNAME_is_nhif, Boolean.valueOf(is_nhif));
	}

	/** Get Is NHIF.
		@return Is NHIF	  */
	public boolean is_nhif () 
	{
		Object oo = get_Value(COLUMNNAME_is_nhif);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Member/Policy Number.
		@param member_number Member/Policy Number	  */
	public void setmember_number (String member_number)
	{
		set_Value (COLUMNNAME_member_number, member_number);
	}

	/** Get Member/Policy Number.
		@return Member/Policy Number	  */
	public String getmember_number () 
	{
		return (String)get_Value(COLUMNNAME_member_number);
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

	/** Set NHIF No.
		@param nhif_no NHIF No	  */
	public void setnhif_no (String nhif_no)
	{
		set_Value (COLUMNNAME_nhif_no, nhif_no);
	}

	/** Get NHIF No.
		@return NHIF No	  */
	public String getnhif_no () 
	{
		return (String)get_Value(COLUMNNAME_nhif_no);
	}

	/** Set Paid Amount.
		@param PaidAmt Paid Amount	  */
	public void setPaidAmt (BigDecimal PaidAmt)
	{
		set_Value (COLUMNNAME_PaidAmt, PaidAmt);
	}

	/** Get Paid Amount.
		@return Paid Amount	  */
	public BigDecimal getPaidAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PaidAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Patient Number.
		@param patient_no Patient Number	  */
	public void setpatient_no (String patient_no)
	{
		set_Value (COLUMNNAME_patient_no, patient_no);
	}

	/** Get Patient Number.
		@return Patient Number	  */
	public String getpatient_no () 
	{
		return (String)get_Value(COLUMNNAME_patient_no);
	}

	/** Set Prepayment Amount.
		@param prepay_amt Prepayment Amount	  */
	public void setprepay_amt (BigDecimal prepay_amt)
	{
		set_Value (COLUMNNAME_prepay_amt, prepay_amt);
	}

	/** Get Prepayment Amount.
		@return Prepayment Amount	  */
	public BigDecimal getprepay_amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_prepay_amt);
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

	/** Set Reversal Reason.
		@param reversal_reason Reversal Reason	  */
	public void setreversal_reason (String reversal_reason)
	{
		set_Value (COLUMNNAME_reversal_reason, reversal_reason);
	}

	/** Get Reversal Reason.
		@return Reversal Reason	  */
	public String getreversal_reason () 
	{
		return (String)get_Value(COLUMNNAME_reversal_reason);
	}

	/** Set Reversed.
		@param reversed Reversed	  */
	public void setreversed (boolean reversed)
	{
		set_Value (COLUMNNAME_reversed, Boolean.valueOf(reversed));
	}

	/** Get Reversed.
		@return Reversed	  */
	public boolean isreversed () 
	{
		Object oo = get_Value(COLUMNNAME_reversed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}