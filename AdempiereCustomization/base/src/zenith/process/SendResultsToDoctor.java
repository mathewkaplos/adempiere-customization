package zenith.process;

import java.sql.Timestamp;

import org.compiere.process.SvrProcess;

import zenith.model.MSpecimenRequest;

public class SendResultsToDoctor extends SvrProcess
{
	
	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		MSpecimenRequest req = new MSpecimenRequest(getCtx(), getRecord_ID(), get_TrxName());
		req.setsend_date(new Timestamp(System.currentTimeMillis()));
		req.setsend_time(new Timestamp(System.currentTimeMillis()));
		req.save();
		return null;
	}

}
