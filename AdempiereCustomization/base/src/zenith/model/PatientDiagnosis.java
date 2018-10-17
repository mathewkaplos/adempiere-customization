package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class PatientDiagnosis extends X_hms_pddiagnosis
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4461879657251410769L;

	public PatientDiagnosis(Properties ctx, int hms_pddiagnosis_ID, String trxName)
	{
		super(ctx, hms_pddiagnosis_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public PatientDiagnosis(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
