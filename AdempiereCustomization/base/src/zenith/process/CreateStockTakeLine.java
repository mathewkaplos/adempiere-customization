package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MStorage;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import zenith.model.hms_stock_take;

//Stock take for a single product
public class CreateStockTakeLine extends SvrProcess
{

	private int M_Product_ID;
	private int headerID = 0;

	@Override
	protected void prepare()
	{

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		headerID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		getStorage();
		return null;
	}

	private void getStorage()
	{

		String sql = "SELECT * FROM adempiere.M_Storage WHERE M_Product_ID =" + M_Product_ID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				MStorage storage = new MStorage(getCtx(), rs, get_TrxName());
				createNonStorageLine(storage);
			}

		} catch (Exception ex)
		{

		}
	}

	private void createNonStorageLine(MStorage s)
	{
		hms_stock_take line = new hms_stock_take(getCtx(), 0, get_TrxName());
		line.setM_Product_ID(s.getM_Product_ID());
		line.setM_Locator_ID(s.getM_Locator_ID());
		line.setM_AttributeSetInstance_ID(s.getM_AttributeSetInstance_ID());
		line.sethms_stock_take_header_ID(headerID);
		line.setQty(s.getQtyOnHand());
		line.setphysical_qty(s.getQtyOnHand());
		line.save();
	}
}
