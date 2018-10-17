package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;

import zenith.model.MPrescription;
import zenith.model.MPrescriptionline;
import zenith.model.MTreatmentDoc;

public class Prescribe extends SvrProcess
{

	int prescription_ID = 0;
	MPrescriptionline[] lines = null;
	MPrescription prescription = null;
	boolean triageBeforeConsultation = false;
	boolean billingAfterService = false;
	String billRule = "";
	int hms_treatment_doc_ID = 0;
	int M_Pricelist_ID = 0;
	boolean admitted = true;
	// int M_Warehouse_ID = 0;

	@Override
	protected void prepare()
	{
		prescription_ID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		prescription = new MPrescription(getCtx(), prescription_ID, get_TrxName());
		hms_treatment_doc_ID = prescription.gethms_treatment_doc_ID();
		MTreatmentDoc doc = new MTreatmentDoc(getCtx(), hms_treatment_doc_ID, get_TrxName());

		int c_bp_group_id = 0;
		/// String sql = "select c_bp_group_id from hms_treatment_doc where
		/// hms_treatment_doc_ID=" + hms_treatment_doc_ID;
		c_bp_group_id = doc.getC_BP_Group_ID();
		admitted = doc.isadmitted();

		// billing_rule
		String sql2 = "select billing_rule from c_bp_group where c_bp_group_id = " + c_bp_group_id;
		// setup settings
		billRule = DB.getSQLValueString(get_TrxName(), sql2);
		billingAfterService = HmsSetup.billingAfterService(Env.getContextAsInt(getCtx(), "#AD_Client_ID"),
				get_TrxName());
		triageBeforeConsultation = HmsSetup.triageBeforeConsultation(Env.getContextAsInt(getCtx(), "#AD_Client_ID"),
				get_TrxName());
		//

		LoadPrecscription(c_bp_group_id);

		return null;
	}

	private void LoadPrecscription(int c_bp_group_id)
	{
		loadPrescriptionline(prescription.get_ID(), c_bp_group_id);
		isASIMandatory();
		newOrder();// new order
		prescription.setprescribed(true);
		prescription.save();
	}

