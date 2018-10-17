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

/** Generated Model for hms_specimens
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_specimens extends PO implements I_hms_specimens, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170521L;

    /** Standard Constructor */
    public X_hms_specimens (Properties ctx, int hms_specimens_ID, String trxName)
    {
      super (ctx, hms_specimens_ID, trxName);
      /** if (hms_specimens_ID == 0)
        {
			sethms_specimens_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_specimens (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_specimens[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set doaction.
		@param doaction doaction	  */
	public void setdoaction (boolean doaction)
	{
		set_Value (COLUMNNAME_doaction, Boolean.valueOf(doaction));
	}

	/** Get doaction.
		@return doaction	  */
	public boolean isdoaction () 
	{
		Object oo = get_Value(COLUMNNAME_doaction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set hms_specimens ID.
		@param hms_specimens_ID hms_specimens ID	  */
	public void sethms_specimens_ID (int hms_specimens_ID)
	{
		if (hms_specimens_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_specimens_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_specimens_ID, Integer.valueOf(hms_specimens_ID));
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

	public I_hms_test gethms_test() throws RuntimeException
    {
		return (I_hms_test)MTable.get(getCtx(), I_hms_test.Table_Name)
			.getPO(gethms_test_ID(), get_TrxName());	}

	/** Set hms_test ID.
		@param hms_test_ID hms_test ID	  */
	public void sethms_test_ID (int hms_test_ID)
	{
		if (hms_test_ID < 1) 
			set_Value (COLUMNNAME_hms_test_ID, null);
		else 
			set_Value (COLUMNNAME_hms_test_ID, Integer.valueOf(hms_test_ID));
	}

	/** Get hms_test ID.
		@return hms_test ID	  */
	public int gethms_test_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_test_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set parameters.
		@param parameters parameters	  */
	public void setparameters (BigDecimal parameters)
	{
		set_Value (COLUMNNAME_parameters, parameters);
	}

	/** Get parameters.
		@return parameters	  */
	public BigDecimal getparameters () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_parameters);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set selected.
		@param selected selected	  */
	public void setselected (boolean selected)
	{
		set_Value (COLUMNNAME_selected, Boolean.valueOf(selected));
	}

	/** Get selected.
		@return selected	  */
	public boolean isselected () 
	{
		Object oo = get_Value(COLUMNNAME_selected);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}