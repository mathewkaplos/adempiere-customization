/*
 * Created by JFormDesigner on Tue Oct 16 11:35:36 EAT 2018
 */

package org.compiere.grid.ed;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathew
 */
public class InpatientDrugOrders extends JDialog {
	public InpatientDrugOrders(Frame owner) {
		super(owner);
		initComponents();
	}

	public InpatientDrugOrders(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		scrollPane2 = new JScrollPane();
		list2 = new JList();
		scrollPane1 = new JScrollPane();
		list1 = new JList();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]"));

		//======== scrollPane2 ========
		{
			scrollPane2.setViewportView(list2);
		}
		contentPane.add(scrollPane2, "cell 0 1 2 1");

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(list1);
		}
		contentPane.add(scrollPane1, "cell 0 1 2 1");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew
	private JScrollPane scrollPane2;
	private JList list2;
	private JScrollPane scrollPane1;
	private JList list1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
