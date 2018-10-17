package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MParameter extends X_hms_parameters
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MParameter(Properties ctx, int hms_parameters_ID, String trxName)
	{
		super(ctx, hms_parameters_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MParameter(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public MParameter copyParameter(MParameter copyFrom, MSpecimen sp, String trxName)
	{
		MParameter to = new MParameter(copyFrom.getCtx(), 0, trxName);
		to.sethms_specimens_ID(sp.get_ID());
		if (copyFrom.getName() != null && copyFrom.getName() != "")
			to.setName(copyFrom.getName());
		if (copyFrom.getunits() != null && copyFrom.getunits() != "")
			to.setunits(copyFrom.getunits());
		if (copyFrom.ishas_range())
		{
			to.sethas_range(true);
			to.setis_age_based(copyFrom.is_age_based());
			to.setis_gender_based(copyFrom.is_gender_based());
		}
		to.save();
		return to;
	}
}
