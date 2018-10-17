package zenith.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public class TimeStuff
{
	public static void main(String[] args)
	{

		BigDecimal number = new BigDecimal(2642.40);
		BigDecimal divisor = new BigDecimal(100);
		BiFunction<BigDecimal, BigDecimal, BigDecimal> f1 = (num, div) -> div
				.multiply(((num.divide(div)).setScale(0, RoundingMode.CEILING)));

		System.out.println("BiFunction :" + f1.apply(number, divisor));

		// lamda
		Foo f = (BigDecimal v, BigDecimal m) -> m.multiply(((v.divide(m)).setScale(0, RoundingMode.CEILING)));

		BigDecimal r = f.doIt(number, divisor);
		System.out.println(r);
		// inner class
		Foo foo = new Foo() {
			public BigDecimal doIt(BigDecimal value, BigDecimal upto)
			{
				return value = upto.multiply(((value.divide(upto)).setScale(0, RoundingMode.CEILING)));
			}
		};
		System.out.println(foo.doIt(number, divisor));
	}
}

interface Foo
{
	BigDecimal doIt(BigDecimal value, BigDecimal upto);
}
// }
