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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for z_bama_patients
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_z_bama_patients extends PO implements I_z_bama_patients, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171117L;

    /** Standard Constructor */
    public X_z_bama_patients (Properties ctx, int z_bama_patients_ID, String trxName)
    {
      super (ctx, z_bama_patients_ID, trxName);
      /** if (z_bama_patients_ID == 0)
        {
			setz_bama_patients_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_bama_patients (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_z_bama_patients[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set patient_dob.
		@param patient_dob patient_dob	  */
	public void setpatient_dob (Timestamp patient_dob)
	{
		set_Value (COLUMNNAME_patient_dob, patient_dob);
	}

	/** Get patient_dob.
		@return patient_dob	  */
	public Timestamp getpatient_dob () 
	{
		return (Timestamp)get_Value(COLUMNNAME_patient_dob);
	}

	/** Set patient_fullname.
		@param patient_fullname patient_fullname	  */
	public void setpatient_fullname (String patient_fullname)
	{
		set_Value (COLUMNNAME_patient_fullname, patient_fullname);
	}

	/** Get patient_fullname.
		@return patient_fullname	  */
	public String getpatient_fullname () 
	{
		return (String)get_Value(COLUMNNAME_patient_fullname);
	}

	/** Set patient_gender.
		@param patient_gender patient_gender	  */
	public void setpatient_gender (String patient_gender)
	{
		set_Value (COLUMNNAME_patient_gender, patient_gender);
	}

	/** Get patient_gender.
		@return patient_gender	  */
	public String getpatient_gender () 
	{
		return (String)get_Value(COLUMNNAME_patient_gender);
	}

	/** Set patient_natioanalid.
		@param patient_natioanalid patient_natioanalid	  */
	public void setpatient_natioanalid (String patient_natioanalid)
	{
		set_Value (COLUMNNAME_patient_natioanalid, patient_natioanalid);
	}

	/** Get patient_natioanalid.
		@return patient_natioanalid	  */
	public String getpatient_natioanalid () 
	{
		return (String)get_Value(COLUMNNAME_patient_natioanalid);
	}

	/** Set patient_nhifno.
		@param patient_nhifno patient_nhifno	  */
	public void setpatient_nhifno (String patient_nhifno)
	{
		set_Value (COLUMNNAME_patient_nhifno, patient_nhifno);
	}

	/** Get patient_nhifno.
		@return patient_nhifno	  */
	public String getpatient_nhifno () 
	{
		return (String)get_Value(COLUMNNAME_patient_nhifno);
	}

	/** Set patient_no.
		@param patient_no patient_no	  */
	public void setpatient_no (String patient_no)
	{
		set_Value (COLUMNNAME_patient_no, patient_no);
	}

	/** Get patient_no.
		@return patient_no	  */
	public String getpatient_no () 
	{
		return (String)get_Value(COLUMNNAME_patient_no);
	}

	/** Set patient_reg.
		@param patient_reg patient_reg	  */
	public void setpatient_reg (Timestamp patient_reg)
	{
		set_Value (COLUMNNAME_patient_reg, patient_reg);
	}

	/** Get patient_reg.
		@return patient_reg	  */
	public Timestamp getpatient_reg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_patient_reg);
	}

	/** Set patient_tel.
		@param patient_tel patient_tel	  */
	public void setpatient_tel (String patient_tel)
	{
		set_Value (COLUMNNAME_patient_tel, patient_tel);
	}

	/** Get patient_tel.
		@return patient_tel	  */
	public String getpatient_tel () 
	{
		return (String)get_Value(COLUMNNAME_patient_tel);
	}

	/** Set petient_pricelistid.
		@param petient_pricelistid petient_pricelistid	  */
	public void setpetient_pricelistid (int petient_pricelistid)
	{
		set_Value (COLUMNNAME_petient_pricelistid, Integer.valueOf(petient_pricelistid));
	}

	/** Get petient_pricelistid.
		@return petient_pricelistid	  */
	public int getpetient_pricelistid () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_petient_pricelistid);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set z_bama_patients ID.
		@param z_bama_patients_ID z_bama_patients ID	  */
	public void setz_bama_patients_ID (int z_bama_patients_ID)
	{
		if (z_bama_patients_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_z_bama_patients_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_z_bama_patients_ID, Integer.valueOf(z_bama_patients_ID));
	}

	/** Get z_bama_patients ID.
		@return z_bama_patients ID	  */
	public int getz_bama_patients_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_bama_patients_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}