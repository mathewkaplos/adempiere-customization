package zenith.model;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.zenith.util.HmsSetup;

public class MOtherCharges extends X_hms_other_charges
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7759600010380400082L;

	public MOtherCharges(Properties ctx, int hms_other_charges_ID, String trxName)
	{
		super(ctx, hms_other_charges_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public int getC_BPartner_ID()
	{
		int C_BPartner_ID = 0;
		String sql = "SELECT C_BPartner_ID  FROM adempiere.hms_treatment_doc where hms_treatment_doc_id  = "
				+ gethms_treatment_doc_ID();
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

	public int getC_BP_Group_ID()
	{
		int c_bp_group_id = 0;
		String sql = "select c_bp_group_id from hms_treatment_doc where hms_treatment_doc_ID="
				+ gethms_treatment_doc_ID();
		try
		{
			c_bp_group_id = DB.getSQLValue(get_TrxName(), sql);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c_bp_group_id;
	}

	// update Patient Location
	public void updatePatientLoc(String location)
	{
		MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		bp.setNextLocation(location);
		bp.setCurrentLocation(location);
		bp.save();

	}

	// Get the default product dedicated for other charges
	public int getM_Product_IDOther()
	{
		return HmsSetup.getM_Product_ID(getAD_Client_ID(), get_TrxName());
	}

	// Get M_PriceList_ID
	public int getM_PriceList_ID()
	{
		int M_PriceList_Version_ID = 0;
		int M_PriceList_ID = 0;
		String sql = "SELECT MAX(M_PriceList_Version_ID) FROM M_ProductPrice WHERE M_Product_ID= " + getM_Product_ID();
		M_PriceList_Version_ID = DB.getSQLValue(get_TrxName(), sql);
		if (M_PriceList_Version_ID > 0)
		{
			String sql2 = "SELECT M_PriceList_ID FROM M_PriceList_Version WHERE M_PriceList_Version_ID= "
					+ M_PriceList_Version_ID;
			M_PriceList_ID = DB.getSQLValue(get_TrxName(), sql2);
			if (M_PriceList_ID > 0)
			{
				return M_PriceList_ID;
			}
		} else
		{
			// Product is not on PriceList
			throw new AdempiereException("Product is not on PriceList.");
		}
		return M_PriceList_ID;
	}

}
