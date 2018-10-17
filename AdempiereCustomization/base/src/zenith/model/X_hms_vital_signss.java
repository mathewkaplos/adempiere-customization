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

/** Generated Model for hms_vital_signss
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_vital_signss extends PO implements I_hms_vital_signss, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171018L;

    /** Standard Constructor */
    public X_hms_vital_signss (Properties ctx, int hms_vital_signss_ID, String trxName)
    {
      super (ctx, hms_vital_signss_ID, trxName);
      /** if (hms_vital_signss_ID == 0)
        {
			sethms_vital_signss_ID (0);
        } */
    }

    /** Load Constructor */
    public X_hms_vital_signss (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_vital_signss[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Age.
		@param Age 
		Age of a person
	  */
	public void setAge (String Age)
	{
		set_Value (COLUMNNAME_Age, Age);
	}

	/** Get Age.
		@return Age of a person
	  */
	public String getAge () 
	{
		return (String)get_Value(COLUMNNAME_Age);
	}

	/** Set Attended Time.
		@param attended_time 
		Attended Time
	  */
	public void setattended_time (Timestamp attended_time)
	{
		set_Value (COLUMNNAME_attended_time, attended_time);
	}

	/** Get Attended Time.
		@return Attended Time
	  */
	public Timestamp getattended_time () 
	{
		return (Timestamp)get_Value(COLUMNNAME_attended_time);
	}

	/** Set BMI.
		@param bmi BMI	  */
	public void setbmi (BigDecimal bmi)
	{
		set_Value (COLUMNNAME_bmi, bmi);
	}

	/** Get BMI.
		@return BMI	  */
	public BigDecimal getbmi () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_bmi);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Diastolic.
		@param bp_diastolic Diastolic	  */
	public void setbp_diastolic (int bp_diastolic)
	{
		set_Value (COLUMNNAME_bp_diastolic, Integer.valueOf(bp_diastolic));
	}

	/** Get Diastolic.
		@return Diastolic	  */
	public int getbp_diastolic () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bp_diastolic);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Systolic.
		@param bp_systolic Systolic	  */
	public void setbp_systolic (int bp_systolic)
	{
		set_Value (COLUMNNAME_bp_systolic, Integer.valueOf(bp_systolic));
	}

	/** Get Systolic.
		@return Systolic	  */
	public int getbp_systolic () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bp_systolic);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set FHR.
		@param fhr FHR	  */
	public void setfhr (int fhr)
	{
		set_Value (COLUMNNAME_fhr, Integer.valueOf(fhr));
	}

	/** Get FHR.
		@return FHR	  */
	public int getfhr () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_fhr);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Height.
		@param height Height	  */
	public void setheight (BigDecimal height)
	{
		set_Value (COLUMNNAME_height, height);
	}

	/** Get Height.
		@return Height	  */
	public BigDecimal getheight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_height);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set hms_vital_signss ID.
		@param hms_vital_signss_ID hms_vital_signss ID	  */
	public void sethms_vital_signss_ID (int hms_vital_signss_ID)
	{
		if (hms_vital_signss_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_vital_signss_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_vital_signss_ID, Integer.valueOf(hms_vital_signss_ID));
	}

	/** Get hms_vital_signss ID.
		@return hms_vital_signss ID	  */
	public int gethms_vital_signss_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_vital_signss_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set lmp.
		@param lmp lmp	  */
	public void setlmp (Timestamp lmp)
	{
		set_Value (COLUMNNAME_lmp, lmp);
	}

	/** Get lmp.
		@return lmp	  */
	public Timestamp getlmp () 
	{
		return (Timestamp)get_Value(COLUMNNAME_lmp);
	}

	/** Set notavail.
		@param notavail notavail	  */
	public void setnotavail (boolean notavail)
	{
		set_Value (COLUMNNAME_notavail, Boolean.valueOf(notavail));
	}

	/** Get notavail.
		@return notavail	  */
	public boolean isnotavail () 
	{
		Object oo = get_Value(COLUMNNAME_notavail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Temperature.
		@param ptemp Temperature	  */
	public void setptemp (BigDecimal ptemp)
	{
		set_Value (COLUMNNAME_ptemp, ptemp);
	}

	/** Get Temperature.
		@return Temperature	  */
	public BigDecimal getptemp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ptemp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pulse.
		@param pulse Pulse	  */
	public void setpulse (int pulse)
	{
		set_Value (COLUMNNAME_pulse, Integer.valueOf(pulse));
	}

	/** Get Pulse.
		@return Pulse	  */
	public int getpulse () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_pulse);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Respiratory.
		@param respiratory Respiratory	  */
	public void setrespiratory (int respiratory)
	{
		set_Value (COLUMNNAME_respiratory, Integer.valueOf(respiratory));
	}

	/** Get Respiratory.
		@return Respiratory	  */
	public int getrespiratory () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_respiratory);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taken By.
		@param taken_by_ID Taken By	  */
	public void settaken_by_ID (int taken_by_ID)
	{
		if (taken_by_ID < 1) 
			set_Value (COLUMNNAME_taken_by_ID, null);
		else 
			set_Value (COLUMNNAME_taken_by_ID, Integer.valueOf(taken_by_ID));
	}

	/** Get Taken By.
		@return Taken By	  */
	public int gettaken_by_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_taken_by_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set treatment_id.
		@param treatment_id treatment_id	  */
	public void settreatment_id (int treatment_id)
	{
		set_Value (COLUMNNAME_treatment_id, Integer.valueOf(treatment_id));
	}

	/** Get treatment_id.
		@return treatment_id	  */
	public int gettreatment_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_treatment_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (BigDecimal Weight)
	{
		set_Value (COLUMNNAME_Weight, Weight);
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public BigDecimal getWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}