package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.HmsInvoice;
import zenith.model.HmsInvoiceLine;
import zenith.model.InsuranceCompany;
import zenith.model.MBilling;
import zenith.model.MInsuranceCompany;
import zenith.model.MInsuredCompany;
import zenith.model.MTreatmentDoc;

public class GenerateHmsInvoice extends SvrProcess
{
	private int docID = 0;
	private int C_BPartner_ID = 0;
	private int C_BP_Group_ID = 0;
	private String invoice_type = "";
	private int hms_insco_ID = 0;
	private int hms_insuredco_ID = 0;
	private Timestamp DateInvoiced = null;
	private BigDecimal rebate_amount = Env.ZERO;
	private BigDecimal copay = Env.ZERO;
	private BigDecimal copay_percent = Env.ZERO;

	MBilling[] bills = null;
	MBPartner bp = null;
	MTreatmentDoc doc = null;
	private BigDecimal prepayAmt = Env.ZERO;

	@Override
	protected void prepare()
	{

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_BPartner_ID"))
				C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BP_Group_ID"))
				C_BP_Group_ID = para[i].getParameterAsInt();
			else if (name.equals("invoice_type"))
				invoice_type = (String) para[i].getParameter();
			else if (name.equals("hms_insco_ID"))
				hms_insco_ID = para[i].getParameterAsInt();
			else if (name.equals("hms_insuredco_ID"))
				hms_insuredco_ID = para[i].getParameterAsInt();
			else if (name.equals("DateInvoiced"))
				DateInvoiced = para[i].getParameterAsTimestamp();

			else if (name.equals("rebate_amount"))
				rebate_amount = para[i].getParameterAsBigDecimal();
			else if (name.equals("copay"))
				copay = para[i].getParameterAsBigDecimal();
			else if (name.equals("copay_percent"))
				copay_percent = para[i].getParameterAsBigDecimal();

		}

