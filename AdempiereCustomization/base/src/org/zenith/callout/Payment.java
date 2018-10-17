package org.zenith.callout;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.HMSPayment;
import zenith.model.MTreatmentDoc;
import zenith.process.CreateHospitalDefaults;

public class Payment extends CalloutEngine
{
	public String cash(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		BigDecimal cash = (BigDecimal) mTab.getValue("cashamt");
		BigDecimal mpesa = (BigDecimal) mTab.getValue("mpesaamt");
		BigDecimal prepayment = (BigDecimal) mTab.getValue("prepayment_amt");
		BigDecimal total = cash.add(mpesa).add(Env.ZERO);
		mTab.setValue("tender_amt", total);
		/// mTab.setValue("balance", total);
		return "";
	}

	public String tenderType(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		mTab.setValue("cashamt", Env.ZERO);
		mTab.setValue("mpesaamt", Env.ZERO);
		return "";
	}

	public String usePrepayment(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		// int treatID = (Integer) mTab.getValue("hms_treatment_doc_ID");
		// BigDecimal prepayment_amt = getAllPrepayments(treatID);
		// updatePrepaymentField(treatID, prepayment_amt);

		return "";
	}

	public String pay(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		int treatID = (Integer) mTab.getValue("hms_treatment_doc_ID");
		int C_BPartner_ID = (Integer) mTab.getValue("C_BPartner_ID");

		if (draftedPaymentExist(treatID))
		{

		} else
		{
			makeNewPayment(treatID, C_BPartner_ID);
		}

		return "";
	}

	private void makeNewPayment(int treatID, int C_BPartner_ID)
	{
		HMSPayment payment = new HMSPayment(Env.getCtx(), 0, null);
		payment.setC_BPartner_ID(C_BPartner_ID);
		payment.sethms_treatment_doc_ID(treatID);
		payment.setTenderType("X");
		payment.setcashAmt(Env.ZERO);
		payment.setmpesaAmt(Env.ZERO);
		payment.setPayAmt(Env.ZERO);
		payment.setBalance(Env.ZERO);
		payment.setdeductiontamount(Env.ZERO);
		payment.setbill_amount(Env.ZERO);
		payment.settotal_bill(Env.ZERO);
		payment.setprepaid_amount(Env.ZERO);
		payment.settender_amt(Env.ZERO);
		payment.save();
	}

	public String refresh1234(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		BigDecimal cash = (BigDecimal) mTab.getValue("cashamt");
		BigDecimal mpesa = (BigDecimal) mTab.getValue("mpesaamt");
		BigDecimal total = cash.add(mpesa);
		mTab.setValue("tender_amt", total);
		return "";
	}

	private BigDecimal getAllPrepayments(int treatID)
	{
		BigDecimal total = Env.ZERO;
		String sql = "SELECT payamt from hms_payment WHERE isPrepayment ='Y' AND use='Y' AND prepayment_used='N' AND hms_treatment_doc_ID ="
				+ treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			while (rs.next())
			{
				BigDecimal payamt = rs.getBigDecimal(1);
				total = total.add(payamt);

			}

		} catch (Exception e)
		{

		} finally
		{

			try
			{
				stm.close();
				DB.close(rs);
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new BigDecimal(total.stripTrailingZeros().toPlainString());
	}

	private boolean draftedPaymentExist(int treatID)
	{
		boolean exists = false;
		String sql = "SELECT * from hms_payment WHERE done ='N' AND hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				exists = true;

			}

		} catch (Exception e)
		{

		} finally
		{

			try
			{
				stm.close();
				DB.close(rs);
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return exists;
	}

	private void updatePrepaymentField(int treatID, BigDecimal prepayment_amt)
	{
		String sql = "SELECT * FROM hms_payment WHERE isPrepayment='N' AND hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			while (rs.next())
			{
				HMSPayment payment = new HMSPayment(Env.getCtx(), rs, null);
				payment.setprepayment_amt(prepayment_amt);
				payment.save();

			}

		} catch (Exception e)
		{

		} finally
		{

			try
			{
				stm.close();
				DB.close(rs);
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String refresh(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{

		// if (value == null)
		// return "";
		// RoundedPayAmt
		// tender_amt
		// PayAmt
		// Balance

		boolean isPrepayment = mTab.getValueAsBoolean("isPrepayment");
		if (isPrepayment)
			return "";
		BigDecimal cash = (BigDecimal) mTab.getValue("cashamt");
		BigDecimal mpesa = (BigDecimal) mTab.getValue("mpesaamt");
		BigDecimal prepayment = (BigDecimal) mTab.getValue("prepayment_amt");
		BigDecimal deduction = (BigDecimal) mTab.getValue("DeductiontAmount");
		BigDecimal total = cash.add(mpesa).add(Env.ZERO);

		BigDecimal amt_topay = Env.ZERO;

		// if (mTab.getAD_Tab_ID() == 1000182) // cashier
		amt_topay = (BigDecimal) mTab.getValue("amt_topay");
		// else if (mTab.getAD_Tab_ID() == 1000220) // direct sales
		// amt_topay = (BigDecimal) mTab.getValue("amt_topay_direct_sale");

		mTab.setValue("tender_amt", total);
		mTab.setValue("prepaid_amount", prepayment);
		mTab.setValue("PayAmt", total);
		mTab.setValue("total_bill", amt_topay);
		BigDecimal bill_amount = amt_topay.subtract(deduction);
		mTab.setValue("bill_amount", bill_amount);

		BigDecimal balance = amt_topay.subtract(total).subtract(deduction).subtract(prepayment);
		mTab.setValue("Balance", balance);
		// Changed Column

		mTab.setValue("bill_amount", amt_topay.subtract(prepayment).subtract(deduction));

		// deactivated(mTab, value, PayAmt, tender_amt, Balance, amt_topay,
		// colName);

		return "";
	}

	private void deactivated(GridTab mTab, Object value, BigDecimal PayAmt, BigDecimal tender_amt, BigDecimal Balance,
			BigDecimal amt_topay, String colName)
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
	}
}
