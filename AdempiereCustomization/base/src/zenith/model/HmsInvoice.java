package zenith.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

public class HmsInvoice extends X_hms_invoice
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2220896230038308579L;

	public HmsInvoice(Properties ctx, int hms_invoice_ID, String trxName)
	{
		super(ctx, hms_invoice_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public HmsInvoice(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	HmsInvoiceLine[] lines = null;

	public HmsInvoiceLine[] getLines()
	{
		List<HmsInvoiceLine> list = new ArrayList<HmsInvoiceLine>();

		String sql = "SELECT * FROM adempiere.hms_invoice_line line WHERE line.hms_invoice_ID =" + this.get_ID();
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				HmsInvoiceLine line = new HmsInvoiceLine(getCtx(), rs, get_TrxName());
				list.add(line);
			}

		} catch (Exception e)
		{

		}
		// bills = new MBilling[list.size()];
		lines = list.toArray(new HmsInvoiceLine[list.size()]);
		return lines;
	}

}
