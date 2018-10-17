package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.X_hms_stock_take;
import zenith.model.X_hms_stock_take_header;

public class CompleteStockTake extends SvrProcess
{

	int M_Locator_ID = 0;

	@Override
	protected void prepare()
	{
		X_hms_stock_take_header head = new X_hms_stock_take_header(getCtx(), getRecord_ID(), get_TrxName());
		M_Locator_ID = head.getM_Locator_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		getStockTakeLines();
		return null;
	}

	private void getStockTakeLines()
	{
		int x = 1;
		int y = 100001;
		String sql = "SELECT * FROM hms_stock_take WHERE hms_stock_take_header_ID =" + getRecord_ID();
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{

				X_hms_stock_take line = new X_hms_stock_take(getCtx(), rs, get_TrxName());
				if (line.getQty() == Env.ZERO || line.getphysical_qty() == Env.ZERO
						|| line.getM_AttributeSetInstance_ID() == 0)
				{
					// do nothing

				} else
				{
					createUpdateStorage(line);
				}
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

		MStorage storage = MStorage.getCreate(getCtx(), M_Locator_ID, line.getM_Product_ID(),
				line.getM_AttributeSetInstance_ID(), get_TrxName());
		storage.setQtyOnHand(line.getphysical_qty());
		storage.save();
	}

}
