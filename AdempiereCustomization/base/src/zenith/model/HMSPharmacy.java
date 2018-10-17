package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class HMSPharmacy extends X_hms_pharmacy
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HMSPharmacy(Properties ctx, int hms_pharmacy_ID, String trxName)
	{
		super(ctx, hms_pharmacy_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public HMSPharmacy(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
