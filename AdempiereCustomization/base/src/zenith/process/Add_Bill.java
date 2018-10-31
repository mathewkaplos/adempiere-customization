package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

public class Add_Bill extends SvrProcess
{

	private int M_Product_ID = 0;
	private BigDecimal Qty = Env.ZERO;
	private int treatID = 0;
	MTreatmentDoc doc = null;
	MProduct product = null;

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
			} else if (name.equals("Qty"))
			{
				Qty = (BigDecimal) para[i].getParameter();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		treatID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		doc = new MTreatmentDoc(getCtx(), treatID, get_TrxName());
		product = new MProduct(getCtx(), M_Product_ID, get_TrxName());
		addBill();
		return null;
	}

	private void addBill()
	{
		add();
	}

	private void add()
	{
		MBilling bill = new MBilling(Env.getCtx(), 0, null);
		bill.sethms_treatment_doc_ID(treatID);
		bill.setC_BPartner_ID(doc.getC_BP_Group_ID());
		bill.setM_Product_ID(M_Product_ID);
		bill.setQty(Qty);
		BigDecimal amt = doIt(M_Product_ID);
		bill.setPrice(amt);
		bill.setPriceActual(amt);
		bill.setLineNetAmt(Qty.multiply(amt));
		bill.setTotalAmt(Qty.multiply(amt));
		bill.setBalance(Qty.multiply(amt));
		// bill.setunits_per_freq(1);
		// bill.setfreq(1);
		// bill.setdays(1);
		// bill.setdosage(1);
		// bill.setDescription("");
		// bill.setdosage_description("");
		if (!product.isService())
		{
			bill.setto_pharm(true);
			bill.setis_prescription(true);
			bill.setbill_group(3);
			doc.setto_pharm(true);
		} else
		{
			bill.setbill_group(4);
		}
		if (doc.getC_BP_Group_ID() == CreateHospitalDefaults.PATIENT_GROUP_ID_CASH)
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		else
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_INSUARANCE);
		bill.setbill_date(Env.getContextAsDate(getCtx(), "Date"));
	
		bill.save();

		doc.save();
	}

	protected BigDecimal doIt(int M_Product_ID)
	{
		// AddDefaultLocation();
		int M_Pricelist_ID = 0;
		BigDecimal amount = Env.ZERO;
		if (doc.getC_BP_Group_ID() == CreateHospitalDefaults.PATIENT_GROUP_ID_CASH)
		{
			M_Pricelist_ID = getPriceListID(CreateHospitalDefaults.PATIENT_GROUP_ID_CASH);
		} else
		{
			M_Pricelist_ID = getPriceListID(CreateHospitalDefaults.PATIENT_GROUP_ID_INSURANCE);
		}
		if (M_Pricelist_ID != 0)
		{
			int M_Pricelist_version_ID = getPriceListVersionID(M_Pricelist_ID);
			if (M_Pricelist_version_ID != 0)
			{
				BigDecimal price = getProductPrice(M_Product_ID, M_Pricelist_version_ID);
				if (price != null && price.compareTo(Env.ZERO) != 0)
					amount = price;
				else
					amount = getAnyPrice(M_Product_ID, "( The Price for the Pricelist Version is ZERO (0). )");

			} else
			{
				amount = getAnyPrice(M_Product_ID, "( The Pricelist has no active Pricelist Version. )");
			}

		} else
		{
			amount = getAnyPrice(M_Product_ID, "( The Patient Group has no pricelist defined. )");
		}

		return amount;
	}

	private int getPriceListID(int C_BP_Group_ID)
	{
		int M_Pricelist_ID = 0;
		String sql = "SELECT M_Pricelist_ID from adempiere.C_BP_Group WHERE C_BP_Group_ID=" + C_BP_Group_ID;
		M_Pricelist_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_ID;
	}

	private int getPriceListVersionID(int M_Pricelist_ID)
	{
		int M_Pricelist_version_ID = 0;
		String sql = " SELECT MAX(M_Pricelist_version_ID) from adempiere.M_Pricelist_version" + " WHERE M_Pricelist_ID="
				+ M_Pricelist_ID + " AND isactive='Y'";
		M_Pricelist_version_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_version_ID;

	}

	// Product Pricelist......
	private BigDecimal getProductPrice(int M_Product_ID, int M_Pricelist_version_ID)
	{
		BigDecimal priceList = Env.ZERO;
		String sql = "SELECT pricelist FROM adempiere.M_Productprice WHERE M_Product_ID= ? AND  M_Pricelist_version_ID = ?";
		priceList = DB.getSQLValueBD(get_TrxName(), sql, M_Product_ID, M_Pricelist_version_ID);
		return priceList;
	}
	/*
	 * get any price of product if: 1)Business partner Group has no Price list.
	 * 2) The product has no price list that belongs to the business partner
	 * group (2nd is not applied anyway) .3) The product price list is zero.
	 * 4)The price list has no active price-list-version
	 */

	private BigDecimal getAnyPrice(int M_Product_ID, String failMsg)
	{
		BigDecimal price = Env.ZERO;
		String sql = "SELECT MAX(pricelist) FROM adempiere.M_Productprice WHERE M_Product_ID=" + M_Product_ID;
		price = DB.getSQLValueBD(get_TrxName(), sql);
		if (price == null || price.compareTo(Env.ZERO) == 0)
		{
			price = Env.ZERO;
		}
		return price.setScale(2, RoundingMode.CEILING);
	}
}