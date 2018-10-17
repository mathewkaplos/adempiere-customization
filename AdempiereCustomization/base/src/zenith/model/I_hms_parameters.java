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

/** Generated Interface for hms_parameters
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_parameters 
{

    /** TableName=hms_parameters */
    public static final String Table_Name = "hms_parameters";

    /** AD_Table_ID=1000014 */
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

    /** Column name disp_range */
    public static final String COLUMNNAME_disp_range = "disp_range";

	/** Set disp_range	  */
	public void setdisp_range (boolean disp_range);

	/** Get disp_range	  */
	public boolean isdisp_range();

    /** Column name has_power */
    public static final String COLUMNNAME_has_power = "has_power";

	/** Set has_power	  */
	public void sethas_power (boolean has_power);

	/** Get has_power	  */
	public boolean ishas_power();

    /** Column name has_range */
    public static final String COLUMNNAME_has_range = "has_range";

	/** Set has_range	  */
	public void sethas_range (boolean has_range);

	/** Get has_range	  */
	public boolean ishas_range();

    /** Column name hms_parameters_ID */
    public static final String COLUMNNAME_hms_parameters_ID = "hms_parameters_ID";

	/** Set Parameter.
	  * Lab Test Parameter
	  */
	public void sethms_parameters_ID (int hms_parameters_ID);

	/** Get Parameter.
	  * Lab Test Parameter
	  */
	public int gethms_parameters_ID();

    /** Column name hms_specimens_ID */
    public static final String COLUMNNAME_hms_specimens_ID = "hms_specimens_ID";

	/** Set hms_specimens ID	  */
	public void sethms_specimens_ID (int hms_specimens_ID);

	/** Get hms_specimens ID	  */
	public int gethms_specimens_ID();

	public I_hms_specimens gethms_specimens() throws RuntimeException;

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

    /** Column name units */
    public static final String COLUMNNAME_units = "units";

	/** Set Units.
	  * Parameter Units
	  */
	public void setunits (String units);

	/** Get Units.
	  * Parameter Units
	  */
	public String getunits();

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
