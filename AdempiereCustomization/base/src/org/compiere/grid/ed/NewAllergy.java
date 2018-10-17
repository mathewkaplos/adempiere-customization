/*
 * Created by JFormDesigner on Thu Nov 09 10:46:57 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.compiere.apps.form.VAllergies;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.PatientAllergy;

/**
 * @author Mathew Kipchumba
 */
public class NewAllergy extends JDialog
{
	private int treatID;

	public NewAllergy(Frame owner, int _treatID)
	{
		super(owner, true);
		treatID = _treatID;
		initComponents();
	}

	public NewAllergy(Dialog owner, int _treatID)
	{
		super(owner, true);
		treatID = _treatID;
		initComponents();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		PatientAllergy pa = new PatientAllergy(Env.getCtx(), 0, null);
		pa.sethms_treatment_doc_ID(treatID);
		pa.setallname(textAreaAllergyName.getText());
		pa.setreaction(textAreaReaction.getText());
		pa.save();

		JOptionPane.showMessageDialog(null, "Saved Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		VAllergies va = new VAllergies();
		va.loadBPartner();
	}

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
		scrollPane1 = new JScrollPane();
		textAreaAllergyName = new JTextArea();
		label1 = new JLabel();
		scrollPane2 = new JScrollPane();
		textAreaReaction = new JTextArea();
		label2 = new JLabel();
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
								+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
						// rows
						"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"
								+ "[]" + "[]"));

				// ======== scrollPane1 ========
				{
					scrollPane1.setMinimumSize(new Dimension(300, 24));

					// ---- textAreaAllergyName ----
					textAreaAllergyName.setMinimumSize(new Dimension(300, 20));
					textAreaAllergyName.setRows(4);
					scrollPane1.setViewportView(textAreaAllergyName);
				}
				contentPanel.add(scrollPane1, "cell 10 0 30 4");

				// ---- label1 ----
				label1.setText("Allergy Name");
				contentPanel.add(label1, "cell 3 1 6 1");

				// ======== scrollPane2 ========
				{
					scrollPane2.setMinimumSize(new Dimension(300, 24));

					// ---- textAreaReaction ----
					textAreaReaction.setRows(4);
					textAreaReaction.setMinimumSize(new Dimension(300, 20));
					scrollPane2.setViewportView(textAreaReaction);
				}
				contentPanel.add(scrollPane2, "cell 10 4 30 4");

				// ---- label2 ----
				label2.setText("Reaction");
				contentPanel.add(label2, "cell 3 5 8 1");
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
				okButton.setText("Save");
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
		setSize(585, 380);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JScrollPane scrollPane1;
	private JTextArea textAreaAllergyName;
	private JLabel label1;
	private JScrollPane scrollPane2;
	private JTextArea textAreaReaction;
	private JLabel label2;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
