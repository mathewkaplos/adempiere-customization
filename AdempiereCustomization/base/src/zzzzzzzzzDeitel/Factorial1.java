package zzzzzzzzzDeitel;

public class Factorial1
{

	public static void main(String[] args)
	{
		int factorial = 1;
		int number = 3;
		for (int counter = number; counter >= 1; counter--)
		{
			factorial *= counter;
		}
		System.out.println(number + "! =" + factorial);
	}

}
