package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MVital extends X_hms_vital_signss
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 859591636614530131L;

	public MVital(Properties ctx, int hms_vital_signss_ID, String trxName)
	{
		super(ctx, hms_vital_signss_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVital(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
