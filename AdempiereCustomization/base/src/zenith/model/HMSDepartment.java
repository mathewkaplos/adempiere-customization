package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class HMSDepartment extends X_hms_department
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -176190181843962707L;

	public HMSDepartment(Properties ctx, int hms_department_ID, String trxName)
	{
		super(ctx, hms_department_ID, trxName);
	}

	public HMSDepartment(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
