package zenith.process;

import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.process.SvrProcess;
import org.compiere.util.CCache;

public class CreateHospitalDefaults extends SvrProcess
{
	/*
	 * Default Categories IDs
	 */
	public static int otherChargesCategoryID = 200;
	public static int consultationCategoryID = 201;
	public static int prepaymentsCategoryID = 202;
	public static int inpatientChargesCategoryID = 203;

	/*
	 * Default Categories Names
	 */
	private static String otherChargesCategoryName = "OTHER CHARGES";
	private static String consultationCategoryName = "CONSULTATION SERVICES";
	private static String prepaymentsCategoryName = "PREPAYMENTS";
	private static String inpatientChargesCategoryName = "INPATIENT SERVICES"; // inpatientChargesCategoryName

	/*
	 * Default Services IDs
	 */
	public static int otherChargesServiceID = 300;
	public static int consultationServiceID = 301;
	public static int copayAmtServiceID = 302;
	public static int bedChargesServiceID = 303;
	public static int prepaymentItemID = 304;
	public static int registrationFeeID = 305;

	/*
	 * Default sERVICE Names
	 */
	private static String otherChargesServiceName = "OTHER CHARGES";
	private static String consultationServiceName = "CONSULTATION";
	private static String copayAmtServiceName = "INSURANCE COPAY";
	private static String bedChargesServiceName = "BED CHARGES";
	private static String prepaymentItemName = "PREPAYMENT";
	private static String registrationFeeName = "REGISTRATION FEE";
	/*
	 * Warehouse IDs Defaults
	 */
	private static int mainStoreWarehouseID = 1000004;
	private static int pharmacyWarehouseID = 1000007;
	private static int wardWarehouseID = 1000015;
	private static int laboratoryWarehouseID = 1000003;

	/*
	 * Locator IDs Defaults
	 * 
	 */
	private static int mainStoreLocatorID = 1000024;
	private static int pharmacyLocatorID = 1000007;
	private static int wardLocatorID = 1000021;
	private static int laboratoryLocatorID = 1000003;

	/*
	 * Department ID Default
	 */
	public static int receptionDepartmentID = 1000003;
	public static int accountsDepartmentID = 1000004;
	public static int consultationDepartmentID = 1000005;
	public static int triageDepartmentID = 1000006;
	public static int laboratoryDepartmentID = 1000007;
	public static int pharmacyDepartmentID = 1000008;
	public static int inventoryDepartmentID = 1000009;
	public static int inpatientDepartmentID = 1000010;
	public static int x_rayDepartmentID = 1000011;
	public static int ultra_soundDepartmentID = 1000012;
	public static int dentalDepartmentID = 1000013;
	public static int eyeDepartmentID = 1000014;
	public static int theatreDepartmentID = 1000015;
	public static int administrationDepartmentID = 1000016;
	public static int mchDepartmentID = 1000017;
	public static int maternatyDepartmentID = 1000018;
	public static int antenatalDepartmentID = 1000019;
	public static int postnatalDepartmentID = 1000020;
	public static int cccDepartmentID = 1000021;

	/*
	 * Patient Group Default ID
	 */
	public static int PATIENT_GROUP_ID_CASH = 1000001;
	public static int PATIENT_GROUP_ID_INSURANCE = 1000002;
	public static int PATIENT_GROUP_ID_NHIF = 1000003;

	/*
	 * Invoice Types
	 */
	public static String INVOICE_TYPE_INURANCE = "I";
	public static String INVOICE_TYPE_NHIF = "N";
	public static String INVOICE_TYPE_PATIENT = "I";

	/*
	 * Insurance Companies Defaults
	 */
	public static int NHIF_INSURANCE_ID = 1000578;
	public static int NHIF_INSUREDCO_ID = 1001579;
	/*
	 * 
	 * Bill modes
	 */

	public static int BILL_MODE_CASH = 1;
	public static int BILL_MODE_INSUARANCE = 2;

	/**
	 * Bill Change Type
	 */
	public static String BILLING_CHANGE_TYPE_FROM_BEGINNING = "B";
	public static String BILLING_CHANGE_TYPE_NOW_ONWARDS = "N";

