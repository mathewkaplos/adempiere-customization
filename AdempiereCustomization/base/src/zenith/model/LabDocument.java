package zenith.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.zenith.util.HmsSetup;
import org.zenith.util.Price;
import org.zenith.util.lab.SaveParameters;

public class LabDocument implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Properties ctx = Env.getCtx();
	String trxName = null;

	HMSDepartment department = null;
	MTreatmentDoc doc = null;
	Price _price = null;
	MTest test = null;
	SaveParameters saveParameters = null;
	Timestamp billDate = null;

	public LabDocument(Properties _ctx)
	{
		ctx = Env.getCtx();

	}

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(LabDocument.class);

	/**
	 * Create new Test Request
	 * 
	 * @param C_BPartner_ID
	 * @param hms_test_ID
	 * @return
	 */

	public void newRequest(int C_BPartner_ID, int hms_test_ID, int treatID, boolean isSelfRequest, int departID,
			MTreatmentDoc _doc, Timestamp _billDate, String trxName)
	{

		trxName = Trx.createTrxName();
		billDate = _billDate;
		department = new HMSDepartment(Env.getCtx(), departID, trxName);
		if (_doc == null)
		{
			this.doc = new MTreatmentDoc(Env.getCtx(), treatID, trxName);
		} else
		{
			this.doc = _doc;
		}

		test = new MTest(ctx, hms_test_ID, trxName);

		MSpecimenRequest request = new MSpecimenRequest(ctx, 0, trxName);
		// request.setAD_Org_ID(departID);
		request.sethms_department_ID(departID);
		request.setC_BPartner_ID(C_BPartner_ID);
		request.sethms_test_ID(hms_test_ID);
		request.sethms_treatment_doc_ID(treatID);
		request.save();

		request.setadmitted(isAdmitted(doc));

		saveParameters = new SaveParameters(request, trxName);
		saveParameters.save();

		// Department

		String code = department.getshortcode();

		request.setdepartmentCode(code);
		request.save();

		String docCode = doc.getdepartmentCode();

		if (docCode != null && docCode.contains(code))
		{

		} else
		{
			docCode = docCode + "," + code;
		}

		doc.setdepartmentCode(docCode);
		doc.setbooking_date(doc.getCreated());
		doc.setstate2("h");
		doc.save();

		int M_Product_ID = getProductID();
		_price = new Price(M_Product_ID, doc.gethms_treatment_doc_ID());
		BigDecimal price = _price.getPrice();

		//
		String sql = "select c_bp_group_id from hms_treatment_doc where hms_treatment_doc_ID=" + treatID;
		int c_bp_group_id = DB.getSQLValue(trxName, sql);

		// billing_rule
		String sql2 = "select billing_rule from c_bp_group where c_bp_group_id = " + c_bp_group_id;
		// setup settings
		String billRule = DB.getSQLValueString(trxName, sql2);
		boolean billingAfterService = HmsSetup.billingAfterService(Env.getContextAsInt(Env.getCtx(), "#AD_Client_ID"),
				null);

		/*
		 * Billing
		 */
		MBilling billing = new MBilling(ctx, 0, trxName);
		// billing.setC_BPartner_ID(C_BPartner_ID);
		billing.sethms_treatment_doc_ID(doc.get_ID());
		billing.setC_BPartner_ID(C_BPartner_ID);
		billing.setM_Product_ID(M_Product_ID);
		billing.setPrice(price);
		billing.setQty(Env.ONE);
		billing.setTotalAmt(price);

		billing.setPriceActual(price);
		billing.setLineNetAmt(price);
		billing.setrounded_lineamt(price);
		billing.setBalance(price);
		billing.setitem_type("LAB");
		billing.setis_lab(true);
		billing.setbill_group(2);
		// bill date[
		if (billDate != null)
		{
			billing.setCreated(billDate);
			billing.setbill_date(billDate);
		} else
		{
			billing.setbill_date(new Timestamp(System.currentTimeMillis()));
		}
		billing.save();
		// update open balance for the instance
		doc.updateTotalOpenBalance(price);
		doc.save();

		request.sethms_billing_ID(billing.get_ID());
		request.save();
		// Billing

		if (!isSelfRequest)
		{
			if (billingAfterService || billRule.trim().equalsIgnoreCase("PA".trim()))
			{
				billing.setpay_after(true);
				billing.save();
				request.setpay_after(true);
				request.save();

			} else
			{
				billing.setpay_after(false);

				billing.save();
			}
		} else
		{
			billing.setpay_after(false);
			billing.save();
		}
		Trx.get(trxName, false).commit();
		Trx.get(trxName, false).close();

	}

	private int getProductID()
	{
		if (test.getM_Product_ID() != 0)
		{
			return test.getM_Product_ID();
		} else
		{

			return createProduct(department.getM_Product_Category_ID());
		}
	}

	private int createProduct(int M_Product_Category_ID)
	{
		String name = test.getName();
		BigDecimal bd = test.getPrice();
		MProduct product = new MProduct(ctx, 0, trxName);
		product.setName(name.toUpperCase());
		product.setM_Product_Category_ID(M_Product_Category_ID);
		// product.setValue(name.toUpperCase());
		product.setC_UOM_ID(100);
		product.setProductType("S");
		product.setC_TaxCategory_ID(1000000);
		product.save();

		MProductPrice pp = new MProductPrice(ctx, 0, trxName);
		pp.setM_Product_ID(product.get_ID());
		pp.setM_PriceList_Version_ID(1000003);
		pp.setPriceList(bd);
		pp.setPriceStd(bd);
		pp.setPriceLimit(bd);
		pp.save();

		MProductPrice pp2 = new MProductPrice(ctx, 0, trxName);
		pp2.setM_Product_ID(product.get_ID());
		pp2.setM_PriceList_Version_ID(1000004);
		pp2.setPriceList(bd);
		pp2.setPriceStd(bd);
		pp2.setPriceLimit(bd);
		pp2.save();

		test.setM_Product_ID(product.get_ID());
		test.save();
		return product.get_ID();
	}

	private boolean isAdmitted(MTreatmentDoc doc)
	{
		return doc.isadmitted();
	}

	/**
	 * check if lab test requested is not yet requested and not done
	 * 
	 * @param hms_test_ID
	 * @param C_BPartner_ID
	 * @return true if exists
	 */

	public static boolean checkTest(int hms_treatment_doc_id, int hms_test_ID)
	{
		boolean exists = false;
		String sql = "SELECT * FROM hms_specimen_requests WHERE hms_test_ID ='" + hms_test_ID + "' AND"
				+ " hms_treatment_doc_id= '" + hms_treatment_doc_id + "' AND done='N'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				exists = true;
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		} finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		return exists;
	}

}
