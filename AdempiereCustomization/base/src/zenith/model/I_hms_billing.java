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

/** Generated Interface for hms_billing
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_billing 
{

    /** TableName=hms_billing */
    public static final String Table_Name = "hms_billing";

    /** AD_Table_ID=1000064 */
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

    /** Column name admitted */
    public static final String COLUMNNAME_admitted = "admitted";

	/** Set Admitted	  */
	public void setadmitted (boolean admitted);

	/** Get Admitted	  */
	public boolean isadmitted();

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

    /** Column name Balance */
    public static final String COLUMNNAME_Balance = "Balance";

	/** Set Balance	  */
	public void setBalance (BigDecimal Balance);

	/** Get Balance	  */
	public BigDecimal getBalance();

    /** Column name bill_date */
    public static final String COLUMNNAME_bill_date = "bill_date";

	/** Set Bill Date	  */
	public void setbill_date (Timestamp bill_date);

	/** Get Bill Date	  */
	public Timestamp getbill_date();

    /** Column name bill_group */
    public static final String COLUMNNAME_bill_group = "bill_group";

	/** Set bill_group	  */
	public void setbill_group (int bill_group);

	/** Get bill_group	  */
	public int getbill_group();

    /** Column name bill_mode */
    public static final String COLUMNNAME_bill_mode = "bill_mode";

	/** Set Bill Mode	  */
	public void setbill_mode (int bill_mode);

	/** Get Bill Mode	  */
	public int getbill_mode();

    /** Column name btn_edit */
    public static final String COLUMNNAME_btn_edit = "btn_edit";

	/** Set EDIT	  */
	public void setbtn_edit (String btn_edit);

	/** Get EDIT	  */
	public String getbtn_edit();

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

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

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

    /** Column name days */
    public static final String COLUMNNAME_days = "days";

	/** Set Days	  */
	public void setdays (int days);

	/** Get Days	  */
	public int getdays();

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

    /** Column name Discount */
    public static final String COLUMNNAME_Discount = "Discount";

	/** Set Discount %.
	  * Discount in percent
	  */
	public void setDiscount (BigDecimal Discount);

	/** Get Discount %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount();

    /** Column name discount_amount */
    public static final String COLUMNNAME_discount_amount = "discount_amount";

	/** Set discount_amount	  */
	public void setdiscount_amount (BigDecimal discount_amount);

	/** Get discount_amount	  */
	public BigDecimal getdiscount_amount();

    /** Column name dosage */
    public static final String COLUMNNAME_dosage = "dosage";

	/** Set Dosage	  */
	public void setdosage (int dosage);

	/** Get Dosage	  */
	public int getdosage();

    /** Column name dosage_description */
    public static final String COLUMNNAME_dosage_description = "dosage_description";

	/** Set dosage_description	  */
	public void setdosage_description (String dosage_description);

	/** Get dosage_description	  */
	public String getdosage_description();

    /** Column name freq */
    public static final String COLUMNNAME_freq = "freq";

	/** Set Frequency	  */
	public void setfreq (int freq);

	/** Get Frequency	  */
	public int getfreq();

    /** Column name hms_billing_ID */
    public static final String COLUMNNAME_hms_billing_ID = "hms_billing_ID";

	/** Set hms_billing ID	  */
	public void sethms_billing_ID (int hms_billing_ID);

	/** Get hms_billing ID	  */
	public int gethms_billing_ID();

    /** Column name hms_payment_ID */
    public static final String COLUMNNAME_hms_payment_ID = "hms_payment_ID";

	/** Set hms_payment ID	  */
	public void sethms_payment_ID (int hms_payment_ID);

	/** Get hms_payment ID	  */
	public int gethms_payment_ID();

	public I_hms_payment gethms_payment() throws RuntimeException;

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name invoiced */
    public static final String COLUMNNAME_invoiced = "invoiced";

	/** Set Invoiced	  */
	public void setinvoiced (boolean invoiced);

	/** Get Invoiced	  */
	public boolean isinvoiced();

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

    /** Column name is_consultation */
    public static final String COLUMNNAME_is_consultation = "is_consultation";

	/** Set is_consultation	  */
	public void setis_consultation (boolean is_consultation);

	/** Get is_consultation	  */
	public boolean is_consultation();

    /** Column name is_direct_sale */
    public static final String COLUMNNAME_is_direct_sale = "is_direct_sale";

	/** Set Is Direct Sale	  */
	public void setis_direct_sale (boolean is_direct_sale);

	/** Get Is Direct Sale	  */
	public boolean is_direct_sale();

    /** Column name is_discharge_drug */
    public static final String COLUMNNAME_is_discharge_drug = "is_discharge_drug";

	/** Set is_discharge_drug	  */
	public void setis_discharge_drug (boolean is_discharge_drug);

	/** Get is_discharge_drug	  */
	public boolean is_discharge_drug();

    /** Column name is_inpatient_service */
    public static final String COLUMNNAME_is_inpatient_service = "is_inpatient_service";

	/** Set is_inpatient_service	  */
	public void setis_inpatient_service (boolean is_inpatient_service);

	/** Get is_inpatient_service	  */
	public boolean is_inpatient_service();

    /** Column name is_lab */
    public static final String COLUMNNAME_is_lab = "is_lab";

	/** Set is_lab	  */
	public void setis_lab (boolean is_lab);

	/** Get is_lab	  */
	public boolean is_lab();

    /** Column name is_othercharges */
    public static final String COLUMNNAME_is_othercharges = "is_othercharges";

	/** Set is_othercharges	  */
	public void setis_othercharges (boolean is_othercharges);

	/** Get is_othercharges	  */
	public boolean is_othercharges();

    /** Column name is_prescription */
    public static final String COLUMNNAME_is_prescription = "is_prescription";

	/** Set is_prescription	  */
	public void setis_prescription (boolean is_prescription);

	/** Get is_prescription	  */
	public boolean is_prescription();

    /** Column name issued */
    public static final String COLUMNNAME_issued = "issued";

	/** Set Issued	  */
	public void setissued (boolean issued);

	/** Get Issued	  */
	public boolean issued();

    /** Column name issue_drugs */
    public static final String COLUMNNAME_issue_drugs = "issue_drugs";

	/** Set issue_drugs	  */
	public void setissue_drugs (boolean issue_drugs);

	/** Get issue_drugs	  */
	public boolean issue_drugs();

    /** Column name item_type */
    public static final String COLUMNNAME_item_type = "item_type";

	/** Set item_type	  */
	public void setitem_type (String item_type);

	/** Get item_type	  */
	public String getitem_type();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

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

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name paid */
    public static final String COLUMNNAME_paid = "paid";

	/** Set paid	  */
	public void setpaid (boolean paid);

	/** Get paid	  */
	public boolean ispaid();

    /** Column name PaidAmt */
    public static final String COLUMNNAME_PaidAmt = "PaidAmt";

	/** Set Paid Amount	  */
	public void setPaidAmt (BigDecimal PaidAmt);

	/** Get Paid Amount	  */
	public BigDecimal getPaidAmt();

    /** Column name pay */
    public static final String COLUMNNAME_pay = "pay";

	/** Set pay	  */
	public void setpay (boolean pay);

	/** Get pay	  */
	public boolean ispay();

    /** Column name pay_after */
    public static final String COLUMNNAME_pay_after = "pay_after";

	/** Set Pay After	  */
	public void setpay_after (boolean pay_after);

	/** Get Pay After	  */
	public boolean ispay_after();

    /** Column name pay_date */
    public static final String COLUMNNAME_pay_date = "pay_date";

	/** Set Pay Date	  */
	public void setpay_date (Timestamp pay_date);

	/** Get Pay Date	  */
	public Timestamp getpay_date();

    /** Column name previous_balance */
    public static final String COLUMNNAME_previous_balance = "previous_balance";

	/** Set previous_balance	  */
	public void setprevious_balance (BigDecimal previous_balance);

	/** Get previous_balance	  */
	public BigDecimal getprevious_balance();

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";

	/** Set Price.
	  * Price
	  */
	public void setPrice (BigDecimal Price);

	/** Get Price.
	  * Price
	  */
	public BigDecimal getPrice();

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name Remarks */
    public static final String COLUMNNAME_Remarks = "Remarks";

	/** Set Remarks.
	  * Remarks
	  */
	public void setRemarks (String Remarks);

	/** Get Remarks.
	  * Remarks
	  */
	public String getRemarks();

    /** Column name rounded_lineamt */
    public static final String COLUMNNAME_rounded_lineamt = "rounded_lineamt";

	/** Set rounded_lineamt	  */
	public void setrounded_lineamt (BigDecimal rounded_lineamt);

	/** Get rounded_lineamt	  */
	public BigDecimal getrounded_lineamt();

    /** Column name to_pharm */
    public static final String COLUMNNAME_to_pharm = "to_pharm";

	/** Set to_pharm	  */
	public void setto_pharm (boolean to_pharm);

	/** Get to_pharm	  */
	public boolean isto_pharm();

    /** Column name TotalAmt */
    public static final String COLUMNNAME_TotalAmt = "TotalAmt";

	/** Set Total Amount.
	  * Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt);

	/** Get Total Amount.
	  * Total Amount
	  */
	public BigDecimal getTotalAmt();

    /** Column name units_per_freq */
    public static final String COLUMNNAME_units_per_freq = "units_per_freq";

	/** Set Units Per Frequency	  */
	public void setunits_per_freq (int units_per_freq);

	/** Get Units Per Frequency	  */
	public int getunits_per_freq();

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
