package zenith.imports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MOrder;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.X_hms_diagnosis;

public class CopyDiagnosis extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		//copyDiag();
		return null;
	}

	int x = 1;

	private void copyDiag()
	{

		String sql = "SELECT name from z_diagnosis ";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				System.out.println(x++);
				String name = rs.getString(1);

				X_hms_diagnosis diag = new X_hms_diagnosis(getCtx(), 0, get_TrxName());
				diag.setName(name);
				diag.sethms_diagnosisg_ID(1000001);
				diag.sethms_diagnosissubg_ID(1000001);

				diag.save();
			}
		} catch (Exception e)
		{

		}
	}
}
