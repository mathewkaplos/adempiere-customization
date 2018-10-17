package zzzzzzzz;

import java.math.BigDecimal;

public class RemoveTrailingZeros
{

	public static void main(String[] args)
	{
		BigDecimal bd = new BigDecimal("15.012000");

		System.out.println(bd.stripTrailingZeros());
	}

}
