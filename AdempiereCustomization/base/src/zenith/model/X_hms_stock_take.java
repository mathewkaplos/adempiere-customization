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

/** Generated Model for hms_stock_take
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_stock_take extends PO implements I_hms_stock_take, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171010L;

    /** Standard Constructor */
    public X_hms_stock_take (Properties ctx, int hms_stock_take_ID, String trxName)
    {
      super (ctx, hms_stock_take_ID, trxName);
      /** if (hms_stock_take_ID == 0)
        {
			sethms_stock_take_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_stock_take (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_stock_take[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set expiry_date.
		@param expiry_date expiry_date	  */
	public void setexpiry_date (Timestamp expiry_date)
	{
		set_Value (COLUMNNAME_expiry_date, expiry_date);
	}

	/** Get expiry_date.
		@return expiry_date	  */
	public Timestamp getexpiry_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_expiry_date);
	}

	public I_hms_stock_take_header gethms_stock_take_header() throws RuntimeException
    {
		return (I_hms_stock_take_header)MTable.get(getCtx(), I_hms_stock_take_header.Table_Name)
			.getPO(gethms_stock_take_header_ID(), get_TrxName());	}

	/** Set hms_stock_take_header ID.
		@param hms_stock_take_header_ID hms_stock_take_header ID	  */
	public void sethms_stock_take_header_ID (int hms_stock_take_header_ID)
	{
		if (hms_stock_take_header_ID < 1) 
			set_Value (COLUMNNAME_hms_stock_take_header_ID, null);
		else 
			set_Value (COLUMNNAME_hms_stock_take_header_ID, Integer.valueOf(hms_stock_take_header_ID));
	}

	/** Get hms_stock_take_header ID.
		@return hms_stock_take_header ID	  */
	public int gethms_stock_take_header_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_stock_take_header_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_stock_take ID.
		@param hms_stock_take_ID hms_stock_take ID	  */
	public void sethms_stock_take_ID (int hms_stock_take_ID)
	{
		if (hms_stock_take_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_stock_take_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_stock_take_ID, Integer.valueOf(hms_stock_take_ID));
	}

	/** Get hms_stock_take ID.
		@return hms_stock_take ID	  */
	public int gethms_stock_take_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_stock_take_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set in_stock.
		@param in_stock in_stock	  */
	public void setin_stock (boolean in_stock)
	{
		set_Value (COLUMNNAME_in_stock, Boolean.valueOf(in_stock));
	}

	/** Get in_stock.
		@return in_stock	  */
	public boolean isin_stock () 
	{
		Object oo = get_Value(COLUMNNAME_in_stock);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Physical Quantity.
		@param physical_qty Physical Quantity	  */
	public void setphysical_qty (BigDecimal physical_qty)
	{
		set_Value (COLUMNNAME_physical_qty, physical_qty);
	}

	/** Get Physical Quantity.
		@return Physical Quantity	  */
	public BigDecimal getphysical_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_physical_qty);
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