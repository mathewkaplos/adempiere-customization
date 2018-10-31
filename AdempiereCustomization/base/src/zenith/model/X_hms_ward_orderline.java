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

/** Generated Model for hms_ward_orderline
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_ward_orderline extends PO implements I_hms_ward_orderline, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181030L;

    /** Standard Constructor */
    public X_hms_ward_orderline (Properties ctx, int hms_ward_orderline_ID, String trxName)
    {
      super (ctx, hms_ward_orderline_ID, trxName);
      /** if (hms_ward_orderline_ID == 0)
        {
			sethms_ward_orderline_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_ward_orderline (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_ward_orderline[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set hms_ward_order ID.
		@param hms_ward_order_ID hms_ward_order ID	  */
	public void sethms_ward_order_ID (int hms_ward_order_ID)
	{
		if (hms_ward_order_ID < 1) 
			set_Value (COLUMNNAME_hms_ward_order_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward_order_ID, Integer.valueOf(hms_ward_order_ID));
	}

	/** Get hms_ward_order ID.
		@return hms_ward_order ID	  */
	public int gethms_ward_order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_ward_orderline ID.
		@param hms_ward_orderline_ID hms_ward_orderline ID	  */
	public void sethms_ward_orderline_ID (int hms_ward_orderline_ID)
	{
		if (hms_ward_orderline_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_ward_orderline_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_ward_orderline_ID, Integer.valueOf(hms_ward_orderline_ID));
	}

	/** Get hms_ward_orderline ID.
		@return hms_ward_orderline ID	  */
	public int gethms_ward_orderline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_orderline_ID);
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