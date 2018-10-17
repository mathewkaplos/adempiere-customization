package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MBed extends X_hms_ward_bed1
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1097245435866331859L;

	public MBed(Properties ctx, int hms_ward_bed1_ID, String trxName)
	{
		super(ctx, hms_ward_bed1_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBed(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
