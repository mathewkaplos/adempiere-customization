package zenith.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MBed;
import zenith.model.MTreatmentDoc;

public class WardTransfer extends SvrProcess
{
	private int treatmentid = 0;
	private int wardID = 0;
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
			if (name.equals("hms_ward1_ID"))
			{
				wardID = para[i].getParameterAsInt();

			} else if (name.equals("hms_ward_bed1_ID"))
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
		int wardManagementID = getWardManagementID();
		updateTreatmentDoc(wardManagementID, wardID, bedID);
		updateBed();
		return null;
	}

	private int getWardManagementID()
	{
		int WardManagementID = 0;
		String sql = "SELECT hms_ward_management1_ID FROM adempiere.hms_ward_management1 WHERE hms_ward1_id = "
				+ wardID;
		try
		{
			WardManagementID = DB.getSQLValue(get_TrxName(), sql);
			System.out.println("WardManagementID:" + WardManagementID);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return WardManagementID;
	}

	/*
	 * Update in-patient details of the instance this is basically the bed
	 * number and the ward
	 */
	private void updateTreatmentDoc(int wardManagementID, int wardID, int bedID)
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentid, get_TrxName());
		doc.sethms_ward_management1_ID(wardManagementID);
		doc.sethms_ward1_ID(wardID);
		doc.sethms_ward_bed1_ID(bedID);
		doc.setadmitted(true);
		doc.setadmitted_date(new Timestamp(System.currentTimeMillis()));
		doc.setpatient_location("Admitted");
		doc.save();
	}

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
