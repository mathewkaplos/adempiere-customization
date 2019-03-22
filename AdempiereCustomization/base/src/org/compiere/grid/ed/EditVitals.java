/*
 * Created by JFormDesigner on Sun Oct 08 10:39:13 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.form.Triage;
import org.compiere.apps.form.VTriage;
import org.compiere.grid.ed.NewVitals.MyIntFilter;
import org.compiere.swing.CDialog;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MTreatmentDoc;
import zenith.model.MVital;

/**
 * @author Mathew Kipchumba
 */
public class EditVitals extends CDialog implements FormPanel, ActionListener, TableModelListener, VetoableChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753421357956129662L;
	int m_vitals_ID = 0;
	int Hms_treatment_doc_ID = 0;

	public EditVitals(Frame owner, int vitalsID, int treatID)
	{
		super(owner, true);
		initComponents();
		m_vitals_ID = vitalsID;
		Hms_treatment_doc_ID = treatID;
		populateWindow();
		validateFill();
		addBMIListeners();
	}

	public EditVitals(Dialog owner)
	{
		super(owner);
		initComponents();
		populateWindow();
		validateFill();
		addBMIListeners();
	}

	public static void main(String arg[])
	{
		EditVitals i = new EditVitals((Frame) null, 0, 0);
		i.pack();
		i.setVisible(true);
	}

	/**
	 * 
	 */
	private void addBMIListeners()
	{
		JTextField[] jTextFields = { textFieldWeight, textFieldHeight };
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
						if ((jTextFields[0].getText().trim() != null && !jTextFields[0].getText().trim().equals(""))
								&& (jTextFields[1].getText().trim() != null
										&& !jTextFields[1].getText().trim().equals("")))
						{
							if (Integer.parseInt(jTextField.getText()) <= 0)
							{
								JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0",
										"Error Massage", JOptionPane.ERROR_MESSAGE);
							} else
							{
								String Weight = textFieldWeight.getText();
								String Height = textFieldHeight.getText();
								double w = new Double(Weight);
								double h = new Double(Height);
								double h2 = (h / 100) * (h / 100);
								double bmi = w / h2;

								DecimalFormat df = new DecimalFormat("#.00");
								String bmiFormated = df.format(bmi);

								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run()
									{
										textFieldBMI.setText(bmiFormated);

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

	private void populateWindow()
	{
		MVital mVital = new MVital(Env.getCtx(), m_vitals_ID, null);
		if (mVital.getbmi() != null)
			textFieldBMI.setText(mVital.getbmi().stripTrailingZeros().toPlainString());
		if (mVital.getheight() != null)
			textFieldHeight.setText(mVital.getheight().stripTrailingZeros().toPlainString());
		if (mVital.getWeight() != null)
			textFieldWeight.setText(mVital.getWeight().stripTrailingZeros().toPlainString());
		if (mVital.getbp_systolic() > 0)
			textFieldSystolic.setText(String.valueOf(mVital.getbp_systolic()));
		if (mVital.getrespiratory() > 0)
			textFieldRespiratory.setText(String.valueOf(mVital.getrespiratory()));
		if (mVital.getptemp() != null)
			textFieldTemperature.setText(mVital.getptemp().stripTrailingZeros().toPlainString());
		if (mVital.getpulse() > 0)
			textFieldPulse.setText(String.valueOf(mVital.getpulse()));
		if (mVital.getbp_diastolic() > 0)
			textFieldDiastolic.setText(String.valueOf(mVital.getbp_diastolic()));
		if (mVital.getfhr() > 0)
			textFieldFHR.setText(String.valueOf(mVital.getfhr()));
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		// int BPartner_ID = Triage.getBPartner_ID();

		MVital mVital = new MVital(Env.getCtx(), m_vitals_ID, null);
		mVital.sethms_treatment_doc_ID(Hms_treatment_doc_ID);
		if (!textFieldSystolic.getText().trim().isEmpty())
			mVital.setbp_systolic(new Double(textFieldSystolic.getText()).intValue());
		if (!textFieldDiastolic.getText().trim().isEmpty())
			mVital.setbp_diastolic(new Double(textFieldDiastolic.getText()).intValue());
		if (!textFieldRespiratory.getText().trim().isEmpty())
			mVital.setrespiratory(new Double(textFieldRespiratory.getText()).intValue());
		if (!textFieldPulse.getText().trim().isEmpty())
			mVital.setpulse(new Double(textFieldPulse.getText()).intValue());
		if (!textFieldTemperature.getText().trim().isEmpty())
			mVital.setptemp(new BigDecimal(textFieldTemperature.getText()));
		if (!textFieldWeight.getText().trim().isEmpty())
			mVital.setWeight(new BigDecimal(textFieldWeight.getText()));
		if (!textFieldHeight.getText().trim().isEmpty())
			mVital.setheight(new BigDecimal(textFieldHeight.getText()));
		if (!textFieldBMI.getText().trim().isEmpty())
			mVital.setbmi(new BigDecimal(textFieldBMI.getText()));
		if (!textFieldFHR.getText().trim().isEmpty())
			mVital.setfhr(new Double(textFieldFHR.getText()).intValue());
		mVital.save();
		String message = "Vitals Updated Successfully...";
		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
		updateDoc();
		this.dispose();

		VTriage vTriage = new VTriage();
		vTriage.LoadBP();
	}

	private void updateDoc()
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), Hms_treatment_doc_ID, null);
		doc.settriage_done(true);
		doc.setvitals_done(true);
		doc.save();

	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.setVisible(false);
		this.dispose();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		contentPanel2 = new JPanel();
		label3 = new JLabel();
		label4 = new JLabel();
		textFieldSystolic = new JTextField();
		label5 = new JLabel();
		textFieldDiastolic = new JTextField();
		label6 = new JLabel();
		textFieldPulse = new JTextField();
		label7 = new JLabel();
		textFieldTemperature = new JTextField();
		label8 = new JLabel();
		textFieldRespiratory = new JTextField();
		label9 = new JLabel();
		textFieldWeight = new JTextField();
		label10 = new JLabel();
		textFieldHeight = new JTextField();
		label11 = new JLabel();
		textFieldBMI = new JTextField();
		label1 = new JLabel();
		textFieldFHR = new JTextField();
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
						"[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]"));

				// ======== contentPanel2 ========
				{
					contentPanel2.setLayout(new MigLayout("insets dialog,hidemode 3",
							// columns
							"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
									+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
									+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
							// rows
							"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

					// ---- label3 ----
					label3.setText("EDIT VITAL SIGNS ");
					label3.setFont(new Font("Tahoma", Font.ITALIC, 14));
					contentPanel2.add(label3, "cell 1 0 2 1");

					// ---- label4 ----
					label4.setText("Systolic");
					contentPanel2.add(label4, "cell 0 3");

					// ---- textFieldSystolic ----
					textFieldSystolic.setColumns(10);
					contentPanel2.add(textFieldSystolic, "cell 1 3");

					// ---- label5 ----
					label5.setText("Diastolic");
					contentPanel2.add(label5, "cell 2 3");

					// ---- textFieldDiastolic ----
					textFieldDiastolic.setColumns(10);
					contentPanel2.add(textFieldDiastolic, "cell 3 3");

					// ---- label6 ----
					label6.setText("Pulse");
					contentPanel2.add(label6, "cell 0 4");

					// ---- textFieldPulse ----
					textFieldPulse.setColumns(10);
					contentPanel2.add(textFieldPulse, "cell 1 4");

					// ---- label7 ----
					label7.setText("Temperature");
					contentPanel2.add(label7, "cell 2 4");

					// ---- textFieldTemperature ----
					textFieldTemperature.setColumns(10);
					contentPanel2.add(textFieldTemperature, "cell 3 4");

					// ---- label8 ----
					label8.setText("Respiratory");
					contentPanel2.add(label8, "cell 0 5");

					// ---- textFieldRespiratory ----
					textFieldRespiratory.setColumns(10);
					contentPanel2.add(textFieldRespiratory, "cell 1 5");

					// ---- label9 ----
					label9.setText("Weight (KG)");
					contentPanel2.add(label9, "cell 2 5");

					// ---- textFieldWeight ----
					textFieldWeight.setColumns(10);
					contentPanel2.add(textFieldWeight, "cell 3 5");

					// ---- label10 ----
					label10.setText("Height (CM)");
					contentPanel2.add(label10, "cell 0 6");

					// ---- textFieldHeight ----
					textFieldHeight.setColumns(10);
					contentPanel2.add(textFieldHeight, "cell 1 6");

					// ---- label11 ----
					label11.setText("BMI");
					contentPanel2.add(label11, "cell 2 6");

					// ---- textFieldBMI ----
					textFieldBMI.setColumns(10);
					textFieldBMI.setEditable(false);
					contentPanel2.add(textFieldBMI, "cell 3 6");

					// ---- label1 ----
					label1.setText("FHR (b/m)");
					contentPanel2.add(label1, "cell 0 7");

					// ---- textFieldFHR ----
					textFieldFHR.setColumns(10);
					contentPanel2.add(textFieldFHR, "cell 1 7");
				}
				contentPanel.add(contentPanel2, "cell 0 0");
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[button,fill]" + "[button,fill]",
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
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	private void validateFill()
	{
		JTextField[] jTextFields = { textFieldBMI, textFieldHeight, textFieldWeight, textFieldSystolic,
				textFieldRespiratory, textFieldTemperature, textFieldPulse, textFieldDiastolic, textFieldFHR };
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
	private JPanel contentPanel2;
	private JLabel label3;
	private JLabel label4;
	private JTextField textFieldSystolic;
	private JLabel label5;
	private JTextField textFieldDiastolic;
	private JLabel label6;
	private JTextField textFieldPulse;
	private JLabel label7;
	private JTextField textFieldTemperature;
	private JLabel label8;
	private JTextField textFieldRespiratory;
	private JLabel label9;
	private JTextField textFieldWeight;
	private JLabel label10;
	private JTextField textFieldHeight;
	private JLabel label11;
	private JTextField textFieldBMI;
	private JLabel label1;
	private JTextField textFieldFHR;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void init(int WindowNo, FormFrame frame)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("some action performed");
		if (e.getSource().equals(okButton))
		{
			System.out.println("hey button OOKay");
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

		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException
		{
			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.delete(offset, offset + length);

			if (test(sb.toString()))
			{
				super.remove(fb, offset, length);
			} else
			{
				// warn the user and don't allow the insert
			}

		}
	}
}
