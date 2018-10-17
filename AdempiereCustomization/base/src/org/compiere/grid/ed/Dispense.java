/*
 * Created by JFormDesigner on Tue Mar 13 16:47:32 EAT 2018
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.compiere.apps.AEnv;
import org.compiere.model.MLocator;
import org.compiere.util.DB;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.util.DateUtil;

/**
 * @author Math Ew
 */
public class Dispense extends JDialog implements TableModelListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int M_Locator_ID = 0;
	private String whereClause = "";

	public Dispense(Frame owner, int M_Locator_ID, String whereClause)
	{
		super(owner, true);
		initComponents();
		this.M_Locator_ID = M_Locator_ID;
		this.whereClause = whereClause;
		// whereClause = " AND doc.admitted ='N' ";
		start();
		dateField.setText(DateUtil.getTodayDateString());
	}

	private void start()
	{
		setLocatorLabel();
		initData();
		doubleClick();
		dateFieldListener();
		patientNameFieldListener();

	}

	private void setLocatorLabel()
	{
		if (M_Locator_ID > 0)
		{
			MLocator locator = new MLocator(Env.getCtx(), M_Locator_ID, null);
			label_Locator.setText("LOCATOR: " + locator.getValue());
		}

	}

	public int getMLocator_ID()
	{
		return M_Locator_ID;
	}

	public Dispense(Dialog owner)
	{
		super(owner);
		initComponents();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		label1 = new JLabel();
		patientNameField = new JTextField();
		label2 = new JLabel();
		dateField = new JTextField();
		label_Locator = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout("hidemode 3",
				// columns
				"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
						+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
				// rows
				"[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

		// ---- label1 ----
		label1.setText("Patient Name:");
		contentPane.add(label1, "cell 0 1 3 1");

		// ---- patientNameField ----
		patientNameField.setMaximumSize(new Dimension(200, 2147483647));
		contentPane.add(patientNameField, "cell 3 1 5 1");

		// ---- label2 ----
		label2.setText("Date:");
		contentPane.add(label2, "cell 0 3 3 1");

		// ---- dateField ----
		dateField.setMaximumSize(new Dimension(200, 2147483647));
		contentPane.add(dateField, "cell 3 3 5 1");

		// ---- label_Locator ----
		label_Locator.setText("LOCATOR: Pharmacy");
		contentPane.add(label_Locator, "cell 3 3 5 1");

		// ======== scrollPane1 ========
		{
			scrollPane1.setViewportView(table1);
		}
		contentPane.add(scrollPane1, "cell 0 5 8 1");
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew
	private JLabel label1;
	private JTextField patientNameField;
	private JLabel label2;
	private JTextField dateField;
	private JLabel label_Locator;
	private JScrollPane scrollPane1;
	private JTable table1;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	private void initData()
	{
		Vector<Vector<Object>> data = getResultData();
		Vector<String> columnNames = getResultColumnNames();

		// Set Model
		DefaultTableModel modelO = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

			@Override
			public void fireTableDataChanged()
			{
				fireTableChanged(new TableModelEvent(this, // tableModel
						0, // firstRow
						getRowCount() - 1, // lastRow
						TableModelEvent.ALL_COLUMNS, // column
						TableModelEvent.UPDATE)); // changeType
			}
		};

		table1 = new JTable(modelO) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column)
			{
				switch (column) {
					case 0:
						return Integer.class;
					case 1:
						return Integer.class;
					case 2:
						return Integer.class;
					case 3:
						return Integer.class;
					case 4:
						return String.class;
					case 5:
						return String.class;
					case 6:
						return String.class;
					case 7:
						return String.class;
					case 8:
						return String.class;
					case 9:
						return String.class;
					case 10:
						return String.class;
					case 11:
						return Boolean.class;
					default:
						return String.class;
				}
			}
		};

		//
		table1.setAutoCreateRowSorter(true);
		modelO.addTableModelListener(this);
		sorter = new TableRowSorter<DefaultTableModel>(modelO);
		table1.setRowSorter(sorter);
		table1.setRowHeight(20);

		// Remove previous listeners2

		// table1.setModel(modelO);
		// setVitalsColumnClass(orderTable);
		table1.setAutoResizeMode(4);

		// set the preferred width of product name column.. mathew 12.10.2017
		try
		{
			int[] columns0 = { 0, 1, 2, 3 };
			for (int i = 0; i < columns0.length; i++)
			{
				int column = columns0[i];
				TableColumn tableColumn = table1.getColumnModel().getColumn(column);
				tableColumn.setMinWidth(0);
				tableColumn.setWidth(0);
				tableColumn.setMaxWidth(0);
				// table1.removeColumn(tableColumn);
			}
			/////////
			TableColumn tableColumnDate = table1.getColumnModel().getColumn(4);
			tableColumnDate.setMaxWidth(100);
			/////////
			TableColumn tableColumnTime = table1.getColumnModel().getColumn(5);
			tableColumnTime.setMaxWidth(80);
			////////
			TableColumn tableColumnPatientNo = table1.getColumnModel().getColumn(5);
			tableColumnPatientNo.setMaxWidth(160);
			////////////
			int[] columns2 = { 7, 8 };

			for (int i = 0; i < columns2.length; i++)
			{
				int column = columns2[i];

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
		table1.setPreferredScrollableViewportSize(new Dimension(1000, 550));
		scrollPane1.setViewportView(table1);
		table1.setDefaultRenderer(Integer.class, new CustomRenderer());
		table1.setDefaultRenderer(String.class, new CustomRenderer());
	}

	public Vector<Vector<Object>> getResultData()
	{
		PreparedStatement pstmt = null;
		Vector<Vector<Object>> data = new Vector();
		String sql = " SELECT  doc.hms_treatment_doc_ID, drugs_ordered,drugs_issued,drugs_not_issued,"
				+ " TO_CHAR(doc.created, 'DD/MM/YYYY'), to_char(doc.created::time,'HH12:MI AM'),"
				+ " bp.documentno ,bp.name  , AGE(bp.dob)::text,doc.diagnosis, g.name,"
				+ " doc.admitted FROM adempiere.hms_treatment_doc doc "
				+ " INNER JOIN adempiere.c_bpartner bp ON bp.c_bpartner_id = doc.c_bpartner_id "
				+ " INNER JOIN adempiere.C_BP_Group g ON doc.C_BP_Group_ID = g.C_BP_Group_ID"
				+ " WHERE drugs_ordered>0 " + whereClause + " ORDER BY doc.created DESC";

		try
		{
			pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(rs.getInt(1));
				line.add(rs.getInt(2));// ordered
				line.add(rs.getInt(3));// issued
				line.add(rs.getInt(4));// not issued
				line.add(rs.getString(5));// date
				line.add(rs.getString(6));// time
				line.add(rs.getString(7));// patient no
				line.add(rs.getString(8));// name
				line.add(rs.getString(9));// age
				line.add(rs.getString(10));// diagnosis
				line.add(rs.getString(11));// Billing Instance
				line.add("Y".equals(rs.getString(12)));
				data.add(line);
			}

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

	private Vector<String> getResultColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add("Tread ID");
		columnNames.add("Ordered");
		columnNames.add("Issued");
		columnNames.add("Not Issued");
		columnNames.add("Date");
		columnNames.add("Time");
		columnNames.add("Patient NO.");
		columnNames.add("Patient Name");
		columnNames.add("Age of Patient");
		columnNames.add("Patient Diagnosis");
		columnNames.add("Billing Type");
		columnNames.add("Admitted");
		return columnNames;
	}

	private void doubleClick()
	{

		table1.addMouseListener(doubleClicML);

	}

	MouseListener doubleClicML = new MouseAdapter() {
		public void mouseClicked(MouseEvent me)
		{
			JTable table = (JTable) me.getSource();
			Point p = me.getPoint();
			int row = table.rowAtPoint(p);
			if (me.getClickCount() == 2)
			{
				loadDrugs(row);
			}

		}

	};

	private void loadDrugs(int row)
	{
		try
		{
			Integer idColumn = (Integer) table1.getValueAt(row, 0);
			Drugs drug = new Drugs((Frame) null, idColumn, this);
			AEnv.showCenterScreen(drug);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void patientNameFieldListener()
	{
		// Listen for changes in the text
		patientNameField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e)
			{
				warn();
			}

			public void removeUpdate(DocumentEvent e)
			{
				warn();
			}

			public void insertUpdate(DocumentEvent e)
			{
				warn();
			}

		});
	}

	private void dateFieldListener()
	{
		// Listen for changes in the text
		dateField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e)
			{
				warn();
			}

			public void removeUpdate(DocumentEvent e)
			{
				warn();
			}

			public void insertUpdate(DocumentEvent e)
			{
				warn();
			}

		});
	}

	TableRowSorter<DefaultTableModel> sorter = null;

	public void warn()
	{
		RowFilter<DefaultTableModel, Object> rf = null;
		List<RowFilter<Object, Object>> rfs = new ArrayList<RowFilter<Object, Object>>(2);
		// If current expression doesn't parse, don't update.
		try
		{

			rfs.add(RowFilter.regexFilter("(?i)" + patientNameField.getText(), 6, 7));
			rfs.add(RowFilter.regexFilter("(?i)" + dateField.getText(), 4));
			rf = RowFilter.andFilter(rfs);

		} catch (java.util.regex.PatternSyntaxException e)
		{
			return;
		}
		sorter.setRowFilter(rf);
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		// TODO Auto-generated method stub

	}

	public void refresh()
	{
		start();
		patientNameField.setText(patientNameField.getText());
		dateField.setText(dateField.getText());
	}

	class CustomRenderer extends DefaultTableCellRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7945339469371183799L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column)
		{
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if ((Integer) table.getValueAt(row, 3) > 0)
			{
				c.setForeground(new java.awt.Color(255, 100, 100));
			} else
			{
				c.setForeground(new java.awt.Color(0, 0, 0));

			}
			if ((Boolean) table.getValueAt(row, 11))
			{
				c.setForeground(new java.awt.Color(0, 0, 255));
			}
			return c;
		}
	}
}
