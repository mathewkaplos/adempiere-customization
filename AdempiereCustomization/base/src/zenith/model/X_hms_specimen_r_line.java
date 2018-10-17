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

/** Generated Model for hms_specimen_r_line
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_specimen_r_line extends PO implements I_hms_specimen_r_line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170521L;

    /** Standard Constructor */
    public X_hms_specimen_r_line (Properties ctx, int hms_specimen_r_line_ID, String trxName)
    {
      super (ctx, hms_specimen_r_line_ID, trxName);
      /** if (hms_specimen_r_line_ID == 0)
        {
			setC_BPartner_ID (0);
			sethms_specimen_r_line_ID (0);
			sethms_specimen_requests_ID (0);
			sethms_specimens_ID (0);
			sethms_test_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_specimen_r_line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_specimen_r_line[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_specimen_r_line ID.
		@param hms_specimen_r_line_ID hms_specimen_r_line ID	  */
	public void sethms_specimen_r_line_ID (int hms_specimen_r_line_ID)
	{
		if (hms_specimen_r_line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_specimen_r_line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_specimen_r_line_ID, Integer.valueOf(hms_specimen_r_line_ID));
	}

	/** Get hms_specimen_r_line ID.
		@return hms_specimen_r_line ID	  */
	public int gethms_specimen_r_line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_specimen_r_line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_specimen_requests gethms_specimen_requests() throws RuntimeException
    {
		return (I_hms_specimen_requests)MTable.get(getCtx(), I_hms_specimen_requests.Table_Name)
			.getPO(gethms_specimen_requests_ID(), get_TrxName());	}

	/** Set hms_specimen_requests ID.
		@param hms_specimen_requests_ID hms_specimen_requests ID	  */
	public void sethms_specimen_requests_ID (int hms_specimen_requests_ID)
	{
		if (hms_specimen_requests_ID < 1) 
			set_Value (COLUMNNAME_hms_specimen_requests_ID, null);
		else 
			set_Value (COLUMNNAME_hms_specimen_requests_ID, Integer.valueOf(hms_specimen_requests_ID));
	}

	/** Get hms_specimen_requests ID.
		@return hms_specimen_requests ID	  */
	public int gethms_specimen_requests_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_specimen_requests_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_specimens gethms_specimens() throws RuntimeException
    {
		return (I_hms_specimens)MTable.get(getCtx(), I_hms_specimens.Table_Name)
			.getPO(gethms_specimens_ID(), get_TrxName());	}

	/** Set hms_specimens ID.
		@param hms_specimens_ID hms_specimens ID	  */
	public void sethms_specimens_ID (int hms_specimens_ID)
	{
		if (hms_specimens_ID < 1) 
			set_Value (COLUMNNAME_hms_specimens_ID, null);
		else 
			set_Value (COLUMNNAME_hms_specimens_ID, Integer.valueOf(hms_specimens_ID));
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

	/** Set percencentage_done.
		@param percencentage_done percencentage_done	  */
	public void setpercencentage_done (BigDecimal percencentage_done)
	{
		set_Value (COLUMNNAME_percencentage_done, percencentage_done);
	}

	/** Get percencentage_done.
		@return percencentage_done	  */
	public BigDecimal getpercencentage_done () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_percencentage_done);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}