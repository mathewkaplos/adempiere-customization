package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDiagnosis extends X_hms_diagnosis
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4068411302867424136L;

	public MDiagnosis(Properties ctx, int hms_diagnosis_ID, String trxName)
	{
		super(ctx, hms_diagnosis_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDiagnosis(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
