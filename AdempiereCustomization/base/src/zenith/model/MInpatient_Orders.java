package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MInpatient_Orders extends X_hms_inpatient_orders
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8467100219080334470L;

	public MInpatient_Orders(Properties ctx, int hms_inpatient_orders_ID, String trxName)
	{
		super(ctx, hms_inpatient_orders_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInpatient_Orders(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
