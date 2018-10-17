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

/** Generated Model for hms_prescription
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_prescription extends PO implements I_hms_prescription, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170608L;

    /** Standard Constructor */
    public X_hms_prescription (Properties ctx, int hms_prescription_ID, String trxName)
    {
      super (ctx, hms_prescription_ID, trxName);
      /** if (hms_prescription_ID == 0)
        {
			sethms_prescription_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_prescription (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_prescription[")
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set hms_prescription ID.
		@param hms_prescription_ID hms_prescription ID	  */
	public void sethms_prescription_ID (int hms_prescription_ID)
	{
		if (hms_prescription_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_prescription_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_prescription_ID, Integer.valueOf(hms_prescription_ID));
	}

	/** Get hms_prescription ID.
		@return hms_prescription ID	  */
	public int gethms_prescription_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_prescription_ID);
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

	/** Set lineamt.
		@param lineamt lineamt	  */
	public void setlineamt (BigDecimal lineamt)
	{
		set_Value (COLUMNNAME_lineamt, lineamt);
	}

	/** Get lineamt.
		@return lineamt	  */
	public BigDecimal getlineamt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_lineamt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set prescribe.
		@param prescribe prescribe	  */
	public void setprescribe (String prescribe)
	{
		set_Value (COLUMNNAME_prescribe, prescribe);
	}

	/** Get prescribe.
		@return prescribe	  */
	public String getprescribe () 
	{
		return (String)get_Value(COLUMNNAME_prescribe);
	}

	/** Set prescribed.
		@param prescribed prescribed	  */
	public void setprescribed (boolean prescribed)
	{
		set_Value (COLUMNNAME_prescribed, Boolean.valueOf(prescribed));
	}

	/** Get prescribed.
		@return prescribed	  */
	public boolean isprescribed () 
	{
		Object oo = get_Value(COLUMNNAME_prescribed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}