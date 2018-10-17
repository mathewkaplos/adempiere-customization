package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MergeStock extends SvrProcess
{

	DailyPharm dailyPharm = null;
	MainStock mainStock = null;

	@Override
	protected void prepare()
	{
		
	}

	@Override
	protected String doIt() throws Exception
	{
		dd();
		return null;
	}

	private void dd()
	{
		int num = 1;
		String sql = "SELECT  M_Product_ID FROM adempiere.M_Product";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				int M_Product_ID = rs.getInt(1);
				merge(M_Product_ID);
				delete(M_Product_ID);
				newRecord();
				System.out.println(num++);
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				if (stm != null)
					stm.close();
				if (rs != null)
					rs.close();
				stm = null;
				rs = null;

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

	}

	private void newRecord()
	{
		pharm(dailyPharm);
		main(mainStock);
	}

	void pharm(DailyPharm d)
	{
		if (dailyPharm != null)
		{
			MStorage storage = new MStorage(getCtx(), 0, get_TrxName());
			storage.setM_AttributeSetInstance_ID(d.getM_attributesetinstance_id());
			storage.setQtyOnHand(d.getQty());
			storage.setM_Locator_ID(1000007);
			storage.setM_Product_ID(d.getM_Product_ID());
			storage.save();
			dailyPharm = null;
		}

	}

	void main(MainStock m)
	{
		if (mainStock != null)
		{
			MStorage storage = new MStorage(getCtx(), 0, get_TrxName());
			storage.setM_AttributeSetInstance_ID(m.getM_attributesetinstance_id());
			storage.setQtyOnHand(m.getQty());
			storage.setM_Locator_ID(1000024);
			storage.setM_Product_ID(m.getM_Product_ID());
			storage.save();
			mainStock = null;
		}
	}

	private void delete(int M_Product_ID)
	{
		try
		{
			String sql = "delete from m_storage where m_product_id =" + M_Product_ID;
			DB.executeUpdate(sql, get_TrxName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	void merge(int M_Product_ID)
	{
		updateMainStore(M_Product_ID);
		updateDailyPharmacy(M_Product_ID);
	}

	private void updateMainStore(int M_Product_ID)
	{
		String sql = "select m_product_id, sum(qtyonhand), MAX(m_attributesetinstance_id) "
				+ " from adempiere.m_storage where m_locator_id in (1000024) and M_Product_ID= " + M_Product_ID
				+ " group by m_product_id";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				int m_Product_ID = rs.getInt(1);
				BigDecimal qty = rs.getBigDecimal(2);
				int m_attributesetinstance_id = rs.getInt(3);
				MainStock m = new MainStock(m_Product_ID, m_attributesetinstance_id, qty);
				mainStock = m;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				rs.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{

				e.printStackTrace();
			}
		}

	}

	private void updateDailyPharmacy(int M_Product_ID)
	{
		String sql = "select m_product_id, sum(qtyonhand), MAX(m_attributesetinstance_id) "
				+ " from adempiere.m_storage where m_locator_id in (1000007) and M_Product_ID= " + M_Product_ID
				+ " group by m_product_id";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				int m_Product_ID = rs.getInt(1);
				BigDecimal qty = rs.getBigDecimal(2);
				int m_attributesetinstance_id = rs.getInt(3);
				DailyPharm d = new DailyPharm(m_Product_ID, m_attributesetinstance_id, qty);
				dailyPharm = d;

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stm.close();
				rs.close();
				stm = null;
				rs = null;
			} catch (SQLException e)
			{

				e.printStackTrace();
			}
		}

	}
}

class DailyPharm
{
	private int M_Product_ID = 0;
	private int m_attributesetinstance_id = 0;
	private BigDecimal qty = Env.ZERO;

	public DailyPharm(int m_Product_ID, int m_attributesetinstance_id, BigDecimal qty)
	{
		this.M_Product_ID = m_Product_ID;
		this.m_attributesetinstance_id = m_attributesetinstance_id;
		this.qty = qty;
	}

	public int getM_Product_ID()
	{
		return M_Product_ID;
	}

	public int getM_attributesetinstance_id()
	{
		return m_attributesetinstance_id;
	}

	public BigDecimal getQty()
	{
		return qty;
	}

}

class MainStock
{
	private int M_Product_ID = 0;
	private int m_attributesetinstance_id = 0;
	private BigDecimal qty = Env.ZERO;

	public MainStock(int m_Product_ID, int m_attributesetinstance_id, BigDecimal qty)
	{
		this.M_Product_ID = m_Product_ID;
		this.m_attributesetinstance_id = m_attributesetinstance_id;
		this.qty = qty;
	}

	public int getM_Product_ID()
	{
		return M_Product_ID;
	}

	public int getM_attributesetinstance_id()
	{
		return m_attributesetinstance_id;
	}

	public BigDecimal getQty()
	{
		return qty;
	}
}
