package zzzzzzzzzDeitel;

import java.math.BigInteger;

public class Fibonacci1
{
	private static BigInteger TWO = BigInteger.valueOf(2);

	public static BigInteger fibonacci(BigInteger number)
	{
		if (number.equals(BigInteger.ZERO) || number.equals(BigInteger.ONE))
			return number;
		return fibonacci(number.subtract(BigInteger.ONE)).add(fibonacci(number.subtract(TWO)));
	}

	public static void main(String[] args)
	{
		for (int counter = 0; counter <= 100; counter++)
		{
			System.out.printf("The Fibonacci of %d : is %d\n", counter, fibonacci(BigInteger.valueOf(counter)));
		}
	}

}
