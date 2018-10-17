package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import zenith.model.MVital;

public class Add_vital extends SvrProcess
{

	private int bp_diastolic = 0;
	private int bp_systolic = 0;

	private BigDecimal height = Env.ZERO;
	private BigDecimal Weight = Env.ZERO;
	private BigDecimal ptemp = Env.ZERO;
	private Integer pulse = 0;
	private Integer respiratory = 0;
	private BigDecimal bmi = Env.ZERO;
	private Timestamp lmp = null;

	private int treatID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("bp_diastolic"))
			{
				bp_diastolic = para[i].getParameterAsInt();
				// charge_per_bed
			} else if (name.equals("bp_systolic"))
			{
				bp_systolic = para[i].getParameterAsInt();
			} else if (name.equals("height"))
			{
				height = (BigDecimal) para[i].getParameter();
			} else if (name.equals("Weight"))
			{
				Weight = (BigDecimal) para[i].getParameter();
			}

			else if (name.equals("ptemp"))
			{
				ptemp = (BigDecimal) para[i].getParameter();
			} else if (name.equals("pulse"))
			{
				pulse = para[i].getParameterAsInt();
			} else if (name.equals("respiratory"))
			{
				respiratory = para[i].getParameterAsInt();
			} else if (name.equals("bmi"))
			{
				bmi = (BigDecimal) para[i].getParameter();
			}
			// lmp
			else if (name.equals("lmp"))
			{
				lmp = para[i].getParameterAsTimestamp();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			treatID = getRecord_ID();
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		addVita();
		return null;
	}

	private void addVita()
	{
		MVital vital = new MVital(getCtx(), 0, get_TrxName());
		vital.sethms_treatment_doc_ID(treatID);
		vital.setbp_diastolic(bp_diastolic);
		vital.setbp_systolic(bp_systolic);
		vital.setheight(height);
		vital.setWeight(Weight);
		vital.setptemp(ptemp);
		vital.setpulse(pulse);
		vital.setrespiratory(respiratory);
		vital.setbmi(getBMI());
		vital.setlmp(lmp);
		vital.save();

	}

	private BigDecimal getBMI()
	{
		BigDecimal bmi = Env.ZERO;
		if (height.compareTo(Env.ZERO) == 1)
		{
			BigDecimal w = Weight.setScale(2, RoundingMode.HALF_DOWN);
			BigDecimal h = height.setScale(2, RoundingMode.HALF_DOWN);
			BigDecimal hkg = h.divide(Env.ONEHUNDRED);
			BigDecimal h2 = hkg.multiply(hkg);
			bmi = w.divide(h2, 2, RoundingMode.HALF_UP);
		}
		return bmi.setScale(2, RoundingMode.HALF_DOWN);
	}

}
