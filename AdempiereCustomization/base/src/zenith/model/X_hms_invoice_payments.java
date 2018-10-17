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

/** Generated Model for hms_invoice_payments
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_invoice_payments extends PO implements I_hms_invoice_payments, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180220L;

    /** Standard Constructor */
    public X_hms_invoice_payments (Properties ctx, int hms_invoice_payments_ID, String trxName)
    {
      super (ctx, hms_invoice_payments_ID, trxName);
      /** if (hms_invoice_payments_ID == 0)
        {
			sethms_invoice_payments_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_invoice_payments (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_invoice_payments[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Check No.
		@param CheckNo 
		Check Number
	  */
	public void setCheckNo (String CheckNo)
	{
		set_Value (COLUMNNAME_CheckNo, CheckNo);
	}

	/** Get Check No.
		@return Check Number
	  */
	public String getCheckNo () 
	{
		return (String)get_Value(COLUMNNAME_CheckNo);
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set EFT Check No.
		@param EftCheckNo 
		Electronic Funds Transfer Check No
	  */
	public void setEftCheckNo (String EftCheckNo)
	{
		set_Value (COLUMNNAME_EftCheckNo, EftCheckNo);
	}

	/** Get EFT Check No.
		@return Electronic Funds Transfer Check No
	  */
	public String getEftCheckNo () 
	{
		return (String)get_Value(COLUMNNAME_EftCheckNo);
	}

	public I_hms_invoice gethms_invoice() throws RuntimeException
    {
		return (I_hms_invoice)MTable.get(getCtx(), I_hms_invoice.Table_Name)
			.getPO(gethms_invoice_ID(), get_TrxName());	}

	/** Set hms_invoice ID.
		@param hms_invoice_ID hms_invoice ID	  */
	public void sethms_invoice_ID (int hms_invoice_ID)
	{
		if (hms_invoice_ID < 1) 
			set_Value (COLUMNNAME_hms_invoice_ID, null);
		else 
			set_Value (COLUMNNAME_hms_invoice_ID, Integer.valueOf(hms_invoice_ID));
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

	/** Set hms_invoice_payments ID.
		@param hms_invoice_payments_ID hms_invoice_payments ID	  */
	public void sethms_invoice_payments_ID (int hms_invoice_payments_ID)
	{
		if (hms_invoice_payments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_invoice_payments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_invoice_payments_ID, Integer.valueOf(hms_invoice_payments_ID));
	}

	/** Get hms_invoice_payments ID.
		@return hms_invoice_payments ID	  */
	public int gethms_invoice_payments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_invoice_payments_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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
}