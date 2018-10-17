package zenith.process;

import java.util.logging.Level;

import org.compiere.model.MInvoice;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import zenith.model.InsuarancePayline;

public class Bill_Insurance extends SvrProcess
{

	private int treatmentid = 0;
	private int C_BPartner_ID = 0;
	private int C_Invoice_ID = 0;
	private int hms_insco_ID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("C_BPartner_ID"))
			{
				C_BPartner_ID = para[i].getParameterAsInt();
			} else if (name.equals("C_Invoice_ID"))
			{
				C_Invoice_ID = para[i].getParameterAsInt();
			} else if (name.equals("hms_insco_ID"))
			{
				hms_insco_ID = para[i].getParameterAsInt();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		// NEVER TODO Auto-generated method stub
		treatmentid = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		test();
		return null;
	}

	void test()
	{
		MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
		System.out.println("Total amt=" + invoice.getGrandTotal());
		System.out.println("Open=" + invoice.getOpenAmt().setScale(2));
		System.out.println(C_BPartner_ID);
		System.out.println(hms_insco_ID);

		bill(invoice);
	}

	private void bill(MInvoice invoice)
	{
		InsuarancePayline line = new InsuarancePayline(getCtx(), 0, get_TrxName());
		line.setC_BPartner_ID(C_BPartner_ID);
		line.sethms_insco_ID(hms_insco_ID);
		line.setC_Invoice_ID(C_Invoice_ID);
		line.setTotalAmt(invoice.getGrandTotal());
		line.setOpenAmt(invoice.getOpenAmt());
		line.setInvoiceDocumentNo(invoice.getDocumentNo());
		line.setDateInvoiced(invoice.getDateInvoiced());
		line.save();
	}
}
