package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceBatch;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MParameter;
import zenith.model.MRequestConsumable;
import zenith.model.MResult;
import zenith.model.MSpecimenRequest;
import zenith.model.MSpecimenRequestLine;

public class Lab_done extends SvrProcess
{
	MSpecimenRequest specimenRequest = null;
	MOrder order = null;

	@Override
	protected void prepare()
	{
		specimenRequest = new MSpecimenRequest(getCtx(), getRecord_ID(), get_TrxName());
	}

	// M_Product_ID=1001513
	@Override
	protected String doIt() throws Exception
	{
		updateResult();
		saveConsumables(getRecord_ID());
		updateRequest();
		updateReference();
		return null;
	}

	private void updateReference()
	{
		if (specimenRequest.getC_Invoice_ID() != 0)
		{
			MInvoice i = new MInvoice(getCtx(), specimenRequest.getC_Invoice_ID(), get_TrxName());
			i.completeIt();
			i.setDocStatus(MInvoice.DOCSTATUS_Completed);
			i.save();
		}
	}

	private void saveConsumables(int sr_ID)
	{
		String sql = "SELECT * FROM hms_request_consumables WHERE hms_specimen_requests_id =" + sr_ID;

		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				if (order == null)
				{
					int docTypeID = getWarehouseDoctype(getCtx());
					order = new MOrder(getCtx(), 0, get_TrxName());
					order.setC_DocTypeTarget_ID(docTypeID);
					order.setC_BPartner_ID(specimenRequest.getC_BPartner_ID()); // prepay

					order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID")); // GardenAdmin
					order.setC_Currency_ID(Env.getContextAsInt(getCtx(), "#C_Currency_ID"));
					order.setC_PaymentTerm_ID(Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID"));
					order.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));
					order.setM_PriceList_ID(Env.getContextAsInt(getCtx(), "#M_PriceList_ID"));
					order.setDocStatus(MOrder.DOCSTATUS_Drafted);
					order.setDocAction(MOrder.DOCACTION_Complete);
					order.setC_DocType_ID(docTypeID);
					order.setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
					order.setCopyFrom("N");
					order.setC_ConversionType_ID(Env.getContextAsInt(getCtx(), "#C_ConversionType_ID"));
					order.setPaymentRule("B");

					order.setDescription("Lab Consumable...");
					order.saveEx();
					// end of order
				}
				if (order != null)
				{
					MRequestConsumable rc = new MRequestConsumable(getCtx(), rs, get_TrxName());
					MOrderLine line = new MOrderLine(order);
					line.setM_Product_ID(rc.getM_Product_ID());
					line.set_TrxName(get_TrxName());
					line.setQtyEntered(rc.getactual_qty());
					line.setQty(rc.getactual_qty());
					line.setPrice();
					line.setDescription("Lab Consumable line...");

					line.setPrice(line.getPriceList());
					line.saveEx();
				}
			}
			if (order != null)
			{
				order.prepareIt();
				// order.processIt(MOrder.DOCACTION_Complete);
				order.completeIt();
				order.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
				order.setDocStatus(MOrder.DOCSTATUS_Completed);
				order.setDocAction(MOrder.DOCSTATUS_Closed);
				order.setProcessed(true);
				order.save();
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e)
		{
		}

		finally
		{
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			pstmt = null;
		}
	}

	public static int getWarehouseDoctype(Properties ctx)
	{
		String docType = MOrder.DocSubTypeSO_Warehouse;
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND docsubtypeso ='"
				+ docType + "'";
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

	// update request
	// done details
	private void updateRequest()
	{
		Timestamp ff = new Timestamp(System.currentTimeMillis());
		if (specimenRequest != null)
		{
			specimenRequest.setdone(true);
			specimenRequest.setedit(false);
			specimenRequest.setdone_date(ff);
			specimenRequest.setdone_time(ff);
			specimenRequest.saveEx();

		}
	}
	/*
	 * private void updateResult2() { MSpecimenRequestLine[] requestLines =
	 * specimenRequest.getRequestLines123();
	 * System.out.println(requestLines.length); for (int i = 0; i <
	 * requestLines.length; i++) { System.out.println("jhdbsdhh");
	 * MSpecimenRequestLine line = requestLines[i]; MResult[] results =
	 * line.getResullts123(); for (int j = 0; j < results.length; j++) { MResult
	 * result = results[j]; System.out.println("result::ID " + result.get_ID());
	 * } } }
	 */

	private void updateResult()
	{
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		String sql = "SELECT * FROM hms_specimen_r_line WHERE hms_specimen_requests_id= " + specimenRequest.get_ID();
		PreparedStatement ps = null;
		try
		{
			ps = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				MSpecimenRequestLine line = new MSpecimenRequestLine(getCtx(), rs, get_TrxName());
				////////////////////////////////////////////////////////////////////////////
				getReqLine(tt, line);
			}

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		finally
		{
			try
			{
				ps.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			if (ps != null)
				ps = null;
		}
	}

	private void getReqLine(Timestamp time, MSpecimenRequestLine line)
	{
		String sql2 = "SELECT * FROM hms_lab_results WHERE hms_specimen_r_line_id= " + line.get_ID();
		PreparedStatement ps2 = null;
		try
		{
			ps2 = DB.prepareStatement(sql2, get_TrxName());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next())
			{

				MResult result = new MResult(getCtx(), rs2, get_TrxName());
				result.setdone(true);

				result.setdone_date(time);
				result.setdone_time(time);

				result.save();

				MParameter para = new MParameter(getCtx(), result.gethms_parameters_ID(), get_TrxName());
				String r = "";
				// range result
				if (para.ishas_range())
				{
					if (result.getvalue1() != null && result.getvalue1().compareTo(Env.ZERO) != 0)
					{
						BigDecimal d = result.getvalue1().setScale(2, RoundingMode.HALF_UP)
								.stripTrailingZeros();
						String s = result.getrange();
						if (s.contains(") X"))
						{
							if (result.getunits() != null && result.getunits() != "")
								r = d.toPlainString() + " " + s.substring(s.lastIndexOf(") X") + 2).trim() + " "
										+ result.getunits();
							else
								r = d.toPlainString() + " " + s.substring(s.lastIndexOf(") X") + 2).trim();

						} else
						{
							if (result.getunits() != null && result.getunits() != "")
								r = d.toPlainString() + " " + result.getunits();
							else
								r = d.toPlainString();
						}
					} else
					{
						r = "Not Provided";
					}
				} else // non -range result
				{
					if (result.getvalue1() != null && result.getvalue1().compareTo(Env.ZERO) != 0)
					{
						BigDecimal d = result.getvalue1().setScale(2, RoundingMode.HALF_UP)
								.stripTrailingZeros();
						if (result.getunits() != null && result.getunits().trim() != "")
							r = d.toPlainString() + " " + result.getunits();
						else
							r = d.toPlainString();

					} else if (result.getresults() != null)
					{
						if (result.getunits() != null && result.getunits().trim() != "")
						{
							r = result.getresults() + " " + result.getunits();
							;
						} else
							r = result.getresults();
					} else
						r = "Not Provided";
				}
				System.out.println(r);
				result.setfinal_results(r);
				result.save();
			}

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		finally
		{
			try
			{
				ps2.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ps2 != null)
				ps2 = null;
		}
	}
}
