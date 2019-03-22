package zenith.process;

import org.compiere.process.SvrProcess;

import zenith.model.MTreatmentDoc;

public class TriageDone extends SvrProcess
{
	int docID = 0;

	@Override
	protected void prepare()
	{
		docID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		updateDoc();
		return "TRIAGE DONE...";
	}

	private void updateDoc()
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), docID, get_TrxName());
		doc.setvitals_done(true);
		doc.settriage_done(true);
		doc.save();
	}

}
