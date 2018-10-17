package org.zenith.model.payment;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
//import org.zenith.model.po.GetPatient;

public class Process_Invoices extends SvrProcess {
	int _patientId = 0;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		//_patientId = GetPatient.getPatientID(getRecord_ID());
	}

	@Override
	protected String doIt() throws Exception {
		process_Invoice();
		// NEVER TODO Auto-generated method stub
		return null;
	}

	private void process_Invoice() {
		SecureRandom r = new SecureRandom();
		int x = r.nextInt(10000);
		String formatted = "INV_" + String.format("%05d", x);
		MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
		invoice.setC_BPartner_ID(_patientId);
		// //// i.setC_BPartner_Location_ID(bp.getb);
		invoice.setC_Currency_ID(266);
		// @C_Currency_ID@
		invoice.setC_DocTypeTarget_ID(1000002);
		invoice.setC_DocType_ID(1000002);
		// 0
		// i.setC_Invoice_ID (0);
		invoice.setC_PaymentTerm_ID(100);
		invoice.setDateAcct(new Timestamp(System.currentTimeMillis()));
		// @#Date@
		invoice.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		// @#Date@
		invoice.setDocStatus(MInvoice.DOCSTATUS_Drafted);
		invoice.setDocAction(MInvoice.DOCACTION_Complete);
		// DR
		// invoice.setDocumentNo(formatted);
		// /// i.setGrandTotal(regFee);
		invoice.setIsApproved(false);
		// @IsApproved@
		invoice.setIsDiscountPrinted(false);
		invoice.setIsInDispute(false);
		// N
		invoice.setIsPaid(false);
		invoice.setIsPayScheduleValid(false);
		invoice.setIsPrinted(false);
		invoice.setIsSOTrx(true);
		// @IsSOTrx@
		invoice.setIsSelfService(false);
		invoice.setIsTaxIncluded(false);
		invoice.setIsTransferred(false);
		invoice.setM_PriceList_ID(Env.getContextAsInt(getCtx(),
				"#M_PriceList_ID"));
		invoice.setC_ConversionType_ID(Env.getContextAsInt(getCtx(),
				"#C_ConversionType_ID"));
		invoice.setPaymentRule("P");
		// P
		invoice.setPosted(false);
		// N
		invoice.setProcessed(false);
		invoice.setSendEMail(false);

		invoice.save();

		ArrayList<MInvoice> list = new ArrayList<MInvoice>();
		String sql = "SELECT * FROM C_Invoice  WHERE C_BPartner_ID=? and processed ='N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, _patientId);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MInvoice(getCtx(), rs, get_TrxName()));
		} catch (Exception e) {
			// s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		MInvoice[] retValue = new MInvoice[list.size()];
		list.toArray(retValue);

		for (int i = 0; i < retValue.length; i++) {
			MInvoice inv = retValue[i];
			MInvoiceLine[] lines = inv.getLines();
			for (int j = 0; j < lines.length; j++) {
				MInvoiceLine line = lines[j];
				// line.setInvoice(invoice);
				MInvoiceLine l = new MInvoiceLine(invoice);
				l.setM_Product_ID(line.getM_Product_ID());
				l.setLineNetAmt(line.getLineNetAmt());
				l.setLineTotalAmt(line.getLineTotalAmt());
				l.setPrice(line.getPriceActual());
				l.setPriceLimit(line.getPriceLimit());
				l.setPriceList(line.getPriceList());
				l.setPriceEntered(line.getPriceEntered());
				l.setQty(line.getQtyEntered());
				// line.setitem_type(Hpo.labItemType);
				l.setC_Tax_ID(1000000);
				l.save();
			}
		}
		invoice.processIt(DocAction.ACTION_Complete);
		invoice.completeIt();
		
	}
}
