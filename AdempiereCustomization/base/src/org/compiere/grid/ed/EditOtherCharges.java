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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.form.OtherCharge;
import org.compiere.apps.form.VBilling;
import org.compiere.apps.form.VOtherCharge;
import org.compiere.model.MColumn;
import org.compiere.model.MProduct;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.Env;
import org.compiere.util.Language;
import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;
import zenith.util.DateUtil;

/**
 * @author Mathew Kipchumba
 */
/**
 * @author Administrator
 *
 */
public class EditOtherCharges extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6616254534884094650L;
	private VLookup mProduct_ID;
	private VDate vDate;

	int m_billID = 0;

	BigDecimal currentAmount = Env.ZERO;
	MBilling billing = null;

	/**
	 * @param Frame
	 *            owner
	 */
	public EditOtherCharges(Frame owner, int billID)
	{
		super(owner, true);
		initComponents();
		init();
		m_billID = billID;
		billing = new MBilling(Env.getCtx(), billID, null);
		currentAmount = billing.getLineNetAmt();
		populateWindow();
		validateFields();
	}

	private void populateWindow()
	{
		MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
		mProduct_ID.setValue(bill.getM_Product_ID());
		if (bill.getPrice() != null)
			textFieldTotal.setText(bill.getPrice().stripTrailingZeros().toPlainString());
		if (bill.getbill_date() != null)
			vDate.setValue(bill.getbill_date());
		if (bill.getDescription() != null)
			textFieldDescription.setText(bill.getDescription());
	}

	/**
	 * @param Dialog
	 *            owner
	 */
	public EditOtherCharges(Dialog owner)
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

		contentPanel.add(mProduct_ID, "cell 4 1 5 1,alignx left,growx 0,width 250::500");
		vDate = new VDate();
		// Date date = Calendar.getInstance().getTime();
		// vDate.setValue(date);
		contentPanel.add(vDate, "cell 4 3 7 1,alignx left,growx 0,width 250::500");
	}

	public static void main(String[] args)
	{
		EditOtherCharges newDrug = new EditOtherCharges((Frame) null, 0);
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
		MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
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
			bill.setbill_date(DateUtil.getTimestamp(vDate.getValue()));
		else
			bill.setbill_date(new Timestamp(System.currentTimeMillis()));
		bill.save();

		updateDoc(bill.getLineNetAmt().subtract(currentAmount));

		VOtherCharge oc = new VOtherCharge();
		oc.loadBPartner();
		JOptionPane.showMessageDialog(null, "Updated: Line updated Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}

	private void updateDoc(BigDecimal amt)
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), billing.gethms_treatment_doc_ID(), null);
		doc.updateTotalOpenBalance(amt);
		doc.save();
	}

	/**
	 * @param e
	 */
	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void deleteButtonActionPerformed(ActionEvent e)
	{
		int x = yesnocancel("Are you sure you want to delete this?");
		if (x == 0)
		{

			MBilling bill = new MBilling(Env.getCtx(), m_billID, null);
			bill.delete(true);
			updateDoc(currentAmount.negate());
			VOtherCharge voc = new VOtherCharge();
			voc.loadBPartner();
			JOptionPane.showMessageDialog(null, "Updated: Line deleted Successfully...", "Information Message",
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
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[button,fill]" + "[button,fill]",
						// rows
						"[]" + "[]"));

				// ---- deleteButton ----
				deleteButton.setText("Delete");
				deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
				buttonBar.add(deleteButton, "cell 1 0");

				// ---- okButton ----
				okButton.setText("SAVE");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 7 0");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 12 0");
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
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}

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

}
