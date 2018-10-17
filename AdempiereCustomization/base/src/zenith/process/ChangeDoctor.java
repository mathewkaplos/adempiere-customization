package zenith.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import zenith.model.MTreatmentDoc;

public class ChangeDoctor extends SvrProcess
{

	private int treatmentid = 0;
	private int C_BPartner_ID = 0;
	private int doctorID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			if (name.equals("C_BPartner_ID"))
			{
				C_BPartner_ID = para[i].getParameterAsInt();

			} else if (name.equals("AD_User_ID"))
			{
				doctorID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		// NEVER TODO Auto-generated method stub
		treatmentid = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentid, get_TrxName());
		doc.setdoctor_ID(doctorID);
		doc.save();
		return "DOCTOR CHANGED SUCCESSFULLY...";
	}

}
