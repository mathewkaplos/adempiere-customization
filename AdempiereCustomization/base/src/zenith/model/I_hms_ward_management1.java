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

/** Generated Interface for hms_ward_management1
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_ward_management1 
{

    /** TableName=hms_ward_management1 */
    public static final String Table_Name = "hms_ward_management1";

    /** AD_Table_ID=1000008 */
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

    /** Column name clear_issued_btn */
    public static final String COLUMNNAME_clear_issued_btn = "clear_issued_btn";

	/** Set clear_issued_btn	  */
	public void setclear_issued_btn (boolean clear_issued_btn);

	/** Get clear_issued_btn	  */
	public boolean isclear_issued_btn();

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

    /** Column name disp01 */
    public static final String COLUMNNAME_disp01 = "disp01";

	/** Set disp01	  */
	public void setdisp01 (boolean disp01);

	/** Get disp01	  */
	public boolean isdisp01();

    /** Column name disp02 */
    public static final String COLUMNNAME_disp02 = "disp02";

	/** Set disp02	  */
	public void setdisp02 (boolean disp02);

	/** Get disp02	  */
	public boolean isdisp02();

    /** Column name hms_ward_management1_ID */
    public static final String COLUMNNAME_hms_ward_management1_ID = "hms_ward_management1_ID";

	/** Set hms_ward_management1 ID	  */
	public void sethms_ward_management1_ID (int hms_ward_management1_ID);

	/** Get hms_ward_management1 ID	  */
	public int gethms_ward_management1_ID();

    /** Column name hms_ward1_ID */
    public static final String COLUMNNAME_hms_ward1_ID = "hms_ward1_ID";

	/** Set hms_ward1 ID	  */
	public void sethms_ward1_ID (int hms_ward1_ID);

	/** Get hms_ward1 ID	  */
	public int gethms_ward1_ID();

	public I_hms_ward1 gethms_ward1() throws RuntimeException;

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

    /** Column name issue_drugs_btn */
    public static final String COLUMNNAME_issue_drugs_btn = "issue_drugs_btn";

	/** Set issue_drugs_btn	  */
	public void setissue_drugs_btn (boolean issue_drugs_btn);

	/** Get issue_drugs_btn	  */
	public boolean issue_drugs_btn();

    /** Column name send_request_btn */
    public static final String COLUMNNAME_send_request_btn = "send_request_btn";

	/** Set send_request_btn	  */
	public void setsend_request_btn (boolean send_request_btn);

	/** Get send_request_btn	  */
	public boolean issend_request_btn();

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
