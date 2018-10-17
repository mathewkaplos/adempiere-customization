package zenith.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.MBed;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

public final class Discharge extends SvrProcess
{
	int treatmentID = 0;
	private String billRule = "";

	@Override
	protected void prepare()
	{
		treatmentID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatmentID, get_TrxName());
		doc.setdischarged(true);
		doc.settreatment_done(true);
		doc.settreat_ref_ID(treatmentID);
		doc.setdischarge_date(new Timestamp(System.currentTimeMillis()));
		doc.settreatment_done_date(new Timestamp(System.currentTimeMillis()));
		doc.save();
		BigDecimal daysAdmitted = Env.ZERO;
		BigDecimal rebateDays = Env.ZERO;
		BigDecimal price = Env.ZERO;
		long x = getDifferenceDays(doc.getcheck_in_date(), doc.getcheck_out_date());
		if (x < 1)
		{
			doc.setdays_admitted("0");
			daysAdmitted = Env.ZERO;
			rebateDays = Env.ONE;
		} else
		{
			doc.setdays_admitted(String.valueOf(x));
			daysAdmitted = BigDecimal.valueOf(x);
			rebateDays = BigDecimal.valueOf(x);
		}
		doc.save();
		if (HmsSetup.getSetup().iscalculate_rebate_automatically())
		{
			doc.setrebate_amount(rebateDays.multiply(HmsSetup.getInpatientNHIFRebateAmt()));
			doc.save();
		}

		MBed bed = new MBed(getCtx(), doc.gethms_ward_bed1_ID(), get_TrxName());
		// billing_rule
		String sql2 = "select billing_rule from c_bp_group where c_bp_group_id = " + doc.getC_BP_Group_ID();
		// setup settings
		billRule = DB.getSQLValueString(get_TrxName(), sql2);
		if (billRule.trim().equalsIgnoreCase("PA".trim()))
		{
			price = bed.getbed_charges_corporate();
		} else
		{
			price = bed.getbed_charges();
		}
		bed.setisoccupied(false);
		bed.save();

		BigDecimal amount = price.multiply(daysAdmitted);
		MBilling billing = new MBilling(getCtx(), 0, get_TrxName());
		billing.sethms_treatment_doc_ID(doc.get_ID());
		billing.setC_BPartner_ID(doc.getC_BPartner_ID());
		billing.setM_Product_ID(CreateHospitalDefaults.getBedChargesID()); // bed
																			// charges
		billing.setPrice(price);
		billing.setQty(daysAdmitted);
		billing.setTotalAmt(amount);

		billing.setPriceActual(price);
		billing.setLineNetAmt(amount);
		billing.setrounded_lineamt(amount);
		billing.setitem_type("BED");
		billing.setis_inpatient_service(true);
		billing.setBalance(amount);

		billing.save();

		updateWard(doc);

		return "PATIENT DISCHARGED SUCCESSFULLY..";
	}

	public static long getDifferenceDays(Date d1, Date d2)
	{
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	void updateWard(MTreatmentDoc doc)
	{

		String sql = "update hms_treatment_doc set hms_ward_bed1_id =NULL, hms_ward1_id =NULL,hms_ward_management1_id=NULL WHERE hms_treatment_doc_id ="
				+ doc.get_ID();
		DB.executeUpdate(sql, get_TrxName());

	}

}
