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

/** Generated Model for hms_insuance_payment
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_insuance_payment extends PO implements I_hms_insuance_payment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170906L;

    /** Standard Constructor */
    public X_hms_insuance_payment (Properties ctx, int hms_insuance_payment_ID, String trxName)
    {
      super (ctx, hms_insuance_payment_ID, trxName);
      /** if (hms_insuance_payment_ID == 0)
        {
			sethms_insuance_payment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_insuance_payment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_insuance_payment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account Name.
		@param A_Name 
		Name on Credit Card or Account holder
	  */
	public void setA_Name (String A_Name)
	{
		set_Value (COLUMNNAME_A_Name, A_Name);
	}

	/** Get Account Name.
		@return Name on Credit Card or Account holder
	  */
	public String getA_Name () 
	{
		return (String)get_Value(COLUMNNAME_A_Name);
	}

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		throw new IllegalArgumentException ("Balance is virtual column");	}

	/** Get Balance.
		@return Balance	  */
	public BigDecimal getBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set btn_dn.
		@param btn_dn btn_dn	  */
	public void setbtn_dn (String btn_dn)
	{
		set_Value (COLUMNNAME_btn_dn, btn_dn);
	}

	/** Get btn_dn.
		@return btn_dn	  */
	public String getbtn_dn () 
	{
		return (String)get_Value(COLUMNNAME_btn_dn);
	}

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException
    {
		return (org.compiere.model.I_C_Bank)MTable.get(getCtx(), org.compiere.model.I_C_Bank.Table_Name)
			.getPO(getC_Bank_ID(), get_TrxName());	}

	/** Set Bank.
		@param C_Bank_ID 
		Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID)
	{
		if (C_Bank_ID < 1) 
			set_Value (COLUMNNAME_C_Bank_ID, null);
		else 
			set_Value (COLUMNNAME_C_Bank_ID, Integer.valueOf(C_Bank_ID));
	}

	/** Get Bank.
		@return Bank
	  */
	public int getC_Bank_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Bank_ID);
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

	/** Set disp_insuarance.
		@param disp_insuarance disp_insuarance	  */
	public void setdisp_insuarance (boolean disp_insuarance)
	{
		set_Value (COLUMNNAME_disp_insuarance, Boolean.valueOf(disp_insuarance));
	}

	/** Get disp_insuarance.
		@return disp_insuarance	  */
	public boolean isdisp_insuarance () 
	{
		Object oo = get_Value(COLUMNNAME_disp_insuarance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set done.
		@param done done	  */
	public void setdone (boolean done)
	{
		set_Value (COLUMNNAME_done, Boolean.valueOf(done));
	}

	/** Get done.
		@return done	  */
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

	public I_hms_insco gethms_insco() throws RuntimeException
    {
		return (I_hms_insco)MTable.get(getCtx(), I_hms_insco.Table_Name)
			.getPO(gethms_insco_ID(), get_TrxName());	}

	/** Set hms_insco_ID.
		@param hms_insco_ID hms_insco_ID	  */
	public void sethms_insco_ID (int hms_insco_ID)
	{
		if (hms_insco_ID < 1) 
			set_Value (COLUMNNAME_hms_insco_ID, null);
		else 
			set_Value (COLUMNNAME_hms_insco_ID, Integer.valueOf(hms_insco_ID));
	}

	/** Get hms_insco_ID.
		@return hms_insco_ID	  */
	public int gethms_insco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_insuance_payment ID.
		@param hms_insuance_payment_ID hms_insuance_payment ID	  */
	public void sethms_insuance_payment_ID (int hms_insuance_payment_ID)
	{
		if (hms_insuance_payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_insuance_payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_insuance_payment_ID, Integer.valueOf(hms_insuance_payment_ID));
	}

	/** Get hms_insuance_payment ID.
		@return hms_insuance_payment ID	  */
	public int gethms_insuance_payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insuance_payment_ID);
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