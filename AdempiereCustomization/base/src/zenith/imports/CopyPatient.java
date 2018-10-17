package zenith.imports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import org.compiere.model.MBPartner;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class CopyPatient extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		copy();
		return null;
	}

	int x = 1;

	private void copy()
	{
		System.out.println("..........................");
		String sql = "select id, patient_no, name, dob,national_id,gender,reg_date, "
				+ "   pricelist,nhif_no,nhif_contribute  from adempiere.z_patient ";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{

				int id = rs.getInt(1);
				String patient_no = rs.getString(2);
				String name = rs.getString(3);
				Date dob = rs.getDate(4);
				String national_id = rs.getString(5);
				String gender = rs.getString(6);
				Date reg_date = rs.getDate(7);
				int pricelist = rs.getInt(8);
				String nhif_no = rs.getString(9);
				int nhif_contribute = rs.getInt(10);

				MBPartner bp = new MBPartner(getCtx(), 0, get_TrxName());
				bp.setOldPatientSystemID(id);
				bp.setOldPatientNo(patient_no);
				bp.setName(name);
				bp.setDor(new Timestamp(reg_date.getTime()));
				bp.setGender(gender);
				bp.setIDNo(national_id);
				bp.setDob(new Timestamp(dob.getTime()));
				bp.setNHIFNo(nhif_no);
				if (pricelist == 1)
					bp.setC_BP_Group_ID(1000001);
				else if (pricelist == 2)
					bp.setC_BP_Group_ID(1000002);
				else if (pricelist == 3)
					bp.setC_BP_Group_ID(1000003);
				else
					bp.setC_BP_Group_ID(1000001);
				bp.setC_Country_ID(219);

				if (nhif_contribute == 1)
					bp.setContributesToNHIF(true);

				bp.saveEx(get_TrxName());
				System.out.println(x++);
			}
		} catch (Exception e)
		{
			 //e.printStackTrace();
		}
	}
}
