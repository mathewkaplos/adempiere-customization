/*
 * Created by JFormDesigner on Tue Nov 14 12:20:34 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.compiere.apps.form.VDepartmentalRequest;
import org.compiere.util.DB;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MParameter;
import zenith.model.MResult;
import zenith.model.MSpecimenRequest;
import zenith.model.MSpecimenRequestLine;
import zenith.model.MTreatmentDoc;

/**
 * @author Mathew Kipchumba
 */
public class ViewResults extends JDialog
{
	int reqID = 0;

	public ViewResults(Frame owner, int _reqID)
	{
		super(owner, true);
		reqID = _reqID;
		initComponents();
		initData();
	}

	public ViewResults(Dialog owner, int _reqID)
	{
		super(owner, true);
		reqID = _reqID;
		initComponents();
		initData();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void btnDeleteActionPerformed(ActionEvent e)
	{
		int billID = getBillingID(reqID);
		if (billingDeletable(billID) && requestDeletable(reqID))
		// if (1 == 1)
		{
			updateDoc(billID);
			deteteBilling(billID);
			deleteParameters(reqID);
			deleteRequest();
			JOptionPane.showMessageDialog(null, "Request Deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
			VDepartmentalRequest dr = new VDepartmentalRequest();
			dr.loadBPartner();
			this.dispose();
		} else
		{
			JOptionPane.showMessageDialog(null, "Not Deletable!, Either the request is done or it has been paid for.",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void updateDoc(int billID)
	{
		MBilling billing = new MBilling(Env.getCtx(), billID, null);
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), billing.gethms_treatment_doc_ID(), null);
		doc.updateTotalOpenBalance(billing.getLineNetAmt().negate());
		doc.save();

	}

	private int getBillingID(int hms_specimen_requests_id)
	{
		int billID = 0;
		String query = "select hms_billing_id from adempiere.hms_specimen_requests where hms_specimen_requests_id ="
				+ hms_specimen_requests_id;
		PreparedStatement stm0 = null;
		try
		{
			stm0 = DB.prepareStatement(query, null);
			ResultSet rs0 = stm0.executeQuery();
			if (rs0.next())
			{
				billID = rs0.getInt(1);

			}
		} catch (Exception ex)
		{

		} finally
		{
			try
			{
				if (stm0 != null)
					stm0.close();
				stm0 = null;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return billID;
	}

	private boolean billingDeletable(int billID)
	{
		if (billID == 0)
			return true;
		MBilling bill = new MBilling(Env.getCtx(), billID, null);
		return !bill.ispaid();

	}

	private void deteteBilling(int billID)
	{
		MBilling bill = new MBilling(Env.getCtx(), billID, null);
		bill.delete(true);
	}

	private boolean requestDeletable(int reqID)
	{
		if (reqID == 0)
			return true;
		MSpecimenRequest req = new MSpecimenRequest(Env.getCtx(), reqID, null);

		return !req.isdone();
	}

	private void deleteParameters(int hms_specimen_requests_id)
	{
		ArrayList<Integer> requestLineIDs = new ArrayList<>();
		ArrayList<Integer> resultIDs = new ArrayList<>();

		String sql1 = "select hms_specimen_r_line_id from adempiere.hms_specimen_r_line where hms_specimen_requests_id ="
				+ hms_specimen_requests_id;
		PreparedStatement stm1 = null;
		try
		{
			stm1 = DB.prepareStatement(sql1, null);
			ResultSet rs = stm1.executeQuery();
			while (rs.next())
			{
				int lineID = rs.getInt(1);
				requestLineIDs.add(lineID);
				String sql2 = "select hms_lab_results_id from adempiere.hms_lab_results  where hms_specimen_r_line_id ="
						+ lineID;
				PreparedStatement stm2 = null;
				stm2 = DB.prepareStatement(sql2, null);
				ResultSet rs2 = stm2.executeQuery();
				try
				{
					while (rs2.next())
					{
						int hms_lab_results_id = rs2.getInt(1);
						resultIDs.add(hms_lab_results_id);
					}
				} catch (Exception ex)
				{

				} finally
				{
					try
					{
						stm2.close();
						stm2 = null;
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		} catch (Exception ex)
		{

		} finally
		{
			try
			{
				stm1.close();
				stm1 = null;
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		for (Integer resultID : resultIDs)
		{
			MResult r = new MResult(Env.getCtx(), resultID, null);
			r.delete(true);
		}
		for (Integer requestLineID : requestLineIDs)
		{
			MSpecimenRequestLine line = new MSpecimenRequestLine(Env.getCtx(), requestLineID, null);
			line.delete(true);
		}
	}

	private void deleteRequest()
	{
		MSpecimenRequest req = new MSpecimenRequest(Env.getCtx(), reqID, null);
		req.delete(true);
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		buttonBar = new JPanel();
		button1 = new JButton();
		btnDelete = new JButton();
		okButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{

			// JFormDesigner evaluation mark
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new MigLayout(
					"insets dialog,hidemode 3",
					// columns
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- label1 ----
				label1.setText("text");
				label1.setFont(new Font("Tahoma", Font.ITALIC, 14));
				label1.setForeground(Color.blue);
				contentPanel.add(label1, "cell 2 1");

				//======== scrollPane1 ========
				{

					//---- table1 ----
					table1.setBorder(new CompoundBorder(
						new TitledBorder("ZZZZZ"),
						new EmptyBorder(5, 5, 5, 5)));
					scrollPane1.setViewportView(table1);
				}
				contentPanel.add(scrollPane1, "cell 0 5 4 1");
			}
			dialogPane.add(contentPanel, BorderLayout.EAST);

			//======== buttonBar ========
			{
				buttonBar.setMinimumSize(new Dimension(100, 66));
				buttonBar.setOpaque(false);
				buttonBar.setPreferredSize(new Dimension(100, 126));
				buttonBar.setLayout(new MigLayout(
					"insets dialog,alignx right",
					// columns
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[button,fill]" +
					"[button,fill]" +
					"[button,fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- button1 ----
				button1.setText("Save Results");
				buttonBar.add(button1, "cell 1 4");

				//---- btnDelete ----
				btnDelete.setText("Delete");
				btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));
				buttonBar.add(btnDelete, "cell 5 4");

				//---- okButton ----
				okButton.setText("Close");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 9 4");
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.WEST);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JPanel buttonBar;
	private JButton button1;
	private JButton btnDelete;
	private JButton okButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	DefaultListModel<String> listModel = new DefaultListModel<>();
	private boolean isRange = true;

	private void initData()
	{
		Vector<Vector<Object>> data = getResultData();
		Vector<String> columnNames = getResultColumnNames();

		// Set Model
		DefaultTableModel modelO = new DefaultTableModel(data, columnNames);

		table1 = new JTable(modelO) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column)
			{
				switch (column) {
					case 0:
						return String.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					default:
						return String.class;
				}
			}
		};
		// Remove previous listeners2

		// table1.setModel(modelO);
		// setVitalsColumnClass(orderTable);
		table1.setAutoResizeMode(4);

		// set the preferred width of product name column.. mathew 12.10.2017
		try
		{

			int[] columns = { 0, 1 };
			if (isRange)
			{
				int[] newcolumns = { 0, 1, 2 };
				columns = newcolumns;
			}
			for (int i = 0; i < columns.length; i++)
			{
				int column = columns[i];

				TableColumn tableColumn = table1.getColumnModel().getColumn(column);
				int pw = tableColumn.getMinWidth();
				int mw = tableColumn.getMaxWidth();

				for (int row = 0; row < table1.getRowCount(); row++)
				{
					TableCellRenderer cellRenderer = table1.getCellRenderer(row, column);
					Component c = table1.prepareRenderer(cellRenderer, row, column);
					int w = c.getPreferredSize().width + table1.getIntercellSpacing().width;
					pw = Math.max(pw, w);

					// We've exceeded the maximum width, no need to check other
					// rows

					if (pw >= mw)
					{
						pw = mw;
						break;
					}
				}

				tableColumn.setPreferredWidth(pw);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// ---- table1 ----
		table1.setPreferredScrollableViewportSize(new Dimension(500, 150));
		scrollPane1.setViewportView(table1);
	}

	public Vector<Vector<Object>> getResultData()
	{
		String req_name = null;
		boolean rangeUpdated = false;
		PreparedStatement pstmt = null;
		Vector<Vector<Object>> data = new Vector();
		String sql = "SELECT test.name,para.name, result.results, result.range ,para.hms_parameters_id "
				+ " FROM adempiere.hms_specimen_requests req INNER JOIN adempiere.hms_specimen_r_line line "
				+ " ON line.hms_specimen_requests_id = req.hms_specimen_requests_id "
				+ " INNER JOIN adempiere.hms_lab_results result"
				+ " ON result.hms_specimen_requests_id = req.hms_specimen_requests_id"
				+ " INNER JOIN adempiere.hms_test test ON test.hms_test_id = req.hms_test_id "
				+ " INNER JOIN adempiere.hms_specimens specimen ON specimen.hms_test_id = test.hms_test_id "
				+ " INNER JOIN adempiere.hms_parameters para ON para.hms_parameters_id= result.hms_parameters_id "
				+ " WHERE req.hms_specimen_requests_id = " + reqID;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(rs.getString(2));
				line.add(rs.getString(3));

				if (!rangeUpdated)
				{
					updateRange(rs.getInt(5));
					rangeUpdated = true;
				}
				if (isRange)
					line.add(rs.getString(4));
				data.add(line);
				if (req_name == null)
					req_name = rs.getString(1);
			}
			label1.setText(req_name + " Results");
		} catch (Exception localException)
		{
			localException.printStackTrace();
		} finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		return data;
	}

	private void updateRange(int paraID)
	{

		MParameter para = new MParameter(Env.getCtx(), paraID, null);
		isRange = para.ishas_range();
	}

	private Vector<String> getResultColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add("Parameter Name");
		columnNames.add("Result");
		if (isRange)
			columnNames.add("Normal Range");

		return columnNames;
	}

}
