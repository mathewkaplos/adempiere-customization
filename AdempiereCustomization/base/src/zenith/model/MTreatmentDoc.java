package zenith.model;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

//import zenith.process.BookPatient;

public class MTreatmentDoc extends X_hms_treatment_doc
{
	/**
	 * 
	 */
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MTreatmentDoc.class);

	private static final long serialVersionUID = 1L;

	public MTreatmentDoc(Properties ctx, int hms_treatment_doc_ID, String trxName)
	{
		super(ctx, hms_treatment_doc_ID, trxName);
		// set BP defaults
		setBPartner();
	}

	public MTreatmentDoc(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	int C_BP_Group_ID = 0;
	// public MProduct product;
	public MBPartner partner;

	public static MTreatmentDoc get(Properties ctx, int C_BPartner_ID, String trx)
	{
		// String whereClause = "C_BPartner_ID=? AND AD_Client_ID=?";
		MTreatmentDoc retValue = new MTreatmentDoc(ctx, C_BPartner_ID, trx);

		return retValue;
	}

	// set business partner defaults
	private void setBPartner()
	{
		MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());

		partner = bp;
		if (bp.getC_BP_Group_ID() > 0)
		{
			C_BP_Group_ID = bp.getC_BP_Group_ID();
		}
		// 1000072
		// default to cash
		else
		{
			C_BP_Group_ID = 1000072;
		}
	}



	

	

	// next_location
	/**
	 * Get All bills
	 */
	public MBilling[] getBills()
	{
		StringBuilder select = new StringBuilder();
		select.append("SELECT * FROM   hms_billing  WHERE  hms_treatment_doc_id = " + this.gethms_treatment_doc_ID());
		// StringBuilder select = "SELECT * FROM hms_billing WHERE
		// hms_treatment_doc_id =" + treatID + " AND pay ='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MBilling> list = new ArrayList<MBilling>();
		try
		{
			pstmt = DB.prepareStatement(select.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, get_TrxName());
				list.add(bill);
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				pstmt.close();
				rs.close();
				pstmt = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		MBilling[] billArray = new MBilling[list.size()];
		return list.toArray(billArray);

	}

	public void updateDrugStatus()
	{
		updateDrugStatus(this);
	}

	public void updateDrugStatus(MTreatmentDoc doc)
	{
		int treatID = doc.gethms_treatment_doc_ID();
		doc.setdrugs_ordered(getAllDrugs(treatID));
		doc.setdrugs_issued(getIssuedDrugs(treatID));
		doc.setdrugs_not_issued(getUnIssuedDrugs(treatID));
		doc.save();

	}

	private int getAllDrugs(int treatID)
	{
		int count = 0;
		String sql = " SELECT COALESCE(count(bill.hms_billing_ID),0) FROM adempiere.hms_billing bill "
				+ " INNER JOIN adempiere.m_product pro ON pro.M_Product_ID = bill.M_Product_ID"
				+ " WHERE   bill.hms_treatment_doc_ID = " + treatID + "  AND bill.is_prescription ='Y' "
				+ " AND pro.producttype='I' ";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
		return count;
	}

	private int getIssuedDrugs(int treatID)
	{
		int count = 0;
		String sql = " SELECT COALESCE(count(bill.hms_billing_ID),0) FROM adempiere.hms_billing bill "
				+ " WHERE   bill.hms_treatment_doc_ID = " + treatID
				+ "  AND bill.is_prescription ='Y' AND bill.issued ='Y'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
		return count;
	}

	private int getUnIssuedDrugs(int treatID)
	{
		int count = 0;
		String sql = " SELECT COALESCE(count(bill.hms_billing_ID),0) FROM adempiere.hms_billing bill "
				+ " INNER JOIN adempiere.M_Product pro ON pro.M_Product_ID = bill.M_Product_ID  "
				+ " WHERE   bill.hms_treatment_doc_ID = " + treatID
				+ "  AND pro.producttype ='I' AND bill.is_prescription ='Y' AND bill.issued ='N'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
		return count;
	}

	public void updateDiagnosis()
	{
		updateDiagnosis(this);
	}

	public void updateDiagnosis(MTreatmentDoc doc)
	{
		String diagnosis = "";
		int id = 0;
		String sql = "SELECT hms_pddiagnosis_ID, diag.name FROM adempiere.hms_pddiagnosis pd "
				+ " INNER JOIN adempiere.hms_diagnosis diag ON diag.hms_diagnosis_ID = pd.hms_diagnosis_ID"
				+ " WHERE hms_treatment_doc_ID= " + doc.gethms_treatment_doc_ID() + "  ORDER BY pd.created ";

		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			if (rs.next())
			{
				id = rs.getInt(1);
				diagnosis = rs.getString(2);

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		doc.sethms_diagnosis_ID(id);
		doc.setdiagnosis(diagnosis);
		doc.save();
	}

	public void exit()
	{
		deleteBills(gethms_treatment_doc_ID());
		deleteInvoices(gethms_treatment_doc_ID());
		deleteTests(gethms_treatment_doc_ID());
		this.delete(true);
	}

	public void deleteBills(int treatID)
	{
		String sql = "SELECT * FROM adempiere.hms_billing WHERE hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, null);
				bill.delete(true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void deleteInvoices(int treatID)
	{
		String sql = "SELECT * FROM adempiere.hms_invoice WHERE hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			while (rs.next())
			{
				HmsInvoice inv = new HmsInvoice(getCtx(), rs, null);
				inv.delete(true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void deleteTests(int treatID)
	{
		String sql = "SELECT * FROM adempiere.hms_specimen_requests WHERE hms_treatment_doc_ID =" + treatID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();

			while (rs.next())
			{
				MSpecimenRequest req = new MSpecimenRequest(getCtx(), rs, null);
				deleteParameters(req.get_ID());
				req.delete(true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void deleteParameters(int hms_specimen_requests_id)
	{
		ArrayList<Integer> requestLineIDs = new ArrayList<>();
		ArrayList<Integer> resultIDs = new ArrayList<>();

		String sql1 = "select hms_specimen_r_line_id from adempiere.hms_specimen_r_line where hms_specimen_requests_id ="
				+ hms_specimen_requests_id;
		PreparedStatement stm1 = null;
		try
		{
			stm1 = DB.prepareStatement(sql1, null);
			ResultSet rs = stm1.executeQuery();
			while (rs.next())
			{
				int lineID = rs.getInt(1);
				requestLineIDs.add(lineID);
				String sql2 = "select hms_lab_results_id from adempiere.hms_lab_results  where hms_specimen_r_line_id ="
						+ lineID;
				PreparedStatement stm2 = null;
				stm2 = DB.prepareStatement(sql2, null);
				ResultSet rs2 = stm2.executeQuery();
				try
				{
					while (rs2.next())
					{
						int hms_lab_results_id = rs2.getInt(1);
						resultIDs.add(hms_lab_results_id);
					}
				} catch (Exception ex)
				{

				} finally
				{
					try
					{
						stm2.close();
						stm2 = null;
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		} catch (Exception ex)
		{

		} finally
		{
			try
			{
				stm1.close();
				stm1 = null;
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		for (Integer resultID : resultIDs)
		{
			MResult r = new MResult(getCtx(), resultID, null);
			r.delete(true);
		}
		for (Integer requestLineID : requestLineIDs)
		{
			MSpecimenRequestLine line = new MSpecimenRequestLine(getCtx(), requestLineID, null);
			line.delete(true);
		}
	}

	public void updateTotalOpenBalance(BigDecimal amt)
	{
		this.setopen_balance(this.getopen_balance().add(amt));
	}
}
