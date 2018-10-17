package zenith.process;

import java.awt.Frame;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.AEnv;
import org.compiere.grid.ed.NewRequest;
import org.compiere.grid.ed.NewVitals;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;
import org.zenith.util.LoadBPartnerID;
import org.zenith.util.Price;

import zenith.model.InsuranceCompany;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

public class BookPatient extends SvrProcess
{
	private Price price = null;

	private int C_BPartner_ID = 0;
	private int M_Product_Category_ID = 0;
	private int M_Product_ID = 0; //
	private String visit_type = "";
	private int hms_insco_ID = 0;
	private boolean refered_in = false;
	private String reffered_from = "";
	private MTreatmentDoc doc = null; //
	//
	private int C_BP_Group_ID = 0;

	private String billRule = "";
	private boolean billingAfterService = false;
	private String patient_priority;
	private int AD_User_ID = 0;

	private BigDecimal copay = Env.ZERO;
	private Timestamp booking_date = null;

	private boolean no_consultation_fee = false;

	private Properties _ctx = null;

	private boolean self_request = false;

	private boolean direct_sale = false;

	int hms_billing_ID = 0;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(BookPatient.class);

	public static BookPatient get(Properties ctx, MTreatmentDoc _doc, int _M_Product_ID)
	{
		BookPatient bookPatient = new BookPatient();
		bookPatient._ctx = ctx;
		bookPatient.doc = _doc;
		if (_M_Product_ID > 0)
			bookPatient.M_Product_ID = _M_Product_ID;
		bookPatient.C_BP_Group_ID = _doc.getC_BP_Group_ID();
		return bookPatient;
	}

	public MBilling getBiling()
	{
		MBilling bill = null;
		String sql = "SELECT * FROM adempiere.hms_billing bill where item_type ='CON' AND  bill.hms_treatment_doc_id="
				+ doc.get_ID();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			rs = stm.executeQuery();
			if (rs.next())
			{
				bill = new MBilling(doc.getCtx(), rs, null);
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				stm.close();
				DB.close(rs);
				stm = null;
				rs = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bill;

	}

	public Properties getCtx()
	{
		return _ctx;
	}

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_Category_ID"))
				M_Product_Category_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Product_ID"))
				M_Product_ID = para[i].getParameterAsInt();
			else if (name.equals("visit_type"))
				visit_type = (String) para[i].getParameter();
			else if (name.equals("C_BP_Group_ID"))
				C_BP_Group_ID = para[i].getParameterAsInt();
			else if (name.equals("hms_insco_ID"))
				hms_insco_ID = para[i].getParameterAsInt();
			else if (name.equals("refered_in"))
				refered_in = para[i].getParameterAsBoolean();
			else if (name.equals("reffered_from"))
				reffered_from = (String) para[i].getParameter();
			else if (name.equals("patient_priority"))
				patient_priority = (String) para[i].getParameter();
			else if (name.equals("AD_User_ID"))
				AD_User_ID = para[i].getParameterAsInt();
			// copay
			else if (name.equals("copay"))
				copay = para[i].getParameterAsBigDecimal();
			else if (name.equals("booking_date"))
				booking_date = para[i].getParameterAsTimestamp();
			else if (name.equals("no_consultation_fee"))
				no_consultation_fee = para[i].getParameterAsBoolean();
			else if (name.equals("self_request"))
				self_request = para[i].getParameterAsBoolean();
			else if (name.equals("direct_sale"))
				direct_sale = para[i].getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		C_BPartner_ID = getRecord_ID();
		_ctx = super.getCtx();
	}

	BigDecimal copayAmt = Env.ZERO;

