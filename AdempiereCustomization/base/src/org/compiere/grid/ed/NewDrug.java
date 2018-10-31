/*
 * Created by JFormDesigner on Mon Oct 09 21:40:03 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
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
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.zenith.util.HmsSetup;
import org.zenith.util.Price;
import org.zenith.util.Stock;

import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MSetup;
import zenith.model.MTreatmentDoc;
import zenith.process.CreateHospitalDefaults;
import zenith.util.DateUtil;

/**
 * @author Mathew Kipchumba
 */
/**
 * @author Administrator
 *
 */
public class NewDrug extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6616254534884094650L;
	private VLookup mProduct_ID;
	private VDate vDate;

	private BigDecimal unitPrice = Env.ZERO;
	Price price = null;
	Stock stockPharm = null;
	Stock stockMain = null;
	BigDecimal availableStock = Env.ZERO;
	MTreatmentDoc doc = null;
	private boolean allowNegativeStock = false;
	private boolean isService = false;
	private boolean drugsIssuedOncePrescribed = false;
	private boolean reserveDrugs = false;
	private boolean inPatientReallTime = false;
	private boolean realtime_updateStock = false;

	private boolean admitted = false;

	/**
	 * @param Frame
	 *            owner
	 */
	public NewDrug(Frame owner)
	{
		super(owner, true);
		initComponents();
		init();
		textFieldDescription.setVisible(false);
		label9.setVisible(false);
		validateFields();
		addListeners();
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

	/**
	 * @param Dialog
	 *            owner
	 */
	public NewDrug(Dialog owner)
	{
		super(owner, true);
		initComponents();
		init();
		textFieldDescription.setVisible(false);
		label9.setVisible(false);
		validateFields();
		addListeners();
		addListeners2();
	}

	private void init()
	{

		try
		{
			mProduct_ID = new VLookup("M_Product_ID", false, false, true,
					ZLookupFactory.get(Env.getCtx(), 0,
							MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_Product_ID), 18,
							Language.getBaseLanguage(), MProduct.COLUMNNAME_M_Product_ID, 1000035, false, ""));
		} catch (Exception e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
					if (mProduct_ID.getValue() != null)
						if ((int) mProduct_ID.getValue() > 0)
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

						}
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPanel.add(mProduct_ID, "cell 2 1 5 1,alignx left,growx 0,width 400::1000");
		vDate = new VDate();
		Date date = Calendar.getInstance().getTime();
		vDate.setValue(date);
		contentPanel.add(vDate, "cell 3 15 ,alignx left,growx 0,width 150::300");

		//// init_Locator();
	}

	// int M_Product_ID = 1008064;
	int M_Warehouse_ID = 1000007; // pharmacy
	int M_Locator_ID = 1000007; // pharmacy
	int M_Locator_ID_pharm = CreateHospitalDefaults.getPharmacyLocatorID();
	int M_Locator_ID_main = CreateHospitalDefaults.getMainStoreLocatorID();;
	int M_AttributeSetInstance_ID = 0;

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

	public static void main(String[] args)
	{
		NewDrug newDrug = new NewDrug((Frame) null);
		newDrug.pack();
		newDrug.setVisible(true);
	}

	private static int yesnocancel(final String theMessage)
	{
		final int result = JOptionPane.showConfirmDialog(null, theMessage, "Alert", 1);
		return result;
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
		int C_BPartner_ID = Billing.getBPartner_ID();
		MBilling bill = new MBilling(Env.getCtx(), 0, null);
		bill.sethms_treatment_doc_ID(hms_treatment_doc_ID);
		bill.setC_BPartner_ID(C_BPartner_ID);
		bill.setM_Product_ID(M_Product_ID);
		bill.setQty(new BigDecimal(textFieldDosage.getText()));
		bill.setPrice(unitPrice);
		bill.setPriceActual(unitPrice);
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
		bill.setis_prescription(true);
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
				stockPharm.updateStock(bill.getQty()).updateQtyOnHand();
			bill.setissued(true);
		} else if (reserveDrugs)
		{
			stockPharm.updateStock(bill.getQty()).updateQtyReserved();
		}
		if (admitted)
			bill.setadmitted(true);
		bill.save();

		setBillMode(bill);
		VBilling vb = new VBilling();
		vb.loadBPartner();
		JOptionPane.showMessageDialog(null, "Saved: New Line created Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		updateDoc(bill.getLineNetAmt());
		this.dispose();
	}

	private void setBillMode(MBilling billing)
	{
		MBilling bill = billing;
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), billing.gethms_treatment_doc_ID(), get_TrxName());
		if (doc.getC_BP_Group_ID() == CreateHospitalDefaults.PATIENT_GROUP_ID_CASH)
		{
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_CASH);
		} else if (doc.getC_BP_Group_ID() == CreateHospitalDefaults.PATIENT_GROUP_ID_INSURANCE
				|| doc.getC_BP_Group_ID() == CreateHospitalDefaults.PATIENT_GROUP_ID_NHIF)
		{
			bill.setbill_mode(CreateHospitalDefaults.BILL_MODE_INSUARANCE);
		}
		if (billing.getbill_date() == null)
			bill.setbill_date(billing.getCreated());
		bill.save();
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

	/**
	 * @param e
	 */
	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		textFieldUnits = new JTextField();
		label3 = new JLabel();
		textFieldFrequency = new JTextField();
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
		label8 = new JLabel();
		textFieldTotal = new JTextField();
		scrollPane1 = new JScrollPane();
		textAreaInfo = new JTextArea();
		label13 = new JLabel();
		checkBoxDischargeDrug = new JCheckBox();
		label12 = new JLabel();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();

		// ======== this ========
		setMinimumSize(new Dimension(16, 30));
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{
			dialogPane.setPreferredSize(new Dimension(550, 450));

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
				contentPanel.setMinimumSize(new Dimension(523, 50));
				contentPanel.setPreferredSize(new Dimension(550, 400));
				contentPanel.setLayout(new MigLayout("insets dialog,hidemode 3",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]0" + "[fill]0",
						// rows
						"[]0" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]0"
								+ "[4]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0"
								+ "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0" + "[]0"
								+ "[]0" + "[]0" + "[]0" + "[]0" + "[]"));

				// ---- label1 ----
				label1.setText("Product Name:");
				contentPanel.add(label1, "cell 0 1 2 1");

				// ---- label2 ----
				label2.setText("Units Per Freq:");
				contentPanel.add(label2, "cell 0 2 2 1");

				// ---- textFieldUnits ----
				textFieldUnits.setColumns(10);
				textFieldUnits.setText("1");
				contentPanel.add(textFieldUnits, "cell 3 2 9 1,alignx left,growx 0,wmin 150");

				// ---- label3 ----
				label3.setText("Frequency");
				contentPanel.add(label3, "cell 0 4 2 1");

				// ---- textFieldFrequency ----
				textFieldFrequency.setColumns(10);
				textFieldFrequency.setText("1");
				contentPanel.add(textFieldFrequency, "cell 3 4 9 1,alignx left,growx 0,wmin 150");

				// ---- label4 ----
				label4.setText("Days:");
				contentPanel.add(label4, "cell 0 5 2 1");

				// ---- textFieldDays ----
				textFieldDays.setColumns(10);
				textFieldDays.setText("1");
				contentPanel.add(textFieldDays, "cell 3 5 9 1,alignx left,growx 0,wmin 150");

				// ---- label6 ----
				label6.setText("Quantity");
				contentPanel.add(label6, "cell 0 6");

				// ---- textFieldDosage ----
				textFieldDosage.setColumns(10);
				textFieldDosage.setText("1");
				textFieldDosage.setBackground(Color.pink);
				contentPanel.add(textFieldDosage, "cell 3 6 8 1,alignx left,growx 0,wmin 150");

				// ---- label5 ----
				label5.setText("Dosage");
				contentPanel.add(label5, "cell 0 7 2 1");
				contentPanel.add(textFieldDosageDescription, "cell 3 7 8 1");

				// ---- label9 ----
				label9.setText("Description");
				contentPanel.add(label9, "cell 0 8");

				// ---- textFieldDescription ----
				textFieldDescription.setColumns(10);
				contentPanel.add(textFieldDescription, "cell 3 8 8 1,alignx left,growx 0,wmin 400");

				// ---- label7 ----
				label7.setText("Unit Price");
				contentPanel.add(label7, "cell 0 9");

				// ---- textFieldPrice ----
				textFieldPrice.setEditable(false);
				contentPanel.add(textFieldPrice, "cell 3 9 8 1,wmin 150");

				// ---- label8 ----
				label8.setText("Total Amount");
				contentPanel.add(label8, "cell 0 10 2 1");

				// ---- textFieldTotal ----
				textFieldTotal.setEditable(false);
				contentPanel.add(textFieldTotal, "cell 3 10 8 1,wmin 150");

				// ======== scrollPane1 ========
				{

					// ---- textAreaInfo ----
					textAreaInfo.setRows(5);
					textAreaInfo.setBackground(new Color(228, 228, 249));
					textAreaInfo.setEditable(false);
					scrollPane1.setViewportView(textAreaInfo);
				}
				contentPanel.add(scrollPane1, "cell 3 11 9 2");

				// ---- label13 ----
				label13.setText("Stock Information");
				contentPanel.add(label13, "cell 0 12");

				// ---- checkBoxDischargeDrug ----
				checkBoxDischargeDrug.setText("Is Discharge Drug");
				contentPanel.add(checkBoxDischargeDrug, "cell 3 14");

				// ---- label12 ----
				label12.setText("Bill Date:");
				contentPanel.add(label12, "cell 0 15");
			}
			dialogPane.add(contentPanel, BorderLayout.WEST);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[button,fill]" + "[button,fill]",
						// rows
						"[]"));

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 10 0");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 11 0");
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
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
	private JLabel label2;
	private JTextField textFieldUnits;
	private JLabel label3;
	private JTextField textFieldFrequency;
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
	private JLabel label8;
	private JTextField textFieldTotal;
	private JScrollPane scrollPane1;
	private JTextArea textAreaInfo;
	private JLabel label13;
	private JCheckBox checkBoxDischargeDrug;
	private JLabel label12;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void actionPerformed(ActionEvent e)
	{

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

	private String get_TrxName()
	{
		return null;
	}

}