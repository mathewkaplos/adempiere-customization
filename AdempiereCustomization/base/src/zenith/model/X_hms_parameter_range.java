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

/** Generated Model for hms_parameter_range
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_parameter_range extends PO implements I_hms_parameter_range, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170606L;

    /** Standard Constructor */
    public X_hms_parameter_range (Properties ctx, int hms_parameter_range_ID, String trxName)
    {
      super (ctx, hms_parameter_range_ID, trxName);
      /** if (hms_parameter_range_ID == 0)
        {
			sethas_power (false);
			sethms_parameter_range_ID (0);
			sethms_parameters_ID (0);
			setis_age_based (false);
			setis_gender_based (false);
        } */
    }

    /** Load Constructor */
    public X_hms_parameter_range (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_parameter_range[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set age_from.
		@param age_from age_from	  */
	public void setage_from (BigDecimal age_from)
	{
		set_Value (COLUMNNAME_age_from, age_from);
	}

	/** Get age_from.
		@return age_from	  */
	public BigDecimal getage_from () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_age_from);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set age_to.
		@param age_to age_to	  */
	public void setage_to (BigDecimal age_to)
	{
		set_Value (COLUMNNAME_age_to, age_to);
	}

	/** Get age_to.
		@return age_to	  */
	public BigDecimal getage_to () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_age_to);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Gender AD_Reference_ID=53612 */
	public static final int GENDER_AD_Reference_ID=53612;
	/** Female = F */
	public static final String GENDER_Female = "F";
	/** Male = M */
	public static final String GENDER_Male = "M";
	/** Set Gender.
		@param Gender Gender	  */
	public void setGender (String Gender)
	{

		set_Value (COLUMNNAME_Gender, Gender);
	}

	/** Get Gender.
		@return Gender	  */
	public String getGender () 
	{
		return (String)get_Value(COLUMNNAME_Gender);
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

	/** Set hms_parameter_range ID.
		@param hms_parameter_range_ID hms_parameter_range ID	  */
	public void sethms_parameter_range_ID (int hms_parameter_range_ID)
	{
		if (hms_parameter_range_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_parameter_range_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_parameter_range_ID, Integer.valueOf(hms_parameter_range_ID));
	}

	/** Get hms_parameter_range ID.
		@return hms_parameter_range ID	  */
	public int gethms_parameter_range_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_parameter_range_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_parameters gethms_parameters() throws RuntimeException
    {
		return (I_hms_parameters)MTable.get(getCtx(), I_hms_parameters.Table_Name)
			.getPO(gethms_parameters_ID(), get_TrxName());	}

	/** Set hms_parameters ID.
		@param hms_parameters_ID hms_parameters ID	  */
	public void sethms_parameters_ID (int hms_parameters_ID)
	{
		if (hms_parameters_ID < 1) 
			set_Value (COLUMNNAME_hms_parameters_ID, null);
		else 
			set_Value (COLUMNNAME_hms_parameters_ID, Integer.valueOf(hms_parameters_ID));
	}

	/** Get hms_parameters ID.
		@return hms_parameters ID	  */
	public int gethms_parameters_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_parameters_ID);
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

	/** Set power.
		@param power power	  */
	public void setpower (String power)
	{
		set_Value (COLUMNNAME_power, power);
	}

	/** Get power.
		@return power	  */
	public String getpower () 
	{
		return (String)get_Value(COLUMNNAME_power);
	}
}