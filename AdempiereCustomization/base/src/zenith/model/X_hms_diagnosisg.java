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

/** Generated Model for hms_diagnosisg
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_diagnosisg extends PO implements I_hms_diagnosisg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170714L;

    /** Standard Constructor */
    public X_hms_diagnosisg (Properties ctx, int hms_diagnosisg_ID, String trxName)
    {
      super (ctx, hms_diagnosisg_ID, trxName);
      /** if (hms_diagnosisg_ID == 0)
        {
			sethms_diagnosisg_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_diagnosisg (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_diagnosisg[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diagnosis Code.
		@param diagg_code Diagnosis Code	  */
	public void setdiagg_code (String diagg_code)
	{
		set_Value (COLUMNNAME_diagg_code, diagg_code);
	}

	/** Get Diagnosis Code.
		@return Diagnosis Code	  */
	public String getdiagg_code () 
	{
		return (String)get_Value(COLUMNNAME_diagg_code);
	}

	/** Set Diagnosis Group.
		@param hms_diagnosisg_ID Diagnosis Group	  */
	public void sethms_diagnosisg_ID (int hms_diagnosisg_ID)
	{
		if (hms_diagnosisg_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_diagnosisg_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_diagnosisg_ID, Integer.valueOf(hms_diagnosisg_ID));
	}

	/** Get Diagnosis Group.
		@return Diagnosis Group	  */
	public int gethms_diagnosisg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosisg_ID);
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