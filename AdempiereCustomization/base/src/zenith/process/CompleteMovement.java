package zenith.process;

import org.compiere.model.MMovement;
import org.compiere.process.SvrProcess;

public class CompleteMovement extends SvrProcess
{
	MMovement movement = null;

	@Override
	protected void prepare()
	{
		movement = new MMovement(getCtx(), getRecord_ID(), get_TrxName());

	}

	@Override
	protected String doIt() throws Exception
	{
		movement.prepareIt();
		movement.completeIt();
		movement.setProcessed(true);
		movement.setDocStatus("CO");
		movement.setIsApproved(true);
		movement.save();
		return null;
	}

}
