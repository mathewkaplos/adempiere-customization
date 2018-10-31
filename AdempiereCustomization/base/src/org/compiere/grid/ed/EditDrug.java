/*
 * Created by JFormDesigner on Fri Oct 13 15:55:45 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.form.Billing;
import org.compiere.apps.form.VBilling;
import org.compiere.model.MColumn;
import org.compiere.model.MProduct;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;
import org.zenith.util.Price;
import org.zenith.util.Stock;

import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MSetup;
import zenith.model.MTreatmentDoc;
import zenith.util.DateUtil;

/**
 * @author Mathew Kipchumba
 */
public class EditDrug extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1559088242202376527L;

	int m_billID = 0;
	private ZLookup mProduct_ID;
	private VDate vDate;

	BigDecimal currentQty = Env.ZERO;
	BigDecimal currentAmount = Env.ZERO;
	MBilling billing = null;
	int M_Product_ID = 0;

	private BigDecimal unitPrice = Env.ZERO;
	Price price = null;
	Stock stockPharm = null;
	Stock stockMain = null;
	BigDecimal availableStock = Env.ZERO;

	private MTreatmentDoc doc = null;
	private boolean allowNegativeStock = false;
	private boolean isService = false;
	private boolean drugsIssuedOncePrescribed = false;
	private boolean reserveDrugs = false;
	private boolean inPatientReallTime = false;
	private boolean realtime_updateStock = false;

	private boolean admitted = false;

	public EditDrug(Frame owner, int billID)
	{
		super(owner, true);
		initComponents();
		init();
		textFieldDescription.setVisible(false);
		label9.setVisible(false);
		m_billID = billID;
		billing = new MBilling(Env.getCtx(), billID, null);
		currentQty = billing.getQty();
		currentAmount = billing.getLineNetAmt();
		M_Product_ID = billing.getM_Product_ID();
		populateWindow();
		addListeners();
		validateFields();
		addListeners2();

		doc = new MTreatmentDoc(Env.getCtx(), Billing.getHms_treatment_doc_ID(), null);
		MSetup setup = HmsSetup.getSetup();
		allowNegativeStock = setup.isallow_negative_stock();
		drugsIssuedOncePrescribed = setup.isdrug_issued_once_prescribed();
		reserveDrugs = setup.isreserve_drugs();
		inPatientReallTime = setup.isinpatient_realltime();
		realtime_updateStock = setup.isrealtime_update_stock();

		admitted = doc.isadmitted();

	}

	public EditDrug(Dialog owner, int billID)
	{
		super(owner, true);
		initComponents();
		init();
		textFieldDescription.setVisible(false);
		label9.setVisible(false);
		m_billID = billID;
		billing = new MBilling(Env.getCtx(), billID, null);
		currentQty = billing.getQty();
		currentAmount = billing.getLineNetAmt();
		M_Product_ID = billing.getM_Product_ID();
		populateWindow();
		addListeners();
		validateFields();
		addListeners2();
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
					int hms_treatment_doc_ID = Billing.getHms_treatment_doc_ID();
					int M_Product_ID = (int) mProduct_ID.getValue();

					price = null;
					price = new Price(M_Product_ID, hms_treatment_doc_ID);
					unitPrice = price.getPrice();

					BigDecimal qty = new BigDecimal(textFieldDosage.getText()).stripTrailingZeros();
					BigDecimal total = unitPrice.multiply(qty);
					textFieldPrice.setText(String.valueOf(unitPrice.toPlainString()));
					textFieldTotal.setText(String.valueOf(total.toPlainString()));

					stockPharm = null;
					stockMain = null;
					stockPharm = new Stock(M_Warehouse_ID, M_Locator_ID_pharm, M_Product_ID);
					stockMain = new Stock(M_Warehouse_ID, M_Locator_ID_main, M_Product_ID);
					availableStock = Env.ZERO;
					availableStock = stockPharm.getQtyAvailable();
					textAreaInfo.setText("");
					textAreaInfo.append(stockPharm.getStockInformation());

					isService = isService(M_Product_ID);

				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

		contentPanel.add(mProduct_ID, "cell 1 0,alignx left,growx 0,wmin 400");

		vDate = new VDate();
		// Date date = Calendar.getInstance().getTime();
		// vDate.setValue(date);
		contentPanel.add(vDate, "cell 1 11 ,alignx left,growx 0,width 150::300");

	}

	// int M_Product_ID = 1008064;
	int M_Warehouse_ID = 1000007;
	int M_Locator_ID_pharm = 1000007;
	int M_Locator_ID_main = 1000024;
	int M_AttributeSetInstance_ID = 0;

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
		if (bill.getdosage_description() != null && !bill.getdosage_description().trim().equals(""))
			textFieldDosageDescription.setText(bill.getdosage_description());
		checkBoxDischargeDrug.setSelected(bill.is_discharge_drug());
		vDate.setValue(bill.getCreated());
	}

	public static void main(String[] args)
	{
		EditDrug ed = new EditDrug((Frame) null, 0);
		ed.setVisible(true);
	}

	/**
	 * 
	 */
	private void addListeners()
	{
		JTextField[] jTextFields = { textFieldUnits, textFieldFrequency, textFieldDays };
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
											BigDecimal qty = new BigDecimal(textFieldDosage.getText())
													.stripTrailingZeros();
											BigDecimal total = unitPrice.multiply(qty);
											textFieldPrice.setText(String.valueOf(unitPrice.toPlainString()));
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

	/**
	 * 
	 */
	private void addListeners2()
	{
		JTextField[] jTextFields = { textFieldDosage };
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
										// textFieldDosage.setText(String.valueOf(qty));
										try
										{
											BigDecimal qty = new BigDecimal(textFieldDosage.getText())
													.stripTrailingZeros();
											BigDecimal total = unitPrice.multiply(qty);
											textFieldPrice.setText(String.valueOf(unitPrice.toPlainString()));
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
		if (M_Product_ID > 0)
		{
			if (admitted && !realtime_updateStock)
			{
				prescribe();
			} else
			{
				BigDecimal totalDose = new BigDecimal(textFieldDosage.getText());
				BigDecimal diffQty = availableStock.subtract(totalDose);

				if (diffQty.signum() == -1 && !isService && !allowNegativeStock)
				{
					JOptionPane.showMessageDialog(null,
							new JLabel("<html><h1><font color='red'>No Available Stock! <br>" + " Available: "
									+ availableStock + " </br> <br>" + "Requested: " + totalDose
									+ "</br></font></h1></html>"),
							"Not Prescribed!", JOptionPane.ERROR_MESSAGE);

				} else
				{

					// check if days exceeds days to expiry... to prevent
					// killing
					// someone
					if (drugExpires())
					{
						final int x = yesnocancel(
								"The selected product is expired or will expire before the dosage is completed. Do you want to continue?");
						if (x == 0)
						{

						} else
						{
							JOptionPane.showMessageDialog(null, "Cancelled..!", "Information Message", 1);
							return;
						}
					}
					prescribe();
				}
			}
		}
	}

	private boolean drugExpires()
	{
		if (stockPharm.daysToExpiry() == null)
			return false;
		int days = Integer.parseInt(textFieldDays.getText());
		int daysToExpiry = stockPharm.daysToExpiry();
		if (days > daysToExpiry)
			return true;

		return false;
	}

	private boolean isService(int M_Product_ID)
	{
		MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
		return product.isService();
	}

	private void prescribe()
	{
		int M_Product_ID = (int) mProduct_ID.getValue();
		int hms_treatment_doc_ID = Billing.getHms_treatment_doc_ID();
		// int C_BPartner_ID = Billing.getBPartner_ID();
		MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
		// bill.sethms_treatment_doc_ID(hms_treatment_doc_ID);
		// bill.setC_BPartner_ID(C_BPartner_ID);
		bill.setM_Product_ID(M_Product_ID);
		bill.setQty(new BigDecimal(textFieldDosage.getText()));
		bill.setPrice(unitPrice);
		bill.setLineNetAmt((new BigDecimal(textFieldDosage.getText())).multiply(unitPrice));
		bill.setTotalAmt((new BigDecimal(textFieldDosage.getText())).multiply(unitPrice));
		bill.setBalance((new BigDecimal(textFieldDosage.getText())).multiply(unitPrice));
		bill.setunits_per_freq(Integer.parseInt(textFieldUnits.getText()));
		bill.setfreq(Integer.parseInt(textFieldFrequency.getText()));
		bill.setdays(Integer.parseInt(textFieldDays.getText()));
		bill.setdosage(Integer.parseInt(textFieldDosage.getText()));
		bill.setDescription(textFieldUnits.getText());
		bill.setdosage_description(textFieldDosageDescription.getText());
		if (!isService)// not service
		{
			bill.setto_pharm(true);
			bill.setbill_group(3);
		} else
		{
			bill.setbill_group(4);
		}
		if (vDate.getValue() != null)
		{
			bill.setbill_date(DateUtil.getTimestamp(vDate.getValue()));
			// bill.setCreated(DateUtil.getTimestamp(vDate.getValue()));
		} else
			bill.setbill_date(new Timestamp(System.currentTimeMillis()));
		bill.setis_discharge_drug(checkBoxDischargeDrug.isSelected());
		if (drugsIssuedOncePrescribed || (inPatientReallTime && admitted))
		{
			if (realtime_updateStock)
			{
				BigDecimal qty = bill.getQty().subtract(currentQty);
				stockPharm.updateStock(qty).updateQtyOnHand();
			}
			bill.setissued(true);
		} else if (reserveDrugs)
		{
			BigDecimal qty = bill.getQty().subtract(currentQty);
			stockPharm.updateStock(qty).updateQtyReserved();
		}
		if (admitted)
			bill.setadmitted(true);
		bill.save();
		VBilling vb = new VBilling();
		vb.loadBPartner();
		JOptionPane.showMessageDialog(null, "Updated: Line updated Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		updateDoc(bill.getLineNetAmt().subtract(currentAmount));
		this.dispose();
	}

	private void updateDoc(BigDecimal amt)
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), VBilling.getHms_treatment_doc_ID(), null);
		doc.setto_pharm(true);
		doc.setpharm_done(false);
		doc.updateTotalOpenBalance(amt);
		doc.save();
		doc.updateDrugStatus();
	}

	private void deleteButtonActionPerformed(ActionEvent e)
	{
		int x = yesnocancel("Are you sure you want to delete this?");
		if (x == 0)
		{

			MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
			int treatID = bill.gethms_treatment_doc_ID();
			bill.delete(true);
			removeFromPharm(treatID);
			VBilling vb = new VBilling();
			vb.loadBPartner();
			JOptionPane.showMessageDialog(null, "Updated: Line deleted Successfully...", "Information Message",
					JOptionPane.INFORMATION_MESSAGE);
			updateDoc(currentAmount.negate());
			if (drugsIssuedOncePrescribed || (inPatientReallTime && admitted))
			{
				if (realtime_updateStock)
					stockPharm.updateStock(currentQty.negate()).updateQtyOnHand();
			} else if (reserveDrugs)
			{
				stockPharm.updateStock(currentQty.negate()).updateQtyReserved();
			}
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

	private void removeFromPharm(int treatID)
	{
		boolean hasPrescription = false;
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), treatID, null);
		MBilling[] bills = doc.getBills();
		for (int i = 0; i < bills.length; i++)
		{
			MBilling billing = bills[i];
			if (billing.is_prescription())
			{
				hasPrescription = true;
				break;
			}
		}
		if (!hasPrescription)
		{
			doc.setto_pharm(false);
			doc.save();
		}
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
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
		textFieldDosageDescription = new JTextField();
		label9 = new JLabel();
		textFieldDescription = new JTextField();
		label7 = new JLabel();
		textFieldPrice = new JTextField();
		textFieldTotal = new JTextField();
		label8 = new JLabel();
		label10 = new JLabel();
		scrollPane1 = new JScrollPane();
		textAreaInfo = new JTextArea();
		checkBoxDischargeDrug = new JCheckBox();
		label12 = new JLabel();
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
						"[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]0" + "[]" + "[]"));

				// ---- textFieldUnits ----
				textFieldUnits.setColumns(10);
				textFieldUnits.setText("1");
				contentPanel.add(textFieldUnits, "cell 1 1,alignx left,growx 0,wmin 150");

				// ---- label1 ----
				label1.setText("Product Name:");
				contentPanel.add(label1, "cell 0 0");

				// ---- label2 ----
				label2.setText("Units Per Freq:");
				contentPanel.add(label2, "cell 0 1");

				// ---- textFieldFrequency ----
				textFieldFrequency.setColumns(10);
				textFieldFrequency.setText("1");
				contentPanel.add(textFieldFrequency, "cell 1 2,alignx left,growx 0,wmin 150");

				// ---- label3 ----
				label3.setText("Frequency");
				contentPanel.add(label3, "cell 0 2");

				// ---- label4 ----
				label4.setText("Days:");
				contentPanel.add(label4, "cell 0 3");

				// ---- textFieldDays ----
				textFieldDays.setColumns(10);
				textFieldDays.setText("1");
				contentPanel.add(textFieldDays, "cell 1 3,alignx left,growx 0,wmin 150");

				// ---- label6 ----
				label6.setText("Quantity");
				contentPanel.add(label6, "cell 0 4");

				// ---- textFieldDosage ----
				textFieldDosage.setColumns(10);
				textFieldDosage.setText("1");
				textFieldDosage.setBackground(Color.pink);
				contentPanel.add(textFieldDosage, "cell 1 4,alignx left,growx 0,wmin 150");

				// ---- label5 ----
				label5.setText("Dosage");
				contentPanel.add(label5, "cell 0 5");

				// ---- textFieldDosageDescription ----
				textFieldDosageDescription.setColumns(10);
				contentPanel.add(textFieldDosageDescription, "cell 1 5,alignx left,growx 0,wmin 400");

				// ---- label9 ----
				label9.setText("Description");
				contentPanel.add(label9, "cell 0 6");
				contentPanel.add(textFieldDescription, "cell 1 6");

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

				// ---- label10 ----
				label10.setText("Stock Information");
				contentPanel.add(label10, "cell 0 9");

				// ======== scrollPane1 ========
				{

					// ---- textAreaInfo ----
					textAreaInfo.setRows(5);
					textAreaInfo.setEditable(false);
					textAreaInfo.setBackground(new Color(228, 228, 249));
					scrollPane1.setViewportView(textAreaInfo);
				}
				contentPanel.add(scrollPane1, "cell 1 9");

				// ---- checkBoxDischargeDrug ----
				checkBoxDischargeDrug.setText("Is Discharge Drug");
				contentPanel.add(checkBoxDischargeDrug, "cell 1 10");

				// ---- label12 ----
				label12.setText("Bill Date:");
				contentPanel.add(label12, "cell 0 11");
			}
			dialogPane.add(contentPanel, BorderLayout.WEST);

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
	// Generated using JFormDesigner Evaluation license - Mathew
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
	private JTextField textFieldDosageDescription;
	private JLabel label9;
	private JTextField textFieldDescription;
	private JLabel label7;
	private JTextField textFieldPrice;
	private JTextField textFieldTotal;
	private JLabel label8;
	private JLabel label10;
	private JScrollPane scrollPane1;
	private JTextArea textAreaInfo;
	private JCheckBox checkBoxDischargeDrug;
	private JLabel label12;
	private JPanel buttonBar;
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
