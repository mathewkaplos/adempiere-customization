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

/** Generated Model for hms_doc_notes
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_doc_notes extends PO implements I_hms_doc_notes, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180721L;

    /** Standard Constructor */
    public X_hms_doc_notes (Properties ctx, int hms_doc_notes_ID, String trxName)
    {
      super (ctx, hms_doc_notes_ID, trxName);
      /** if (hms_doc_notes_ID == 0)
        {
			sethms_doc_notes_ID (0);
			setpaid (false);
			setpatient_admit (false);
			setpatient_treated (false);
        } */
    }

    /** Load Constructor */
    public X_hms_doc_notes (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_hms_doc_notes[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (String Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public String getAmount () 
	{
		return (String)get_Value(COLUMNNAME_Amount);
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

	/** Set diagnosis.
		@param diagnosis diagnosis	  */
	public void setdiagnosis (String diagnosis)
	{
		set_Value (COLUMNNAME_diagnosis, diagnosis);
	}

	/** Get diagnosis.
		@return diagnosis	  */
	public String getdiagnosis () 
	{
		return (String)get_Value(COLUMNNAME_diagnosis);
	}

	/** Set diagnosis2.
		@param diagnosis2 diagnosis2	  */
	public void setdiagnosis2 (int diagnosis2)
	{
		set_Value (COLUMNNAME_diagnosis2, Integer.valueOf(diagnosis2));
	}

	/** Get diagnosis2.
		@return diagnosis2	  */
	public int getdiagnosis2 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_diagnosis2);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set drug_reaction.
		@param drug_reaction drug_reaction	  */
	public void setdrug_reaction (String drug_reaction)
	{
		set_Value (COLUMNNAME_drug_reaction, drug_reaction);
	}

	/** Get drug_reaction.
		@return drug_reaction	  */
	public String getdrug_reaction () 
	{
		return (String)get_Value(COLUMNNAME_drug_reaction);
	}

	/** Set family_medical.
		@param family_medical family_medical	  */
	public void setfamily_medical (String family_medical)
	{
		set_Value (COLUMNNAME_family_medical, family_medical);
	}

	/** Get family_medical.
		@return family_medical	  */
	public String getfamily_medical () 
	{
		return (String)get_Value(COLUMNNAME_family_medical);
	}

	/** Set finding.
		@param finding finding	  */
	public void setfinding (String finding)
	{
		set_Value (COLUMNNAME_finding, finding);
	}

	/** Get finding.
		@return finding	  */
	public String getfinding () 
	{
		return (String)get_Value(COLUMNNAME_finding);
	}

	/** Set General Examination.
		@param general_examination General Examination	  */
	public void setgeneral_examination (String general_examination)
	{
		set_Value (COLUMNNAME_general_examination, general_examination);
	}

	/** Get General Examination.
		@return General Examination	  */
	public String getgeneral_examination () 
	{
		return (String)get_Value(COLUMNNAME_general_examination);
	}

	/** Set hms_doc_notes ID.
		@param hms_doc_notes_ID hms_doc_notes ID	  */
	public void sethms_doc_notes_ID (int hms_doc_notes_ID)
	{
		if (hms_doc_notes_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_doc_notes_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_doc_notes_ID, Integer.valueOf(hms_doc_notes_ID));
	}

	/** Get hms_doc_notes ID.
		@return hms_doc_notes ID	  */
	public int gethms_doc_notes_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_doc_notes_ID);
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

	/** Set H.P.I.
		@param hpi H.P.I	  */
	public void sethpi (String hpi)
	{
		set_Value (COLUMNNAME_hpi, hpi);
	}

	/** Get H.P.I.
		@return H.P.I	  */
	public String gethpi () 
	{
		return (String)get_Value(COLUMNNAME_hpi);
	}

	/** Set Impression.
		@param impression Impression	  */
	public void setimpression (String impression)
	{
		set_Value (COLUMNNAME_impression, impression);
	}

	/** Get Impression.
		@return Impression	  */
	public String getimpression () 
	{
		return (String)get_Value(COLUMNNAME_impression);
	}

	/** Set Local Examination.
		@param local_examination Local Examination	  */
	public void setlocal_examination (String local_examination)
	{
		set_Value (COLUMNNAME_local_examination, local_examination);
	}

	/** Get Local Examination.
		@return Local Examination	  */
	public String getlocal_examination () 
	{
		return (String)get_Value(COLUMNNAME_local_examination);
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

	/** Set paid.
		@param paid paid	  */
	public void setpaid (boolean paid)
	{
		set_Value (COLUMNNAME_paid, Boolean.valueOf(paid));
	}

	/** Get paid.
		@return paid	  */
	public boolean ispaid () 
	{
		Object oo = get_Value(COLUMNNAME_paid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set past_dental_medical.
		@param past_dental_medical past_dental_medical	  */
	public void setpast_dental_medical (String past_dental_medical)
	{
		set_Value (COLUMNNAME_past_dental_medical, past_dental_medical);
	}

	/** Get past_dental_medical.
		@return past_dental_medical	  */
	public String getpast_dental_medical () 
	{
		return (String)get_Value(COLUMNNAME_past_dental_medical);
	}

	/** Set past_medical.
		@param past_medical past_medical	  */
	public void setpast_medical (String past_medical)
	{
		set_Value (COLUMNNAME_past_medical, past_medical);
	}

	/** Get past_medical.
		@return past_medical	  */
	public String getpast_medical () 
	{
		return (String)get_Value(COLUMNNAME_past_medical);
	}

	/** Set patient_admit.
		@param patient_admit patient_admit	  */
	public void setpatient_admit (boolean patient_admit)
	{
		set_Value (COLUMNNAME_patient_admit, Boolean.valueOf(patient_admit));
	}

	/** Get patient_admit.
		@return patient_admit	  */
	public boolean ispatient_admit () 
	{
		Object oo = get_Value(COLUMNNAME_patient_admit);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set patient_treated.
		@param patient_treated patient_treated	  */
	public void setpatient_treated (boolean patient_treated)
	{
		set_Value (COLUMNNAME_patient_treated, Boolean.valueOf(patient_treated));
	}

	/** Get patient_treated.
		@return patient_treated	  */
	public boolean ispatient_treated () 
	{
		Object oo = get_Value(COLUMNNAME_patient_treated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Past Medical & Surgical History.
		@param pmsh Past Medical & Surgical History	  */
	public void setpmsh (String pmsh)
	{
		set_Value (COLUMNNAME_pmsh, pmsh);
	}

	/** Get Past Medical & Surgical History.
		@return Past Medical & Surgical History	  */
	public String getpmsh () 
	{
		return (String)get_Value(COLUMNNAME_pmsh);
	}

	/** Set Pre-Medication.
		@param pre_medication Pre-Medication	  */
	public void setpre_medication (String pre_medication)
	{
		set_Value (COLUMNNAME_pre_medication, pre_medication);
	}

	/** Get Pre-Medication.
		@return Pre-Medication	  */
	public String getpre_medication () 
	{
		return (String)get_Value(COLUMNNAME_pre_medication);
	}

	/** Set Present Complain.
		@param present_complain Present Complain	  */
	public void setpresent_complain (String present_complain)
	{
		set_Value (COLUMNNAME_present_complain, present_complain);
	}

	/** Get Present Complain.
		@return Present Complain	  */
	public String getpresent_complain () 
	{
		return (String)get_Value(COLUMNNAME_present_complain);
	}

	/** Set Provisional Diagnosis.
		@param provdiag Provisional Diagnosis	  */
	public void setprovdiag (String provdiag)
	{
		set_Value (COLUMNNAME_provdiag, provdiag);
	}

	/** Get Provisional Diagnosis.
		@return Provisional Diagnosis	  */
	public String getprovdiag () 
	{
		return (String)get_Value(COLUMNNAME_provdiag);
	}

	/** Set Remarks.
		@param Remarks 
		Remarks
	  */
	public void setRemarks (String Remarks)
	{
		set_Value (COLUMNNAME_Remarks, Remarks);
	}

	/** Get Remarks.
		@return Remarks
	  */
	public String getRemarks () 
	{
		return (String)get_Value(COLUMNNAME_Remarks);
	}

	/** Set symptoms.
		@param symptoms symptoms	  */
	public void setsymptoms (String symptoms)
	{
		set_Value (COLUMNNAME_symptoms, symptoms);
	}

	/** Get symptoms.
		@return symptoms	  */
	public String getsymptoms () 
	{
		return (String)get_Value(COLUMNNAME_symptoms);
	}

	/** Set symptoms2.
		@param symptoms2 symptoms2	  */
	public void setsymptoms2 (int symptoms2)
	{
		set_Value (COLUMNNAME_symptoms2, Integer.valueOf(symptoms2));
	}

	/** Get symptoms2.
		@return symptoms2	  */
	public int getsymptoms2 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_symptoms2);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment Adviced.
		@param treatment_adviced Treatment Adviced	  */
	public void settreatment_adviced (String treatment_adviced)
	{
		set_Value (COLUMNNAME_treatment_adviced, treatment_adviced);
	}

	/** Get Treatment Adviced.
		@return Treatment Adviced	  */
	public String gettreatment_adviced () 
	{
		return (String)get_Value(COLUMNNAME_treatment_adviced);
	}
}