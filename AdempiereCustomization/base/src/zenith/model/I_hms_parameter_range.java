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

/** Generated Interface for hms_parameter_range
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_parameter_range 
{

    /** TableName=hms_parameter_range */
    public static final String Table_Name = "hms_parameter_range";

    /** AD_Table_ID=1000047 */
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

    /** Column name age_from */
    public static final String COLUMNNAME_age_from = "age_from";

	/** Set age_from	  */
	public void setage_from (BigDecimal age_from);

	/** Get age_from	  */
	public BigDecimal getage_from();

    /** Column name age_to */
    public static final String COLUMNNAME_age_to = "age_to";

	/** Set age_to	  */
	public void setage_to (BigDecimal age_to);

	/** Get age_to	  */
	public BigDecimal getage_to();

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

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name has_power */
    public static final String COLUMNNAME_has_power = "has_power";

	/** Set has_power	  */
	public void sethas_power (boolean has_power);

	/** Get has_power	  */
	public boolean ishas_power();

    /** Column name hms_parameter_range_ID */
    public static final String COLUMNNAME_hms_parameter_range_ID = "hms_parameter_range_ID";

	/** Set hms_parameter_range ID	  */
	public void sethms_parameter_range_ID (int hms_parameter_range_ID);

	/** Get hms_parameter_range ID	  */
	public int gethms_parameter_range_ID();

    /** Column name hms_parameters_ID */
    public static final String COLUMNNAME_hms_parameters_ID = "hms_parameters_ID";

	/** Set hms_parameters ID	  */
	public void sethms_parameters_ID (int hms_parameters_ID);

	/** Get hms_parameters ID	  */
	public int gethms_parameters_ID();

	public I_hms_parameters gethms_parameters() throws RuntimeException;

    /** Column name is_age_based */
    public static final String COLUMNNAME_is_age_based = "is_age_based";

	/** Set is_age_based	  */
	public void setis_age_based (boolean is_age_based);

	/** Get is_age_based	  */
	public boolean is_age_based();

    /** Column name is_gender_based */
    public static final String COLUMNNAME_is_gender_based = "is_gender_based";

	/** Set is_gender_based	  */
	public void setis_gender_based (boolean is_gender_based);

	/** Get is_gender_based	  */
	public boolean is_gender_based();

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

    /** Column name max_value */
    public static final String COLUMNNAME_max_value = "max_value";

	/** Set max_value	  */
	public void setmax_value (BigDecimal max_value);

	/** Get max_value	  */
	public BigDecimal getmax_value();

    /** Column name min_value */
    public static final String COLUMNNAME_min_value = "min_value";

	/** Set min_value	  */
	public void setmin_value (BigDecimal min_value);

	/** Get min_value	  */
	public BigDecimal getmin_value();

    /** Column name power */
    public static final String COLUMNNAME_power = "power";

	/** Set power	  */
	public void setpower (String power);

	/** Get power	  */
	public String getpower();

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
