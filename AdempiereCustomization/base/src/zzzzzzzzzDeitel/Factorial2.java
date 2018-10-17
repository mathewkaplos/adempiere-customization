package zzzzzzzzzDeitel;

import java.math.BigInteger;

public class Factorial2
{

	public static BigInteger factorial(BigInteger number)
	{
		if (number.compareTo(BigInteger.ONE) <= 0)
			return BigInteger.ONE;
		else
			return number.multiply(factorial(number.subtract(BigInteger.ONE)));
	}

	public static void main(String[] args)
	{
		System.out.println();
		for (int counter = 0; counter <= 100; counter++)
			System.out.printf("%d! = %d\n", counter, factorial(BigInteger.valueOf(counter)));
	}

	public static void getValyue()
	{
		System.out.println("this is a test...");
		System.out.println("Hello world.");
	}

}
