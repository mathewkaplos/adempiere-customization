package zenith.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LoopThrougthDates
{

	public static void main(String[] args) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2010-12-20");
		Date endDate = formatter.parse("2010-12-26");
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
		    // Do your job here with `date`.
		    System.out.println(date);
		}

	}

}
