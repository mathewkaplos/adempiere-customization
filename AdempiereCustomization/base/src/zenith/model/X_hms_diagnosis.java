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

/** Generated Model for hms_diagnosis
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_diagnosis extends PO implements I_hms_diagnosis, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171106L;

    /** Standard Constructor */
    public X_hms_diagnosis (Properties ctx, int hms_diagnosis_ID, String trxName)
    {
      super (ctx, hms_diagnosis_ID, trxName);
      /** if (hms_diagnosis_ID == 0)
        {
			sethms_diagnosis_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_diagnosis (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_diagnosis[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diagnosis Code.
		@param diag_code Diagnosis Code	  */
	public void setdiag_code (String diag_code)
	{
		set_Value (COLUMNNAME_diag_code, diag_code);
	}

	/** Get Diagnosis Code.
		@return Diagnosis Code	  */
	public String getdiag_code () 
	{
		return (String)get_Value(COLUMNNAME_diag_code);
	}

	/** Set Diagnosis.
		@param hms_diagnosis_ID Diagnosis	  */
	public void sethms_diagnosis_ID (int hms_diagnosis_ID)
	{
		if (hms_diagnosis_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_diagnosis_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_diagnosis_ID, Integer.valueOf(hms_diagnosis_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis	  */
	public int gethms_diagnosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_diagnosisg gethms_diagnosisg() throws RuntimeException
    {
		return (I_hms_diagnosisg)MTable.get(getCtx(), I_hms_diagnosisg.Table_Name)
			.getPO(gethms_diagnosisg_ID(), get_TrxName());	}

	/** Set Diagnosis Group.
		@param hms_diagnosisg_ID Diagnosis Group	  */
	public void sethms_diagnosisg_ID (int hms_diagnosisg_ID)
	{
		if (hms_diagnosisg_ID < 1) 
			set_Value (COLUMNNAME_hms_diagnosisg_ID, null);
		else 
			set_Value (COLUMNNAME_hms_diagnosisg_ID, Integer.valueOf(hms_diagnosisg_ID));
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

	public I_hms_diagnosissubg gethms_diagnosissubg() throws RuntimeException
    {
		return (I_hms_diagnosissubg)MTable.get(getCtx(), I_hms_diagnosissubg.Table_Name)
			.getPO(gethms_diagnosissubg_ID(), get_TrxName());	}

	/** Set Diagnosis Sub Group.
		@param hms_diagnosissubg_ID 
		Diagnosis Sub Group
	  */
	public void sethms_diagnosissubg_ID (int hms_diagnosissubg_ID)
	{
		if (hms_diagnosissubg_ID < 1) 
			set_Value (COLUMNNAME_hms_diagnosissubg_ID, null);
		else 
			set_Value (COLUMNNAME_hms_diagnosissubg_ID, Integer.valueOf(hms_diagnosissubg_ID));
	}

	/** Get Diagnosis Sub Group.
		@return Diagnosis Sub Group
	  */
	public int gethms_diagnosissubg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosissubg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set importd.
		@param importd importd	  */
	public void setimportd (boolean importd)
	{
		set_Value (COLUMNNAME_importd, Boolean.valueOf(importd));
	}

	/** Get importd.
		@return importd	  */
	public boolean isimportd () 
	{
		Object oo = get_Value(COLUMNNAME_importd);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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