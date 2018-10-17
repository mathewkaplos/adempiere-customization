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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.compiere.util.Util;

//import it.businesslogic.ireport.gui.MessageBox;
import zenith.model.LabDocument;
import zenith.model.MTest;
import zenith.model.X_hms_diagnosisg;

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
public class InfoDiagnosis extends Info implements ActionListener, ChangeListener
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
	public InfoDiagnosis(Frame frame, boolean modal, int WindowNo, String value, boolean multiSelection,
			String whereClause)
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
	public InfoDiagnosis(Frame frame, boolean modal, int WindowNo, int record_id, String value, boolean multiSelection,
			boolean saveResults, String whereClause)
	{
		super(frame, modal, WindowNo, "d", "hms_diagnosis", multiSelection, saveResults, whereClause);
		setTitle("Diagnosis");
		//
		// Modify where clause to fit with column info definitions
		StringBuffer where = new StringBuffer();
		where.append("d.IsActive='Y'");
		// Modify Where Clause
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ") // replace fully qualified name with alias
					.append(Util.replace(whereClause, "hms_diagnosis.", "d."));
		setWhereClause(where.toString());
		//
		statInit();

		// To get the focus after the table update
		m_heldLastFocus = fieldValue;

		p_loadedOK = true;
		executeQuery();
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

		labelName.setText(Msg.getMsg(Env.getCtx(), "Search Diagnosis Name"));
		fieldName.setBackground(AdempierePLAF.getInfoBackground());
		fieldName.addActionListener(this);

	
		

		// Override the isSOTrx context,
		// Vendors only
		// Setup the Grid

		//p_criteriaGrid.add(labelWarehouse, null);
		//p_criteriaGrid.add(fWarehouse_ID, null);

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
		list.add(new Info_Column(" ", "d.hms_diagnosis_ID", IDColumn.class, false));
		list.add(new Info_Column("Diagnosis Name ", "d.diag_name", String.class));

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
		String s_productFrom = "hms_diagnosis d "
				+ " INNER JOIN hms_diagnosissubg s ON (s.hms_diagnosissubg_id =d.hms_diagnosissubg_id) "
				+ " INNER JOIN hms_diagnosisg g ON (g.hms_diagnosisg_id =d.hms_diagnosisg_id)";

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

		orderClause += " d.diag_name";

		return orderClause;
	}

	static int registration_TabID = 1000082; // requesting from reception
	static int treatment_TabID = 1000026;// Doctor Request

	@Override
	protected String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();

		// => ID
		if (isResetRecordID())
			fieldID = 0;
		if (!(fieldID == 0))
		{
			list.add("d.hms_diagnosis_ID = ?");
		}

		// => Name
		if (isValidSQLText(fieldName))
			list.add("UPPER(d.diag_name) LIKE '%" + fieldName.getText().toUpperCase() + "%'");

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
