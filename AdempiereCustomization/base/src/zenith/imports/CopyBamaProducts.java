package zenith.imports;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class CopyBamaProducts extends SvrProcess
{
	// zenith.imports.CopyBamaProducts
	@Override
	protected void prepare()
	{

	}

	@Override
	protected String doIt() throws Exception
	{
		runIt();
		return null;
	}

	private void runIt()
	{
		int count = 0;
		String sql = "select name,cash,nhif, insurance from adempiere.z_import_product";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				String name = rs.getString(1);
				BigDecimal cash = rs.getBigDecimal(2);
				BigDecimal nhif = rs.getBigDecimal(3);
				BigDecimal insurance = rs.getBigDecimal(4);

				System.out.println(count++);

				newProduct(name, cash, nhif, insurance);

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

	void newProduct(String name, BigDecimal cash, BigDecimal nhif, BigDecimal insurance)
	{
		MProduct product = new MProduct(getCtx(), 0, getName());
		product.setValue(name);

		product.setName(name);
		product.setM_Product_Category_ID(1000213);
		product.setC_TaxCategory_ID(1000000);
		product.setC_UOM_ID(100);

		product.saveEx(get_TrxName());

		int M_Product_ID = product.getM_Product_ID();

		MProductPrice productPrice1 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice1.setM_Product_ID(M_Product_ID);
		productPrice1.setM_PriceList_Version_ID(1000003);
		productPrice1.setPriceList(cash);
		productPrice1.setPriceStd(cash);
		productPrice1.setPriceLimit(cash);
		productPrice1.saveEx(get_TrxName());

		MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice2.setM_Product_ID(M_Product_ID);
		productPrice2.setM_PriceList_Version_ID(1000004);
		productPrice2.setPriceList(nhif);
		productPrice2.setPriceStd(nhif);
		productPrice2.setPriceLimit(nhif);
		productPrice2.saveEx(get_TrxName());

		MProductPrice productPrice3 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice3.setM_Product_ID(M_Product_ID);
		productPrice3.setM_PriceList_Version_ID(1000006);
		productPrice3.setPriceList(insurance);
		productPrice3.setPriceStd(insurance);
		productPrice3.setPriceLimit(insurance);
		productPrice3.saveEx(get_TrxName());

	}

}
