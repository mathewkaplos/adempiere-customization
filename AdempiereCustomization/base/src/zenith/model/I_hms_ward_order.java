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

/** Generated Interface for hms_ward_order
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_ward_order 
{

    /** TableName=hms_ward_order */
    public static final String Table_Name = "hms_ward_order";

    /** AD_Table_ID=1000077 */
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

    /** Column name btn_add_item */
    public static final String COLUMNNAME_btn_add_item = "btn_add_item";

	/** Set ADD ITEM	  */
	public void setbtn_add_item (String btn_add_item);

	/** Get ADD ITEM	  */
	public String getbtn_add_item();

    /** Column name btn_complete */
    public static final String COLUMNNAME_btn_complete = "btn_complete";

	/** Set COMPLETE	  */
	public void setbtn_complete (String btn_complete);

	/** Get COMPLETE	  */
	public String getbtn_complete();

    /** Column name comment */
    public static final String COLUMNNAME_comment = "comment";

	/** Set Comment	  */
	public void setcomment (String comment);

	/** Get Comment	  */
	public String getcomment();

    /** Column name completed */
    public static final String COLUMNNAME_completed = "completed";

	/** Set Completed	  */
	public void setcompleted (boolean completed);

	/** Get Completed	  */
	public boolean iscompleted();

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

    /** Column name DateRequired */
    public static final String COLUMNNAME_DateRequired = "DateRequired";

	/** Set Date Required.
	  * Date when required
	  */
	public void setDateRequired (Timestamp DateRequired);

	/** Get Date Required.
	  * Date when required
	  */
	public Timestamp getDateRequired();

    /** Column name disp01 */
    public static final String COLUMNNAME_disp01 = "disp01";

	/** Set disp01	  */
	public void setdisp01 (boolean disp01);

	/** Get disp01	  */
	public boolean isdisp01();

    /** Column name hms_ward_order_ID */
    public static final String COLUMNNAME_hms_ward_order_ID = "hms_ward_order_ID";

	/** Set hms_ward_order ID	  */
	public void sethms_ward_order_ID (int hms_ward_order_ID);

	/** Get hms_ward_order ID	  */
	public int gethms_ward_order_ID();

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

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name TimeRequired */
    public static final String COLUMNNAME_TimeRequired = "TimeRequired";

	/** Set Time Required	  */
	public void setTimeRequired (Timestamp TimeRequired);

	/** Get Time Required	  */
	public Timestamp getTimeRequired();

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
