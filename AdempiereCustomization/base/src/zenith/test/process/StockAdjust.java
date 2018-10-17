package zenith.test.process;

import java.math.BigDecimal;

import org.compiere.model.MStorage;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class StockAdjust extends SvrProcess
{
	int M_Product_ID = 1008064;
	int M_Warehouse_ID = 1000007;
	int M_Locator_ID = 1000007;
	int M_AttributeSetInstance_ID = 0;

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		adjust(M_Product_ID, Qty);
		return null;
	}

	BigDecimal Qty = BigDecimal.valueOf(2);

	private void adjust(int M_Product_ID, BigDecimal Qty)
	{
		boolean k = !MStorage.add(getCtx(), M_Warehouse_ID, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID, 0,
				Qty, Env.ZERO, Env.ZERO, null);
	}
}
