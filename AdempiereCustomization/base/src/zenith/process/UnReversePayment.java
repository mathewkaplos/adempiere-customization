package zenith.process;

import org.compiere.process.SvrProcess;

import zenith.model.HMSPayment;

public class UnReversePayment extends SvrProcess
{
	int payID = 0;

	@Override
	protected void prepare()
	{
		payID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		HMSPayment payment = new HMSPayment(getCtx(), payID, get_TrxName());
		payment.setpayment_reversed(false);
		payment.setreversal_reason("");
		payment.save();
		return null;
	}

}
