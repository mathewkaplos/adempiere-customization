package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class hms_stock_take extends X_hms_stock_take
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2109997987758813421L;

	public hms_stock_take(Properties ctx, int hms_stock_take_ID, String trxName)
	{
		super(ctx, hms_stock_take_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public hms_stock_take(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
