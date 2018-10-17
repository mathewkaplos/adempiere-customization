package org.compiere.apps.form;

import java.awt.Frame;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.swing.CDialog;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class Triage // extends CDialog
{
	// int m_C_BPartner_ID = 0;
	protected static int m_hms_treatment_doc_ID = 0;
	protected static int m_vitals_ID = 0;
	protected int C_BPartner_ID = 0;

	public void dynInit() throws Exception
	{

	}

	public Vector<Vector<Object>> getVitalsData(String whereClause)
	{
		Vector<Vector<Object>> data = new Vector();

		String sql = "select  hms_vital_signss_id, v.created, u.name,bp_systolic,"
				+ " bp_diastolic, ptemp,pulse, respiratory,  weight, height, bmi, fhr"
				+ " from adempiere.hms_vital_signss v " + " inner join adempiere.hms_treatment_doc doc on "
				+ " doc.hms_treatment_doc_id =v.hms_treatment_doc_id "
				+ " inner join adempiere.c_bpartner bp on bp.c_bpartner_id =doc.c_bpartner_id  "
				+ " inner join adempiere.ad_user u on u.ad_user_id =v.createdby " + whereClause
				+ " order by v.created DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(new IDColumn(rs.getInt(1)));
				line.add(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(2)));
				line.add(rs.getString(3));
				line.add(rs.getBigDecimal(4));
				line.add(rs.getBigDecimal(5));
				line.add(rs.getBigDecimal(6));
				line.add(rs.getBigDecimal(7));
				line.add(rs.getBigDecimal(8));
				line.add(rs.getBigDecimal(9));
				line.add(rs.getBigDecimal(10));
				line.add(rs.getBigDecimal(11));
				line.add(rs.getBigDecimal(12));
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
		columnNames.add("Taken On");
		columnNames.add("Staff");
		columnNames.add("Systolic");
		columnNames.add("Diastolic ");
		columnNames.add("Temperature");
		columnNames.add("Pulse");
		columnNames.add("Respiratory ");
		columnNames.add("Weight ");
		columnNames.add("Height ");
		columnNames.add("BMI ");
		columnNames.add("FHR ");

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

		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
	}

}