package zzzzzzzz;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
* @author Crunchify.com
*
*/

public class CrunchifyCopyOnWriteArrayList {

	public static void main(String[] args) {

		// Disable this to avoid ConcurrentModificationException
		List<String> companies = new ArrayList<>();

		// Enable this to avoid ConcurrentModificationException
		// List<String> companies = new CopyOnWriteArrayList<>();

		companies.add("Google");
		companies.add("Yahoo");
		companies.add("Facebook");
		companies.add("eBay");
		companies.add("Microsoft");

		// Get an iterator over a collection. Iterator takes the place of
		// Enumeration in the Java Collections Framework.
		Iterator<String> crunchifyIterator = companies.iterator();

		// Make changes to companies List while performing hasNext()
		while (crunchifyIterator.hasNext()) {

			System.out.println("companies list: " + companies);

			String crunchifyString = crunchifyIterator.next();

			// Test1: Below statement causes ConcurrentModificationException
			System.out.println(crunchifyString);
			if (crunchifyString.equals("Yahoo"))
				// modCount = 6
				companies.remove("Microsoft");
			if (crunchifyString.equals("eBay"))
				companies.add("My Message Goes here... eBay present");

			// Test2: Below change wont throw ConcurrentModificationException
			// Reason: It doesn't change modCount variable of "companies"
			if (crunchifyString.equals("Google"))
				companies.set(2, "Google");

		}
	}

}