package zenith.process;

import org.compiere.process.SvrProcess;

import zenith.model.MTreatmentDoc;

public class ExitTreatment extends SvrProcess
{
	MTreatmentDoc doc = null;

	@Override
	protected void prepare()
	{
		doc = new MTreatmentDoc(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception
	{
		doc.exit();
		return null;
	}
	//    zenith.process.ExitTreatment
}
