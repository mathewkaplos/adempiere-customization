package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.X_hms_stock_take;
import zenith.model.X_hms_stock_take_header;
import zenith.model.hms_stock_take;

public class CreateStockTakeLines extends SvrProcess
{

	int M_Locator_ID = 0;

	List<MStorage> l_storage = null;
	List<MProduct> l_product = null;

	@Override
	protected void prepare()
	{
		X_hms_stock_take_header head = new X_hms_stock_take_header(getCtx(), getRecord_ID(), get_TrxName());
		M_Locator_ID = head.getM_Locator_ID();
	}

	int x = 1;
	int y = 10001;

	@Override
	protected String doIt() throws Exception
	{
		getStorageProducts();
		for (MStorage s : l_storage)
		{
			createLineFromStorage(s);
			System.out.println(x++);
		}
		getNonStorageProducts();
		for (MProduct p : l_product)
		{
			createNonStorageLine(p);
			System.out.println(y++);
		}
		//
		// createLines();
		return null;
	}

	private void getStorageProducts()
	{
		List<MStorage> list = new ArrayList<>();
		String sql = "SELECT * FROM M_Storage WHERE M_Locator_ID =" + M_Locator_ID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MStorage storage = new MStorage(getCtx(), rs, get_TrxName());
				list.add(storage);
			}

		} catch (Exception e)
		{

		}
		l_storage = list;
	}

	private void getNonStorageProducts()
	{
		List<MProduct> list = new ArrayList<>();
		String sql = "SELECT * FROM M_Product WHERE producttype ='I' AND M_Product_ID  NOT IN (SELECT M_Product_ID FROM M_Storage WHERE M_Locator_ID ="
				+ M_Locator_ID + ")";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MProduct product = new MProduct(getCtx(), rs, get_TrxName());
				list.add(product);
			}

		} catch (Exception e)
		{

		}
		l_product = list;
	}

	private void createLineFromStorage(MStorage storage)
	{
		X_hms_stock_take line = new X_hms_stock_take(getCtx(), 0, get_TrxName());
		line.setM_Product_ID(storage.getM_Product_ID());
		line.setM_Product_Category_ID(MProduct.get(getCtx(), storage.getM_Product_ID()).getM_Product_Category_ID());
		line.sethms_stock_take_header_ID(getRecord_ID());
		line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
		line.setQty(storage.getQtyOnHand());
		line.setphysical_qty(Env.ZERO);
		line.setin_stock(true);
		line.save();
	}

	private void createNonStorageLine(MProduct product)
	{
		hms_stock_take line = new hms_stock_take(getCtx(), 0, get_TrxName());
		line.setM_Product_ID(product.getM_Product_ID());
		line.setM_Product_Category_ID(product.getM_Product_Category_ID());
		line.sethms_stock_take_header_ID(getRecord_ID());
		line.setQty(Env.ZERO);
		line.setphysical_qty(Env.ZERO);
		line.save();
	}
}
