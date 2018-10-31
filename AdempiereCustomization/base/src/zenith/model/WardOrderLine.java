package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class WardOrderLine extends X_hms_ward_orderline
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1277792323042148716L;

	public WardOrderLine(Properties ctx, int hms_ward_orderline_ID, String trxName)
	{
		super(ctx, hms_ward_orderline_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public WardOrderLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
