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
import org.compiere.util.KeyNamePair;

/** Generated Model for hms_ward_room1
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_ward_room1 extends PO implements I_hms_ward_room1, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170630L;

    /** Standard Constructor */
    public X_hms_ward_room1 (Properties ctx, int hms_ward_room1_ID, String trxName)
    {
      super (ctx, hms_ward_room1_ID, trxName);
      /** if (hms_ward_room1_ID == 0)
        {
			sethms_ward_room1_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_ward_room1 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_ward_room1[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set hms_ward_room1 ID.
		@param hms_ward_room1_ID hms_ward_room1 ID	  */
	public void sethms_ward_room1_ID (int hms_ward_room1_ID)
	{
		if (hms_ward_room1_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_ward_room1_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_ward_room1_ID, Integer.valueOf(hms_ward_room1_ID));
	}

	/** Get hms_ward_room1 ID.
		@return hms_ward_room1 ID	  */
	public int gethms_ward_room1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_room1_ID);
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
}