		docID = getRecord_ID();
		bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
		doc = new MTreatmentDoc(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception
	{

		processInvoice();
		return null;
	}

	private BigDecimal getCopay(BigDecimal grandTotal)
	{
		if (copay.compareTo(Env.ZERO) == 1)
		{
			return copay;
		}
		if (copay_percent.compareTo(Env.ZERO) == 1)
		{
			return (copay_percent.divide(Env.ONEHUNDRED)).multiply(grandTotal);
		}

		return Env.ZERO;
	}

	private void createCopayBilling(BigDecimal copayAmt)
	{

		// create billing
		MBilling billing = null;
		BigDecimal amount = copayAmt;

		MBilling existingBill = getExistingCopayBill();
		if (existingBill != null)
			billing = existingBill;
		else
			billing = new MBilling(super.getCtx(), 0, get_TrxName());
		// billing.setC_BPartner_ID(C_BPartner_ID);
		billing.sethms_treatment_doc_ID(doc.get_ID());
		billing.setC_BPartner_ID(C_BPartner_ID);
		billing.setM_Product_ID(CreateHospitalDefaults.geteCopayChargeID());
		billing.setPrice(amount);
		billing.setQty(Env.ONE);
		billing.setTotalAmt(amount);

		billing.setPriceActual(amount);
		billing.setLineNetAmt(amount);//////////////////
		billing.setrounded_lineamt(amount);

		billing.setBalance(amount);

		billing.setitem_type("COPAY");
		// billing.setis_consultation(true);

		billing.save();

		billing.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		billing.setbill_date(Env.getContextAsDate(getCtx(), "Date"));
		billing.save();

	}

	private MBilling getExistingCopayBill()
	{

		MBilling bill = null;

		String select = "SELECT * FROM   hms_billing  WHERE  hms_treatment_doc_ID = " + docID + " AND M_Product_ID ="
				+ CreateHospitalDefaults.geteCopayChargeID();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(select, get_TrxName());
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				bill = new MBilling(getCtx(), rs, get_TrxName());

			}
		} catch (Exception e)
		{

		}
		return bill;
	}

	private void processInvoice()
	{
		HmsInvoice inv = null;
		BigDecimal amount = Env.ZERO;
		if (getBills() > 0)
		{
			if (getExistingInvoice() != null)
			{
				inv = getExistingInvoice();
				updateBills(inv);
				inv.sethms_insco_ID(0);

				// inv.setcopay(new BigDecimal(insco.getcopay()));
				// amount = new BigDecimal(insco.getcopay());

				inv.setcompleted(false);
				inv.save();

			} else
			{
				inv = new HmsInvoice(getCtx(), 0, get_TrxName());
				inv.sethms_treatment_doc_ID(docID);
				inv.setC_BPartner_ID(C_BPartner_ID);
				inv.setmember_number(bp.getMemberNumber());

				inv.setName(bp.getName());
				inv.setpatient_no(bp.getDocumentNo());
				inv.setinpatient_no(String.valueOf(bp.getInpatientNo()));
				inv.setid_no(bp.getIDNo());
				inv.save();
			}
			inv.setDateInvoiced(DateInvoiced);

			inv.setinvoice_type(invoice_type);

			if (CreateHospitalDefaults.INVOICE_TYPE_INURANCE.equalsIgnoreCase(invoice_type))
			{
				MInsuredCompany ic = getCompany();
				if (ic == null)
				{
					// do nothing
				} else
				{
					inv.sethms_insco_ID(ic.gethms_insco_ID());
					inv.sethms_insuredco_ID(ic.gethms_insuredco_ID());
				}
				// inv.setcopay(new BigDecimal(insco.getcopay()));
				// amount = new BigDecimal(insco.getcopay());

			}
			if (CreateHospitalDefaults.INVOICE_TYPE_NHIF.equalsIgnoreCase(invoice_type)
					|| CreateHospitalDefaults.NHIF_INSURANCE_ID == hms_insco_ID)
			{
				inv.setis_nhif(true);

				inv.sethms_insco_ID(CreateHospitalDefaults.NHIF_INSURANCE_ID);
				inv.sethms_insuredco_ID(CreateHospitalDefaults.NHIF_INSUREDCO_ID);
			}
			if (CreateHospitalDefaults.INVOICE_TYPE_PATIENT.equalsIgnoreCase(invoice_type))
			{
				// get already paid bills
				// inv.setPaidAmt(getPaidAmountForCashPatient());
			}

			inv.setrebate_amount(rebate_amount);
			inv.setnhif_no(bp.getNHIFNo());

			inv.setcopay(doc.getcopay());

			inv.save();
			BigDecimal grandTotal = Env.ZERO;
			for (int i = 0; i < bills.length; i++)
			{

				HmsInvoiceLine line = new HmsInvoiceLine(getCtx(), 0, get_TrxName());
				line.sethms_invoice_ID(inv.gethms_invoice_ID());
				line.setM_Product_ID(bills[i].getM_Product_ID());
				line.setQty(bills[i].getQty());
				line.setPrice(bills[i].getPrice());
				line.setLineNetAmt(bills[i].getLineNetAmt());
				line.sethms_billing_ID(bills[i].gethms_billing_ID());
				line.setbill_group(bills[i].getbill_group());
				line.setDescription(bills[i].getdosage_description());
				line.save();
				grandTotal = grandTotal.add(bills[i].getLineNetAmt());
			}

			BigDecimal _copay = getCopay(grandTotal);
			prepayAmt = getPrepayAmount();
			inv.setcopay(_copay);
			amount = amount.add(rebate_amount.add(_copay));
			inv.setGrandTotal(grandTotal);
			inv.setPaidAmt(_copay);
			inv.setbill_amount(grandTotal.subtract(amount));
			inv.setBalance(grandTotal.subtract(amount.add(prepayAmt)));
			inv.setprepay_amt(prepayAmt);
			System.out.println("prepayAmt:" + prepayAmt);
			inv.save();
			if (_copay.compareTo(Env.ZERO) == 1)
				createCopayBilling(_copay);

		}
	}

	private MInsuredCompany getCompany()
	{
		MBPartner bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
		int id = bp.getInsuredCompany_ID();
		if (id == 0)
			return null;
		else
			return new MInsuredCompany(getCtx(), id, get_TrxName());
	}

	private HmsInvoice getExistingInvoice()
	{
		HmsInvoice invoice = null;
		String sql = "SELECT * FROM hms_invoice WHERE completed= 'N' AND  hms_treatment_doc_id= " + docID;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			if (rs.next())
			{
				invoice = new HmsInvoice(getCtx(), rs, get_TrxName());

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{

			try
			{
				DB.close(rs);
				stm.close();
				rs = null;
				stm = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return invoice;
	}

	private int getBills()
	{
		List<MBilling> list = new ArrayList<MBilling>();

		String sql = "SELECT * FROM adempiere.hms_billing bill WHERE bill.invoiced='N' AND bill.paid ='N' AND bill.hms_treatment_doc_id ="
				+ docID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MBilling billing = new MBilling(getCtx(), rs, get_TrxName());
				
				// already paid
				list.add(billing);
			}

		} catch (Exception e)
		{

		}
		// bills = new MBilling[list.size()];
		bills = list.toArray(new MBilling[list.size()]);
		return bills.length;
	}

	private BigDecimal getPrepayAmount()
	{
		BigDecimal bd = Env.ZERO;
		String sql = "SELECT * FROM adempiere.hms_billing bill WHERE  bill.hms_treatment_doc_id =" + docID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MBilling billing = new MBilling(getCtx(), rs, get_TrxName());
				if (MProduct.get(getCtx(), billing.getM_Product_ID())
						.getM_Product_Category_ID() == CreateHospitalDefaults.prepaymentsCategoryID)
				{
					if (billing.getM_Product_ID() == CreateHospitalDefaults.prepaymentItemID)
					{
						bd = bd.add(billing.getLineNetAmt());
					}
				}
				// already paid
			}

		} catch (Exception e)
		{

		}
		return bd;
	}

	/**
	 * Update bills when another invoice exists
	 * 
	 * @param _invoice
	 */
	private void updateBills(HmsInvoice _invoice)
	{
		HmsInvoiceLine[] lines = _invoice.getLines();
		for (int i = 0; i < lines.length; i++)
		{
			MBilling bill = new MBilling(getCtx(), lines[i].gethms_billing_ID(), get_TrxName());
			bill.setinvoiced(false);
			bill.setpaid(false);
			bill.setpay(true);
			bill.setBalance(bill.getLineNetAmt());
			bill.setPaidAmt(Env.ZERO);
			bill.setcashAmt(Env.ZERO);
			bill.setmpesaAmt(Env.ZERO);
			/// bill.save();
		}
		// Delete them
		for (int i = 0; i < lines.length; i++)
		{
			HmsInvoiceLine line = lines[i];
			line.delete(true);
		}

	}

	private BigDecimal getPaidAmountForCashPatient()
	{
		BigDecimal paidAmt = Env.ZERO;
		String sql = "SELECT * FROM adempiere.hms_billing bill WHERE bill.hms_treatment_doc_id =" + docID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				MBilling billing = new MBilling(getCtx(), rs, get_TrxName());
				if (MProduct.get(getCtx(), billing.getM_Product_ID())
						.getM_Product_Category_ID() == CreateHospitalDefaults.prepaymentsCategoryID)
				{
					continue;
				}
				paidAmt = paidAmt.add(billing.getPaidAmt());
			}

		} catch (Exception e)
		{

		}
		return paidAmt;
	}
}
