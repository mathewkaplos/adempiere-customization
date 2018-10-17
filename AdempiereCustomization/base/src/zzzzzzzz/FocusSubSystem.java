package zzzzzzzz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FocusSubSystem
{

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				FocusSubSystem mframe = new FocusSubSystem();
			}
		});
	}

	public FocusSubSystem()
	{
		JFrame frame = new JFrame("Test");
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 400));

		JButton button2 = new JButton("I am not");
		panel.add(button2, BorderLayout.NORTH);

		JButton button1 = new JButton("I am the First");
		panel.add(button1, BorderLayout.SOUTH);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("JButton Size - " + button1.getSize());
				System.out.println("JPanel Size - " + panel.getSize());
				System.out.println("JFrame Size - " + frame.getSize());
			}
		});

		frame.getContentPane().add(panel);
		frame.setResizable(false);
		
		Dimension  dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x =dim.width/2 - frame.getSize().width/2;
		int y = dim.height/2 -frame.getSize().height/2;
		
		frame.setLocation(100, 100);
		//frame.setLocationRelativeTo(null);
		frame.pack();

		// This button will have the initial focus
		button1.requestFocusInWindow();
		frame.setVisible(true);
	}

}
