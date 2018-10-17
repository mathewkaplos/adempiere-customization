package zenith.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import zenith.model.HmsInvoice;
import zenith.model.HmsInvoiceLine;
import zenith.model.MBilling;

public class ReverseInvoice extends SvrProcess
{

	int hms_invoice_ID = 0;
	HmsInvoice _invoice = null;

	String reversal_reason = "";

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("reversal_reason"))
				reversal_reason = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		hms_invoice_ID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		reverse();
		updateBills();
		return null;
	}

	private void reverse()
	{
		HmsInvoice invoice = new HmsInvoice(getCtx(), hms_invoice_ID, get_TrxName());

		invoice.setcompleted(false);
		invoice.setreversal_reason(reversal_reason);
		invoice.setreversed(true);
		invoice.setdate_completed(null);
		invoice.save();
		_invoice = invoice;
	}

	private void updateBills()
	{
		HmsInvoiceLine[] lines = _invoice.getLines();
		for (int i = 0; i < lines.length; i++)
		{
			MBilling bill = new MBilling(getCtx(), lines[i].gethms_billing_ID(), get_TrxName());
			bill.setinvoiced(false);
			bill.setpaid(false);
			bill.setpay(true);
			if (CreateHospitalDefaults.INVOICE_TYPE_PATIENT.equalsIgnoreCase(_invoice.getinvoice_type()))
			{
				// do nothing, for cash patients
			} else // insurance patients
			{
				bill.setBalance(bill.getLineNetAmt());
				bill.setPaidAmt(Env.ZERO);
				bill.setcashAmt(Env.ZERO);
				bill.setmpesaAmt(Env.ZERO);
			}
			bill.save();
		}
	}
}
