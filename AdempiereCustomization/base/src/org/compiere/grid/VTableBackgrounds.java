package org.compiere.grid;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class VTableBackgrounds
{
	private int rowNum;
	private Color color;
	private Font font;
	private final Font FONT = (new JLabel()).getFont();

	public int getRowNum()
	{
		return this.rowNum;
	}

	public void setRowNum(int rowNum)
	{
		this.rowNum = rowNum;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public Color getColor()
	{
		return this.color;
	}

	public void setFont(Font font)
	{
		this.font = font;
	}

	public Font getFont()
	{
		new Font("Verdana", 0, 12);
		return this.FONT;
	}
}
