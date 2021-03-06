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

/** Generated Model for hms_drugs
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_drugs extends PO implements I_hms_drugs, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190722L;

    /** Standard Constructor */
    public X_hms_drugs (Properties ctx, int hms_drugs_ID, String trxName)
    {
      super (ctx, hms_drugs_ID, trxName);
      /** if (hms_drugs_ID == 0)
        {
			sethms_drugs_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_drugs (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_drugs[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Dosage.
		@param dosage Dosage	  */
	public void setdosage (String dosage)
	{
		set_Value (COLUMNNAME_dosage, dosage);
	}

	/** Get Dosage.
		@return Dosage	  */
	public String getdosage () 
	{
		return (String)get_Value(COLUMNNAME_dosage);
	}

	/** Set hms_billing ID.
		@param hms_billing_ID hms_billing ID	  */
	public void sethms_billing_ID (int hms_billing_ID)
	{
		if (hms_billing_ID < 1) 
			set_Value (COLUMNNAME_hms_billing_ID, null);
		else 
			set_Value (COLUMNNAME_hms_billing_ID, Integer.valueOf(hms_billing_ID));
	}

	/** Get hms_billing ID.
		@return hms_billing ID	  */
	public int gethms_billing_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_billing_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_drugs ID.
		@param hms_drugs_ID hms_drugs ID	  */
	public void sethms_drugs_ID (int hms_drugs_ID)
	{
		if (hms_drugs_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_drugs_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_drugs_ID, Integer.valueOf(hms_drugs_ID));
	}

	/** Get hms_drugs ID.
		@return hms_drugs ID	  */
	public int gethms_drugs_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_drugs_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}