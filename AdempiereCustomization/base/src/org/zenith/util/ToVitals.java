package org.zenith.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.compiere.util.DB;

public class ToVitals {

	public ToVitals() {
	}

	public static void updateDoc(int doc_ID) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE hms_treatment_doc SET tovitals='Y' WHERE "
				+ " hms_treatment_doc_id = " + doc_ID;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
			pstmt = null;
		}
	}

}