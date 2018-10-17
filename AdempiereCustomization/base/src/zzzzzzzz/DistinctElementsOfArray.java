package zzzzzzzz;

/* Java program to print all distinct elements of a given array */
import java.util.*;

public class DistinctElementsOfArray
{

	// This function prints all distinct elements
	static void printDistinct(int arr[])
	{
		// Creates an empty hashset
		HashSet<Integer> set = new HashSet<>();

		// Traverse the input array
		for (int i = 0; i < arr.length; i++)
		{
			// If not present, then put it in hashtable and print it
			if (!set.contains(arr[i]))
			{
				set.add(arr[i]);
				System.out.print(arr[i] + " ");
			}
		}
		System.out.println();
		for (Integer s : set) {
		
		    System.out.println(s);
		}
		System.out.println();
	}

	// Driver method to test above method
	public static void main(String[] args)
	{
		int arr[] = { 10, 5, 3, 4, 3, 5, 6 };
		printDistinct(arr);
	}
	
}