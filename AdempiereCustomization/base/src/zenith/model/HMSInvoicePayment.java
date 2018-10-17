package zenith.model;

import java.sql.ResultSet;
import java.util.Properties;

public class HMSInvoicePayment extends X_hms_invoice_payments
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HMSInvoicePayment(Properties ctx, int hms_insuance_payment_ID, String trxName)
	{
		super(ctx, hms_insuance_payment_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public HMSInvoicePayment(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
