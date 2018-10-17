package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_C_Invoice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MLabConsumable;
import zenith.model.MParameter;
import zenith.model.MParameterRange;
import zenith.model.MSpecimen;
import zenith.model.MTest;

public class CopyLabSetup extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		// selectTest();

		// getOrderline();
		// openAmt();
		// createALlocations(1000157);
		// copyInvoice();
		// System.out.println(getARInvoiceDoctype(getCtx()));
		// System.out.println(getPaymentTermID(getCtx(), get_TrxName()));
		System.out.println(getPricelist(get_TrxName()));
		return null;
	}

	// Product Pricelist......
	private static BigDecimal getPricelist(String trxName)
	{
		BigDecimal priceList = Env.ZERO;
		int M_Product_ID = 1001518;
		int M_Pricelist_version_ID = 1000001;
		String sql = "SELECT pricelist FROM adempiere.M_Productprice WHERE M_Product_ID= ? AND  M_Pricelist_version_ID = ?";
		priceList = DB.getSQLValueBD(trxName, sql, M_Product_ID, M_Pricelist_version_ID);
		return priceList;
	}

	// Payment term
	private static int getPaymentTermID(Properties ctx, String trxName)
	{
		int PaymentTermID = 0;
		// PaymentTermID
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT c_paymentterm_id FROM c_paymentterm WHERE AD_Client_ID=" + AD_Client_ID + " AND name ='"
				+ "Immediate" + "'";
		PaymentTermID = DB.getSQLValue(trxName, sql);
		// 1000000 default for immediate ..don't use this
		if (PaymentTermID == 0)
			return 1000000;
		return PaymentTermID;
	}

	// ARI AR Invoice
	private static int getARInvoiceDoctype(Properties ctx)
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND name ='"
				+ "AR Invoice" + "'";
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

	void getOrderline()
	{
		// 1003395V
		MPayment pay = new MPayment(getCtx(), 1000143, get_TrxName());
		System.out.println("is invoice= " + pay.isInvoice());
	}

	private void copyInvoice()
	{
		MInvoice newInv = null;
		final String whereClause = "C_BPartner_ID=? AND docstatus =? AND ispaid=?";
		Query query = MTable.get(getCtx(), X_C_Invoice.Table_ID).createQuery(whereClause, get_TrxName());
		query.setParameters(1000032, "CO", false).setOrderBy("C_Invoice_ID DESC").setOnlyActiveRecords(true);
		List<MInvoice> list = query.list();
		MInvoice[] invoices = list.toArray(new MInvoice[list.size()]);
		System.out.println("Length:" + invoices.length);
		BigDecimal amt = Env.ZERO;
		for (int i = 0; i < invoices.length; i++)
		{
			System.out.println(invoices[i].get_ID());
			MInvoice inv = invoices[i];
			// getSQLValueBD
			String sql = "SELECT invoiceOpen(C_Invoice_ID, 0) FROM C_Invoice WHERE C_Invoice_ID=?";
			BigDecimal bd = DB.getSQLValueBD(get_TrxName(), sql, inv.get_ID());
			amt = amt.add(bd);
			if (newInv == null)
			{
				inv.setProcessed(false);
				inv.setDocStatus(MInvoice.DOCSTATUS_Drafted);
				inv.setDocAction(MInvoice.DOCACTION_Complete);
				inv.save();
				newInv = inv;
			}
			if (newInv != null)
			{
				MInvoiceLine[] lines = inv.getLines();
				for (int j = 0; j < lines.length; j++)
				{
					lines[j].setProcessed(false);
					lines[j].save();
					lines[j].setC_Invoice_ID(newInv.get_ID());
					lines[j].save();
				}
			}
		}
		MInvoiceLine[] lines = newInv.getLines();
		// update business partner
		if (newInv != null)
		{
			MBPartner bp = new MBPartner(getCtx(), newInv.getC_BPartner_ID(), get_TrxName());
			bp.setTotalOpenBalance();
			bp.save();
		}

		System.out.println("Lines: " + lines.length);
		newInv.completeIt();

	}

	private void createALlocations(int C_Payment_ID)
	{
		final String whereClause = "C_BPartner_ID=? AND docstatus =? AND ispaid=?";
		Query query = MTable.get(getCtx(), X_C_Invoice.Table_ID).createQuery(whereClause, get_TrxName());
		query.setParameters(1000032, "CO", false).setOrderBy("C_Invoice_ID").setOnlyActiveRecords(true);
		List<MInvoice> list = query.list();
		MInvoice[] invoices = list.toArray(new MInvoice[list.size()]);
		// System.out.println("Length:" + invoices.length);
		BigDecimal amt = BigDecimal.valueOf(130);
		for (int i = 0; i < invoices.length; i++)
		{
			MInvoice inv = invoices[i];
			// getSQLValueBD
			String sql = "SELECT invoiceOpen(C_Invoice_ID, 0) FROM C_Invoice WHERE C_Invoice_ID=?";
			BigDecimal bd = DB.getSQLValueBD(get_TrxName(), sql, inv.get_ID());
			System.out.println(inv.get_ID() + " , open=" + bd);
			if (amt.compareTo(bd) == 1)
			{
				System.out.println(inv.get_ID() + " takes " + bd);
				MPaymentAllocate payAllocate = new MPaymentAllocate(getCtx(), 0, get_TrxName());
				payAllocate.setC_Payment_ID(C_Payment_ID);
				payAllocate.setC_Invoice_ID(inv.get_ID());

				payAllocate.setInvoiceAmt(bd);
				payAllocate.setAmount(bd);
				payAllocate.setOverUnderAmt(Env.ZERO);

				payAllocate.save();

				amt = amt.subtract(bd);
				System.out.println("------------------------------");
			} else
			{
				System.out.println(inv.get_ID() + " takes " + amt);
				MPaymentAllocate payAllocate = new MPaymentAllocate(getCtx(), 0, get_TrxName());
				payAllocate.setC_Payment_ID(C_Payment_ID);
				payAllocate.setC_Invoice_ID(inv.get_ID());

				payAllocate.setInvoiceAmt(bd);
				payAllocate.setAmount(amt);
				payAllocate.setOverUnderAmt(bd.subtract(amt));

				payAllocate.save();

				break;
			}

		}
	}

	private void openAmt()
	{
		String sql = "SELECT C_BPartner_ID,C_Currency_ID," // 1..2
				+ " invoiceOpen(C_Invoice_ID, ?)" // 3 #1
				// 4..5 #2/3
				+ "FROM C_Invoice WHERE C_Invoice_ID=?"; // #4
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 1000267);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				//
				BigDecimal InvoiceOpen = rs.getBigDecimal(3); // Set Invoice
																// OPen Amount
				System.out.println("InvoiceOpen: " + InvoiceOpen);
				if (InvoiceOpen == null)
					InvoiceOpen = Env.ZERO;
			}
		} catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		} finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	int InitialClientID = 11;

	void selectTest()
	{
		List<String> initialList = new ArrayList<>();
		String sql = "SELECT name FROM hms_test WHERE AD_Client_ID=" + InitialClientID;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String name = rs.getString(1);
				initialList.add(name.trim());
			}
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
		///

		List<String> currentList = new ArrayList<>();

		int currentClientID = Env.getContextAsInt(getCtx(), "#AD_Client_ID");
		System.out.println(currentClientID);
		String sql2 = "SELECT name FROM hms_test WHERE AD_Client_ID=" + currentClientID;
		PreparedStatement pstmt2 = null;
		try
		{
			pstmt2 = DB.prepareStatement(sql2, null);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next())
			{
				String name = rs2.getString(1);
				currentList.add(name.trim());
			}
		} catch (Exception e)
		{
		}
		try
		{
			if (pstmt2 != null)
				pstmt2.close();
			pstmt2 = null;
		} catch (Exception e)
		{
			pstmt2 = null;
		}
		initialList.removeAll(currentList);
		String[] names = initialList.toArray(new String[initialList.size()]);
		for (int i = 0; i < names.length; i++)
		{
			System.out.println(12);
			System.out.println(names[i]);
		}

		// select test which isn't in
		for (int i = 0; i < names.length; i++)
		{
			////////////////////////////////////////// TEST
			String sql3 = "SELECT * FROM hms_test WHERE name='" + names[i] + "'";
			PreparedStatement pstmt3 = null;
			try
			{

				pstmt3 = DB.prepareStatement(sql3, get_TrxName());
				ResultSet rs3 = pstmt3.executeQuery();
				while (rs3.next())
				{
					MTest test = new MTest(getCtx(), rs3, get_TrxName());
					MTest newTest = test.copyTest(test, get_TrxName());
					System.out.println("Test ID: " + test.get_ID());
					////////////////////////////////////////////////////////////// LAB
					////////////////////////////////////////////////////////////// CONSUMABLES
					String sql7 = "SELECT * FROM hms_lab_consumables WHERE hms_test_ID= " + test.get_ID();
					PreparedStatement ps7 = null;
					try
					{
						ps7 = DB.prepareStatement(sql7, get_TrxName());
						ResultSet rs7 = ps7.executeQuery();
						while (rs7.next())
						{
							System.out.println("newTest:" + newTest);
							MLabConsumable lc = new MLabConsumable(getCtx(), rs7, get_TrxName());
							lc.copyConsumable(lc, newTest, get_TrxName());

						}

					} catch (Exception e)
					{

					}
					try
					{
						if (ps7 != null)
							ps7.close();
						ps7 = null;
					} catch (Exception e)
					{
						ps7 = null;
					}

					///////////////////////////////////////////////////////// SPECIMEN
					String sql4 = "SELECT * FROM hms_specimens WHERE hms_test_ID =" + test.get_ID();
					PreparedStatement ps4 = null;
					try
					{
						ps4 = DB.prepareStatement(sql4, get_TrxName());
						ResultSet rs4 = ps4.executeQuery();
						while (rs4.next())
						{
							MSpecimen sp = new MSpecimen(getCtx(), rs4, get_TrxName());
							MSpecimen newsP = sp.copySpecimen(sp, newTest, get_TrxName());

							System.out.println("SP ID:" + sp.get_ID());
							System.out.println();
							System.out.println();
							/////////////////////////////////////////////////////////////////////// PARAMETER
							String sql5 = "SELECT * FROM hms_parameters WHERE hms_specimens_ID =" + sp.get_ID();
							PreparedStatement ps5 = null;
							try
							{
								ps5 = DB.prepareStatement(sql5, get_TrxName());
								ResultSet rs5 = ps5.executeQuery();
								while (rs5.next())
								{
									MParameter para = new MParameter(getCtx(), rs5, get_TrxName());
									MParameter newPara = para.copyParameter(para, newsP, get_TrxName());
									System.out.println("ParaID: " + para.get_ID());
									//////////////////////////////////////////////////////////////// PARAMETER
									//////////////////////////////////////////////////////////////// RANGE
									String sql6 = "SELECT * FROM hms_parameter_range WHERE hms_parameters_ID="
											+ para.get_ID();
									PreparedStatement ps6 = null;
									try
									{
										ps6 = DB.prepareStatement(sql6, get_TrxName());
										ResultSet rs6 = ps6.executeQuery();
										while (rs6.next())
										{
											MParameterRange range = new MParameterRange(getCtx(), rs6, get_TrxName());
											range.copyParameterRange(range, newPara, get_TrxName());
											System.out.println("    " + range.get_ID());
										}

									} catch (Exception e)
									{

									}
								}
							} catch (Exception e)
							{

							}
							try
							{
								if (ps5 != null)
									ps5.close();
								ps5 = null;
							} catch (Exception e)
							{
								ps5 = null;
							}
						}
					} catch (Exception e)
					{

					}

				}
			} catch (Exception e)
			{
			}
			try
			{
				if (pstmt3 != null)
					pstmt3.close();
				pstmt3 = null;
			} catch (Exception e)
			{
				pstmt3 = null;
			}
		}
	}

	public boolean equals(Object anObject)
	{
		return false;
	}

}
