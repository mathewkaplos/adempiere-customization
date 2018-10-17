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

/** Generated Interface for hms_insco
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_insco 
{

    /** TableName=hms_insco */
    public static final String Table_Name = "hms_insco";

    /** AD_Table_ID=1000038 */
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

    /** Column name companyname */
    public static final String COLUMNNAME_companyname = "companyname";

	/** Set companyname	  */
	public void setcompanyname (String companyname);

	/** Get companyname	  */
	public String getcompanyname();

    /** Column name copay */
    public static final String COLUMNNAME_copay = "copay";

	/** Set Copay Amount	  */
	public void setcopay (int copay);

	/** Get Copay Amount	  */
	public int getcopay();

    /** Column name county */
    public static final String COLUMNNAME_county = "county";

	/** Set county	  */
	public void setcounty (String county);

	/** Get county	  */
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

    /** Column name dispil1 */
    public static final String COLUMNNAME_dispil1 = "dispil1";

	/** Set dispil1	  */
	public void setdispil1 (BigDecimal dispil1);

	/** Get dispil1	  */
	public BigDecimal getdispil1();

    /** Column name dispil2 */
    public static final String COLUMNNAME_dispil2 = "dispil2";

	/** Set dispil2	  */
	public void setdispil2 (BigDecimal dispil2);

	/** Get dispil2	  */
	public BigDecimal getdispil2();

    /** Column name emailadd */
    public static final String COLUMNNAME_emailadd = "emailadd";

	/** Set emailadd	  */
	public void setemailadd (String emailadd);

	/** Get emailadd	  */
	public String getemailadd();

    /** Column name hms_insco_ID */
    public static final String COLUMNNAME_hms_insco_ID = "hms_insco_ID";

	/** Set Insurance Company	  */
	public void sethms_insco_ID (int hms_insco_ID);

	/** Get Insurance Company	  */
	public int gethms_insco_ID();

    /** Column name import_id */
    public static final String COLUMNNAME_import_id = "import_id";

	/** Set import_id	  */
	public void setimport_id (int import_id);

	/** Get import_id	  */
	public int getimport_id();

    /** Column name ins_phoneno */
    public static final String COLUMNNAME_ins_phoneno = "ins_phoneno";

	/** Set ins_phoneno	  */
	public void setins_phoneno (int ins_phoneno);

	/** Get ins_phoneno	  */
	public int getins_phoneno();

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

    /** Column name paddress */
    public static final String COLUMNNAME_paddress = "paddress";

	/** Set paddress	  */
	public void setpaddress (String paddress);

	/** Get paddress	  */
	public String getpaddress();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name pobox */
    public static final String COLUMNNAME_pobox = "pobox";

	/** Set pobox	  */
	public void setpobox (String pobox);

	/** Get pobox	  */
	public String getpobox();

    /** Column name town */
    public static final String COLUMNNAME_town = "town";

	/** Set town	  */
	public void settown (String town);

	/** Get town	  */
	public String gettown();

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
