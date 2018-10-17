package zenith.process;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import zenith.model.HmsInvoice;
import zenith.model.HmsInvoiceLine;
import zenith.model.MBilling;

public class CompleteInvoice extends SvrProcess
{
	int hms_invoice_ID = 0;
	HmsInvoice _invoice = null;

	@Override
	protected void prepare()
	{
		hms_invoice_ID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		complete();
		updateBills();
		allocatePrePayement();
		return null;
	}

	private void complete()
	{
		HmsInvoice invoice = new HmsInvoice(getCtx(), hms_invoice_ID, get_TrxName());

		invoice.setcompleted(true);
		invoice.setdate_completed(new Timestamp(System.currentTimeMillis()));
		invoice.setreversed(false);
		invoice.setreversal_reason("");
		invoice.save();
		_invoice = invoice;
	}

	private void updateBills()
	{
		HmsInvoiceLine[] lines = _invoice.getLines();
		for (int i = 0; i < lines.length; i++)
		{
			MBilling bill = new MBilling(getCtx(), lines[i].gethms_billing_ID(), get_TrxName());
			bill.setinvoiced(true);
			// bill.setpaid(true);
			bill.setpay(false);
			bill.save();

		}
	}

	private void allocatePrePayement()
	{
		HmsInvoiceLine[] lines = _invoice.getLines();
		BigDecimal amt = _invoice.getprepay_amt().add(_invoice.getPaidAmt());
		for (int i = 0; i < lines.length; i++)
		{
			MBilling bill = new MBilling(getCtx(), lines[i].gethms_billing_ID(), get_TrxName());
			try
			{
				BigDecimal balance = bill.getBalance();
				if (amt.compareTo(balance) < 1)
				{
					// bill.setcashAmt(bill.getcashAmt().add(amt));
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
					bill.save();
					break;
				} else
				{
					bill.setpaid(true);
					bill.setpay(false);

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
	}
}
