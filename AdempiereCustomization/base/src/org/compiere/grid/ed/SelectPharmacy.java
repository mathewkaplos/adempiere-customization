/*
 * Created by JFormDesigner on Fri Oct 05 13:43:02 EAT 2018
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.model.MColumn;
import org.compiere.model.MLocator;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.Env;
import org.compiere.util.Language;

import net.miginfocom.swing.*;

/**
 * @author Mathew
 */
public class SelectPharmacy extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3277672433967156609L;

	private VLookup mLocator_ID;
	private static String whereClause = "";

	public SelectPharmacy(Frame owner, String title, String whereClause)
	{
		super(owner, true);
		initComponents();
		this.setTitle(title);
		this.whereClause = whereClause;
		init();
	}

	public SelectPharmacy(Dialog owner)
	{
		super(owner);
		initComponents();
	}

	private void OkButtonActionPerformed(ActionEvent e)
	{
		int M_Locator_ID = (int) mLocator_ID.getValue();
		Dispense dispense = new Dispense((Frame) null, M_Locator_ID, whereClause);
		AEnv.showCenterScreen(dispense);
		// this.dispose();
	}

	private void init()
	{
		try
		{
			mLocator_ID = new VLookup("M_Locator_ID", true, false, true,
					ZLookupFactory.get(Env.getCtx(), 0,
							MColumn.getColumn_ID(MLocator.Table_Name, MLocator.COLUMNNAME_M_Locator_ID), 18,
							Language.getBaseLanguage(), MLocator.COLUMNNAME_M_Locator_ID, 1000042, false, ""));
		} catch (Exception e2)
		{
			e2.printStackTrace();
		}
		mLocator_ID.setBackground(AdempierePLAF.getInfoBackground());
		mLocator_ID.addActionListener(new ZLookup() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1800241106974099101L;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (mLocator_ID.getValue() != null)
						if ((int) mLocator_ID.getValue() > 0)
						{
							// M_Locator_ID = (int) mLocator_ID.getValue();

						}
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPanel.add(mLocator_ID, "cell 3 0 5 1,alignx left,growx 0,width 200::200");
	}

	private void btnCloseActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - mathew359722@gmail.com
		btnClose = new JButton();
		contentPanel = new JPanel();
		label2 = new JLabel();
		OkButton = new JButton();

		//======== this ========
		setTitle("s");
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
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
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]"));

		//---- btnClose ----
		btnClose.setText("Close");
		btnClose.setForeground(Color.red);
		btnClose.addActionListener(e -> btnCloseActionPerformed(e));
		contentPane.add(btnClose, "cell 15 0 1 2");

		//======== contentPanel ========
		{

			// JFormDesigner evaluation mark
			contentPanel.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), contentPanel.getBorder())); contentPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contentPanel.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- label2 ----
			label2.setText("Locator:");
			contentPanel.add(label2, "cell 0 0");

			//---- OkButton ----
			OkButton.setText("OK");
			OkButton.setPreferredSize(new Dimension(47, 30));
			OkButton.addActionListener(e -> OkButtonActionPerformed(e));
			contentPanel.add(OkButton, "cell 8 3");
		}
		contentPane.add(contentPanel, "cell 0 2 10 1");
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - mathew359722@gmail.com
	private JButton btnClose;
	private JPanel contentPanel;
	private JLabel label2;
	private JButton OkButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
