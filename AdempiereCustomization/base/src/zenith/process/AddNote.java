package zenith.process;

import org.compiere.process.SvrProcess;

public class AddNote extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		addNote();
		return null;
	}

	private void addNote()
	{

	}

}
