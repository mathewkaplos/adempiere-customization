package org.zenith.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class CashierCallout extends CalloutEngine
{
	public String patientName(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null || (int) value == 0)
		{

			Object oo = mTab.getValue("hms_treatment_doc_id");
			if (oo != null)
			{
				String trx = Trx.createTrxName();
				int docID = (int) oo;
				String sql = "SELECT C_BPartner_ID FROM adempiere.hms_treatment_doc where hms_treatment_doc_id= "
						+ docID;
				int C_BPartner_ID = DB.getSQLValue(trx, sql);
				mTab.setValue("C_BPartner_ID", C_BPartner_ID);
				Trx.get(trx, false).close();
			}
		}
		return "";
	}

	public String amounts(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		// RoundedPayAmt
		// tender_amt
		// PayAmt
		// Balance
		BigDecimal amt_topay = Env.ZERO;

		BigDecimal PayAmt = (BigDecimal) mTab.getValue("PayAmt");
		BigDecimal DiscountAmt = (BigDecimal) mTab.getValue("DiscountAmt");
		// BigDecimal RoundedPayAmt = (BigDecimal)
		// mTab.getValue("RoundedPayAmt");
		BigDecimal tender_amt = (BigDecimal) mTab.getValue("tender_amt");
		BigDecimal Balance = (BigDecimal) mTab.getValue("Balance");
		if (mTab.getAD_Tab_ID() == 1000182) // cashier
			amt_topay = (BigDecimal) mTab.getValue("amt_topay");
		else if (mTab.getAD_Tab_ID() == 1000220) // direct sales
			amt_topay = (BigDecimal) mTab.getValue("amt_topay_direct_sale");
		// BigDecimal amt_topay_rounded = (BigDecimal)
		// mTab.getValue("amt_topay_rounded");

		// System.out.println(RoundedPayAmt.doubleValue());

		// Changed Column
		String colName = mField.getColumnName();
		if (colName.equals("tender_amt"))
		{
			if (tender_amt.compareTo(amt_topay) == 1)
			{

				mTab.setValue("RoundedPayAmt", amt_topay);
				Balance = new BigDecimal(tender_amt.doubleValue() - amt_topay.doubleValue());
				mTab.setValue("PayAmt", amt_topay);
				mTab.setValue("Balance", Balance);
			}
			if (tender_amt.compareTo(amt_topay) == 0)
			{

				mTab.setValue("RoundedPayAmt", amt_topay);
				mTab.setValue("PayAmt", amt_topay);
				mTab.setValue("Balance", Env.ZERO);
			}
			if (tender_amt.compareTo(amt_topay) == -1)
			{
				BigDecimal diff = new BigDecimal(amt_topay.doubleValue() - tender_amt.doubleValue());

				mTab.setValue("RoundedPayAmt", new BigDecimal(amt_topay.doubleValue() - diff.doubleValue()));

				mTab.setValue("PayAmt", new BigDecimal(amt_topay.doubleValue() - diff.doubleValue()));
				mTab.setValue("Balance", Env.ZERO.subtract(diff));
			}
			mTab.setValue("DiscountAmt", Env.ZERO);
		}
		if (colName.equals("DiscountAmt"))
		{
			if (PayAmt.doubleValue() != 0.0 && PayAmt.doubleValue() != 0.0)
			{
				BigDecimal d_value = (BigDecimal) value;
				mTab.setValue("RoundedPayAmt", amt_topay.subtract(d_value));
				mTab.setValue("PayAmt", amt_topay.subtract(d_value));
				mTab.setValue("Balance", Balance.add(d_value));

			}
		}
		if (colName.equals("amt_topay"))
		{
			mTab.setValue("RoundedPayAmt", Env.ZERO);
			mTab.setValue("PayAmt", Env.ZERO);
			mTab.setValue("Balance", Env.ZERO);
			mTab.setValue("DiscountAmt", Env.ZERO);
			mTab.setValue("tender_amt", Env.ZERO);
		}

		return "";
	}
}
