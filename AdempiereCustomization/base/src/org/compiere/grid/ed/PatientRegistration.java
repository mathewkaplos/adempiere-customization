//
///*
// * Created by JFormDesigner on Mon Nov 27 12:21:41 EAT 2017
// */
//
//package org.compiere.grid.ed;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.text.SimpleDateFormat;
//
//import javax.swing.*;
//import javax.swing.border.*;
//import org.compiere.model.MBPGroup;
//import org.compiere.model.MBPartner;
//import org.compiere.model.MColumn;
//import org.compiere.model.MLookupFactory;
//import org.compiere.util.DisplayType;
//import org.compiere.util.Env;
//import net.miginfocom.swing.*;
//import zenith.model.X_hms_insuredco;
//import zenith.util.DateUtil;
//
///**
// * @author Mathew Kipchumba
// */
//public class PatientRegistration extends JDialog
//{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -1968102914507985454L;
//
//	int C_BParter_ID = 0;
//
//	public PatientRegistration(Frame frame, int bpID)
//	{
//		super(frame, true);
//		this.C_BParter_ID = bpID;
//
//		initComponents();
//
//		init();
//		if (C_BParter_ID > 0) // Existing Record
//		{
//			populateWindow(C_BParter_ID);
//
//			deactivateWindow();
//		} else
//		{
//			activateWindow();
//		}
//	}
//
//	private VDate dor;
//	private VDate dob;
//	private VLookup vPatientGroup_ID;
//	private VLookup vInsuredCompany_ID;
//	private VLookup vPatientPriority;
//	private VLookup vGender;
//
//	private void init()
//	{
//		String pattern = "dd-MM-yyyy";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		// dor
//		dor = new VDate();
//		dor.setFormat(simpleDateFormat);
//		dor.setMandatory(true);
//		panel2.add(dor, "cell 9 5");
//		// DOB
//		dob = new VDate();
//		dob.setFormat(simpleDateFormat);
//		dob.setMandatory(true);
//		panel2.add(dob, "cell 2 10");
//		// Patient Group
//		vPatientGroup_ID = new VLookup("C_BP_Group_ID", true, false, true, MLookupFactory.get(Env.getCtx(), 2, 0,
//				MColumn.getColumn_ID(MBPGroup.Table_Name, MBPGroup.COLUMNNAME_C_BP_Group_ID), DisplayType.TableDir));
//
//		panel2.add(vPatientGroup_ID, "cell 2 7");
//
//		// X_hms_insuredco
//		vInsuredCompany_ID = new VLookup("hms_insuredco_ID", false, false, true,
//				MLookupFactory.get(Env.getCtx(), 0, 0,
//						MColumn.getColumn_ID(X_hms_insuredco.Table_Name, X_hms_insuredco.COLUMNNAME_hms_insuredco_ID),
//						18));
//		panel2.add(vInsuredCompany_ID, "cell 9 13");
//		// patient_priority
//		vPatientPriority = new VLookup("patient_priority", false, false, true, MLookupFactory.get(Env.getCtx(), 0, 0,
//				MColumn.getColumn_ID(MBPartner.Table_Name, "patient_priority"), DisplayType.List));
//		vPatientPriority.setValue("NM");
//		vPatientPriority.setMandatory(true);
//		panel2.add(vPatientPriority, "cell 2 15");
//
//		// patient Gender
//		vGender = new VLookup("Gender", false, false, true, MLookupFactory.get(Env.getCtx(), 0, 0,
//				MColumn.getColumn_ID(MBPartner.Table_Name, "Gender"), DisplayType.List));
//		vGender.setMandatory(true);
//		panel2.add(vGender, "cell 9 7");
//	}
//
//	private void populateWindow(int bpID)
//	{
//		if (bpID > 0)
//		{
//			MBPartner bp = new MBPartner(Env.getCtx(), bpID, null);
//			txtSystemNumber.setText(String.valueOf(C_BParter_ID));
//			txtOldPatientNumber.setText(bp.getOldPatientNo());
//			txtPatientNo.setText(bp.getDocumentNo());
//			txtInpatientNo.setText(String.valueOf(bp.getInpatientNo()));
//			txtPatientName.setText(bp.getName());
//			dor.setValue(bp.getDor());
//			vPatientGroup_ID.setValue(bp.getC_BP_Group_ID());
//			bp.getGender();
//			txtNationalID.setText(String.valueOf(bp.getIDNo()));
//			txtTelephone.setText(bp.getTelNo());
//			dob.setValue(bp.getDob());
//			contributesToNHIF.setSelected(bp.getContributesToNHIF());
//			txtNHIFNo.setText(bp.getNHIFNo());
//			isSelfEmployed.setSelected(bp.getIsSelfEmployed());
//			vInsuredCompany_ID.setValue(bp.getInsuredCompany_ID());
//			vPatientPriority.setValue(bp.getpatient_priority());
//			txtNextLocation.setText(bp.getNextLocation());
//			vGender.setValue(bp.getGender());
//			txtAge.setText(bp.getAge());
//			txtLastVisit.setText(bp.getLastVisit());
//			txtPeriodSinceLastVisit.setText(bp.getPeriodSinceLastVist());
//		}
//
//	}
//
//	private void deactivateWindow()
//	{
//		txtPatientName.setEditable(false);
//		dor.setReadWrite(false);
//		vPatientGroup_ID.setReadWrite(false);
//		vGender.setReadWrite(false);
//		txtNationalID.setEditable(false);
//		txtTelephone.setEditable(false);
//		dob.setReadWrite(false);
//		contributesToNHIF.setEnabled(false);
//		txtNHIFNo.setEditable(false);
//		isSelfEmployed.setEnabled(false);
//		vInsuredCompany_ID.setReadWrite(false);
//		isPhotoAttached.setEnabled(false);
//		vPatientPriority.setReadWrite(false);
//		btnSave.setEnabled(false);
//		btnUpdate.setEnabled(false);
//		btnEdit.setEnabled(true);
//		btnBook.setEnabled(true);
//		btnCancel.setEnabled(false);
//	}
//
//	private void activateWindow()
//	{
//		txtPatientName.setEditable(true);
//		dor.setReadWrite(true);
//		vPatientGroup_ID.setReadWrite(true);
//		vGender.setReadWrite(true);
//		txtNationalID.setEditable(true);
//		txtTelephone.setEditable(true);
//		dob.setReadWrite(true);
//		contributesToNHIF.setEnabled(true);
//		txtNHIFNo.setEditable(true);
//		isSelfEmployed.setEnabled(true);
//		vInsuredCompany_ID.setReadWrite(true);
//		isPhotoAttached.setEnabled(true);
//		vPatientPriority.setReadWrite(true);
//		if (C_BParter_ID > 0)
//		{
//			btnUpdate.setEnabled(true);
//			btnSave.setEnabled(false);
//			btnCancel.setEnabled(true);
//		} else
//		{
//			btnSave.setEnabled(true);
//			btnUpdate.setEnabled(false);
//			btnCancel.setEnabled(false);
//		}
//		btnEdit.setEnabled(false);
//		btnBook.setEnabled(false);
//
//	}
//
//	private void saveNew()
//	{
//		save(true);
//		JOptionPane.showMessageDialog(null,
//				new JLabel("<html><h1><font color='green'>Saved Successfully...</font></h1></html>"), "Updated!",
//				JOptionPane.INFORMATION_MESSAGE);
//	}
//
//	private void update()
//	{
//		save(false);
//		JOptionPane.showMessageDialog(null,
//				new JLabel("<html><h1><font color='green'>Updated Successfully...</font></h1></html>"), "Updated!",
//				JOptionPane.INFORMATION_MESSAGE);
//	}
//
//	private boolean save(boolean newRecord)
//	{
//		MBPartner bp = null;
//		if (newRecord)
//		{
//			bp = new MBPartner(Env.getCtx(), 0, null);
//		} else
//		{
//			int bpID = Integer.parseInt(txtSystemNumber.getText());
//			bp = new MBPartner(Env.getCtx(), bpID, null);
//			// txtOldPatientNumber.getText();
//			bp.setDocumentNo(txtPatientNo.getText());
//			// txtInpatientNo.getText();
//		}
//		bp.setName(txtPatientName.getText());
//		bp.setDor(DateUtil.getTimestamp(dor.getValue()));
//		if (vPatientGroup_ID.getValue() != null)
//			bp.setC_BP_Group_ID((Integer) vPatientGroup_ID.getValue());
//		bp.setGender((String) vGender.getValue());
//		bp.setIDNo(txtNationalID.getText());
//		bp.setTelNo(txtTelephone.getText());
//		bp.setDob(DateUtil.getTimestamp(dob.getValue()));
//		bp.setContributesToNHIF((contributesToNHIF.isSelected()));
//		bp.setNHIFNo(txtNHIFNo.getText());
//		bp.setIsSelfEmployed(isSelfEmployed.isSelected());
//		if (vInsuredCompany_ID.getValue() != null)
//			bp.setInsuredCompany_ID((Integer) vInsuredCompany_ID.getValue());
//		bp.setIsPhotoAttached(isPhotoAttached.isSelected());
//		bp.setPatientPriority((String) vPatientPriority.getValue());
//		bp.save();
//		if (newRecord)
//			C_BParter_ID = bp.getC_BPartner_ID();
//		populateWindow(bp.getC_BPartner_ID());
//		deactivateWindow();
//		return true;
//	}
//
//	private void btnSaveActionPerformed(ActionEvent e)
//	{
//		if (C_BParter_ID == 0)
//		{
//			saveNew();
//		}
//	}
//
//	private void btnUpdateActionPerformed(ActionEvent e)
//	{
//		if (C_BParter_ID > 0)
//		{
//			update();
//		}
//	}
//
//	private void btnEditActionPerformed(ActionEvent e)
//	{
//		activateWindow();
//	}
//
//	private void btnCancelActionPerformed(ActionEvent e)
//	{
//		deactivateWindow();
//	}
//
//	private void btnBookActionPerformed(ActionEvent e)
//	{
//		BookPatient book = new BookPatient((Frame) null);
//		book.setVisible(true);
//	}
//
//	private void initComponents()
//	{
//		// JFormDesigner - Component initialization - DO NOT MODIFY
//		// //GEN-BEGIN:initComponents
//		// Generated using JFormDesigner Evaluation license - mathew kipchumba
//		panel2 = new JPanel();
//		label1 = new JLabel();
//		txtSystemNumber = new JTextField();
//		label4 = new JLabel();
//		txtOldPatientNumber = new JTextField();
//		label2 = new JLabel();
//		txtPatientNo = new JTextField();
//		label5 = new JLabel();
//		txtInpatientNo = new JTextField();
//		label3 = new JLabel();
//		txtPatientName = new JTextField();
//		label6 = new JLabel();
//		label7 = new JLabel();
//		label8 = new JLabel();
//		label9 = new JLabel();
//		txtNationalID = new JTextField();
//		label10 = new JLabel();
//		txtTelephone = new JTextField();
//		label11 = new JLabel();
//		label12 = new JLabel();
//		txtAge = new JTextField();
//		label13 = new JLabel();
//		txtLastVisit = new JTextField();
//		label14 = new JLabel();
//		txtPeriodSinceLastVisit = new JTextField();
//		contributesToNHIF = new JCheckBox();
//		label16 = new JLabel();
//		txtNHIFNo = new JTextField();
//		isSelfEmployed = new JCheckBox();
//		label18 = new JLabel();
//		isPhotoAttached = new JCheckBox();
//		label20 = new JLabel();
//		txtOpenBalance = new JTextField();
//		label21 = new JLabel();
//		label22 = new JLabel();
//		txtNextLocation = new JTextField();
//		panel1 = new JPanel();
//		btnEdit = new JButton();
//		btnUpdate = new JButton();
//		btnCancel = new JButton();
//		btnSave = new JButton();
//		btnBook = new JButton();
//
//		// ======== this ========
//		setTitle("Frame");
//		Container contentPane = getContentPane();
//		contentPane.setLayout(new MigLayout("hidemode 3",
//				// columns
//				"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
//						+ "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]",
//				// rows
//				"[]" + "[]" + "[]" + "[25]" + "[]" + "[]" + "[25]" + "[]" + "[25]" + "[]" + "[]" + "[]" + "[]" + "[]"
//						+ "[]" + "[25]" + "[]" + "[25]" + "[]" + "[25]" + "[]" + "[25]" + "[]" + "[]" + "[]" + "[]"
//						+ "[]" + "[]" + "[]"));
//
//		// ======== panel2 ========
//		{
//			panel2.setBorder(new TitledBorder("Patient Info"));
//
//			// JFormDesigner evaluation mark
//			panel2.setBorder(
//					new javax.swing.border.CompoundBorder(
//							new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
//									"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
//									javax.swing.border.TitledBorder.BOTTOM,
//									new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red),
//							panel2.getBorder()));
//			panel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
//				public void propertyChange(java.beans.PropertyChangeEvent e)
//				{
//					if ("border".equals(e.getPropertyName()))
//						throw new RuntimeException();
//				}
//			});
//
//			panel2.setLayout(new MigLayout("hidemode 3",
//					// columns
//					"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
//							+ "[fill]",
//					// rows
//					"[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]"
//							+ "[]" + "[]"));
//
//			// ---- label1 ----
//			label1.setText("System Number");
//			panel2.add(label1, "cell 0 0 2 3");
//
//			// ---- txtSystemNumber ----
//			txtSystemNumber.setMinimumSize(new Dimension(250, 20));
//			txtSystemNumber.setEditable(false);
//			panel2.add(txtSystemNumber, "cell 2 1");
//
//			// ---- label4 ----
//			label4.setText("Old Patient No");
//			panel2.add(label4, "cell 6 1");
//
//			// ---- txtOldPatientNumber ----
//			txtOldPatientNumber.setMinimumSize(new Dimension(250, 20));
//			txtOldPatientNumber.setEditable(false);
//			panel2.add(txtOldPatientNumber, "cell 9 1");
//
//			// ---- label2 ----
//			label2.setText("Patient No.");
//			panel2.add(label2, "cell 0 3");
//
//			// ---- txtPatientNo ----
//			txtPatientNo.setMinimumSize(new Dimension(250, 20));
//			txtPatientNo.setEditable(false);
//			panel2.add(txtPatientNo, "cell 2 3");
//
//			// ---- label5 ----
//			label5.setText("Inpatient No");
//			panel2.add(label5, "cell 6 3");
//
//			// ---- txtInpatientNo ----
//			txtInpatientNo.setMinimumSize(new Dimension(250, 20));
//			txtInpatientNo.setEditable(false);
//			panel2.add(txtInpatientNo, "cell 9 3");
//
//			// ---- label3 ----
//			label3.setText("Patient Name");
//			panel2.add(label3, "cell 0 5");
//
//			// ---- txtPatientName ----
//			txtPatientName.setMinimumSize(new Dimension(250, 20));
//			panel2.add(txtPatientName, "cell 2 5");
//
//			// ---- label6 ----
//			label6.setText("Date of Registration");
//			panel2.add(label6, "cell 6 5");
//
//			// ---- label7 ----
//			label7.setText("Patient Group");
//			panel2.add(label7, "cell 0 7");
//
//			// ---- label8 ----
//			label8.setText("Gender");
//			panel2.add(label8, "cell 6 7");
//
//			// ---- label9 ----
//			label9.setText("National ID No");
//			panel2.add(label9, "cell 0 9");
//
//			// ---- txtNationalID ----
//			txtNationalID.setMinimumSize(new Dimension(250, 20));
//			panel2.add(txtNationalID, "cell 2 9");
//
//			// ---- label10 ----
//			label10.setText("Telephone");
//			panel2.add(label10, "cell 6 9");
//
//			// ---- txtTelephone ----
//			txtTelephone.setMinimumSize(new Dimension(250, 20));
//			panel2.add(txtTelephone, "cell 9 9");
//
//			// ---- label11 ----
//			label11.setText("Date of Birth");
//			panel2.add(label11, "cell 0 10");
//
//			// ---- label12 ----
//			label12.setText("Age");
//			panel2.add(label12, "cell 6 10");
//
//			// ---- txtAge ----
//			txtAge.setMinimumSize(new Dimension(250, 20));
//			txtAge.setEditable(false);
//			panel2.add(txtAge, "cell 9 10");
//
//			// ---- label13 ----
//			label13.setText("Patient Last Visit");
//			panel2.add(label13, "cell 0 11");
//
//			// ---- txtLastVisit ----
//			txtLastVisit.setMinimumSize(new Dimension(250, 20));
//			txtLastVisit.setEditable(false);
//			panel2.add(txtLastVisit, "cell 2 11");
//
//			// ---- label14 ----
//			label14.setText("Period Since Last Visit");
//			panel2.add(label14, "cell 6 11");
//
//			// ---- txtPeriodSinceLastVisit ----
//			txtPeriodSinceLastVisit.setMinimumSize(new Dimension(250, 20));
//			txtPeriodSinceLastVisit.setEditable(false);
//			panel2.add(txtPeriodSinceLastVisit, "cell 9 11");
//
//			// ---- contributesToNHIF ----
//			contributesToNHIF.setText("Contibutes to NHIF");
//			panel2.add(contributesToNHIF, "cell 2 12");
//
//			// ---- label16 ----
//			label16.setText("NHIF No");
//			panel2.add(label16, "cell 6 12");
//
//			// ---- txtNHIFNo ----
//			txtNHIFNo.setMinimumSize(new Dimension(250, 20));
//			panel2.add(txtNHIFNo, "cell 9 12");
//
//			// ---- isSelfEmployed ----
//			isSelfEmployed.setText("Is Self Emplyed");
//			panel2.add(isSelfEmployed, "cell 2 13");
//
//			// ---- label18 ----
//			label18.setText("Company/Emplyee");
//			panel2.add(label18, "cell 6 13");
//
//			// ---- isPhotoAttached ----
//			isPhotoAttached.setText("Photo Attached");
//			panel2.add(isPhotoAttached, "cell 2 14");
//
//			// ---- label20 ----
//			label20.setText("Open Balance");
//			panel2.add(label20, "cell 6 14");
//
//			// ---- txtOpenBalance ----
//			txtOpenBalance.setMinimumSize(new Dimension(250, 20));
//			txtOpenBalance.setEditable(false);
//			panel2.add(txtOpenBalance, "cell 9 14");
//
//			// ---- label21 ----
//			label21.setText("Patient Priority");
//			panel2.add(label21, "cell 0 15");
//
//			// ---- label22 ----
//			label22.setText("Next Location");
//			panel2.add(label22, "cell 6 15");
//
//			// ---- txtNextLocation ----
//			txtNextLocation.setMinimumSize(new Dimension(250, 20));
//			txtNextLocation.setEditable(false);
//			panel2.add(txtNextLocation, "cell 9 15");
//		}
//		contentPane.add(panel2, "cell 3 1");
//
//		// ======== panel1 ========
//		{
//			panel1.setBorder(new TitledBorder(""));
//			panel1.setLayout(new MigLayout("hidemode 3",
//					// columns
//					"[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]" + "[fill]"
//							+ "[fill]" + "[fill]" + "[fill]" + "[fill]",
//					// rows
//					"[]" + "[]" + "[]" + "[]"));
//
//			// ---- btnEdit ----
//			btnEdit.setText("EDIT");
//			btnEdit.addActionListener(e -> btnEditActionPerformed(e));
//			panel1.add(btnEdit, "cell 1 1");
//
//			// ---- btnUpdate ----
//			btnUpdate.setText("UPDATE");
//			btnUpdate.addActionListener(e -> btnUpdateActionPerformed(e));
//			panel1.add(btnUpdate, "cell 3 1");
//
//			// ---- btnCancel ----
//			btnCancel.setText("CANCEL");
//			btnCancel.addActionListener(e -> btnCancelActionPerformed(e));
//			panel1.add(btnCancel, "cell 5 1");
//
//			// ---- btnSave ----
//			btnSave.setText("SAVE NEW RECORD");
//			btnSave.addActionListener(e -> btnSaveActionPerformed(e));
//			panel1.add(btnSave, "cell 7 1");
//
//			// ---- btnBook ----
//			btnBook.setText("BOOK PATIENT");
//			btnBook.addActionListener(e -> btnBookActionPerformed(e));
//			panel1.add(btnBook, "cell 12 1");
//		}
//		contentPane.add(panel1, "cell 3 5 12 6");
//		pack();
//		setLocationRelativeTo(getOwner());
//		// //GEN-END:initComponents
//	}
//
//	// JFormDesigner - Variables declaration - DO NOT MODIFY
//	// //GEN-BEGIN:variables
//	// Generated using JFormDesigner Evaluation license - mathew kipchumba
//	private JPanel panel2;
//	private JLabel label1;
//	private JTextField txtSystemNumber;
//	private JLabel label4;
//	private JTextField txtOldPatientNumber;
//	private JLabel label2;
//	private JTextField txtPatientNo;
//	private JLabel label5;
//	private JTextField txtInpatientNo;
//	private JLabel label3;
//	private JTextField txtPatientName;
//	private JLabel label6;
//	private JLabel label7;
//	private JLabel label8;
//	private JLabel label9;
//	private JTextField txtNationalID;
//	private JLabel label10;
//	private JTextField txtTelephone;
//	private JLabel label11;
//	private JLabel label12;
//	private JTextField txtAge;
//	private JLabel label13;
//	private JTextField txtLastVisit;
//	private JLabel label14;
//	private JTextField txtPeriodSinceLastVisit;
//	private JCheckBox contributesToNHIF;
//	private JLabel label16;
//	private JTextField txtNHIFNo;
//	private JCheckBox isSelfEmployed;
//	private JLabel label18;
//	private JCheckBox isPhotoAttached;
//	private JLabel label20;
//	private JTextField txtOpenBalance;
//	private JLabel label21;
//	private JLabel label22;
//	private JTextField txtNextLocation;
//	private JPanel panel1;
//	private JButton btnEdit;
//	private JButton btnUpdate;
//	private JButton btnCancel;
//	private JButton btnSave;
//	private JButton btnBook;
//	// JFormDesigner - End of variables declaration //GEN-END:variables
//
//}
////public class PatientRegistration extends JDialog {}
