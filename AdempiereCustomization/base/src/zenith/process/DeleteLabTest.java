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

public class DeleteLabTest extends SvrProcess
{
	private String Name = null;
	private int M_Product_ID = 0;
	private int hms_test_ID = 0;

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
				Name = para[i].getParameterAsString();

			} else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
			}

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		hms_test_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		removePriceList();
		removeProduct();
		deleteTest();

		return null;
	}

	private void removeProduct()
	{
		MProduct product = new MProduct(getCtx(), M_Product_ID, getName());
		product.delete(true);
	}

	private void removePriceList()
	{
		String sql = "DELETE FROM M_ProductPrice WHERE M_Product_ID =" + M_Product_ID;
		DB.executeUpdate(sql, get_TrxName());
	}

	private void deleteTest()
	{
		MTest test = new MTest(getCtx(), hms_test_ID, get_TrxName());
		test.delete(true);

	}

}
