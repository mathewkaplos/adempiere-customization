/*
 * Created by JFormDesigner on Mon Nov 06 16:33:31 EAT 2017
 */

package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.form.VDiagnosis;
import org.compiere.model.MColumn;
import org.compiere.model.ZLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import net.miginfocom.swing.*;
import zenith.model.MTreatmentDoc;
import zenith.model.PatientDiagnosis;
import zenith.model.X_hms_diagnosis;

/**
 * @author Mathew Kipchumba
 */
public class NewDiagnosis extends JDialog
{
	private ZLookup mDiagnosis_ID;
	private int treatID;

	public NewDiagnosis(Frame owner, int _treatID)
	{
		super(owner, true);
		initComponents();
		init();
		treatID = _treatID;
	}

	public NewDiagnosis(Dialog owner)
	{
		super(owner, true);
		initComponents();
		init();
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

	public static void main(String[] args)
	{
		NewDiagnosis nd = new NewDiagnosis((JDialog) null);
		nd.pack();
		nd.setVisible(true);
	}

	private void cancelButtonActionPerformed(ActionEvent e)
	{
		this.dispose();
	}

	private void okButtonActionPerformed(ActionEvent e)
	{
		int diagID = (int) mDiagnosis_ID.getValue();
		PatientDiagnosis pd = new PatientDiagnosis(Env.getCtx(), 0, null);
		pd.sethms_diagnosis_ID(diagID);
		pd.sethms_treatment_doc_ID(treatID);

		pd.save();

		JOptionPane.showMessageDialog(null, "Saved Successfully...", "Information Message",
				JOptionPane.INFORMATION_MESSAGE);
		VDiagnosis vb = new VDiagnosis();
		vb.loadBPartner();
		updateDiagnosis();
		this.dispose();
	}

	private void updateDiagnosis()
	{
		MTreatmentDoc doc = new MTreatmentDoc(Env.getCtx(), treatID, null);
		doc.updateDiagnosis();
	}

	private void buttonNewActionPerformed(ActionEvent e)
	{
		this.dispose();
		SetupDiagnosis setup = new SetupDiagnosis((Frame) null);
		AEnv.showCenterScreen(setup);
	}

	private void initComponents()
	{
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mathew
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		buttonNew = new JButton();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();

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
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- label1 ----
				label1.setText("Search Diagnosis:");
				contentPanel.add(label1, "cell 0 1 2 1");

				//---- buttonNew ----
				buttonNew.setText("Not found? Setup New");
				buttonNew.addActionListener(e -> buttonNewActionPerformed(e));
				contentPanel.add(buttonNew, "cell 9 4");
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setLayout(new MigLayout(
					"insets dialog,alignx right",
					// columns
					"[button,fill]" +
					"[button,fill]" +
					"[button,fill]",
					// rows
					null));

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(e -> okButtonActionPerformed(e));
				buttonBar.add(okButton, "cell 0 0");

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
				buttonBar.add(cancelButton, "cell 1 0");
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
	// Generated using JFormDesigner Evaluation license - Mathew
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JButton buttonNew;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
