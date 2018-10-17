package zenith.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

/**
 * Specimen Request Line Model
 * 
 * @author Administrator
 *
 */
public class MSpecimenRequestLine extends X_hms_specimen_r_line
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6473792253950222247L;

	/**
	 * 
	 */

	public MSpecimenRequestLine(Properties ctx, int hms_specimen_r_line_ID, String trxName)
	{
		super(ctx, hms_specimen_r_line_ID, trxName);

		// TODO Auto-generated constructor stub
	}

	public MSpecimenRequestLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);

		// TODO Auto-generated constructor stub
	}

	private MResult[] results = null;

	private void loadResults123()
	{
		List<MResult> list = new ArrayList<>();
		String sql = "SELECT * FROM hms_lab_results WHERE hms_specimen_r_line_id= " + get_ID();
		PreparedStatement ps = null;
		try
		{
			ps = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				MResult result = new MResult(getCtx(), rs, get_TrxName());
				list.add(result);
			}
			rs.close();
			ps.close();
			ps = null;

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		finally
		{
			try
			{
				ps.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ps != null)
				ps = null;
		}
		results = list.toArray(new MResult[list.size()]);
	}

	public MResult[] getResullts123()
	{
		return results;
	}
}
