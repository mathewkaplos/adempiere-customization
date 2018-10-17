package zenith.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

public class MBilling extends X_hms_billing
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MBilling(Properties ctx, int hms_billing_ID, String trxName)
	{
		super(ctx, hms_billing_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBilling(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void setCreated(Timestamp timestamp)
	{
		set_Value(COLUMNNAME_Created, timestamp);
	}

}
