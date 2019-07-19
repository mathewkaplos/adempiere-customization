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
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import org.compiere.apps.form.VTriage;
import org.compiere.model.MBPartner;
import org.compiere.swing.CDialog;
import org.compiere.util.DB;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MTreatmentDoc;
import zenith.model.MVital;
import zenith.model.VitalSignsSetup;

/**
 * @author Mathew Kipchumba
 */
public class NewVitals extends CDialog implements ActionListener, TableModelListener, VetoableChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753421357956129662L;
	int Hms_treatment_doc_ID = 0;
	MBPartner bp = null;
	MTreatmentDoc doc = null;

	public NewVitals(Frame owner, int treaID)
	{
		super(owner, true);
		Hms_treatment_doc_ID = treaID;
		initComponents();
		validateFields();
		addBMIListeners();
		doc = new MTreatmentDoc(Env.getCtx(), Hms_treatment_doc_ID, null);
		bp = new MBPartner(Env.getCtx(), doc.getC_BPartner_ID(), null);
	}

	public NewVitals(Frame owner, MTreatmentDoc doc, MBPartner bp)
	{
		super(owner, true);
		Hms_treatment_doc_ID = doc.get_ID();
		initComponents();
		this.setTitle(doc.getName());
		validateFields();
		addBMIListeners();
		this.doc = doc;
		this.bp = bp;
	}

	public NewVitals(Dialog owner, int treaID)
	{
		super(owner);
		Hms_treatment_doc_ID = treaID;
		initComponents();
		validateFields();
		addBMIListeners();
	}

	public static void main(String arg[])
	{
		NewVitals i = new NewVitals((Frame) null, 0);
		i.pack();
		i.setVisible(true);
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		if (vitalsOk())
		{
			save();
		}
	}

	private VitalSignsSetup vitalSignsSetup = null;

	private boolean vitalsOk()
	{
		boolean validFields = true;
		// systolic
		if (!fieldOk(1000000, textFieldSystolic))
		{
			textFieldSystolic.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldSystolic.setBorder(new JTextField().getBorder());
		}
		// diastolic
		if (!fieldOk(1000001, textFieldDiastolic))
		{
			textFieldDiastolic.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldDiastolic.setBorder(new JTextField().getBorder());
		}
		// pulse
		if (!fieldOk(1000002, textFieldPulse))
		{
			textFieldPulse.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldPulse.setBorder(new JTextField().getBorder());
		}
		// temperature
		if (!fieldOk(1000003, textFieldTemperature))
		{
			textFieldTemperature.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldTemperature.setBorder(new JTextField().getBorder());
		}
		// Respiratory
		if (!fieldOk(1000004, textFieldRespiratory))
		{
			textFieldRespiratory.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldRespiratory.setBorder(new JTextField().getBorder());
		}
		// Weight
		if (!fieldOk(1000005, textFieldWeight))
		{
			textFieldWeight.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldWeight.setBorder(new JTextField().getBorder());
		}
		// height
		if (!fieldOk(1000006, textFieldHeight))
		{
			textFieldHeight.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldHeight.setBorder(new JTextField().getBorder());
		}
		// FHR
		if (!fieldOk(1000007, textFieldFHR))
		{
			textFieldFHR.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldFHR.setBorder(new JTextField().getBorder());
		}
		// SPO2
		if (!fieldOk(1000008, textFieldSPO2))
		{
			textFieldSPO2.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldSPO2.setBorder(new JTextField().getBorder());
		}
		// Heart Rate
		if (!fieldOk(1000009, textFieldHeartRate))
		{
			textFieldHeartRate.setBorder(new LineBorder(Color.red));
			validFields = false;
		} else
		{
			textFieldHeartRate.setBorder(new JTextField().getBorder());
		}

		return validFields;
	}

	private boolean fieldOk(int vital_setup_id, JTextField textField)
	{
		vitalSignsSetup = new VitalSignsSetup(Env.getCtx(), vital_setup_id, null);
		String mandatoryLogic = vitalSignsSetup.getMandatoryLogic();
		String sql = "SELECT COUNT(*) FROM adempiere.C_BPartner bp WHERE bp.C_BPartner_ID=" + bp.get_ID() + "  AND "
				+ mandatoryLogic;
		int count = DB.getSQLValue(null, sql);
		if (count == 1)// is mandatory
		{
			if (textField.getText().trim().isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	private void save()
	{
		MVital mVital = new MVital(Env.getCtx(), 0, null);
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
		if (!textFieldSPO2.getText().trim().isEmpty())
			mVital.setspo2(new Double(textFieldSPO2.getText()).intValue());
		if (!textFieldHeartRate.getText().trim().isEmpty())
			mVital.setheart_rate(new Double(textFieldHeartRate.getText()).intValue());
		mVital.save();
		if (doc != null && bp != null && 1 == 2)
		{
			String s = new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(doc.getCreated());
			bp.setNotes("Vitals entered on: " + s);
			bp.save();
		}
		doc.settriage_done(true);
		doc.save();
		String message = "Vitals Saved Successfully...";
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
		this.dispose();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license -
		// mathew359722@gmail.com
		dialogPane = new JPanel();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
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
		label12 = new JLabel();
		textFieldSPO2 = new JTextField();
		label2 = new JLabel();
		textFieldHeartRate = new JTextField();

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

			// ======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout("insets dialog,alignx right",
						// columns
						"[button,fill]" + "[button,fill]" + "[button,fill]",
						// rows
						null));

				// ---- okButton ----
				okButton.setText("Save");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 1 0");

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 2 0");
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);

			// ======== contentPanel2 ========
			{
				contentPanel2.setLayout(new MigLayout("insets dialog,hidemode 3",
						// columns
						"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

				// ---- label3 ----
				label3.setText("VITAL SIGNS:- New Record");
				label3.setFont(new Font("Tahoma", Font.ITALIC, 14));
				contentPanel2.add(label3, "cell 2 0 4 1");

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
				label1.setText("FHR (bpm)");
				contentPanel2.add(label1, "cell 0 7");

				// ---- textFieldFHR ----
				textFieldFHR.setColumns(10);
				contentPanel2.add(textFieldFHR, "cell 1 7");

				// ---- label12 ----
				label12.setText("SPO2(%)");
				contentPanel2.add(label12, "cell 2 7");

				// ---- textFieldSPO2 ----
				textFieldSPO2.setColumns(10);
				contentPanel2.add(textFieldSPO2, "cell 3 7");

				// ---- label2 ----
				label2.setText("Heart Rate(bpm)");
				contentPanel2.add(label2, "cell 0 8");

				// ---- textFieldHeartRate ----
				textFieldHeartRate.setColumns(10);
				contentPanel2.add(textFieldHeartRate, "cell 1 8");
			}
			dialogPane.add(contentPanel2, BorderLayout.NORTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	private void validateFields()
	{
		JTextField[] jTextFields = { textFieldBMI, textFieldHeight, textFieldWeight, textFieldSystolic,
				textFieldRespiratory, textFieldTemperature, textFieldPulse, textFieldDiastolic, textFieldFHR,
				textFieldHeartRate, textFieldSPO2 };
		for (int i = 0; i < jTextFields.length; i++)
		{
			PlainDocument doc = (PlainDocument) jTextFields[i].getDocument();
			doc.setDocumentFilter(new MyIntFilter());
		}

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - mathew359722@gmail.com
	private JPanel dialogPane;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
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
	private JLabel label12;
	private JTextField textFieldSPO2;
	private JLabel label2;
	private JTextField textFieldHeartRate;
	// JFormDesigner - End of variables declaration //GEN-END:variables

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

		/**
		 * Test if is valid number
		 * 
		 * @param text
		 * @return true if valid number of empty , otherwise false
		 * @author Matthew Kipchumba
		 */
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
