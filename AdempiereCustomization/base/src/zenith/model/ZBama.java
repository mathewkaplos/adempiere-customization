package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class ZBama extends X_z_bama_patients
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2626970999735500135L;

	public ZBama(Properties ctx, int z_bama_patients_ID, String trxName)
	{
		super(ctx, z_bama_patients_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public ZBama(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
