package zenith.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import zenith.model.X_hms_ward_orderline;

public class AddWardOrder extends SvrProcess
{
	private int M_Product_ID = 0;
	private BigDecimal Qty = Env.ZERO;
	private int orderID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
			} else if (name.equals("Qty"))
			{
				Qty = (BigDecimal) para[i].getParameter();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		orderID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		add();
		return null;
	}

	private void add()
	{
		X_hms_ward_orderline ol = new X_hms_ward_orderline(getCtx(), 0, get_TrxName());
		ol.setM_Product_ID(M_Product_ID);
		ol.setQty(Qty);
		ol.sethms_ward_order_ID(orderID);
		ol.save();
	}
}
