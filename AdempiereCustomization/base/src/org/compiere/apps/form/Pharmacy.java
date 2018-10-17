package org.compiere.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class Pharmacy
{
	static int m_C_BPartner_ID = 0;
	static int m_hms_treatment_doc_ID = 0;
	static int m_bill_ID = 0;

	public Pharmacy()
	{
	}

	public void dynInit() throws Exception
	{
	}

	public static Vector<Vector<Object>> getVitalsData()
	{
		Vector<Vector<Object>> data = new Vector();
		String sql = "select doc.hms_treatment_doc_id, doc.created,to_char(doc.created::time,'HH12:MI AM'), bp.documentno, bp.name ,age(bp.dob)::text,doc.patient_location from adempiere.hms_treatment_doc doc"

				+ " inner join adempiere.c_bpartner bp on bp.c_bpartner_id = doc.c_bpartner_id "
				+ " WHERE to_pharm='Y' ORDER BY doc.pharm_done, doc.created DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(new IDColumn(rs.getInt(1)));
				line.add(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(2)));
				line.add(rs.getString(3));
				line.add(rs.getString(4));
				line.add(rs.getString(5));
				line.add(rs.getString(6));
				line.add(rs.getString(7));
				// line.add(rs.getString(5).equalsIgnoreCase("Y"));
				data.add(line);
			}
		} catch (Exception localException)
		{
			localException.printStackTrace();
		} finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		return data;
	}

	public static Vector<String> getVitalsColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add("Date");
		columnNames.add("Time");
		columnNames.add("Patient No");
		columnNames.add("Patient Name");
		columnNames.add("Patient Age");
		columnNames.add("Patient Location");
		// columnNames.add("Issued");

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
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
		// orderTable.setColumnClass(i, Boolean.class, true, (String)
		// names.get(i++));
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