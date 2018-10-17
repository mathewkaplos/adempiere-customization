package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class InsuranceCompany extends X_hms_insco
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6263481591310922155L;

	public InsuranceCompany(Properties ctx, int hms_insco_ID, String trxName)
	{
		super(ctx, hms_insco_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public InsuranceCompany(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
