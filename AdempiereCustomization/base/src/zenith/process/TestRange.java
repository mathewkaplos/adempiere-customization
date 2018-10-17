package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MParameter;
import zenith.model.MParameterRange;

public class TestRange extends SvrProcess
{

	@Override
	protected void prepare()
	{

	}

	// hms_test - hms_test_ID=1000002
	// hms_specimens - hms_specimens_ID=1000002
	// hms_parameters - hms_parameters_ID=1000003
	// hms_parameter_range - hms_parameter_range_ID=1000003

	@Override
	protected String doIt() throws Exception
	{
		getRange(1000003);
		return null;
	}

	private String getRange(int hms_parameters_ID)
	{
		StringBuilder sb = new StringBuilder();
		MParameter param = new MParameter(getCtx(), hms_parameters_ID, get_TrxName());
		if (param.ishas_range())
		{
			String sql = "SELECT * FROM hms_parameter_range WHERE hms_parameters_ID=" + hms_parameters_ID;
			PreparedStatement pstmt = null;
			BigDecimal age = BigDecimal.valueOf(0);
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					MParameterRange range = new MParameterRange(getCtx(), rs, get_TrxName());
					if (param.is_age_based())
					{
						if (age.compareTo(range.getage_from()) >= 0 && age.compareTo(range.getage_to()) < 0)
						{
							sb.append(range.getmin_value().setScale(2) + " --> " + range.getmax_value().setScale(2));
							if (range.ishas_power())
							{
								sb = new StringBuilder("(" + sb + ")");
								sb.append(" X " + range.getpower());
							}
							break;
						}
					} else
					{ // select only one
						sb.append(range.getmin_value().setScale(2) + " --> " + range.getmax_value().setScale(2));
						if (range.ishas_power())
						{
							sb = new StringBuilder("(" + sb + ")");
							sb.append(" X " + range.getpower());
						}
						break;
					}

				}
			} catch (Exception e)
			{
			}
			try
			{
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
			} catch (Exception e)
			{
				pstmt = null;
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
