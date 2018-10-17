package zenith.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.CLogger;

public class HMSPayment extends X_hms_payment
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HMSPayment(Properties ctx, int hms_payment_ID, String trxName)
	{
		super(ctx, hms_payment_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public HMSPayment(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	//@Override
	protected boolean afterSave123(boolean newRecord, boolean success)
	{
		System.out.println("after save payment model");
		return true;
	}
	public void setCreated(Timestamp timestamp)
	{
		set_Value(COLUMNNAME_Created, timestamp);
	}
	public void setUpdated(Timestamp timestamp)
	{
		set_Value(COLUMNNAME_Updated, timestamp);
	}
}
