package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

public class MLabConsumable extends X_hms_lab_consumables
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5778950540829105323L;

	public MLabConsumable(Properties ctx, int hms_lab_consumables_ID, String trxName)
	{
		super(ctx, hms_lab_consumables_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MLabConsumable(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void copyConsumable(MLabConsumable from, MTest test, String trxName)
	{
		MLabConsumable to = new MLabConsumable(from.getCtx(), 0, trxName);
		if (from.getM_Product_ID() > 0)
			to.setM_Product_ID(from.getM_Product_ID());
		to.sethms_test_ID(test.get_ID());
		if (from.getstd_qty() != null && from.getstd_qty() != Env.ZERO)
			to.setstd_qty(from.getstd_qty());

		to.save();

	}

}
