package zenith.imports;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CopyPrice extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		int x = 1;
		String sql = "select * from adempiere.m_product where product_import_id>0";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{

			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				MProduct product = new MProduct(getCtx(), rs, get_TrxName());
				copy(product);
				// System.out.println(x++);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (stm != null)
				{
					stm.close();
					stm = null;
				}
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	int y = 1;

	private void copy(MProduct pro)
	{
		int storage_productid = pro.getImport_product_id();
		BigDecimal price1 = Env.ZERO;
		boolean hasPrice = false;
		String sql = "select MAX(storage_unitprice) from adempiere.z_pricelist where storage_productid ="
				+ storage_productid + "";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{

			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				hasPrice = true;
				BigDecimal price = rs.getBigDecimal(1);
				price1 = price;
				System.out.println(y++);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (stm != null)
				{
					stm.close();
					stm = null;
				}
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (hasPrice)
		{

			if (price1 != null)
				addPriceLists(pro.getM_Product_ID(), price1);
			else
			{
				System.out.println("k: " + k++);
			}
		}

	}

	int k = 1;

	private void addPriceLists(int M_Product_ID, BigDecimal price1)
	{
		deletePriceList(M_Product_ID);

		MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice2.setM_Product_ID(M_Product_ID);
		productPrice2.setM_PriceList_Version_ID(1000004);
		productPrice2.setPriceList(price1);
		productPrice2.setPriceStd(price1);
		productPrice2.setPriceLimit(price1);
		productPrice2.saveEx(get_TrxName());

	}

	private void deletePriceList(int m_Product_ID)
	{
		String sql = "DELETE FROM adempiere.M_ProductPrice WHERE M_Product_ID =" + m_Product_ID
				+ " AND M_PriceList_Version_ID IN(1000004)";
		DB.executeUpdate(sql, get_TrxName());

	}

}
