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

/** Generated Interface for hms_payment
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_payment 
{

    /** TableName=hms_payment */
    public static final String Table_Name = "hms_payment";

    /** AD_Table_ID=1000065 */
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

    /** Column name amt_to_pay_direct_sale */
    public static final String COLUMNNAME_amt_to_pay_direct_sale = "amt_to_pay_direct_sale";

	/** Set Amount To Pay.
	  * Amount To Pay Direct Sale
	  */
	public void setamt_to_pay_direct_sale (BigDecimal amt_to_pay_direct_sale);

	/** Get Amount To Pay.
	  * Amount To Pay Direct Sale
	  */
	public BigDecimal getamt_to_pay_direct_sale();

    /** Column name amt_topay */
    public static final String COLUMNNAME_amt_topay = "amt_topay";

	/** Set amt_topay	  */
	public void setamt_topay (BigDecimal amt_topay);

	/** Get amt_topay	  */
	public BigDecimal getamt_topay();

    /** Column name amt_topay_rounded */
    public static final String COLUMNNAME_amt_topay_rounded = "amt_topay_rounded";

	/** Set amt_topay_rounded	  */
	public void setamt_topay_rounded (BigDecimal amt_topay_rounded);

	/** Get amt_topay_rounded	  */
	public BigDecimal getamt_topay_rounded();

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

    /** Column name btn_add_direct_sale_item */
    public static final String COLUMNNAME_btn_add_direct_sale_item = "btn_add_direct_sale_item";

	/** Set ADD DIECT SALE ITEM	  */
	public void setbtn_add_direct_sale_item (String btn_add_direct_sale_item);

	/** Get ADD DIECT SALE ITEM	  */
	public String getbtn_add_direct_sale_item();

    /** Column name btn_complete */
    public static final String COLUMNNAME_btn_complete = "btn_complete";

	/** Set COMPLETE	  */
	public void setbtn_complete (String btn_complete);

	/** Get COMPLETE	  */
	public String getbtn_complete();

    /** Column name btn_complete_othercharges */
    public static final String COLUMNNAME_btn_complete_othercharges = "btn_complete_othercharges";

	/** Set btn_complete_othercharges	  */
	public void setbtn_complete_othercharges (boolean btn_complete_othercharges);

	/** Get btn_complete_othercharges	  */
	public boolean isbtn_complete_othercharges();

    /** Column name btn_complete_prepayment */
    public static final String COLUMNNAME_btn_complete_prepayment = "btn_complete_prepayment";

	/** Set COMPLETE PREPAYMENT	  */
	public void setbtn_complete_prepayment (String btn_complete_prepayment);

	/** Get COMPLETE PREPAYMENT	  */
	public String getbtn_complete_prepayment();

    /** Column name btn_print */
    public static final String COLUMNNAME_btn_print = "btn_print";

	/** Set PRINT	  */
	public void setbtn_print (String btn_print);

	/** Get PRINT	  */
	public String getbtn_print();

    /** Column name btn_print_prepayment */
    public static final String COLUMNNAME_btn_print_prepayment = "btn_print_prepayment";

	/** Set PRINT PREPAYMENT	  */
	public void setbtn_print_prepayment (String btn_print_prepayment);

	/** Get PRINT PREPAYMENT	  */
	public String getbtn_print_prepayment();

    /** Column name btn_reverse */
    public static final String COLUMNNAME_btn_reverse = "btn_reverse";

	/** Set REVERSE	  */
	public void setbtn_reverse (String btn_reverse);

	/** Get REVERSE	  */
	public String getbtn_reverse();

    /** Column name btn_unreverse */
    public static final String COLUMNNAME_btn_unreverse = "btn_unreverse";

	/** Set btn_unreverse	  */
	public void setbtn_unreverse (String btn_unreverse);

	/** Get btn_unreverse	  */
	public String getbtn_unreverse();

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

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name cashAmt */
    public static final String COLUMNNAME_cashAmt = "cashAmt";

	/** Set Cash Amount.
	  * Amount being paid
	  */
	public void setcashAmt (BigDecimal cashAmt);

	/** Get Cash Amount.
	  * Amount being paid
	  */
	public BigDecimal getcashAmt();

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

    /** Column name deductiontamount */
    public static final String COLUMNNAME_deductiontamount = "deductiontamount";

	/** Set deductiontamount	  */
	public void setdeductiontamount (BigDecimal deductiontamount);

	/** Get deductiontamount	  */
	public BigDecimal getdeductiontamount();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DiscountAmt */
    public static final String COLUMNNAME_DiscountAmt = "DiscountAmt";

	/** Set Discount Amount.
	  * Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt);

	/** Get Discount Amount.
	  * Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt();

    /** Column name disp_orderline */
    public static final String COLUMNNAME_disp_orderline = "disp_orderline";

	/** Set disp_orderline	  */
	public void setdisp_orderline (boolean disp_orderline);

	/** Get disp_orderline	  */
	public boolean isdisp_orderline();

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

    /** Column name done */
    public static final String COLUMNNAME_done = "done";

	/** Set Done	  */
	public void setdone (boolean done);

	/** Get Done	  */
	public boolean isdone();

    /** Column name hms_payment_ID */
    public static final String COLUMNNAME_hms_payment_ID = "hms_payment_ID";

	/** Set hms_payment ID	  */
	public void sethms_payment_ID (int hms_payment_ID);

	/** Get hms_payment ID	  */
	public int gethms_payment_ID();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name is_direct_sale */
    public static final String COLUMNNAME_is_direct_sale = "is_direct_sale";

	/** Set Is Direct Sale	  */
	public void setis_direct_sale (boolean is_direct_sale);

	/** Get Is Direct Sale	  */
	public boolean is_direct_sale();

    /** Column name is_invoice */
    public static final String COLUMNNAME_is_invoice = "is_invoice";

	/** Set is_invoice	  */
	public void setis_invoice (boolean is_invoice);

	/** Get is_invoice	  */
	public boolean is_invoice();

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

    /** Column name IsPrepayment */
    public static final String COLUMNNAME_IsPrepayment = "IsPrepayment";

	/** Set Prepayment.
	  * The Payment/Receipt is a Prepayment
	  */
	public void setIsPrepayment (boolean IsPrepayment);

	/** Get Prepayment.
	  * The Payment/Receipt is a Prepayment
	  */
	public boolean isPrepayment();

    /** Column name mpesa_transaction_code */
    public static final String COLUMNNAME_mpesa_transaction_code = "mpesa_transaction_code";

	/** Set MPesa Transaction Code	  */
	public void setmpesa_transaction_code (String mpesa_transaction_code);

	/** Get MPesa Transaction Code	  */
	public String getmpesa_transaction_code();

    /** Column name mpesaAmt */
    public static final String COLUMNNAME_mpesaAmt = "mpesaAmt";

	/** Set MPesa Amount.
	  * Amount being paid
	  */
	public void setmpesaAmt (BigDecimal mpesaAmt);

	/** Get MPesa Amount.
	  * Amount being paid
	  */
	public BigDecimal getmpesaAmt();

    /** Column name parent_payment_id */
    public static final String COLUMNNAME_parent_payment_id = "parent_payment_id";

	/** Set Parent Payment ID.
	  * Parent payment in which Prepayment is used.
	  */
	public void setparent_payment_id (int parent_payment_id);

	/** Get Parent Payment ID.
	  * Parent payment in which Prepayment is used.
	  */
	public int getparent_payment_id();

    /** Column name PayAmt */
    public static final String COLUMNNAME_PayAmt = "PayAmt";

	/** Set Payment amount.
	  * Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt);

	/** Get Payment amount.
	  * Amount being paid
	  */
	public BigDecimal getPayAmt();

    /** Column name payment_reversed */
    public static final String COLUMNNAME_payment_reversed = "payment_reversed";

	/** Set Reverse Payment	  */
	public void setpayment_reversed (boolean payment_reversed);

	/** Get Reverse Payment	  */
	public boolean ispayment_reversed();

    /** Column name prepaid_amount */
    public static final String COLUMNNAME_prepaid_amount = "prepaid_amount";

	/** Set prepaid_amount	  */
	public void setprepaid_amount (BigDecimal prepaid_amount);

	/** Get prepaid_amount	  */
	public BigDecimal getprepaid_amount();

    /** Column name prepayment_amt */
    public static final String COLUMNNAME_prepayment_amt = "prepayment_amt";

	/** Set Prepayment Amount	  */
	public void setprepayment_amt (BigDecimal prepayment_amt);

	/** Get Prepayment Amount	  */
	public BigDecimal getprepayment_amt();

    /** Column name prepayment_used */
    public static final String COLUMNNAME_prepayment_used = "prepayment_used";

	/** Set Prepayment Used	  */
	public void setprepayment_used (boolean prepayment_used);

	/** Get Prepayment Used	  */
	public boolean isprepayment_used();

    /** Column name refresh */
    public static final String COLUMNNAME_refresh = "refresh";

	/** Set REFRESH	  */
	public void setrefresh (String refresh);

	/** Get REFRESH	  */
	public String getrefresh();

    /** Column name reversal_reason */
    public static final String COLUMNNAME_reversal_reason = "reversal_reason";

	/** Set Reversal Reason	  */
	public void setreversal_reason (String reversal_reason);

	/** Get Reversal Reason	  */
	public String getreversal_reason();

    /** Column name round_off */
    public static final String COLUMNNAME_round_off = "round_off";

	/** Set round_off	  */
	public void setround_off (boolean round_off);

	/** Get round_off	  */
	public boolean isround_off();

    /** Column name RoundedPayAmt */
    public static final String COLUMNNAME_RoundedPayAmt = "RoundedPayAmt";

	/** Set RoundedPayAmt.
	  *  Rounded Amount being paid
	  */
	public void setRoundedPayAmt (BigDecimal RoundedPayAmt);

	/** Get RoundedPayAmt.
	  *  Rounded Amount being paid
	  */
	public BigDecimal getRoundedPayAmt();

    /** Column name rounding_level */
    public static final String COLUMNNAME_rounding_level = "rounding_level";

	/** Set rounding_level	  */
	public void setrounding_level (String rounding_level);

	/** Get rounding_level	  */
	public String getrounding_level();

    /** Column name tender_amt */
    public static final String COLUMNNAME_tender_amt = "tender_amt";

	/** Set tender_amt	  */
	public void settender_amt (BigDecimal tender_amt);

	/** Get tender_amt	  */
	public BigDecimal gettender_amt();

    /** Column name TenderType */
    public static final String COLUMNNAME_TenderType = "TenderType";

	/** Set Tender type.
	  * Method of Payment
	  */
	public void setTenderType (String TenderType);

	/** Get Tender type.
	  * Method of Payment
	  */
	public String getTenderType();

    /** Column name total_bill */
    public static final String COLUMNNAME_total_bill = "total_bill";

	/** Set total_bill	  */
	public void settotal_bill (BigDecimal total_bill);

	/** Get total_bill	  */
	public BigDecimal gettotal_bill();

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

    /** Column name use */
    public static final String COLUMNNAME_use = "use";

	/** Set Use	  */
	public void setuse (boolean use);

	/** Get Use	  */
	public boolean isuse();

    /** Column name WriteOffAmt */
    public static final String COLUMNNAME_WriteOffAmt = "WriteOffAmt";

	/** Set Write-off Amount.
	  * Amount to write-off
	  */
	public void setWriteOffAmt (BigDecimal WriteOffAmt);

	/** Get Write-off Amount.
	  * Amount to write-off
	  */
	public BigDecimal getWriteOffAmt();
}
