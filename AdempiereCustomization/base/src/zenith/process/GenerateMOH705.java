package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.Detail705;
import zenith.model.Diagnosis_grouping;
import zenith.model.X_hms_gererate_705;

public class GenerateMOH705 extends SvrProcess
{
	X_hms_gererate_705 gen705 = null;
	Timestamp _startDate = null;
	Timestamp _endDate = null;

	String reportType = null;

	String whereClause = "";

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("reportType"))
			{
				reportType = (String) para[i].getParameter();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		gen705 = new X_hms_gererate_705(getCtx(), getRecord_ID(), get_TrxName());
		_startDate = gen705.getstart_date();
		_endDate = gen705.getEndDate();
	}

	@Override
	protected String doIt() throws Exception
	{
		deleteOld();

		if (reportType.equalsIgnoreCase("A"))
		{
			gen705.setName("MOH 705A SUMMARY REPORT");
			whereClause = " AND  EXTRACT(YEAR FROM AGE(pd.created , bp.dob))<=5 ";
		} else
		{
			gen705.setName("MOH 705B SUMMARY REPORT");
			whereClause = " AND  EXTRACT(YEAR FROM AGE(pd.created , bp.dob))>5 ";
		}
		gen705.save();
		generate();
		return null;
	}

	private void deleteOld()
	{
		String sql = "DELETE FROM hms_705_detail";
		DB.executeUpdate(sql, get_TrxName());

	}

	private void generate()
	{

		String sql = "SELECT *  FROM hms_diagnosis_grouping ORDER BY hms_diagnosis_grouping_ID";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				Diagnosis_grouping grouping = new Diagnosis_grouping(getCtx(), rs, get_TrxName());

				gen(grouping);
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

	}

	private void gen(Diagnosis_grouping grouping)
	{
		Date startDate = new Date(_startDate.getTime());
		Date endDate = new Date(_endDate.getTime());
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Detail705 detail705 = new Detail705(getCtx(), 0, get_TrxName());
		detail705.setName(grouping.getName());
		detail705.save();
		int totals = 0;
		for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1))
		{
			// Do your job here with `date`.
			int x = grouping.getTotalInDate(date.toString(), date.plusDays(1).toString(), whereClause);
			String field = "d" + date.getDayOfMonth();
			insertValue(field, x, detail705.gethms_705_detail_ID());
			totals = totals + x;
		}
		detail705.settotals(totals);
		if (reportType.equalsIgnoreCase("A"))
		{
			detail705.setDescription("MOH 705A SUMMARY REPORT");
		} else
		{
			detail705.setDescription("MOH 705B SUMMARY REPORT");
		}

		detail705.save();

	}

	private void insertValue(String field, int x, int hms_705_detail_ID)
	{
		String sql = "UPDATE hms_705_detail SET " + field + "=" + x + " WHERE hms_705_detail_ID =" + hms_705_detail_ID;
		DB.executeUpdate(sql, get_TrxName());
	}

}
