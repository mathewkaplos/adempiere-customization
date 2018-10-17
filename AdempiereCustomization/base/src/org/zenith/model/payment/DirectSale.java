package org.zenith.model.payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.MPeriod;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.X_C_BPartner;
import org.compiere.model.X_C_DocType;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.util.DB;

public class DirectSale {
	/**************************************************************************
	 * Prepare Document
	 * 
	 * @return new status (In Progress or Invalid)
	 */
	/** Process Message */
	private String m_processMsg = null;
	MOrder _order;
	Properties _ctx;
	MPayment _payment;
	String _trx;

	public String prepareIt() {
		m_processMsg = ModelValidationEngine.get().fireDocValidate(_payment, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		// Std Period open?
		if (!MPeriod.isOpen(_ctx, _order.getDateOrdered(), X_C_DocType.DOCBASETYPE_ARReceipt)) {
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		int C_Order_ID = _order.get_ID();
		// Waiting Payment - Need to create Invoice & Shipment
		if (C_Order_ID != 0) { // WebOrder.process

			_payment.setC_Order_ID(C_Order_ID);

			MOrder order = new MOrder(_ctx, C_Order_ID, _trx);
			order.setC_Payment_ID(_payment.get_ID());
			order.save(_trx);
			// Set Invoice
			MInvoice[] invoices = order.getInvoices();
			int length = invoices.length;
			if (length > 0) // get last invoice
				_payment.setC_Invoice_ID(invoices[length - 1].getC_Invoice_ID());
			//
			if (_payment.getC_Invoice_ID() == 0) {
				m_processMsg = "@NotFound@ @C_Invoice_ID@";
				return DocAction.STATUS_Invalid;
			}
		}

		MPaymentAllocate[] pAllocs = MPaymentAllocate.get(_payment);

		// Moses W - KRA 27052009 - should not complete a Payment/Receipt
		// without an allocation line
		int length = pAllocs.length;
		System.out.println("length=" + length);
		// Consistency of Invoice / Document Type and IsReceipt
		if (!verifyDocType(pAllocs)) {
			m_processMsg = "@PaymentDocTypeInvoiceInconsistent@";
			return DocAction.STATUS_Invalid;
		}

		// Payment Allocate is ignored if charge/invoice/order exists in header
		if (!verifyPaymentAllocateVsHeader(pAllocs)) {
			m_processMsg = "@PaymentAllocateIgnored@";
			return DocAction.STATUS_Invalid;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(_payment, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Verify Document Type with Invoice
	 * 
	 * @param pAllocs
	 * @return true if ok
	 */
	private boolean verifyDocType(MPaymentAllocate[] pAllocs) {
		if (_payment.getC_DocType_ID() == 0)
			return false;
		//
		Boolean documentSO = null;
		// Check Invoice First
		if (_payment.getC_Invoice_ID() > 0) {
			String sql = "SELECT idt.IsSOTrx " + "FROM C_Invoice i"
					+ " INNER JOIN C_DocType idt ON (i.C_DocType_ID=idt.C_DocType_ID) " + "WHERE i.C_Invoice_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, _trx);
				pstmt.setInt(1, _payment.getC_Invoice_ID());
				rs = pstmt.executeQuery();
				if (rs.next())
					documentSO = new Boolean("Y".equals(rs.getString(1)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		} // now Order - in Adempiere is allowed to pay PO or receive SO
		else if (_payment.getC_Order_ID() > 0) {
			String sql = "SELECT odt.IsSOTrx " + "FROM C_Order o"
					+ " INNER JOIN C_DocType odt ON (o.C_DocType_ID=odt.C_DocType_ID) " + "WHERE o.C_Order_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, _trx);
				pstmt.setInt(1, _payment.getC_Order_ID());
				rs = pstmt.executeQuery();
				if (rs.next())
					documentSO = new Boolean("Y".equals(rs.getString(1)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		} // now Charge
		else {
			if (pAllocs.length > 0) {
				for (MPaymentAllocate pAlloc : pAllocs) {
					String sql = "SELECT idt.IsSOTrx " + "FROM C_Invoice i"
							+ " INNER JOIN C_DocType idt ON (i.C_DocType_ID=idt.C_DocType_ID) "
							+ "WHERE i.C_Invoice_ID=?";
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						pstmt = DB.prepareStatement(sql, _trx);
						pstmt.setInt(1, pAlloc.getC_Invoice_ID());
						rs = pstmt.executeQuery();
						if (rs.next()) {
							if (documentSO != null) { // already set, compare
														// with current
								if (documentSO.booleanValue() != ("Y".equals(rs.getString(1)))) {
									return false;
								}
							} else {
								documentSO = new Boolean("Y".equals(rs.getString(1)));
							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} finally {
						DB.close(rs, pstmt);
						rs = null;
						pstmt = null;
					}
				}
			}
		}

		// DocumentType
		Boolean paymentSO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT IsSOTrx " + "FROM C_DocType " + "WHERE C_DocType_ID=?";
		try {
			pstmt = DB.prepareStatement(sql, _trx);
			pstmt.setInt(1, _payment.getC_DocType_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				paymentSO = new Boolean("Y".equals(rs.getString(1)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		// OK
		return true;
	} // verifyDocType

	/**
	 * Verify Payment Allocate is ignored (must not exists) if the payment
	 * header has charge/invoice/order
	 * 
	 * @param pAllocs
	 * @return true if ok
	 */
	private boolean verifyPaymentAllocateVsHeader(MPaymentAllocate[] pAllocs) {
		if (pAllocs.length > 0) {
			if (_payment.getC_Invoice_ID() > 0 || _payment.getC_Order_ID() > 0)
				return false;
		}
		return true;
	}
}
