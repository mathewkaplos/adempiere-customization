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

/** Generated Model for hms_gererate_705
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_gererate_705 extends PO implements I_hms_gererate_705, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190214L;

    /** Standard Constructor */
    public X_hms_gererate_705 (Properties ctx, int hms_gererate_705_ID, String trxName)
    {
      super (ctx, hms_gererate_705_ID, trxName);
      /** if (hms_gererate_705_ID == 0)
        {
			sethms_gererate_705_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_gererate_705 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_gererate_705[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set GENERATE REPORT.
		@param btn_generate GENERATE REPORT	  */
	public void setbtn_generate (String btn_generate)
	{
		set_Value (COLUMNNAME_btn_generate, btn_generate);
	}

	/** Get GENERATE REPORT.
		@return GENERATE REPORT	  */
	public String getbtn_generate () 
	{
		return (String)get_Value(COLUMNNAME_btn_generate);
	}

	/** Set PRINT.
		@param btn_print PRINT	  */
	public void setbtn_print (String btn_print)
	{
		set_Value (COLUMNNAME_btn_print, btn_print);
	}

	/** Get PRINT.
		@return PRINT	  */
	public String getbtn_print () 
	{
		return (String)get_Value(COLUMNNAME_btn_print);
	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set County.
		@param county County	  */
	public void setcounty (String county)
	{
		set_Value (COLUMNNAME_county, county);
	}

	/** Get County.
		@return County	  */
	public String getcounty () 
	{
		return (String)get_Value(COLUMNNAME_county);
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** facility_type AD_Reference_ID=1000044 */
	public static final int FACILITY_TYPE_AD_Reference_ID=1000044;
	/** Public = Public */
	public static final String FACILITY_TYPE_Public = "Public";
	/** Private = Private */
	public static final String FACILITY_TYPE_Private = "Private";
	/** Set Facility Type.
		@param facility_type Facility Type	  */
	public void setfacility_type (String facility_type)
	{

		set_Value (COLUMNNAME_facility_type, facility_type);
	}

	/** Get Facility Type.
		@return Facility Type	  */
	public String getfacility_type () 
	{
		return (String)get_Value(COLUMNNAME_facility_type);
	}

	/** Set hms_gererate_705 ID.
		@param hms_gererate_705_ID hms_gererate_705 ID	  */
	public void sethms_gererate_705_ID (int hms_gererate_705_ID)
	{
		if (hms_gererate_705_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_gererate_705_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_gererate_705_ID, Integer.valueOf(hms_gererate_705_ID));
	}

	/** Get hms_gererate_705 ID.
		@return hms_gererate_705 ID	  */
	public int gethms_gererate_705_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_gererate_705_ID);
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

	/** Set start_date.
		@param start_date start_date	  */
	public void setstart_date (Timestamp start_date)
	{
		set_Value (COLUMNNAME_start_date, start_date);
	}

	/** Get start_date.
		@return start_date	  */
	public Timestamp getstart_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_start_date);
	}

	/** Set Sub County.
		@param sub_county Sub County	  */
	public void setsub_county (String sub_county)
	{
		set_Value (COLUMNNAME_sub_county, sub_county);
	}

	/** Get Sub County.
		@return Sub County	  */
	public String getsub_county () 
	{
		return (String)get_Value(COLUMNNAME_sub_county);
	}

	/** Set Ward.
		@param ward Ward	  */
	public void setward (String ward)
	{
		set_Value (COLUMNNAME_ward, ward);
	}

	/** Get Ward.
		@return Ward	  */
	public String getward () 
	{
		return (String)get_Value(COLUMNNAME_ward);
	}
}