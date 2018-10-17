package zenith.process;

import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import zenith.model.MDiagnosis;

public class AddDiagnosis extends SvrProcess
{
	private String Name = null;

	// zenith.process.AddDiagnosis
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("Name"))
			{
				Name = para[i].getParameterAsString();
				// charge_per_bed
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception
	{
		// price3 = (price1.add(price2)).divide(BigDecimal.valueOf(2));
		addDiagnosis();
		JOptionPane.showMessageDialog(null, Name + " Added Successfully...", "Information Message", 1);

		return null;
	}

	private void addDiagnosis()
	{
		MDiagnosis diagnosis = new MDiagnosis(getCtx(), 0, get_TrxName());
		diagnosis.setName(Name);
		diagnosis.save();

	}

}
