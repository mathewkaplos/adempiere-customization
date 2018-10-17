package zzzzzzzz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueSet
{
	// driver
	public static void main(String[] args)
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(5);
		list.add(5);

		Set<Integer> u = new HashSet<Integer>(list);

		Integer[] arr = new Integer[u.size()];
		u.toArray(arr);

		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
}
