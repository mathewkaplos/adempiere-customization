package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.HMSInvoicePayment;
import zenith.model.HMSPayment;
import zenith.model.HmsInvoice;	
import zenith.model.MBilling;

public class ReconcileInvoice extends SvrProcess
{
	//zenith.process.ReconcileInvoice
	private int hms_invoice_ID = 0;
	private HmsInvoice invoice = null;
	private BigDecimal PayAmt = Env.ZERO;
	private Timestamp DateTrx = null;
	private String TenderType = "";
	private String CheckNo = "";
	private String EftCheckNo = "";
	private MBilling[] bills = null;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("PayAmt"))
			{
				PayAmt = para[i].getParameterAsBigDecimal();
			} else if (name.equals("DateTrx"))
			{
				DateTrx = para[i].getParameterAsTimestamp();
			} else if (name.equals("TenderType"))
			{
				TenderType = para[i].getParameterAsString();
			} else if (name.equals("CheckNo"))
			{
				CheckNo = para[i].getParameterAsString();
			} else if (name.equals("EftCheckNo"))
			{
				EftCheckNo = para[i].getParameterAsString();
			}
		}
		// NEVER TODO Auto-generated method stub
		hms_invoice_ID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		invoice = new HmsInvoice(getCtx(), hms_invoice_ID, get_TrxName());
		getInvoiceLines();
		createInvoicePayment();
		completeInvoicePayment();
		allocatePayment();

		return null;
	}

	private void completeInvoicePayment()
	{
		invoice.setPaidAmt(invoice.getPaidAmt().add(PayAmt));
		invoice.setBalance(invoice.getBalance().subtract(PayAmt));
		invoice.save();
	}

	private void createInvoicePayment()
	{
		HMSInvoicePayment ip = new HMSInvoicePayment(getCtx(), 0, get_TrxName());
		ip.sethms_invoice_ID(hms_invoice_ID);
		ip.setPayAmt(PayAmt);
		ip.setTenderType(TenderType);
		ip.setDateTrx(DateTrx);
		ip.setEftCheckNo(EftCheckNo);
		ip.setCheckNo(CheckNo);
		ip.setC_BPartner_ID(invoice.getC_BPartner_ID());
		ip.save();
	}

	private void getInvoiceLines()
	{
		List<MBilling> list = new ArrayList<MBilling>();
		String sql = "SELECT hms_billing_ID FROM adempiere.hms_invoice_line  WHERE hms_invoice_ID =" + hms_invoice_ID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int hms_billing_ID = rs.getInt(1);
				MBilling billing = new MBilling(getCtx(), hms_billing_ID, get_TrxName());
				list.add(billing);
			}

		} catch (Exception e)
		{

		} finally
		{
			try
			{
				stm.close();
				stm = null;
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		bills = list.toArray(new MBilling[list.size()]);
	}

	private void allocatePayment()
	{
		processAllocation(bills, PayAmt);
	}

	/**
	 * @param bills
	 * @param _amt
	 */
	private void processAllocation(MBilling[] bills, BigDecimal _amt)
	{
		//HMSPayment payment = makeNewPayment();
		
		BigDecimal amt = _amt;
		for (int i = 0; i < bills.length; i++)
		{
			if (amt.compareTo(Env.ZERO) < 1)
				break;
			MBilling bill = bills[i];
			if (bill.ispaid())
				continue;
			try
			{
				BigDecimal balance = bill.getBalance();
				bill.setprevious_balance(balance);
				bill.save();
				if (amt.compareTo(balance) < 1)
				{
					// bill.setcashAmt(bill.getcashAmt().add(amt));
					bill.setBalance(bill.getBalance().subtract(amt));
					bill.setPaidAmt(bill.getPaidAmt().add(amt));
				//	bill.sethms_payment_ID(payment.gethms_payment_ID());
					// bill.setmpesaAmt(Env.ZERO);
					if (amt.compareTo(balance) == 0)
					{
						//bill.setpaid(true);
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
					//bill.setpaid(true);
					bill.setpay(false);
					//bill.sethms_payment_ID(payment.gethms_payment_ID());
					// bill.setcashAmt(bill.getcashAmt().add(bill.getBalance()));
					bill.setPaidAmt(bill.getPaidAmt().add(bill.getBalance()));

					// bill.setmpesaAmt(Env.ZERO);
					bill.save();
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
	/**	payment.setTenderType("X");
		payment.setcashAmt(_amt);
		payment.setmpesaAmt(Env.ZERO);
		payment.setPayAmt(_amt);
		payment.setBalance(Env.ZERO);
		payment.setdeductiontamount(Env.ZERO);
		payment.setbill_amount(_amt);
		payment.settotal_bill(_amt);
		payment.setprepaid_amount(Env.ZERO);
		payment.settender_amt(_amt);
		payment.setdone(true);
		payment.setis_invoice(true);
		payment.save();
		**/
	}

	private HMSPayment makeNewPayment123()
	{
		HMSPayment payment = new HMSPayment(Env.getCtx(), 0, null);
		payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
		payment.sethms_treatment_doc_ID(invoice.gethms_treatment_doc_ID());
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
		return payment;
	}
	//
}
