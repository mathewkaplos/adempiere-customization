package zenith.process;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.MBilling;
import zenith.model.MOtherCharges;
import zenith.model.MTreatmentDoc;

public class CompleteOtherCharges extends SvrProcess
{
	MOtherCharges otherCharges = null;
	private String billRule = "";
	private boolean billingAfterService = false;

	@Override
	protected void prepare()
	{
		otherCharges = new MOtherCharges(getCtx(), getRecord_ID(), get_TrxName());

	}

	int ProdID = 0;

	@Override
	protected String doIt() throws Exception
	{
		newBill();

		return null;
	}

	private void newBill()
	{

		// billing_rule
		String sql2 = "select billing_rule from c_bp_group where c_bp_group_id = " + otherCharges.getC_BP_Group_ID();
		// setup settings
		billRule = DB.getSQLValueString(get_TrxName(), sql2);
		billingAfterService = HmsSetup.billingAfterService(Env.getContextAsInt(getCtx(), "#AD_Client_ID"),
				get_TrxName());

		if (otherCharges.getM_Product_ID() > 0)
			ProdID = otherCharges.getM_Product_ID();
		else
			ProdID = otherCharges.getM_Product_IDOther();
		// under some conditions
		// complet the order whic creates an invoice
		if (otherCharges.getAmt().compareTo(Env.ZERO) != 1)
		{
			throw new AdempiereException("Amount should be more than ZERO (0) !.");
		}

		BigDecimal amount = otherCharges.getAmt();
		MBilling billing = new MBilling(getCtx(), 0, get_TrxName());
		// billing.setC_BPartner_ID(C_BPartner_ID);
		billing.sethms_treatment_doc_ID(otherCharges.gethms_treatment_doc_ID());
		billing.setC_BPartner_ID(getBP_ID(otherCharges.gethms_treatment_doc_ID()));
		billing.setM_Product_ID(ProdID);
		billing.setPrice(amount);
		billing.setQty(Env.ONE);
		billing.setTotalAmt(amount);

		billing.setPriceActual(amount);
		billing.setLineNetAmt(amount);
		billing.setrounded_lineamt(amount);
		billing.setitem_type("OTHER");
		billing.setis_othercharges(true);

		billing.save();

		if (billingAfterService || billRule.trim().equalsIgnoreCase("PA".trim()))
		{
			billing.setpay_after(true);
			billing.save();
		} else
		{
			billing.setpay_after(false);
			billing.setpay(true);
			billing.save();
		}
		otherCharges.setotherCharges_completed(true);
		otherCharges.save();
	}

	private int getBP_ID(int docID)
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), docID, get_TrxName());
		return doc.getC_BPartner_ID();
	}
}
