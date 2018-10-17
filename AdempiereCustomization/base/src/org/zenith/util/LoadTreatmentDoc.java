package org.zenith.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.util.DB;

public class LoadTreatmentDoc
{
	/**
	 * Load the most recent Treatment record ID of the Patient
	 * 
	 * @param BP_ID
	 * @return  hms_treatment_doc_id
	 */
	public static int getTreatmentDoc_ID(int BP_ID)
	{
		StringBuffer sql2 = new StringBuffer();
		PreparedStatement pstmt2 = null;

		sql2 = sql2.append("SELECT hms_treatment_doc_id from adempiere.hms_treatment_doc where" + " c_bpartner_id = "
				+ BP_ID + " order by created desc ");
		int hms_treatment_doc_id = 0;
		int Tophms_treatment_doc_id = 0;
		try
		{
			pstmt2 = DB.prepareStatement(sql2.toString(), null);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next())
			{
				int topID = rs2.getInt(1);
				if (topID != 0)
				{
					hms_treatment_doc_id = rs2.getInt(1);
					break;
				}
				if (hms_treatment_doc_id == 0)
					Tophms_treatment_doc_id = rs2.getInt(1);
			}
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
		if (hms_treatment_doc_id != 0)
			return hms_treatment_doc_id;
		return Tophms_treatment_doc_id;
	}
}
