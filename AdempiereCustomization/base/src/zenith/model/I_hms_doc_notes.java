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
package zenith.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for hms_doc_notes
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_doc_notes 
{

    /** TableName=hms_doc_notes */
    public static final String Table_Name = "hms_doc_notes";

    /** AD_Table_ID=1000020 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Department.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Department.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (String Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public String getAmount();

    /** Column name attended_time */
    public static final String COLUMNNAME_attended_time = "attended_time";

	/** Set Attended Time.
	  * Attended Time
	  */
	public void setattended_time (Timestamp attended_time);

	/** Get Attended Time.
	  * Attended Time
	  */
	public Timestamp getattended_time();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name diagnosis */
    public static final String COLUMNNAME_diagnosis = "diagnosis";

	/** Set diagnosis	  */
	public void setdiagnosis (String diagnosis);

	/** Get diagnosis	  */
	public String getdiagnosis();

    /** Column name diagnosis2 */
    public static final String COLUMNNAME_diagnosis2 = "diagnosis2";

	/** Set diagnosis2	  */
	public void setdiagnosis2 (int diagnosis2);

	/** Get diagnosis2	  */
	public int getdiagnosis2();

    /** Column name drug_reaction */
    public static final String COLUMNNAME_drug_reaction = "drug_reaction";

	/** Set drug_reaction	  */
	public void setdrug_reaction (String drug_reaction);

	/** Get drug_reaction	  */
	public String getdrug_reaction();

    /** Column name family_medical */
    public static final String COLUMNNAME_family_medical = "family_medical";

	/** Set family_medical	  */
	public void setfamily_medical (String family_medical);

	/** Get family_medical	  */
	public String getfamily_medical();

    /** Column name finding */
    public static final String COLUMNNAME_finding = "finding";

	/** Set finding	  */
	public void setfinding (String finding);

	/** Get finding	  */
	public String getfinding();

    /** Column name general_examination */
    public static final String COLUMNNAME_general_examination = "general_examination";

	/** Set General Examination	  */
	public void setgeneral_examination (String general_examination);

	/** Get General Examination	  */
	public String getgeneral_examination();

    /** Column name hms_doc_notes_ID */
    public static final String COLUMNNAME_hms_doc_notes_ID = "hms_doc_notes_ID";

	/** Set hms_doc_notes ID	  */
	public void sethms_doc_notes_ID (int hms_doc_notes_ID);

	/** Get hms_doc_notes ID	  */
	public int gethms_doc_notes_ID();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name hpi */
    public static final String COLUMNNAME_hpi = "hpi";

	/** Set H.P.I	  */
	public void sethpi (String hpi);

	/** Get H.P.I	  */
	public String gethpi();

    /** Column name impression */
    public static final String COLUMNNAME_impression = "impression";

	/** Set Impression	  */
	public void setimpression (String impression);

	/** Get Impression	  */
	public String getimpression();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name local_examination */
    public static final String COLUMNNAME_local_examination = "local_examination";

	/** Set Local Examination	  */
	public void setlocal_examination (String local_examination);

	/** Get Local Examination	  */
	public String getlocal_examination();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name paid */
    public static final String COLUMNNAME_paid = "paid";

	/** Set paid	  */
	public void setpaid (boolean paid);

	/** Get paid	  */
	public boolean ispaid();

    /** Column name past_dental_medical */
    public static final String COLUMNNAME_past_dental_medical = "past_dental_medical";

	/** Set past_dental_medical	  */
	public void setpast_dental_medical (String past_dental_medical);

	/** Get past_dental_medical	  */
	public String getpast_dental_medical();

    /** Column name past_medical */
    public static final String COLUMNNAME_past_medical = "past_medical";

	/** Set past_medical	  */
	public void setpast_medical (String past_medical);

	/** Get past_medical	  */
	public String getpast_medical();

    /** Column name patient_admit */
    public static final String COLUMNNAME_patient_admit = "patient_admit";

	/** Set patient_admit	  */
	public void setpatient_admit (boolean patient_admit);

	/** Get patient_admit	  */
	public boolean ispatient_admit();

    /** Column name patient_treated */
    public static final String COLUMNNAME_patient_treated = "patient_treated";

	/** Set patient_treated	  */
	public void setpatient_treated (boolean patient_treated);

	/** Get patient_treated	  */
	public boolean ispatient_treated();

    /** Column name pmsh */
    public static final String COLUMNNAME_pmsh = "pmsh";

	/** Set Past Medical & Surgical History	  */
	public void setpmsh (String pmsh);

	/** Get Past Medical & Surgical History	  */
	public String getpmsh();

    /** Column name pre_medication */
    public static final String COLUMNNAME_pre_medication = "pre_medication";

	/** Set Pre-Medication	  */
	public void setpre_medication (String pre_medication);

	/** Get Pre-Medication	  */
	public String getpre_medication();

    /** Column name present_complain */
    public static final String COLUMNNAME_present_complain = "present_complain";

	/** Set Present Complain	  */
	public void setpresent_complain (String present_complain);

	/** Get Present Complain	  */
	public String getpresent_complain();

    /** Column name provdiag */
    public static final String COLUMNNAME_provdiag = "provdiag";

	/** Set Provisional Diagnosis	  */
	public void setprovdiag (String provdiag);

	/** Get Provisional Diagnosis	  */
	public String getprovdiag();

    /** Column name Remarks */
    public static final String COLUMNNAME_Remarks = "Remarks";

	/** Set Remarks.
	  * Remarks
	  */
	public void setRemarks (String Remarks);

	/** Get Remarks.
	  * Remarks
	  */
	public String getRemarks();

    /** Column name symptoms */
    public static final String COLUMNNAME_symptoms = "symptoms";

	/** Set symptoms	  */
	public void setsymptoms (String symptoms);

	/** Get symptoms	  */
	public String getsymptoms();

    /** Column name symptoms2 */
    public static final String COLUMNNAME_symptoms2 = "symptoms2";

	/** Set symptoms2	  */
	public void setsymptoms2 (int symptoms2);

	/** Get symptoms2	  */
	public int getsymptoms2();

    /** Column name treatment_adviced */
    public static final String COLUMNNAME_treatment_adviced = "treatment_adviced";

	/** Set Treatment Adviced	  */
	public void settreatment_adviced (String treatment_adviced);

	/** Get Treatment Adviced	  */
	public String gettreatment_adviced();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
