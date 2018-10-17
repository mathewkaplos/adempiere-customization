package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class HmsInvoiceLine extends X_hms_invoice_line
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8688090370031016070L;

	public HmsInvoiceLine(Properties ctx, int hms_invoice_line_ID, String trxName)
	{
		super(ctx, hms_invoice_line_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public HmsInvoiceLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
