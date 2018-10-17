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

/** Generated Interface for z_bama_patients
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_z_bama_patients 
{

    /** TableName=z_bama_patients */
    public static final String Table_Name = "z_bama_patients";

    /** AD_Table_ID=1000050 */
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

    /** Column name patient_dob */
    public static final String COLUMNNAME_patient_dob = "patient_dob";

	/** Set patient_dob	  */
	public void setpatient_dob (Timestamp patient_dob);

	/** Get patient_dob	  */
	public Timestamp getpatient_dob();

    /** Column name patient_fullname */
    public static final String COLUMNNAME_patient_fullname = "patient_fullname";

	/** Set patient_fullname	  */
	public void setpatient_fullname (String patient_fullname);

	/** Get patient_fullname	  */
	public String getpatient_fullname();

    /** Column name patient_gender */
    public static final String COLUMNNAME_patient_gender = "patient_gender";

	/** Set patient_gender	  */
	public void setpatient_gender (String patient_gender);

	/** Get patient_gender	  */
	public String getpatient_gender();

    /** Column name patient_natioanalid */
    public static final String COLUMNNAME_patient_natioanalid = "patient_natioanalid";

	/** Set patient_natioanalid	  */
	public void setpatient_natioanalid (String patient_natioanalid);

	/** Get patient_natioanalid	  */
	public String getpatient_natioanalid();

    /** Column name patient_nhifno */
    public static final String COLUMNNAME_patient_nhifno = "patient_nhifno";

	/** Set patient_nhifno	  */
	public void setpatient_nhifno (String patient_nhifno);

	/** Get patient_nhifno	  */
	public String getpatient_nhifno();

    /** Column name patient_no */
    public static final String COLUMNNAME_patient_no = "patient_no";

	/** Set patient_no	  */
	public void setpatient_no (String patient_no);

	/** Get patient_no	  */
	public String getpatient_no();

    /** Column name patient_reg */
    public static final String COLUMNNAME_patient_reg = "patient_reg";

	/** Set patient_reg	  */
	public void setpatient_reg (Timestamp patient_reg);

	/** Get patient_reg	  */
	public Timestamp getpatient_reg();

    /** Column name patient_tel */
    public static final String COLUMNNAME_patient_tel = "patient_tel";

	/** Set patient_tel	  */
	public void setpatient_tel (String patient_tel);

	/** Get patient_tel	  */
	public String getpatient_tel();

    /** Column name petient_pricelistid */
    public static final String COLUMNNAME_petient_pricelistid = "petient_pricelistid";

	/** Set petient_pricelistid	  */
	public void setpetient_pricelistid (int petient_pricelistid);

	/** Get petient_pricelistid	  */
	public int getpetient_pricelistid();

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

    /** Column name z_bama_patients_ID */
    public static final String COLUMNNAME_z_bama_patients_ID = "z_bama_patients_ID";

	/** Set z_bama_patients ID	  */
	public void setz_bama_patients_ID (int z_bama_patients_ID);

	/** Get z_bama_patients ID	  */
	public int getz_bama_patients_ID();
}
