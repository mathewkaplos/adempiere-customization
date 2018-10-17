package zzzzzzzz;

import java.awt.Component;

import javax.swing.JOptionPane;

public class YesNoCancelMain
{
	public static void main(String[] argv) throws Exception
	{
		int i = yesnocancel("Are your sure ?");
		System.out.println("ret : " + i);
	}

	public static int yesnocancel(String theMessage)
	{
		int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "alert",
				JOptionPane.YES_NO_CANCEL_OPTION);
		return result;
	}
}