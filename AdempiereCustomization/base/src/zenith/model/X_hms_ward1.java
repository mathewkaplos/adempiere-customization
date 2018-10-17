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
import org.compiere.util.KeyNamePair;

/** Generated Model for hms_ward1
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_ward1 extends PO implements I_hms_ward1, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170630L;

    /** Standard Constructor */
    public X_hms_ward1 (Properties ctx, int hms_ward1_ID, String trxName)
    {
      super (ctx, hms_ward1_ID, trxName);
      /** if (hms_ward1_ID == 0)
        {
			sethms_ward1_ID (0);
			setward_gender (null);
        } */
    }

    /** Load Constructor */
    public X_hms_ward1 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_ward1[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set add_beds.
		@param add_beds add_beds	  */
	public void setadd_beds (String add_beds)
	{
		set_Value (COLUMNNAME_add_beds, add_beds);
	}

	/** Get add_beds.
		@return add_beds	  */
	public String getadd_beds () 
	{
		return (String)get_Value(COLUMNNAME_add_beds);
	}

	/** Set disp_beds.
		@param disp_beds disp_beds	  */
	public void setdisp_beds (boolean disp_beds)
	{
		set_Value (COLUMNNAME_disp_beds, Boolean.valueOf(disp_beds));
	}

	/** Get disp_beds.
		@return disp_beds	  */
	public boolean isdisp_beds () 
	{
		Object oo = get_Value(COLUMNNAME_disp_beds);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_hms_ward_block1 gethms_ward_block1() throws RuntimeException
    {
		return (I_hms_ward_block1)MTable.get(getCtx(), I_hms_ward_block1.Table_Name)
			.getPO(gethms_ward_block1_ID(), get_TrxName());	}

	/** Set hms_ward_block1 ID.
		@param hms_ward_block1_ID hms_ward_block1 ID	  */
	public void sethms_ward_block1_ID (int hms_ward_block1_ID)
	{
		if (hms_ward_block1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward_block1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward_block1_ID, Integer.valueOf(hms_ward_block1_ID));
	}

	/** Get hms_ward_block1 ID.
		@return hms_ward_block1 ID	  */
	public int gethms_ward_block1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_block1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_ward_floor1 gethms_ward_floor1() throws RuntimeException
    {
		return (I_hms_ward_floor1)MTable.get(getCtx(), I_hms_ward_floor1.Table_Name)
			.getPO(gethms_ward_floor1_ID(), get_TrxName());	}

	/** Set hms_ward_floor1 ID.
		@param hms_ward_floor1_ID hms_ward_floor1 ID	  */
	public void sethms_ward_floor1_ID (int hms_ward_floor1_ID)
	{
		if (hms_ward_floor1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward_floor1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward_floor1_ID, Integer.valueOf(hms_ward_floor1_ID));
	}

	/** Get hms_ward_floor1 ID.
		@return hms_ward_floor1 ID	  */
	public int gethms_ward_floor1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_floor1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_ward_wing1 gethms_ward_wing1() throws RuntimeException
    {
		return (I_hms_ward_wing1)MTable.get(getCtx(), I_hms_ward_wing1.Table_Name)
			.getPO(gethms_ward_wing1_ID(), get_TrxName());	}

	/** Set hms_ward_wing1 ID.
		@param hms_ward_wing1_ID hms_ward_wing1 ID	  */
	public void sethms_ward_wing1_ID (int hms_ward_wing1_ID)
	{
		if (hms_ward_wing1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward_wing1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward_wing1_ID, Integer.valueOf(hms_ward_wing1_ID));
	}

	/** Get hms_ward_wing1 ID.
		@return hms_ward_wing1 ID	  */
	public int gethms_ward_wing1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_wing1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_ward1 ID.
		@param hms_ward1_ID hms_ward1 ID	  */
	public void sethms_ward1_ID (int hms_ward1_ID)
	{
		if (hms_ward1_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_ward1_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_ward1_ID, Integer.valueOf(hms_ward1_ID));
	}

	/** Get hms_ward1 ID.
		@return hms_ward1 ID	  */
	public int gethms_ward1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward1_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set number_of_beds.
		@param number_of_beds number_of_beds	  */
	public void setnumber_of_beds (BigDecimal number_of_beds)
	{
		set_Value (COLUMNNAME_number_of_beds, number_of_beds);
	}

	/** Get number_of_beds.
		@return number_of_beds	  */
	public BigDecimal getnumber_of_beds () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_number_of_beds);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set remove_beds.
		@param remove_beds remove_beds	  */
	public void setremove_beds (boolean remove_beds)
	{
		set_Value (COLUMNNAME_remove_beds, Boolean.valueOf(remove_beds));
	}

	/** Get remove_beds.
		@return remove_beds	  */
	public boolean isremove_beds () 
	{
		Object oo = get_Value(COLUMNNAME_remove_beds);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set transfer_beds.
		@param transfer_beds transfer_beds	  */
	public void settransfer_beds (boolean transfer_beds)
	{
		set_Value (COLUMNNAME_transfer_beds, Boolean.valueOf(transfer_beds));
	}

	/** Get transfer_beds.
		@return transfer_beds	  */
	public boolean istransfer_beds () 
	{
		Object oo = get_Value(COLUMNNAME_transfer_beds);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** ward_gender AD_Reference_ID=53612 */
	public static final int WARD_GENDER_AD_Reference_ID=53612;
	/** Female = F */
	public static final String WARD_GENDER_Female = "F";
	/** Male = M */
	public static final String WARD_GENDER_Male = "M";
	/** Set ward_gender.
		@param ward_gender ward_gender	  */
	public void setward_gender (String ward_gender)
	{

		set_Value (COLUMNNAME_ward_gender, ward_gender);
	}

	/** Get ward_gender.
		@return ward_gender	  */
	public String getward_gender () 
	{
		return (String)get_Value(COLUMNNAME_ward_gender);
	}

	/** Set ward_prefix.
		@param ward_prefix ward_prefix	  */
	public void setward_prefix (String ward_prefix)
	{
		set_Value (COLUMNNAME_ward_prefix, ward_prefix);
	}

	/** Get ward_prefix.
		@return ward_prefix	  */
	public String getward_prefix () 
	{
		return (String)get_Value(COLUMNNAME_ward_prefix);
	}
}