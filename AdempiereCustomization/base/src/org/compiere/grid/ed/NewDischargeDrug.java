/*
 * Created by JFormDesigner on Mon Oct 09 21:40:03 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.compiere.apps.AEnv;
import org.compiere.apps.form.Billing;
import org.compiere.apps.form.VBilling;
import org.compiere.model.MColumn;
import org.compiere.model.MLocator;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.python.modules.synchronize;
import org.zenith.util.HmsSetup;

import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;
import zenith.process.CreateHospitalDefaults;

/**
 * @author Mathew Kipchumba
 */
/**
 * @author Administrator
 *
 */
public class NewDischargeDrug extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6616254534884094650L;
	private VLookup mProduct_ID;
	private ZLookup mLocator_ID;
	private VDate vDate;

	/**
	 * @param Frame
	 *            owner
	 */
	public NewDischargeDrug(Frame owner)
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

	/**
	 * @param Dialog
	 *            owner
	 */
	public NewDischargeDrug(Dialog owner)
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
							BigDecimal amt = doIt(M_Product_ID, hms_treatment_doc_ID).stripTrailingZeros();
							BigDecimal qty = new BigDecimal(textFieldDosage.getText()).stripTrailingZeros();
							BigDecimal total = amt.multiply(qty);
							textFieldPrice.setText(String.valueOf(amt.toPlainString()));
							textFieldTotal.setText(String.valueOf(total.toPlainString()));

							String x = String.valueOf(getStock(M_Product_ID, M_Locator_ID_pharm));
							String y = String.valueOf(getStock(M_Product_ID, M_Locator_ID_main));
							textFieldStock.setText(x);
							textFieldStockMain.setText(y);
						}
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPanel.add(mProduct_ID, "cell 2 1 5 1,alignx left,growx 0,width 400::1000");
		//// init_Locator();
	}

	private void init_Locator()
	{
		mLocator_ID = new ZLookup("M_Locator_ID", false, false, true, ZLookupFactory.get(Env.getCtx(), 0, 0,
				MColumn.getColumn_ID(MLocator.Table_Name, MLocator.COLUMNNAME_M_Locator_ID), DisplayType.Table));
		mLocator_ID.setBackground(AdempierePLAF.getInfoBackground());
		mLocator_ID.addActionListener(new ZLookup() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1800241106974099101L;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (mLocator_ID.getValue() != null)
						if ((int) mLocator_ID.getValue() > 0)
						{
							/**
							 * int hms_treatment_doc_ID =
							 * Billing.getHms_treatment_doc_ID(); int
							 * M_Product_ID = (int) mProduct_ID.getValue();
							 * BigDecimal amt = doIt(M_Product_ID,
							 * hms_treatment_doc_ID).stripTrailingZeros();
							 * BigDecimal qty = new
							 * BigDecimal(textFieldDosage.getText()).stripTrailingZeros();
							 * BigDecimal total = amt.multiply(qty);
							 * textFieldPrice.setText(String.valueOf(amt.toPlainString()));
							 * textFieldTotal.setText(String.valueOf(total.toPlainString()));
							 * 
							 * String x = String.valueOf(getStock(M_Product_ID,
							 * M_Locator_ID_pharm)); String y =
							 * String.valueOf(getStock(M_Product_ID,
							 * M_Locator_ID_main)); textFieldStock.setText(x);
							 * textFieldStockMain.setText(y);
							 */
						}
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPanel.add(mLocator_ID, "cell 2 3 5 1,alignx left,growx 0,width 200::400");

	}

	// int M_Product_ID = 1008064;
	int M_Warehouse_ID = 1000007;
	int M_Locator_ID_pharm = CreateHospitalDefaults.getPharmacyLocatorID();
	int M_Locator_ID_main = CreateHospitalDefaults.getMainStoreLocatorID();;
	int M_AttributeSetInstance_ID = 0;

	/*
	 * 
	 */
	private BigDecimal getStock(int M_Product_ID, int M_Locator_ID)
	{
		return MStorage.getQtyAvailable(M_Warehouse_ID, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
				get_TrxName());
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
											int hms_treatment_doc_ID = Billing.getHms_treatment_doc_ID();
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
											int hms_treatment_doc_ID = Billing.getHms_treatment_doc_ID();
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

	public static void main(String[] args)
	{
		NewDischargeDrug newDrug = new NewDischargeDrug((Frame) null);
		newDrug.pack();
		newDrug.setVisible(true);
	}

	private void okButtonActionPerformed(ActionEvent e)
	{

		int M_Product_ID = (int) mProduct_ID.getValue();
		if (M_Product_ID > 0)
		{
			BigDecimal availableStock = new BigDecimal(textFieldStock.getText());
			BigDecimal totalDose = new BigDecimal(textFieldDosage.getText());
			BigDecimal diffQty = availableStock.subtract(totalDose);

			if (diffQty.signum() == -1 && !isService(M_Product_ID) && !HmsSetup.allowNegativeStock())
			{
				JOptionPane
						.showMessageDialog(null,
								new JLabel("<html><h1><font color='red'>No Available Stock! <br>" + " Available: "
										+ availableStock + " </br> <br>" + "Requested: " + totalDose
										+ "</br></font></h1></html>"),
								"Not Prescribed!", JOptionPane.ERROR_MESSAGE);

			} else
			{
				prescribe();
			}
		}
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
		BigDecimal amt = doIt(M_Product_ID, hms_treatment_doc_ID);
		bill.setPrice(amt);
		bill.setPriceActual(amt);
		bill.setLineNetAmt((new BigDecimal(textFieldDosage.getText())).multiply(amt));
		bill.setTotalAmt((new BigDecimal(textFieldDosage.getText())).multiply(amt));
		bill.setBalance((new BigDecimal(textFieldDosage.getText())).multiply(amt));
		bill.setunits_per_freq(Integer.parseInt(textFieldUnits.getText()));
		bill.setfreq(Integer.parseInt(textFieldFrequency.getText()));
		bill.setdays(Integer.parseInt(textFieldDays.getText()));
		bill.setdosage(Integer.parseInt(textFieldDosage.getText()));
		bill.setDescription(textFieldUnits.getText());
		bill.setdosage_description(textFieldDosageDescription.getText());
		if (!isService(M_Product_ID))// not service
		{
			bill.setto_pharm(true);
			bill.setbill_group(3);
		} else
		{
			bill.setbill_group(4);
		}
		bill.setis_prescription(true);

		bill.save();
		setBillMode(bill);
		VBilling vb = new VBilling();
		vb.loadBPartner();
		JOptionPane.showMessageDialog(null, "Saved: New Line created Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		updateDoc();
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

	private void updateDoc()
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), VBilling.getHms_treatment_doc_ID(), null);
		doc.setto_pharm(true);
		doc.setpharm_done(false);
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
		// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
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
		label10 = new JLabel();
		textFieldStock = new JTextField();
		label11 = new JLabel();
		textFieldStockMain = new JTextField();
		buttonBar = new JPanel();
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
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"
								+ "[]" + "[]" + "[]"));

				// ---- label1 ----
				label1.setText("Product Name:");
				contentPanel.add(label1, "cell 0 1 2 1");

				// ---- label2 ----
				label2.setText("Units Per Freq:");
				contentPanel.add(label2, "cell 0 4 2 1");

				// ---- textFieldUnits ----
				textFieldUnits.setColumns(10);
				textFieldUnits.setText("1");
				contentPanel.add(textFieldUnits, "cell 3 4 10 1,alignx left,growx 0,wmin 150");

				// ---- label3 ----
				label3.setText("Frequency");
				contentPanel.add(label3, "cell 0 5 2 1");

				// ---- textFieldFrequency ----
				textFieldFrequency.setColumns(10);
				textFieldFrequency.setText("1");
				contentPanel.add(textFieldFrequency, "cell 3 5 10 1,alignx left,growx 0,wmin 150");

				// ---- label4 ----
				label4.setText("Days:");
				contentPanel.add(label4, "cell 0 6 2 1");

				// ---- textFieldDays ----
				textFieldDays.setColumns(10);
				textFieldDays.setText("1");
				contentPanel.add(textFieldDays, "cell 3 6 10 1,alignx left,growx 0,wmin 150");

				// ---- label6 ----
				label6.setText("Quantity");
				contentPanel.add(label6, "cell 0 7");

				// ---- textFieldDosage ----
				textFieldDosage.setColumns(10);
				textFieldDosage.setText("1");
				contentPanel.add(textFieldDosage, "cell 3 7 7 1,alignx left,growx 0,wmin 150");

				// ---- label5 ----
				label5.setText("Dosage");
				contentPanel.add(label5, "cell 0 9 2 1");
				contentPanel.add(textFieldDosageDescription, "cell 3 9 14 1");

				// ---- label9 ----
				label9.setText("Description");
				contentPanel.add(label9, "cell 0 10");

				// ---- textFieldDescription ----
				textFieldDescription.setColumns(10);
				contentPanel.add(textFieldDescription, "cell 3 10 14 1,alignx left,growx 0,wmin 400");

				// ---- label7 ----
				label7.setText("Unit Price");
				contentPanel.add(label7, "cell 0 12");

				// ---- textFieldPrice ----
				textFieldPrice.setEditable(false);
				contentPanel.add(textFieldPrice, "cell 3 12 7 1,wmin 150");

				// ---- label8 ----
				label8.setText("Total Amount");
				contentPanel.add(label8, "cell 0 13 2 1");

				// ---- textFieldTotal ----
				textFieldTotal.setEditable(false);
				contentPanel.add(textFieldTotal, "cell 3 13 7 1,wmin 150");

				// ---- label10 ----
				label10.setText("Pharmacy Stock");
				contentPanel.add(label10, "cell 0 14");

				// ---- textFieldStock ----
				textFieldStock.setEditable(false);
				contentPanel.add(textFieldStock, "cell 3 14 7 1");

				// ---- label11 ----
				label11.setText("Main Store Stock");
				contentPanel.add(label11, "cell 0 15 1 2");

				// ---- textFieldStockMain ----
				textFieldStockMain.setEditable(false);
				contentPanel.add(textFieldStockMain, "cell 3 15 7 1");
			}
			dialogPane.add(contentPanel, BorderLayout.WEST);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[button,fill]" + "[button,fill]",
						// rows
						"[]" + "[]"));

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
	// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
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
	private JLabel label10;
	private JTextField textFieldStock;
	private JLabel label11;
	private JTextField textFieldStockMain;
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

	int getC_BP_Group_ID(int hms_treatment_doc_id)
	{

		String sql = "select C_BP_Group_ID FROM hms_treatment_doc WHERE hms_treatment_doc_id =" + hms_treatment_doc_id;
		int C_BP_Group_ID = DB.getSQLValue(get_TrxName(), sql);
		return C_BP_Group_ID;
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
}
