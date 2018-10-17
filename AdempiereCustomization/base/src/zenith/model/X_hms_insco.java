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

/** Generated Model for hms_insco
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_insco extends PO implements I_hms_insco, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180301L;

    /** Standard Constructor */
    public X_hms_insco (Properties ctx, int hms_insco_ID, String trxName)
    {
      super (ctx, hms_insco_ID, trxName);
      /** if (hms_insco_ID == 0)
        {
			sethms_insco_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_insco (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_insco[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set companyname.
		@param companyname companyname	  */
	public void setcompanyname (String companyname)
	{
		set_Value (COLUMNNAME_companyname, companyname);
	}

	/** Get companyname.
		@return companyname	  */
	public String getcompanyname () 
	{
		return (String)get_Value(COLUMNNAME_companyname);
	}

	/** Set Copay Amount.
		@param copay Copay Amount	  */
	public void setcopay (int copay)
	{
		set_Value (COLUMNNAME_copay, Integer.valueOf(copay));
	}

	/** Get Copay Amount.
		@return Copay Amount	  */
	public int getcopay () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_copay);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set county.
		@param county county	  */
	public void setcounty (String county)
	{
		set_Value (COLUMNNAME_county, county);
	}

	/** Get county.
		@return county	  */
	public String getcounty () 
	{
		return (String)get_Value(COLUMNNAME_county);
	}

	/** Set dispil1.
		@param dispil1 dispil1	  */
	public void setdispil1 (BigDecimal dispil1)
	{
		set_Value (COLUMNNAME_dispil1, dispil1);
	}

	/** Get dispil1.
		@return dispil1	  */
	public BigDecimal getdispil1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_dispil1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set dispil2.
		@param dispil2 dispil2	  */
	public void setdispil2 (BigDecimal dispil2)
	{
		set_Value (COLUMNNAME_dispil2, dispil2);
	}

	/** Get dispil2.
		@return dispil2	  */
	public BigDecimal getdispil2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_dispil2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set emailadd.
		@param emailadd emailadd	  */
	public void setemailadd (String emailadd)
	{
		set_Value (COLUMNNAME_emailadd, emailadd);
	}

	/** Get emailadd.
		@return emailadd	  */
	public String getemailadd () 
	{
		return (String)get_Value(COLUMNNAME_emailadd);
	}

	/** Set Insurance Company.
		@param hms_insco_ID Insurance Company	  */
	public void sethms_insco_ID (int hms_insco_ID)
	{
		if (hms_insco_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_insco_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_insco_ID, Integer.valueOf(hms_insco_ID));
	}

	/** Get Insurance Company.
		@return Insurance Company	  */
	public int gethms_insco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set import_id.
		@param import_id import_id	  */
	public void setimport_id (int import_id)
	{
		set_Value (COLUMNNAME_import_id, Integer.valueOf(import_id));
	}

	/** Get import_id.
		@return import_id	  */
	public int getimport_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_import_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ins_phoneno.
		@param ins_phoneno ins_phoneno	  */
	public void setins_phoneno (int ins_phoneno)
	{
		set_Value (COLUMNNAME_ins_phoneno, Integer.valueOf(ins_phoneno));
	}

	/** Get ins_phoneno.
		@return ins_phoneno	  */
	public int getins_phoneno () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ins_phoneno);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set paddress.
		@param paddress paddress	  */
	public void setpaddress (String paddress)
	{
		set_Value (COLUMNNAME_paddress, paddress);
	}

	/** Get paddress.
		@return paddress	  */
	public String getpaddress () 
	{
		return (String)get_Value(COLUMNNAME_paddress);
	}

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set pobox.
		@param pobox pobox	  */
	public void setpobox (String pobox)
	{
		set_Value (COLUMNNAME_pobox, pobox);
	}

	/** Get pobox.
		@return pobox	  */
	public String getpobox () 
	{
		return (String)get_Value(COLUMNNAME_pobox);
	}

	/** Set town.
		@param town town	  */
	public void settown (String town)
	{
		set_Value (COLUMNNAME_town, town);
	}

	/** Get town.
		@return town	  */
	public String gettown () 
	{
		return (String)get_Value(COLUMNNAME_town);
	}
}