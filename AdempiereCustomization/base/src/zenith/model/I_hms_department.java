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

/** Generated Interface for hms_department
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_department 
{

    /** TableName=hms_department */
    public static final String Table_Name = "hms_department";

    /** AD_Table_ID=1000010 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name blocation */
    public static final String COLUMNNAME_blocation = "blocation";

	/** Set Location	  */
	public void setblocation (String blocation);

	/** Get Location	  */
	public String getblocation();

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

    /** Column name department */
    public static final String COLUMNNAME_department = "department";

	/** Set Department Name	  */
	public void setdepartment (String department);

	/** Get Department Name	  */
	public String getdepartment();

    /** Column name head_of_department_id */
    public static final String COLUMNNAME_head_of_department_id = "head_of_department_id";

	/** Set Head of Department	  */
	public void sethead_of_department_id (int head_of_department_id);

	/** Get Head of Department	  */
	public int gethead_of_department_id();

	public I_C_ValidCombination gethead_of_department() throws RuntimeException;

    /** Column name hms_department_ID */
    public static final String COLUMNNAME_hms_department_ID = "hms_department_ID";

	/** Set Department	  */
	public void sethms_department_ID (int hms_department_ID);

	/** Get Department	  */
	public int gethms_department_ID();

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

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

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

    /** Column name shortcode */
    public static final String COLUMNNAME_shortcode = "shortcode";

	/** Set Short Code	  */
	public void setshortcode (String shortcode);

	/** Get Short Code	  */
	public String getshortcode();

    /** Column name sub_head_of_department_id */
    public static final String COLUMNNAME_sub_head_of_department_id = "sub_head_of_department_id";

	/** Set Assistant Head of Department	  */
	public void setsub_head_of_department_id (int sub_head_of_department_id);

	/** Get Assistant Head of Department	  */
	public int getsub_head_of_department_id();

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

    /** Column name user1 */
    public static final String COLUMNNAME_user1 = "user1";

	/** Set user1	  */
	public void setuser1 (int user1);

	/** Get user1	  */
	public int getuser1();

    /** Column name user2 */
    public static final String COLUMNNAME_user2 = "user2";

	/** Set user2	  */
	public void setuser2 (int user2);

	/** Get user2	  */
	public int getuser2();
}
