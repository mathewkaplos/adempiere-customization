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

/** Generated Model for hms_mch_services
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_mch_services extends PO implements I_hms_mch_services, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190214L;

    /** Standard Constructor */
    public X_hms_mch_services (Properties ctx, int hms_mch_services_ID, String trxName)
    {
      super (ctx, hms_mch_services_ID, trxName);
      /** if (hms_mch_services_ID == 0)
        {
			sethms_mch_services_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_mch_services (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_mch_services[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Done.
		@param done Done	  */
	public void setdone (boolean done)
	{
		set_Value (COLUMNNAME_done, Boolean.valueOf(done));
	}

	/** Get Done.
		@return Done	  */
	public boolean isdone () 
	{
		Object oo = get_Value(COLUMNNAME_done);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set hms_mch_services ID.
		@param hms_mch_services_ID hms_mch_services ID	  */
	public void sethms_mch_services_ID (int hms_mch_services_ID)
	{
		if (hms_mch_services_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_mch_services_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_mch_services_ID, Integer.valueOf(hms_mch_services_ID));
	}

	/** Get hms_mch_services ID.
		@return hms_mch_services ID	  */
	public int gethms_mch_services_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_mch_services_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Name.
		@param hms_test_ID 
		Service Name
	  */
	public void sethms_test_ID (int hms_test_ID)
	{
		if (hms_test_ID < 1) 
			set_Value (COLUMNNAME_hms_test_ID, null);
		else 
			set_Value (COLUMNNAME_hms_test_ID, Integer.valueOf(hms_test_ID));
	}

	/** Get Service Name.
		@return Service Name
	  */
	public int gethms_test_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_test_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException
    {
		return (I_hms_treatment_doc)MTable.get(getCtx(), I_hms_treatment_doc.Table_Name)
			.getPO(gethms_treatment_doc_ID(), get_TrxName());	}

	/** Set hms_treatment_doc ID.
		@param hms_treatment_doc_ID hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID)
	{
		if (hms_treatment_doc_ID < 1) 
			set_Value (COLUMNNAME_hms_treatment_doc_ID, null);
		else 
			set_Value (COLUMNNAME_hms_treatment_doc_ID, Integer.valueOf(hms_treatment_doc_ID));
	}

	/** Get hms_treatment_doc ID.
		@return hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_treatment_doc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}