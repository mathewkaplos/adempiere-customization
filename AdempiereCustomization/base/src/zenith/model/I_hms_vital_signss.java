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

/** Generated Interface for hms_vital_signss
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_vital_signss 
{

    /** TableName=hms_vital_signss */
    public static final String Table_Name = "hms_vital_signss";

    /** AD_Table_ID=1000019 */
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

    /** Column name Age */
    public static final String COLUMNNAME_Age = "Age";

	/** Set Age.
	  * Age of a person
	  */
	public void setAge (String Age);

	/** Get Age.
	  * Age of a person
	  */
	public String getAge();

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

    /** Column name bmi */
    public static final String COLUMNNAME_bmi = "bmi";

	/** Set BMI	  */
	public void setbmi (BigDecimal bmi);

	/** Get BMI	  */
	public BigDecimal getbmi();

    /** Column name bp_diastolic */
    public static final String COLUMNNAME_bp_diastolic = "bp_diastolic";

	/** Set Diastolic	  */
	public void setbp_diastolic (int bp_diastolic);

	/** Get Diastolic	  */
	public int getbp_diastolic();

    /** Column name bp_systolic */
    public static final String COLUMNNAME_bp_systolic = "bp_systolic";

	/** Set Systolic	  */
	public void setbp_systolic (int bp_systolic);

	/** Get Systolic	  */
	public int getbp_systolic();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name fhr */
    public static final String COLUMNNAME_fhr = "fhr";

	/** Set FHR	  */
	public void setfhr (int fhr);

	/** Get FHR	  */
	public int getfhr();

    /** Column name height */
    public static final String COLUMNNAME_height = "height";

	/** Set Height	  */
	public void setheight (BigDecimal height);

	/** Get Height	  */
	public BigDecimal getheight();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name hms_vital_signss_ID */
    public static final String COLUMNNAME_hms_vital_signss_ID = "hms_vital_signss_ID";

	/** Set hms_vital_signss ID	  */
	public void sethms_vital_signss_ID (int hms_vital_signss_ID);

	/** Get hms_vital_signss ID	  */
	public int gethms_vital_signss_ID();

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

    /** Column name lmp */
    public static final String COLUMNNAME_lmp = "lmp";

	/** Set lmp	  */
	public void setlmp (Timestamp lmp);

	/** Get lmp	  */
	public Timestamp getlmp();

    /** Column name notavail */
    public static final String COLUMNNAME_notavail = "notavail";

	/** Set notavail	  */
	public void setnotavail (boolean notavail);

	/** Get notavail	  */
	public boolean isnotavail();

    /** Column name ptemp */
    public static final String COLUMNNAME_ptemp = "ptemp";

	/** Set Temperature	  */
	public void setptemp (BigDecimal ptemp);

	/** Get Temperature	  */
	public BigDecimal getptemp();

    /** Column name pulse */
    public static final String COLUMNNAME_pulse = "pulse";

	/** Set Pulse	  */
	public void setpulse (int pulse);

	/** Get Pulse	  */
	public int getpulse();

    /** Column name respiratory */
    public static final String COLUMNNAME_respiratory = "respiratory";

	/** Set Respiratory	  */
	public void setrespiratory (int respiratory);

	/** Get Respiratory	  */
	public int getrespiratory();

    /** Column name taken_by_ID */
    public static final String COLUMNNAME_taken_by_ID = "taken_by_ID";

	/** Set Taken By	  */
	public void settaken_by_ID (int taken_by_ID);

	/** Get Taken By	  */
	public int gettaken_by_ID();

    /** Column name treatment_id */
    public static final String COLUMNNAME_treatment_id = "treatment_id";

	/** Set treatment_id	  */
	public void settreatment_id (int treatment_id);

	/** Get treatment_id	  */
	public int gettreatment_id();

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

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();
}
