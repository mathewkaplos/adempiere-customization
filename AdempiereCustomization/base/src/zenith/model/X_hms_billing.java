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

/** Generated Model for hms_billing
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_billing extends PO implements I_hms_billing, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181017L;

    /** Standard Constructor */
    public X_hms_billing (Properties ctx, int hms_billing_ID, String trxName)
    {
      super (ctx, hms_billing_ID, trxName);
      /** if (hms_billing_ID == 0)
        {
			setadmitted (false);
// N
			sethms_billing_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_billing (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_billing[")
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

	/** Set Bill Date.
		@param bill_date Bill Date	  */
	public void setbill_date (Timestamp bill_date)
	{
		set_Value (COLUMNNAME_bill_date, bill_date);
	}

	/** Get Bill Date.
		@return Bill Date	  */
	public Timestamp getbill_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_bill_date);
	}

	/** Set bill_group.
		@param bill_group bill_group	  */
	public void setbill_group (int bill_group)
	{
		set_Value (COLUMNNAME_bill_group, Integer.valueOf(bill_group));
	}

	/** Get bill_group.
		@return bill_group	  */
	public int getbill_group () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bill_group);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bill Mode.
		@param bill_mode Bill Mode	  */
	public void setbill_mode (int bill_mode)
	{
		set_Value (COLUMNNAME_bill_mode, Integer.valueOf(bill_mode));
	}

	/** Get Bill Mode.
		@return Bill Mode	  */
	public int getbill_mode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bill_mode);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EDIT.
		@param btn_edit EDIT	  */
	public void setbtn_edit (String btn_edit)
	{
		set_Value (COLUMNNAME_btn_edit, btn_edit);
	}

	/** Get EDIT.
		@return EDIT	  */
	public String getbtn_edit () 
	{
		return (String)get_Value(COLUMNNAME_btn_edit);
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_Value (COLUMNNAME_C_Order_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
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

	/** Set Days.
		@param days Days	  */
	public void setdays (int days)
	{
		set_Value (COLUMNNAME_days, Integer.valueOf(days));
	}

	/** Get Days.
		@return Days	  */
	public int getdays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_days);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Deduction Amount.
		@param DeductiontAmount 
		Amount in a defined currency
	  */
	public void setDeductiontAmount (BigDecimal DeductiontAmount)
	{
		set_Value (COLUMNNAME_DeductiontAmount, DeductiontAmount);
	}

	/** Get Deduction Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getDeductiontAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeductiontAmount);
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

	/** Set Discount %.
		@param Discount 
		Discount in percent
	  */
	public void setDiscount (BigDecimal Discount)
	{
		set_Value (COLUMNNAME_Discount, Discount);
	}

	/** Get Discount %.
		@return Discount in percent
	  */
	public BigDecimal getDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set discount_amount.
		@param discount_amount discount_amount	  */
	public void setdiscount_amount (BigDecimal discount_amount)
	{
		set_Value (COLUMNNAME_discount_amount, discount_amount);
	}

	/** Get discount_amount.
		@return discount_amount	  */
	public BigDecimal getdiscount_amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_discount_amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Dosage.
		@param dosage Dosage	  */
	public void setdosage (int dosage)
	{
		set_Value (COLUMNNAME_dosage, Integer.valueOf(dosage));
	}

	/** Get Dosage.
		@return Dosage	  */
	public int getdosage () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_dosage);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set dosage_description.
		@param dosage_description dosage_description	  */
	public void setdosage_description (String dosage_description)
	{
		set_Value (COLUMNNAME_dosage_description, dosage_description);
	}

	/** Get dosage_description.
		@return dosage_description	  */
	public String getdosage_description () 
	{
		return (String)get_Value(COLUMNNAME_dosage_description);
	}

	/** Set Frequency.
		@param freq Frequency	  */
	public void setfreq (int freq)
	{
		set_Value (COLUMNNAME_freq, Integer.valueOf(freq));
	}

	/** Get Frequency.
		@return Frequency	  */
	public int getfreq () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_freq);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_billing ID.
		@param hms_billing_ID hms_billing ID	  */
	public void sethms_billing_ID (int hms_billing_ID)
	{
		if (hms_billing_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_billing_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_billing_ID, Integer.valueOf(hms_billing_ID));
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

	public I_hms_payment gethms_payment() throws RuntimeException
    {
		return (I_hms_payment)MTable.get(getCtx(), I_hms_payment.Table_Name)
			.getPO(gethms_payment_ID(), get_TrxName());	}

	/** Set hms_payment ID.
		@param hms_payment_ID hms_payment ID	  */
	public void sethms_payment_ID (int hms_payment_ID)
	{
		if (hms_payment_ID < 1) 
			set_Value (COLUMNNAME_hms_payment_ID, null);
		else 
			set_Value (COLUMNNAME_hms_payment_ID, Integer.valueOf(hms_payment_ID));
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

	/** Set Invoiced.
		@param invoiced Invoiced	  */
	public void setinvoiced (boolean invoiced)
	{
		set_Value (COLUMNNAME_invoiced, Boolean.valueOf(invoiced));
	}

	/** Get Invoiced.
		@return Invoiced	  */
	public boolean isinvoiced () 
	{
		Object oo = get_Value(COLUMNNAME_invoiced);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_consultation.
		@param is_consultation is_consultation	  */
	public void setis_consultation (boolean is_consultation)
	{
		set_Value (COLUMNNAME_is_consultation, Boolean.valueOf(is_consultation));
	}

	/** Get is_consultation.
		@return is_consultation	  */
	public boolean is_consultation () 
	{
		Object oo = get_Value(COLUMNNAME_is_consultation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set is_discharge_drug.
		@param is_discharge_drug is_discharge_drug	  */
	public void setis_discharge_drug (boolean is_discharge_drug)
	{
		set_Value (COLUMNNAME_is_discharge_drug, Boolean.valueOf(is_discharge_drug));
	}

	/** Get is_discharge_drug.
		@return is_discharge_drug	  */
	public boolean is_discharge_drug () 
	{
		Object oo = get_Value(COLUMNNAME_is_discharge_drug);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_inpatient_service.
		@param is_inpatient_service is_inpatient_service	  */
	public void setis_inpatient_service (boolean is_inpatient_service)
	{
		set_Value (COLUMNNAME_is_inpatient_service, Boolean.valueOf(is_inpatient_service));
	}

	/** Get is_inpatient_service.
		@return is_inpatient_service	  */
	public boolean is_inpatient_service () 
	{
		Object oo = get_Value(COLUMNNAME_is_inpatient_service);
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

	/** Set is_othercharges.
		@param is_othercharges is_othercharges	  */
	public void setis_othercharges (boolean is_othercharges)
	{
		set_Value (COLUMNNAME_is_othercharges, Boolean.valueOf(is_othercharges));
	}

	/** Get is_othercharges.
		@return is_othercharges	  */
	public boolean is_othercharges () 
	{
		Object oo = get_Value(COLUMNNAME_is_othercharges);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_prescription.
		@param is_prescription is_prescription	  */
	public void setis_prescription (boolean is_prescription)
	{
		set_Value (COLUMNNAME_is_prescription, Boolean.valueOf(is_prescription));
	}

	/** Get is_prescription.
		@return is_prescription	  */
	public boolean is_prescription () 
	{
		Object oo = get_Value(COLUMNNAME_is_prescription);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set issue_drugs.
		@param issue_drugs issue_drugs	  */
	public void setissue_drugs (boolean issue_drugs)
	{
		set_Value (COLUMNNAME_issue_drugs, Boolean.valueOf(issue_drugs));
	}

	/** Get issue_drugs.
		@return issue_drugs	  */
	public boolean issue_drugs () 
	{
		Object oo = get_Value(COLUMNNAME_issue_drugs);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Issued.
		@param issued Issued	  */
	public void setissued (boolean issued)
	{
		set_Value (COLUMNNAME_issued, Boolean.valueOf(issued));
	}

	/** Get Issued.
		@return Issued	  */
	public boolean issued () 
	{
		Object oo = get_Value(COLUMNNAME_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set item_type.
		@param item_type item_type	  */
	public void setitem_type (String item_type)
	{
		set_Value (COLUMNNAME_item_type, item_type);
	}

	/** Get item_type.
		@return item_type	  */
	public String getitem_type () 
	{
		return (String)get_Value(COLUMNNAME_item_type);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_Value (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set pay.
		@param pay pay	  */
	public void setpay (boolean pay)
	{
		set_Value (COLUMNNAME_pay, Boolean.valueOf(pay));
	}

	/** Get pay.
		@return pay	  */
	public boolean ispay () 
	{
		Object oo = get_Value(COLUMNNAME_pay);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pay After.
		@param pay_after Pay After	  */
	public void setpay_after (boolean pay_after)
	{
		set_Value (COLUMNNAME_pay_after, Boolean.valueOf(pay_after));
	}

	/** Get Pay After.
		@return Pay After	  */
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

	/** Set Pay Date.
		@param pay_date Pay Date	  */
	public void setpay_date (Timestamp pay_date)
	{
		set_Value (COLUMNNAME_pay_date, pay_date);
	}

	/** Get Pay Date.
		@return Pay Date	  */
	public Timestamp getpay_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_pay_date);
	}

	/** Set Previous Balance.
		@param previous_balance Previous Balance	  */
	public void setprevious_balance (BigDecimal previous_balance)
	{
		set_Value (COLUMNNAME_previous_balance, previous_balance);
	}

	/** Get Previous Balance.
		@return Previous Balance	  */
	public BigDecimal getprevious_balance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_previous_balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Unit Price.
		@param PriceActual 
		Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual)
	{
		set_Value (COLUMNNAME_PriceActual, PriceActual);
	}

	/** Get Unit Price.
		@return Actual Price 
	  */
	public BigDecimal getPriceActual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set rounded_lineamt.
		@param rounded_lineamt rounded_lineamt	  */
	public void setrounded_lineamt (BigDecimal rounded_lineamt)
	{
		set_Value (COLUMNNAME_rounded_lineamt, rounded_lineamt);
	}

	/** Get rounded_lineamt.
		@return rounded_lineamt	  */
	public BigDecimal getrounded_lineamt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_rounded_lineamt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		set_Value (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Units Per Frequency.
		@param units_per_freq Units Per Frequency	  */
	public void setunits_per_freq (int units_per_freq)
	{
		set_Value (COLUMNNAME_units_per_freq, Integer.valueOf(units_per_freq));
	}

	/** Get Units Per Frequency.
		@return Units Per Frequency	  */
	public int getunits_per_freq () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_units_per_freq);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}