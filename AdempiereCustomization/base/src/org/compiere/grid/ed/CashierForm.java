/*
 * Created by JFormDesigner on Fri Nov 03 18:59:45 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathew Kipchumba
 */
public class CashierForm extends JDialog
{
	public CashierForm(Frame owner)
	{
		super(owner);
		initComponents();
	}

	public CashierForm(Dialog owner)
	{
		super(owner);
		initComponents();
	}

	public static void main(String[] args)
	{
		CashierForm cf = new CashierForm((Frame) null);
		cf.pack();
		cf.setVisible(true);
		cf.xx();
	}

	void xx()
	{

	}

	int x = 1;
	JPanel[] panels = null;

	private void button2ActionPerformed(ActionEvent e)
	{
		panel1.setVisible(false);
		panel2.setVisible(false);
		panel3.setVisible(false);
		if (x == 1)
		{
			panel2.setVisible(true);
			x++;
		}
		if (x == 2)
		{
			panel3.setVisible(true);
			//button2.setEnabled(false);
			x++;
		}

	}

	private void button1ActionPerformed(ActionEvent e)
	{
		panel1.setVisible(false);
		panel2.setVisible(false);
		panel3.setVisible(false);
		if (x == 3)
		{
			panel2.setVisible(true);
			x--;
		}
		if (x == 2)
		{
			panel1.setVisible(true);
			//button1.setEnabled(false);
			x--;
		}

	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		panel1 = new JPanel();
		label1 = new JLabel();
		panel2 = new JPanel();
		label2 = new JLabel();
		panel3 = new JPanel();
		label3 = new JLabel();
		buttonBar = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		okButton = new JButton();
		cancelButton = new JButton();
		helpButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{

			// JFormDesigner evaluation mark
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new CardLayout());

				//======== panel1 ========
				{
					panel1.setLayout(new MigLayout(
						"insets 0,hidemode 3",
						// columns
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]",
						// rows
						"[fill]" +
						"[]"));

					//---- label1 ----
					label1.setText("Panel 1");
					label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() & ~Font.ITALIC));
					label1.setMaximumSize(null);
					label1.setMinimumSize(null);
					label1.setInheritsPopupMenu(false);
					label1.setIconTextGap(8);
					panel1.add(label1, "cell 1 1 3 1");
				}
				contentPanel.add(panel1, "card1");

				//======== panel2 ========
				{
					panel2.setLayout(new MigLayout(
						"insets 0,hidemode 3",
						// columns
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]",
						// rows
						"[fill]" +
						"[]"));

					//---- label2 ----
					label2.setText("Panel 2");
					panel2.add(label2, "cell 4 1");
				}
				contentPanel.add(panel2, "card2");

				//======== panel3 ========
				{
					panel3.setLayout(new MigLayout(
						"insets 0,hidemode 3",
						// columns
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]" +
						"[fill]",
						// rows
						"[fill]" +
						"[]" +
						"[]"));

					//---- label3 ----
					label3.setText("Panel 3");
					panel3.add(label3, "cell 7 2");
				}
				contentPanel.add(panel3, "card3");
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout(
					"insets dialog,alignx right",
					// columns
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[fill]" +
					"[button,fill]" +
					"[button,fill]" +
					"[button,fill]",
					// rows
					"[]" +
					"[]"));

				//---- button1 ----
				button1.setText("Previous");
				button1.addActionListener(e -> button1ActionPerformed(e));
				buttonBar.add(button1, "cell 0 1");

				//---- button2 ----
				button2.setText("Next");
				button2.addActionListener(e -> button2ActionPerformed(e));
				buttonBar.add(button2, "cell 3 1");

				//---- okButton ----
				okButton.setText("OK");
				buttonBar.add(okButton, "cell 13 1");

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				buttonBar.add(cancelButton, "cell 14 1");

				//---- helpButton ----
				helpButton.setText("Help");
				buttonBar.add(helpButton, "cell 15 1");
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
	private JPanel panel1;
	private JLabel label1;
	private JPanel panel2;
	private JLabel label2;
	private JPanel panel3;
	private JLabel label3;
	private JPanel buttonBar;
	private JButton button1;
	private JButton button2;
	private JButton okButton;
	private JButton cancelButton;
	private JButton helpButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
