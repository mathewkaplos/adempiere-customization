package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Test specimens Model Class
 * 
 * @author Administrator
 *
 */

public class MSpecimen extends X_hms_specimens
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MSpecimen(Properties ctx, int hms_specimens_ID, String trxName)
	{
		super(ctx, hms_specimens_ID, trxName);
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
	public MSpecimen(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public MSpecimen copySpecimen(MSpecimen from, MTest test, String trxName)
	{
		MSpecimen to = new MSpecimen(from.getCtx(), 0, trxName);
		to.sethms_test_ID(test.get_ID());
		if (from.getName() != null && from.getName() != "")
			to.setName(from.getName());
		to.save();
		return to;
	}
}
