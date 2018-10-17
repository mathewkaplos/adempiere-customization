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

/** Generated Model for hms_insuance_payline
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_insuance_payline extends PO implements I_hms_insuance_payline, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170906L;

    /** Standard Constructor */
    public X_hms_insuance_payline (Properties ctx, int hms_insuance_payline_ID, String trxName)
    {
      super (ctx, hms_insuance_payline_ID, trxName);
      /** if (hms_insuance_payline_ID == 0)
        {
			sethms_insuance_payline_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_insuance_payline (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_insuance_payline[")
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

	/** Set hms_insuance_payline ID.
		@param hms_insuance_payline_ID hms_insuance_payline ID	  */
	public void sethms_insuance_payline_ID (int hms_insuance_payline_ID)
	{
		if (hms_insuance_payline_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_insuance_payline_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_insuance_payline_ID, Integer.valueOf(hms_insuance_payline_ID));
	}

	/** Get hms_insuance_payline ID.
		@return hms_insuance_payline ID	  */
	public int gethms_insuance_payline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insuance_payline_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoice Document No.
		@param InvoiceDocumentNo 
		Document Number of the Invoice
	  */
	public void setInvoiceDocumentNo (String InvoiceDocumentNo)
	{
		set_Value (COLUMNNAME_InvoiceDocumentNo, InvoiceDocumentNo);
	}

	/** Get Invoice Document No.
		@return Document Number of the Invoice
	  */
	public String getInvoiceDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_InvoiceDocumentNo);
	}

	/** Set Open Amount.
		@param OpenAmt 
		Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt)
	{
		throw new IllegalArgumentException ("OpenAmt is virtual column");	}

	/** Get Open Amount.
		@return Open item amount
	  */
	public BigDecimal getOpenAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OpenAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Selected.
		@param selected Selected	  */
	public void setselected (boolean selected)
	{
		set_Value (COLUMNNAME_selected, Boolean.valueOf(selected));
	}

	/** Get Selected.
		@return Selected	  */
	public boolean isselected () 
	{
		Object oo = get_Value(COLUMNNAME_selected);
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
}