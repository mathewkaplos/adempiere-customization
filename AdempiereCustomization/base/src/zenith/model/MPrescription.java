package zenith.model;

import java.util.Properties;

import org.compiere.model.MBPGroup;
import org.compiere.util.DB;
import org.compiere.util.Msg;

public class MPrescription extends X_hms_prescription
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MPrescription(Properties ctx, int hms_prescription_ID, String trxName)
	{
		super(ctx, hms_prescription_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave123(boolean newRecord)
	{
		if (newRecord)
		{
			if (getC_BPartner_ID() == 0)
			{
				int treatID = gethms_treatment_doc_ID();
				String sql = "SELECT C_BPartner_ID FROM adempiere.C_BPartner WHERE hms_treatment_doc_ID = " + treatID;
				int C_BPartner = DB.getSQLValue(get_TrxName(), sql);
				setC_BPartner_ID(C_BPartner);
				return save();
				// return true;
			}
		}
		return true;
	} // beforeSave

	/**************************************************************************
	 * After Save
	 * 
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		System.out.println("great.................");
		if (newRecord)
		{
			if (getC_BPartner_ID() == 0)
			{
				int treatID = gethms_treatment_doc_ID();
				String sql = "SELECT C_BPartner_ID FROM adempiere.C_BPartner WHERE hms_treatment_doc_ID = " + treatID;
				int C_BPartner = DB.getSQLValue(get_TrxName(), sql);
				setC_BPartner_ID(C_BPartner);
				return save();
				// return true;
			}
		}
		return true;
	}
}
