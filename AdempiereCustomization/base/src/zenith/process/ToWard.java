package zenith.process;

import java.sql.Timestamp;

import org.compiere.process.SvrProcess;

import zenith.model.MTreatmentDoc;

public class ToWard extends SvrProcess
{

	int treatmentid = 0;

	@Override
	protected void prepare()
	{
		treatmentid = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		updateTreatmentDoc();
		return null;
	}

	private void updateTreatmentDoc()
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentid, get_TrxName());
		doc.setadmitted(true);
		doc.setto_ward("Y");
		doc.setadmitted_date(new Timestamp(System.currentTimeMillis()));
		doc.setpatient_location("Admitted");
		doc.save();
	}
}
