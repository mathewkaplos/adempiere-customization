package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class ZInsuranceCompany extends X_z_bama_ins_co
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820413583747501561L;

	public ZInsuranceCompany(Properties ctx, int z_bama_ins_co_ID, String trxName)
	{
		super(ctx, z_bama_ins_co_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public ZInsuranceCompany(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}



}
