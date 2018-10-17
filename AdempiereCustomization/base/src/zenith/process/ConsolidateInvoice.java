package zenith.process;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_C_Invoice;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class ConsolidateInvoice extends SvrProcess
{
	int C_BPartner_ID = 0;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			if (name.equals("C_BPartner_ID"))
			{
				C_BPartner_ID = para[i].getParameterAsInt();

			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception
	{
		return copyInvoice();

	}

	private String copyInvoice()
	{
		MInvoice newInv = null;
		final String whereClause = "C_BPartner_ID=? AND docstatus =? AND ispaid=?";
		Query query = MTable.get(getCtx(), X_C_Invoice.Table_ID).createQuery(whereClause, get_TrxName());
		query.setParameters(C_BPartner_ID, "CO", false).setOrderBy("C_Invoice_ID DESC").setOnlyActiveRecords(true);
		List<MInvoice> list = query.list();
		MInvoice[] invoices = list.toArray(new MInvoice[list.size()]);
		System.out.println("Length:" + invoices.length);
		// BigDecimal amt = Env.ZERO;
		for (int i = 0; i < invoices.length; i++)
		{
			System.out.println(invoices[i].get_ID());
			MInvoice inv = invoices[i];
			if (newInv == null)
			{
				inv.setProcessed(false);
				inv.setDocStatus(MInvoice.DOCSTATUS_Drafted);
				inv.setDocAction(MInvoice.DOCACTION_Complete);
				inv.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
				inv.setDateAcct(new Timestamp(System.currentTimeMillis()));

				inv.save(get_TrxName());
				newInv = inv;
			}
			if (newInv != null)
			{
				inv.setProcessed(false);
				inv.setDocStatus(MInvoice.DOCSTATUS_Drafted);
				inv.setDocAction(MInvoice.DOCACTION_Complete);
				inv.save();
				MInvoiceLine[] lines = inv.getLines();
				for (int j = 0; j < lines.length; j++)
				{
					lines[j].setProcessed(false);
					lines[j].save(get_TrxName());
					lines[j].setC_Invoice_ID(newInv.get_ID());
					lines[j].save(get_TrxName());
				}
			}
		}
		MInvoiceLine[] lines = newInv.getLines();
		// update business partner
		if (newInv != null)
		{
			if (newInv.getTreatmentDocID() == 0)
			{
				newInv.setTreatmentDocID(getLastInstanceID(newInv.getC_BPartner_ID()));
				newInv.save();
			}
			newInv.completeIt();
			newInv.setProcessed(false);
			newInv.setDocStatus(MInvoice.DOCSTATUS_Completed);
			newInv.setDocAction(MInvoice.DOCACTION_Close);
			newInv.save(get_TrxName());
			MBPartner bp = new MBPartner(getCtx(), newInv.getC_BPartner_ID(), get_TrxName());
			bp.setTotalOpenBalance();
			bp.save(get_TrxName());
		}

		System.out.println("Lines: " + lines.length);
		// newInv.completeIt();
		if (newInv.getDocStatus().equalsIgnoreCase(MInvoice.DOCSTATUS_Completed))
		{
			return "All Bills Consolidated Successfully. Invoice Number: " + newInv.getDocumentNo();
		} else
		{
			return "NO BILLS CONSOLIDATED";
		}
	}

	private int getLastInstanceID(int bpID)
	{
		String sql = "SELECT MAX(hms_treatment_doc_id) FROM hms_treatment_doc WHERE C_BPartner_ID = " + bpID;
		int treatID = DB.getSQLValue(get_TrxName(), sql);
		return treatID;
	}
}
