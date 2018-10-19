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
import javax.swing.text.DocumentFilter.FilterBypass;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.form.Billing;
import org.compiere.apps.form.OtherCharge;
import org.compiere.apps.form.VBilling;
import org.compiere.apps.form.VOtherCharge;
import org.compiere.grid.ed.NewDrug.MyIntFilter;
import org.compiere.model.MColumn;
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
import zenith.util.DateUtil;

/**
 * @author Mathew Kipchumba
 */
/**
 * @author Administrator
 *
 */
public class NewOtherCharges extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6616254534884094650L;
	private VLookup mProduct_ID;
	private VDate vDate;
	private int treatID = 0;

	/**
	 * @param Frame
	 *            owner
	 */
	public NewOtherCharges(Frame owner, int _treatID)
	{
		super(owner, true);
		initComponents();
		init();
		validateFields();
		treatID = _treatID;
	}

	/**
	 * @param Dialog
	 *            owner
	 */
	public NewOtherCharges(Dialog owner)
	{
		super(owner, true);
		initComponents();
		init();

	}

	private void init()
	{

		try
		{
			mProduct_ID = new VLookup("M_Product_ID", false, false, true,
					ZLookupFactory.get(Env.getCtx(), 0,
							MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_Product_ID), 18,
							Language.getBaseLanguage(), MProduct.COLUMNNAME_M_Product_ID, 1000033, false, ""));

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
							int M_Product_ID = (int) mProduct_ID.getValue();
							BigDecimal amt = doIt(M_Product_ID, treatID).stripTrailingZeros();
							BigDecimal qty = Env.ONE;
							BigDecimal total = amt.multiply(qty);
							textFieldTotal.setText(String.valueOf(total.toPlainString()));
						}
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPanel.add(mProduct_ID, "cell 4 1 5 1,alignx left,growx 0,width 250::500");
		vDate = new VDate();
		Date date = Calendar.getInstance().getTime();
		vDate.setValue(date);
		contentPanel.add(vDate, "cell 4 3 7 1,alignx left,growx 0,width 250::500");
	}

	public static void main(String[] args)
	{
		NewOtherCharges newDrug = new NewOtherCharges((Frame) null, 0);
		newDrug.pack();
		newDrug.setVisible(true);
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		prescribe();
	}

	private void prescribe()
	{
		int M_Product_ID = (int) mProduct_ID.getValue();
		int hms_treatment_doc_ID = OtherCharge.getHms_treatment_doc_ID();
		int C_BPartner_ID = OtherCharge.getBPartner_ID();
		MBilling bill = new MBilling(Env.getCtx(), 0, null);
		bill.sethms_treatment_doc_ID(hms_treatment_doc_ID);
		bill.setC_BPartner_ID(C_BPartner_ID);
		bill.setM_Product_ID(M_Product_ID);
		bill.setQty(Env.ONE);
		BigDecimal amt = new BigDecimal(textFieldTotal.getText());
		bill.setPrice(amt);
		bill.setTotalAmt(amt);
		bill.setLineNetAmt(amt);
		bill.setBalance(amt);
		bill.setis_othercharges(true);
		bill.setDescription(textFieldDescription.getText());
		bill.setbill_group(4);

		if (vDate.getValue() != null)
		{
			bill.setbill_date(DateUtil.getTimestamp(vDate.getValue()));
			bill.setCreated(DateUtil.getTimestamp(vDate.getValue()));
		} else
		{
			bill.setbill_date(new Timestamp(System.currentTimeMillis()));
		}
		bill.save();

		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), treatID, null);
		doc.updateTotalOpenBalance(bill.getLineNetAmt());
		doc.save();

		setBillMode(bill);
		VOtherCharge oc = new VOtherCharge();
		oc.loadBPartner();
		JOptionPane.showMessageDialog(null, "Saved: New Line created Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}

	private void setBillMode(MBilling billing)
	{
		MBilling bill = billing;
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), billing.gethms_treatment_doc_ID(), null);
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
		// Generated using JFormDesigner Evaluation license - Math Ew
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		label12 = new JLabel();
		label8 = new JLabel();
		textFieldTotal = new JTextField();
		label5 = new JLabel();
		textFieldDescription = new JTextField();
		buttonBar = new JPanel();
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
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"
								+ "[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

				// ---- label1 ----
				label1.setText("Service Name:");
				contentPanel.add(label1, "cell 0 1 13 1");

				// ---- label12 ----
				label12.setText("Bill Date:");
				contentPanel.add(label12, "cell 0 3 2 1");

				// ---- label8 ----
				label8.setText("Charge Amount:");
				contentPanel.add(label8, "cell 0 5 3 1");
				contentPanel.add(textFieldTotal, "cell 4 5 2 1,wmin 150");

				// ---- label5 ----
				label5.setText("Description:");
				contentPanel.add(label5, "cell 0 7 6 1");

				// ---- textFieldDescription ----
				textFieldDescription.setColumns(10);
				contentPanel.add(textFieldDescription, "cell 4 7 12 1,alignx left,growx 0,wmin 400");
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[button,fill]"
								+ "[button,fill]",
						// rows
						"[]" + "[]"));

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 14 0");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 15 0");
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
	// Generated using JFormDesigner Evaluation license - Math Ew
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JLabel label12;
	private JLabel label8;
	private JTextField textFieldTotal;
	private JLabel label5;
	private JTextField textFieldDescription;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	private void validateFields()
	{
		JTextField[] jTextFields = { textFieldTotal };
		for (int i = 0; i < jTextFields.length; i++)
		{
			PlainDocument doc = (PlainDocument) jTextFields[i].getDocument();
			doc.setDocumentFilter(new MyIntFilter());
		}

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

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}
}
