/*
 * Created by JFormDesigner on Wed Oct 10 00:15:15 EAT 2018
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MDiagnosis;

/**
 * @author Mathew
 */
public class SetupDiagnosis extends JDialog
{
	public SetupDiagnosis(Frame owner)
	{
		super(owner, true);
		initComponents();
	}

	public SetupDiagnosis(Dialog owner)
	{
		super(owner);
		initComponents();
	}

	private void buttonRegisterActionPerformed(ActionEvent e)
	{
		MDiagnosis diag = new MDiagnosis(Env.getCtx(), 0, null);
		diag.setName(textField1.getText());
		diag.save();
		JOptionPane.showMessageDialog(null, "Diagnosis registered successfully...");
		this.dispose();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		buttonRegister = new JButton();

		// ======== this ========
		setTitle("Setup New Diagnosis");
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout("hidemode 3",
				// columns
				"[fill]" + "[fill]",
				// rows
				"[]"));

		// ======== panel1 ========
		{
			panel1.setPreferredSize(new Dimension(400, 120));

			// JFormDesigner evaluation mark
			panel1.setBorder(
					new javax.swing.border.CompoundBorder(
							new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
									"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
									javax.swing.border.TitledBorder.BOTTOM,
									new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red),
							panel1.getBorder()));
			panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e)
				{
					if ("border".equals(e.getPropertyName()))
						throw new RuntimeException();
				}
			});

			panel1.setLayout(new MigLayout("hidemode 3",
					// columns
					"[fill]" + "[fill]",
					// rows
					"[]" + "[]" + "[]" + "[]"));

			// ---- label1 ----
			label1.setText("Diagnosis Name:");
			panel1.add(label1, "cell 0 0");

			// ---- textField1 ----
			textField1.setPreferredSize(new Dimension(300, 20));
			panel1.add(textField1, "cell 1 0");

			// ---- buttonRegister ----
			buttonRegister.setText("REGISTER");
			buttonRegister.addActionListener(e -> buttonRegisterActionPerformed(e));
			panel1.add(buttonRegister, "cell 1 3");
		}
		contentPane.add(panel1, "cell 0 0 2 1");
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew
	private JPanel panel1;
	private JLabel label1;
	private JTextField textField1;
	private JButton buttonRegister;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
