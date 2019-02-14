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

/** Generated Model for hms_diagnosis_grouping
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_diagnosis_grouping extends PO implements I_hms_diagnosis_grouping, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190213L;

    /** Standard Constructor */
    public X_hms_diagnosis_grouping (Properties ctx, int hms_diagnosis_grouping_ID, String trxName)
    {
      super (ctx, hms_diagnosis_grouping_ID, trxName);
      /** if (hms_diagnosis_grouping_ID == 0)
        {
			sethms_diagnosis_grouping_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_diagnosis_grouping (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_diagnosis_grouping[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set hms_diagnosis_grouping ID.
		@param hms_diagnosis_grouping_ID hms_diagnosis_grouping ID	  */
	public void sethms_diagnosis_grouping_ID (int hms_diagnosis_grouping_ID)
	{
		if (hms_diagnosis_grouping_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_diagnosis_grouping_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_diagnosis_grouping_ID, Integer.valueOf(hms_diagnosis_grouping_ID));
	}

	/** Get hms_diagnosis_grouping ID.
		@return hms_diagnosis_grouping ID	  */
	public int gethms_diagnosis_grouping_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosis_grouping_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
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
}