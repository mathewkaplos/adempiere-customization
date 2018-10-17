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
import org.compiere.apps.AEnv;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.Util;

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
public class InfoResults extends Info implements ActionListener, ChangeListener
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
	public InfoResults(Frame frame, boolean modal, int WindowNo, String value, boolean multiSelection,
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
	public InfoResults(Frame frame, boolean modal, int WindowNo, int record_id, String value, boolean multiSelection,
			boolean saveResults, String whereClause)
	{
		super(frame, modal, WindowNo, "r", "hms_lab_results", multiSelection, saveResults, whereClause);
		setTitle("Results");
		//

		// Modify where clause to fit with column info definitions
		StringBuffer where = new StringBuffer();
		where.append("r.hms_specimen_requests_id=" + record_id);
		// Modify Where Clause
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ") // replace fully qualified name with alias
					.append(Util.replace(whereClause, "hms_lab_results.", "r."));
		setWhereClause(where.toString());
		//
		// statInit();
		// initInfo();
		executeQuery();
		// To get the focus after the table update

		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);

	} // InfoProduct

	/** Array of Column Info */
	private static Info_Column[] s_Layout = null;

	//
	private int fieldID = 0;

	/**
	 * Static Setup - add fields to parameterPanel
	 */

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
		list.add(new Info_Column(" ", "r.hms_lab_results_ID", IDColumn.class, false));
		list.add(new Info_Column("Parameter ", "para.name", String.class));
		list.add(new Info_Column("Result ", "r.final_results", String.class));
		list.add(new Info_Column("Normal Range ", "r.range", String.class));

		//
		s_Layout = new Info_Column[list.size()];
		list.toArray(s_Layout);
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
		String s_productFrom = "hms_lab_results r";
		String from = "hms_lab_results r "
				+ " LEFT OUTER  JOIN hms_parameters para ON (para.hms_parameters_id = r.hms_parameters_id )"
				+ " LEFT OUTER  JOIN hms_specimen_r_line rline ON (rline.hms_specimen_r_line_ID =r.hms_specimen_r_line_ID )"
				+ " LEFT OUTER  JOIN hms_specimen_requests req ON (req.hms_specimen_requests_id = r.hms_specimen_requests_id) ";
		return from;
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

	// saveSelectionDetail

	@Override
	protected String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();

		// => ID
		if (isResetRecordID())
			fieldID = 0;
		if (!(fieldID == 0))
		{
			list.add("r.hms_lab_results_ID = ?");
		}

		StringBuffer sql = new StringBuffer();
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
