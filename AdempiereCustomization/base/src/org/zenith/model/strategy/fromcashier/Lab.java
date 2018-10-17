package org.zenith.model.strategy.fromcashier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MOrderLine;
import org.compiere.util.DB;
import org.zenith.util.ToVitals;


public class Lab implements CashierStrategy {

	@Override
	public void execute(Properties ctx, MOrderLine ol, int doc_ID) {
		// TODO Auto-generated method stub
		updateDocLab(doc_ID, ol.get_TrxName());
		ToVitals.updateDoc(doc_ID);
		/**** TO DO
		if (ol.getM_Product_ID() == 1005392) { 
			updateLabRequest(ol.getc_orderline_id_source(), ol.get_TrxName());
		}*/
	}

	static void updateDocLab(int doc_ID, String trx) {
		System.out.println("lllll");
		PreparedStatement pstmt = null;
		String sql = "UPDATE hms_treatment_doc SET lab_paid='Y' WHERE "
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

	static void updateLabRequest(int ol_ID, String trx) {
		PreparedStatement pstmt = null;
		System.out.println(ol_ID);
		String sql = "UPDATE hms_specimen_requests SET paid='Y' WHERE "
				+ " c_orderline_id = " + ol_ID;
		try {
			pstmt = DB.prepareStatement(sql, trx);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
			pstmt = null;
		}
	}
}
