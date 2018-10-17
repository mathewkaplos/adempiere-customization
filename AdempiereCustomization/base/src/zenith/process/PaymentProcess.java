package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.HMSPayment;
import zenith.model.MBilling;
import zenith.model.MSpecimenRequest;
import zenith.model.MTreatmentDoc;
import zenith.util.DateUtil;

public class PaymentProcess extends SvrProcess
{
	int payID = 0;
	int C_BPartner_ID = 0;
	int treatID = 0;
	int C_Payment_ID = 0;
	HMSPayment pay = null;
	MTreatmentDoc doc = null;
	MBilling[] bills = null;
	// private BigDecimal total_bill_amount = Env.ZERO;
	// private BigDecimal bill_amount = Env.ZERO;
	// private BigDecimal deduction = Env.ZERO;
	// private BigDecimal prepaid_amount = Env.ZERO;

	private HMSPayment[] prepayments = null;

	@Override
	protected void prepare()
	{

		payID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		pay = new HMSPayment(getCtx(), payID, get_TrxName());
		if (haskBalance())
		{
			raiseBalanceError();
			return null;
		} else
		{
			start();
			// pay.setdone(true);
			// pay.save();
			if (pay.is_direct_sale())
			{
				updateStock();
			}
			finish();
		}
		return null;
	}

	private boolean haskBalance()
	{
		boolean hasBal = false;
		if (HmsSetup.allowBalance())
		{
			return false;
		} else
		{
			if (pay.getBalance().compareTo(Env.ZERO) != 0)
			{
				return true;
			}
		}

		return hasBal;
	}

	private void raiseBalanceError()
	{
		JOptionPane.showMessageDialog(null,
				new JLabel(
						"<html><h1><font color='red'>Bill Amount And Pay Amount Does NOT Match! Please Try Again!</font></h1></html>"),
				"Not Completed!", JOptionPane.ERROR_MESSAGE);
	}

