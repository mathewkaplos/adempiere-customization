package zenith.test.process;

import java.math.BigDecimal;

import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;

public class StockAvailable extends SvrProcess
{
	int M_Product_ID = 1008064;
	int M_Warehouse_ID = 1000007;
	int M_Locator_ID = 0;
	int M_AttributeSetInstance_ID = 0;

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String doIt() throws Exception
	{
		getStock();
		
		return null;
	}

	private void getStock()
	{
		BigDecimal qty = MStorage.getQtyAvailable(M_Warehouse_ID, M_Locator_ID, M_Product_ID,
				M_AttributeSetInstance_ID,
				get_TrxName());
		System.out.println(qty);
	}
}
