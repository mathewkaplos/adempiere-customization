package org.zenith.model.strategy.fromcashier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MOrderLine;
import org.compiere.util.DB;

public class CashierContext
{
	private int _doc_ID = 0;
	private MOrderLine _ol = null;
	// Item types i.e Consultation, Product, Lab(Service) etc
	private String Item_type = null;
	private static String Consultation_item = "CON";
	private static String Lab_item = "LAB";
	private static String Product_item = "PRO";

	private Properties _ctx = null;

	public CashierContext(MOrderLine ol)
	{
		System.out.println(ol.get_ID());
		System.out.println("mathew mathew mathew mathew mathew");
		this._ol = ol;
		this._doc_ID = getTreatmentDoc_ID(ol.getC_BPartner_ID());
		this._ctx = ol.getCtx();

		this.Item_type = ol.getitem_type();
		execute();
	}

	private void execute()
	{
		Strategy s = new Strategy(_ctx, _ol, _doc_ID);
		_ol.setIspaid("Y");
		_ol.setIspay("N");
		_ol.save();
		System.out.println("getitem_type()=" + _ol.getitem_type());
		// s.execute(new Product());
		if (_ol.getitem_type().equalsIgnoreCase(Lab_item))
		{
			s.execute(new Lab());
		} else if (_ol.getitem_type().equalsIgnoreCase(Consultation_item))
		{
			s.execute(new Consultation());
		} else if (_ol.getitem_type().equalsIgnoreCase(Product_item))
		{
			s.execute(new Product());
		}

	}

	private static int getTreatmentDoc_ID(int BP_ID)
	{
		StringBuffer sql2 = new StringBuffer();
		PreparedStatement pstmt2 = null;

		sql2 = sql2.append("SELECT hms_treatment_doc_id from adempiere.hms_treatment_doc where" + " c_bpartner_id = "
				+ BP_ID + " order by created desc ");
		int hms_treatment_doc_id = 0;
		int Tophms_treatment_doc_id = 0;
		try
		{
			pstmt2 = DB.prepareStatement(sql2.toString(), null);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next())
			{
				int topID = rs2.getInt(1);
				if (topID != 0)
				{
					hms_treatment_doc_id = rs2.getInt(1);
					break;
				}
				if (hms_treatment_doc_id == 0)
					Tophms_treatment_doc_id = rs2.getInt(1);
			}
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
		if (hms_treatment_doc_id != 0)
			return hms_treatment_doc_id;
		return Tophms_treatment_doc_id;
	}
}