	private void updateStock()
	{
		// MBilling[] bills = getLines();
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			bill.setissued(true);
			bill.save();
			updateStock(bill.getM_Product_ID(), bill.getQty());
		}
	}

	int M_Warehouse_ID = 1000007; // pharmacy
	int M_Locator_ID = 1000007; // pharmacy
	int M_AttributeSetInstance_ID = 0;

	private void updateStock(int M_Product_ID, BigDecimal Qty)
	{
		Map<Integer, BigDecimal> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		String sql = "select m_attributesetinstance_id,qtyonhand from adempiere.m_storage where m_product_id= "
				+ M_Product_ID + " and m_locator_id = " + M_Locator_ID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int m_attributesetinstance_id = rs.getInt(1);
				BigDecimal qtyonhand = rs.getBigDecimal(2);
				map.put(m_attributesetinstance_id, qtyonhand);
				list.add(m_attributesetinstance_id);
			}
		} catch (Exception e)
		{

		}
		BigDecimal required = Qty;
		for (Integer asi : list)
		{
			if (required.compareTo(map.get(asi)) == 1)
			{
				updateStorage(M_Product_ID, map.get(asi), asi);
				required = required.subtract(map.get(asi));
			} else if (required.compareTo(map.get(asi)) == 0)
			{
				updateStorage(M_Product_ID, map.get(asi), asi);
				required = Env.ZERO;
				break;
			} else if (required.compareTo(map.get(asi)) == -1)
			{
				updateStorage(M_Product_ID, required, asi);
				required = Env.ZERO;
				break;
			}
		}
		if (required.compareTo(Env.ZERO) == 1)
		{
			updateStorage(M_Product_ID, required, 0);
		}
	}

	private void updateStorage(int M_Product_ID, BigDecimal Qty, int asi)
	{
		MStorage.add(Env.getCtx(), M_Warehouse_ID, M_Locator_ID, M_Product_ID, asi, 0, Qty, Env.ZERO, Env.ZERO, null);
	}

	private void start()
	{

		pay.setCreated(zenith.util.DateUtil.newTimestamp());
		pay.setUpdated(zenith.util.DateUtil.newTimestamp());
		pay.save();
		treatID = pay.gethms_treatment_doc_ID();
		doc = new MTreatmentDoc(getCtx(), treatID, get_TrxName());
		C_BPartner_ID = pay.getC_BPartner_ID();

		getAllPrepaymentAmount();

		bills = getLines();
		BigDecimal mpesaAmt = pay.getmpesaAmt();
		BigDecimal cashAmt = pay.getcashAmt();
		BigDecimal payAmt = pay.getPayAmt();

		boolean hasBalance = (pay.getBalance().signum() == 1);

		if (bills.length > 0)
		{
			if (pay.getTenderType().equalsIgnoreCase("X")) // cash only
			{
				if (!hasBalance)
				{
					processCashCompletePayment(bills);
				} else
				{
					processCashInCompletePayment(bills, cashAmt.add(pay.getprepaid_amount()));
				}

			} else if (pay.getTenderType().equalsIgnoreCase("M")) // M-Pesa only
			{
				if (!hasBalance)
				{
					processMPesaCompletePayment(bills);

				} else
				{
					processMPesaInCompletePayment(bills, mpesaAmt.add(pay.getprepaid_amount()));
				}

			} else if (pay.getTenderType().equalsIgnoreCase("I")) // Mixed,
			{
				processMixedPayment(bills, cashAmt, mpesaAmt);
			}
			if (pay.getdeductiontamount().compareTo(Env.ZERO) == 1)
			{
				processDeduction(bills, pay.getdeductiontamount());
			}
			for (int j = 0; j < bills.length; j++)
			{
				MBilling bill = bills[j];
				bill.setpay_date(DateUtil.newTimestamp());
				bill.save();
			}

		}
	}

	private void processMixedPayment(MBilling[] bills2, BigDecimal cashAmt, BigDecimal mpesaAmt)
	{
		processMPesaInCompletePayment(bills2, mpesaAmt);
		processCashInCompletePayment(bills2, cashAmt.add(pay.getprepaid_amount()));
	}

	/**
	 * @param bills
	 */
	private void processCashCompletePayment(MBilling[] bills)
	{
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			if (bill.ispaid())
				continue;
			try
			{
				bill.setpaid(true);
				bill.setpay(false);
				bill.save();
				bill.sethms_payment_ID(payID);

				bill.setcashAmt(bill.getcashAmt().add(bill.getBalance()));
				bill.setPaidAmt(bill.getPaidAmt().add(bill.getBalance()));
				// bill.setmpesaAmt(Env.ZERO);
				bill.setBalance(Env.ZERO);
				bill.setpay_date(DateUtil.newTimestamp());
				bill.save();
				updateDocument(bill);

			} catch (AdempiereException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @param bills
	 * @param _amt
	 */
	private void processCashInCompletePayment(MBilling[] bills, BigDecimal _amt)
	{
		BigDecimal amt = _amt;
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			if (bill.ispaid())
				continue;
			try
			{
				BigDecimal balance = bill.getBalance();
				bill.sethms_payment_ID(payID);
				if (amt.compareTo(balance) < 1)
				{
					bill.setcashAmt(bill.getcashAmt().add(amt));
					bill.setBalance(bill.getBalance().subtract(amt));
					bill.setPaidAmt(bill.getPaidAmt().add(amt));
					// bill.setmpesaAmt(Env.ZERO);
					if (amt.compareTo(balance) == 0)
					{
						bill.setpaid(true);
						bill.setpay(false);
					} else
					{
						bill.setpaid(false);
						bill.setpay(true);
					}
					bill.setpay_date(DateUtil.newTimestamp());
					bill.save();
					break;
				} else
				{
					bill.setpaid(true);
					bill.setpay(false);
					bill.sethms_payment_ID(payID);

					bill.setcashAmt(bill.getcashAmt().add(bill.getBalance()));
					bill.setPaidAmt(bill.getPaidAmt().add(bill.getBalance()));

					// bill.setmpesaAmt(Env.ZERO);
					bill.setpay_date(DateUtil.newTimestamp());
					bill.save();

					updateDocument(bill);
					amt = amt.subtract(bill.getBalance());
					bill.setBalance(Env.ZERO);
					bill.save();
				}

			} catch (AdempiereException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @param bills
	 */
	private void processMPesaCompletePayment(MBilling[] bills)
	{
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			if (bill.ispaid())
				continue;
			try
			{
				bill.setpaid(true);
				bill.setpay(false);
				bill.save();
				bill.sethms_payment_ID(payID);

				// bill.setcashAmt(Env.ZERO);
				bill.setPaidAmt(bill.getPaidAmt().add(bill.getBalance()));
				bill.setmpesaAmt(bill.getmpesaAmt().add(bill.getBalance()));
				bill.setBalance(Env.ZERO);
				bill.setpay_date(DateUtil.newTimestamp());
				bill.save();
				updateDocument(bill);

			} catch (AdempiereException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @param bills
	 * @param _amt
	 */
	private void processMPesaInCompletePayment(MBilling[] bills, BigDecimal _amt)
	{
		BigDecimal amt = _amt;
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			if (bill.ispaid())
				continue;
			try
			{
				BigDecimal balance = bill.getBalance();
				bill.sethms_payment_ID(payID);
				if (amt.compareTo(balance) < 1)
				{

					// bill.setcashAmt(Env.ZERO);
					bill.setBalance(bill.getBalance().subtract(amt));
					bill.setPaidAmt(bill.getPaidAmt().add(amt));
					bill.setmpesaAmt(bill.getmpesaAmt().add(amt));

					if (amt.compareTo(balance) == 0)
					{
						bill.setpaid(true);
						bill.setpay(false);
					} else
					{
						bill.setpaid(false);
						bill.setpay(true);
					}
					bill.setpay_date(DateUtil.newTimestamp());
					bill.save();
					break;
				} else
				{
					bill.setpaid(true);
					bill.setpay(false);
					bill.sethms_payment_ID(payID);

					bill.setmpesaAmt(bill.getmpesaAmt().add(bill.getBalance()));
					bill.setPaidAmt(bill.getPaidAmt().add(bill.getBalance()));

					// bill.setcashAmt(Env.ZERO);
					bill.save();
					updateDocument(bill);
					amt = amt.subtract(bill.getBalance());
					bill.setBalance(Env.ZERO);
					bill.setpay_date(DateUtil.newTimestamp());
					bill.save();
				}

			} catch (AdempiereException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void processDeduction(MBilling[] bills, BigDecimal _amt)
	{
		BigDecimal amt = _amt;
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			if (bill.ispaid())
				continue;
			try
			{
				BigDecimal balance = bill.getBalance();
				bill.sethms_payment_ID(payID);
				if (amt.compareTo(balance) < 1)
				{
					bill.setDeductiontAmount((bill.getDeductiontAmount().add(amt)));
					bill.setBalance(bill.getBalance().subtract(amt));
					/// bill.setPaidAmt(bill.getPaidAmt().add(amt));
					// bill.setmpesaAmt(Env.ZERO);
					if (amt.compareTo(balance) == 0)
					{
						bill.setpaid(true);
						bill.setpay(false);
					} else
					{
						bill.setpaid(false);
						bill.setpay(true);
					}
					bill.save();
					break;
				} else
				{
					bill.setpaid(true);
					bill.setpay(false);
					bill.sethms_payment_ID(payID);

					/// bill.setcashAmt(bill.getcashAmt().add(bill.getBalance()));
					// bill.setPaidAmt(bill.getPaidAmt().add(bill.getBalance()));
					bill.setDeductiontAmount((bill.getDeductiontAmount().add(bill.getBalance())));
					// bill.setmpesaAmt(Env.ZERO);
					bill.save();

					updateDocument(bill);
					amt = amt.subtract(bill.getBalance());
					bill.setBalance(Env.ZERO);
					bill.save();
				}

			} catch (AdempiereException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void updateDocument(MBilling bill)
	{
		if (bill.getitem_type().equalsIgnoreCase("PRO"))
		{

		} else if (bill.getitem_type().equalsIgnoreCase("CON"))
		{
			doc.setto_doctor(true);
			doc.save();
		} else if (bill.getitem_type().equalsIgnoreCase("LAB"))
		{
			String select = "SELECT hms_specimen_requests_id FROM   hms_specimen_requests  WHERE hms_billing_id ="
					+ bill.get_ID();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(select, get_TrxName());
				rs = pstmt.executeQuery();

				if (rs.next())
				{
					int hms_specimen_requests_id = rs.getInt(1);
					MSpecimenRequest req = new MSpecimenRequest(getCtx(), hms_specimen_requests_id, get_TrxName());
					req.setpaid(true);
					req.save();
				}
			} catch (Exception e)
			{

			}

		}
	}

	/**
	 * @param bills
	 */
	private void updateDocuments(MBilling[] bills)
	{
		for (int i = 0; i < bills.length; i++)
		{
			MBilling bill = bills[i];
			try
			{
				bill.setpaid(true);
				bill.setpay(false);
				bill.save();
				bill.sethms_payment_ID(payID);
				bill.save();

			} catch (AdempiereException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			updateDocument(bill);
		}
	}

	/**
	 * @return
	 */
	MBilling[] getLines()
	{
		StringBuilder select = new StringBuilder();
		select.append("SELECT * FROM   hms_billing  WHERE  pay ='Y' ");
		if (pay.is_direct_sale())
			select.append("  AND hms_payment_ID =" + payID);
		else
			select.append(" AND hms_treatment_doc_id =" + treatID);
		// StringBuilder select = "SELECT * FROM hms_billing WHERE
		// hms_treatment_doc_id =" + treatID + " AND pay ='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MBilling> list = new ArrayList<MBilling>();
		try
		{
			pstmt = DB.prepareStatement(select.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, get_TrxName());
				bill.setprevious_balance(bill.getBalance());
				bill.save();
				list.add(bill);
			}
		} catch (Exception e)
		{

		}

		MBilling[] billArray = new MBilling[list.size()];
		return list.toArray(billArray);

	}

	/**
	 * 
	 */
	private void finish()
	{
		HMSPayment pay = new HMSPayment(getCtx(), payID, get_TrxName());
		pay.setdone(true);
		pay.save();
		updateAllPrepayments();
	}

	private void updateAllPrepayments()
	{
		for (int i = 0; i < prepayments.length; i++)
		{
			HMSPayment payment = prepayments[i];
			payment.setprepayment_used(true);
			payment.setuse(false);
			payment.setparent_payment_id(pay.get_ID());
			payment.save();
		}

	}

	private BigDecimal getAllPrepaymentAmount()
	{
		BigDecimal total = Env.ZERO;
		List<HMSPayment> prepays = new ArrayList<>();
		String sql = "SELECT * FROM hms_payment WHERE isPrepayment ='Y' AND use='Y' AND prepayment_used='N' AND done='Y' AND hms_treatment_doc_ID ="
				+ treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			while (rs.next())
			{
				HMSPayment payment = new HMSPayment(Env.getCtx(), rs, null);

				total = total.add(payment.getPayAmt());
				prepays.add(payment);
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

		prepayments = prepays.toArray(new HMSPayment[prepays.size()]);
		return new BigDecimal(total.stripTrailingZeros().toPlainString());
	}

}
