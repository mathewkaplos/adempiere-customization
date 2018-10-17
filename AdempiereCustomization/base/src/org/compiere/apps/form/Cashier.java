package org.compiere.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class Cashier
{
	static int m_C_BPartner_ID = 0;
	static int m_hms_treatment_doc_ID = 0;
	static int m_bill_ID = 0;

	public Cashier()
	{
	}

	public void dynInit() throws Exception
	{
	}

	public static Vector<Vector<Object>> getVitalsData()
	{
		Vector<Vector<Object>> data = new Vector();
		String sql = "select doc.hms_treatment_doc_id, bp.documentno, bp.name from adempiere.hms_treatment_doc doc"
				+ " inner join adempiere.c_bpartner bp on bp.c_bpartner_id = doc.c_bpartner_id";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(new IDColumn(rs.getInt(1)));
				line.add(rs.getString(2));
				line.add(rs.getString(3));
				data.add(line);
			}
		} catch (Exception localException)
		{
			localException.printStackTrace();
		}

		return data;
	}

	public static Vector<String> getVitalsColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add("Patient No");
		columnNames.add("Patient Name");

		return columnNames;
	}

	public static void setVitalsColumnClass(IMiniTable orderTable)
	{
		Vector<String> names = getVitalsColumnNames();
		int i = 0;
		orderTable.setKeyColumnIndex(i);
		orderTable.setColumnClass(i, IDColumn.class, true, (String) names.get(i++));
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