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

/** Generated Model for hms_department
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_department extends PO implements I_hms_department, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181004L;

    /** Standard Constructor */
    public X_hms_department (Properties ctx, int hms_department_ID, String trxName)
    {
      super (ctx, hms_department_ID, trxName);
      /** if (hms_department_ID == 0)
        {
			setdepartment (null);
			sethms_department_ID (0);
			setM_Product_Category_ID (0);
			setshortcode (null);
        } */
    }

    /** Load Constructor */
    public X_hms_department (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_department[")
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

	/** Set Location.
		@param blocation Location	  */
	public void setblocation (String blocation)
	{
		set_Value (COLUMNNAME_blocation, blocation);
	}

	/** Get Location.
		@return Location	  */
	public String getblocation () 
	{
		return (String)get_Value(COLUMNNAME_blocation);
	}

	/** Set Department Name.
		@param department Department Name	  */
	public void setdepartment (String department)
	{
		set_Value (COLUMNNAME_department, department);
	}

	/** Get Department Name.
		@return Department Name	  */
	public String getdepartment () 
	{
		return (String)get_Value(COLUMNNAME_department);
	}

	public I_C_ValidCombination gethead_of_department() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(gethead_of_department_id(), get_TrxName());	}

	/** Set Head of Department.
		@param head_of_department_id Head of Department	  */
	public void sethead_of_department_id (int head_of_department_id)
	{
		set_Value (COLUMNNAME_head_of_department_id, Integer.valueOf(head_of_department_id));
	}

	/** Get Head of Department.
		@return Head of Department	  */
	public int gethead_of_department_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_head_of_department_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Department.
		@param hms_department_ID Department	  */
	public void sethms_department_ID (int hms_department_ID)
	{
		if (hms_department_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_department_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_department_ID, Integer.valueOf(hms_department_ID));
	}

	/** Get Department.
		@return Department	  */
	public int gethms_department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_department_ID);
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

	/** Set Short Code.
		@param shortcode Short Code	  */
	public void setshortcode (String shortcode)
	{
		set_Value (COLUMNNAME_shortcode, shortcode);
	}

	/** Get Short Code.
		@return Short Code	  */
	public String getshortcode () 
	{
		return (String)get_Value(COLUMNNAME_shortcode);
	}

	/** Set Assistant Head of Department.
		@param sub_head_of_department_id Assistant Head of Department	  */
	public void setsub_head_of_department_id (int sub_head_of_department_id)
	{
		set_Value (COLUMNNAME_sub_head_of_department_id, Integer.valueOf(sub_head_of_department_id));
	}

	/** Get Assistant Head of Department.
		@return Assistant Head of Department	  */
	public int getsub_head_of_department_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_sub_head_of_department_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set user1.
		@param user1 user1	  */
	public void setuser1 (int user1)
	{
		set_Value (COLUMNNAME_user1, Integer.valueOf(user1));
	}

	/** Get user1.
		@return user1	  */
	public int getuser1 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_user1);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set user2.
		@param user2 user2	  */
	public void setuser2 (int user2)
	{
		set_Value (COLUMNNAME_user2, Integer.valueOf(user2));
	}

	/** Get user2.
		@return user2	  */
	public int getuser2 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_user2);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}