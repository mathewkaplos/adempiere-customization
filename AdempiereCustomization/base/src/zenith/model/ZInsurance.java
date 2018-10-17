package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class ZInsurance extends X_z_bama_ins
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6367355060479906644L;

	public ZInsurance(Properties ctx, int z_bama_ins_ID, String trxName)
	{
		super(ctx, z_bama_ins_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public ZInsurance(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
