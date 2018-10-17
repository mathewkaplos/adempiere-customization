package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MTreatmentDoc;

public class ZUpdateDrugStatus extends SvrProcess
{
	//zenith.process.ZUpdateDrugStatus
	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		updateAllDrugs();
		return null;
	}

	int x = 0;

	private void updateAllDrugs() // one time use
	{
		String sql = " SELECT hms_treatment_doc_ID FROM adempiere.hms_treatment_doc";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			while (rs.next())
			{
				int treatID = rs.getInt(1);
				MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), treatID, null);
				doc.updateDrugStatus(doc);
				System.out.println(x++);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
	}

}
