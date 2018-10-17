package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class InsuarancePayline extends X_hms_insuance_payline
{

	/**
	 *
	 */
	private static final long serialVersionUID = 8161557384699054127L;

	public InsuarancePayline(Properties ctx, int hms_insuance_payline_ID, String trxName)
	{
		super(ctx, hms_insuance_payline_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public InsuarancePayline(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
