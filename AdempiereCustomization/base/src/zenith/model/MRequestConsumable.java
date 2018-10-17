package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MRequestConsumable extends X_hms_request_consumables
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MRequestConsumable(Properties ctx, int hms_request_consumables_ID, String trxName)
	{
		super(ctx, hms_request_consumables_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MRequestConsumable(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
