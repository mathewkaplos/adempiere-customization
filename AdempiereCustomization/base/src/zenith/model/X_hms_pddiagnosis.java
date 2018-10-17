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

/** Generated Model for hms_pddiagnosis
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_pddiagnosis extends PO implements I_hms_pddiagnosis, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180315L;

    /** Standard Constructor */
    public X_hms_pddiagnosis (Properties ctx, int hms_pddiagnosis_ID, String trxName)
    {
      super (ctx, hms_pddiagnosis_ID, trxName);
      /** if (hms_pddiagnosis_ID == 0)
        {
			sethms_pddiagnosis_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_pddiagnosis (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_pddiagnosis[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_hms_diagnosis gethms_diagnosis() throws RuntimeException
    {
		return (I_hms_diagnosis)MTable.get(getCtx(), I_hms_diagnosis.Table_Name)
			.getPO(gethms_diagnosis_ID(), get_TrxName());	}

	/** Set Diagnosis.
		@param hms_diagnosis_ID Diagnosis	  */
	public void sethms_diagnosis_ID (int hms_diagnosis_ID)
	{
		if (hms_diagnosis_ID < 1) 
			set_Value (COLUMNNAME_hms_diagnosis_ID, null);
		else 
			set_Value (COLUMNNAME_hms_diagnosis_ID, Integer.valueOf(hms_diagnosis_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis	  */
	public int gethms_diagnosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_diagnosisg gethms_diagnosisg() throws RuntimeException
    {
		return (I_hms_diagnosisg)MTable.get(getCtx(), I_hms_diagnosisg.Table_Name)
			.getPO(gethms_diagnosisg_ID(), get_TrxName());	}

	/** Set Diagnosis Group.
		@param hms_diagnosisg_ID Diagnosis Group	  */
	public void sethms_diagnosisg_ID (int hms_diagnosisg_ID)
	{
		if (hms_diagnosisg_ID < 1) 
			set_Value (COLUMNNAME_hms_diagnosisg_ID, null);
		else 
			set_Value (COLUMNNAME_hms_diagnosisg_ID, Integer.valueOf(hms_diagnosisg_ID));
	}

	/** Get Diagnosis Group.
		@return Diagnosis Group	  */
	public int gethms_diagnosisg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosisg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis Sub Group.
		@param hms_diagnosissubg_ID 
		Diagnosis Sub Group
	  */
	public void sethms_diagnosissubg_ID (int hms_diagnosissubg_ID)
	{
		if (hms_diagnosissubg_ID < 1) 
			set_Value (COLUMNNAME_hms_diagnosissubg_ID, null);
		else 
			set_Value (COLUMNNAME_hms_diagnosissubg_ID, Integer.valueOf(hms_diagnosissubg_ID));
	}

	/** Get Diagnosis Sub Group.
		@return Diagnosis Sub Group
	  */
	public int gethms_diagnosissubg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_diagnosissubg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_pddiagnosis ID.
		@param hms_pddiagnosis_ID hms_pddiagnosis ID	  */
	public void sethms_pddiagnosis_ID (int hms_pddiagnosis_ID)
	{
		if (hms_pddiagnosis_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_pddiagnosis_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_pddiagnosis_ID, Integer.valueOf(hms_pddiagnosis_ID));
	}

	/** Get hms_pddiagnosis ID.
		@return hms_pddiagnosis ID	  */
	public int gethms_pddiagnosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_pddiagnosis_ID);
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

	public I_zhms_diagnosis getzhms_diagnosis() throws RuntimeException
    {
		return (I_zhms_diagnosis)MTable.get(getCtx(), I_zhms_diagnosis.Table_Name)
			.getPO(getzhms_diagnosis_ID(), get_TrxName());	}

	/** Set zhms_diagnosis ID.
		@param zhms_diagnosis_ID zhms_diagnosis ID	  */
	public void setzhms_diagnosis_ID (int zhms_diagnosis_ID)
	{
		if (zhms_diagnosis_ID < 1) 
			set_Value (COLUMNNAME_zhms_diagnosis_ID, null);
		else 
			set_Value (COLUMNNAME_zhms_diagnosis_ID, Integer.valueOf(zhms_diagnosis_ID));
	}

	/** Get zhms_diagnosis ID.
		@return zhms_diagnosis ID	  */
	public int getzhms_diagnosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_zhms_diagnosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}