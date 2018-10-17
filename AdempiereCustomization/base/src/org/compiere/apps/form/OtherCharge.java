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

public class OtherCharge
{
	static int m_C_BPartner_ID = 0;
	static int m_hms_treatment_doc_ID = 0;
	static int m_bill_ID = 0;

	public OtherCharge()
	{
	}

	public void dynInit() throws Exception
	{
	}

	public static Vector<Vector<Object>> getOtherChargesData(String whereClause)
	{
		Vector<Vector<Object>> data = new Vector();

		String sql = "select b.hms_billing_id, b.bill_date , u.name , pr.name ,b.qty,b.price,"
				+ " b.linenetamt,b.description from adempiere.hms_billing b "
				+ " inner join adempiere.ad_user u on u.ad_user_id =b.createdby "
				+ " inner join adempiere.hms_treatment_doc doc ON doc.hms_treatment_doc_ID = b.hms_treatment_doc_id"
				+ " inner join adempiere.m_product pr on pr.m_product_id = b.m_product_id " + whereClause
				+ " AND is_othercharges ='Y' ORDER BY b.created";
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
				line.add(rs.getBigDecimal(5));
				line.add(rs.getBigDecimal(6));
				line.add(rs.getBigDecimal(7));
				line.add(rs.getString(8));
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
		columnNames.add("Bill Date");
		columnNames.add("Staff");
		columnNames.add("Product Name");
		columnNames.add("Quantity ");
		columnNames.add("Price");
		columnNames.add("Total Amount");
		columnNames.add("Description");

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
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
		orderTable.setColumnClass(i, BigDecimal.class, true, (String) names.get(i++));
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