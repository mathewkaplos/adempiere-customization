package zenith.process;

import org.compiere.process.SvrProcess;

public class AbcScheduler extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		System.out.println("scheduled....");
		return null;
	}

}
