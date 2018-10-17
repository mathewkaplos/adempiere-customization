package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import zenith.model.HMSPayment;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

public class ProcessPrepayment extends SvrProcess
{
	int payID = 0;
	int C_BPartner_ID = 0;
	int treatID = 0;
	int C_Payment_ID = 0;
	HMSPayment pay = null;
	MTreatmentDoc doc = null;
	Timestamp DateTrx = null;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("DateTrx"))
			{
				DateTrx = para[i].getParameterAsTimestamp();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		payID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		start2();
		// pay.setdone(true);
		// pay.save();
		// updateAllPrepayments();
		return null;
	}

	private void start2()
	{
		pay = new HMSPayment(getCtx(), payID, get_TrxName());
		treatID = pay.gethms_treatment_doc_ID();
		doc = new MTreatmentDoc(getCtx(), treatID, get_TrxName());
		C_BPartner_ID = pay.getC_BPartner_ID();
		BigDecimal mpesaAmt = pay.getmpesaAmt();
		BigDecimal cashAmt = pay.getcashAmt();
		BigDecimal totalAmt = pay.getPayAmt();
		createBilling(totalAmt, mpesaAmt, cashAmt);
		pay.setdone(true);

		//
		pay.setCreated(DateTrx);

		pay.save();

	}

	private void createBilling(BigDecimal amount, BigDecimal mpesaAmt, BigDecimal cashAmt)
	{

		MBilling billing = new MBilling(getCtx(), 0, get_TrxName());
		billing.sethms_treatment_doc_ID(doc.get_ID());
		billing.setC_BPartner_ID(C_BPartner_ID);
		billing.setM_Product_ID(CreateHospitalDefaults.prepaymentItemID);
		billing.setPrice(amount);
		billing.setQty(Env.ONE);
		billing.setTotalAmt(amount);

		billing.setPriceActual(amount);
		billing.setLineNetAmt(amount);//////////////////

		billing.setpaid(true);
		billing.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		billing.setitem_type("PREPAY");
		billing.sethms_payment_ID(payID);
		// billing.setmpesaAmt(mpesaAmt);
		billing.setcashAmt(amount);
		billing.setPaidAmt(amount);
		billing.setBalance(Env.ZERO);
		billing.setbill_date(DateTrx);
		billing.setCreated(DateTrx);
		billing.save();

	}

}
