package zenith.process;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

public class Age
{
	public static void main(String[] args)
	{
		LocalDate bd = LocalDate.of(1993, 10, 10);
		LocalDate cd = LocalDate.of(2017, 06, 07);

		System.out.println(Period.between(bd, cd).getDays());
	}

	public static int calculateAge(LocalDate birthDate)
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		 LocalDate today =now.toLocalDateTime().toLocalDate();
		if ((birthDate != null) && (today != null))
		{
			return Period.between(birthDate, today).getYears();
		} else
		{
			return 0;
		}
	}
}
