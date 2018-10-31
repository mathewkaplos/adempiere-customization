package zenith.imports;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPrice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class CopyProduct extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		selectCategory();
		return null;
	}

	private void selectCategory()
	{
		String sql = "SELECT * from M_Product_Category ";
		PreparedStatement stm = null;
		try 
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MProductCategory cat = new MProductCategory(getCtx(), rs, get_TrxName());
				copyProduct(cat);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(stm);
			stm = null;
		}
	}
	int xp = 1;
	private void copyProduct(MProductCategory productCategory)
	{
		String sql2 = "SELECT product_id,product_name,product_category,product_code,price,initial_stock FROM z_product WHERE product_category ="
				+ productCategory.getImport_category_id();
		PreparedStatement ps2 = null;
	
		try
		{
			ps2 = DB.prepareStatement(sql2, get_TrxName());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next())
			{
				System.out.println(xp++);
				int product_id = rs2.getInt(1);
				String product_name = rs2.getString(2);
				int product_category = rs2.getInt(3);
				String product_code = rs2.getString(4);
				BigDecimal price = rs2.getBigDecimal(5);
				BigDecimal initial_stock = rs2.getBigDecimal(6);
				MProduct product = new MProduct(getCtx(), 0, getName());
				product.setValue(product_code);
				product.setInitialStock(initial_stock);

				product.setName(product_name);
				product.setM_Product_Category_ID(productCategory.getM_Product_Category_ID());
				product.setC_TaxCategory_ID(1000000);
				product.setC_UOM_ID(100);

				product.setImport_category_id(product_category);
				product.setImport_product_id(product_id);

				product.saveEx(get_TrxName());

				addPriceList(product.getM_Product_ID(), product_id, price);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			DB.close(ps2);
			ps2 = null;
		}
	}

	private void addPriceList(int M_Product_ID, int storage_productid, BigDecimal price)
	{
		Map<Integer, BigDecimal> pricelists = new HashMap<>();
		String sql2 = "SELECT storage_pricelistid,storage_unitprice  FROM z_pricelist WHERE storage_productid ="
				+ storage_productid;
		PreparedStatement ps2 = null;
		int x = 0;
		try
		{
			ps2 = DB.prepareStatement(sql2, get_TrxName());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next())
			{
				Integer storage_pricelistid = rs2.getInt(1);
				BigDecimal storage_unitprice = rs2.getBigDecimal(2);
				x++;
				if (x < 3)
				{
					pricelists.put(storage_pricelistid, storage_unitprice);
				} else
				{
					break;
				}
			}
		} catch (Exception e)
		{

		} finally
		{
			DB.close(ps2);
			ps2 = null;
		}

		if (pricelists.size() == 2)
		{
			MProductPrice productPrice1 = new MProductPrice(getCtx(), 0, get_TrxName());
			productPrice1.setM_Product_ID(M_Product_ID);
			productPrice1.setM_PriceList_Version_ID(1000003);
			productPrice1.setPriceList(pricelists.get(1));
			productPrice1.setPriceStd(pricelists.get(1));
			productPrice1.setPriceLimit(pricelists.get(1));
			productPrice1.saveEx(get_TrxName());

			MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
			productPrice2.setM_Product_ID(M_Product_ID);
			productPrice2.setM_PriceList_Version_ID(1000004);
			productPrice2.setPriceList(pricelists.get(2));
			productPrice2.setPriceStd(pricelists.get(2));
			productPrice2.setPriceLimit(pricelists.get(2));
			productPrice2.saveEx(get_TrxName());

			MProductPrice productPrice3 = new MProductPrice(getCtx(), 0, get_TrxName());
			productPrice3.setM_Product_ID(M_Product_ID);
			productPrice3.setM_PriceList_Version_ID(1000006);
			productPrice3.setPriceList(pricelists.get(2));
			productPrice3.setPriceStd(pricelists.get(2));
			productPrice3.setPriceLimit(pricelists.get(2));
			productPrice3.saveEx(get_TrxName());

		} else
		{
			addDefauldPriceLists(M_Product_ID, price);
		}
	}

	private void addDefauldPriceLists(int M_Product_ID, BigDecimal price)
	{
		MProductPrice productPrice1 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice1.setM_Product_ID(M_Product_ID);
		productPrice1.setM_PriceList_Version_ID(1000003);
		productPrice1.setPriceList(price);
		productPrice1.setPriceStd(price);
		productPrice1.setPriceLimit(price);
		productPrice1.saveEx(get_TrxName());

		MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice2.setM_Product_ID(M_Product_ID);
		productPrice2.setM_PriceList_Version_ID(1000004);
		productPrice2.setPriceList(price);
		productPrice2.setPriceStd(price);
		productPrice2.setPriceLimit(price);
		productPrice2.saveEx(get_TrxName());

		MProductPrice productPrice3 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice3.setM_Product_ID(M_Product_ID);
		productPrice3.setM_PriceList_Version_ID(1000006);
		productPrice3.setPriceList(price);
		productPrice3.setPriceStd(price);
		productPrice3.setPriceLimit(price);
		productPrice3.saveEx(get_TrxName());
	}

}
