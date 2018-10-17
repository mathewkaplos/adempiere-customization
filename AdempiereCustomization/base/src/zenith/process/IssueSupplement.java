package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.model.MStorage;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.X_hms_supplement;

public class IssueSupplement extends SvrProcess
{
	
	int M_Product_ID = 0;
	BigDecimal Qty = Env.ZERO;
	private String Name = "";

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();

			} else if (name.equals("Qty"))
			{
				Qty = (BigDecimal) para[i].getParameter();
			} else if (name.equals("Name"))
			{
				Name = para[i].getParameterAsString();
			}

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		updateStock(M_Product_ID, Qty);
		newSupplemnt();
		run();
		return null;
	}

	int M_Warehouse_ID = 1000007; // pharmacy
	int M_Locator_ID = 1000007; // pharmacy

	private void updateStock(int M_Product_ID, BigDecimal Qty)
	{
		Map<Integer, BigDecimal> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		String sql = "select m_attributesetinstance_id,qtyonhand from adempiere.m_storage where m_product_id= "
				+ M_Product_ID + " and m_locator_id = " + M_Locator_ID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int m_attributesetinstance_id = rs.getInt(1);
				BigDecimal qtyonhand = rs.getBigDecimal(2);
				map.put(m_attributesetinstance_id, qtyonhand);
				list.add(m_attributesetinstance_id);
			}
		} catch (Exception e)
		{

		}
		BigDecimal required = Qty;
		for (Integer asi : list)
		{
			if (required.compareTo(map.get(asi)) == 1)
			{
				updateStorage(M_Product_ID, map.get(asi), asi);
				required = required.subtract(map.get(asi));
			} else if (required.compareTo(map.get(asi)) == 0)
			{
				updateStorage(M_Product_ID, map.get(asi), asi);
				required = Env.ZERO;
				break;
			} else if (required.compareTo(map.get(asi)) == -1)
			{
				updateStorage(M_Product_ID, required, asi);
				required = Env.ZERO;
				break;
			}
		}
		if (required.compareTo(Env.ZERO) == 1)
		{
			updateStorage(M_Product_ID, required, 0);
		}
	}

	private void updateStorage(int M_Product_ID, BigDecimal Qty, int asi)
	{
		MStorage.add(Env.getCtx(), M_Warehouse_ID, M_Locator_ID, M_Product_ID, asi, 0, Qty.negate(), Env.ZERO, Env.ZERO,
				null);
	}

	void run()
	{
		String sql = "UPDATE adempiere.m_storage SET qtyonhand = 0  WHERE qtyonhand<0 ";
		DB.executeUpdate(sql, get_TrxName());
	}

	void newSupplemnt()
	{
		X_hms_supplement hms_supplement = new X_hms_supplement(getCtx(), 0, get_TrxName());
		hms_supplement.setM_Product_ID(M_Product_ID);
		hms_supplement.setQty(Qty);
		hms_supplement.setName(Name);
		hms_supplement.save();
	}
}
