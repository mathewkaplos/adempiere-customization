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

/** Generated Interface for hms_gererate_705
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_gererate_705 
{

    /** TableName=hms_gererate_705 */
    public static final String Table_Name = "hms_gererate_705";

    /** AD_Table_ID=1000084 */
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

    /** Column name btn_generate */
    public static final String COLUMNNAME_btn_generate = "btn_generate";

	/** Set GENERATE REPORT	  */
	public void setbtn_generate (String btn_generate);

	/** Get GENERATE REPORT	  */
	public String getbtn_generate();

    /** Column name btn_print */
    public static final String COLUMNNAME_btn_print = "btn_print";

	/** Set PRINT	  */
	public void setbtn_print (String btn_print);

	/** Get PRINT	  */
	public String getbtn_print();

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

    /** Column name county */
    public static final String COLUMNNAME_county = "county";

	/** Set County	  */
	public void setcounty (String county);

	/** Get County	  */
	public String getcounty();

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

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name facility_type */
    public static final String COLUMNNAME_facility_type = "facility_type";

	/** Set Facility Type	  */
	public void setfacility_type (String facility_type);

	/** Get Facility Type	  */
	public String getfacility_type();

    /** Column name hms_gererate_705_ID */
    public static final String COLUMNNAME_hms_gererate_705_ID = "hms_gererate_705_ID";

	/** Set hms_gererate_705 ID	  */
	public void sethms_gererate_705_ID (int hms_gererate_705_ID);

	/** Get hms_gererate_705 ID	  */
	public int gethms_gererate_705_ID();

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

    /** Column name start_date */
    public static final String COLUMNNAME_start_date = "start_date";

	/** Set start_date	  */
	public void setstart_date (Timestamp start_date);

	/** Get start_date	  */
	public Timestamp getstart_date();

    /** Column name sub_county */
    public static final String COLUMNNAME_sub_county = "sub_county";

	/** Set Sub County	  */
	public void setsub_county (String sub_county);

	/** Get Sub County	  */
	public String getsub_county();

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

    /** Column name ward */
    public static final String COLUMNNAME_ward = "ward";

	/** Set Ward	  */
	public void setward (String ward);

	/** Get Ward	  */
	public String getward();
}
