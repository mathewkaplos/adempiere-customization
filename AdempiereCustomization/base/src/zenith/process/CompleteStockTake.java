package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import zenith.model.X_hms_stock_take;
import zenith.model.X_hms_stock_take_header;

public class CompleteStockTake extends SvrProcess
{

	@Override
	protected void prepare()
	{

	}

	@Override
	protected String doIt() throws Exception
	{
		getStockTakeLines();
		X_hms_stock_take_header header = new X_hms_stock_take_header(getCtx(), getRecord_ID(), get_TrxName());
		header.setcompleted(true);
		header.save();
		return null;
	}

	private void getStockTakeLines()
	{
		String sql = "SELECT * FROM hms_stock_take WHERE hms_stock_take_header_ID =" + getRecord_ID();
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{

				X_hms_stock_take line = new X_hms_stock_take(getCtx(), rs, get_TrxName());
				createUpdateStorage(line);
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

	private void createUpdateStorage(X_hms_stock_take line)
	{

		MStorage storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), line.getM_Product_ID(),
				line.getM_AttributeSetInstance_ID(), get_TrxName());
		storage.setQtyOnHand(line.getphysical_qty());
		storage.save();
	}

}
