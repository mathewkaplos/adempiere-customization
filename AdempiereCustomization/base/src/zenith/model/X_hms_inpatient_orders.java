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
/** Generated Model - DO NOT CHANGE */
package zenith.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for hms_inpatient_orders
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_hms_inpatient_orders extends PO implements I_hms_inpatient_orders, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170911L;

    /** Standard Constructor */
    public X_hms_inpatient_orders (Properties ctx, int hms_inpatient_orders_ID, String trxName)
    {
      super (ctx, hms_inpatient_orders_ID, trxName);
      /** if (hms_inpatient_orders_ID == 0)
        {
			setafternoon (false);
// N
			setafternoon_issued (false);
			setevenning (false);
// N
			setevenning_issued (false);
			sethms_inpatient_orders_ID (0);
			setmed_dosage_type (null);
// S
			setmidday (false);
// N
			setmidday_issued (false);
			setmidmorning (false);
// N
			setmidmorning_issued (false);
			setmidnight (false);
// N
			setmidnight_issued (false);
			setmorning (false);
// N
			setmorning_issued (false);
			setorder_closed (false);
			setorder_issued (false);
			setorder_nullified (false);
			setorder_recieved (false);
			setorder_sent (false);
			setquantity (0);
			setstart_dose (false);
        } */
    }

    /** Load Constructor */
    public X_hms_inpatient_orders (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_hms_inpatient_orders[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set afternoon.
		@param afternoon afternoon	  */
	public void setafternoon (boolean afternoon)
	{
		set_Value (COLUMNNAME_afternoon, Boolean.valueOf(afternoon));
	}

	/** Get afternoon.
		@return afternoon	  */
	public boolean isafternoon () 
	{
		Object oo = get_Value(COLUMNNAME_afternoon);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set afternoon_issued.
		@param afternoon_issued afternoon_issued	  */
	public void setafternoon_issued (boolean afternoon_issued)
	{
		set_Value (COLUMNNAME_afternoon_issued, Boolean.valueOf(afternoon_issued));
	}

	/** Get afternoon_issued.
		@return afternoon_issued	  */
	public boolean isafternoon_issued () 
	{
		Object oo = get_Value(COLUMNNAME_afternoon_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set afternoon_qty.
		@param afternoon_qty afternoon_qty	  */
	public void setafternoon_qty (BigDecimal afternoon_qty)
	{
		set_Value (COLUMNNAME_afternoon_qty, afternoon_qty);
	}

	/** Get afternoon_qty.
		@return afternoon_qty	  */
	public BigDecimal getafternoon_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_afternoon_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set bed_no.
		@param bed_no bed_no	  */
	public void setbed_no (BigDecimal bed_no)
	{
		set_Value (COLUMNNAME_bed_no, bed_no);
	}

	/** Get bed_no.
		@return bed_no	  */
	public BigDecimal getbed_no () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_bed_no);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set daysgiven.
		@param daysgiven daysgiven	  */
	public void setdaysgiven (BigDecimal daysgiven)
	{
		set_Value (COLUMNNAME_daysgiven, daysgiven);
	}

	/** Get daysgiven.
		@return daysgiven	  */
	public BigDecimal getdaysgiven () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_daysgiven);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set dosetyp.
		@param dosetyp dosetyp	  */
	public void setdosetyp (String dosetyp)
	{
		set_Value (COLUMNNAME_dosetyp, dosetyp);
	}

	/** Get dosetyp.
		@return dosetyp	  */
	public String getdosetyp () 
	{
		return (String)get_Value(COLUMNNAME_dosetyp);
	}

	/** Set evenning.
		@param evenning evenning	  */
	public void setevenning (boolean evenning)
	{
		set_Value (COLUMNNAME_evenning, Boolean.valueOf(evenning));
	}

	/** Get evenning.
		@return evenning	  */
	public boolean isevenning () 
	{
		Object oo = get_Value(COLUMNNAME_evenning);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set evenning_issued.
		@param evenning_issued evenning_issued	  */
	public void setevenning_issued (boolean evenning_issued)
	{
		set_Value (COLUMNNAME_evenning_issued, Boolean.valueOf(evenning_issued));
	}

	/** Get evenning_issued.
		@return evenning_issued	  */
	public boolean isevenning_issued () 
	{
		Object oo = get_Value(COLUMNNAME_evenning_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set evenning_qty.
		@param evenning_qty evenning_qty	  */
	public void setevenning_qty (BigDecimal evenning_qty)
	{
		set_Value (COLUMNNAME_evenning_qty, evenning_qty);
	}

	/** Get evenning_qty.
		@return evenning_qty	  */
	public BigDecimal getevenning_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_evenning_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (BigDecimal Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Frequency);
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public BigDecimal getFrequency () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Frequency);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set hms_inpatient_orders ID.
		@param hms_inpatient_orders_ID hms_inpatient_orders ID	  */
	public void sethms_inpatient_orders_ID (int hms_inpatient_orders_ID)
	{
		if (hms_inpatient_orders_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_hms_inpatient_orders_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_hms_inpatient_orders_ID, Integer.valueOf(hms_inpatient_orders_ID));
	}

	/** Get hms_inpatient_orders ID.
		@return hms_inpatient_orders ID	  */
	public int gethms_inpatient_orders_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_inpatient_orders_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_hms_treatment_doc gethms_treatment_doc() throws RuntimeException
    {
		return (I_hms_treatment_doc)MTable.get(getCtx(), I_hms_treatment_doc.Table_Name)
			.getPO(gethms_treatment_doc_ID(), get_TrxName());	}

	/** Set hms_treatment_doc ID.
		@param hms_treatment_doc_ID hms_treatment_doc ID	  */
	public void sethms_treatment_doc_ID (int hms_treatment_doc_ID)
	{
		if (hms_treatment_doc_ID < 1) 
			set_Value (COLUMNNAME_hms_treatment_doc_ID, null);
		else 
			set_Value (COLUMNNAME_hms_treatment_doc_ID, Integer.valueOf(hms_treatment_doc_ID));
	}

	/** Get hms_treatment_doc ID.
		@return hms_treatment_doc ID	  */
	public int gethms_treatment_doc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_hms_treatment_doc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set itemcode.
		@param itemcode itemcode	  */
	public void setitemcode (String itemcode)
	{
		set_Value (COLUMNNAME_itemcode, itemcode);
	}

	/** Get itemcode.
		@return itemcode	  */
	public String getitemcode () 
	{
		return (String)get_Value(COLUMNNAME_itemcode);
	}

	/** Set lineamt.
		@param lineamt lineamt	  */
	public void setlineamt (BigDecimal lineamt)
	{
		set_Value (COLUMNNAME_lineamt, lineamt);
	}

	/** Get lineamt.
		@return lineamt	  */
	public BigDecimal getlineamt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_lineamt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Movement getM_Movement() throws RuntimeException
    {
		return (org.compiere.model.I_M_Movement)MTable.get(getCtx(), org.compiere.model.I_M_Movement.Table_Name)
			.getPO(getM_Movement_ID(), get_TrxName());	}

	/** Set Inventory Move.
		@param M_Movement_ID 
		Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID)
	{
		if (M_Movement_ID < 1) 
			set_Value (COLUMNNAME_M_Movement_ID, null);
		else 
			set_Value (COLUMNNAME_M_Movement_ID, Integer.valueOf(M_Movement_ID));
	}

	/** Get Inventory Move.
		@return Movement of Inventory
	  */
	public int getM_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** med_dosage_type AD_Reference_ID=1000021 */
	public static final int MED_DOSAGE_TYPE_AD_Reference_ID=1000021;
	/** Start Dose = S */
	public static final String MED_DOSAGE_TYPE_StartDose = "S";
	/** Continuing Dose = C */
	public static final String MED_DOSAGE_TYPE_ContinuingDose = "C";
	/** Discharge Dose = D */
	public static final String MED_DOSAGE_TYPE_DischargeDose = "D";
	/** Set med_dosage_type.
		@param med_dosage_type med_dosage_type	  */
	public void setmed_dosage_type (String med_dosage_type)
	{

		set_Value (COLUMNNAME_med_dosage_type, med_dosage_type);
	}

	/** Get med_dosage_type.
		@return med_dosage_type	  */
	public String getmed_dosage_type () 
	{
		return (String)get_Value(COLUMNNAME_med_dosage_type);
	}

	/** Set midday.
		@param midday midday	  */
	public void setmidday (boolean midday)
	{
		set_Value (COLUMNNAME_midday, Boolean.valueOf(midday));
	}

	/** Get midday.
		@return midday	  */
	public boolean ismidday () 
	{
		Object oo = get_Value(COLUMNNAME_midday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set midday_issued.
		@param midday_issued midday_issued	  */
	public void setmidday_issued (boolean midday_issued)
	{
		set_Value (COLUMNNAME_midday_issued, Boolean.valueOf(midday_issued));
	}

	/** Get midday_issued.
		@return midday_issued	  */
	public boolean ismidday_issued () 
	{
		Object oo = get_Value(COLUMNNAME_midday_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set midday_qty.
		@param midday_qty midday_qty	  */
	public void setmidday_qty (BigDecimal midday_qty)
	{
		set_Value (COLUMNNAME_midday_qty, midday_qty);
	}

	/** Get midday_qty.
		@return midday_qty	  */
	public BigDecimal getmidday_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_midday_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set midmorning.
		@param midmorning midmorning	  */
	public void setmidmorning (boolean midmorning)
	{
		set_Value (COLUMNNAME_midmorning, Boolean.valueOf(midmorning));
	}

	/** Get midmorning.
		@return midmorning	  */
	public boolean ismidmorning () 
	{
		Object oo = get_Value(COLUMNNAME_midmorning);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set midmorning_issued.
		@param midmorning_issued midmorning_issued	  */
	public void setmidmorning_issued (boolean midmorning_issued)
	{
		set_Value (COLUMNNAME_midmorning_issued, Boolean.valueOf(midmorning_issued));
	}

	/** Get midmorning_issued.
		@return midmorning_issued	  */
	public boolean ismidmorning_issued () 
	{
		Object oo = get_Value(COLUMNNAME_midmorning_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set midmorning_qty.
		@param midmorning_qty midmorning_qty	  */
	public void setmidmorning_qty (BigDecimal midmorning_qty)
	{
		set_Value (COLUMNNAME_midmorning_qty, midmorning_qty);
	}

	/** Get midmorning_qty.
		@return midmorning_qty	  */
	public BigDecimal getmidmorning_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_midmorning_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set midnight.
		@param midnight midnight	  */
	public void setmidnight (boolean midnight)
	{
		set_Value (COLUMNNAME_midnight, Boolean.valueOf(midnight));
	}

	/** Get midnight.
		@return midnight	  */
	public boolean ismidnight () 
	{
		Object oo = get_Value(COLUMNNAME_midnight);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set midnight_issued.
		@param midnight_issued midnight_issued	  */
	public void setmidnight_issued (boolean midnight_issued)
	{
		set_Value (COLUMNNAME_midnight_issued, Boolean.valueOf(midnight_issued));
	}

	/** Get midnight_issued.
		@return midnight_issued	  */
	public boolean ismidnight_issued () 
	{
		Object oo = get_Value(COLUMNNAME_midnight_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set midnight_qty.
		@param midnight_qty midnight_qty	  */
	public void setmidnight_qty (BigDecimal midnight_qty)
	{
		set_Value (COLUMNNAME_midnight_qty, midnight_qty);
	}

	/** Get midnight_qty.
		@return midnight_qty	  */
	public BigDecimal getmidnight_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_midnight_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set morning.
		@param morning morning	  */
	public void setmorning (boolean morning)
	{
		set_Value (COLUMNNAME_morning, Boolean.valueOf(morning));
	}

	/** Get morning.
		@return morning	  */
	public boolean ismorning () 
	{
		Object oo = get_Value(COLUMNNAME_morning);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set morning_issued.
		@param morning_issued morning_issued	  */
	public void setmorning_issued (boolean morning_issued)
	{
		set_Value (COLUMNNAME_morning_issued, Boolean.valueOf(morning_issued));
	}

	/** Get morning_issued.
		@return morning_issued	  */
	public boolean ismorning_issued () 
	{
		Object oo = get_Value(COLUMNNAME_morning_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set morning_qty.
		@param morning_qty morning_qty	  */
	public void setmorning_qty (BigDecimal morning_qty)
	{
		set_Value (COLUMNNAME_morning_qty, morning_qty);
	}

	/** Get morning_qty.
		@return morning_qty	  */
	public BigDecimal getmorning_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_morning_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set order_closed.
		@param order_closed order_closed	  */
	public void setorder_closed (boolean order_closed)
	{
		set_Value (COLUMNNAME_order_closed, Boolean.valueOf(order_closed));
	}

	/** Get order_closed.
		@return order_closed	  */
	public boolean isorder_closed () 
	{
		Object oo = get_Value(COLUMNNAME_order_closed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set order_issued.
		@param order_issued order_issued	  */
	public void setorder_issued (boolean order_issued)
	{
		set_Value (COLUMNNAME_order_issued, Boolean.valueOf(order_issued));
	}

	/** Get order_issued.
		@return order_issued	  */
	public boolean isorder_issued () 
	{
		Object oo = get_Value(COLUMNNAME_order_issued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set order_nullified.
		@param order_nullified order_nullified	  */
	public void setorder_nullified (boolean order_nullified)
	{
		set_Value (COLUMNNAME_order_nullified, Boolean.valueOf(order_nullified));
	}

	/** Get order_nullified.
		@return order_nullified	  */
	public boolean isorder_nullified () 
	{
		Object oo = get_Value(COLUMNNAME_order_nullified);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set order_recieved.
		@param order_recieved order_recieved	  */
	public void setorder_recieved (boolean order_recieved)
	{
		set_Value (COLUMNNAME_order_recieved, Boolean.valueOf(order_recieved));
	}

	/** Get order_recieved.
		@return order_recieved	  */
	public boolean isorder_recieved () 
	{
		Object oo = get_Value(COLUMNNAME_order_recieved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set order_sent.
		@param order_sent order_sent	  */
	public void setorder_sent (boolean order_sent)
	{
		set_Value (COLUMNNAME_order_sent, Boolean.valueOf(order_sent));
	}

	/** Get order_sent.
		@return order_sent	  */
	public boolean isorder_sent () 
	{
		Object oo = get_Value(COLUMNNAME_order_sent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set quantity.
		@param quantity quantity	  */
	public void setquantity (int quantity)
	{
		set_Value (COLUMNNAME_quantity, Integer.valueOf(quantity));
	}

	/** Get quantity.
		@return quantity	  */
	public int getquantity () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_quantity);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set start_dose.
		@param start_dose start_dose	  */
	public void setstart_dose (boolean start_dose)
	{
		set_Value (COLUMNNAME_start_dose, Boolean.valueOf(start_dose));
	}

	/** Get start_dose.
		@return start_dose	  */
	public boolean isstart_dose () 
	{
		Object oo = get_Value(COLUMNNAME_start_dose);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set startdose_qty.
		@param startdose_qty startdose_qty	  */
	public void setstartdose_qty (BigDecimal startdose_qty)
	{
		set_Value (COLUMNNAME_startdose_qty, startdose_qty);
	}

	/** Get startdose_qty.
		@return startdose_qty	  */
	public BigDecimal getstartdose_qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_startdose_qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set stopdate.
		@param stopdate stopdate	  */
	public void setstopdate (Timestamp stopdate)
	{
		set_Value (COLUMNNAME_stopdate, stopdate);
	}

	/** Get stopdate.
		@return stopdate	  */
	public Timestamp getstopdate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_stopdate);
	}

	/** Set unitprice.
		@param unitprice unitprice	  */
	public void setunitprice (BigDecimal unitprice)
	{
		set_Value (COLUMNNAME_unitprice, unitprice);
	}

	/** Get unitprice.
		@return unitprice	  */
	public BigDecimal getunitprice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_unitprice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set units_per_frequency.
		@param units_per_frequency units_per_frequency	  */
	public void setunits_per_frequency (BigDecimal units_per_frequency)
	{
		set_Value (COLUMNNAME_units_per_frequency, units_per_frequency);
	}

	/** Get units_per_frequency.
		@return units_per_frequency	  */
	public BigDecimal getunits_per_frequency () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_units_per_frequency);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ward_frequency AD_Reference_ID=1000030 */
	public static final int WARD_FREQUENCY_AD_Reference_ID=1000030;
	/** X 1 = 1 */
	public static final String WARD_FREQUENCY_X1 = "1";
	/** X 2 = 2 */
	public static final String WARD_FREQUENCY_X2 = "2";
	/** X 3 = 3 */
	public static final String WARD_FREQUENCY_X3 = "3";
	/** X 4 = 4 */
	public static final String WARD_FREQUENCY_X4 = "4";
	/** X 5 = 5 */
	public static final String WARD_FREQUENCY_X5 = "5";
	/** X 6 = 6 */
	public static final String WARD_FREQUENCY_X6 = "6";
	/** Set ward_frequency.
		@param ward_frequency ward_frequency	  */
	public void setward_frequency (String ward_frequency)
	{

		set_Value (COLUMNNAME_ward_frequency, ward_frequency);
	}

	/** Get ward_frequency.
		@return ward_frequency	  */
	public String getward_frequency () 
	{
		return (String)get_Value(COLUMNNAME_ward_frequency);
	}

	/** Set ward_name.
		@param ward_name ward_name	  */
	public void setward_name (BigDecimal ward_name)
	{
		set_Value (COLUMNNAME_ward_name, ward_name);
	}

	/** Get ward_name.
		@return ward_name	  */
	public BigDecimal getward_name () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ward_name);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}