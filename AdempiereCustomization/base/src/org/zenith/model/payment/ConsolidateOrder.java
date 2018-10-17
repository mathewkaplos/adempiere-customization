package org.zenith.model.payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_C_OrderLine;
import org.compiere.util.DB;
import org.compiere.util.Env;
//import org.zenith.model.po.Hpo;

public class ConsolidateOrder
{

	public static int ConsolidatedOrderID123(Properties ctx, int bp_ID, String trx)
	{
		int OrderID = 0;
		int docTypeID = getPrepayDoctype(ctx);
		MOrder order = new MOrder(ctx, 0, trx);
		// ///order.setDescription(name + " Fee");
		order.setC_DocTypeTarget_ID(docTypeID);
		order.setC_BPartner_ID(bp_ID); // prepay

		order.setSalesRep_ID(Env.getContextAsInt(ctx, "#SalesRep_ID")); // GardenAdmin
		// order.setDeliveryRule(MOrder.DELIVERYRULE_Force);
		// C_Currency_ID
		order.setC_Currency_ID(Env.getContextAsInt(ctx, "#C_Currency_ID"));
		// order.setC_Currency_ID(266); // KES
		// C_PaymentTerm_ID
		order.setC_PaymentTerm_ID(Env.getContextAsInt(ctx, "#C_PaymentTerm_ID"));
		// M_Warehouse_ID
		order.setM_Warehouse_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));
		// ////order.setM_Warehouse_ID(Hpo.LabWarehouseID);
		// M_PriceList_ID
		//// order.setM_PriceList_ID(Env.getContextAsInt(ctx,
		// "#M_PriceList_ID"));

		// order.setDocStatus(MOrder.DOCSTATUS_Drafted);
		order.setDocStatus(MOrder.DOCSTATUS_Drafted);
		order.setDocAction(MOrder.DOCACTION_Complete);
		// order.setC_DocTypeTarget_ID(docTypeID); // Pre-pay Order
		// order.setC_DocType_ID(0);
		// order.setInvoiceRule("I");
		// order.setDeliveryRule("R");
		// order.setFreightCostRule("I");
		order.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
		order.setCopyFrom("N");
		// C_ConversionType_ID
		order.setC_ConversionType_ID(Env.getContextAsInt(ctx, "#C_ConversionType_ID"));
		// order.setTotalLines(bd);
		// order.setGrandTotal(bd);
		order.setPaymentRule("B");

		order.save();
		// end of order

		List<OrderLineSet> orderLineList = new ArrayList<OrderLineSet>();

		PreparedStatement pstmt3 = null;

		String sql = "SELECT c_orderline_id from adempiere.c_orderline "
				+ " WHERE pay ='Y' and paid ='N' and processed='N' and C_BPartner_ID =" + bp_ID;

		try
		{
			pstmt3 = DB.prepareStatement(sql, trx);
			ResultSet rs3 = pstmt3.executeQuery();
			while (rs3.next())
			{
				int c_orderline_id = rs3.getInt(1);
				MOrderLine ol = new MOrderLine(ctx, c_orderline_id, trx);

				OrderLineSet os = new OrderLineSet(ol.get_ID(), ol.getM_Product_ID(), ol.getM_Warehouse_ID(),
						ol.getLineNetAmt(), ol.getPriceActual(), ol.getPriceList(), ol.getPriceEntered(),
						ol.getQtyEntered(), ol.getitem_type(), ol.getDescription(), ol.getM_AttributeSetInstance_ID());

				orderLineList.add(os);
				ol.setIspaid("Y");
				ol.setIspay("N");
				ol.save();
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		} finally
		{

		}
		int wh = 0;
		int count = 0;
		OrderLineSet[] set = new OrderLineSet[orderLineList.size()];
		set = orderLineList.toArray(set);

		for (int i = 0; i < set.length; i++)
		{
			if (wh == 0)
				wh = set[i].getWarehouseID();

			if (wh != 0 && count == 0)
			{
				order.setM_Warehouse_ID(wh);
				order.save();
				count++;
				System.out.println("updated the warehouse..");
			}
			MOrderLine line = new MOrderLine(order);
			line.setc_orderline_id_source(set[i].getOrderline_id_source());
			line.setM_Product_ID(set[i].getProductID());
			line.setM_AttributeSetInstance_ID(set[i].get_M_AttributeSetInstance_ID());
			line.setM_Warehouse_ID(set[i].getWarehouseID());
			line.setLineNetAmt(set[i].getLinenetAmt());
			line.setPrice(set[i].getPrice());
			line.setPriceList(set[i].getPricelist());
			line.setPriceEntered(set[i].getPriceEntered());
			line.setQty(set[i].getQty());
			line.setitem_type(set[i].getItemType());
			line.setIspaid("Y");
			line.setC_Tax_ID(109);// exempt
			line.setDescription(set[i].getDescription());
			line.save();
		}

		// order.completeIt();

		// order.save();

		// String a= order.prepareIt();
		String b = order.completeIt();
		// System.out.println(a);
		System.out.println(b);
		// order.setDocAction(MOrder.DOCACTION_Complete);
		order.setDocStatus(MOrder.DOCSTATUS_WaitingPayment);
		order.setDescription("Order: " + order.getDocumentNo());
		// order.setProcessed(true);
		// order.setPaymentRule("S");
		order.save();
		OrderID = order.get_ID();
		return OrderID;
	}

	public static int getPrepayDoctype(Properties ctx)
	{
		String docType = MOrder.DocSubTypeSO_Prepay;
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_Prepay + "'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				docTypeID = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		}
		return docTypeID;
	}

	public static int copyOrder(Properties ctx, int bpID, String trxName)
	{
		int OrderID = 0;
		int docTypeID = getPrepayDoctype(ctx);
		MOrder order = new MOrder(ctx, 0, trxName);
		order.setC_DocTypeTarget_ID(docTypeID);
		order.setC_BPartner_ID(bpID); // prepay
		order.setSalesRep_ID(Env.getContextAsInt(ctx, "#SalesRep_ID")); // GardenAdmin
		order.setC_Currency_ID(Env.getContextAsInt(ctx, "#C_Currency_ID"));
		order.setC_PaymentTerm_ID(Env.getContextAsInt(ctx, "#C_PaymentTerm_ID"));
		order.setM_Warehouse_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));
		order.setDocStatus(MOrder.DOCSTATUS_Drafted);
		order.setDocAction(MOrder.DOCACTION_Complete);
		order.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
		order.setCopyFrom("N");
		order.setC_ConversionType_ID(Env.getContextAsInt(ctx, "#C_ConversionType_ID"));
		order.setPaymentRule("B");

		order.save();
		// end of order

		final String whereClause = "C_BPartner_ID=? AND pay =? AND paid=? AND processed=?";
		Query query = MTable.get(ctx, X_C_OrderLine.Table_ID).createQuery(whereClause, trxName);
		query.setParameters(bpID, true, false,false).setOnlyActiveRecords(true);
		List<MOrderLine> list = query.list();
		MOrderLine[] lines = list.toArray(new MOrderLine[list.size()]);

		System.out.println("Length:" + lines.length);
		int pharm_id = 0;
		for (int i = 0; i < lines.length; i++)
		{
			lines[i].setC_Order_ID(order.get_ID());
			lines[i].save();
			if (lines[i].getM_Warehouse_ID() == 1000004 && pharm_id == 0)
				pharm_id = 1000004;
		}
		if (pharm_id == 1000004)
		{
			order.setAD_Org_ID(1000008);
			order.setM_Warehouse_ID(1000004);
			order.save();
			
			for (int i = 0; i < lines.length; i++)
			{
				lines[i].setAD_Org_ID(1000008);
				lines[i].setM_Warehouse_ID(1000004);
				lines[i].save();
			}
			
		}
		String b = order.completeIt();
		System.out.println(b);
		order.setDocStatus(MOrder.DOCSTATUS_WaitingPayment);
		order.setDescription("Order: " + order.getDocumentNo());
		order.save();
		OrderID = order.get_ID();
		return OrderID;
	}
}