	/*
	 * Business Partners Default IDs
	 */
	public static int BUSINESS_PARTNER_DIRECT_SALES_ID = 500;
	public static int BUSINESS_PARTNER_OPENNING_STOCK_ID = 501;

	/*
	 * Business Partners Default Names
	 */
	public static String BUSINESS_PARTNER_DIRECT_SALES_NAME = "DIRECT SALES";
	public static String BUSINESS_PARTNER_OPENNING_STOCK_NAME = "OPENNING STOCK";
	/*
	 * Edit Type
	 */
	public static String EDIT_TYPE_DELETE = "D";
	public static String EDIT_TYPE_SWAP = "S";

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		createDefaulCategories();
		createDefaultServices();
		createDefaultBPartners();
		return null;
	}

	private void createDefaultBPartners()
	{
		if (!MBPartner.exists(BUSINESS_PARTNER_DIRECT_SALES_ID))
		{
			MBPartner bp = new MBPartner(getCtx(), 0, get_TrxName());
			bp.setC_BPartner_ID(BUSINESS_PARTNER_DIRECT_SALES_ID);
			bp.setDocumentNo(BUSINESS_PARTNER_DIRECT_SALES_NAME);
			bp.setName(BUSINESS_PARTNER_DIRECT_SALES_NAME);
			bp.setValue(BUSINESS_PARTNER_DIRECT_SALES_NAME);
			bp.setC_BP_Group_ID(PATIENT_GROUP_ID_CASH);
			bp.save();
		}
		if (!MBPartner.exists(BUSINESS_PARTNER_OPENNING_STOCK_ID))
		{
			MBPartner bp = new MBPartner(getCtx(), 0, get_TrxName());
			bp.setC_BPartner_ID(BUSINESS_PARTNER_OPENNING_STOCK_ID);
			bp.setDocumentNo(BUSINESS_PARTNER_OPENNING_STOCK_NAME);
			bp.setName(BUSINESS_PARTNER_OPENNING_STOCK_NAME);
			bp.setValue(BUSINESS_PARTNER_OPENNING_STOCK_NAME);
			bp.setC_BP_Group_ID(PATIENT_GROUP_ID_CASH);
			bp.setIsCustomer(false);
			bp.setIsVendor(true);
			bp.save();
		}

	}

	private void createDefaulCategories()
	{
		// Other charges category
		if (!MProductCategory.exists(otherChargesCategoryID))
		{
			MProductCategory cat = new MProductCategory(getCtx(), 0, get_TrxName());
			cat.setM_Product_Category_ID(otherChargesCategoryID);
			cat.setName(otherChargesCategoryName);
			cat.setValue(otherChargesCategoryName);
			cat.setAD_Org_ID(0);
			cat.save();
		}
		System.out.println("consultationCategoryID::exists -" + MProductCategory.exists(consultationCategoryID));
		// Consultation category
		if (!MProductCategory.exists(consultationCategoryID))
		{
			MProductCategory cat = new MProductCategory(getCtx(), 0, get_TrxName());
			cat.setM_Product_Category_ID(consultationCategoryID);
			cat.setName(consultationCategoryName);
			cat.setValue(consultationCategoryName);
			cat.setAD_Org_ID(consultationDepartmentID);
			cat.save();
		}
		// Insurance Charges category
		if (!MProductCategory.exists(prepaymentsCategoryID))
		{
			MProductCategory cat = new MProductCategory(getCtx(), 0, get_TrxName());
			cat.setM_Product_Category_ID(prepaymentsCategoryID);
			cat.setName(prepaymentsCategoryName);
			cat.setValue(prepaymentsCategoryName);
			cat.setAD_Org_ID(0);
			cat.save();
		}

		// In patient Charges category
		if (!MProductCategory.exists(inpatientChargesCategoryID))
		{
			MProductCategory cat = new MProductCategory(getCtx(), 0, get_TrxName());
			cat.setM_Product_Category_ID(inpatientChargesCategoryID);
			cat.setName(inpatientChargesCategoryName);
			cat.setValue(inpatientChargesCategoryName);
			cat.setAD_Org_ID(inpatientDepartmentID);
			cat.save();
		}
	}

	private void createDefaultServices()
	{
		// Other Charges Service
		if (!MProduct.exists(otherChargesServiceID))
		{
			MProduct product = new MProduct(getCtx(), 0, get_TrxName());
			product.setM_Product_ID(otherChargesServiceID);
			product.setM_Product_Category_ID(otherChargesCategoryID);
			product.setName(otherChargesServiceName);
			product.setValue(otherChargesServiceName);
			product.setAD_Org_ID(0);
			product.setC_TaxCategory_ID(1000000);
			product.setC_UOM_ID(100);
			product.setProductType("S");
			product.save();
		}
		// Consultation Service
		if (!MProduct.exists(consultationServiceID))
		{
			MProduct product = new MProduct(getCtx(), 0, get_TrxName());
			product.setM_Product_ID(consultationServiceID);
			product.setM_Product_Category_ID(consultationCategoryID);
			product.setName(consultationServiceName);
			product.setValue(consultationServiceName);
			product.setAD_Org_ID(consultationDepartmentID);
			product.setC_TaxCategory_ID(1000000);
			product.setC_UOM_ID(100);
			product.setProductType("S");
			product.save();
		}
		// Co-pay Amount Service
		if (!MProduct.exists(copayAmtServiceID))
		{
			MProduct product = new MProduct(getCtx(), 0, get_TrxName());
			product.setM_Product_ID(copayAmtServiceID);
			product.setM_Product_Category_ID(prepaymentsCategoryID);
			product.setName(copayAmtServiceName);
			product.setValue(copayAmtServiceName);
			product.setAD_Org_ID(0);
			product.setC_TaxCategory_ID(1000000);
			product.setC_UOM_ID(100);
			product.setProductType("S");
			product.save();
		}
		// bed charge Service
		if (!MProduct.exists(bedChargesServiceID))
		{
			MProduct product = new MProduct(getCtx(), 0, get_TrxName());
			product.setM_Product_ID(bedChargesServiceID);
			product.setM_Product_Category_ID(inpatientChargesCategoryID);
			product.setName(bedChargesServiceName);
			product.setValue(bedChargesServiceName);
			product.setAD_Org_ID(inpatientDepartmentID);
			product.setC_TaxCategory_ID(1000000);
			product.setC_UOM_ID(100);
			product.setProductType("S");
			product.save();
		}
		// Co-pay Amount Service
		if (!MProduct.exists(prepaymentItemID))
		{
			MProduct product = new MProduct(getCtx(), 0, get_TrxName());
			product.setM_Product_ID(prepaymentItemID);
			product.setM_Product_Category_ID(prepaymentsCategoryID);
			product.setName(prepaymentItemName);
			product.setValue(prepaymentItemName);
			product.setAD_Org_ID(0);
			product.setC_TaxCategory_ID(1000000);
			product.setC_UOM_ID(100);
			product.setProductType("S");
			product.save();
		}
		// Registration Fee Service
		if (!MProduct.exists(registrationFeeID))
		{
			MProduct product = new MProduct(getCtx(), 0, get_TrxName());
			product.setM_Product_ID(registrationFeeID);
			product.setM_Product_Category_ID(otherChargesCategoryID);
			product.setName(registrationFeeName);
			product.setValue(registrationFeeName);
			product.setAD_Org_ID(0);
			product.setC_TaxCategory_ID(1000000);
			product.setC_UOM_ID(100);
			product.setProductType("S");
			product.save();
		}
		

	}

	/*
	 * 
	 * Locator IDs
	 */
	static public int getPharmacyLocatorID()
	{
		return pharmacyLocatorID;
	}

	public static int getMainStoreLocatorID()
	{
		return mainStoreLocatorID;
	}

	public static int getLaboratoryLocatorID()
	{
		return laboratoryLocatorID;
	}

	public static int getWardLocatorID()
	{
		return wardLocatorID;
	}

	// TODO Warehouse IDs

	/*
	 * get bed charges ID
	 */
	public static int getBedChargesID()
	{
		return bedChargesServiceID;
	}

	/*
	 * get copay ID
	 */
	public static int geteCopayChargeID()
	{
		return copayAmtServiceID;
	}

	/*
	 * get Other charges ID
	 */
	public static int getOtherChargesID()
	{
		return otherChargesServiceID;
	}

	/*
	 * get Consultation Service ID
	 */
	public static int getConsultationID()
	{
		return consultationServiceID;
	}
}
