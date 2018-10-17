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

/** Generated Model for hms_ward_management1
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_ward_management1 extends PO implements I_hms_ward_management1, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170630L;

    /** Standard Constructor */
    public X_hms_ward_management1 (Properties ctx, int hms_ward_management1_ID, String trxName)
    {
      super (ctx, hms_ward_management1_ID, trxName);
      /** if (hms_ward_management1_ID == 0)
        {
			sethms_ward_management1_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_ward_management1 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_ward_management1[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set clear_issued_btn.
		@param clear_issued_btn clear_issued_btn	  */
	public void setclear_issued_btn (boolean clear_issued_btn)
	{
		set_Value (COLUMNNAME_clear_issued_btn, Boolean.valueOf(clear_issued_btn));
	}

	/** Get clear_issued_btn.
		@return clear_issued_btn	  */
	public boolean isclear_issued_btn () 
	{
		Object oo = get_Value(COLUMNNAME_clear_issued_btn);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp01.
		@param disp01 disp01	  */
	public void setdisp01 (boolean disp01)
	{
		set_Value (COLUMNNAME_disp01, Boolean.valueOf(disp01));
	}

	/** Get disp01.
		@return disp01	  */
	public boolean isdisp01 () 
	{
		Object oo = get_Value(COLUMNNAME_disp01);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set disp02.
		@param disp02 disp02	  */
	public void setdisp02 (boolean disp02)
	{
		set_Value (COLUMNNAME_disp02, Boolean.valueOf(disp02));
	}

	/** Get disp02.
		@return disp02	  */
	public boolean isdisp02 () 
	{
		Object oo = get_Value(COLUMNNAME_disp02);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set hms_ward_management1 ID.
		@param hms_ward_management1_ID hms_ward_management1 ID	  */
	public void sethms_ward_management1_ID (int hms_ward_management1_ID)
	{
		if (hms_ward_management1_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_ward_management1_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_ward_management1_ID, Integer.valueOf(hms_ward_management1_ID));
	}

	/** Get hms_ward_management1 ID.
		@return hms_ward_management1 ID	  */
	public int gethms_ward_management1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward_management1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_ward1 gethms_ward1() throws RuntimeException
    {
		return (I_hms_ward1)MTable.get(getCtx(), I_hms_ward1.Table_Name)
			.getPO(gethms_ward1_ID(), get_TrxName());	}

	/** Set hms_ward1 ID.
		@param hms_ward1_ID hms_ward1 ID	  */
	public void sethms_ward1_ID (int hms_ward1_ID)
	{
		if (hms_ward1_ID < 1) 
			set_Value (COLUMNNAME_hms_ward1_ID, null);
		else 
			set_Value (COLUMNNAME_hms_ward1_ID, Integer.valueOf(hms_ward1_ID));
	}

	/** Get hms_ward1 ID.
		@return hms_ward1 ID	  */
	public int gethms_ward1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_ward1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set issue_drugs_btn.
		@param issue_drugs_btn issue_drugs_btn	  */
	public void setissue_drugs_btn (boolean issue_drugs_btn)
	{
		set_Value (COLUMNNAME_issue_drugs_btn, Boolean.valueOf(issue_drugs_btn));
	}

	/** Get issue_drugs_btn.
		@return issue_drugs_btn	  */
	public boolean issue_drugs_btn () 
	{
		Object oo = get_Value(COLUMNNAME_issue_drugs_btn);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set send_request_btn.
		@param send_request_btn send_request_btn	  */
	public void setsend_request_btn (boolean send_request_btn)
	{
		set_Value (COLUMNNAME_send_request_btn, Boolean.valueOf(send_request_btn));
	}

	/** Get send_request_btn.
		@return send_request_btn	  */
	public boolean issend_request_btn () 
	{
		Object oo = get_Value(COLUMNNAME_send_request_btn);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}