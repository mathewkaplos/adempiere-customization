package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MBed;
import zenith.model.MWard;
import zenith.model.MWardManagement;

public class Add_beds extends SvrProcess
{
	private int ID = 0;
	private int Added_bed_No = 0;
	private BigDecimal BedCharge = Env.ZERO;
	private BigDecimal BedChargeCorporate = Env.ZERO;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("number_of_beds"))
			{
				Added_bed_No = para[i].getParameterAsInt();
				// charge_per_bed
			} else if (name.equals("bed_charges"))
			{
				BedCharge = (BigDecimal) para[i].getParameter();
			} else if (name.equals("bed_charges_corporate"))
			{
				BedChargeCorporate = (BigDecimal) para[i].getParameter();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			ID = getRecord_ID();
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		System.out.println("Added_bed_No..." + Added_bed_No);
		MWard ward = new MWard(getCtx(), ID, get_TrxName());

		// Add beds only if the number entered is more Zero

		if (Added_bed_No > 0)
		{
			for (int i = 0; i < Added_bed_No; i++)
			{
				int next = getMaxBedNo(ID);
				createBeds(ward, next + 1);
			}

			// check ward management, if it doesn't exist, then add one

			if (!isInWardManagement(ID))
			{
				addWardManagement(ID);
			}
		}
		return null;
	}

	// create beds
	private void createBeds(MWard ward, int nextBedNo)
	{
		int wardID = ward.get_ID();
		MBed bed = new MBed(getCtx(), 0, get_TrxName());
		bed.sethms_ward_block1_ID(ward.gethms_ward_block1_ID());
		bed.sethms_ward_wing1_ID(ward.gethms_ward_wing1_ID());
		bed.sethms_ward_floor1_ID(ward.gethms_ward_floor1_ID());
		bed.sethms_ward1_ID(wardID);
		bed.setbed_no(BigDecimal.valueOf(nextBedNo));
		bed.setbed_charges(BedCharge);
		bed.setbed_charges_corporate(BedChargeCorporate);
		bed.setName(ward.getward_prefix() + "- Bed No:" + nextBedNo);
		bed.save();
	}

	// Get Maximum Bed Number
	// adempiere.hms_ward_bed1 ADD COLUMN bed_no numeric
	private int getMaxBedNo(int wardID)
	{
		StringBuffer sql2 = new StringBuffer();
		PreparedStatement pstmt2 = null;
		sql2 = sql2.append(
				"SELECT COALESCE(MAX(bed_no),0) from adempiere.hms_ward_bed1 " + " WHERE hms_ward1_id =" + wardID);
		int max = 0;
		try
		{
			pstmt2 = DB.prepareStatement(sql2.toString(), get_TrxName());
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next())
			{
				max = rs2.getInt(1);
			}
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
		return max;
	}

	/**
	 * Check if the ward is in ward management
	 */

	private boolean isInWardManagement(int wardID)
	{
		boolean exists = false;
		StringBuffer sql2 = new StringBuffer();
		PreparedStatement pstmt2 = null;
		sql2 = sql2.append("SELECT * from adempiere.hms_ward_management1 " + " WHERE hms_ward1_id =" + wardID);
		try
		{
			pstmt2 = DB.prepareStatement(sql2.toString(), get_TrxName());
			ResultSet rs2 = pstmt2.executeQuery();
			if (rs2.next())
			{
				exists = true;
			}
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
		return exists;
	}

	private void addWardManagement(int WardID)
	{
		MWardManagement wardManagement = new MWardManagement(getCtx(), 0, get_TrxName());
		wardManagement.sethms_ward1_ID(WardID);
		wardManagement.save();
	}
}
