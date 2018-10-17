/*
 * Created by JFormDesigner on Thu Nov 30 19:17:22 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MUser;
import org.compiere.model.ZLookupFactory;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MTreatmentDoc;

/**
 * @author mathew kipchumba
 */
public class BookPatient extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8791315916261884966L;

	public BookPatient(Frame owner)
	{
		super(owner, true);
		initComponents();
		init();
	}

	public BookPatient(Dialog owner)
	{
		super(owner);
		initComponents();
		init();
	}

	private VLookup vDepartment_ID;
	private VLookup vCategory_ID;
	private ZLookup vProduct_ID;
	private VLookup vVisitType;
	private VLookup vPatientPriority;
	private VLookup vPatientGroup_ID;
	private CTextField txtReferred_from;
	private VLookup vDoctor_ID;

	private void init()
	{
		// Department ID
		vDepartment_ID = new VLookup("AD_Org_ID", true, false, true, MLookupFactory.get(Env.getCtx(), 2, 0,
				MColumn.getColumn_ID(MOrg.Table_Name, MOrg.COLUMNNAME_AD_Org_ID), DisplayType.TableDir));
		panel1.add(vDepartment_ID, "cell 1 0 1 1,alignx left,growx 0,width 300");
		// Category ID
		vCategory_ID = new VLookup("M_Product_Category_ID", true, false, true, MLookupFactory.get(Env.getCtx(), 2, 0,
				MColumn.getColumn_ID(MProductCategory.Table_Name, MProductCategory.COLUMNNAME_M_Product_Category_ID),
				DisplayType.TableDir));
		panel1.add(vCategory_ID, "cell 1 1 1 1,alignx left,growx 0,width 300");
		// Product ID
		vProduct_ID = new ZLookup("M_Product_ID", true, false, true, ZLookupFactory.get(Env.getCtx(), 2, 0,
				MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_Product_ID), DisplayType.TableDir));
		panel1.add(vProduct_ID, "cell 1 2 1 1,alignx left,growx 0,width 300");
		// Visit Type
		vVisitType = new VLookup("visit_type", true, false, true, MLookupFactory.get(Env.getCtx(), 2, 0,
				MColumn.getColumn_ID(MTreatmentDoc.Table_Name, MTreatmentDoc.COLUMNNAME_visit_type), DisplayType.List));
		panel1.add(vVisitType, "cell 1 3 1 1,alignx left,growx 0,width 300");
		// patient_priority
		vPatientPriority = new VLookup("patient_priority", true, false, true, MLookupFactory.get(Env.getCtx(), 0, 0,
				MColumn.getColumn_ID(MBPartner.Table_Name, "patient_priority"), DisplayType.List));
		vPatientPriority.setValue("NM");
		panel1.add(vPatientPriority, "cell 1 4 1 1,alignx left,growx 0,width 300");
		// Patient Group --- Instance billing
		vPatientGroup_ID = new VLookup("C_BP_Group_ID", true, false, true, MLookupFactory.get(Env.getCtx(), 2, 0,
				MColumn.getColumn_ID(MBPGroup.Table_Name, MBPGroup.COLUMNNAME_C_BP_Group_ID), DisplayType.TableDir));
		panel1.add(vPatientGroup_ID, "cell 1 5 1 1,alignx left,growx 0,width 300");

		// Referred From
		txtReferred_from = new CTextField(50);
		panel1.add(txtReferred_from, "cell 1 7 1 1,alignx left,growx 0,width 300");

		// Doctor ID
		vDoctor_ID = new VLookup("AD_User_ID", true, false, true, MLookupFactory.get(Env.getCtx(), 2, 0,
				MColumn.getColumn_ID(MUser.Table_Name, MUser.COLUMNNAME_AD_User_ID), DisplayType.TableDir));
		panel1.add(vDoctor_ID, "cell 1 8 1 1,alignx left,growx 0,width 300");
	}

	private void btnCancelActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - mathew kipchumba
		panel1 = new JPanel();
		label18 = new JLabel();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		checkBox1 = new JCheckBox();
		label6 = new JLabel();
		label7 = new JLabel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		btnCancel = new JButton();
		button2 = new JButton();

		//======== this ========
		setMinimumSize(new Dimension(500, 490));
		setModal(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]"));

		//======== panel1 ========
		{
			panel1.setBorder(new TitledBorder("Booking Details"));

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new MigLayout(
				"insets panel,hidemode 3,gap 10 10",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- label18 ----
			label18.setText("Department");
			panel1.add(label18, "cell 0 0");

			//---- label1 ----
			label1.setText("Category");
			panel1.add(label1, "cell 0 1");

			//---- label2 ----
			label2.setText("Category Details");
			panel1.add(label2, "cell 0 2");

			//---- label3 ----
			label3.setText("Visit Type");
			panel1.add(label3, "cell 0 3");

			//---- label4 ----
			label4.setText("Patient Priority");
			panel1.add(label4, "cell 0 4");

			//---- label5 ----
			label5.setText("Instance Billing");
			panel1.add(label5, "cell 0 5");

			//---- checkBox1 ----
			checkBox1.setText("Referred In");
			panel1.add(checkBox1, "cell 1 6");

			//---- label6 ----
			label6.setText("Referred From");
			panel1.add(label6, "cell 0 7");

			//---- label7 ----
			label7.setText("Doctor");
			panel1.add(label7, "cell 0 8");
		}
		contentPane.add(panel1, "cell 0 0");

		//======== panel2 ========
		{
			panel2.setBorder(new TitledBorder("Actions"));
			panel2.setLayout(new MigLayout(
				"hidemode 3",
				// columns
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

			//======== panel3 ========
			{
				panel3.setLayout(new MigLayout(
					"hidemode 3",
					// columns
					"[fill]" +
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]"));
			}
			panel2.add(panel3, "cell 0 1 6 1");

			//---- btnCancel ----
			btnCancel.setText("CANCEL");
			btnCancel.setMaximumSize(new Dimension(200, 30));
			btnCancel.setMinimumSize(new Dimension(100, 30));
			btnCancel.setPreferredSize(new Dimension(120, 30));
			btnCancel.setForeground(Color.red);
			btnCancel.addActionListener(e -> btnCancelActionPerformed(e));
			panel2.add(btnCancel, "cell 6 1");

			//---- button2 ----
			button2.setText("BOOK");
			button2.setMaximumSize(new Dimension(200, 30));
			button2.setMinimumSize(new Dimension(100, 30));
			button2.setPreferredSize(new Dimension(120, 30));
			button2.setBackground(new Color(0, 128, 0));
			button2.setForeground(Color.white);
			panel2.add(button2, "cell 6 1");
		}
		contentPane.add(panel2, "cell 0 2 2 1");
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - mathew kipchumba
	private JPanel panel1;
	private JLabel label18;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JCheckBox checkBox1;
	private JLabel label6;
	private JLabel label7;
	private JPanel panel2;
	private JPanel panel3;
	private JButton btnCancel;
	private JButton button2;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
