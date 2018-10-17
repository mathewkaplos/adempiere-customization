package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MInOut;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.HMSPharmacy;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

public class PharmacyProcess extends SvrProcess
{
	int pharmID = 0;
	int C_BPartner_ID = 0;
	int treatID = 0;
	MBilling[] bills = null;
	int C_BP_Group_ID = 0;
	boolean triageBeforeConsultation = false;
	boolean billingAfterService = false;
	String billRule = "";
	int M_Pricelist_ID = 0;
	boolean admitted = true;

	@Override
	protected void prepare()
	{

		pharmID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		start();
		return null;
	}

	void start()
	{
		HMSPharmacy pharm = new HMSPharmacy(getCtx(), pharmID, get_TrxName());
		treatID = pharm.gethms_treatment_doc_ID();
		C_BPartner_ID = pharm.getC_BPartner_ID();
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), treatID, get_TrxName());
		C_BP_Group_ID = doc.getC_BP_Group_ID();

		admitted = doc.isadmitted();

		// billing_rule
		String sql2 = "select billing_rule from c_bp_group where c_bp_group_id = " + C_BP_Group_ID;
		// setup settings
		billRule = DB.getSQLValueString(get_TrxName(), sql2);
		billingAfterService = HmsSetup.billingAfterService(Env.getContextAsInt(getCtx(), "#AD_Client_ID"),
				get_TrxName());
		triageBeforeConsultation = HmsSetup.triageBeforeConsultation(Env.getContextAsInt(getCtx(), "#AD_Client_ID"),
				get_TrxName());

		bills = getLines();
		if (bills.length > 0)
		{
			// int[] orderIDs = null;
			for (int i = 0; i < bills.length; i++)
			{
				//// getUniqueOrders();
			}
		}
		MOrder order = newOrder(bills);
		completeShipment(order.get_ID());

	}

	MBilling[] getLines()
	{
		String select = "SELECT * FROM   hms_billing  WHERE hms_treatment_doc_id =" + treatID;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MBilling> list = new ArrayList<MBilling>();
		try
		{
			pstmt = DB.prepareStatement(select, get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				MBilling bill = new MBilling(getCtx(), rs, get_TrxName());
				list.add(bill);
			}
		} catch (Exception e)
		{

		}

		MBilling[] billArray = new MBilling[list.size()];
		return list.toArray(billArray);

	}

	private void getUniqueOrders()
	{
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < bills.length; i++)
		{
			if (!set.contains(bills[i].getC_Order_ID()))
			{
				set.add(bills[i].getC_Order_ID());
			}
		}

		Integer[] arr = set.toArray(new Integer[set.size()]);
		for (int i = 0; i < arr.length; i++)
		{
			completeShipment(arr[i]);
		}

		/**
		 * Iterator<Integer> itr = set.iterator(); while (itr.hasNext()) { if
		 * (itr.next() > 0) { completeShipment(itr.next()); } }
		 **/
	}

	private void completeShipment(int C_Order_ID)
	{
		String sql = "SELECT * FROM M_InOut WHERE C_Order_ID =" + C_Order_ID;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MInOut io = new MInOut(getCtx(), rs, get_TrxName());
				String ms = io.completeIt();
				io.setDocStatus("CO");
				io.save();
				System.out.println("Shipment process message:" + ms);
			}
		} catch (Exception e)
		{

		}
	}

	private MOrder newOrder(MBilling[] set)
	{

		int docTypeID = getSoDoctype();
		MOrder order = new MOrder(getCtx(), 0, get_TrxName());
		order.setC_DocTypeTarget_ID(docTypeID);
		order.setC_DocType_ID(docTypeID);
		order.setC_BPartner_ID(set[0].getC_BPartner_ID()); // prepay
		order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#SalesRep_ID")); // GardenAdmin
		order.setC_Currency_ID(Env.getContextAsInt(getCtx(), "#C_Currency_ID"));
		order.setC_PaymentTerm_ID(Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID"));
		// order.setM_Warehouse_ID(Env.getContextAsInt(getCtx(),
		// "#M_Warehouse_ID"));
		order.setDocStatus(MOrder.DOCSTATUS_Drafted);
		order.setDocAction(MOrder.DOCACTION_Complete);
		order.setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
		order.setCopyFrom("N");
		order.setC_ConversionType_ID(Env.getContextAsInt(getCtx(), "#C_ConversionType_ID"));
		order.setPaymentRule("B");

		order.setAD_Org_ID(1000008);
		order.setM_Warehouse_ID(1000004);

		order.save();

		// lines
		for (int i = 0; i < set.length; i++)
		{
			MOrderLine line = new MOrderLine(order);
			line.setM_Product_ID(set[i].getM_Product_ID());
			line.setM_Warehouse_ID(set[i].getM_Warehouse_ID());
			line.setLineNetAmt(set[i].getLineNetAmt());
			line.setPrice(set[i].getPrice());
			line.setPriceList(set[i].getPrice());
			line.setPriceEntered(set[i].getPriceActual());
			line.setQty(set[i].getQty());
			line.setitem_type(set[i].getitem_type());
			line.setIspaid("Y");
			line.setC_Tax_ID(109);// exempt
			line.setDescription(set[i].getDescription());

			set[i].setAD_Org_ID(1000008);
			set[i].setM_Warehouse_ID(1000004);
			line.save();

			MBilling bill = set[i];
			bill.setissued(true);
			bill.setC_Order_ID(order.get_ID());
			bill.save();
		}

		String ms = order.completeIt();
		System.out.println(ms);
		order.setDocStatus(MOrder.DOCSTATUS_Completed);
		order.setDescription("Order: " + order.getDocumentNo());
		order.save();
		return order;

	}

	// DocSubTypeSO_Prepay ID
	private static int getPrepayDoctype(Properties ctx, String trxName)
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_Prepay + "'";
		docTypeID = DB.getSQLValue(trxName, sql);
		return docTypeID;
	}

	// DocSubTypeSO_Standard ID
	private int getSoDoctype()
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(getCtx(), "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_Standard + "'";
		docTypeID = DB.getSQLValue(get_TrxName(), sql);
		return docTypeID;
	}
}
