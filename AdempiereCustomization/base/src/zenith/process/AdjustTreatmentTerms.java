package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

public class AdjustTreatmentTerms extends SvrProcess
{
	private MTreatmentDoc doc = null;
	private Timestamp booking_date = null;
	private int doctor_ID = 0;
	private int C_BP_Group_ID = 0;
	private String billing_change_type = "";
	private int M_Product_ID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("booking_date"))
				booking_date = para[i].getParameterAsTimestamp();
			else if (name.equals("doctor_ID"))
			{
				doctor_ID = para[i].getParameterAsInt();
			} else if (name.equals("C_BP_Group_ID"))
			{
				C_BP_Group_ID = para[i].getParameterAsInt();
			} else if (name.equals("billing_change_type"))
				billing_change_type = para[i].getParameterAsString();
			else if (name.equals("M_Product_ID"))
				M_Product_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		doc = new MTreatmentDoc(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception
	{
		adjust();
		return null;
	}

	private void adjust()
	{
		doc.setbooking_date(booking_date);
		if (doctor_ID > 0)
			doc.setdoctor_ID(doctor_ID);
		if (C_BP_Group_ID > 0)
		{
			doc.setC_BP_Group_ID(C_BP_Group_ID);
			if (billing_change_type.equalsIgnoreCase(CreateHospitalDefaults.BILLING_CHANGE_TYPE_FROM_BEGINNING))
			{
				updateBillingMode();
			} else if (billing_change_type.equalsIgnoreCase(CreateHospitalDefaults.BILLING_CHANGE_TYPE_NOW_ONWARDS))
			{

			}
		}
		if (M_Product_ID > 0)
		{
		}
		doc.save();
	}

	private void updateBillingMode()
	{
		String sql = "SELECT * FROM adempiere.hms_billing bill WHERE  bill.hms_treatment_doc_id=" + doc.get_ID();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			while (rs.next())
			{
				MBilling bill = new MBilling(doc.getCtx(), rs, null);
				setBillMode(bill);
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				stm.close();
				DB.close(rs);
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setBillMode(MBilling billing)
	{
		MBilling bill = billing;
		if (C_BP_Group_ID == CreateHospitalDefaults.PATIENT_GROUP_ID_CASH)
		{
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		} else if (C_BP_Group_ID == CreateHospitalDefaults.PATIENT_GROUP_ID_INSURANCE
				|| C_BP_Group_ID == CreateHospitalDefaults.PATIENT_GROUP_ID_NHIF)
		{
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_INSUARANCE);
		}
		if (billing.getbill_date() == null)
			bill.setbill_date(billing.getCreated());
		bill.save();
	}
}
