package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MInsuranceCompany extends X_hms_insco
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5811321984860415435L;

	public MInsuranceCompany(Properties ctx, int hms_insco_ID, String trxName)
	{
		super(ctx, hms_insco_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInsuranceCompany(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
