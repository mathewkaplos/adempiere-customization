/*
 * Created by JFormDesigner on Fri Oct 13 15:55:45 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.text.DocumentFilter.FilterBypass;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.form.VDischargeDrug;
import org.compiere.model.MColumn;
import org.compiere.model.MProduct;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MBilling;

/**
 * @author Mathew Kipchumba
 */
public class EditDischargeDrug extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3606164055016668586L;
	int m_billID = 0;
	private ZLookup mProduct_ID;

	public EditDischargeDrug(Frame owner, int billID)
	{
		super(owner, true);
		initComponents();

		init();
		m_billID = billID;
		populateWindow();
		addListeners();
		validateFields();
	}

	public EditDischargeDrug(Dialog owner, int billID)
	{
		super(owner, true);
		initComponents();
		init();
		populateWindow();
		addListeners();
		validateFields();
	}

	private void init()
	{

		mProduct_ID = new ZLookup("M_Product_ID", false, false, true, ZLookupFactory.get(Env.getCtx(), 0, 0,
				MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_Product_ID), DisplayType.Table));
		mProduct_ID.setBackground(AdempierePLAF.getInfoBackground());
		mProduct_ID.addActionListener(new ZLookup() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1800241106974099101L;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int hms_treatment_doc_ID = VDischargeDrug.getHms_treatment_doc_ID();
					int M_Product_ID = (int) mProduct_ID.getValue();
					BigDecimal amt = doIt(M_Product_ID, hms_treatment_doc_ID).stripTrailingZeros();
					BigDecimal qty = new BigDecimal(textFieldDosage.getText()).stripTrailingZeros();
					BigDecimal total = amt.multiply(qty);
					textFieldPrice.setText(String.valueOf(amt.toPlainString()));
					textFieldTotal.setText(String.valueOf(total.toPlainString()));
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

		contentPanel.add(mProduct_ID, "cell 1 1,alignx left,growx 0,wmin 400");

	}

	private void populateWindow()
	{
		MBilling bill = new MBilling(Env.getCtx(), m_billID, null);

		mProduct_ID.setValue(bill.getM_Product_ID());
		if (bill.getunits_per_freq() > 0)
			textFieldUnits.setText(String.valueOf(bill.getunits_per_freq()));
		if (bill.getfreq() > 0)
			textFieldFrequency.setText(String.valueOf(bill.getfreq()));
		if (bill.getdays() > 0)
			textFieldDays.setText(String.valueOf(bill.getdays()));
		if (bill.getdosage() > 0)
			textFieldDosage.setText(String.valueOf(bill.getdosage()));
		if (bill.getDescription() != null && !bill.getDescription().trim().equals(""))
			textFieldDescription.setText(bill.getDescription());
		if (bill.getPrice() != null)
			textFieldPrice.setText(bill.getPrice().stripTrailingZeros().toPlainString());
		if (bill.getLineNetAmt() != null)
			textFieldTotal.setText(bill.getLineNetAmt().stripTrailingZeros().toPlainString());
	}

	public static void main(String[] args)
	{
		EditDischargeDrug ed = new EditDischargeDrug((Frame) null, 0);
		ed.setVisible(true);
	}

	/**
	 * 
	 */
	private void addListeners()
	{
		JTextField[] jTextFields = { textFieldUnits, textFieldFrequency, textFieldDays, textFieldDosage };
		for (int i = 0; i < jTextFields.length; i++)
		{
			JTextField field = jTextFields[i];
			field.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e)
				{
					warn(field);
				}

				public void removeUpdate(DocumentEvent e)
				{
					warn(field);
				}

				public void insertUpdate(DocumentEvent e)
				{
					warn(field);
				}

				public void warn(JTextField jTextField)
				{

					try
					{
						if (jTextField.getText().trim() != null && !jTextField.getText().trim().equals(""))
						{
							if (Integer.parseInt(jTextField.getText()) <= 0)
							{
								JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0",
										"Error Massage", JOptionPane.ERROR_MESSAGE);
							} else
							{
								String units = textFieldUnits.getText();
								String freq = textFieldFrequency.getText();
								String days = textFieldDays.getText();
								textFieldDescription.setText(units + " X " + freq);
								int qty = Integer.parseInt(units) * Integer.parseInt(freq) * Integer.parseInt(days);
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run()
									{
										textFieldDosage.setText(String.valueOf(qty));
										try
										{
											int hms_treatment_doc_ID = VDischargeDrug.getHms_treatment_doc_ID();
											int M_Product_ID = (int) mProduct_ID.getValue();
											BigDecimal amt = doIt(M_Product_ID, hms_treatment_doc_ID)
													.stripTrailingZeros();
											BigDecimal qty = new BigDecimal(textFieldDosage.getText())
													.stripTrailingZeros();
											BigDecimal total = amt.multiply(qty);
											textFieldPrice.setText(String.valueOf(amt.toPlainString()));
											textFieldTotal.setText(String.valueOf(total.toPlainString()));
										} catch (Exception e1)
										{
											e1.printStackTrace();
										}
									}
								});
							}
						}
					} catch (NumberFormatException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (HeadlessException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		int M_Product_ID = (int) mProduct_ID.getValue();
		int hms_treatment_doc_ID = VDischargeDrug.getHms_treatment_doc_ID();
		// int C_BPartner_ID = VDischargeDrug.getBPartner_ID();
		MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
		// bill.sethms_treatment_doc_ID(hms_treatment_doc_ID);
		// bill.setC_BPartner_ID(C_BPartner_ID);
		bill.setM_Product_ID(M_Product_ID);
		bill.setQty(new BigDecimal(textFieldDosage.getText()));
		BigDecimal amt = doIt(M_Product_ID, hms_treatment_doc_ID);
		bill.setPrice(amt);
		bill.setLineNetAmt((new BigDecimal(textFieldDosage.getText())).multiply(amt));
		bill.setTotalAmt((new BigDecimal(textFieldDosage.getText())).multiply(new BigDecimal("100")));
		bill.setunits_per_freq(Integer.parseInt(textFieldUnits.getText()));
		bill.setfreq(Integer.parseInt(textFieldFrequency.getText()));
		bill.setdays(Integer.parseInt(textFieldDays.getText()));
		bill.setdosage(Integer.parseInt(textFieldDosage.getText()));
		bill.setDescription(textFieldUnits.getText());
		if (!isService(M_Product_ID))// not service
		{
			bill.setto_pharm(true);
			bill.setbill_group(3);
		} else
		{
			bill.setbill_group(4);
		}
		bill.save();
		VDischargeDrug vb = new VDischargeDrug();
		vb.loadBPartner();
		JOptionPane.showMessageDialog(null, "Updated: Line updated Succesfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
	private boolean isService(int M_Product_ID)
	{
		MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
		return product.isService();
	}
	private void deleteButtonActionPerformed(ActionEvent e)
	{
		int x = yesnocancel("Are you sure you want to delete this?");
		if (x == 0)
		{
			MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
			bill.delete(true);
			VDischargeDrug vb = new VDischargeDrug();
			vb.loadBPartner();
			JOptionPane.showMessageDialog(null, "Updated: Line deleted Succesfully...", "Information Message",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else if (x == 1)
		{
			this.dispose();
		} else if (x == 2)
		{
			return;
		}
	}

	private static int yesnocancel(String theMessage)
	{
		int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "Alert",
				JOptionPane.YES_NO_CANCEL_OPTION);
		return result;
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		textFieldUnits = new JTextField();
		label1 = new JLabel();
		label2 = new JLabel();
		textFieldFrequency = new JTextField();
		label3 = new JLabel();
		label4 = new JLabel();
		textFieldDays = new JTextField();
		label6 = new JLabel();
		textFieldDosage = new JTextField();
		label5 = new JLabel();
		textFieldDescription = new JTextField();
		label7 = new JLabel();
		textFieldPrice = new JTextField();
		textFieldTotal = new JTextField();
		label8 = new JLabel();
		buttonBar = new JPanel();
		deleteButton = new JButton();
		okButton = new JButton();
		cancelButton = new JButton();

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
						"[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

				// ---- textFieldUnits ----
				textFieldUnits.setColumns(10);
				textFieldUnits.setText("1");
				contentPanel.add(textFieldUnits, "cell 1 2,alignx left,growx 0,wmin 150");

				// ---- label1 ----
				label1.setText("Product Name:");
				contentPanel.add(label1, "cell 0 1");

				// ---- label2 ----
				label2.setText("Units Per Freq:");
				contentPanel.add(label2, "cell 0 2");

				// ---- textFieldFrequency ----
				textFieldFrequency.setColumns(10);
				textFieldFrequency.setText("1");
				contentPanel.add(textFieldFrequency, "cell 1 3,alignx left,growx 0,wmin 150");

				// ---- label3 ----
				label3.setText("Frequency");
				contentPanel.add(label3, "cell 0 3");

				// ---- label4 ----
				label4.setText("Days:");
				contentPanel.add(label4, "cell 0 4");

				// ---- textFieldDays ----
				textFieldDays.setColumns(10);
				textFieldDays.setText("1");
				contentPanel.add(textFieldDays, "cell 1 4,alignx left,growx 0,wmin 150");

				// ---- label6 ----
				label6.setText("Dosage:");
				contentPanel.add(label6, "cell 0 5");

				// ---- textFieldDosage ----
				textFieldDosage.setColumns(10);
				textFieldDosage.setText("1");
				textFieldDosage.setEditable(false);
				contentPanel.add(textFieldDosage, "cell 1 5,alignx left,growx 0,wmin 150");

				// ---- label5 ----
				label5.setText("Description:");
				contentPanel.add(label5, "cell 0 6");

				// ---- textFieldDescription ----
				textFieldDescription.setColumns(10);
				textFieldDescription.setText("1 X 1");
				contentPanel.add(textFieldDescription, "cell 1 6,alignx left,growx 0,wmin 400");

				// ---- label7 ----
				label7.setText("Unit Price");
				contentPanel.add(label7, "cell 0 7");

				// ---- textFieldPrice ----
				textFieldPrice.setEditable(false);
				contentPanel.add(textFieldPrice, "cell 1 7,wmin 150");

				// ---- textFieldTotal ----
				textFieldTotal.setEditable(false);
				contentPanel.add(textFieldTotal, "cell 1 8,wmin 150");

				// ---- label8 ----
				label8.setText("Total Amount");
				contentPanel.add(label8, "cell 0 8");
			}
			dialogPane.add(contentPanel, BorderLayout.NORTH);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[button,fill]" + "[button,fill]"
								+ "[button,fill]",
						// rows
						null));

				// ---- deleteButton ----
				deleteButton.setText("Delete");
				deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
				buttonBar.add(deleteButton, "cell 0 0");

				// ---- okButton ----
				okButton.setText("Save");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 13 0");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 14 0");
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	protected BigDecimal doIt(int M_Product_ID, int hms_treatment_doc_id)
	{
		// AddDefaultLocation();

		BigDecimal amount = Env.ZERO;
		int M_Pricelist_ID = getPriceListID(getC_BP_Group_ID(hms_treatment_doc_id));
		if (M_Pricelist_ID != 0)
		{
			int M_Pricelist_version_ID = getPriceListVersionID(M_Pricelist_ID);
			if (M_Pricelist_version_ID != 0)
			{
				BigDecimal price = getProductPrice(M_Product_ID, M_Pricelist_version_ID);
				if (price != null && price.compareTo(Env.ZERO) != 0)
					amount = price;
				else
					amount = getAnyPrice(M_Product_ID, "( The Price for the Pricelist Version is ZERO (0). )");

			} else
			{
				amount = getAnyPrice(M_Product_ID, "( The Pricelist has no active Pricelist Version. )");
			}

		} else
		{
			amount = getAnyPrice(M_Product_ID, "( The Patient Group has no pricelist defined. )");
		}

		return amount;
	}

	private int getPriceListID(int C_BP_Group_ID)
	{
		int M_Pricelist_ID = 0;
		String sql = "SELECT M_Pricelist_ID from adempiere.C_BP_Group WHERE C_BP_Group_ID=" + C_BP_Group_ID;
		M_Pricelist_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_ID;
	}

	private String get_TrxName()
	{
		return null;
	}

	private int getPriceListVersionID(int M_Pricelist_ID)
	{
		int M_Pricelist_version_ID = 0;
		String sql = " SELECT MAX(M_Pricelist_version_ID) from adempiere.M_Pricelist_version" + " WHERE M_Pricelist_ID="
				+ M_Pricelist_ID + " AND isactive='Y'";
		M_Pricelist_version_ID = DB.getSQLValue(get_TrxName(), sql);
		return M_Pricelist_version_ID;

	}

	// Product Pricelist......
	private BigDecimal getProductPrice(int M_Product_ID, int M_Pricelist_version_ID)
	{
		BigDecimal priceList = Env.ZERO;
		String sql = "SELECT pricelist FROM adempiere.M_Productprice WHERE M_Product_ID= ? AND  M_Pricelist_version_ID = ?";
		priceList = DB.getSQLValueBD(get_TrxName(), sql, M_Product_ID, M_Pricelist_version_ID);
		return priceList;
	}
	/*
	 * get any price of product if: 1)Business partner Group has no Price list.
	 * 2) The product has no price list that belongs to the business partner
	 * group (2nd is not applied anyway) .3) The product price list is zero.
	 * 4)The price list has no active price-list-version
	 */

	private BigDecimal getAnyPrice(int M_Product_ID, String failMsg)
	{
		BigDecimal price = Env.ZERO;
		String sql = "SELECT MAX(pricelist) FROM adempiere.M_Productprice WHERE M_Product_ID=" + M_Product_ID;
		price = DB.getSQLValueBD(get_TrxName(), sql);
		if (price == null || price.compareTo(Env.ZERO) == 0)
		{
			String m_processMsg = "Product/Service: " + MProduct.get(Env.getCtx(), M_Product_ID).getName().toUpperCase()
					+ "  is not in valid price list!. " + failMsg;
			JOptionPane.showMessageDialog(null, m_processMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
			throw new AdempiereException(m_processMsg);
		}
		return price.setScale(2, RoundingMode.CEILING);
	}

	int getC_BP_Group_ID(int hms_treatment_doc_id)
	{

		String sql = "select C_BP_Group_ID FROM hms_treatment_doc WHERE hms_treatment_doc_id =" + hms_treatment_doc_id;
		int C_BP_Group_ID = DB.getSQLValue(get_TrxName(), sql);
		return C_BP_Group_ID;
	}

	class MyIntFilter extends DocumentFilter
	{
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException
		{

			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.insert(offset, string);

			if (test(sb.toString()))
			{
				super.insertString(fb, offset, string, attr);
			} else
			{
				// warn the user and don't allow the insert
			}
		}

		private boolean test(String text)
		{
			try
			{
				// Integer.parseInt(text);
				Double.parseDouble(text);
				return true;
			} catch (NumberFormatException e)
			{
				if (text.trim().isEmpty())
					return true;
				return false;
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException
		{

			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.replace(offset, offset + length, text);

			if (test(sb.toString()))
			{
				super.replace(fb, offset, length, text, attrs);
			} else
			{
				// warn the user and don't allow the insert
			}

		}
	}

	private void validateFields()
	{
		JTextField[] jTextFields = { textFieldDays, textFieldUnits, textFieldFrequency, textFieldDosage };
		for (int i = 0; i < jTextFields.length; i++)
		{
			PlainDocument doc = (PlainDocument) jTextFields[i].getDocument();
			doc.setDocumentFilter(new MyIntFilter());
		}

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JTextField textFieldUnits;
	private JLabel label1;
	private JLabel label2;
	private JTextField textFieldFrequency;
	private JLabel label3;
	private JLabel label4;
	private JTextField textFieldDays;
	private JLabel label6;
	private JTextField textFieldDosage;
	private JLabel label5;
	private JTextField textFieldDescription;
	private JLabel label7;
	private JTextField textFieldPrice;
	private JTextField textFieldTotal;
	private JLabel label8;
	private JPanel buttonBar;
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
