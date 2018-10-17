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

/** Generated Model for hms_prescription_line
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_prescription_line extends PO implements I_hms_prescription_line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170922L;

    /** Standard Constructor */
    public X_hms_prescription_line (Properties ctx, int hms_prescription_line_ID, String trxName)
    {
      super (ctx, hms_prescription_line_ID, trxName);
      /** if (hms_prescription_line_ID == 0)
        {
			setC_BPartner_ID (0);
// @C_BPartner_ID@
			sethms_prescription_ID (0);
			sethms_prescription_line_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_prescription_line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_prescription_line[")
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

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Days.
		@param days Days	  */
	public void setdays (BigDecimal days)
	{
		set_Value (COLUMNNAME_days, days);
	}

	/** Get Days.
		@return Days	  */
	public BigDecimal getdays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_days);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Dosage.
		@param dosage Dosage	  */
	public void setdosage (BigDecimal dosage)
	{
		set_Value (COLUMNNAME_dosage, dosage);
	}

	/** Get Dosage.
		@return Dosage	  */
	public BigDecimal getdosage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_dosage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Frequency.
		@param freq Frequency	  */
	public void setfreq (BigDecimal freq)
	{
		set_Value (COLUMNNAME_freq, freq);
	}

	/** Get Frequency.
		@return Frequency	  */
	public BigDecimal getfreq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_freq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_hms_prescription gethms_prescription() throws RuntimeException
    {
		return (I_hms_prescription)MTable.get(getCtx(), I_hms_prescription.Table_Name)
			.getPO(gethms_prescription_ID(), get_TrxName());	}

	/** Set hms_prescription ID.
		@param hms_prescription_ID hms_prescription ID	  */
	public void sethms_prescription_ID (int hms_prescription_ID)
	{
		if (hms_prescription_ID < 1) 
			set_Value (COLUMNNAME_hms_prescription_ID, null);
		else 
			set_Value (COLUMNNAME_hms_prescription_ID, Integer.valueOf(hms_prescription_ID));
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

	/** Set hms_prescription_line ID.
		@param hms_prescription_line_ID hms_prescription_line ID	  */
	public void sethms_prescription_line_ID (int hms_prescription_line_ID)
	{
		if (hms_prescription_line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_prescription_line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_prescription_line_ID, Integer.valueOf(hms_prescription_line_ID));
	}

	/** Get hms_prescription_line ID.
		@return hms_prescription_line ID	  */
	public int gethms_prescription_line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_prescription_line_ID);
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

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
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

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList_Version getM_PriceList_Version() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList_Version)MTable.get(getCtx(), org.compiere.model.I_M_PriceList_Version.Table_Name)
			.getPO(getM_PriceList_Version_ID(), get_TrxName());	}

	/** Set Price List Version.
		@param M_PriceList_Version_ID 
		Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, Integer.valueOf(M_PriceList_Version_ID));
	}

	/** Get Price List Version.
		@return Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Version_ID);
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

	/** Set Prescribed.
		@param prescribed Prescribed	  */
	public void setprescribed (boolean prescribed)
	{
		set_Value (COLUMNNAME_prescribed, Boolean.valueOf(prescribed));
	}

	/** Get Prescribed.
		@return Prescribed	  */
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

	/** Set Units Per Frequency.
		@param units_per_freq Units Per Frequency	  */
	public void setunits_per_freq (BigDecimal units_per_freq)
	{
		set_Value (COLUMNNAME_units_per_freq, units_per_freq);
	}

	/** Get Units Per Frequency.
		@return Units Per Frequency	  */
	public BigDecimal getunits_per_freq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_units_per_freq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}