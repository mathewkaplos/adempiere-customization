package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MInsuredCompany extends X_hms_insuredco
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1071798081931748109L;

	public MInsuredCompany(Properties ctx, int hms_insuredco_ID, String trxName)
	{
		super(ctx, hms_insuredco_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInsuredCompany(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
