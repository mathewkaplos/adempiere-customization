package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.compiere.apps.AEnv;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.NewVitals;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CPanel;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zenith.util.HmsSetup;
import org.zenith.util.ZEnv;

import zenith.model.MVital;
import zenith.util.DateUtil;

import org.compiere.grid.ed.EditVitals;

public class VTriage extends Triage implements FormPanel, ActionListener, TableModelListener, VetoableChangeListener
{

	private CPanel panel = new CPanel();
	private int m_WindowNo;
	private FormFrame m_frame;
	private StatusBar statusBar = new StatusBar();
	private VLookup bpartnerSearch = null;
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private JLabel bpartnerLabel = new JLabel();

	private static MiniTable orderTable = new MiniTable();
	private CPanel orderPanel = new CPanel();
	private BorderLayout orderLayout = new BorderLayout();
	private JLabel orderInfo = new JLabel();

	private JScrollPane orderScrollPanel = new JScrollPane();
	private JLabel orderLabel = new JLabel();
	private JSplitPane infoPanel = new JSplitPane();

	private CPanel parameterPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();

	private JButton newButton = new JButton();

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

		try
		{
			if (HmsSetup.maximizeForms())
				m_frame.setMaximize(true);
			super.dynInit();
			dynInit();
			jbInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);

			LoadBP();
			doubleClick();
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
		bpartnerSearch = new VLookup("C_BPartner_ID", true, false, true, lookupBP);
		bpartnerSearch.addVetoableChangeListener(this);
		// int C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), 2,
		// "C_BPartner_ID", true);
		C_BPartner_ID = ZEnv.getC_BPartner_ID();
		m_hms_treatment_doc_ID = ZEnv.getHms_treatment_doc_ID();
		bpartnerSearch.setValue(ZEnv.getC_BPartner_ID());
		bpartnerSearch.setReadWrite(false);
		// Translation
		statusBar.setStatusLine(
				"Double Click on record to edit. You can only edit records which were created on the current date.");
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
		bpartnerLabel.setText("Patient Name:");

		newButton.setText("New Vitals");
		newButton.setPreferredSize(new Dimension(150, 30));
		newButton.setFont(new Font("Arial", Font.BOLD, 20));
		newButton.addActionListener(this);

		orderLabel.setText("Vital Signs");

		orderPanel.setLayout(orderLayout);

		orderInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		orderInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		orderInfo.setText(".");

		mainPanel.add(parameterPanel, BorderLayout.NORTH);

		orderScrollPanel.setPreferredSize(new Dimension(200, 500));

		parameterPanel.add(bpartnerLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(bpartnerSearch, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		parameterPanel.add(newButton, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//
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
		infoPanel.setPreferredSize(new Dimension(1020, 250));
		infoPanel.setDividerLocation(0);

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

		if (e.getSource().equals(newButton))
		{
			newVitalsWindow();
		}
	}

	private void newVitalsWindow()
	{
		NewVitals wewVitals = new NewVitals((Frame) null, ZEnv.getHms_treatment_doc_ID());
		AEnv.showCenterScreen(wewVitals);
	}

	private void doubleClick()
	{
		orderTable.addMouseListener(doubleClickML);
	}

	MouseListener doubleClickML = new MouseAdapter() {
		public void mousePressed(MouseEvent me)
		{
			MiniTable table = (MiniTable) me.getSource();
			Point p = me.getPoint();
			int row = table.rowAtPoint(p);

			if (me.getClickCount() == 2)
			{
				try
				{

					IDColumn idColumn = (IDColumn) orderTable.getValueAt(row, 0);
					m_vitals_ID = idColumn.getRecord_ID();
					if (editable(idColumn.getRecord_ID()))
					{
						EditVitals editVitals = new EditVitals((Frame) null, idColumn.getRecord_ID(),
								ZEnv.getHms_treatment_doc_ID());
						AEnv.showCenterScreen(editVitals);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	};

	private boolean editable(int recordID)
	{
		MVital vital = new MVital(Env.getCtx(), recordID, null);
		// DateUtil.

		if (DateUtil.newDate().before(DateUtil.getDateTime(vital.getCreated())))
			return true;
		else
			return false;
	}

	@Override
	public void dispose()
	{
		orderTable.removeMouseListener(doubleClickML);
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
	}

	public void LoadBP()
	{
		String whereClause = "";
		if (HmsSetup.getSetup().isdisplay_all_patient_treatments())
			whereClause = " WHERE bp.C_BPartner_ID =" + ZEnv.getC_BPartner_ID();
		else
			whereClause = " WHERE v.hms_treatment_doc_ID=" + ZEnv.getHms_treatment_doc_ID();

		
		Vector<Vector<Object>> data = getVitalsData(whereClause);
		Vector<String> columnNames = getVitalsColumnNames();
		// Remove previous listeners2
		orderTable.getModel().removeTableModelListener(this);

		// Set Model
		DefaultTableModel modelO = new DefaultTableModel(data, columnNames);
		modelO.addTableModelListener(this);
		orderTable.setModel(modelO);
		setVitalsColumnClass(orderTable);

	}
}
