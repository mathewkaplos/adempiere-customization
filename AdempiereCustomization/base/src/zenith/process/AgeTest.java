package zenith.process;
/**

import java.time.LocalDate;

import org.junit.Test;

import junit.framework.Assert;

public class AgeTest
{

	@Test
	public void test()
	{
		LocalDate bd = LocalDate.of(1993, 10, 10);
		//LocalDate cd = LocalDate.of(2017, 06, 07);
		int actual = Age.calculateAge(bd);
		Assert.assertEquals(23, actual);
	}

}


*/