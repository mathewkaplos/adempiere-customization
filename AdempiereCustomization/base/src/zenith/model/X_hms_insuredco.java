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

/** Generated Model for hms_insuredco
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_insuredco extends PO implements I_hms_insuredco, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180301L;

    /** Standard Constructor */
    public X_hms_insuredco (Properties ctx, int hms_insuredco_ID, String trxName)
    {
      super (ctx, hms_insuredco_ID, trxName);
      /** if (hms_insuredco_ID == 0)
        {
			sethms_insuredco_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_insuredco (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_insuredco[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_hms_insco gethms_insco() throws RuntimeException
    {
		return (I_hms_insco)MTable.get(getCtx(), I_hms_insco.Table_Name)
			.getPO(gethms_insco_ID(), get_TrxName());	}

	/** Set Insurance Company.
		@param hms_insco_ID Insurance Company	  */
	public void sethms_insco_ID (int hms_insco_ID)
	{
		if (hms_insco_ID < 1) 
			set_Value (COLUMNNAME_hms_insco_ID, null);
		else 
			set_Value (COLUMNNAME_hms_insco_ID, Integer.valueOf(hms_insco_ID));
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

	/** Set hms_insco_policy_id.
		@param hms_insco_policy_id hms_insco_policy_id	  */
	public void sethms_insco_policy_id (int hms_insco_policy_id)
	{
		set_Value (COLUMNNAME_hms_insco_policy_id, Integer.valueOf(hms_insco_policy_id));
	}

	/** Get hms_insco_policy_id.
		@return hms_insco_policy_id	  */
	public int gethms_insco_policy_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insco_policy_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}


	/** Set hms_inspolicy_ID.
		@param hms_inspolicy_ID hms_inspolicy_ID	  */
	public void sethms_inspolicy_ID (int hms_inspolicy_ID)
	{
		if (hms_inspolicy_ID < 1) 
			set_Value (COLUMNNAME_hms_inspolicy_ID, null);
		else 
			set_Value (COLUMNNAME_hms_inspolicy_ID, Integer.valueOf(hms_inspolicy_ID));
	}

	/** Get hms_inspolicy_ID.
		@return hms_inspolicy_ID	  */
	public int gethms_inspolicy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_inspolicy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_insuredco_ID.
		@param hms_insuredco_ID hms_insuredco_ID	  */
	public void sethms_insuredco_ID (int hms_insuredco_ID)
	{
		if (hms_insuredco_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_insuredco_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_insuredco_ID, Integer.valueOf(hms_insuredco_ID));
	}

	/** Get hms_insuredco_ID.
		@return hms_insuredco_ID	  */
	public int gethms_insuredco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_insuredco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set iconame.
		@param iconame iconame	  */
	public void seticoname (String iconame)
	{
		set_Value (COLUMNNAME_iconame, iconame);
	}

	/** Get iconame.
		@return iconame	  */
	public String geticoname () 
	{
		return (String)get_Value(COLUMNNAME_iconame);
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
}