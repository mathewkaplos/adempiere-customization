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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for hms_ward_order
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_ward_order extends PO implements I_hms_ward_order, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181031L;

    /** Standard Constructor */
    public X_hms_ward_order (Properties ctx, int hms_ward_order_ID, String trxName)
    {
      super (ctx, hms_ward_order_ID, trxName);
      /** if (hms_ward_order_ID == 0)
        {
			setcompleted (false);
// N
			setDateRequired (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setdisp01 (false);
// N
			sethms_ward_order_ID (0);
			setTimeRequired (new Timestamp( System.currentTimeMillis() ));
// @Date@
        } */
    }

    /** Load Constructor */
    public X_hms_ward_order (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_ward_order[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ADD ITEM.
		@param btn_add_item ADD ITEM	  */
	public void setbtn_add_item (String btn_add_item)
	{
		set_Value (COLUMNNAME_btn_add_item, btn_add_item);
	}

	/** Get ADD ITEM.
		@return ADD ITEM	  */
	public String getbtn_add_item () 
	{
		return (String)get_Value(COLUMNNAME_btn_add_item);
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

	/** Set Date Required.
		@param DateRequired 
		Date when required
	  */
	public void setDateRequired (Timestamp DateRequired)
	{
		set_Value (COLUMNNAME_DateRequired, DateRequired);
	}

	/** Get Date Required.
		@return Date when required
	  */
	public Timestamp getDateRequired () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateRequired);
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

	/** Set hms_ward_order ID.
		@param hms_ward_order_ID hms_ward_order ID	  */
	public void sethms_ward_order_ID (int hms_ward_order_ID)
	{
		if (hms_ward_order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_ward_order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_ward_order_ID, Integer.valueOf(hms_ward_order_ID));
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
	}

	/** Set Time Required.
		@param TimeRequired Time Required	  */
	public void setTimeRequired (Timestamp TimeRequired)
	{
		set_Value (COLUMNNAME_TimeRequired, TimeRequired);
	}

	/** Get Time Required.
		@return Time Required	  */
	public Timestamp getTimeRequired () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TimeRequired);
	}
}