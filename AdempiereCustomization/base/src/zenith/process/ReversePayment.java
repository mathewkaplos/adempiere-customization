package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.python.modules.synchronize;

import zenith.model.HMSPayment;
import zenith.model.MBilling;

public class ReversePayment extends SvrProcess
{
	int payID = 0;
	String reason = "";
	private boolean IsPrepayment = false;
	private HMSPayment payment = null;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("reversal_reason"))
			{
				reason = para[i].getParameterAsString();
			} else if (name.equals("IsPrepayment"))
			{
				IsPrepayment = para[i].getParameterAsBoolean();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		// NEVER TODO Auto-generated method stub
		payID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		System.out.println("12345");
		payment = new HMSPayment(getCtx(), payID, null);
		if (IsPrepayment)
		{
			reversePrepay();
		} else
		{
			payment.setpayment_reversed(true);
			payment.setreversal_reason(reason);
			payment.setdone(false);
			payment.save();
			updateBills(payment.gethms_payment_ID());
		}

		return null;
	}

	private void updateBills(int hms_payment_ID)
	{
		String sql = "SELECT * FROM adempiere.hms_billing bill WHERE bill.hms_payment_ID =" + hms_payment_ID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, get_TrxName());
				bill.setpaid(false);
				bill.setissued(false);
				bill.setBalance(bill.getLineNetAmt());
				bill.setPaidAmt(Env.ZERO);
				bill.setmpesaAmt(Env.ZERO);
				bill.setcashAmt(Env.ZERO);
				bill.setprevious_balance(Env.ZERO);
				bill.sethms_payment_ID(0);
				bill.save();
			}

		} catch (Exception e)
		{

		}
	}

	private void reversePrepay()
	{
		removePrepaymentItem(payID);
		payment.delete(true);
	}

	private void removePrepaymentItem(int hms_payment_ID)
	{
		String sql = "SELECT * FROM adempiere.hms_billing bill WHERE bill.hms_payment_ID =" + hms_payment_ID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			if (rs.next())
			{
				MBilling billing = new MBilling(getCtx(), rs, get_TrxName());
				billing.delete(true);

			}

		} catch (Exception e)
		{

		}
	}

}
