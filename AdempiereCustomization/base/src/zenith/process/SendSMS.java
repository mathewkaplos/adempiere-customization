package zenith.process;

import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.process.SvrProcess;

import zenith.model.X_hms_bulk_sms;
import zenith.util.smsSend;

public class SendSMS extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		MClient client = MClient.get(getCtx());

		X_hms_bulk_sms sms = new X_hms_bulk_sms(getCtx(), getRecord_ID(), get_TrxName());
		String message = sms.getmessage_body();
		String telNo = sms.gettel_no();
		int C_BPartner_ID = sms.getC_BPartner_ID();

		MBPartner partner = MBPartner.get(getCtx(), C_BPartner_ID);
		message = "Helo " + partner.getName() + ", " + message + ". From " + client.getName();
		smsSend.send(message, telNo);

		return "MESSAGE SEND SUCCESFULLY...";
	}

}
