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

public class Diagnosis
{
	static int m_C_BPartner_ID = 0;
	static int m_hms_treatment_doc_ID = 0;
	static int m_Diag_ID = 0;

	public Diagnosis()
	{
	}

	public void dynInit() throws Exception
	{
	}

	public static Vector<Vector<Object>> getDiagnosisData(String whereClause)
	{
		Vector<Vector<Object>> data = new Vector();

		String sql = "SELECT hms_pddiagnosis_id,pd.created , u.name, d.name " + " FROM adempiere.hms_pddiagnosis pd "
				+ " INNER JOIN adempiere.hms_diagnosis d ON d.hms_diagnosis_id = pd.hms_diagnosis_id "
				+ " INNER JOIN adempiere.ad_user u ON u.ad_user_id = pd.createdby "
				+ " INNER JOIN adempiere.hms_treatment_doc doc ON doc.hms_treatment_doc_id = pd.hms_treatment_doc_id "
				+ whereClause + " Order BY pd.created desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
		columnNames.add("Created On");
		columnNames.add("Staff");
		columnNames.add("Diagnosis Name");

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