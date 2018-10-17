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

/** Generated Model for z_bama_product
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_z_bama_product extends PO implements I_z_bama_product, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170706L;

    /** Standard Constructor */
    public X_z_bama_product (Properties ctx, int z_bama_product_ID, String trxName)
    {
      super (ctx, z_bama_product_ID, trxName);
      /** if (z_bama_product_ID == 0)
        {
			setz_bama_product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_bama_product (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_z_bama_product[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set product_category.
		@param product_category product_category	  */
	public void setproduct_category (int product_category)
	{
		set_Value (COLUMNNAME_product_category, Integer.valueOf(product_category));
	}

	/** Get product_category.
		@return product_category	  */
	public int getproduct_category () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_product_category);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set product_code.
		@param product_code product_code	  */
	public void setproduct_code (String product_code)
	{
		set_Value (COLUMNNAME_product_code, product_code);
	}

	/** Get product_code.
		@return product_code	  */
	public String getproduct_code () 
	{
		return (String)get_Value(COLUMNNAME_product_code);
	}

	/** Set product_name.
		@param product_name product_name	  */
	public void setproduct_name (String product_name)
	{
		set_Value (COLUMNNAME_product_name, product_name);
	}

	/** Get product_name.
		@return product_name	  */
	public String getproduct_name () 
	{
		return (String)get_Value(COLUMNNAME_product_name);
	}

	/** Set z_bama_product ID.
		@param z_bama_product_ID z_bama_product ID	  */
	public void setz_bama_product_ID (int z_bama_product_ID)
	{
		if (z_bama_product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_z_bama_product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_z_bama_product_ID, Integer.valueOf(z_bama_product_ID));
	}

	/** Get z_bama_product ID.
		@return z_bama_product ID	  */
	public int getz_bama_product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_bama_product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}