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

/** Generated Model for hms_pallergies
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_pallergies extends PO implements I_hms_pallergies, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180125L;

    /** Standard Constructor */
    public X_hms_pallergies (Properties ctx, int hms_pallergies_ID, String trxName)
    {
      super (ctx, hms_pallergies_ID, trxName);
      /** if (hms_pallergies_ID == 0)
        {
			sethms_pallergies_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_pallergies (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_pallergies[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Allergy Name.
		@param allname Allergy Name	  */
	public void setallname (String allname)
	{
		set_Value (COLUMNNAME_allname, allname);
	}

	/** Get Allergy Name.
		@return Allergy Name	  */
	public String getallname () 
	{
		return (String)get_Value(COLUMNNAME_allname);
	}

	/** Set hms_pallergies ID.
		@param hms_pallergies_ID hms_pallergies ID	  */
	public void sethms_pallergies_ID (int hms_pallergies_ID)
	{
		if (hms_pallergies_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_pallergies_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_pallergies_ID, Integer.valueOf(hms_pallergies_ID));
	}

	/** Get hms_pallergies ID.
		@return hms_pallergies ID	  */
	public int gethms_pallergies_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_pallergies_ID);
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

	/** Set Reaction.
		@param reaction Reaction	  */
	public void setreaction (String reaction)
	{
		set_Value (COLUMNNAME_reaction, reaction);
	}

	/** Get Reaction.
		@return Reaction	  */
	public String getreaction () 
	{
		return (String)get_Value(COLUMNNAME_reaction);
	}
}