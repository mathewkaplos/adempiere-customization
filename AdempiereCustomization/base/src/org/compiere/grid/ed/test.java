package org.compiere.grid.ed;

import java.awt.*;
import javax.swing.*;

import org.compiere.swing.CDialog;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Sat Oct 07 13:35:36 EAT 2017
 */

/**
 * @author Mathew Kipchumba
 */
public class test extends CDialog
{
	public test()
	{
		initComponents();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
		panel1 = new JPanel();
		button1 = new JButton();

		// ======== panel1 ========
		{

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
					"[fill]" + "[fill]" + "[fill]",
					// rows
					"[]" + "[]" + "[]" + "[]"));

			// ---- button1 ----
			button1.setText("text");
			panel1.add(button1, "cell 2 3");
		}
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew Kipchumba
	private JPanel panel1;
	private JButton button1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
