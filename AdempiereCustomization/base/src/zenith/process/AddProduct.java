package zenith.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MStorage;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class AddProduct extends SvrProcess
{
	private String Name = null;
	private int M_Product_Category_ID = 0;
	private BigDecimal price1 = Env.ZERO;
	private BigDecimal price2 = Env.ZERO;
	private BigDecimal Qty = Env.ZERO;
	// private BigDecimal price3 = Env.ZERO;

	// zenith.process.AddProduct
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
			} else if (name.equals("M_Product_Category_ID"))
			{
				M_Product_Category_ID = para[i].getParameterAsInt();
			} else if (name.equals("price1"))
			{
				price1 = (BigDecimal) para[i].getParameter();
			} else if (name.equals("price2"))
			{
				price2 = (BigDecimal) para[i].getParameter();
			} else if (name.equals("Qty"))
			{
				Qty = (BigDecimal) para[i].getParameter();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception
	{
		// price3 = (price1.add(price2)).divide(BigDecimal.valueOf(2));
		addProduct();
		JOptionPane.showMessageDialog(null, Name + " Added Successfully...", "Information Message", 1);

		return null;
	}

	private void addProduct()
	{
		MProduct product = new MProduct(getCtx(), 0, getName());
		product.setValue(Name);
		product.setName(Name);
		product.setM_Product_Category_ID(M_Product_Category_ID);
		product.setC_TaxCategory_ID(1000000);
		product.setC_UOM_ID(100);
		product.setProductType("I");
		product.saveEx(get_TrxName());
		addProductPrice(product);

	}

	void addProductPrice(MProduct product)
	{
		MProductPrice productPrice = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice.setM_Product_ID(product.get_ID());
		productPrice.setM_PriceList_Version_ID(1000003);
		productPrice.setPriceList(price1);
		productPrice.setPriceStd(price1);
		productPrice.setPriceLimit(price1);
		productPrice.saveEx(get_TrxName());

		MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice2.setM_Product_ID(product.get_ID());
		productPrice2.setM_PriceList_Version_ID(1000004);
		productPrice2.setPriceList(price2);
		productPrice2.setPriceStd(price2);
		productPrice2.setPriceLimit(price2);
		productPrice2.saveEx(get_TrxName());

		//
		MProductPrice productPrice3 = new MProductPrice(getCtx(), 0, get_TrxName());
		productPrice3.setM_Product_ID(product.get_ID());
		productPrice3.setM_PriceList_Version_ID(1000006);
		productPrice3.setPriceList(price2);
		productPrice3.setPriceStd(price2);
		productPrice3.setPriceLimit(price2);
		productPrice3.saveEx(get_TrxName());

		addStock(product.get_ID());
	}

	private void addStock(int M_Product_ID)
	{
		MStorage.add(getCtx(), 1000007, 1000007, M_Product_ID, 0, 0, Qty, Env.ZERO, Env.ZERO, get_TrxName());
	}
}
