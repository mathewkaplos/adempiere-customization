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

/** Generated Model for hms_test
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_test extends PO implements I_hms_test, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171231L;

    /** Standard Constructor */
    public X_hms_test (Properties ctx, int hms_test_ID, String trxName)
    {
      super (ctx, hms_test_ID, trxName);
      /** if (hms_test_ID == 0)
        {
			sethms_test_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_test (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_test[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set disp_consumables.
		@param disp_consumables disp_consumables	  */
	public void setdisp_consumables (boolean disp_consumables)
	{
		set_Value (COLUMNNAME_disp_consumables, Boolean.valueOf(disp_consumables));
	}

	/** Get disp_consumables.
		@return disp_consumables	  */
	public boolean isdisp_consumables () 
	{
		Object oo = get_Value(COLUMNNAME_disp_consumables);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Send Request.
		@param doaction Send Request	  */
	public void setdoaction (String doaction)
	{
		set_Value (COLUMNNAME_doaction, doaction);
	}

	/** Get Send Request.
		@return Send Request	  */
	public String getdoaction () 
	{
		return (String)get_Value(COLUMNNAME_doaction);
	}

	/** Set has_consumables.
		@param has_consumables has_consumables	  */
	public void sethas_consumables (boolean has_consumables)
	{
		set_Value (COLUMNNAME_has_consumables, Boolean.valueOf(has_consumables));
	}

	/** Get has_consumables.
		@return has_consumables	  */
	public boolean ishas_consumables () 
	{
		Object oo = get_Value(COLUMNNAME_has_consumables);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}



	/** Set hms_department ID.
		@param hms_department_ID hms_department ID	  */
	public void sethms_department_ID (int hms_department_ID)
	{
		if (hms_department_ID < 1) 
			set_Value (COLUMNNAME_hms_department_ID, null);
		else 
			set_Value (COLUMNNAME_hms_department_ID, Integer.valueOf(hms_department_ID));
	}

	/** Get hms_department ID.
		@return hms_department ID	  */
	public int gethms_department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}


	/** Set hms_lab_setup ID.
		@param hms_lab_setup_ID hms_lab_setup ID	  */
	public void sethms_lab_setup_ID (int hms_lab_setup_ID)
	{
		if (hms_lab_setup_ID < 1) 
			set_Value (COLUMNNAME_hms_lab_setup_ID, null);
		else 
			set_Value (COLUMNNAME_hms_lab_setup_ID, Integer.valueOf(hms_lab_setup_ID));
	}

	/** Get hms_lab_setup ID.
		@return hms_lab_setup ID	  */
	public int gethms_lab_setup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_lab_setup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Test.
		@param hms_test_ID 
		Lab Test 
	  */
	public void sethms_test_ID (int hms_test_ID)
	{
		if (hms_test_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_test_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_test_ID, Integer.valueOf(hms_test_ID));
	}

	/** Get Test.
		@return Lab Test 
	  */
	public int gethms_test_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_test_ID);
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

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Select.
		@param selected Select	  */
	public void setselected (boolean selected)
	{
		set_Value (COLUMNNAME_selected, Boolean.valueOf(selected));
	}

	/** Get Select.
		@return Select	  */
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

	/** Set specimens.
		@param specimens specimens	  */
	public void setspecimens (BigDecimal specimens)
	{
		set_Value (COLUMNNAME_specimens, specimens);
	}

	/** Get specimens.
		@return specimens	  */
	public BigDecimal getspecimens () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_specimens);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}