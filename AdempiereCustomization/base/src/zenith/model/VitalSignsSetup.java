package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class VitalSignsSetup extends X_hms_vitals_setup
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2531979955943466907L;

	public VitalSignsSetup(Properties ctx, int hms_vitals_setup_ID, String trxName)
	{
		super(ctx, hms_vitals_setup_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public VitalSignsSetup(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
