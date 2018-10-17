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

/** Generated Model for z_bama_ins
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_z_bama_ins extends PO implements I_z_bama_ins, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170710L;

    /** Standard Constructor */
    public X_z_bama_ins (Properties ctx, int z_bama_ins_ID, String trxName)
    {
      super (ctx, z_bama_ins_ID, trxName);
      /** if (z_bama_ins_ID == 0)
        {
			setz_bama_ins_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_bama_ins (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_z_bama_ins[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set insurance_boxno.
		@param insurance_boxno insurance_boxno	  */
	public void setinsurance_boxno (String insurance_boxno)
	{
		set_Value (COLUMNNAME_insurance_boxno, insurance_boxno);
	}

	/** Get insurance_boxno.
		@return insurance_boxno	  */
	public String getinsurance_boxno () 
	{
		return (String)get_Value(COLUMNNAME_insurance_boxno);
	}

	/** Set insurance_email.
		@param insurance_email insurance_email	  */
	public void setinsurance_email (String insurance_email)
	{
		set_Value (COLUMNNAME_insurance_email, insurance_email);
	}

	/** Get insurance_email.
		@return insurance_email	  */
	public String getinsurance_email () 
	{
		return (String)get_Value(COLUMNNAME_insurance_email);
	}

	/** Set insurance_name.
		@param insurance_name insurance_name	  */
	public void setinsurance_name (String insurance_name)
	{
		set_Value (COLUMNNAME_insurance_name, insurance_name);
	}

	/** Get insurance_name.
		@return insurance_name	  */
	public String getinsurance_name () 
	{
		return (String)get_Value(COLUMNNAME_insurance_name);
	}

	/** Set insurance_town.
		@param insurance_town insurance_town	  */
	public void setinsurance_town (String insurance_town)
	{
		set_Value (COLUMNNAME_insurance_town, insurance_town);
	}

	/** Get insurance_town.
		@return insurance_town	  */
	public String getinsurance_town () 
	{
		return (String)get_Value(COLUMNNAME_insurance_town);
	}

	/** Set z_bama_ins ID.
		@param z_bama_ins_ID z_bama_ins ID	  */
	public void setz_bama_ins_ID (int z_bama_ins_ID)
	{
		if (z_bama_ins_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_z_bama_ins_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_z_bama_ins_ID, Integer.valueOf(z_bama_ins_ID));
	}

	/** Get z_bama_ins ID.
		@return z_bama_ins ID	  */
	public int getz_bama_ins_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_bama_ins_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}