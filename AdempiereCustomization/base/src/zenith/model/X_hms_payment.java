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

/** Generated Model for hms_payment
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_payment extends PO implements I_hms_payment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180702L;

    /** Standard Constructor */
    public X_hms_payment (Properties ctx, int hms_payment_ID, String trxName)
    {
      super (ctx, hms_payment_ID, trxName);
      /** if (hms_payment_ID == 0)
        {
			sethms_payment_ID (0);
			setTenderType (null);
        } */
    }

    /** Load Constructor */
    public X_hms_payment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_payment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount To Pay.
		@param amt_to_pay_direct_sale 
		Amount To Pay Direct Sale
	  */
	public void setamt_to_pay_direct_sale (BigDecimal amt_to_pay_direct_sale)
	{
		set_Value (COLUMNNAME_amt_to_pay_direct_sale, amt_to_pay_direct_sale);
	}

	/** Get Amount To Pay.
		@return Amount To Pay Direct Sale
	  */
	public BigDecimal getamt_to_pay_direct_sale () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_amt_to_pay_direct_sale);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set amt_topay.
		@param amt_topay amt_topay	  */
	public void setamt_topay (BigDecimal amt_topay)
	{
		throw new IllegalArgumentException ("amt_topay is virtual column");	}

	/** Get amt_topay.
		@return amt_topay	  */
	public BigDecimal getamt_topay () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_amt_topay);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set amt_topay_rounded.
		@param amt_topay_rounded amt_topay_rounded	  */
	public void setamt_topay_rounded (BigDecimal amt_topay_rounded)
	{
		throw new IllegalArgumentException ("amt_topay_rounded is virtual column");	}

	/** Get amt_topay_rounded.
		@return amt_topay_rounded	  */
	public BigDecimal getamt_topay_rounded () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_amt_topay_rounded);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set ADD DIECT SALE ITEM.
		@param btn_add_direct_sale_item ADD DIECT SALE ITEM	  */
	public void setbtn_add_direct_sale_item (String btn_add_direct_sale_item)
	{
		set_Value (COLUMNNAME_btn_add_direct_sale_item, btn_add_direct_sale_item);
	}

	/** Get ADD DIECT SALE ITEM.
		@return ADD DIECT SALE ITEM	  */
	public String getbtn_add_direct_sale_item () 
	{
		return (String)get_Value(COLUMNNAME_btn_add_direct_sale_item);
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

	/** Set btn_complete_othercharges.
		@param btn_complete_othercharges btn_complete_othercharges	  */
	public void setbtn_complete_othercharges (boolean btn_complete_othercharges)
	{
		set_Value (COLUMNNAME_btn_complete_othercharges, Boolean.valueOf(btn_complete_othercharges));
	}

	/** Get btn_complete_othercharges.
		@return btn_complete_othercharges	  */
	public boolean isbtn_complete_othercharges () 
	{
		Object oo = get_Value(COLUMNNAME_btn_complete_othercharges);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set COMPLETE PREPAYMENT.
		@param btn_complete_prepayment COMPLETE PREPAYMENT	  */
	public void setbtn_complete_prepayment (String btn_complete_prepayment)
	{
		set_Value (COLUMNNAME_btn_complete_prepayment, btn_complete_prepayment);
	}

	/** Get COMPLETE PREPAYMENT.
		@return COMPLETE PREPAYMENT	  */
	public String getbtn_complete_prepayment () 
	{
		return (String)get_Value(COLUMNNAME_btn_complete_prepayment);
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

	/** Set PRINT PREPAYMENT.
		@param btn_print_prepayment PRINT PREPAYMENT	  */
	public void setbtn_print_prepayment (String btn_print_prepayment)
	{
		set_Value (COLUMNNAME_btn_print_prepayment, btn_print_prepayment);
	}

	/** Get PRINT PREPAYMENT.
		@return PRINT PREPAYMENT	  */
	public String getbtn_print_prepayment () 
	{
		return (String)get_Value(COLUMNNAME_btn_print_prepayment);
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

	/** Set btn_unreverse.
		@param btn_unreverse btn_unreverse	  */
	public void setbtn_unreverse (String btn_unreverse)
	{
		set_Value (COLUMNNAME_btn_unreverse, btn_unreverse);
	}

	/** Get btn_unreverse.
		@return btn_unreverse	  */
	public String getbtn_unreverse () 
	{
		return (String)get_Value(COLUMNNAME_btn_unreverse);
	}

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

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_Value (COLUMNNAME_C_Payment_ID, null);
		else 
			set_Value (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Amount.
		@param cashAmt 
		Amount being paid
	  */
	public void setcashAmt (BigDecimal cashAmt)
	{
		set_Value (COLUMNNAME_cashAmt, cashAmt);
	}

	/** Get Cash Amount.
		@return Amount being paid
	  */
	public BigDecimal getcashAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_cashAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set deductiontamount.
		@param deductiontamount deductiontamount	  */
	public void setdeductiontamount (BigDecimal deductiontamount)
	{
		set_Value (COLUMNNAME_deductiontamount, deductiontamount);
	}

	/** Get deductiontamount.
		@return deductiontamount	  */
	public BigDecimal getdeductiontamount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_deductiontamount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Discount Amount.
		@param DiscountAmt 
		Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		set_Value (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set disp_orderline.
		@param disp_orderline disp_orderline	  */
	public void setdisp_orderline (boolean disp_orderline)
	{
		set_Value (COLUMNNAME_disp_orderline, Boolean.valueOf(disp_orderline));
	}

	/** Get disp_orderline.
		@return disp_orderline	  */
	public boolean isdisp_orderline () 
	{
		Object oo = get_Value(COLUMNNAME_disp_orderline);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set hms_payment ID.
		@param hms_payment_ID hms_payment ID	  */
	public void sethms_payment_ID (int hms_payment_ID)
	{
		if (hms_payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_payment_ID, Integer.valueOf(hms_payment_ID));
	}

	/** Get hms_payment ID.
		@return hms_payment ID	  */
	public int gethms_payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_payment_ID);
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

	/** Set Is Direct Sale.
		@param is_direct_sale Is Direct Sale	  */
	public void setis_direct_sale (boolean is_direct_sale)
	{
		set_Value (COLUMNNAME_is_direct_sale, Boolean.valueOf(is_direct_sale));
	}

	/** Get Is Direct Sale.
		@return Is Direct Sale	  */
	public boolean is_direct_sale () 
	{
		Object oo = get_Value(COLUMNNAME_is_direct_sale);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_invoice.
		@param is_invoice is_invoice	  */
	public void setis_invoice (boolean is_invoice)
	{
		set_Value (COLUMNNAME_is_invoice, Boolean.valueOf(is_invoice));
	}

	/** Get is_invoice.
		@return is_invoice	  */
	public boolean is_invoice () 
	{
		Object oo = get_Value(COLUMNNAME_is_invoice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Prepayment.
		@param IsPrepayment 
		The Payment/Receipt is a Prepayment
	  */
	public void setIsPrepayment (boolean IsPrepayment)
	{
		set_Value (COLUMNNAME_IsPrepayment, Boolean.valueOf(IsPrepayment));
	}

	/** Get Prepayment.
		@return The Payment/Receipt is a Prepayment
	  */
	public boolean isPrepayment () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrepayment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set MPesa Transaction Code.
		@param mpesa_transaction_code MPesa Transaction Code	  */
	public void setmpesa_transaction_code (String mpesa_transaction_code)
	{
		set_Value (COLUMNNAME_mpesa_transaction_code, mpesa_transaction_code);
	}

	/** Get MPesa Transaction Code.
		@return MPesa Transaction Code	  */
	public String getmpesa_transaction_code () 
	{
		return (String)get_Value(COLUMNNAME_mpesa_transaction_code);
	}

	/** Set MPesa Amount.
		@param mpesaAmt 
		Amount being paid
	  */
	public void setmpesaAmt (BigDecimal mpesaAmt)
	{
		set_Value (COLUMNNAME_mpesaAmt, mpesaAmt);
	}

	/** Get MPesa Amount.
		@return Amount being paid
	  */
	public BigDecimal getmpesaAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_mpesaAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Parent Payment ID.
		@param parent_payment_id 
		Parent payment in which Prepayment is used.
	  */
	public void setparent_payment_id (int parent_payment_id)
	{
		set_Value (COLUMNNAME_parent_payment_id, Integer.valueOf(parent_payment_id));
	}

	/** Get Parent Payment ID.
		@return Parent payment in which Prepayment is used.
	  */
	public int getparent_payment_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_parent_payment_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reverse Payment.
		@param payment_reversed Reverse Payment	  */
	public void setpayment_reversed (boolean payment_reversed)
	{
		set_Value (COLUMNNAME_payment_reversed, Boolean.valueOf(payment_reversed));
	}

	/** Get Reverse Payment.
		@return Reverse Payment	  */
	public boolean ispayment_reversed () 
	{
		Object oo = get_Value(COLUMNNAME_payment_reversed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set prepaid_amount.
		@param prepaid_amount prepaid_amount	  */
	public void setprepaid_amount (BigDecimal prepaid_amount)
	{
		set_Value (COLUMNNAME_prepaid_amount, prepaid_amount);
	}

	/** Get prepaid_amount.
		@return prepaid_amount	  */
	public BigDecimal getprepaid_amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_prepaid_amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prepayment Amount.
		@param prepayment_amt Prepayment Amount	  */
	public void setprepayment_amt (BigDecimal prepayment_amt)
	{
		throw new IllegalArgumentException ("prepayment_amt is virtual column");	}

	/** Get Prepayment Amount.
		@return Prepayment Amount	  */
	public BigDecimal getprepayment_amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_prepayment_amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prepayment Used.
		@param prepayment_used Prepayment Used	  */
	public void setprepayment_used (boolean prepayment_used)
	{
		set_Value (COLUMNNAME_prepayment_used, Boolean.valueOf(prepayment_used));
	}

	/** Get Prepayment Used.
		@return Prepayment Used	  */
	public boolean isprepayment_used () 
	{
		Object oo = get_Value(COLUMNNAME_prepayment_used);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set REFRESH.
		@param refresh REFRESH	  */
	public void setrefresh (String refresh)
	{
		set_Value (COLUMNNAME_refresh, refresh);
	}

	/** Get REFRESH.
		@return REFRESH	  */
	public String getrefresh () 
	{
		return (String)get_Value(COLUMNNAME_refresh);
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

	/** Set round_off.
		@param round_off round_off	  */
	public void setround_off (boolean round_off)
	{
		set_Value (COLUMNNAME_round_off, Boolean.valueOf(round_off));
	}

	/** Get round_off.
		@return round_off	  */
	public boolean isround_off () 
	{
		Object oo = get_Value(COLUMNNAME_round_off);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set RoundedPayAmt.
		@param RoundedPayAmt 
		 Rounded Amount being paid
	  */
	public void setRoundedPayAmt (BigDecimal RoundedPayAmt)
	{
		set_ValueNoCheck (COLUMNNAME_RoundedPayAmt, RoundedPayAmt);
	}

	/** Get RoundedPayAmt.
		@return  Rounded Amount being paid
	  */
	public BigDecimal getRoundedPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RoundedPayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set rounding_level.
		@param rounding_level rounding_level	  */
	public void setrounding_level (String rounding_level)
	{
		set_Value (COLUMNNAME_rounding_level, rounding_level);
	}

	/** Get rounding_level.
		@return rounding_level	  */
	public String getrounding_level () 
	{
		return (String)get_Value(COLUMNNAME_rounding_level);
	}

	/** Set tender_amt.
		@param tender_amt tender_amt	  */
	public void settender_amt (BigDecimal tender_amt)
	{
		set_Value (COLUMNNAME_tender_amt, tender_amt);
	}

	/** Get tender_amt.
		@return tender_amt	  */
	public BigDecimal gettender_amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_tender_amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** TenderType AD_Reference_ID=214 */
	public static final int TENDERTYPE_AD_Reference_ID=214;
	/** Credit Card = C */
	public static final String TENDERTYPE_CreditCard = "C";
	/** Check = K */
	public static final String TENDERTYPE_Check = "K";
	/** Direct Deposit = A */
	public static final String TENDERTYPE_DirectDeposit = "A";
	/** Direct Debit = D */
	public static final String TENDERTYPE_DirectDebit = "D";
	/** Account = T */
	public static final String TENDERTYPE_Account = "T";
	/** Cash = X */
	public static final String TENDERTYPE_Cash = "X";
	/** M-Pesa = M */
	public static final String TENDERTYPE_M_Pesa = "M";
	/** Mixed = I */
	public static final String TENDERTYPE_Mixed = "I";
	/** E.F.T = E */
	public static final String TENDERTYPE_EFT = "E";
	/** Set Tender type.
		@param TenderType 
		Method of Payment
	  */
	public void setTenderType (String TenderType)
	{

		set_Value (COLUMNNAME_TenderType, TenderType);
	}

	/** Get Tender type.
		@return Method of Payment
	  */
	public String getTenderType () 
	{
		return (String)get_Value(COLUMNNAME_TenderType);
	}

	/** Set total_bill.
		@param total_bill total_bill	  */
	public void settotal_bill (BigDecimal total_bill)
	{
		set_Value (COLUMNNAME_total_bill, total_bill);
	}

	/** Get total_bill.
		@return total_bill	  */
	public BigDecimal gettotal_bill () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_total_bill);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Use.
		@param use Use	  */
	public void setuse (boolean use)
	{
		set_Value (COLUMNNAME_use, Boolean.valueOf(use));
	}

	/** Get Use.
		@return Use	  */
	public boolean isuse () 
	{
		Object oo = get_Value(COLUMNNAME_use);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Write-off Amount.
		@param WriteOffAmt 
		Amount to write-off
	  */
	public void setWriteOffAmt (BigDecimal WriteOffAmt)
	{
		set_Value (COLUMNNAME_WriteOffAmt, WriteOffAmt);
	}

	/** Get Write-off Amount.
		@return Amount to write-off
	  */
	public BigDecimal getWriteOffAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WriteOffAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}