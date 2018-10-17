package zenith.process;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.search.Info;
import org.compiere.swing.CLabel;
import org.compiere.swing.CTextField;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class TestViewer extends Info implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private CTextField fieldValue = new CTextField(10);
	private CLabel labelName = new CLabel();
	private CTextField fieldName = new CTextField(10);
	private static final long serialVersionUID = -2078566832243548043L;

	protected TestViewer(Frame frame, boolean modal, int WindowNo, int record_id, String value, boolean multiSelection,
			boolean saveResults, String whereClause)
	{
		// super(frame, modal, WindowNo, tableName, keyColumn,
		// multiSelection, saveResults, whereClause);
		super(frame, modal, WindowNo, "p", "hms_test", multiSelection, saveResults, whereClause);
		setTitle("Test Test test Service");
		statInit();
		// TODO Auto-generated constructor stub
	}

	void statInit()
	{
		labelName.setText(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.setBackground(AdempierePLAF.getInfoBackground());
		fieldName.addActionListener(this);
		p_criteriaGrid.add(labelName, new ALayoutConstraint(1, 0));
		p_criteriaGrid.add(fieldName, null);
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String getSQLWhere()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		// TODO Auto-generated method stub

	}

}
