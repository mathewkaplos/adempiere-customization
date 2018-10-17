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

/** Generated Model for z_bama_pro_cat
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_z_bama_pro_cat extends PO implements I_z_bama_pro_cat, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170706L;

    /** Standard Constructor */
    public X_z_bama_pro_cat (Properties ctx, int z_bama_pro_cat_ID, String trxName)
    {
      super (ctx, z_bama_pro_cat_ID, trxName);
      /** if (z_bama_pro_cat_ID == 0)
        {
			setz_bama_pro_cat_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_bama_pro_cat (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_z_bama_pro_cat[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set category_name.
		@param category_name category_name	  */
	public void setcategory_name (String category_name)
	{
		set_Value (COLUMNNAME_category_name, category_name);
	}

	/** Get category_name.
		@return category_name	  */
	public String getcategory_name () 
	{
		return (String)get_Value(COLUMNNAME_category_name);
	}

	/** Set z_bama_pro_cat ID.
		@param z_bama_pro_cat_ID z_bama_pro_cat ID	  */
	public void setz_bama_pro_cat_ID (int z_bama_pro_cat_ID)
	{
		if (z_bama_pro_cat_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_z_bama_pro_cat_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_z_bama_pro_cat_ID, Integer.valueOf(z_bama_pro_cat_ID));
	}

	/** Get z_bama_pro_cat ID.
		@return z_bama_pro_cat ID	  */
	public int getz_bama_pro_cat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_bama_pro_cat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}