	private void createCopayBilling()
	{
		if (hms_insco_ID > 0)
		{
			InsuranceCompany insuranceCompany = new InsuranceCompany(super.getCtx(), hms_insco_ID, get_TrxName());
			/// copayAmt = insuranceCompany.getcopay();
			copayAmt = copay;

		}
		if (copayAmt.compareTo(Env.ZERO) == 1)
		{
			// create billing
			BigDecimal amount = copayAmt;
			MBilling billing = new MBilling(super.getCtx(), 0, get_TrxName());
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
			billing.setpay(true);
			billing.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
			billing.setbill_date(booking_date);
			billing.save();
		}

	}

	private MBPartner bp = null;

	@Override
	protected String doIt() throws Exception
	{
		bp = MBPartner.get(this.getCtx(), this.C_BPartner_ID);

		if (self_request)
		{
			return newSelfRequest();
		}
		if (direct_sale)
		{
			return newDirectSale();
		}

		final BigDecimal balance = bp.getHMSBalance().setScale(2, RoundingMode.HALF_UP);
		if (balance.compareTo(Env.ZERO) == 1)
		{
			final int x = yesnocancel(
					bp.getName() + " has balance of: " + balance + ". Do you want to continue with booking?");
			if (x == 0)
			{
				this.book();
			}
		} else
		{
			this.book();
		}

		return bp.getName() + " booked successfuly...";
	}

	private String newSelfRequest()
	{
		newBooking();
		NewRequest req = new NewRequest((Frame) null, doc, bp, get_TrxName());
		AEnv.showCenterScreen(req);
		return null;
	}

	private String newDirectSale()
	{
		newBooking();
		JOptionPane.showMessageDialog(null, "Direct Sale booked successfully...go to ORDERS");
		return null;
	}

	private static int yesnocancel(final String theMessage)
	{
		final int result = JOptionPane.showConfirmDialog(null, theMessage, "Alert", 1);
		return result;
	}

	private void book()
	{
		// check if recently booked... to prevent double booking
		if (bp.bookedRecently())
		{
			final int x = yesnocancel(bp.getName() + " was booked recently. Do you want to continue with booking?");
			if (x == 0)
			{

			} else
			{
				JOptionPane.showMessageDialog(null, "Cancelled..!", "Information Message", 1);
				return;
			}
		}

		// AddDefaultLocation();
		hasLocation();

		// new booking
		newBooking();

		BigDecimal amount = Env.ZERO;
		if (!no_consultation_fee)
		{
			price = new Price(M_Product_ID, doc.get_ID());
			amount = price.getPrice();
		}
		if (!no_consultation_fee)
		{
			createBilling(amount, M_Product_ID, 1);
			createCopayBilling();
		}

		// registration fee
		if (visit_type.equalsIgnoreCase("N"))
			getRegistrationFee();

		if (HmsSetup.getSetup().istriage_before_consoltation())
		{
			final int x = yesnocancel("Booked Successfully.....do you want to enter vital signs? ");
			if (x == 0)
			{
				NewVitals wewVitals = new NewVitals((Frame) null, doc, bp);
				AEnv.showCenterScreen(wewVitals);
			} else
			{

			}
		} else
		{
			JOptionPane.showMessageDialog(null, "Booked Successfully...", "Information Message", 1);
		}
	}

	private MBilling createBilling(BigDecimal amount, int productID, int billGroup)
	{
		MBilling billing = null;
		if (doc != null)
		{
			billing = new MBilling(super.getCtx(), 0, get_TrxName());
			// billing.setC_BPartner_ID(C_BPartner_ID);
			billing.sethms_treatment_doc_ID(doc.get_ID());
			billing.setC_BPartner_ID(C_BPartner_ID);
			billing.setM_Product_ID(productID);
			billing.setPrice(amount);
			billing.setQty(Env.ONE);
			billing.setTotalAmt(amount);

			billing.setPriceActual(amount);
			billing.setLineNetAmt(amount);//////////////////
			billing.setrounded_lineamt(amount);

			billing.setBalance(amount);

			billing.setitem_type("CON");
			billing.setis_consultation(true);

			billing.setbill_date(booking_date);
			billing.setbill_group(billGroup);

			billing.save();
			hms_billing_ID = billing.get_ID();

			setBillingRule(billing);

			setBillMode(billing);
			// update open balance for the instance
			doc.updateTotalOpenBalance(billing.getLineNetAmt());
			doc.save();
		}
		return billing;
	}

