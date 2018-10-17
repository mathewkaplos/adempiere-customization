package zenith.process;

import org.compiere.process.SvrProcess;

import zenith.model.LabDocument;

public class TestLab extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		// 1000000
		// 1000009
		LabDocument ld = new LabDocument(getCtx());
		//ld.newRequest(1000009, 1000000);
		return null;
	}

}
