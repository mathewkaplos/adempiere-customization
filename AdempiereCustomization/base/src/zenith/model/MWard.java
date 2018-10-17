package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MWard extends X_hms_ward1
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5192796706414359361L;

	public MWard(Properties ctx, int hms_ward1_ID, String trxName)
	{
		super(ctx, hms_ward1_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MWard(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
