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

/** Generated Model for z_bama_price
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_z_bama_price extends PO implements I_z_bama_price, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170706L;

    /** Standard Constructor */
    public X_z_bama_price (Properties ctx, int z_bama_price_ID, String trxName)
    {
      super (ctx, z_bama_price_ID, trxName);
      /** if (z_bama_price_ID == 0)
        {
			setz_bama_price_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_bama_price (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_z_bama_price[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set storage_pricelistid.
		@param storage_pricelistid storage_pricelistid	  */
	public void setstorage_pricelistid (int storage_pricelistid)
	{
		set_Value (COLUMNNAME_storage_pricelistid, Integer.valueOf(storage_pricelistid));
	}

	/** Get storage_pricelistid.
		@return storage_pricelistid	  */
	public int getstorage_pricelistid () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_storage_pricelistid);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set storage_productid.
		@param storage_productid storage_productid	  */
	public void setstorage_productid (int storage_productid)
	{
		set_Value (COLUMNNAME_storage_productid, Integer.valueOf(storage_productid));
	}

	/** Get storage_productid.
		@return storage_productid	  */
	public int getstorage_productid () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_storage_productid);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set storage_unitprice.
		@param storage_unitprice storage_unitprice	  */
	public void setstorage_unitprice (BigDecimal storage_unitprice)
	{
		set_Value (COLUMNNAME_storage_unitprice, storage_unitprice);
	}

	/** Get storage_unitprice.
		@return storage_unitprice	  */
	public BigDecimal getstorage_unitprice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_storage_unitprice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set z_bama_price ID.
		@param z_bama_price_ID z_bama_price ID	  */
	public void setz_bama_price_ID (int z_bama_price_ID)
	{
		if (z_bama_price_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_z_bama_price_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_z_bama_price_ID, Integer.valueOf(z_bama_price_ID));
	}

	/** Get z_bama_price ID.
		@return z_bama_price ID	  */
	public int getz_bama_price_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_bama_price_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}