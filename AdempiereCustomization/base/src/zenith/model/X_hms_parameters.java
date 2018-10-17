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

/** Generated Model for hms_parameters
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_parameters extends PO implements I_hms_parameters, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171106L;

    /** Standard Constructor */
    public X_hms_parameters (Properties ctx, int hms_parameters_ID, String trxName)
    {
      super (ctx, hms_parameters_ID, trxName);
      /** if (hms_parameters_ID == 0)
        {
			sethms_parameters_ID (0);
			sethms_specimens_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_parameters (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_parameters[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set disp_range.
		@param disp_range disp_range	  */
	public void setdisp_range (boolean disp_range)
	{
		set_Value (COLUMNNAME_disp_range, Boolean.valueOf(disp_range));
	}

	/** Get disp_range.
		@return disp_range	  */
	public boolean isdisp_range () 
	{
		Object oo = get_Value(COLUMNNAME_disp_range);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set has_power.
		@param has_power has_power	  */
	public void sethas_power (boolean has_power)
	{
		set_Value (COLUMNNAME_has_power, Boolean.valueOf(has_power));
	}

	/** Get has_power.
		@return has_power	  */
	public boolean ishas_power () 
	{
		Object oo = get_Value(COLUMNNAME_has_power);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set has_range.
		@param has_range has_range	  */
	public void sethas_range (boolean has_range)
	{
		set_Value (COLUMNNAME_has_range, Boolean.valueOf(has_range));
	}

	/** Get has_range.
		@return has_range	  */
	public boolean ishas_range () 
	{
		Object oo = get_Value(COLUMNNAME_has_range);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Parameter.
		@param hms_parameters_ID 
		Lab Test Parameter
	  */
	public void sethms_parameters_ID (int hms_parameters_ID)
	{
		if (hms_parameters_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_parameters_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_parameters_ID, Integer.valueOf(hms_parameters_ID));
	}

	/** Get Parameter.
		@return Lab Test Parameter
	  */
	public int gethms_parameters_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_parameters_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_specimens gethms_specimens() throws RuntimeException
    {
		return (I_hms_specimens)MTable.get(getCtx(), I_hms_specimens.Table_Name)
			.getPO(gethms_specimens_ID(), get_TrxName());	}

	/** Set hms_specimens ID.
		@param hms_specimens_ID hms_specimens ID	  */
	public void sethms_specimens_ID (int hms_specimens_ID)
	{
		if (hms_specimens_ID < 1) 
			set_Value (COLUMNNAME_hms_specimens_ID, null);
		else 
			set_Value (COLUMNNAME_hms_specimens_ID, Integer.valueOf(hms_specimens_ID));
	}

	/** Get hms_specimens ID.
		@return hms_specimens ID	  */
	public int gethms_specimens_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_specimens_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set is_age_based.
		@param is_age_based is_age_based	  */
	public void setis_age_based (boolean is_age_based)
	{
		set_Value (COLUMNNAME_is_age_based, Boolean.valueOf(is_age_based));
	}

	/** Get is_age_based.
		@return is_age_based	  */
	public boolean is_age_based () 
	{
		Object oo = get_Value(COLUMNNAME_is_age_based);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is_gender_based.
		@param is_gender_based is_gender_based	  */
	public void setis_gender_based (boolean is_gender_based)
	{
		set_Value (COLUMNNAME_is_gender_based, Boolean.valueOf(is_gender_based));
	}

	/** Get is_gender_based.
		@return is_gender_based	  */
	public boolean is_gender_based () 
	{
		Object oo = get_Value(COLUMNNAME_is_gender_based);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set max_value.
		@param max_value max_value	  */
	public void setmax_value (BigDecimal max_value)
	{
		set_Value (COLUMNNAME_max_value, max_value);
	}

	/** Get max_value.
		@return max_value	  */
	public BigDecimal getmax_value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_max_value);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set min_value.
		@param min_value min_value	  */
	public void setmin_value (BigDecimal min_value)
	{
		set_Value (COLUMNNAME_min_value, min_value);
	}

	/** Get min_value.
		@return min_value	  */
	public BigDecimal getmin_value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_min_value);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Units.
		@param units 
		Parameter Units
	  */
	public void setunits (String units)
	{
		set_Value (COLUMNNAME_units, units);
	}

	/** Get Units.
		@return Parameter Units
	  */
	public String getunits () 
	{
		return (String)get_Value(COLUMNNAME_units);
	}
}