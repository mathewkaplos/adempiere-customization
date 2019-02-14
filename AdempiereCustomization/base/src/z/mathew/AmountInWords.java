package z.mathew;

public class AmountInWords
{

	public static void main(String[] args)
	{
		double one = 123.00;
		if (one % 1 == 0)
		{
			System.out.println("whole");
		}
		{
			System.out.println("decimal");
		}
		String WholeNumber = "", Decimal = "";
		String StringOfOne = Double.toString(one);
		boolean isDecimal = false;
		System.out.println(StringOfOne);
		int decimalIndex = StringOfOne.lastIndexOf(".");
		System.out.println("decimalIndex:" + decimalIndex);

		if (decimalIndex > 0)
		{
			WholeNumber = StringOfOne.substring(0, decimalIndex);
			Decimal = StringOfOne.substring(decimalIndex + 1, StringOfOne.length())
					+ StringOfOne.substring(StringOfOne.length());

			// now you have the WholeNumber split up.
			isDecimal = true;
		} else
			WholeNumber = StringOfOne;

		if (isDecimal)
			outputWholeAndDecimal();
		else
			outputWhole();

	}

	private static void outputWhole()
	{
		System.out.println("whole");

	}

	private static void outputWholeAndDecimal()
	{
		System.out.println("whole and decimal");

	}

}
