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

/** Generated Interface for hms_appointments
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_appointments 
{

    /** TableName=hms_appointments */
    public static final String Table_Name = "hms_appointments";

    /** AD_Table_ID=1000028 */
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

    /** Column name appoint_date */
    public static final String COLUMNNAME_appoint_date = "appoint_date";

	/** Set Appointment Date	  */
	public void setappoint_date (Timestamp appoint_date);

	/** Get Appointment Date	  */
	public Timestamp getappoint_date();

    /** Column name appoint_doc */
    public static final String COLUMNNAME_appoint_doc = "appoint_doc";

	/** Set Appointment Doctor	  */
	public void setappoint_doc (int appoint_doc);

	/** Get Appointment Doctor	  */
	public int getappoint_doc();

	public org.compiere.model.I_AD_User getappoint_() throws RuntimeException;

    /** Column name appointtime */
    public static final String COLUMNNAME_appointtime = "appointtime";

	/** Set Appointment Time	  */
	public void setappointtime (Timestamp appointtime);

	/** Get Appointment Time	  */
	public Timestamp getappointtime();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name displo */
    public static final String COLUMNNAME_displo = "displo";

	/** Set displo	  */
	public void setdisplo (int displo);

	/** Get displo	  */
	public int getdisplo();

    /** Column name doctor_ID */
    public static final String COLUMNNAME_doctor_ID = "doctor_ID";

	/** Set Doctor	  */
	public void setdoctor_ID (int doctor_ID);

	/** Get Doctor	  */
	public int getdoctor_ID();

	public org.compiere.model.I_AD_User getdoctor() throws RuntimeException;

    /** Column name hms_appointments_ID */
    public static final String COLUMNNAME_hms_appointments_ID = "hms_appointments_ID";

	/** Set hms_appointments ID	  */
	public void sethms_appointments_ID (int hms_appointments_ID);

	/** Get hms_appointments ID	  */
	public int gethms_appointments_ID();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

    /** Column name honoured_appoint */
    public static final String COLUMNNAME_honoured_appoint = "honoured_appoint";

	/** Set honoured_appoint	  */
	public void sethonoured_appoint (boolean honoured_appoint);

	/** Get honoured_appoint	  */
	public boolean ishonoured_appoint();

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

    /** Column name missed_appoint */
    public static final String COLUMNNAME_missed_appoint = "missed_appoint";

	/** Set missed_appoint	  */
	public void setmissed_appoint (boolean missed_appoint);

	/** Get missed_appoint	  */
	public boolean ismissed_appoint();

    /** Column name pending_appoint */
    public static final String COLUMNNAME_pending_appoint = "pending_appoint";

	/** Set pending_appoint	  */
	public void setpending_appoint (boolean pending_appoint);

	/** Get pending_appoint	  */
	public boolean ispending_appoint();

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
