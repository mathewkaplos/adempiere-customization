package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class ZCategory extends X_z_bama_pro_cat
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3585970588721500196L;

	public ZCategory(Properties ctx, int z_bama_pro_cat_ID, String trxName)
	{
		super(ctx, z_bama_pro_cat_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public ZCategory(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
