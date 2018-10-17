package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class ZProduct extends X_z_bama_product
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1938126051563708345L;

	public ZProduct(Properties ctx, int z_bama_product_ID, String trxName)
	{
		super(ctx, z_bama_product_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public ZProduct(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}



}
