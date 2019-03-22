package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MSpecimenRequest;

public class RefreshLabPayment extends SvrProcess
{
	private int hms_treatment_doc_ID = 0;

	// zenith.process.RefreshLabPayment
	@Override
	protected void prepare()
	{
		MSpecimenRequest request = new MSpecimenRequest(getCtx(), getRecord_ID(), get_TrxName());
		hms_treatment_doc_ID = request.gethms_treatment_doc_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		String sql = "SELECT hms_billing_id FROM adempiere.hms_billing WHERE  hms_treatment_doc_ID= "
				+ hms_treatment_doc_ID + " AND item_type ='LAB' AND paid ='Y'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				int hms_billing_id = rs.getInt(1);
				updateDocument(hms_billing_id);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (stm != null)
				{
					stm.close();
					stm = null;
				}
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			} catch (Exception e2)
			{
				e2.printStackTrace();
			}

		}
		return null;
	}

	private void updateDocument(int hms_billing_id)
	{
		String select = "SELECT hms_specimen_requests_id FROM   hms_specimen_requests  WHERE hms_billing_id ="
				+ hms_billing_id;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(select, get_TrxName());
			rs = stm.executeQuery();

			if (rs.next())
			{
				int hms_specimen_requests_id = rs.getInt(1);
				MSpecimenRequest req = new MSpecimenRequest(getCtx(), hms_specimen_requests_id, get_TrxName());
				req.setpaid(true);
				req.save();
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				if (stm != null)
				{
					stm.close();
					stm = null;
				}
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			} catch (Exception e2)
			{
				e2.printStackTrace();
			}

		}
	}
}
