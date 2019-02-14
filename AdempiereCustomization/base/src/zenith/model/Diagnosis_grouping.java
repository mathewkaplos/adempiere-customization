package zenith.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.DB;

public class Diagnosis_grouping extends X_hms_diagnosis_grouping
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5244304807493282600L;

	public Diagnosis_grouping(Properties ctx, int hms_diagnosis_grouping_ID, String trxName)
	{
		super(ctx, hms_diagnosis_grouping_ID, trxName);
	}

	public Diagnosis_grouping(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public int getTotalInDate(String date, String nextDate, String whereClause)
	{
		int count = 0;
		String sql = "SELECT hms_diagnosis_id FROM hms_diagnosis_detail  WHERE hms_diagnosis_grouping_ID="
				+ this.gethms_diagnosis_grouping_ID();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				int hms_diagnosisg_id = rs.getInt(1);
				int x = getTotalPerDetail(hms_diagnosisg_id, date, nextDate, whereClause);
				count = count + x;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (stm != null)
				{
					stm.close();
					stm = null;
				}
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return count;
	}

	private int getTotalPerDetail(int hms_diagnosisg_id, String date, String nextDate, String whereClause)
	{
		int x = 0;

		String sql = "SELECT pd.hms_diagnosis_id FROM adempiere.hms_pddiagnosis pd "
				+ " JOIN adempiere.hms_treatment_doc doc ON doc.hms_treatment_doc_ID = pd.hms_treatment_doc_ID "
				+ " JOIN adempiere.C_BPartner bp  ON bp.C_BPartner_ID = doc.C_BPartner_ID  "
				+ " WHERE pd.hms_diagnosis_ID=" + hms_diagnosisg_id + " AND pd.created>='" + date + "' AND pd.created<'"
				+ nextDate + "'" + whereClause;

		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{

			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				x++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (stm != null)
				{
					stm.close();
					stm = null;
				}
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return x;

	}
}
