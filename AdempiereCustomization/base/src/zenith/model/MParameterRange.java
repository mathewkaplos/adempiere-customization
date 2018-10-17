package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

public class MParameterRange extends X_hms_parameter_range
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1338087083230644109L;

	public MParameterRange(Properties ctx, int hms_parameter_range_ID, String trxName)
	{
		super(ctx, hms_parameter_range_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MParameterRange(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void copyParameterRange(MParameterRange from, MParameter para,String trxName)
	{
		MParameterRange to = new MParameterRange(from.getCtx(), 0, trxName);
		to.sethms_parameters_ID(para.gethms_parameters_ID());
		to.setmin_value(from.getmin_value());
		to.setmax_value(from.getmax_value());
		if (para.is_age_based())
		{
			if (from.getage_from() != null)
				to.setage_from(from.getage_from());
			else
				to.setage_from(Env.ZERO);
			if (from.getage_to() != null)
				to.setage_to(from.getage_to());
			else
				to.setage_to(Env.ZERO);
		}
		if (para.is_gender_based())
		{
			if (from.getGender() != null && from.getGender() != "")
				to.setGender(from.getGender());

		}
		if (from.ishas_power())
		{
			to.sethas_power(true);
			if (from.getpower() != null && from.getpower() != "")
				to.setpower(from.getpower());
		}
		to.save();
	}
}
