package zenith.imports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.MBPartner;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MInsuranceCompany;
import zenith.model.MInsuredCompany;

public class CopyInsurance extends SvrProcess
{
	
	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		copyInsurance();
		return null;
	}

	private void copyInsurance()
	{
		String sql = "select id,name,box,town from adempiere.z_insurance";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String box = rs.getString(3);
				String town = rs.getString(4);

				MInsuranceCompany i = new MInsuranceCompany(getCtx(), 0, get_TrxName());
				i.setimport_id(id);
				i.setcompanyname(name);
				i.setpobox(box);
				i.settown(town);
				i.save();
				copyCompany(id, i.gethms_insco_ID());
			}

		} catch (Exception e)
		{

		} finally
		{
			try
			{
				stm.close();
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void copyCompany(int importID, int newID)
	{
		String sql = "select id,name,box,town from adempiere.z_insured_company where insurance_id= " + importID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String box = rs.getString(3);
				String town = rs.getString(4);

				MInsuredCompany i = new MInsuredCompany(getCtx(), 0, get_TrxName());
				i.setimport_id(id);
				i.seticoname(name);
				i.setinscomp_boxno(box);
				i.setinscomp_town(town);
				i.sethms_insco_ID(newID);
				i.save();
				updatePatient(id, i.gethms_insuredco_ID());
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
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void updatePatient(int id, int gethms_insuredco_ID)
	{
		String sql = "select patient_id,company_id from adempiere.z_patient_company where company_id= " + id;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int patient_id = rs.getInt(1);
				int company_id = rs.getInt(2);

				updatePatientInsurance(patient_id, gethms_insuredco_ID);
			}

		} catch (Exception e)
		{

		} finally
		{
			try
			{
				stm.close();
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	int x = 1;

	private void updatePatientInsurance(int patient_id, int gethms_insuredco_ID)
	{
		String sql = "select * from adempiere.C_BPartner where old_patient_system_id= " + patient_id;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				System.out.println(x++);
				MBPartner bp = new MBPartner(getCtx(), rs, get_TrxName());
				bp.setInsuredCompany_ID(gethms_insuredco_ID);
				bp.save();
			}

		} catch (Exception e)
		{

		} finally
		{
			try
			{
				stm.close();
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
