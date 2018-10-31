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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for hms_stock_take_header
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_stock_take_header extends PO implements I_hms_stock_take_header, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181031L;

    /** Standard Constructor */
    public X_hms_stock_take_header (Properties ctx, int hms_stock_take_header_ID, String trxName)
    {
      super (ctx, hms_stock_take_header_ID, trxName);
      /** if (hms_stock_take_header_ID == 0)
        {
			setbtn_create_item (null);
// N
			setcompleted (false);
// N
			setdisp01 (false);
// N
			sethms_stock_take_header_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_stock_take_header (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_stock_take_header[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set CREATE ITEM LINE.
		@param btn_create_item CREATE ITEM LINE	  */
	public void setbtn_create_item (String btn_create_item)
	{
		set_Value (COLUMNNAME_btn_create_item, btn_create_item);
	}

	/** Get CREATE ITEM LINE.
		@return CREATE ITEM LINE	  */
	public String getbtn_create_item () 
	{
		return (String)get_Value(COLUMNNAME_btn_create_item);
	}

	/** Set btn_createlines.
		@param btn_createlines btn_createlines	  */
	public void setbtn_createlines (String btn_createlines)
	{
		set_Value (COLUMNNAME_btn_createlines, btn_createlines);
	}

	/** Get btn_createlines.
		@return btn_createlines	  */
	public String getbtn_createlines () 
	{
		return (String)get_Value(COLUMNNAME_btn_createlines);
	}

	/** Set btn_update_stock.
		@param btn_update_stock btn_update_stock	  */
	public void setbtn_update_stock (String btn_update_stock)
	{
		set_Value (COLUMNNAME_btn_update_stock, btn_update_stock);
	}

	/** Get btn_update_stock.
		@return btn_update_stock	  */
	public String getbtn_update_stock () 
	{
		return (String)get_Value(COLUMNNAME_btn_update_stock);
	}

	/** Set Comment.
		@param comment Comment	  */
	public void setcomment (String comment)
	{
		set_Value (COLUMNNAME_comment, comment);
	}

	/** Get Comment.
		@return Comment	  */
	public String getcomment () 
	{
		return (String)get_Value(COLUMNNAME_comment);
	}

	/** Set Completed.
		@param completed Completed	  */
	public void setcompleted (boolean completed)
	{
		set_Value (COLUMNNAME_completed, Boolean.valueOf(completed));
	}

	/** Get Completed.
		@return Completed	  */
	public boolean iscompleted () 
	{
		Object oo = get_Value(COLUMNNAME_completed);
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

	/** Set hms_stock_take_header ID.
		@param hms_stock_take_header_ID hms_stock_take_header ID	  */
	public void sethms_stock_take_header_ID (int hms_stock_take_header_ID)
	{
		if (hms_stock_take_header_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_stock_take_header_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_stock_take_header_ID, Integer.valueOf(hms_stock_take_header_ID));
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

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}