	private void loadPrescriptionline(int hms_prescription_ID, int c_bp_group_id)
	{
		List<MPrescriptionline> list = new ArrayList<MPrescriptionline>();
		String sql = "SELECT * FROM hms_prescription_line WHERE hms_prescription_ID=? "; // #1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, hms_prescription_ID);
			rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				// System.out.println("uhhjerf wejfserj f frjhrewkfh");
				MPrescriptionline line = new MPrescriptionline(getCtx(), rs, get_TrxName());
				line.sethms_treatment_doc_ID(hms_treatment_doc_ID);
				line.setprescribed(true);
				line.save();
				BigDecimal amount = Env.ZERO;
				int M_Product_ID = line.getM_Product_ID();
				M_Pricelist_ID = getPriceListID(c_bp_group_id);

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
				line.setPrice(amount);
				list.add(line);
			}
		} catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		} finally
		{
			pstmt = null;
		}
		lines = list.toArray(new MPrescriptionline[list.size()]);
	}

	private void newOrder()
	{
		try
		{
			MOrder order = new MOrder(getCtx(), 0, get_TrxName());
			// order.setDescription(name + " Test Fee ");
			order.setC_DocTypeTarget_ID(getPrepayDoctype(getCtx(), get_TrxName()));
			order.setC_BPartner_ID(prescription.getC_BPartner_ID());

			// order.setC_Currency_ID(266);// KES
			// C_PaymentTerm_ID
			order.setC_PaymentTerm_ID(Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID"));
			// M_Warehouse_ID
			/// if (product.getm_warehouse_id() > 0)
			/// order.setM_Warehouse_ID(product.getm_warehouse_id());
			// else
			order.setAD_Org_ID(prescription.getAD_Org_ID());
			order.setM_Warehouse_ID(prescription.getM_Warehouse_ID());
			// M_PriceList_ID
			order.setM_PriceList_ID(M_Pricelist_ID);
			// "#M_PriceList_ID"));

			order.setDocStatus(MOrder.DOCSTATUS_Drafted);
			order.setDocAction(MOrder.DOCACTION_Complete);
			order.setC_DocType_ID(getPrepayDoctype(getCtx(), get_TrxName())); // Pre-pay
																				// Order
			order.setInvoiceRule("I");
			order.setDeliveryRule("R");
			order.setFreightCostRule("I");
			order.setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
			order.setCopyFrom("N");
			// C_ConversionType_ID
			order.setC_ConversionType_ID(Env.getContextAsInt(getCtx(), "#C_ConversionType_ID"));
			// SalesRep_ID
			order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#SalesRep_ID"));
			// order.setTotalLines(bd);
			// order.setGrandTotal(bd);
			order.setPaymentRule("B");
			order.setTreatmentDocID(hms_treatment_doc_ID);

			order.save();

			for (int i = 0; i < lines.length; i++)
			{
				MOrderLine line = new MOrderLine(order);
				line.setM_Product_ID(lines[i].getM_Product_ID());
				line.setM_AttributeSetInstance_ID(lines[i].getM_AttributeSetInstance_ID());
				line.setPrice(lines[i].getPrice());
				line.setPriceActual(lines[i].getPrice());
				line.setPriceEntered(lines[i].getPrice());
				line.setPriceList(lines[i].getPrice());

				line.setQty(lines[i].getdosage());
				line.setLineNetAmt();

				line.setDescription(lines[i].getDescription());
				line.setIspay("Y");
				line.setC_Tax_ID(1000000);
				line.save();
				line.setitem_type("PRO");
				line.setRoundedLineamt(line.getLineNetAmt());

				line.save();
			}

			order.setDescription("Drug Prescription");
			order.save();

			// under some conditions
			if (billingAfterService || billRule.trim().equalsIgnoreCase("PA".trim()) || admitted)
			{
				// order.setC_DocTypeTarget_ID(getSoDoctype(getCtx(),
				// get_TrxName()));
				// order.setC_DocType_ID(getSoDoctype(getCtx(), get_TrxName()));

				// order.save();
				// order.completeIt();
				// order.setSalesRep_ID(Env.getContextAsInt(getCtx(),
				// "#SalesRep_ID"));
				order.setDocStatus("CO");
				order.save();
				updatePatientLoc(order.getC_BPartner_ID(), "Pharmacy");

				MOrderLine[] lines = order.getLines();
				for (int i = 0; i < lines.length; i++)
				{
					MOrderLine line = lines[i];
					line.setIspaid("Y");
					line.save();
				}
				newShipment(order);
			} else
			{
				updatePatientLoc(order.getC_BPartner_ID(), "Cashier");
			}

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private void newShipment(MOrder order)
	{
		MInOut shipment = new MInOut(order, 0, order.getDateAcct());
		shipment.setDateAcct(order.getDateAcct());
		shipment.setInvoiceFirst(true);
		if (!shipment.save(get_TrxName()))
		{
			System.out.println("jklllllllll");
			// m_processMsg = "Could not create Shipment";
			return;
		}
		//
		MOrderLine[] oLines = order.getLines(true, null);
		for (int i = 0; i < oLines.length; i++)
		{
			MOrderLine oLine = oLines[i];
			System.out.println(" oLine.getM_Product_ID()::" + oLine.getM_Product_ID());
			System.out.println("....." + MProduct.get(getCtx(), oLine.getM_Product_ID()).getProductType());
			if (MProduct.get(getCtx(), oLine.getM_Product_ID()).getProductType().equalsIgnoreCase("S"))
				continue;
			//
			MInOutLine ioLine = new MInOutLine(shipment);
			// Qty = Ordered - Delivered
			BigDecimal MovementQty = oLine.getQtyOrdered().subtract(oLine.getQtyDelivered());
			// Location
			int M_Locator_ID = MStorage.getM_Locator_ID(oLine.getM_Warehouse_ID(), oLine.getM_Product_ID(),
					oLine.getM_AttributeSetInstance_ID(), MovementQty, get_TrxName());
			if (M_Locator_ID == 0) // Get default Location
			{
				MWarehouse wh = MWarehouse.get(getCtx(), oLine.getM_Warehouse_ID());
				M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
			}
			//
			ioLine.setOrderLine(oLine, M_Locator_ID, MovementQty);
			ioLine.setQty(MovementQty);
			if (oLine.getQtyEntered().compareTo(oLine.getQtyOrdered()) != 0)
				ioLine.setQtyEntered(MovementQty.multiply(oLine.getQtyEntered()).divide(oLine.getQtyOrdered(), 6,
						BigDecimal.ROUND_HALF_UP));
			if (!ioLine.save(get_TrxName()))
			{
				System.out.println("jklllllllll12121322323");
				// m_processMsg = "Could not create Shipment Line";
				return;
			}
		}
		createInvoice(order, shipment);
	}

	/**
	 * Create Invoice .. Mathew
	 * 
	 * @param dt
	 *            order document type
	 * @param shipment
	 *            optional shipment
	 * @param invoiceDate
	 *            invoice date
	 * @return invoice or null
	 */
	private MInvoice createInvoice(MOrder order, MInOut shipment)
	{
		// MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		MInvoice invoice = new MInvoice(order, 0, order.getDateAcct());
		String m_processMsg;
		if (!invoice.save(get_TrxName()))
		{
			m_processMsg = "Could not create Invoice";
			return null;
		}

		// If we have a Shipment - use that as a base
		if (shipment != null)
		{
			//
			MInOutLine[] sLines = shipment.getLines(false);
			for (int i = 0; i < sLines.length; i++)
			{
				MInOutLine sLine = sLines[i];
				//
				MInvoiceLine iLine = new MInvoiceLine(invoice);
				iLine.setShipLine(sLine);
				iLine.setM_Product_ID(sLine.getM_Product_ID());
				iLine.setC_Tax_ID(1000000);
				// Qty = Delivered
				if (sLine.sameOrderLineUOM())
					iLine.setQtyEntered(sLine.getQtyEntered());
				else
					iLine.setQtyEntered(sLine.getMovementQty());
				iLine.setQtyInvoiced(sLine.getMovementQty());
				if (!iLine.save(get_TrxName()))
				{
					m_processMsg = "Could not create Invoice Line from Shipment Line";
					return null;
				}
				//
				sLine.setIsInvoiced(true);

				if (!sLine.save(get_TrxName()))
				{
					log.warning("Could not update Shipment line: " + sLine);
				}
			}
		}

		// Manually Process Invoice
		invoice.processIt(DocAction.ACTION_Complete);
		invoice.saveEx(get_TrxName());
		order.setC_CashLine_ID(invoice.getC_CashLine_ID());
		order.save();
		if (!MInvoice.DOCSTATUS_Completed.equals(invoice.getDocStatus()))
		{
			m_processMsg = "@C_Invoice_ID@: " + invoice.getProcessMsg();
			return null;
		}
		return invoice;
	} // createInvoice
		//

	// Mandatory Product Attribute Set Instance
	private void isASIMandatory()
	{
		for (MPrescriptionline ol : lines)
		{
			MProduct product = new MProduct(getCtx(), ol.getM_Product_ID(), get_TrxName());
			if (product.getM_AttributeSet_ID() > 0)
			{
				if (product.isASIMandatory(true, ol.getAD_Org_ID()))
				{
					MAttributeSet mas = MAttributeSet.get(getCtx(), product.getM_AttributeSet_ID());
					if (!mas.excludeEntry(
							MColumn.getColumn_ID(MOrderLine.Table_Name, MOrderLine.COLUMNNAME_C_OrderLine_ID), true)
							&& ol.getM_AttributeSetInstance_ID() == 0)
					{
						String m_processMsg = "@LinesWithoutProductAttribute@ (" + ol.getLine() + ")";
						throw new AdempiereException(m_processMsg);
						// throw new AdempiereException("Attribute Set Not
						// set");
					}
				}
			}
		}
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
	private static int getSoDoctype(Properties ctx, String trxName)
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ MOrder.DocSubTypeSO_Standard + "'";
		docTypeID = DB.getSQLValue(trxName, sql);
		return docTypeID;
	}

	// ARI AR Invoice
	private static int getARInvoiceDoctype(Properties ctx, String trxName)
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND name ='"
				+ "AR Invoice" + "'";
		docTypeID = DB.getSQLValue(trxName, sql);
		// 1000002 default for ARI AR Invoice ..don't use this
		if (docTypeID == 0)
			return 1000002;
		return docTypeID;
	}

	private int getPriceListID(int C_BP_Group_ID)
	{
		int M_Pricelist_ID = 0;
		String sql = "SELECT M_Pricelist_ID from adempiere.C_BP_Group WHERE C_BP_Group_ID=" + C_BP_Group_ID;
		M_Pricelist_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_ID;
	}

	// ........................................................

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
			String m_processMsg = "Product/Service: " + MProduct.get(getCtx(), M_Product_ID).getName().toUpperCase()
					+ "  is not in valid price list!. " + failMsg;
			throw new AdempiereException(m_processMsg);
		}
		return price.setScale(2, RoundingMode.CEILING);
	}

	private void updatePatientLoc(int C_BPartner_ID, String location)
	{
		if (C_BPartner_ID != 0)
		{
			MBPartner bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
			bp.setNextLocation(location);
			bp.setCurrentLocation(location);
			bp.save();

		}

	}
}
