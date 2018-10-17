/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps.search;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrg;
import org.compiere.swing.CLabel;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

//import it.businesslogic.ireport.gui.MessageBox;
import zenith.model.LabDocument;
import zenith.model.MTest;
import zenith.model.MTreatmentDoc;

/**
 * Search Product and return selection
 *
 * @author Jorg Janke
 * @version $Id: InfoProduct.java,v 1.4 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Bogdan Ioan, SC ARHIPAC SERVICE SRL
 *         <li>FR [ 2012362 ] Info Product: Add Product Category
 * 
 * @author Michael McKay,
 *         <li>ADEMPIERE-72 VLookup and Info Window improvements
 *         https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoMOM extends Info implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2076229793041196087L;

	/**
	 * Standard Constructor
	 * 
	 * @param frame
	 *            frame
	 * @param modal
	 *            modal
	 * @param WindowNo
	 *            window no
	 * 
	 * @param value
	 *            Query Value or Name if enclosed in @
	 * @param multiSelection
	 *            multiple selections
	 * @param whereClause
	 *            where clause
	 */
	@Deprecated
	public InfoMOM(Frame frame, boolean modal, int WindowNo, String value, boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo, 0, value, multiSelection, true, whereClause);
	}

	/**
	 * Standard Constructor
	 * 
	 * @param frame
	 *            frame
	 * @param modal
	 *            modal
	 * @param WindowNo
	 *            window no
	 * 
	 * @param record_id
	 *            The record ID to find
	 * @param value
	 *            Query Value or Name if enclosed in @
	 * @param multiSelection
	 *            multiple selections
	 * @param saveResults
	 *            True if results will be saved, false for info only
	 * @param whereClause
	 *            where clause
	 */

	public InfoMOM(Frame frame, boolean modal, int WindowNo, int record_id, String value, boolean multiSelection,
			boolean saveResults, String whereClause)
	{
		super(frame, modal, WindowNo, "p", "hms_test", multiSelection, saveResults, whereClause);
		setTitle("Departmental Service");

		// Modify where clause to fit with column info definitions
		StringBuffer where = new StringBuffer();
		where.append("p.IsActive='Y'");
		// Modify Where Clause
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ") // replace fully qualified name with alias
					.append(Util.replace(whereClause, "hms_test.", "p."));
		setWhereClause(where.toString());
		//
		statInit();

		// To get the focus after the table update
		m_heldLastFocus = fieldValue;

		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);

	} // InfoProduct

	/** Array of Column Info */
	private static Info_Column[] s_Layout = null;

	//
	private int fieldID = 0;
	private CTextField fieldValue = new CTextField(10);
	private CLabel labelName = new CLabel();
	private CTextField fieldName = new CTextField(10);
	private CLabel labelWarehouse = new CLabel();
	private VLookup fWarehouse_ID;

	private void statInit()
	{

		labelName.setText(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.setBackground(AdempierePLAF.getInfoBackground());
		fieldName.addActionListener(this);

		labelWarehouse.setText("Department");
		fWarehouse_ID = new VLookup("M_Warehouse_ID", false, false, true, MLookupFactory.get(Env.getCtx(), p_WindowNo,
				0, MColumn.getColumn_ID(MOrg.Table_Name, MOrg.COLUMNNAME_AD_Org_ID), DisplayType.TableDir));
		fWarehouse_ID.setBackground(AdempierePLAF.getInfoBackground());
		fWarehouse_ID.addActionListener(this);

		// Override the isSOTrx context,
		// Vendors only
		// Setup the Grid

		p_criteriaGrid.add(labelWarehouse, null);
		p_criteriaGrid.add(fWarehouse_ID, null);

		// Line 2
		p_criteriaGrid.add(labelName, new ALayoutConstraint(1, 0));
		p_criteriaGrid.add(fieldName, null);

		/*
		 * // Line 3 p_criteriaGrid.add(labelUPC, new ALayoutConstraint(2, 0));
		 * p_criteriaGrid.add(fieldUPC, null);
		 * p_criteriaGrid.add(labelProductCategory, null);
		 * p_criteriaGrid.add(fProductCategory_ID, null);
		 */

		// new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNote"),
		// "DocumentNote", String.class)};

	} // statInit

	/**
	 * Query ATP
	 */
	/**
	 * Reset the criteria panel
	 */
	protected void initInfo()
	{
		init();
	}

	/**
	 * Dynamic Init
	 * 
	 */
	private void init()
	{

		//
		prepareTable(getTableLayout(), getFromClause(), getWhereClause(), getOrderClause());
		// prepareTable(getTableLayout(), "M_Product", "M_Product_ID>0", false,
		// "M_Product");

	}

	/**
	 * Get Product Layout - Dynamic
	 *
	 * @return array of Column_Info
	 */
	protected Info_Column[] getTableLayout()
	{

		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "p.hms_test_ID", IDColumn.class, false));
		list.add(new Info_Column("Service Name ", "p.name", String.class));

		//
		s_Layout = new Info_Column[list.size()];
		list.toArray(s_Layout);
		//
		//
		return s_Layout;
	} // getProductLayout// initInfo

	/**************************************************************************
	 * Construct SQL From Clause
	 * 
	 * @return SQL From clause
	 */
	protected String getFromClause()
	{
		/** SQL From */
		String s_productFrom = "hms_test p";

		return s_productFrom;
	}

	/**
	 * Get Order Clause - Dynamic
	 *
	 * @return orderClause "
	 */
	protected String getOrderClause()
	{
		String orderClause = "";

		orderClause += " name";

		return orderClause;
	}

	static int registration_TabID = 1000082; // requesting from reception
	static int treatment_TabID = 1000026;// Doctor Request

	/**
	 * Save Selection Settings for PriceList
	 */
	int hms_treatment_doc_ID = 0;
	int C_BPartner_ID = 0;

	protected void saveSelectionDetail()
	{
		String trxName = Trx.createTrxName();
		int departID = 0;
		if (fWarehouse_ID.getValue() != null)
			departID = ((Integer) fWarehouse_ID.getValue()).intValue();

		// 1000144 --registration
		// 1000025 --treatment window
		// 1000071 -- ward management
		//
		boolean isSelfRequest = false;
		C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID");
		int tabID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, p_TabNo, "_TabInfo_AD_Tab_ID");
		System.out.println("tabID::" + tabID);
		System.out.println("p_TabNo::" + p_TabNo);
		System.out.println("p_WindowNo::" + p_WindowNo);
		if (tabID == 1000025 || tabID == 1000071 || tabID == 0)
		{
			hms_treatment_doc_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, p_TabNo, "hms_treatment_doc_ID");
		} else // if (tabID == 1000144)
		{
			hms_treatment_doc_ID = newTreatmentDoc(C_BPartner_ID, trxName);
			isSelfRequest = true;
		}

		// publish for Callout to read
		ArrayList<Integer> selectedKeys = getSelectedRowKeys();

		Integer[] keys = selectedKeys.toArray(new Integer[selectedKeys.size()]);
		for (int i = 0; i < keys.length; i++)
		{
			System.out.println(keys[i]);

			int testID = keys[i];

			// check if test is request and not done yet
			if (LabDocument.checkTest(hms_treatment_doc_ID, testID))
			{
				javax.swing.JOptionPane.showMessageDialog(null,
						"Service Already Requested and not done.Please Try again.");
				return;
			}
			LabDocument ld = new LabDocument(Env.getCtx());
			ld.newRequest(C_BPartner_ID, testID, hms_treatment_doc_ID, isSelfRequest, departID, null,null, null);
		}
		javax.swing.JOptionPane.showMessageDialog(null, "Request send successfully...");
		System.out.println("Request send successfully...");
		Trx.get(trxName, false).commit();
		Trx.get(trxName, false).close();

	} // saveSelectionDetail

	/**
	 * Create new treatment document
	 */
	private int newTreatmentDoc(int C_BPartner_ID, String trxName)
	{

		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), 0, null);

		doc.setC_BPartner_ID(C_BPartner_ID);
		doc.setlab_self_request(true);
		doc.setC_BP_Group_ID(1000001); // Cash
		doc.save();

		DateFormat tf = new SimpleDateFormat("EEE, MMM d,yyy  HH:mm");
		Date date = new Date(doc.getCreated().getTime());
		String textDate = tf.format(date);

		doc.setName(textDate);
		doc.save();

		return doc.get_ID();
	}

	@Override
	protected String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();

		// => ID
		if (isResetRecordID())
			fieldID = 0;
		if (!(fieldID == 0))
		{
			list.add("p.hms_test_ID = ?");
		}

		// Warehouse - if defined, don't include summary products
		if (fWarehouse_ID.getValue() != null)
		{
			list.add("p.AD_Org_ID= " + ((Integer) fWarehouse_ID.getValue()).intValue());
		} else
		{
			list.add("p.AD_Org_ID= " + 1);
		}

		// => Name
		if (isValidSQLText(fieldName))
			list.add("UPPER(p.Name) LIKE '%" + fieldName.getText().toUpperCase() + "%'");

		StringBuffer sql = new StringBuffer();
		int size = list.size();
		// Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		else if (size > 1)
		{
			boolean AND = true;// checkAND.isSelected();
			sql.append(" AND ");
			if (!AND)
				sql.append("(");
			for (int i = 0; i < size; i++)
			{
				if (i > 0)
					sql.append(AND ? " AND " : " OR ");
				sql.append(list.get(i));
			}
			if (!AND)
				sql.append(")");
		}

		return sql.toString();
	}

	@Override
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Does the parameter panel have outstanding changes that have not been used
	 * in a query?
	 * 
	 * @return true if there are outstanding changes.
	 */

	/**
	 * Record outstanding changes by copying the current value to the oldValue
	 * on all fields
	 */

} // InfoProduct
