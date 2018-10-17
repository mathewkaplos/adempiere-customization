package zenith.process;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.compiere.apps.AEnv;
import org.compiere.apps.search.Info;
import org.compiere.process.SvrProcess;
import org.compiere.swing.CFrame;

public class ZZZ extends SvrProcess
{
	
	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub
		System.out.println("Ca");
	}

	@Override
	protected String doIt() throws Exception
	{
		Form form = new Form();
		Info info = null;
		info = new TestViewer(form, false, 0, 0, "", false, false, "");
		AEnv.positionCenterWindow(form, info);
		return null;
	}

}

class Form extends CFrame implements ActionListener, ChangeListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6343948281657164642L;

	Form()
	{
		setTitle("Test");
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setVisible(true);

	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
