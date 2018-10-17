package zenith.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.zenith.util.HmsSetup;

import zenith.model.MBed;
import zenith.model.MTreatmentDoc;
import zenith.model.MWardManagement;

public class Admit extends SvrProcess
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
		// NEVER TODO Auto-generated method stub
		treatmentid = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		// NEVER EVER TODO Auto-generated method stub
		int C_BPartner_ID = getC_BPartner_ID();
		if (HmsSetup.overwriteInpatientNumber())
		{
			insertInpatientNo(C_BPartner_ID);
		} else
		{
			// check if the patient already has an in-patient number
			if (!hasInpatientNo(C_BPartner_ID))
			{
				insertInpatientNo(C_BPartner_ID);
			} else
			{
				// already has a patient number
			}
		}
		int wardManagementID = getWardManagementID();
		updateTreatmentDoc(wardManagementID, wardID, bedID);
		updateBed();
		updateBPartnerLocation(C_BPartner_ID);

		JOptionPane.showMessageDialog(null, "Patient Admitted Successfully....");
		;
		return "PATIENT ADMITTED SUCCESSFULLY..";
	}

	private void insertInpatientNo(int C_BPartner_ID)
	{
		int max = getMaxInpatientNo();
		setInpatientNo(C_BPartner_ID, max + 1);
	}

	private int getC_BPartner_ID()
	{
		int C_BPartner_ID = 0;
		String sql = "SELECT C_BPartner_ID  FROM adempiere.hms_treatment_doc where hms_treatment_doc_id  = "
				+ treatmentid;
		try
		{
			C_BPartner_ID = DB.getSQLValue(get_TrxName(), sql);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return C_BPartner_ID;
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
			System.out.println("WardManagementID:" + WardManagementID);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
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
	 * returns the max inpatient no
	 */
	private int getMaxInpatientNo()
	{
		int MaxInpatientNo = 0;
		String sql = "SELECT COALESCE(MAX(inpatient_no),0) FROM adempiere.C_BPartner";
		try
		{
			MaxInpatientNo = DB.getSQLValue(get_TrxName(), sql);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MaxInpatientNo;

	}

	/*
	 * Checks if the patient number The in patient number is reused
	 */
	private boolean hasInpatientNo(int C_BPartner_ID)
	{
		String sql = "SELECT inpatient_no FROM  adempiere.C_BPartner WHERE C_BPartner_ID =" + C_BPartner_ID;
		int inpno = 0;
		try
		{
			inpno = DB.getSQLValue(get_TrxName(), sql);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (inpno > 0)
		{
			return true;
		}
		return false;
	}

	private void setInpatientNo(int C_BPartner_ID, int inpatient_no)
	{
		String sql = "UPDATE c_bpartner SET inpatient_no = " + inpatient_no + " WHERE C_BPartner_ID =" + C_BPartner_ID;
		try
		{
			DB.executeUpdate(sql, get_TrxName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void updateBPartnerLocation(int C_BPartner_ID)
	{
		MBPartner bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
		bp.setCurrentLocation("Admitted");
		bp.setNextLocation("Admitted");
		bp.save();
	}

	/*
	 * Update in-patient details of the instance this is basically the bed
	 * number and the ward
	 */
	private void updateTreatmentDoc(int wardManagementID, int wardID, int bedID)
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentid, get_TrxName());
		doc.sethms_ward_management1_ID(wardManagementID);
		doc.sethms_ward1_ID(wardID);
		doc.sethms_ward_bed1_ID(bedID);
		doc.setadmitted(true);
		doc.setadmitted_date(new Timestamp(System.currentTimeMillis()));
		doc.setpatient_location("Admitted");
		doc.setto_ward("N");
		doc.save();
	}
}
