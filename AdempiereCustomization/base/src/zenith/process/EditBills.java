package zenith.process;

import java.awt.Component;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import zenith.model.MBilling;
import zenith.model.X_hms_edit_bills_log;

public class EditBills extends SvrProcess
{
	private int hms_billing_ID = 0;
	private String edit_type = "";
	private String reason = "";

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("edit_type"))
				edit_type = para[i].getParameterAsString();
			else if (name.equals("reason"))
				reason = para[i].getParameterAsString();
			else
			{

			}
			// log.log(Level.SEVERE, "Unknown Parameter: " + name);

		}
		hms_billing_ID = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception
	{
		if (edit_type.equalsIgnoreCase(CreateHospitalDefaults.EDIT_TYPE_DELETE))
		{
			delete();
		}
		return null;
	}

	private void delete()
	{
		MBilling bill = new MBilling(getCtx(), hms_billing_ID, get_TrxName());
		if (bill.ispaid() && 1 == 2) // disable
		{
			JOptionPane.showMessageDialog(null, new JLabel(
					"<html><h1><font color='red'>Bill Already Paid...Not Editable! <br>" + "</br></font></h1></html>"),
					"Not Edited!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (bill.isinvoiced() && 1 == 2) // disable
		{
			JOptionPane.showMessageDialog(null,
					new JLabel("<html><h1><font color='red'>Bill Already Invoiced...Not Editable! <br>"
							+ "</br></font></h1></html>"),
					"Not Edited!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int x = yesnocancel("Are you sure you want to do this?. Note that this will not be undone!");
		if (x == 0)
		{

			X_hms_edit_bills_log log = new X_hms_edit_bills_log(getCtx(), 0, get_TrxName());
			log.setC_BPartner_ID(bill.getC_BPartner_ID());
			log.setM_Product_ID(bill.getM_Product_ID());
			log.setPrice(bill.getPrice());
			log.setQty(bill.getQty());
			log.setGrandTotal(bill.getLineNetAmt());
			log.setreason(reason);
			log.setedit_type(edit_type);
			log.save();

			bill.delete(true);

			JOptionPane.showMessageDialog(null,
					new JLabel("<html><h1><font color='green'>Done....! <br>" + "</br></font></h1></html>"), "Edited!",
					JOptionPane.ERROR_MESSAGE);

		} else if (x == 1)
		{
			return;
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
}
