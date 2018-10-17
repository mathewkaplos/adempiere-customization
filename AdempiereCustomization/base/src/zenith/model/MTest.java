package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * Test Model
 * 
 * @author Administrator
 *
 */
public class MTest extends X_hms_test
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 * 
	 * @param ctx
	 * @param hms_test_ID
	 * @param trxName
	 */

	public MTest(Properties ctx, int hms_test_ID, String trxName)
	{
		super(ctx, hms_test_ID, trxName);
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
	public MTest(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	} // MTest

	public MTest copyTest(MTest from, String trxName)
	{
		MTest to = new MTest(from.getCtx(), 0, trxName);
		if (from.getName() != null && from.getName() != "")
			to.setName(from.getName());
		if (from.getPrice() != null)
			to.setPrice(from.getPrice());
		to.save();
		return to;
	}

	public static String getName(int ID)
	{
		MTest test = new MTest(Env.getCtx(), ID, "");
		return test.getName();
	}
}
