package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class AbcASI extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		getRecords();
		return null;
	}

	private void getRecords()
	{
		Map<Integer, BigDecimal> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		String sql = "select m_attributesetinstance_id,qtyonhand from adempiere.m_storage where m_product_id= 1007979 and m_locator_id =1000007";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int m_attributesetinstance_id = rs.getInt(1);
				BigDecimal qtyonhand = rs.getBigDecimal(2);
				map.put(m_attributesetinstance_id, qtyonhand);
				list.add(m_attributesetinstance_id);
				System.out.println(m_attributesetinstance_id + " - " + qtyonhand);
			}
		} catch (Exception e)
		{

		}
		BigDecimal required = new BigDecimal("3");
		for (Integer asi : list)
		{
			if (required.compareTo(map.get(asi)) == 1)
			{
				System.out.println("Taken: " + map.get(asi) + " from " + asi);
				required = required.subtract(map.get(asi));
			} else if (required.compareTo(map.get(asi)) == 0)
			{
				System.out.println("Taken: " + map.get(asi) + " from " + asi);
				required = Env.ZERO;
				break;
			} else if (required.compareTo(map.get(asi)) == -1)
			{
				System.out.println("Taken: " + required + " from " + asi);
				required = Env.ZERO;
				break;
			}
		}
		if (required.compareTo(Env.ZERO) == 1)
		{
			System.out.println("Unsorted:" + required);
		}
	}

}
