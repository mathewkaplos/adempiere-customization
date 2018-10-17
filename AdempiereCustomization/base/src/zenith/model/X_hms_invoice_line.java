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

/** Generated Model for hms_invoice_line
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_invoice_line extends PO implements I_hms_invoice_line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180227L;

    /** Standard Constructor */
    public X_hms_invoice_line (Properties ctx, int hms_invoice_line_ID, String trxName)
    {
      super (ctx, hms_invoice_line_ID, trxName);
      /** if (hms_invoice_line_ID == 0)
        {
			sethms_invoice_line_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_invoice_line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_invoice_line[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bill Group.
		@param bill_group Bill Group	  */
	public void setbill_group (int bill_group)
	{
		set_Value (COLUMNNAME_bill_group, Integer.valueOf(bill_group));
	}

	/** Get Bill Group.
		@return Bill Group	  */
	public int getbill_group () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bill_group);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_hms_billing gethms_billing() throws RuntimeException
    {
		return (I_hms_billing)MTable.get(getCtx(), I_hms_billing.Table_Name)
			.getPO(gethms_billing_ID(), get_TrxName());	}

	/** Set hms_billing ID.
		@param hms_billing_ID hms_billing ID	  */
	public void sethms_billing_ID (int hms_billing_ID)
	{
		if (hms_billing_ID < 1) 
			set_Value (COLUMNNAME_hms_billing_ID, null);
		else 
			set_Value (COLUMNNAME_hms_billing_ID, Integer.valueOf(hms_billing_ID));
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

	/** Set hms_invoice_line ID.
		@param hms_invoice_line_ID hms_invoice_line ID	  */
	public void sethms_invoice_line_ID (int hms_invoice_line_ID)
	{
		if (hms_invoice_line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_invoice_line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_invoice_line_ID, Integer.valueOf(hms_invoice_line_ID));
	}

	/** Get hms_invoice_line ID.
		@return hms_invoice_line ID	  */
	public int gethms_invoice_line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_invoice_line_ID);
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
}