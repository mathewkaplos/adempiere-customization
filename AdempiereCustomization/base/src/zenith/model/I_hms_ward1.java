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

/** Generated Interface for hms_ward1
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_ward1 
{

    /** TableName=hms_ward1 */
    public static final String Table_Name = "hms_ward1";

    /** AD_Table_ID=1000006 */
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

    /** Column name add_beds */
    public static final String COLUMNNAME_add_beds = "add_beds";

	/** Set add_beds	  */
	public void setadd_beds (String add_beds);

	/** Get add_beds	  */
	public String getadd_beds();

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

    /** Column name disp_beds */
    public static final String COLUMNNAME_disp_beds = "disp_beds";

	/** Set disp_beds	  */
	public void setdisp_beds (boolean disp_beds);

	/** Get disp_beds	  */
	public boolean isdisp_beds();

    /** Column name hms_ward_block1_ID */
    public static final String COLUMNNAME_hms_ward_block1_ID = "hms_ward_block1_ID";

	/** Set hms_ward_block1 ID	  */
	public void sethms_ward_block1_ID (int hms_ward_block1_ID);

	/** Get hms_ward_block1 ID	  */
	public int gethms_ward_block1_ID();

	public I_hms_ward_block1 gethms_ward_block1() throws RuntimeException;

    /** Column name hms_ward_floor1_ID */
    public static final String COLUMNNAME_hms_ward_floor1_ID = "hms_ward_floor1_ID";

	/** Set hms_ward_floor1 ID	  */
	public void sethms_ward_floor1_ID (int hms_ward_floor1_ID);

	/** Get hms_ward_floor1 ID	  */
	public int gethms_ward_floor1_ID();

	public I_hms_ward_floor1 gethms_ward_floor1() throws RuntimeException;

    /** Column name hms_ward_wing1_ID */
    public static final String COLUMNNAME_hms_ward_wing1_ID = "hms_ward_wing1_ID";

	/** Set hms_ward_wing1 ID	  */
	public void sethms_ward_wing1_ID (int hms_ward_wing1_ID);

	/** Get hms_ward_wing1 ID	  */
	public int gethms_ward_wing1_ID();

	public I_hms_ward_wing1 gethms_ward_wing1() throws RuntimeException;

    /** Column name hms_ward1_ID */
    public static final String COLUMNNAME_hms_ward1_ID = "hms_ward1_ID";

	/** Set hms_ward1 ID	  */
	public void sethms_ward1_ID (int hms_ward1_ID);

	/** Get hms_ward1 ID	  */
	public int gethms_ward1_ID();

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

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name number_of_beds */
    public static final String COLUMNNAME_number_of_beds = "number_of_beds";

	/** Set number_of_beds	  */
	public void setnumber_of_beds (BigDecimal number_of_beds);

	/** Get number_of_beds	  */
	public BigDecimal getnumber_of_beds();

    /** Column name remove_beds */
    public static final String COLUMNNAME_remove_beds = "remove_beds";

	/** Set remove_beds	  */
	public void setremove_beds (boolean remove_beds);

	/** Get remove_beds	  */
	public boolean isremove_beds();

    /** Column name transfer_beds */
    public static final String COLUMNNAME_transfer_beds = "transfer_beds";

	/** Set transfer_beds	  */
	public void settransfer_beds (boolean transfer_beds);

	/** Get transfer_beds	  */
	public boolean istransfer_beds();

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

    /** Column name ward_gender */
    public static final String COLUMNNAME_ward_gender = "ward_gender";

	/** Set ward_gender	  */
	public void setward_gender (String ward_gender);

	/** Get ward_gender	  */
	public String getward_gender();

    /** Column name ward_prefix */
    public static final String COLUMNNAME_ward_prefix = "ward_prefix";

	/** Set ward_prefix	  */
	public void setward_prefix (String ward_prefix);

	/** Get ward_prefix	  */
	public String getward_prefix();
}
