package zenith.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MTest;

public class EditLabTest extends SvrProcess
{
	private String Name = null;
	private BigDecimal cash_price = Env.ZERO;
	private BigDecimal corporate_price = Env.ZERO;
	private int M_Product_ID = 0;
	private int hms_test_ID =0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("Name"))
			{
				Name = para[i].getParameterAsString().toUpperCase();
				// charge_per_bed
			} else if (name.equals("cash_price"))
			{
				cash_price = (BigDecimal) para[i].getParameter();
			} else if (name.equals("corporate_price"))
			{
				corporate_price = (BigDecimal) para[i].getParameter();
			} else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("hms_test_ID"))
			{
				hms_test_ID = para[i].getParameterAsInt();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		editProduct();
		editTest();

		return null;
	}

	private void editProduct()
	{
		MProduct product = new MProduct(getCtx(), M_Product_ID, getName());
		product.setValue(Name);
		product.setName(Name);
		product.setC_TaxCategory_ID(1000000);
		product.setC_UOM_ID(100);
		product.saveEx(get_TrxName());
		removePriceList();
		addProductPrice(product);
		M_Product_ID = product.getM_Product_ID();

	}

	private void removePriceList()
	{
		String sql = "DELETE FROM M_ProductPrice WHERE M_Product_ID ="+M_Product_ID;
		DB.executeUpdate(sql, get_TrxName());
	}

	private void editTest()
	{
		MTest test = new MTest(getCtx(), hms_test_ID, get_TrxName());
		test.setName(Name);
		test.setPrice(cash_price);
		test.setM_Product_ID(M_Product_ID);
		test.save();

	}

	void addProductPrice(MProduct product)
	{
		MProductPrice productPrice = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice.setM_Product_ID(product.get_ID());
		productPrice.setM_PriceList_Version_ID(1000003);

		productPrice.setPriceList(cash_price);
		productPrice.setPriceStd(cash_price);
		productPrice.setPriceLimit(cash_price);
		productPrice.saveEx(get_TrxName());

		MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice2.setM_Product_ID(product.get_ID());

		productPrice2.setM_PriceList_Version_ID(1000004);
		productPrice2.setPriceList(corporate_price);
		productPrice2.setPriceStd(corporate_price);
		productPrice2.setPriceLimit(corporate_price);
		productPrice2.saveEx(get_TrxName());

		MProductPrice productPrice3 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice3.setM_Product_ID(product.get_ID());
		productPrice3.setM_PriceList_Version_ID(1000006);

		productPrice3.setPriceList(corporate_price);
		productPrice3.setPriceStd(corporate_price);
		productPrice3.setPriceLimit(corporate_price);
		productPrice3.saveEx(get_TrxName());
	}
}
