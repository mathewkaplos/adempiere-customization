package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MOrder;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TestPrepayDoctype extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		getPrepayDoctype();
		return null;
	}

	public int getPrepayDoctype()
	{
		String docType = MOrder.DocSubTypeSO_Prepay;
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(getCtx(), "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ docType + "'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				docTypeID = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		}
		return docTypeID;
	}
}
