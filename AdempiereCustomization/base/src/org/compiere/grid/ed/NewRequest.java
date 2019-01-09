/*
 * Created by JFormDesigner on Thu Nov 09 19:10:52 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.form.VDepartmentalRequest;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.zenith.util.HmsSetup;

import net.miginfocom.swing.*;
import zenith.model.LabDocument;
import zenith.model.MTreatmentDoc;
import zenith.model.X_hms_department;
import zenith.util.DateUtil;

/**
 * @author Mathew Kipchumba
 */
public class NewRequest extends JDialog implements TableModelListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7080714450859916407L;
	private ZLookup mDepartment_ID;
	private VDate vDate = null;
	private int treatID;
	private int bpID;
	MTreatmentDoc doc = null;
	MBPartner bp = null;
	String trxName = null;

	public NewRequest(Frame owner, int _treatID, int _bpID)
	{
		super(owner, true);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		treatID = _treatID;
		bpID = _bpID;
		initComponents();
		departmentIDStub();
		vDateStub();
		initData();
		textField1.getDocument().addDocumentListener(new MyDocListener());
		textField2.getDocument().addDocumentListener(new MyDocListener());
		// set textField to some arbitrary string....
		textField2.setText("0"); //
		textField2.setVisible(false);
		oldDepartmentValue = -1;
	}

	public NewRequest(Frame owner, MTreatmentDoc doc, MBPartner bp, String trxName)
	{
		super(owner, true);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		treatID = doc.get_ID();
		bpID = bp.get_ID();
		this.doc = doc;
		this.bp = bp;
		System.out.println("doc:" + doc.get_ID());
		System.out.println("bp:" + bp.get_ID());
		initComponents();
		departmentIDStub();
		initData();
		textField1.getDocument().addDocumentListener(new MyDocListener());
		textField2.getDocument().addDocumentListener(new MyDocListener());
		// set textField to some arbitrary string....
		textField2.setText("0"); //
		textField2.setVisible(false);
		oldDepartmentValue = -1;
	}

	public NewRequest(Dialog owner)
	{
		super(owner, true);
		initComponents();

		initData();

	}

	private void vDateStub()
	{
		vDate = new VDate();
		Date date = Calendar.getInstance().getTime();
		vDate.setValue(date);
		contentPanel.add(vDate, "cell 2 16 ,alignx left,growx 0,width 150::300");
	}

	TableRowSorter<DefaultTableModel> sorter = null;

	private void initData()
	{

		Vector<Vector<Object>> data = getDrugsData();
		Vector<String> columnNames = getDrugsColumnNames();

		// Set Model
		DefaultTableModel modelO = new DefaultTableModel(data, columnNames);
		sorter = new TableRowSorter<DefaultTableModel>(modelO);
		table1 = new JTable(modelO) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column)
			{
				switch (column) {
					case 0:
						return Integer.class;
					case 1:
						return Boolean.class;
					case 2:
						return String.class;
					default:
						return String.class;
				}
			}
		};

		// ../

		// ..
		table1.setRowSorter(sorter);

		// Remove previous listeners2
		table1.getModel().removeTableModelListener(this);

		table1.setAutoResizeMode(4);
		// hide the ID column
		TableColumn myTableColumn0 = table1.getColumnModel().getColumn(0);
		myTableColumn0.setMinWidth(0);
		myTableColumn0.setMaxWidth(0);
		myTableColumn0.setWidth(0);
		myTableColumn0.setPreferredWidth(0);
		// collective column size setting
		int[] indexs = {}; /// disabled
		int minWidth = 50;
		int maxWidth = 100;
		int preferredWidth = 50;
		for (int i = 0; i < indexs.length; i++)
		{
			TableColumn column = table1.getColumnModel().getColumn(indexs[i]);
			column.setMinWidth(minWidth);
			column.setMaxWidth(maxWidth);
			// column.setWidth(width);
			column.setPreferredWidth(preferredWidth);
		}
		// set the preferred width of product name column.. mathew 12.10.2017
		try
		{
			TableColumn tableColumn1 = table1.getColumnModel().getColumn(1);
			tableColumn1.setMaxWidth(50);

			// ID Department
			int x = 0;
			TableColumn tableColumn3 = table1.getColumnModel().getColumn(3);
			tableColumn3.setMinWidth(x);
			tableColumn3.setMaxWidth(x);
			tableColumn3.setWidth(x);
			tableColumn3.setPreferredWidth(x);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		modelO.addTableModelListener(this);
		// ---- table1 ----
		table1.setPreferredScrollableViewportSize(new Dimension(300, 200));
		scrollPane1.setViewportView(table1);
		// JList
		list1.setModel(listModel);

		list1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt)
			{
				JList list = (JList) evt.getSource();

				if (evt.getClickCount() == 1 || evt.getClickCount() == 2)
				{
					textField1.setText("");
					for (int i = 0; i < table1.getRowCount(); i++)
					{// For each row

						if (table1.getModel().getValueAt(table1.convertRowIndexToModel(i), 2)
								.equals(list.getSelectedValue()))
						{

							table1.requestFocus();
							table1.changeSelection(i, 2, false, false);
							if (evt.getClickCount() == 2)
							{
								// remove ID from the set
								int requestID = (int) table1.getModel().getValueAt(table1.convertRowIndexToModel(i), 0);
								set.remove(requestID);
								// remove name from the ListModel
								String name = (String) table1.getModel().getValueAt(table1.convertRowIndexToModel(i),
										2);
								listModel.removeElement(name);
								// un-check it
								table1.getModel().setValueAt(false, table1.convertRowIndexToModel(i), 1);
							}
						}

					}
				}
			}
		});
	}

	private Vector<String> getDrugsColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("Select");
		columnNames.add("Service Name");
		columnNames.add("Department ID");
		return columnNames;
	}

	private Vector<Vector<Object>> getDrugsData()
	{

		Vector<Vector<Object>> data = new Vector();
		String sql = "SELECT test.hms_test_id, test.selected , test.name,test.hms_department_ID  FROM adempiere.hms_test test"
				+ "  ORDER BY test.name";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(rs.getInt(1));
				line.add(rs.getString(2).equals("Y"));
				line.add(rs.getString(3));
				line.add(rs.getInt(4));
				data.add(line);
			}
		} catch (Exception localException)
		{
			localException.printStackTrace();
		}

		return data;
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		saveSelectionDetail();
	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	protected void saveSelectionDetail()
	{
		String trxName = Trx.createTrxName();
		int departID = (int) mDepartment_ID.getValue();

		Timestamp billDate = null;
		if (vDate != null)
			billDate = DateUtil.getTimestamp(vDate.getValue());

		Integer[] keys = set.toArray(new Integer[set.size()]);
		for (int i = 0; i < keys.length; i++)
		{

			int testID = keys[i];

			// check if test is request and not done yet
			if (!HmsSetup.getSetup().isrequest_service_before_done())
			{
				if (LabDocument.checkTest(treatID, testID))
				{
					javax.swing.JOptionPane.showMessageDialog(null,
							"Service Already Requested and not done.Please Try again.");
					 return;
				}
			}

			LabDocument ld = new LabDocument(Env.getCtx());
			ld.newRequest(bpID, testID, treatID, false, departID, doc, billDate, trxName);
		}
		if (keys.length > 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Request send successfully...");
			if (doc != null && bp != null)
			{
			}
		} else
		{
			if (doc != null)
			{
				doc.delete(true);
			}
		}
		Trx.get(trxName, false).commit();
		Trx.get(trxName, false).close();
		VDepartmentalRequest dr = new VDepartmentalRequest();
		dr.loadBPartner();
		this.dispose();

	} // saveSelectionDetail

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		textField2 = new JTextField();
		scrollPane2 = new JScrollPane();
		list1 = new JList();
		label4 = new JLabel();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		label3 = new JLabel();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{

			// JFormDesigner evaluation mark
			dialogPane
					.setBorder(new javax.swing.border.CompoundBorder(
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
				contentPanel.setMinimumSize(new Dimension(100, 175));
				contentPanel.setLayout(new MigLayout("insets dialog,hidemode 3",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"
								+ "[]ind" + "[]" + "[]"));

				// ---- label1 ----
				label1.setText("Department");
				contentPanel.add(label1, "cell 0 1 2 1");

				// ---- textField1 ----
				textField1.setMinimumSize(new Dimension(200, 20));
				contentPanel.add(textField1, "cell 2 2 2 4");

				// ---- label2 ----
				label2.setText("Service Name");
				contentPanel.add(label2, "cell 0 3");

				// ======== scrollPane1 ========
				{

					// ---- table1 ----
					table1.setMinimumSize(new Dimension(200, 32));
					scrollPane1.setViewportView(table1);
				}
				contentPanel.add(scrollPane1, "cell 2 5 2 10");
				contentPanel.add(textField2, "cell 0 6 2 1");

				// ======== scrollPane2 ========
				{

					// ---- list1 ----
					list1.setMinimumSize(new Dimension(150, 48));
					list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					list1.setToolTipText("Selected Requests");
					list1.setBorder(new TitledBorder(null, "Selected Requests", TitledBorder.DEFAULT_JUSTIFICATION,
							TitledBorder.ABOVE_TOP));
					scrollPane2.setViewportView(list1);
				}
				contentPanel.add(scrollPane2, "cell 4 5 7 10");

				// ---- label4 ----
				label4.setText("Bill Date");
				contentPanel.add(label4, "cell 0 16");
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[button,fill]"
								+ "[button,fill]" + "[button,fill]",
						// rows
						"[]" + "[]"));

				// ---- okButton ----
				okButton.setText("Send Request");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 7 1");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 8 1");
				buttonBar.add(label3, "cell 9 1");
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	private static int oldDepartmentValue = 0;

	/**
	 * 
	 */
	private void departmentIDStub()
	{
		mDepartment_ID = new ZLookup("hms_department_ID", false, false, true, ZLookupFactory.get(Env.getCtx(), 0, 0,
				MColumn.getColumn_ID(X_hms_department.Table_Name, X_hms_department.COLUMNNAME_hms_department_ID),
				DisplayType.Table));
		mDepartment_ID.setBackground(AdempierePLAF.getInfoBackground());
		mDepartment_ID.addActionListener(new ZLookup() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1800241106974099101L;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (mDepartment_ID.getValue() != null && (int) mDepartment_ID.getValue() == oldDepartmentValue)
					{
						// NEVER TODO

					} else
					{

						if (mDepartment_ID.getValue() != null && (int) mDepartment_ID.getValue() > 0)
						{
							textField2.setText(String.valueOf(mDepartment_ID.getValue()));
							oldDepartmentValue = (int) mDepartment_ID.getValue();
						} else
						{
							textField2.setText("xxx");
							oldDepartmentValue = -1;
						}
						listModel.removeAllElements();
						set.clear();
						// uncheck all
						for (int i = 0; i < table1.getRowCount(); i++)
						{
							table1.getModel().setValueAt(false, table1.convertRowIndexToModel(i), 1);
						}
					}
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPanel.add(mDepartment_ID, "cell 2 1 5 1,alignx left,growx 0,width 300::1000");
		mDepartment_ID.setValue(1000000);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JTextField textField1;
	private JLabel label2;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JTextField textField2;
	private JScrollPane scrollPane2;
	private JList list1;
	private JLabel label4;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel label3;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	DefaultListModel<String> listModel = new DefaultListModel<>();

	Set<Integer> set = new HashSet<>();

	@Override
	public void tableChanged(TableModelEvent e)
	{

		int row = e.getFirstRow();
		int column = e.getColumn();
		if (column == 1)
		{
			TableModel model = (TableModel) e.getSource();
			// String columnName = model.getColumnName(column);
			Boolean checked = (Boolean) model.getValueAt(row, column);
			Integer id = (Integer) model.getValueAt(row, 0);
			String name = (String) model.getValueAt(row, 2);
			if (checked)
			{
				set.add(id);
				listModel.addElement(name);
			} else
			{
				set.remove(id);
				listModel.removeElement(name);
			}

		}
	}

	private final class MyDocListener implements DocumentListener
	{
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

		public void warn()
		{
			RowFilter<DefaultTableModel, Object> rf = null;
			List<RowFilter<Object, Object>> rfs = new ArrayList<RowFilter<Object, Object>>();
			// If current expression doesn't parse, don't update.
			try
			{
				rfs.add(RowFilter.regexFilter("(?i)" + textField1.getText(), 2));
				rfs.add(RowFilter.regexFilter("(?i)" + textField2.getText(), 3));
				rf = RowFilter.andFilter(rfs);

			} catch (java.util.regex.PatternSyntaxException e)
			{
				return;
			}
			sorter.setRowFilter(rf);
		}
	}
}
