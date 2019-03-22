package ztest;

import java.awt.*;
import javax.swing.*;

public class SetColor
{
	public static void main(String[] args)
	{
		UIManager um = new UIManager();
		um.put("OptionPane.background", Color.yellow);
		um.put("Panel.background", Color.red);
		JOptionPane.showMessageDialog(null, "Hello", "Set Color", JOptionPane.INFORMATION_MESSAGE);
	}
}