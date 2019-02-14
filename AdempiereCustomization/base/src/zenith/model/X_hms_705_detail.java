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

/** Generated Model for hms_705_detail
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_705_detail extends PO implements I_hms_705_detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190214L;

    /** Standard Constructor */
    public X_hms_705_detail (Properties ctx, int hms_705_detail_ID, String trxName)
    {
      super (ctx, hms_705_detail_ID, trxName);
      /** if (hms_705_detail_ID == 0)
        {
			sethms_705_detail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_705_detail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_705_detail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set 1.
		@param d1 1	  */
	public void setd1 (int d1)
	{
		set_Value (COLUMNNAME_d1, Integer.valueOf(d1));
	}

	/** Get 1.
		@return 1	  */
	public int getd1 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d1);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 10.
		@param d10 10	  */
	public void setd10 (int d10)
	{
		set_Value (COLUMNNAME_d10, Integer.valueOf(d10));
	}

	/** Get 10.
		@return 10	  */
	public int getd10 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d10);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 11.
		@param d11 11	  */
	public void setd11 (int d11)
	{
		set_Value (COLUMNNAME_d11, Integer.valueOf(d11));
	}

	/** Get 11.
		@return 11	  */
	public int getd11 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d11);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 12.
		@param d12 12	  */
	public void setd12 (int d12)
	{
		set_Value (COLUMNNAME_d12, Integer.valueOf(d12));
	}

	/** Get 12.
		@return 12	  */
	public int getd12 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d12);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 13.
		@param d13 13	  */
	public void setd13 (int d13)
	{
		set_Value (COLUMNNAME_d13, Integer.valueOf(d13));
	}

	/** Get 13.
		@return 13	  */
	public int getd13 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d13);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 14.
		@param d14 14	  */
	public void setd14 (int d14)
	{
		set_Value (COLUMNNAME_d14, Integer.valueOf(d14));
	}

	/** Get 14.
		@return 14	  */
	public int getd14 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d14);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 15.
		@param d15 15	  */
	public void setd15 (int d15)
	{
		set_Value (COLUMNNAME_d15, Integer.valueOf(d15));
	}

	/** Get 15.
		@return 15	  */
	public int getd15 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d15);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 16.
		@param d16 16	  */
	public void setd16 (int d16)
	{
		set_Value (COLUMNNAME_d16, Integer.valueOf(d16));
	}

	/** Get 16.
		@return 16	  */
	public int getd16 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d16);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 17.
		@param d17 17	  */
	public void setd17 (int d17)
	{
		set_Value (COLUMNNAME_d17, Integer.valueOf(d17));
	}

	/** Get 17.
		@return 17	  */
	public int getd17 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d17);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 18.
		@param d18 18	  */
	public void setd18 (int d18)
	{
		set_Value (COLUMNNAME_d18, Integer.valueOf(d18));
	}

	/** Get 18.
		@return 18	  */
	public int getd18 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d18);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 19.
		@param d19 19	  */
	public void setd19 (int d19)
	{
		set_Value (COLUMNNAME_d19, Integer.valueOf(d19));
	}

	/** Get 19.
		@return 19	  */
	public int getd19 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d19);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 2.
		@param d2 2	  */
	public void setd2 (int d2)
	{
		set_Value (COLUMNNAME_d2, Integer.valueOf(d2));
	}

	/** Get 2.
		@return 2	  */
	public int getd2 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d2);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 20.
		@param d20 20	  */
	public void setd20 (int d20)
	{
		set_Value (COLUMNNAME_d20, Integer.valueOf(d20));
	}

	/** Get 20.
		@return 20	  */
	public int getd20 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d20);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 21.
		@param d21 21	  */
	public void setd21 (int d21)
	{
		set_Value (COLUMNNAME_d21, Integer.valueOf(d21));
	}

	/** Get 21.
		@return 21	  */
	public int getd21 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d21);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 22.
		@param d22 22	  */
	public void setd22 (int d22)
	{
		set_Value (COLUMNNAME_d22, Integer.valueOf(d22));
	}

	/** Get 22.
		@return 22	  */
	public int getd22 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d22);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 23.
		@param d23 23	  */
	public void setd23 (int d23)
	{
		set_Value (COLUMNNAME_d23, Integer.valueOf(d23));
	}

	/** Get 23.
		@return 23	  */
	public int getd23 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d23);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 24.
		@param d24 24	  */
	public void setd24 (int d24)
	{
		set_Value (COLUMNNAME_d24, Integer.valueOf(d24));
	}

	/** Get 24.
		@return 24	  */
	public int getd24 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d24);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 25.
		@param d25 25	  */
	public void setd25 (int d25)
	{
		set_Value (COLUMNNAME_d25, Integer.valueOf(d25));
	}

	/** Get 25.
		@return 25	  */
	public int getd25 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d25);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 26.
		@param d26 26	  */
	public void setd26 (int d26)
	{
		set_Value (COLUMNNAME_d26, Integer.valueOf(d26));
	}

	/** Get 26.
		@return 26	  */
	public int getd26 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d26);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 27.
		@param d27 27	  */
	public void setd27 (int d27)
	{
		set_Value (COLUMNNAME_d27, Integer.valueOf(d27));
	}

	/** Get 27.
		@return 27	  */
	public int getd27 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d27);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 28.
		@param d28 28	  */
	public void setd28 (int d28)
	{
		set_Value (COLUMNNAME_d28, Integer.valueOf(d28));
	}

	/** Get 28.
		@return 28	  */
	public int getd28 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d28);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 29.
		@param d29 29	  */
	public void setd29 (int d29)
	{
		set_Value (COLUMNNAME_d29, Integer.valueOf(d29));
	}

	/** Get 29.
		@return 29	  */
	public int getd29 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d29);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 3.
		@param d3 3	  */
	public void setd3 (int d3)
	{
		set_Value (COLUMNNAME_d3, Integer.valueOf(d3));
	}

	/** Get 3.
		@return 3	  */
	public int getd3 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d3);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 30.
		@param d30 30	  */
	public void setd30 (int d30)
	{
		set_Value (COLUMNNAME_d30, Integer.valueOf(d30));
	}

	/** Get 30.
		@return 30	  */
	public int getd30 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d30);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 31.
		@param d31 31	  */
	public void setd31 (int d31)
	{
		set_Value (COLUMNNAME_d31, Integer.valueOf(d31));
	}

	/** Get 31.
		@return 31	  */
	public int getd31 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d31);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 4.
		@param d4 4	  */
	public void setd4 (int d4)
	{
		set_Value (COLUMNNAME_d4, Integer.valueOf(d4));
	}

	/** Get 4.
		@return 4	  */
	public int getd4 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d4);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 5.
		@param d5 5	  */
	public void setd5 (int d5)
	{
		set_Value (COLUMNNAME_d5, Integer.valueOf(d5));
	}

	/** Get 5.
		@return 5	  */
	public int getd5 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d5);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 6.
		@param d6 6	  */
	public void setd6 (int d6)
	{
		set_Value (COLUMNNAME_d6, Integer.valueOf(d6));
	}

	/** Get 6.
		@return 6	  */
	public int getd6 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d6);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 7.
		@param d7 7	  */
	public void setd7 (int d7)
	{
		set_Value (COLUMNNAME_d7, Integer.valueOf(d7));
	}

	/** Get 7.
		@return 7	  */
	public int getd7 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d7);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 8.
		@param d8 8	  */
	public void setd8 (int d8)
	{
		set_Value (COLUMNNAME_d8, Integer.valueOf(d8));
	}

	/** Get 8.
		@return 8	  */
	public int getd8 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d8);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set 9.
		@param d9 9	  */
	public void setd9 (int d9)
	{
		set_Value (COLUMNNAME_d9, Integer.valueOf(d9));
	}

	/** Get 9.
		@return 9	  */
	public int getd9 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_d9);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set hms_705_detail ID.
		@param hms_705_detail_ID hms_705_detail ID	  */
	public void sethms_705_detail_ID (int hms_705_detail_ID)
	{
		if (hms_705_detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_705_detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_705_detail_ID, Integer.valueOf(hms_705_detail_ID));
	}

	/** Get hms_705_detail ID.
		@return hms_705_detail ID	  */
	public int gethms_705_detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_705_detail_ID);
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

	/** Set Totals.
		@param totals Totals	  */
	public void settotals (int totals)
	{
		set_Value (COLUMNNAME_totals, Integer.valueOf(totals));
	}

	/** Get Totals.
		@return Totals	  */
	public int gettotals () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_totals);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}