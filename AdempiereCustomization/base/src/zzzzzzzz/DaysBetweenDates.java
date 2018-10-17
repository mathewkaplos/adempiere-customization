package zzzzzzzz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//import org.joda.time.LocalDate;

public class DaysBetweenDates
{
	public static void main(String[] args)
	{
		//
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		System.out.println(date);
		//
	}

	public static long getDifferenceDays(Date d1, Date d2)
	{
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
}
