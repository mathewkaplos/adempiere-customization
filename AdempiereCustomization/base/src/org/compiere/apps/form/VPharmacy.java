package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.compiere.apps.AEnv;
import org.compiere.apps.StatusBar;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zenith.util.HmsSetup;
import org.compiere.grid.ed.Dispense;
import org.compiere.grid.ed.SelectPharmacy;

public class VPharmacy extends Pharmacy implements FormPanel, ActionListener, TableModelListener, VetoableChangeListener
{
	private boolean combinedPharmacy = false;

	private CPanel panel = new CPanel();
	private int m_WindowNo;
	private FormFrame m_frame;
	private StatusBar statusBar = new StatusBar();
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CButton btnOpenDispense = new CButton();
	private CButton btnOpenDispenseOutpatient = new CButton();
	private CButton btnOpenDispenseInpatient = new CButton();
	private static MiniTable orderTable = new MiniTable();
	private CPanel orderPanel = new CPanel();
	private BorderLayout orderLayout = new BorderLayout();
	private JLabel orderInfo = new JLabel();

	private JScrollPane orderScrollPanel = new JScrollPane();
	private JLabel orderLabel = new JLabel();
	private JSplitPane infoPanel = new JSplitPane();

	private CPanel parameterPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();

	// private JButton newButton = new JButton();

	/**
	 * Initialize Panel
	 * 
	 * @param WindowNo
	 *            window
	 * @param frame
	 *            frame
	 */
	@Override

	public void init(int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		combinedPharmacy = HmsSetup.getSetup().ispharmacy_combined();

		try
		{
			m_frame.setMaximize(true);
			super.dynInit();
			dynInit();
			jbInit();

			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);

		} catch (Exception e)
		{

		}
	}

	/**
	 * Dynamic Init (prepare dynamic fields)
	 * 
	 * @throws Exception
	 *             if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception
	{

		// BPartner
		int AD_Column_ID = 2762; // C_Order.C_BPartner_ID
		MLookup lookupBP = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		int C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), 2, "C_BPartner_ID", true);
		int hms_treatment_doc_ID = Env.getContextAsInt(Env.getCtx(), 2, "hms_treatment_doc_ID", true);
		m_C_BPartner_ID = C_BPartner_ID;
		m_hms_treatment_doc_ID = hms_treatment_doc_ID;
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "AllocateStatus"));
		statusBar.setStatusDB("");

	}

	private void jbInit()
	{
		CompiereColor.setBackground(panel);

		// orderTable.setMultiSelection(true); // Should be performed before the
		// class is set.

		// orderTable.setSurrendersFocusOnKeystroke(true);

		mainPanel.setLayout(mainLayout);

		parameterPanel.setLayout(parameterLayout);

		btnOpenDispense.setText("OPEN DISPENSE WINDOW");
		btnOpenDispenseOutpatient.setText("OUTPATIENT DISPENSE WINDOW");
		btnOpenDispenseInpatient.setText("INPATIENT DISPENSE WINDOW");

		Font font = new Font("Courier", Font.BOLD, 22);
		btnOpenDispense.setFont(font);
		btnOpenDispense.addActionListener(this);

		btnOpenDispenseOutpatient.setFont(font);
		btnOpenDispenseOutpatient.addActionListener(this);

		btnOpenDispenseInpatient.setFont(font);
		btnOpenDispenseInpatient.addActionListener(this);

		orderLabel.setText("Drug  Orders");

		orderPanel.setLayout(orderLayout);

		orderInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		orderInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		orderInfo.setText(".");

		mainPanel.add(parameterPanel, BorderLayout.NORTH);

		orderScrollPanel.setPreferredSize(new Dimension(200, 500));
		if (combinedPharmacy)
		{
			parameterPanel.add(btnOpenDispense, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
					GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		} else
		{
			parameterPanel.add(btnOpenDispenseOutpatient, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			parameterPanel.add(btnOpenDispenseInpatient, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		}
		orderPanel.add(orderLabel, BorderLayout.NORTH);
		orderPanel.add(orderInfo, BorderLayout.SOUTH);
		orderPanel.add(orderScrollPanel, BorderLayout.CENTER);
		orderScrollPanel.getViewport().add(orderTable, null);

		//
		mainPanel.add(infoPanel, BorderLayout.CENTER);
		infoPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		infoPanel.setBorder(BorderFactory.createEtchedBorder());

		infoPanel.setBottomComponent(orderPanel);
		infoPanel.add(orderPanel, JSplitPane.TOP);

		infoPanel.setContinuousLayout(true);
		infoPanel.setPreferredSize(new Dimension(1200, 500));
		infoPanel.setDividerLocation(0);

		//

	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnOpenDispense))
		{
			Dispense dispense = new Dispense((Frame) null, 1000007, "");
			AEnv.showCenterScreen(dispense);
		}
		if (e.getSource().equals(btnOpenDispenseOutpatient))
		{
			String whereClause = " AND doc.admitted ='N' ";

			Dispense dispense = new Dispense((Frame) null, 1000007, whereClause);
			AEnv.showCenterScreen(dispense);
		}
		if (e.getSource().equals(btnOpenDispenseInpatient))
		{
			SelectPharmacy selectPharmacy = new SelectPharmacy((Frame) null, "Inpatient dispensing window",
					" AND doc.admitted ='Y' ");
			AEnv.showCenterScreen(selectPharmacy);
		}
	}

	@Override
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}
}
