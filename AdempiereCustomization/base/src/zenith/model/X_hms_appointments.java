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

/** Generated Model for hms_appointments
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_appointments extends PO implements I_hms_appointments, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180507L;

    /** Standard Constructor */
    public X_hms_appointments (Properties ctx, int hms_appointments_ID, String trxName)
    {
      super (ctx, hms_appointments_ID, trxName);
      /** if (hms_appointments_ID == 0)
        {
			sethms_appointments_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_appointments (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_appointments[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Appointment Date.
		@param appoint_date Appointment Date	  */
	public void setappoint_date (Timestamp appoint_date)
	{
		set_Value (COLUMNNAME_appoint_date, appoint_date);
	}

	/** Get Appointment Date.
		@return Appointment Date	  */
	public Timestamp getappoint_date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_appoint_date);
	}

	public org.compiere.model.I_AD_User getappoint_() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getappoint_doc(), get_TrxName());	}

	/** Set Appointment Doctor.
		@param appoint_doc Appointment Doctor	  */
	public void setappoint_doc (int appoint_doc)
	{
		set_Value (COLUMNNAME_appoint_doc, Integer.valueOf(appoint_doc));
	}

	/** Get Appointment Doctor.
		@return Appointment Doctor	  */
	public int getappoint_doc () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_appoint_doc);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Appointment Time.
		@param appointtime Appointment Time	  */
	public void setappointtime (Timestamp appointtime)
	{
		set_Value (COLUMNNAME_appointtime, appointtime);
	}

	/** Get Appointment Time.
		@return Appointment Time	  */
	public Timestamp getappointtime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_appointtime);
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

	/** Set displo.
		@param displo displo	  */
	public void setdisplo (int displo)
	{
		set_Value (COLUMNNAME_displo, Integer.valueOf(displo));
	}

	/** Get displo.
		@return displo	  */
	public int getdisplo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_displo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getdoctor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getdoctor_ID(), get_TrxName());	}

	/** Set Doctor.
		@param doctor_ID Doctor	  */
	public void setdoctor_ID (int doctor_ID)
	{
		if (doctor_ID < 1) 
			set_Value (COLUMNNAME_doctor_ID, null);
		else 
			set_Value (COLUMNNAME_doctor_ID, Integer.valueOf(doctor_ID));
	}

	/** Get Doctor.
		@return Doctor	  */
	public int getdoctor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_doctor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set hms_appointments ID.
		@param hms_appointments_ID hms_appointments ID	  */
	public void sethms_appointments_ID (int hms_appointments_ID)
	{
		if (hms_appointments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_appointments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_appointments_ID, Integer.valueOf(hms_appointments_ID));
	}

	/** Get hms_appointments ID.
		@return hms_appointments ID	  */
	public int gethms_appointments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_appointments_ID);
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

	/** Set honoured_appoint.
		@param honoured_appoint honoured_appoint	  */
	public void sethonoured_appoint (boolean honoured_appoint)
	{
		set_Value (COLUMNNAME_honoured_appoint, Boolean.valueOf(honoured_appoint));
	}

	/** Get honoured_appoint.
		@return honoured_appoint	  */
	public boolean ishonoured_appoint () 
	{
		Object oo = get_Value(COLUMNNAME_honoured_appoint);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set missed_appoint.
		@param missed_appoint missed_appoint	  */
	public void setmissed_appoint (boolean missed_appoint)
	{
		set_Value (COLUMNNAME_missed_appoint, Boolean.valueOf(missed_appoint));
	}

	/** Get missed_appoint.
		@return missed_appoint	  */
	public boolean ismissed_appoint () 
	{
		Object oo = get_Value(COLUMNNAME_missed_appoint);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set pending_appoint.
		@param pending_appoint pending_appoint	  */
	public void setpending_appoint (boolean pending_appoint)
	{
		set_Value (COLUMNNAME_pending_appoint, Boolean.valueOf(pending_appoint));
	}

	/** Get pending_appoint.
		@return pending_appoint	  */
	public boolean ispending_appoint () 
	{
		Object oo = get_Value(COLUMNNAME_pending_appoint);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}