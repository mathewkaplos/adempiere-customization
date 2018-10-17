package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class StockAdjustmentLine extends X_hms_stock_adjustment_line
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4140713494719619159L;

	public StockAdjustmentLine(Properties ctx, int hms_stock_adjustment_line_ID, String trxName)
	{
		super(ctx, hms_stock_adjustment_line_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public StockAdjustmentLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
