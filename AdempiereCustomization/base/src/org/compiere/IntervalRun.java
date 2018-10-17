package org.compiere;

import java.sql.Timestamp;
import java.util.Date;

import org.compiere.util.Env;

public class IntervalRun
{

	public static void main(String[] args)
	{
		runIt();

	}

	static void runIt()
	{
		try
		{
			long oneDay = 24 * 60 * 60 * 1000;
			while (true)
			{
				
				Timestamp time = new Timestamp(System.currentTimeMillis() + oneDay);
				Env.setContext(Env.getCtx(), "#Date", time);
				oneDay =oneDay +oneDay;
				Thread.sleep(5 * 1000);
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
