package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MBed;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;
import zenith.model.MWardManagement;

//zenith.process.ReAdmit
public class ReAdmit extends SvrProcess
{
	private int treatmentid = 0;
	private int wardID = 0;
	private int bedID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			if (name.equals("hms_ward1_ID"))
			{
				wardID = para[i].getParameterAsInt();

			} else if (name.equals("hms_ward_bed1_ID"))
			{
				bedID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		treatmentid = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{

		updateTreatmentDoc();
		updateBed();
		removeBedCharges();

		JOptionPane.showMessageDialog(null, "Patient Re-admitted Successfully....");
		return "PATIENT RE-ADMITTED SUCCESSFULLY..";
	}

	private void removeBedCharges()
	{
		String sql = "SELECT * FROM adempiere.hms_billing WHERE M_Product_ID=303 AND hms_treatment_doc_ID="
				+ treatmentid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				MBilling billing = new MBilling(getCtx(), rs, get_TrxName());
				billing.delete(true);
			}
		} catch (Exception e)
		{

		}
	}

	/*
	 * set bed as occupied
	 */
	private void updateBed()
	{
		MBed bed = new MBed(getCtx(), bedID, get_TrxName());
		bed.setisoccupied(true);
		bed.save();
	}

	private int getWardManagementID()
	{
		int WardManagementID = 0;
		String sql = "SELECT hms_ward_management1_ID FROM adempiere.hms_ward_management1 WHERE hms_ward1_id = "
				+ wardID;
		try
		{
			WardManagementID = DB.getSQLValue(get_TrxName(), sql);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (WardManagementID == 0 || WardManagementID == -1)
			WardManagementID = newWardManagementID();
		return WardManagementID;
	}

	private int newWardManagementID()
	{
		MWardManagement wardManagement = new MWardManagement(getCtx(), 0, get_TrxName());
		wardManagement.sethms_ward1_ID(wardID);
		wardManagement.save();
		return wardManagement.get_ID();
	}

	/*
	 * Update in-patient details of the instance this is basically the bed
	 * number and the ward
	 */
	private void updateTreatmentDoc()
	{
		int wardManagementID = getWardManagementID();
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentid, get_TrxName());
		doc.sethms_ward_management1_ID(wardManagementID);
		doc.sethms_ward1_ID(wardID);
		doc.sethms_ward_bed1_ID(bedID);

		doc.setdischarge_date(null);
		doc.setdischarged(false);
		doc.setcheck_out(false);
		doc.setcheck_out_date(null);
		doc.setdays_admitted("");

		doc.setpatient_location("Admitted");
		doc.save();

		updateBPartnerLocation(doc.getC_BPartner_ID());
	}

	private void updateBPartnerLocation(int C_BPartner_ID)
	{
		MBPartner bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
		bp.setCurrentLocation("Admitted");
		bp.setNextLocation("Admitted");
		bp.save();
	}
}
