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

/** Generated Interface for hms_inpatient_orders
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_hms_inpatient_orders 
{

    /** TableName=hms_inpatient_orders */
    public static final String Table_Name = "hms_inpatient_orders";

    /** AD_Table_ID=1000054 */
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

    /** Column name afternoon */
    public static final String COLUMNNAME_afternoon = "afternoon";

	/** Set afternoon	  */
	public void setafternoon (boolean afternoon);

	/** Get afternoon	  */
	public boolean isafternoon();

    /** Column name afternoon_issued */
    public static final String COLUMNNAME_afternoon_issued = "afternoon_issued";

	/** Set afternoon_issued	  */
	public void setafternoon_issued (boolean afternoon_issued);

	/** Get afternoon_issued	  */
	public boolean isafternoon_issued();

    /** Column name afternoon_qty */
    public static final String COLUMNNAME_afternoon_qty = "afternoon_qty";

	/** Set afternoon_qty	  */
	public void setafternoon_qty (BigDecimal afternoon_qty);

	/** Get afternoon_qty	  */
	public BigDecimal getafternoon_qty();

    /** Column name bed_no */
    public static final String COLUMNNAME_bed_no = "bed_no";

	/** Set bed_no	  */
	public void setbed_no (BigDecimal bed_no);

	/** Get bed_no	  */
	public BigDecimal getbed_no();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name daysgiven */
    public static final String COLUMNNAME_daysgiven = "daysgiven";

	/** Set daysgiven	  */
	public void setdaysgiven (BigDecimal daysgiven);

	/** Get daysgiven	  */
	public BigDecimal getdaysgiven();

    /** Column name dosetyp */
    public static final String COLUMNNAME_dosetyp = "dosetyp";

	/** Set dosetyp	  */
	public void setdosetyp (String dosetyp);

	/** Get dosetyp	  */
	public String getdosetyp();

    /** Column name evenning */
    public static final String COLUMNNAME_evenning = "evenning";

	/** Set evenning	  */
	public void setevenning (boolean evenning);

	/** Get evenning	  */
	public boolean isevenning();

    /** Column name evenning_issued */
    public static final String COLUMNNAME_evenning_issued = "evenning_issued";

	/** Set evenning_issued	  */
	public void setevenning_issued (boolean evenning_issued);

	/** Get evenning_issued	  */
	public boolean isevenning_issued();

    /** Column name evenning_qty */
    public static final String COLUMNNAME_evenning_qty = "evenning_qty";

	/** Set evenning_qty	  */
	public void setevenning_qty (BigDecimal evenning_qty);

	/** Get evenning_qty	  */
	public BigDecimal getevenning_qty();

    /** Column name Frequency */
    public static final String COLUMNNAME_Frequency = "Frequency";

	/** Set Frequency.
	  * Frequency of events
	  */
	public void setFrequency (BigDecimal Frequency);

	/** Get Frequency.
	  * Frequency of events
	  */
	public BigDecimal getFrequency();

    /** Column name hms_inpatient_orders_ID */
    public static final String COLUMNNAME_hms_inpatient_orders_ID = "hms_inpatient_orders_ID";

	/** Set hms_inpatient_orders ID	  */
	public void sethms_inpatient_orders_ID (int hms_inpatient_orders_ID);

	/** Get hms_inpatient_orders ID	  */
	public int gethms_inpatient_orders_ID();

    /** Column name hms_treatment_doc_ID */
    public static final String COLUMNNAME_hms_treatment_doc_ID = "hms_treatment_doc_ID";

	/** Set hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID);

	/** Get hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID();

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException;

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

    /** Column name itemcode */
    public static final String COLUMNNAME_itemcode = "itemcode";

	/** Set itemcode	  */
	public void setitemcode (String itemcode);

	/** Get itemcode	  */
	public String getitemcode();

    /** Column name lineamt */
    public static final String COLUMNNAME_lineamt = "lineamt";

	/** Set lineamt	  */
	public void setlineamt (BigDecimal lineamt);

	/** Get lineamt	  */
	public BigDecimal getlineamt();

    /** Column name M_Movement_ID */
    public static final String COLUMNNAME_M_Movement_ID = "M_Movement_ID";

	/** Set Inventory Move.
	  * Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID);

	/** Get Inventory Move.
	  * Movement of Inventory
	  */
	public int getM_Movement_ID();

	public org.compiere.model.I_M_Movement getM_Movement() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name med_dosage_type */
    public static final String COLUMNNAME_med_dosage_type = "med_dosage_type";

	/** Set med_dosage_type	  */
	public void setmed_dosage_type (String med_dosage_type);

	/** Get med_dosage_type	  */
	public String getmed_dosage_type();

    /** Column name midday */
    public static final String COLUMNNAME_midday = "midday";

	/** Set midday	  */
	public void setmidday (boolean midday);

	/** Get midday	  */
	public boolean ismidday();

    /** Column name midday_issued */
    public static final String COLUMNNAME_midday_issued = "midday_issued";

	/** Set midday_issued	  */
	public void setmidday_issued (boolean midday_issued);

	/** Get midday_issued	  */
	public boolean ismidday_issued();

    /** Column name midday_qty */
    public static final String COLUMNNAME_midday_qty = "midday_qty";

	/** Set midday_qty	  */
	public void setmidday_qty (BigDecimal midday_qty);

	/** Get midday_qty	  */
	public BigDecimal getmidday_qty();

    /** Column name midmorning */
    public static final String COLUMNNAME_midmorning = "midmorning";

	/** Set midmorning	  */
	public void setmidmorning (boolean midmorning);

	/** Get midmorning	  */
	public boolean ismidmorning();

    /** Column name midmorning_issued */
    public static final String COLUMNNAME_midmorning_issued = "midmorning_issued";

	/** Set midmorning_issued	  */
	public void setmidmorning_issued (boolean midmorning_issued);

	/** Get midmorning_issued	  */
	public boolean ismidmorning_issued();

    /** Column name midmorning_qty */
    public static final String COLUMNNAME_midmorning_qty = "midmorning_qty";

	/** Set midmorning_qty	  */
	public void setmidmorning_qty (BigDecimal midmorning_qty);

	/** Get midmorning_qty	  */
	public BigDecimal getmidmorning_qty();

    /** Column name midnight */
    public static final String COLUMNNAME_midnight = "midnight";

	/** Set midnight	  */
	public void setmidnight (boolean midnight);

	/** Get midnight	  */
	public boolean ismidnight();

    /** Column name midnight_issued */
    public static final String COLUMNNAME_midnight_issued = "midnight_issued";

	/** Set midnight_issued	  */
	public void setmidnight_issued (boolean midnight_issued);

	/** Get midnight_issued	  */
	public boolean ismidnight_issued();

    /** Column name midnight_qty */
    public static final String COLUMNNAME_midnight_qty = "midnight_qty";

	/** Set midnight_qty	  */
	public void setmidnight_qty (BigDecimal midnight_qty);

	/** Get midnight_qty	  */
	public BigDecimal getmidnight_qty();

    /** Column name morning */
    public static final String COLUMNNAME_morning = "morning";

	/** Set morning	  */
	public void setmorning (boolean morning);

	/** Get morning	  */
	public boolean ismorning();

    /** Column name morning_issued */
    public static final String COLUMNNAME_morning_issued = "morning_issued";

	/** Set morning_issued	  */
	public void setmorning_issued (boolean morning_issued);

	/** Get morning_issued	  */
	public boolean ismorning_issued();

    /** Column name morning_qty */
    public static final String COLUMNNAME_morning_qty = "morning_qty";

	/** Set morning_qty	  */
	public void setmorning_qty (BigDecimal morning_qty);

	/** Get morning_qty	  */
	public BigDecimal getmorning_qty();

    /** Column name order_closed */
    public static final String COLUMNNAME_order_closed = "order_closed";

	/** Set order_closed	  */
	public void setorder_closed (boolean order_closed);

	/** Get order_closed	  */
	public boolean isorder_closed();

    /** Column name order_issued */
    public static final String COLUMNNAME_order_issued = "order_issued";

	/** Set order_issued	  */
	public void setorder_issued (boolean order_issued);

	/** Get order_issued	  */
	public boolean isorder_issued();

    /** Column name order_nullified */
    public static final String COLUMNNAME_order_nullified = "order_nullified";

	/** Set order_nullified	  */
	public void setorder_nullified (boolean order_nullified);

	/** Get order_nullified	  */
	public boolean isorder_nullified();

    /** Column name order_recieved */
    public static final String COLUMNNAME_order_recieved = "order_recieved";

	/** Set order_recieved	  */
	public void setorder_recieved (boolean order_recieved);

	/** Get order_recieved	  */
	public boolean isorder_recieved();

    /** Column name order_sent */
    public static final String COLUMNNAME_order_sent = "order_sent";

	/** Set order_sent	  */
	public void setorder_sent (boolean order_sent);

	/** Get order_sent	  */
	public boolean isorder_sent();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name quantity */
    public static final String COLUMNNAME_quantity = "quantity";

	/** Set quantity	  */
	public void setquantity (int quantity);

	/** Get quantity	  */
	public int getquantity();

    /** Column name start_dose */
    public static final String COLUMNNAME_start_dose = "start_dose";

	/** Set start_dose	  */
	public void setstart_dose (boolean start_dose);

	/** Get start_dose	  */
	public boolean isstart_dose();

    /** Column name startdose_qty */
    public static final String COLUMNNAME_startdose_qty = "startdose_qty";

	/** Set startdose_qty	  */
	public void setstartdose_qty (BigDecimal startdose_qty);

	/** Get startdose_qty	  */
	public BigDecimal getstartdose_qty();

    /** Column name stopdate */
    public static final String COLUMNNAME_stopdate = "stopdate";

	/** Set stopdate	  */
	public void setstopdate (Timestamp stopdate);

	/** Get stopdate	  */
	public Timestamp getstopdate();

    /** Column name unitprice */
    public static final String COLUMNNAME_unitprice = "unitprice";

	/** Set unitprice	  */
	public void setunitprice (BigDecimal unitprice);

	/** Get unitprice	  */
	public BigDecimal getunitprice();

    /** Column name units_per_frequency */
    public static final String COLUMNNAME_units_per_frequency = "units_per_frequency";

	/** Set units_per_frequency	  */
	public void setunits_per_frequency (BigDecimal units_per_frequency);

	/** Get units_per_frequency	  */
	public BigDecimal getunits_per_frequency();

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

    /** Column name ward_frequency */
    public static final String COLUMNNAME_ward_frequency = "ward_frequency";

	/** Set ward_frequency	  */
	public void setward_frequency (String ward_frequency);

	/** Get ward_frequency	  */
	public String getward_frequency();

    /** Column name ward_name */
    public static final String COLUMNNAME_ward_name = "ward_name";

	/** Set ward_name	  */
	public void setward_name (BigDecimal ward_name);

	/** Get ward_name	  */
	public BigDecimal getward_name();
}
