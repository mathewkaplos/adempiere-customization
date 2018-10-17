package org.compiere.apps.form;

import java.math.BigDecimal;
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

public class DoctorNotes
{
	static int m_C_BPartner_ID = 0;
	static int m_hms_treatment_doc_ID = 0;
	static int m_note_ID = 0;

	public DoctorNotes()
	{
	}

	public void dynInit() throws Exception
	{
	}

	public static Vector<Vector<Object>> getNotesData(String whereClause)
	{

		Vector<Vector<Object>> data = new Vector();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT hms_doc_notes_id, note.created , u.name, present_complain,hpi ,"
				+ " pmsh ,pre_medication,general_examination,local_examination,impression FROM adempiere.hms_doc_notes note "
				+ " INNER JOIN adempiere.hms_treatment_doc doc ON"
				+ " doc.hms_treatment_doc_id = note.hms_treatment_doc_id "
				+ " INNER JOIN adempiere.ad_user u ON u.ad_user_id = note.createdby   " + whereClause
				+ " ORDER BY note.created DESC";
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
				line.add(rs.getString(3));// staff
				line.add(rs.getString(4)); // chief complain
				line.add(rs.getString(5));// hpi
				line.add(rs.getString(6));// pmsh
				line.add(rs.getString(7)); // pre medication
				line.add(rs.getString(8)); // general examination
				line.add(rs.getString(9)); // local examination
				line.add(rs.getString(10)); // impression
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
		columnNames.add("Created On ");
		columnNames.add("Staff ");
		columnNames.add("Chief Complain ");
		columnNames.add("History of Presenting Illness ");
		columnNames.add("Past Medical & Surgical History");
		columnNames.add("Pre-Medication");
		columnNames.add("General Examination ");
		columnNames.add("Local Examination");
		columnNames.add("Impression");

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
		orderTable.setColumnClass(i, String.class, true, (String) names.get(i++));
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