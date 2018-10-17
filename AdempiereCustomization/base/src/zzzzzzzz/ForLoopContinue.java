package zzzzzzzz;

public class ForLoopContinue
{
	public static void main(String[] args)
	{
		String texts[] = { "A", "B", "C", "D" };
		for (int i = 0; i < texts.length; i++)
		{
			if (texts[i].equalsIgnoreCase("B"))
				continue;
			System.out.println(texts[i]);
		}
	}
}
