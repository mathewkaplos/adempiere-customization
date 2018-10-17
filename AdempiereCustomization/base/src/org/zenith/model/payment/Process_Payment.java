package org.zenith.model.payment;

import org.compiere.model.MPayment;
import org.compiere.process.SvrProcess;

public class Process_Payment extends SvrProcess {
	private int _paymentID = 0;

	@Override
	protected void prepare() {
		_paymentID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		completePayment();
		return null;
	}

	private void completePayment() {
		MPayment payment = new MPayment(getCtx(), _paymentID, get_TrxName());
		payment.processIt(MPayment.DOCSTATUS_Completed);
		payment.prepareIt();
		payment.completeIt();
		payment.setDocStatus(MPayment.DOCSTATUS_Completed);
		///payment.setPosted(true);
		payment.saveEx(get_TrxName());
	}
}
