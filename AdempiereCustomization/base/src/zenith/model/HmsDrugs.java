package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class HmsDrugs extends X_hms_drugs
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -330800189055101423L;

	public HmsDrugs(Properties ctx, int hms_drugs_ID, String trxName)
	{
		super(ctx, hms_drugs_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public HmsDrugs(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
