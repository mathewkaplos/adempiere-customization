package org.zenith.model.strategy.fromcashier;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MOrderLine;
import org.compiere.util.DB;
import org.zenith.util.ToVitals;

public class Consultation implements CashierStrategy
{

	@Override
	public void execute(Properties ctx, MOrderLine ol, int doc_ID)
	{
		// TODO Auto-generated method stub
		updateDocCON(doc_ID);
		ToVitals.updateDoc(doc_ID);
	}

	static void updateDocCON(int doc_ID)
	{
		PreparedStatement pstmt = null;
		String sql = "UPDATE hms_treatment_doc SET patient_location='At consultation' WHERE "
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
	}

	static void updateDocLab(int doc_ID)
	{
		PreparedStatement pstmt = null;
		String sql = "UPDATE hms_treatment_doc SET lab_paid='Y' WHERE " + " hms_treatment_doc_id = " + doc_ID;
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
	}
}
