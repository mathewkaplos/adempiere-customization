package org.compiere.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class DepartmentalRequest
{
	static int m_C_BPartner_ID = 0;
	static int m_hms_treatment_doc_ID = 0;
	static int m_request_ID = 0;

	public DepartmentalRequest()
	{
	}

	public void dynInit() throws Exception
	{
	}

	public static Vector<Vector<Object>> getDepartmentalRequestData(String whereClause)
	{
		Vector<Vector<Object>> data = new Vector();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT hms_specimen_requests_id, req.created ,dep.department,test.name ,req.done,"
				+ " req.done_date, req.done_time,req.cancelled, req.repeat, req.not_available,"
				+ " req.results FROM adempiere.hms_specimen_requests req "
				+ " INNER JOIN adempiere.hms_department dep ON dep.hms_department_ID = req.hms_department_ID"
				+ " INNER JOIN adempiere.hms_test test ON test.hms_test_ID = req.hms_test_ID "
				+ " INNER JOIN adempiere.hms_treatment_doc doc ON doc.hms_treatment_doc_ID =req.hms_treatment_doc_ID "
				+ whereClause + " ORDER BY req.created";
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(new IDColumn(rs.getInt(1)));
				line.add(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(2)));
				line.add(rs.getString(3));
				line.add(rs.getString(4));
				line.add(rs.getString(5).equals("Y"));
				line.add(rs.getString(6));
				line.add(rs.getString(7));
				line.add(rs.getString(8).equals("Y"));
				line.add(rs.getString(9).equals("Y"));
				line.add(rs.getString(10).equals("Y"));
				line.add(rs.getString(11));
				data.add(line);
			}
		} catch (Exception localException)
		{
			localException.printStackTrace();
		} finally
		{
			try
			{
				pstmt.close();
				pstmt = null;
				rs.close();
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public static Vector<String> getVitalsColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add("Date");
		columnNames.add("Department ");
		columnNames.add("Service Name ");
		columnNames.add("Done ");
		columnNames.add("Done Date ");
		columnNames.add("Done Time ");
		columnNames.add("Cancelled");
		columnNames.add("Repeat ");
		columnNames.add("N/A");
		columnNames.add("Note");
		return columnNames;
	}

	public static void setVitalsColumnClass(IMiniTable orderTable)
	{
		Vector<String> names = getVitalsColumnNames();
		int i = 0;
		orderTable.setKeyColumnIndex(i);
		orderTable.setColumnClass(i, IDColumn.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, Date.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, Boolean.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, Boolean.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, Boolean.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, Boolean.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
	}

	public static int getBPartner_ID()
	{
		return m_C_BPartner_ID;
	}

	public static int getHms_treatment_doc_ID()
	{
		return m_hms_treatment_doc_ID;
	}
}