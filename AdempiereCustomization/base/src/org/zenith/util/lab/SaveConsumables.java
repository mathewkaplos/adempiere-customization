package org.zenith.util.lab;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MSpecimenRequest;
import zenith.model.MTest;
import zenith.model.X_hms_request_consumables;

public class SaveConsumables
{
	MSpecimenRequest req = null;

	public SaveConsumables(MSpecimenRequest req)
	{
		this.req = req;
	}

	public void save()
	{
		SaveConsumable();
	}

	private BigDecimal SaveConsumable()
	{
		String sql2 = "SELECT m_product_id, std_qty  FROM hms_lab_consumables " + "  WHERE  hms_test_id = ? ";
		BigDecimal consumable_price = Env.ZERO;
		BigDecimal grandPrice = Env.ZERO;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try
		{
			pstmt2 = DB.prepareStatement(sql2, null);
			pstmt2.setInt(1, req.gethms_test_ID());
			rs2 = pstmt2.executeQuery();
			while (rs2.next())
			{
				int p_id = rs2.getInt(1);
				BigDecimal std_qty = rs2.getBigDecimal(2);
				X_hms_request_consumables rc = new X_hms_request_consumables(Env.getCtx(), 0, null);
				rc.setactual_qty(std_qty);
				rc.setstd_qty(std_qty);
				rc.sethms_test_ID(req.gethms_test_ID());
				rc.setM_Product_ID(p_id);
				rc.setC_BPartner_ID(req.getC_BPartner_ID());
				rc.sethms_specimen_requests_ID(req.gethms_specimen_requests_ID());
				rc.save();
			}
			MTest lt = new MTest(Env.getCtx(), req.gethms_test_ID(), null);
			BigDecimal testPrice = lt.getPrice();
			grandPrice = testPrice.add(consumable_price);
			// lab test
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
		return grandPrice;
	}
}
