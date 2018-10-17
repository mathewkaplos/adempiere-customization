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

/** Generated Interface for hms_specimen_r_line
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_specimen_r_line 
{

    /** TableName=hms_specimen_r_line */
    public static final String Table_Name = "hms_specimen_r_line";

    /** AD_Table_ID=1000016 */
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

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

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

    /** Column name hms_specimen_r_line_ID */
    public static final String COLUMNNAME_hms_specimen_r_line_ID = "hms_specimen_r_line_ID";

	/** Set hms_specimen_r_line ID	  */
	public void sethms_specimen_r_line_ID (int hms_specimen_r_line_ID);

	/** Get hms_specimen_r_line ID	  */
	public int gethms_specimen_r_line_ID();

    /** Column name hms_specimen_requests_ID */
    public static final String COLUMNNAME_hms_specimen_requests_ID = "hms_specimen_requests_ID";

	/** Set hms_specimen_requests ID	  */
	public void sethms_specimen_requests_ID (int hms_specimen_requests_ID);

	/** Get hms_specimen_requests ID	  */
	public int gethms_specimen_requests_ID();

	public I_hms_specimen_requests gethms_specimen_requests() throws RuntimeException;

    /** Column name hms_specimens_ID */
    public static final String COLUMNNAME_hms_specimens_ID = "hms_specimens_ID";

	/** Set hms_specimens ID	  */
	public void sethms_specimens_ID (int hms_specimens_ID);

	/** Get hms_specimens ID	  */
	public int gethms_specimens_ID();

	public I_hms_specimens gethms_specimens() throws RuntimeException;

    /** Column name hms_test_ID */
    public static final String COLUMNNAME_hms_test_ID = "hms_test_ID";

	/** Set hms_test ID	  */
	public void sethms_test_ID (int hms_test_ID);

	/** Get hms_test ID	  */
	public int gethms_test_ID();

	public I_hms_test gethms_test() throws RuntimeException;

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

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

    /** Column name percencentage_done */
    public static final String COLUMNNAME_percencentage_done = "percencentage_done";

	/** Set percencentage_done	  */
	public void setpercencentage_done (BigDecimal percencentage_done);

	/** Get percencentage_done	  */
	public BigDecimal getpercencentage_done();

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
