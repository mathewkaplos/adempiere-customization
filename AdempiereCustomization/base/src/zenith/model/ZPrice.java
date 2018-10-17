package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class ZPrice extends X_z_bama_price
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5709059454433041547L;

	public ZPrice(Properties ctx, int z_bama_price_ID, String trxName)
	{
		super(ctx, z_bama_price_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public ZPrice(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
