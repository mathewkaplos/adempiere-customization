package zenith.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.zenith.util.LoadTreatmentDoc;

/**
 * Lab Specimen Request Model
 * 
 * @author Administrator
 *
 */
public class MSpecimenRequest extends X_hms_specimen_requests
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Logger */

	MTreatmentDoc doc = null;

	public MSpecimenRequest(Properties ctx, int hms_specimen_requests_ID, String trxName)
	{
		super(ctx, hms_specimen_requests_ID, trxName);
		
		// TODO Auto-generated constructor stub
	}

	public MSpecimenRequest(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		
		// TODO Auto-generated constructor stub
	}

	private MSpecimenRequestLine[] requestLines = null;

	private void loadLines123()
	{
		List<MSpecimenRequestLine> list = new ArrayList<>();
		String sql = "SELECT * FROM hms_specimen_r_line WHERE hms_specimen_requests_id= " + get_ID();
		PreparedStatement ps = null;
		try
		{
			ps = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				System.out.println(43535);
				MSpecimenRequestLine line = new MSpecimenRequestLine(getCtx(), rs, get_TrxName());
				list.add(line);
				System.out.println(list.size());
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
		System.out.println(list.size());
		requestLines = list.toArray(new MSpecimenRequestLine[list.size()]);
	}

	public MSpecimenRequestLine[] getRequestLines123()
	{
		return requestLines;
	}

}
