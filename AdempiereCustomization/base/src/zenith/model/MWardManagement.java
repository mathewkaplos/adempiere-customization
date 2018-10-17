package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MWardManagement extends X_hms_ward_management1
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2724235694881880447L;

	public MWardManagement(Properties ctx, int hms_ward_management1_ID, String trxName)
	{
		super(ctx, hms_ward_management1_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MWardManagement(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
