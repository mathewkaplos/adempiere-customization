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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for hms_lab_results
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_lab_results extends PO implements I_hms_lab_results, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170713L;

    /** Standard Constructor */
    public X_hms_lab_results (Properties ctx, int hms_lab_results_ID, String trxName)
    {
      super (ctx, hms_lab_results_ID, trxName);
      /** if (hms_lab_results_ID == 0)
        {
			setC_BPartner_ID (0);
			sethms_lab_results_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_lab_results (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_lab_results[")
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

	/** Set done.
		@param done done	  */
	public void setdone (boolean done)
	{
		set_Value (COLUMNNAME_done, Boolean.valueOf(done));
	}

	/** Get done.
		@return done	  */
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

	/** Set Done Date.
		@param done_date 
		Done Date
	  */
	public void setdone_date (Timestamp done_date)
	{
		set_Value (COLUMNNAME_done_date, done_date);
	}

	/** Get Done Date.
		@return Done Date
	  */
	public Timestamp getdone_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_done_date);
	}

	/** Set Done Time.
		@param done_time 
		Done Time
	  */
	public void setdone_time (Timestamp done_time)
	{
		set_Value (COLUMNNAME_done_time, done_time);
	}

	/** Get Done Time.
		@return Done Time
	  */
	public Timestamp getdone_time () 
	{
		return (Timestamp)get_Value(COLUMNNAME_done_time);
	}

	/** Set Final Results.
		@param final_results 
		Final Results
	  */
	public void setfinal_results (String final_results)
	{
		set_Value (COLUMNNAME_final_results, final_results);
	}

	/** Get Final Results.
		@return Final Results
	  */
	public String getfinal_results () 
	{
		return (String)get_Value(COLUMNNAME_final_results);
	}

	/** Set hms_lab_results ID.
		@param hms_lab_results_ID hms_lab_results ID	  */
	public void sethms_lab_results_ID (int hms_lab_results_ID)
	{
		if (hms_lab_results_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_lab_results_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_lab_results_ID, Integer.valueOf(hms_lab_results_ID));
	}

	/** Get hms_lab_results ID.
		@return hms_lab_results ID	  */
	public int gethms_lab_results_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_lab_results_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_parameters gethms_parameters() throws RuntimeException
    {
		return (I_hms_parameters)MTable.get(getCtx(), I_hms_parameters.Table_Name)
			.getPO(gethms_parameters_ID(), get_TrxName());	}

	/** Set Parameter.
		@param hms_parameters_ID 
		Lab Test Parameter
	  */
	public void sethms_parameters_ID (int hms_parameters_ID)
	{
		if (hms_parameters_ID < 1) 
			set_Value (COLUMNNAME_hms_parameters_ID, null);
		else 
			set_Value (COLUMNNAME_hms_parameters_ID, Integer.valueOf(hms_parameters_ID));
	}

	/** Get Parameter.
		@return Lab Test Parameter
	  */
	public int gethms_parameters_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_parameters_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_specimen_r_line gethms_specimen_r_line() throws RuntimeException
    {
		return (I_hms_specimen_r_line)MTable.get(getCtx(), I_hms_specimen_r_line.Table_Name)
			.getPO(gethms_specimen_r_line_ID(), get_TrxName());	}

	/** Set hms_specimen_r_line ID.
		@param hms_specimen_r_line_ID hms_specimen_r_line ID	  */
	public void sethms_specimen_r_line_ID (int hms_specimen_r_line_ID)
	{
		if (hms_specimen_r_line_ID < 1) 
			set_Value (COLUMNNAME_hms_specimen_r_line_ID, null);
		else 
			set_Value (COLUMNNAME_hms_specimen_r_line_ID, Integer.valueOf(hms_specimen_r_line_ID));
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

	/** Set Test.
		@param hms_test_ID 
		Lab Test 
	  */
	public void sethms_test_ID (int hms_test_ID)
	{
		if (hms_test_ID < 1) 
			set_Value (COLUMNNAME_hms_test_ID, null);
		else 
			set_Value (COLUMNNAME_hms_test_ID, Integer.valueOf(hms_test_ID));
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

	/** Set Range.
		@param range 
		Normal Range
	  */
	public void setrange (String range)
	{
		set_Value (COLUMNNAME_range, range);
	}

	/** Get Range.
		@return Normal Range
	  */
	public String getrange () 
	{
		return (String)get_Value(COLUMNNAME_range);
	}

	/** Set results.
		@param results results	  */
	public void setresults (String results)
	{
		set_Value (COLUMNNAME_results, results);
	}

	/** Get results.
		@return results	  */
	public String getresults () 
	{
		return (String)get_Value(COLUMNNAME_results);
	}

	/** Set Units.
		@param units 
		Parameter Units
	  */
	public void setunits (String units)
	{
		set_Value (COLUMNNAME_units, units);
	}

	/** Get Units.
		@return Parameter Units
	  */
	public String getunits () 
	{
		return (String)get_Value(COLUMNNAME_units);
	}

	/** Set value1.
		@param value1 value1	  */
	public void setvalue1 (BigDecimal value1)
	{
		set_Value (COLUMNNAME_value1, value1);
	}

	/** Get value1.
		@return value1	  */
	public BigDecimal getvalue1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_value1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}