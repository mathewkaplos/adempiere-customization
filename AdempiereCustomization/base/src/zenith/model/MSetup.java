package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MSetup extends X_hms_setup
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1697614758970656024L;

	public MSetup(Properties ctx, int hms_setup_ID, String trxName)
	{
		super(ctx, hms_setup_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSetup(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
