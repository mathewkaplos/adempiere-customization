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

/** Generated Interface for hms_705_detail
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_705_detail 
{

    /** TableName=hms_705_detail */
    public static final String Table_Name = "hms_705_detail";

    /** AD_Table_ID=1000085 */
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

    /** Column name d1 */
    public static final String COLUMNNAME_d1 = "d1";

	/** Set 1	  */
	public void setd1 (int d1);

	/** Get 1	  */
	public int getd1();

    /** Column name d10 */
    public static final String COLUMNNAME_d10 = "d10";

	/** Set 10	  */
	public void setd10 (int d10);

	/** Get 10	  */
	public int getd10();

    /** Column name d11 */
    public static final String COLUMNNAME_d11 = "d11";

	/** Set 11	  */
	public void setd11 (int d11);

	/** Get 11	  */
	public int getd11();

    /** Column name d12 */
    public static final String COLUMNNAME_d12 = "d12";

	/** Set 12	  */
	public void setd12 (int d12);

	/** Get 12	  */
	public int getd12();

    /** Column name d13 */
    public static final String COLUMNNAME_d13 = "d13";

	/** Set 13	  */
	public void setd13 (int d13);

	/** Get 13	  */
	public int getd13();

    /** Column name d14 */
    public static final String COLUMNNAME_d14 = "d14";

	/** Set 14	  */
	public void setd14 (int d14);

	/** Get 14	  */
	public int getd14();

    /** Column name d15 */
    public static final String COLUMNNAME_d15 = "d15";

	/** Set 15	  */
	public void setd15 (int d15);

	/** Get 15	  */
	public int getd15();

    /** Column name d16 */
    public static final String COLUMNNAME_d16 = "d16";

	/** Set 16	  */
	public void setd16 (int d16);

	/** Get 16	  */
	public int getd16();

    /** Column name d17 */
    public static final String COLUMNNAME_d17 = "d17";

	/** Set 17	  */
	public void setd17 (int d17);

	/** Get 17	  */
	public int getd17();

    /** Column name d18 */
    public static final String COLUMNNAME_d18 = "d18";

	/** Set 18	  */
	public void setd18 (int d18);

	/** Get 18	  */
	public int getd18();

    /** Column name d19 */
    public static final String COLUMNNAME_d19 = "d19";

	/** Set 19	  */
	public void setd19 (int d19);

	/** Get 19	  */
	public int getd19();

    /** Column name d2 */
    public static final String COLUMNNAME_d2 = "d2";

	/** Set 2	  */
	public void setd2 (int d2);

	/** Get 2	  */
	public int getd2();

    /** Column name d20 */
    public static final String COLUMNNAME_d20 = "d20";

	/** Set 20	  */
	public void setd20 (int d20);

	/** Get 20	  */
	public int getd20();

    /** Column name d21 */
    public static final String COLUMNNAME_d21 = "d21";

	/** Set 21	  */
	public void setd21 (int d21);

	/** Get 21	  */
	public int getd21();

    /** Column name d22 */
    public static final String COLUMNNAME_d22 = "d22";

	/** Set 22	  */
	public void setd22 (int d22);

	/** Get 22	  */
	public int getd22();

    /** Column name d23 */
    public static final String COLUMNNAME_d23 = "d23";

	/** Set 23	  */
	public void setd23 (int d23);

	/** Get 23	  */
	public int getd23();

    /** Column name d24 */
    public static final String COLUMNNAME_d24 = "d24";

	/** Set 24	  */
	public void setd24 (int d24);

	/** Get 24	  */
	public int getd24();

    /** Column name d25 */
    public static final String COLUMNNAME_d25 = "d25";

	/** Set 25	  */
	public void setd25 (int d25);

	/** Get 25	  */
	public int getd25();

    /** Column name d26 */
    public static final String COLUMNNAME_d26 = "d26";

	/** Set 26	  */
	public void setd26 (int d26);

	/** Get 26	  */
	public int getd26();

    /** Column name d27 */
    public static final String COLUMNNAME_d27 = "d27";

	/** Set 27	  */
	public void setd27 (int d27);

	/** Get 27	  */
	public int getd27();

    /** Column name d28 */
    public static final String COLUMNNAME_d28 = "d28";

	/** Set 28	  */
	public void setd28 (int d28);

	/** Get 28	  */
	public int getd28();

    /** Column name d29 */
    public static final String COLUMNNAME_d29 = "d29";

	/** Set 29	  */
	public void setd29 (int d29);

	/** Get 29	  */
	public int getd29();

    /** Column name d3 */
    public static final String COLUMNNAME_d3 = "d3";

	/** Set 3	  */
	public void setd3 (int d3);

	/** Get 3	  */
	public int getd3();

    /** Column name d30 */
    public static final String COLUMNNAME_d30 = "d30";

	/** Set 30	  */
	public void setd30 (int d30);

	/** Get 30	  */
	public int getd30();

    /** Column name d31 */
    public static final String COLUMNNAME_d31 = "d31";

	/** Set 31	  */
	public void setd31 (int d31);

	/** Get 31	  */
	public int getd31();

    /** Column name d4 */
    public static final String COLUMNNAME_d4 = "d4";

	/** Set 4	  */
	public void setd4 (int d4);

	/** Get 4	  */
	public int getd4();

    /** Column name d5 */
    public static final String COLUMNNAME_d5 = "d5";

	/** Set 5	  */
	public void setd5 (int d5);

	/** Get 5	  */
	public int getd5();

    /** Column name d6 */
    public static final String COLUMNNAME_d6 = "d6";

	/** Set 6	  */
	public void setd6 (int d6);

	/** Get 6	  */
	public int getd6();

    /** Column name d7 */
    public static final String COLUMNNAME_d7 = "d7";

	/** Set 7	  */
	public void setd7 (int d7);

	/** Get 7	  */
	public int getd7();

    /** Column name d8 */
    public static final String COLUMNNAME_d8 = "d8";

	/** Set 8	  */
	public void setd8 (int d8);

	/** Get 8	  */
	public int getd8();

    /** Column name d9 */
    public static final String COLUMNNAME_d9 = "d9";

	/** Set 9	  */
	public void setd9 (int d9);

	/** Get 9	  */
	public int getd9();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name hms_705_detail_ID */
    public static final String COLUMNNAME_hms_705_detail_ID = "hms_705_detail_ID";

	/** Set hms_705_detail ID	  */
	public void sethms_705_detail_ID (int hms_705_detail_ID);

	/** Get hms_705_detail ID	  */
	public int gethms_705_detail_ID();

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

    /** Column name totals */
    public static final String COLUMNNAME_totals = "totals";

	/** Set Totals	  */
	public void settotals (int totals);

	/** Get Totals	  */
	public int gettotals();

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
