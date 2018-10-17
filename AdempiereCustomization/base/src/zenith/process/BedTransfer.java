package zenith.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import zenith.model.MBed;
import zenith.model.MTreatmentDoc;

public class BedTransfer extends SvrProcess
{

	private int treatmentid = 0;
	private int bedID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("hms_ward_bed1_ID"))
			{
				bedID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		// NEVER TODO Auto-generated method stub
		treatmentid = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		updateBed();
		return null;
	}

	/*
	 * Update in-patient details of the instance this is basically the bed
	 * number
	 */

	/*
	 * set bed as occupied
	 */
	private void updateBed()
	{

		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentid, get_TrxName());

		int origanlBedId = doc.gethms_ward_bed1_ID();
		// if existing bed, then set to unoccupied
		if (origanlBedId > 0)
		{
			MBed bed1 = new MBed(getCtx(), origanlBedId, get_TrxName());
			bed1.setisoccupied(false);
			bed1.save();
		}

		// set the new bed to occupied
		MBed bed = new MBed(getCtx(), bedID, get_TrxName());
		bed.setisoccupied(true);
		bed.save();

		// assign the new bed to the instance --- treatment document
		doc.sethms_ward_bed1_ID(bedID);
		doc.save();
	}
}
