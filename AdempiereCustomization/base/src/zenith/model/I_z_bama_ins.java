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

/** Generated Interface for z_bama_ins
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_z_bama_ins 
{

    /** TableName=z_bama_ins */
    public static final String Table_Name = "z_bama_ins";

    /** AD_Table_ID=1000057 */
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

    /** Column name insurance_boxno */
    public static final String COLUMNNAME_insurance_boxno = "insurance_boxno";

	/** Set insurance_boxno	  */
	public void setinsurance_boxno (String insurance_boxno);

	/** Get insurance_boxno	  */
	public String getinsurance_boxno();

    /** Column name insurance_email */
    public static final String COLUMNNAME_insurance_email = "insurance_email";

	/** Set insurance_email	  */
	public void setinsurance_email (String insurance_email);

	/** Get insurance_email	  */
	public String getinsurance_email();

    /** Column name insurance_name */
    public static final String COLUMNNAME_insurance_name = "insurance_name";

	/** Set insurance_name	  */
	public void setinsurance_name (String insurance_name);

	/** Get insurance_name	  */
	public String getinsurance_name();

    /** Column name insurance_town */
    public static final String COLUMNNAME_insurance_town = "insurance_town";

	/** Set insurance_town	  */
	public void setinsurance_town (String insurance_town);

	/** Get insurance_town	  */
	public String getinsurance_town();

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

    /** Column name z_bama_ins_ID */
    public static final String COLUMNNAME_z_bama_ins_ID = "z_bama_ins_ID";

	/** Set z_bama_ins ID	  */
	public void setz_bama_ins_ID (int z_bama_ins_ID);

	/** Get z_bama_ins ID	  */
	public int getz_bama_ins_ID();
}
