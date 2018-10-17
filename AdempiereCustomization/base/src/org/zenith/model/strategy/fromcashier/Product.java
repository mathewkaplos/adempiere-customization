package org.zenith.model.strategy.fromcashier;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;

/*
 * 15.02.17(mathew) This code is not executed because 
 * products are automatically 
 * reserved when they are completed
 */

public class Product implements CashierStrategy
{

	@Override
	public void execute(Properties ctx, MOrderLine ol, int doc_ID)
	{
		// reserveStock(ctx,ol);
		updateDocCON(doc_ID);
	}

	private static void updateDocCON(int doc_ID)
	{
		PreparedStatement pstmt = null;
		String sql = "UPDATE hms_treatment_doc SET to_pharm='Y'," + "patient_location='At Pharmacy' WHERE "
				+ " hms_treatment_doc_id = " + doc_ID;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		System.out.println("hhhhhhhhhhhhhhhhhh");
	}
}
