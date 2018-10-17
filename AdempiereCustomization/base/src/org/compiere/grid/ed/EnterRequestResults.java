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
import org.compiere.apps.form.VDepartmentalResults;
import org.compiere.util.DB;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MParameter;
import zenith.model.MResult;
import zenith.model.MSpecimenRequest;
import zenith.model.MSpecimenRequestLine;
import zenith.util.DateUtil;

/**
 * @author Mathew Kipchumba
 */
public class EnterRequestResults extends JDialog
{
	int reqID = 0;

	public EnterRequestResults(Frame owner, int _reqID)
	{
		super(owner, true);
		reqID = _reqID;
		initComponents();
		initData();
	}

	public EnterRequestResults(Dialog owner, int _reqID)
	{
		super(owner, true);
		reqID = _reqID;
		initComponents();
		initData();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		// this.dispose();
		save();

		JOptionPane.showMessageDialog(null, "Saved Successfully...", "Information", JOptionPane.INFORMATION_MESSAGE);
		VDepartmentalResults dr = new VDepartmentalResults();
		dr.loadBPartner();
	}

	String final_Results = "";

	private void save()
	{
		try
		{
			int count = table1.getRowCount();
			int col = table1.getColumnCount();
			for (int i = 0; i < count; i++)
			{
				int resultID = (int) table1.getValueAt(i, 0);
				String results = "";
				String range = "";
				if (table1.getValueAt(i, 2) != null)
				{
					results = table1.getValueAt(i, 2).toString();
				}
				if (isRange)
				{
					if (table1.getValueAt(i, 3) != null)
					{
						range = table1.getValueAt(i, 3).toString();
					}
				}
				MResult r = new MResult(Env.getCtx(), resultID, null);
				r.setrange(range);
				r.setresults(results);
				r.save();
				MParameter para = new MParameter(Env.getCtx(), r.gethms_parameters_ID(), null);
				results = para.getName() + ":" + results;
				if (i == 0)
					final_Results = results;
				else
					final_Results = final_Results + "  " + System.lineSeparator() + results;
			}

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void btnDeleteActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void doneButtonActionPerformed(ActionEvent e)
	{
		done();
		JOptionPane.showMessageDialog(null, "Done! Results Send to Doctor...", "Information",
				JOptionPane.INFORMATION_MESSAGE);
		VDepartmentalResults dr = new VDepartmentalResults();
		dr.loadBPartner();
		this.dispose();
	}

	private void done()
	{
		save();

		MSpecimenRequest req = new MSpecimenRequest(Env.getCtx(), reqID, null);

		req.setdone(true);
		req.setdone_date(DateUtil.newTimestamp());
		req.setdone_time(DateUtil.newTimestamp());
		req.setpositive(positiveCheckBox.isSelected());
		req.setresults(final_Results);
		req.save();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Math Ew
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		buttonBar = new JPanel();
		positiveCheckBox = new JCheckBox();
		btnDelete = new JButton();
		okButton = new JButton();
		doneButton = new JButton();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{

			// JFormDesigner evaluation mark
			dialogPane.setBorder(
					new javax.swing.border.CompoundBorder(
							new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
									"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
									javax.swing.border.TitledBorder.BOTTOM,
									new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red),
							dialogPane.getBorder()));
			dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e)
				{
					if ("border".equals(e.getPropertyName()))
						throw new RuntimeException();
				}
			});

			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{
				contentPanel.setLayout(new MigLayout("insets dialog,hidemode 3",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

				// ---- label1 ----
				label1.setText("text");
				label1.setFont(new Font("Tahoma", Font.ITALIC, 14));
				label1.setForeground(Color.blue);
				contentPanel.add(label1, "cell 2 1");

				// ======== scrollPane1 ========
				{

					// ---- table1 ----
					table1.setBorder(new CompoundBorder(new TitledBorder("ZZZZZ"), new EmptyBorder(5, 5, 5, 5)));
					table1.setPreferredScrollableViewportSize(new Dimension(600, 400));
					scrollPane1.setViewportView(table1);
				}
				contentPanel.add(scrollPane1, "cell 0 5 6 1");
			}
			dialogPane.add(contentPanel, BorderLayout.WEST);

			// ======== buttonBar ========
			{
				buttonBar.setMinimumSize(new Dimension(100, 66));
				buttonBar.setOpaque(false);
				buttonBar.setPreferredSize(new Dimension(100, 126));
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[button,fill]" + "[button,fill]" + "[button,fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

				// ---- positiveCheckBox ----
				positiveCheckBox.setText("Positive");
				buttonBar.add(positiveCheckBox, "cell 1 1");

				// ---- btnDelete ----
				btnDelete.setText("CLOSE");
				btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));
				buttonBar.add(btnDelete, "cell 1 5");

				// ---- okButton ----
				okButton.setText("SAVE");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 9 5");

				// ---- doneButton ----
				doneButton.setText("DONE");
				doneButton.addActionListener(e -> doneButtonActionPerformed(e));
				buttonBar.add(doneButton, "cell 13 5");
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
	// Generated using JFormDesigner Evaluation license - Math Ew
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JPanel buttonBar;
	private JCheckBox positiveCheckBox;
	private JButton btnDelete;
	private JButton okButton;
	private JButton doneButton;
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
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
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
		table1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table1.setRowHeight(20);
		MSpecimenRequest req = new MSpecimenRequest(Env.getCtx(), reqID, null);
		positiveCheckBox.setSelected(req.ispositive());

		DefaultCellEditor singleClick = new DefaultCellEditor(new JTextField());
		for (int i = 0; i < table1.getColumnCount(); i++)
		{
			table1.setDefaultEditor(table1.getColumnClass(i), singleClick);
		}

		// set the preferred width of product name column.. mathew 12.10.2017
		try
		{
			TableColumn tableColumnID = table1.getColumnModel().getColumn(0);
			tableColumnID.setPreferredWidth(0);
			tableColumnID.setWidth(0);
			tableColumnID.setMaxWidth(0);

			// name
			TableColumn tableColumnName = table1.getColumnModel().getColumn(1);
			tableColumnName.setPreferredWidth(100);
			// result
			TableColumn tableColumnResult = table1.getColumnModel().getColumn(2);
			tableColumnResult.setPreferredWidth(200);
			// range
			if (isRange)
			{
				TableColumn tableColumnRange = table1.getColumnModel().getColumn(3);
				tableColumnRange.setPreferredWidth(200);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// ---- table1 ----
		table1.setPreferredScrollableViewportSize(new Dimension(700, 250));
		scrollPane1.setViewportView(table1);
	}

	public Vector<Vector<Object>> getResultData()
	{
		String req_name = null;
		boolean rangeUpdated = false;
		PreparedStatement pstmt = null;
		Vector<Vector<Object>> data = new Vector();
		String sql = "SELECT result.hms_lab_results_ID, test.name,para.name, result.results, result.range ,para.hms_parameters_id "
				+ " FROM adempiere.hms_specimen_requests req INNER JOIN adempiere.hms_specimen_r_line line "
				+ " ON line.hms_specimen_requests_id = req.hms_specimen_requests_id "
				+ " INNER JOIN adempiere.hms_lab_results result"
				+ " ON result.hms_specimen_requests_id = req.hms_specimen_requests_id"
				+ " INNER JOIN adempiere.hms_test test ON test.hms_test_id = req.hms_test_id "
				+ " INNER JOIN adempiere.hms_specimens specimen ON specimen.hms_test_id = test.hms_test_id "
				+ " INNER JOIN adempiere.hms_parameters para ON para.hms_parameters_id= result.hms_parameters_id "
				+ " WHERE req.hms_specimen_requests_id = " + reqID + " ORDER BY para.created";
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(rs.getInt(1));
				line.add(rs.getString(3));
				line.add(rs.getString(4));

				if (!rangeUpdated)
				{
					updateRange(rs.getInt(6));
					rangeUpdated = true;
				}
				if (isRange)
					line.add(rs.getString(5));
				data.add(line);
				if (req_name == null)
					req_name = rs.getString(2);
			}
			label1.setText("Enter " + req_name + " Results");
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
		columnNames.add("");
		columnNames.add("Parameter Name");
		columnNames.add("Result");
		if (isRange)
			columnNames.add("Normal Range");

		return columnNames;
	}

}
