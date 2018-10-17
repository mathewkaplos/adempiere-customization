/*
 * Created by JFormDesigner on Wed Nov 08 11:51:48 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.compiere.apps.form.VDoctorNotes;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.DoctorNotes;

/**
 * @author Mathew Kipchumba
 */
public class NewNote extends JDialog
{
	private int treatID;

	public NewNote(Frame owner, int _treatID)
	{
		super(owner, true);
		treatID = _treatID;
		initComponents();
	}

	public NewNote(Dialog owner, int _treatID)
	{
		super(owner, true);
		treatID = _treatID;
		initComponents();
	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		DoctorNotes doctorNotes = new DoctorNotes(Env.getCtx(), 0, null);
		doctorNotes.sethms_treatment_doc_ID(treatID);

		doctorNotes.setpresent_complain(textAreaPesentComplain.getText());
		doctorNotes.sethpi(textAreaHPI.getText());
		doctorNotes.setpmsh(textAreaPMSH.getText());
		doctorNotes.setpre_medication(textAreaPreMedication.getText());
		doctorNotes.setgeneral_examination(textAreaGeneralExamination.getText());
		doctorNotes.setlocal_examination(textAreaLocalExamination.getText());
		doctorNotes.setimpression(textAreaImpression.getText());

		doctorNotes.save();
		JOptionPane.showMessageDialog(null, "Saved Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		VDoctorNotes dn = new VDoctorNotes();
		dn.loadBPartner();

	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		textAreaPesentComplain = new JTextArea();
		label1 = new JLabel();
		label5 = new JLabel();
		scrollPane5 = new JScrollPane();
		textAreaHPI = new JTextArea();
		scrollPane2 = new JScrollPane();
		textAreaPMSH = new JTextArea();
		label2 = new JLabel();
		scrollPane3 = new JScrollPane();
		textAreaPreMedication = new JTextArea();
		label3 = new JLabel();
		scrollPane4 = new JScrollPane();
		textAreaGeneralExamination = new JTextArea();
		label4 = new JLabel();
		label6 = new JLabel();
		scrollPane6 = new JScrollPane();
		textAreaLocalExamination = new JTextArea();
		label7 = new JLabel();
		scrollPane7 = new JScrollPane();
		textAreaImpression = new JTextArea();
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
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"
								+ "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"));

				// ======== scrollPane1 ========
				{
					scrollPane1.setMinimumSize(new Dimension(300, 24));

					// ---- textAreaPesentComplain ----
					textAreaPesentComplain.setMinimumSize(new Dimension(300, 20));
					textAreaPesentComplain.setRows(4);
					scrollPane1.setViewportView(textAreaPesentComplain);
				}
				contentPanel.add(scrollPane1, "cell 11 0 32 5");

				// ---- label1 ----
				label1.setText("Chief Complain");
				contentPanel.add(label1, "cell 3 1 7 1");

				// ---- label5 ----
				label5.setText("H.P.I");
				contentPanel.add(label5, "cell 5 8");

				// ======== scrollPane5 ========
				{

					// ---- textAreaHPI ----
					textAreaHPI.setMinimumSize(new Dimension(300, 20));
					textAreaHPI.setRows(4);
					scrollPane5.setViewportView(textAreaHPI);
				}
				contentPanel.add(scrollPane5, "cell 11 8 32 1");

				// ======== scrollPane2 ========
				{
					scrollPane2.setMinimumSize(new Dimension(300, 24));

					// ---- textAreaPMSH ----
					textAreaPMSH.setRows(4);
					textAreaPMSH.setMinimumSize(new Dimension(300, 20));
					scrollPane2.setViewportView(textAreaPMSH);
				}
				contentPanel.add(scrollPane2, "cell 11 10 32 6");

				// ---- label2 ----
				label2.setText("P.M.S.H");
				contentPanel.add(label2, "cell 3 13 8 1");

				// ======== scrollPane3 ========
				{
					scrollPane3.setMinimumSize(new Dimension(300, 24));

					// ---- textAreaPreMedication ----
					textAreaPreMedication.setRows(4);
					textAreaPreMedication.setMinimumSize(new Dimension(300, 20));
					scrollPane3.setViewportView(textAreaPreMedication);
				}
				contentPanel.add(scrollPane3, "cell 11 16 32 4");

				// ---- label3 ----
				label3.setText("Pre-Medication");
				contentPanel.add(label3, "cell 3 17 8 1");

				// ======== scrollPane4 ========
				{
					scrollPane4.setMinimumSize(new Dimension(300, 24));

					// ---- textAreaGeneralExamination ----
					textAreaGeneralExamination.setRows(4);
					textAreaGeneralExamination.setMinimumSize(new Dimension(300, 20));
					scrollPane4.setViewportView(textAreaGeneralExamination);
				}
				contentPanel.add(scrollPane4, "cell 11 20 32 3");

				// ---- label4 ----
				label4.setText("General Examination");
				contentPanel.add(label4, "cell 3 21 7 1");

				// ---- label6 ----
				label6.setText("Local Examination");
				contentPanel.add(label6, "cell 5 23");

				// ======== scrollPane6 ========
				{

					// ---- textAreaLocalExamination ----
					textAreaLocalExamination.setRows(4);
					scrollPane6.setViewportView(textAreaLocalExamination);
				}
				contentPanel.add(scrollPane6, "cell 11 23 32 1");

				// ---- label7 ----
				label7.setText("Impression");
				contentPanel.add(label7, "cell 5 24");

				// ======== scrollPane7 ========
				{

					// ---- textAreaImpression ----
					textAreaImpression.setRows(4);
					scrollPane7.setViewportView(textAreaImpression);
				}
				contentPanel.add(scrollPane7, "cell 11 24 32 1");
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
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(585, 560);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JScrollPane scrollPane1;
	private JTextArea textAreaPesentComplain;
	private JLabel label1;
	private JLabel label5;
	private JScrollPane scrollPane5;
	private JTextArea textAreaHPI;
	private JScrollPane scrollPane2;
	private JTextArea textAreaPMSH;
	private JLabel label2;
	private JScrollPane scrollPane3;
	private JTextArea textAreaPreMedication;
	private JLabel label3;
	private JScrollPane scrollPane4;
	private JTextArea textAreaGeneralExamination;
	private JLabel label4;
	private JLabel label6;
	private JScrollPane scrollPane6;
	private JTextArea textAreaLocalExamination;
	private JLabel label7;
	private JScrollPane scrollPane7;
	private JTextArea textAreaImpression;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