	protected void setBillingRule(MBilling billing)
	{
		// billing_rule
		String sql2 = "select billing_rule from c_bp_group where c_bp_group_id = " + doc.getC_BP_Group_ID();
		// setup settings
		billRule = DB.getSQLValueString(get_TrxName(), sql2);
		billingAfterService = HmsSetup.allowBillingAfterService();

		if (billingAfterService || billRule.trim().equalsIgnoreCase("PA".trim()))
		{
			// doc.createInvoice(M_Product_ID, amount);
			billing.setpay_after(true);

			/// billing.setis_consultation(true);
			billing.save();

			doc.setto_doctor(true);
			doc.save();

			updateBpartner();
		} else
		{
			// doc.createOder(M_Product_ID, amount);
			billing.setpay_after(false);
			billing.setpay(true);
			billing.save();
			updateBpartner();
		}
	}

	protected void setBillMode(MBilling billing)
	{
		MBilling bill = billing;
		if (C_BP_Group_ID == CreateHospitalDefaults.PATIENT_GROUP_ID_CASH)
		{
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		} else if (C_BP_Group_ID == CreateHospitalDefaults.PATIENT_GROUP_ID_INSURANCE
				|| C_BP_Group_ID == CreateHospitalDefaults.PATIENT_GROUP_ID_NHIF)
		{
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_INSUARANCE);
		}
		if (billing.getbill_date() == null)
			bill.setbill_date(billing.getCreated());
		bill.save();
	}

	void hasLocation()
	{
		MBPartner bp = new MBPartner(super.getCtx(), C_BPartner_ID, get_TrxName());
		MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs.length < 1)
		{
			String m_processMsg = "Please set the Patient Contact Details first..";
			throw new AdempiereException(m_processMsg);
		}
	}

	private void newBooking()
	{
		doc = new MTreatmentDoc(getCtx(), 0, get_TrxName());
		doc.setC_BPartner_ID(this.C_BPartner_ID);
		this.doc.setC_BP_Group_ID(this.C_BP_Group_ID);
		this.doc.setdoctor_ID(this.AD_User_ID);
		this.doc.save();
		this.doc.setName(LoadBPartnerID.getBPartnerName(this.doc.getC_BPartner_ID()));
		this.doc.setcopay(this.copay);
		this.doc.setbooking_date(this.booking_date);
		this.doc.setvisit_type(this.visit_type);
		this.doc.setstate("d");
		this.doc.setstate2("h");
		final MBPartner bp = new MBPartner(this.getCtx(), this.C_BPartner_ID, (String) null);
		this.doc.sethms_insuredco_ID(bp.getInsuredCompany_ID());
		this.doc.sethms_insco_ID(bp.getInsuranceCompany_ID());
		this.doc.setreferred_in(refered_in);
		this.doc.setreferred_from(reffered_from);
		this.doc.save();

	}

	private void updateBpartner()
	{
		if (C_BPartner_ID != 0 && doc != null)
		{
			MBPartner bp = new MBPartner(super.getCtx(), C_BPartner_ID, get_TrxName());
			bp.setIsBooked(true);
			bp.save();

		}

	}

	private void getRegistrationFee()
	{
		if (HmsSetup.getRegistrationFee().compareTo(Env.ZERO) == 1)
		{
			createNewRegistrationBilling();
		}
	}

	private void createNewRegistrationBilling()
	{
		MBilling billing = createBilling(HmsSetup.getRegistrationFee(), CreateHospitalDefaults.registrationFeeID, 4);
		billing.setis_othercharges(true);
		billing.save();
	}
}
