/*
 * Created by JFormDesigner on Thu Oct 19 14:34:40 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.form.VPharmacy;
import org.compiere.model.MBPartner;
import org.compiere.model.MLocator;
import org.compiere.model.MQuery;
import org.compiere.model.MStorage;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.zenith.util.HmsSetup;

import net.miginfocom.swing.*;
import zenith.model.HmsDrugs;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;

/**
 * @author Mathew Kipchumba
 */
public class Drugs extends JDialog implements TableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4535832867922968003L;
	private static int m_treatID;
	private Dispense dispense = null;

	private static int M_Warehouse_ID = 0;
	private static int M_Locator_ID = 0;
	private static boolean showStock = false;
	private boolean issueNegatives = false;

	private boolean isPayAfter = false;

	public Drugs(Frame owner, int treatID, Dispense _dispense)
	{
		super(owner, true);
		set_TrxName(Trx.createTrxName());
		m_treatID = treatID;
		dispense = _dispense;
		showStock = HmsSetup.getSetup().ispharmacy_show_stock();
		setLocatorDetails();
		setIsPayAfter();
		initComponents();
		initData();
		this.setTitle("Issue Drugs : " + getPatientName());
		// addEscapeListener(this);
		addEscapeListenerLamda(this);
		// // //

	}

	private String trxName = null;

	private void set_TrxName(String name)
	{
		trxName = name;
	}

	private String get_TrxName()
	{
		return trxName;
	}

	private void setIsPayAfter()
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), m_treatID, get_TrxName());
		int C_BP_Group_ID = doc.getC_BP_Group_ID();
		String br = getBillingRule(C_BP_Group_ID);
		if (br.equals("PA"))
			isPayAfter = true;
	}

	private String getBillingRule(int C_BP_Group_ID)
	{
		String sql = "SELECT billing_rule FROM adempiere.C_BP_Group WHERE C_BP_Group_ID=" + C_BP_Group_ID;
		return DB.getSQLValueString(get_TrxName(), sql);
	}

	public Drugs(Dialog owner)
	{
		super(owner, true);
		initComponents();
	}

	private void setLocatorDetails()
	{
		MLocator locator = new MLocator(Env.getCtx(), dispense.getMLocator_ID(), get_TrxName());
		M_Locator_ID = locator.getM_Locator_ID();
		M_Warehouse_ID = locator.getM_Warehouse_ID();
	}

	private String getPatientName()
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), m_treatID, get_TrxName());
		MBPartner bp = new MBPartner(Env.getCtx(), doc.getC_BPartner_ID(), get_TrxName());
		return bp.getName();
	}

	public static void addEscapeListener(final JDialog dialog)
	{
		ActionListener escListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dialog.dispose();
			}
		};
		dialog.getRootPane().registerKeyboardAction(escListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	// java 8
	public static void addEscapeListenerLamda(final JDialog dialog)
	{
		dialog.getRootPane().registerKeyboardAction(e -> {
			dialog.dispose();
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private static void anotherLambdaTest(final JDialog dialog)
	{
		dialog.getRootPane().registerKeyboardAction(e -> {
			dialog.dispose();
		}, KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private void initData()
	{

		if (!HmsSetup.getSetup().iszoom_from_pharmacy())
		{
			openTreatmentWindow.setVisible(false);
		}

		Vector<Vector<Object>> data = getDrugsData();
		Vector<String> columnNames = getDrugsColumnNames();

		// Set Model
		DefaultTableModel modelO = new DefaultTableModel(data, columnNames) {

			/**
			 * 
			* */
			private static final long serialVersionUID = 1L;
			int issuedColumn = 7;

			@Override
			public boolean isCellEditable(int row, int column)
			{
				Boolean issued = (Boolean) this.getValueAt(row, issuedColumn);
				if (column == 1 && !issued)
					return true;
				return false;
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
						return Boolean.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return BigDecimal.class;
					case 5:
						return Boolean.class;
					case 6:
						return Boolean.class;
					case 7:
						return Boolean.class;
					case 8:
						return String.class;

					case 9:
						if (showStock)
							return String.class;
					default:
						return String.class;
				}

			}
		};
		// Remove previous listeners2
		table1.getModel().removeTableModelListener(this);

		// table1.setModel(modelO);
		// setVitalsColumnClass(orderTable);
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
			// staff
			TableColumn tableColumnStaff = table1.getColumnModel().getColumn(8);
			tableColumnStaff.setMaxWidth(200);
			//
			int columns[] = { 1, 2, 4, 5, 6, 7 };
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
				// dosage description with ... mathew 03.01.18
				TableColumn columnDosage = table1.getColumnModel().getColumn(3); // dosage
				columnDosage.setPreferredWidth(200); // description

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		modelO.addTableModelListener(this);
		// ---- table1 ----
		table1.setPreferredScrollableViewportSize(new Dimension(850, 250));
		scrollPane1.setViewportView(table1);

		table1.setDefaultRenderer(String.class, new CustomRenderer());
		table1.setDefaultRenderer(BigDecimal.class, new CustomRenderer());
		// table1.setDefaultRenderer(Boolean.class, new CustomRenderer());
	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		for (Integer id : set)
		{
			MBilling bill = new MBilling(Env.getCtx(), id, get_TrxName());
			bill.setissued(true);
			bill.save();
			// update stock .... mathew 06.01.2018
			updateStock(bill.getM_Product_ID(), bill.getQty().negate());
			if (1 == 1)
			{
				HmsDrugs drugs = new HmsDrugs(Env.getCtx(), 0, null);
				drugs.sethms_billing_ID(bill.gethms_billing_ID());
				drugs.setQty(bill.getQty());
				drugs.save();
			}
		}
		if (set.size() > 0)
		{
			updateDoc();
			JOptionPane.showMessageDialog(null, "Issued", "Information", JOptionPane.INFORMATION_MESSAGE);
			initData();
			VPharmacy pharm = new VPharmacy();
			dispense.refresh();

		} else
		{
			JOptionPane.showMessageDialog(null, "Please select what to issue!", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}
		// clear the set
		set.removeAll(set);
		// remove negatives
		String deleteQuery = "UPDATE  adempiere.m_storage set qtyonhand=0 WHERE qtyonhand<0";
		DB.executeUpdate(deleteQuery, get_TrxName());
		// Remove decimals
		String updateQuery = "UPDATE adempiere.m_storage  SET qtyonhand = CAST(to_char(qtyonhand, 'FM999999999990.999999') AS NUMERIC)";
		DB.executeUpdate(updateQuery, get_TrxName());
	}

	private void updateDoc()
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), m_treatID, get_TrxName());
		doc.setpharm_done(true);
		doc.save();
		doc.updateDrugStatus();
		// updateAllDrugs();
	}

	int x = 0;

	private void updateAllDrugs() // one time use
	{
		String sql = " SELECT hms_treatment_doc_ID FROM adempiere.hms_treatment_doc";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			rs = stm.executeQuery();
			while (rs.next())
			{
				int treatID = rs.getInt(1);
				MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), treatID, get_TrxName());
				doc.updateDrugStatus(doc);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(rs);
			DB.close(stm);
			rs = null;
			stm = null;
		}
	}

	private void updateStock(int M_Product_ID, BigDecimal Qty)
	{
		Map<Integer, BigDecimal> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		String sql = "select m_attributesetinstance_id,qtyonhand from adempiere.m_storage where m_product_id= "
				+ M_Product_ID + " and m_locator_id = " + M_Locator_ID + " ORDER BY qtyonhand DESC";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int m_attributesetinstance_id = rs.getInt(1);
				BigDecimal qtyonhand = rs.getBigDecimal(2);
				map.put(m_attributesetinstance_id, qtyonhand);
				list.add(m_attributesetinstance_id);
			}
		} catch (Exception e)
		{

		}
		BigDecimal required = Qty;
		for (Integer asi : list)
		{
			if (required.compareTo(map.get(asi)) == 1)
			{
				updateStorage(M_Product_ID, map.get(asi), asi);
				required = required.subtract(map.get(asi));
			} else if (required.compareTo(map.get(asi)) == 0)
			{
				updateStorage(M_Product_ID, map.get(asi), asi);
				required = Env.ZERO;
				break;
			} else if (required.compareTo(map.get(asi)) == -1)
			{
				updateStorage(M_Product_ID, required, asi);
				required = Env.ZERO;
				break;
			}
		}
		if (required.compareTo(Env.ZERO) == 1)
		{
			updateStorage(M_Product_ID, required, 0);
		}

	}

	private void updateStorage(int M_Product_ID, BigDecimal Qty, int asi)
	{
		MStorage.add(Env.getCtx(), M_Warehouse_ID, M_Locator_ID, M_Product_ID, asi, 0, Qty, Env.ZERO, Env.ZERO, null);
	}

	private void openTreatmentWindowActionPerformed(ActionEvent e)
	{
		this.dispose();
		dispense.dispose();
		openTreatmentWindow();

	}

	private boolean openTreatmentWindow()
	{
		final int Treatment_WINDOW_ID = 1000004;
		/** filter the data - needs to be generated for real use... */
		String whereString = " hms_treatment_doc_ID=" + m_treatID;
		final AWindow poFrame = new AWindow();
		final MQuery query = new MQuery("hms_treatment_doc");
		query.addRestriction(whereString);
		final boolean ok = poFrame.initWindow(Treatment_WINDOW_ID, query);
		if (!ok)
		{
			return false;
		}
		poFrame.pack();
		AEnv.showCenterScreen(poFrame);
		return true;
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license -
		// mathew359722@gmail.com
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		openTreatmentWindow = new JButton();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		helpButton = new JButton();

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
				contentPanel.setLayout(new MigLayout("insets dialog,hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" + "[]0" + "[]0" + "[]0" + "[]0" + "[]"));

				// ---- openTreatmentWindow ----
				openTreatmentWindow.setText("Open Treatment Window");
				openTreatmentWindow.addActionListener(e -> {
					openTreatmentWindowActionPerformed(e);
				});
				contentPanel.add(openTreatmentWindow, "cell 0 1");

				// ======== scrollPane1 ========
				{

					// ---- table1 ----
					table1.setPreferredScrollableViewportSize(new Dimension(450, 150));
					scrollPane1.setViewportView(table1);
				}
				contentPanel.add(scrollPane1, "cell 0 5");
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[button,fill]" + "[button,fill]" + "[button,fill]",
						// rows
						null));

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 0 0");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 1 0");

				// ---- helpButton ----
				helpButton.setText("Help");
				buttonBar.add(helpButton, "cell 2 0");
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(535, 250);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - mathew359722@gmail.com
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JButton openTreatmentWindow;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	private JButton helpButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	Set<Integer> set = new HashSet<>();

	@Override
	public void tableChanged(TableModelEvent e)
	{

		int row = e.getFirstRow();
		int column = e.getColumn();
		int paidColummn = 5;
		int invoicedColummn = 6;
		if (column == 1)
		{
			TableModel model = (TableModel) e.getSource();

			// String columnName = model.getColumnName(column);
			Boolean checked = (Boolean) model.getValueAt(row, column);
			Boolean paid = (Boolean) model.getValueAt(row, paidColummn);
			Boolean invoiced = (Boolean) model.getValueAt(row, invoicedColummn);
			Integer id = (Integer) model.getValueAt(row, 0);
			if ((paid || invoiced || isPayAfter) && checked)
			{
				if (issueNegatives)
				{
					set.add(id);
				} else
				{
					int hms_billing_ID = (int) model.getValueAt(row, 0);
					BigDecimal qty = (BigDecimal) model.getValueAt(row, 4);

					int M_Product_ID = getM_ProductID(hms_billing_ID);
					BigDecimal qtyAvailable = getQuantity(M_Product_ID);
					if (qtyAvailable.compareTo(qty) < 0)
						JOptionPane.showMessageDialog(null,
								new JLabel("<html><h1><font color='red'>Stock not available!</font></h1></html>"),
								"Not Issued!", JOptionPane.ERROR_MESSAGE);
					else
					{
						set.add(id);
					}
				}
			} else if ((paid || invoiced) && !checked)
			{
				set.remove(id);
			} else if (!(paid || invoiced) && checked)
			{
				JOptionPane.showMessageDialog(null,
						new JLabel("<html><h1><font color='red'>Line Not Paid/Invoiced!</font></h1></html>"),
						"Not Issued!", JOptionPane.ERROR_MESSAGE);
				// JOptionPane.showMessageDialog(null, "Line Not Paid!",
				// "Error!", JOptionPane.ERROR_MESSAGE);
				model.setValueAt(false, row, column);
				set.remove(id);
			}
		}
	}

	private int getM_ProductID(int hms_billing_ID)
	{
		MBilling billing = new MBilling(Env.getCtx(), hms_billing_ID, get_TrxName());
		return billing.getM_Product_ID();
	}

	public Vector<Vector<Object>> getDrugsData()
	{
		Vector<Vector<Object>> data = new Vector();
		StringBuilder sql = new StringBuilder();
		sql.append("select hms_billing_id,bill.issue_drugs, pro.name,  bill.dosage_description,bill.qty,"
				+ " bill.paid,bill.invoiced,bill.issued,u.name,pro.M_Product_ID from adempiere.hms_billing bill "
				+ " inner join adempiere.m_product pro on pro.m_product_id = bill.m_product_id "
				+ " INNER JOIN adempiere.AD_User u ON u.AD_User_ID = bill.createdby ");

		String whereClause = " WHERE bill.hms_treatment_doc_id = " + m_treatID
				+ " AND (bill.to_pharm = 'Y' OR bill.is_discharge_drug ='Y')";
		if (!HmsSetup.allowBillingAfterService())
			whereClause = whereClause + " AND (bill.paid='Y' OR bill.invoiced ='Y') ";
		String orderClause = " ORDER BY bill.created DESC";
		sql.append(whereClause).append(orderClause);
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			// pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector();
				line.add(rs.getInt(1));
				line.add(rs.getString(2).equals("Y"));
				line.add(rs.getString(3));
				line.add(rs.getString(4));
				line.add(rs.getBigDecimal(5).setScale(0));
				line.add(rs.getString(6).equals("Y"));
				line.add(rs.getString(7).equals("Y"));
				line.add(rs.getString(8).equals("Y"));
				line.add(rs.getString(9));

				int prodID = rs.getInt(10);
				if (showStock)
					line.add(getQuantityAsString(prodID));
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

	public static String getQuantityAsString(int M_Product_ID)
	{
		return getQuantity(M_Product_ID).stripTrailingZeros().toPlainString();
	}

	public static BigDecimal getQuantity(int M_Product_ID)
	{
		String sql = "SELECT COALESCE(SUM(qtyonhand),0) FROM adempiere.M_Storage WHERE M_Product_ID =" + M_Product_ID
				+ " AND M_Locator_ID=" + M_Locator_ID;
		BigDecimal qty = DB.getSQLValueBD(null, sql);
		return qty;
	}

	public static Vector<String> getDrugsColumnNames()
	{
		Vector<String> columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("Issue");
		columnNames.add("Product Name");
		columnNames.add("Dosage");
		columnNames.add("Qty");
		columnNames.add("Paid");
		columnNames.add("Invoiced");
		columnNames.add("Issued");
		columnNames.add("Staff");
		if (showStock)
			columnNames.add("Available Stock");
		return columnNames;
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
			if (!(Boolean) table.getValueAt(row, 7))
			{
				c.setForeground(new java.awt.Color(255, 100, 100));
			} else
			{
				c.setForeground(new java.awt.Color(0, 0, 0));
			}
			if (!(Boolean) table.getValueAt(row, 5) && !(Boolean) table.getValueAt(row, 6))
			{
				c.setForeground(new java.awt.Color(150, 0, 150));
			}
			return c;
		}
	}

}
