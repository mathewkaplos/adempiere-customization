package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPrescriptionline extends X_hms_prescription_line
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5187584333304543449L;

	public MPrescriptionline(Properties ctx, int hms_prescription_line_ID, String trxName)
	{
		super(ctx, hms_prescription_line_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPrescriptionline(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
