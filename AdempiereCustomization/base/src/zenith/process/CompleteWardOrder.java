package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.zenith.util.Stock;

import zenith.model.WardOrderLine;
import zenith.model.X_hms_ward_order;

public class CompleteWardOrder extends SvrProcess
{
	private int orderID = 0;

	@Override
	protected void prepare()
	{

		orderID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		updateStorage();
		complete();
		return null;
	}

	private void complete()
	{
		X_hms_ward_order order = new X_hms_ward_order(getCtx(), orderID, get_TrxName());
		order.setcompleted(true);
		order.save();

	}

	private void updateStorage() throws Exception
	{
		String sql = "SELECT * FROM adempiere.hms_ward_orderline WHERE hms_ward_order_ID= " + orderID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				WardOrderLine ol = new WardOrderLine(getCtx(), rs, get_TrxName());
				update(ol);
			}
		} catch (Exception e)
		{

		} finally
		{
			stm.close();
			rs.close();
			stm = null;
			rs = null;
		}

	}

	private void update(WardOrderLine ol)
	{
		Stock stock = new Stock(1000007, 1000007, ol.getM_Product_ID());
		stock.updateStock(ol.getQty()).updateQtyOnHand();
	}
}
