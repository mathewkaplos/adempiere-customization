package zenith.process;

import org.compiere.model.MBPartner;
import org.compiere.process.SvrProcess;

public class ReactivateBooking extends SvrProcess
{
	MBPartner bp = null;

	@Override
	protected void prepare()
	{
		bp = new MBPartner(getCtx(), getRecord_ID(), get_TrxName());

	}

	@Override
	protected String doIt() throws Exception
	{
		updateBpartner();
		return null;
	}

	private void updateBpartner()
	{
		if (bp != null)
		{
			bp.setIsBooked(false);
			bp.save();
		}

	}

}
