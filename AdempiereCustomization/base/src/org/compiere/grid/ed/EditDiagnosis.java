/*
 * Created by JFormDesigner on Tue Nov 07 00:42:36 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.form.VBilling;
import org.compiere.apps.form.VDiagnosis;
import org.compiere.model.MColumn;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MBilling;
import zenith.model.MTreatmentDoc;
import zenith.model.PatientDiagnosis;
import zenith.model.X_hms_diagnosis;

/**
 * @author Mathew Kipchumba
 */
public class EditDiagnosis extends JDialog
{
	private int p_diagID;
	private ZLookup mDiagnosis_ID;

	public EditDiagnosis(Frame owner, int _diagID)
	{
		super(owner, true);
		initComponents();
		init();
		p_diagID = _diagID;
		populateWindow();
	}

	public EditDiagnosis(Dialog owner, int _diagID, int _treatID)
	{
		super(owner, true);
		initComponents();
		init();
		p_diagID = _diagID;
		populateWindow();
	}

	private void init()
	{
		mDiagnosis_ID = new ZLookup("hms_diagnosis_ID", false, false, true,
				ZLookupFactory.get(Env.getCtx(), 0, 0,
						MColumn.getColumn_ID(X_hms_diagnosis.Table_Name, X_hms_diagnosis.COLUMNNAME_hms_diagnosis_ID),
						DisplayType.Table));
		mDiagnosis_ID.setBackground(AdempierePLAF.getInfoBackground());
		mDiagnosis_ID.addActionListener(new ZLookup() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1800241106974099101L;

			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		});
		contentPanel.add(mDiagnosis_ID, "cell 2 1 5 1,alignx left,growx 0,width 400::1000");

	}

	private void populateWindow()
	{
		PatientDiagnosis pd = new PatientDiagnosis(Env.getCtx(), p_diagID, null);
		mDiagnosis_ID.setValue(pd.gethms_diagnosis_ID());
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		int diagID = (int) mDiagnosis_ID.getValue();
		PatientDiagnosis pd = new PatientDiagnosis(Env.getCtx(), p_diagID, null);
		pd.sethms_diagnosis_ID(diagID);

		pd.save();

		JOptionPane.showMessageDialog(null, "Updated Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		VDiagnosis vb = new VDiagnosis();
		vb.loadBPartner();
		updateDiagnosis(pd.gethms_treatment_doc_ID());
		this.dispose();
	}

	private void updateDiagnosis(int treatID)
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), treatID, null);
		doc.updateDiagnosis();
	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void deleteButtonActionPerformed(ActionEvent e)
	{
		int x = yesnocancel("Are you sure you want to delete this?");
		if (x == 0)
		{
			PatientDiagnosis pd = new PatientDiagnosis(Env.getCtx(), p_diagID, null);
			int treatID = pd.gethms_treatment_doc_ID();
			pd.delete(true);
			VDiagnosis vd = new VDiagnosis();
			vd.loadBPartner();
			JOptionPane.showMessageDialog(null, "Deleted Successfully...", "Information Message",
					JOptionPane.INFORMATION_MESSAGE);
			updateDiagnosis(treatID);
			this.dispose();
		} else if (x == 1)
		{
			this.dispose();
		} else if (x == 2)
		{
			return;
		}
	}

	private static int yesnocancel(String theMessage)
	{
		int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "Alert",
				JOptionPane.YES_NO_CANCEL_OPTION);
		return result;
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		contentPanel = new JPanel();
		label1 = new JLabel();
		buttonBar = new JPanel();
		deleteButton = new JButton();
		okButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== contentPanel ========
		{
			contentPanel.setEnabled(false);

			// JFormDesigner evaluation mark
			contentPanel.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), contentPanel.getBorder())); contentPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contentPanel.setLayout(new MigLayout(
				"insets dialog,hidemode 3",
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
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- label1 ----
			label1.setText("Name:");
			contentPanel.add(label1, "cell 0 1 2 1");

			//======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout(
					"insets dialog,alignx right",
					// columns
					"[button,fill]" +
					"[button,fill]" +
					"[button,fill]",
					// rows
					"[]" +
					"[]"));

				//---- deleteButton ----
				deleteButton.setText("Delete");
				deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
				buttonBar.add(deleteButton, "cell 0 1");

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 1 1");

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 2 1");
			}
			contentPanel.add(buttonBar, "cell 0 7 5 1");
		}
		contentPane.add(contentPanel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mathew
	private JPanel contentPanel;
	private JLabel label1;
	private JPanel buttonBar;
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
