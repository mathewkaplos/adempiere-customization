package org.zenith.model.payment;

import java.sql.Timestamp;

import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class Process_DirectSale extends SvrProcess {
	int C_Order_ID = 0;

	@Override
	protected void prepare() {
		C_Order_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		MOrder order = new MOrder(getCtx(), C_Order_ID, get_TrxName());
		boolean processed = order.processIt(MOrder.DOCSTATUS_Completed);
		System.out.println("processed processed= " + processed);
		//order.completeIt();
		order.setDocStatus(MOrder.DOCSTATUS_Completed);
		order.save();
		MInvoice[] invoices = order.getInvoices();
		int C_Invoice_ID = 0;
		int length = invoices.length;
		if (length > 0) {
			// get last invoice
			C_Invoice_ID = invoices[length - 1].getC_Invoice_ID();
			MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID,
					get_TrxName());
			newPayment(invoice, order);
			System.out.println("C_Invoice_ID=" + C_Invoice_ID);
		}
		return null;
	}

	private void newPayment(MInvoice invoice, MOrder order) {
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
		payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
		payment.setC_Invoice_ID(invoice.get_ID());
		payment.setC_DocType_ID(1000008); // AR Receipt payment
		payment.setC_Currency_ID(266); // KES
		payment.setC_CashBook_ID(1000000); // Petty Cash
		payment.setC_BankAccount_ID(1000003); // Some Bank 1000003 Petty Cash

		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		payment.setTrxType(MPayment.TRXTYPE_Sales);
		//
		payment.setR_AvsAddr(MPayment.R_AVSZIP_Unavailable);
		payment.setR_AvsZip(MPayment.R_AVSZIP_Unavailable);
		//
		payment.setIsReceipt(true); // ///
		payment.setIsApproved(false);
		payment.setIsReconciled(false);
		payment.setIsAllocated(false);
		payment.setIsOnline(false);
		payment.setIsSelfService(false);
		payment.setIsDelayedCapture(false);
		payment.setIsPrepayment(false);
		payment.setProcessed(false);
		payment.setProcessing(false);
		payment.setPosted(false);
		//payment.setIsVATWithholding(true);
		//payment.setIsConsultancyWithholding(false);
		//
		payment.setPayAmt(invoice.getGrandTotal());
		payment.setDiscountAmt(Env.ZERO);
		payment.setTaxAmt(Env.ZERO);
		payment.setWriteOffAmt(Env.ZERO);
		payment.setIsOverUnderPayment(false);
		payment.setOverUnderAmt(Env.ZERO);
		//
		payment.setDateTrx(new Timestamp(System.currentTimeMillis()));
		payment.setDateAcct(invoice.getDateInvoiced());
		//payment.setTenderType(MPayment.TENDERTYPE_PettyCash);
		//payment.setC_AcctSchema_ID(Env.getContextAsInt(getCtx(),
		//		"$C_AcctSchema_ID"));
		payment.save();
		// //payment.processIt(MPayment.DOCACTION_Complete);
		// //payment.prepareIt();
		payment.completeIt();
		payment.setDocStatus(MPayment.DOCSTATUS_Completed);
		payment.setDescription("Direct Sale Payment Record");
		
		//set order ID
		payment.setC_Order_ID(C_Order_ID);
		payment.save();
		order.setProcessed(true);
		order.setPosted(true);
		order.setC_Payment_ID(payment.get_ID());
		order.save();
	}
}
