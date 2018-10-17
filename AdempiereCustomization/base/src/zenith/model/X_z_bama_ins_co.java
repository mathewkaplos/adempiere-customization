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

/** Generated Model for z_bama_ins_co
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_z_bama_ins_co extends PO implements I_z_bama_ins_co, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170710L;

    /** Standard Constructor */
    public X_z_bama_ins_co (Properties ctx, int z_bama_ins_co_ID, String trxName)
    {
      super (ctx, z_bama_ins_co_ID, trxName);
      /** if (z_bama_ins_co_ID == 0)
        {
			setz_bama_ins_co_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_bama_ins_co (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_z_bama_ins_co[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set inscomp_boxno.
		@param inscomp_boxno inscomp_boxno	  */
	public void setinscomp_boxno (String inscomp_boxno)
	{
		set_Value (COLUMNNAME_inscomp_boxno, inscomp_boxno);
	}

	/** Get inscomp_boxno.
		@return inscomp_boxno	  */
	public String getinscomp_boxno () 
	{
		return (String)get_Value(COLUMNNAME_inscomp_boxno);
	}

	/** Set inscomp_email.
		@param inscomp_email inscomp_email	  */
	public void setinscomp_email (String inscomp_email)
	{
		set_Value (COLUMNNAME_inscomp_email, inscomp_email);
	}

	/** Get inscomp_email.
		@return inscomp_email	  */
	public String getinscomp_email () 
	{
		return (String)get_Value(COLUMNNAME_inscomp_email);
	}

	/** Set inscomp_fax.
		@param inscomp_fax inscomp_fax	  */
	public void setinscomp_fax (String inscomp_fax)
	{
		set_Value (COLUMNNAME_inscomp_fax, inscomp_fax);
	}

	/** Get inscomp_fax.
		@return inscomp_fax	  */
	public String getinscomp_fax () 
	{
		return (String)get_Value(COLUMNNAME_inscomp_fax);
	}

	/** Set inscomp_insuranceid.
		@param inscomp_insuranceid inscomp_insuranceid	  */
	public void setinscomp_insuranceid (int inscomp_insuranceid)
	{
		set_Value (COLUMNNAME_inscomp_insuranceid, Integer.valueOf(inscomp_insuranceid));
	}

	/** Get inscomp_insuranceid.
		@return inscomp_insuranceid	  */
	public int getinscomp_insuranceid () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_inscomp_insuranceid);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set inscomp_name.
		@param inscomp_name inscomp_name	  */
	public void setinscomp_name (String inscomp_name)
	{
		set_Value (COLUMNNAME_inscomp_name, inscomp_name);
	}

	/** Get inscomp_name.
		@return inscomp_name	  */
	public String getinscomp_name () 
	{
		return (String)get_Value(COLUMNNAME_inscomp_name);
	}

	/** Set inscomp_tel.
		@param inscomp_tel inscomp_tel	  */
	public void setinscomp_tel (String inscomp_tel)
	{
		set_Value (COLUMNNAME_inscomp_tel, inscomp_tel);
	}

	/** Get inscomp_tel.
		@return inscomp_tel	  */
	public String getinscomp_tel () 
	{
		return (String)get_Value(COLUMNNAME_inscomp_tel);
	}

	/** Set inscomp_town.
		@param inscomp_town inscomp_town	  */
	public void setinscomp_town (String inscomp_town)
	{
		set_Value (COLUMNNAME_inscomp_town, inscomp_town);
	}

	/** Get inscomp_town.
		@return inscomp_town	  */
	public String getinscomp_town () 
	{
		return (String)get_Value(COLUMNNAME_inscomp_town);
	}

	/** Set z_bama_ins_co ID.
		@param z_bama_ins_co_ID z_bama_ins_co ID	  */
	public void setz_bama_ins_co_ID (int z_bama_ins_co_ID)
	{
		if (z_bama_ins_co_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_z_bama_ins_co_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_z_bama_ins_co_ID, Integer.valueOf(z_bama_ins_co_ID));
	}

	/** Get z_bama_ins_co ID.
		@return z_bama_ins_co ID	  */
	public int getz_bama_ins_co_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_bama_ins_co_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}