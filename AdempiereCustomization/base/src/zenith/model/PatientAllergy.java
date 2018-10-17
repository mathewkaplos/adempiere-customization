package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class PatientAllergy extends X_hms_pallergies
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5864677085314358584L;

	public PatientAllergy(Properties ctx, int hms_pallergies_ID, String trxName)
	{
		super(ctx, hms_pallergies_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public PatientAllergy(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
