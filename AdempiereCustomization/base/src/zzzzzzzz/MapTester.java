//package zzzzzzzz;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//import com.google.common.collect.MapDifference;
//import com.google.common.collect.Maps;
//
//public class MapTester
//{
//
//	public static void main(String[] args)
//	{
//		HashMap<Integer, String> map = new HashMap<>();
//
//		HashMap<Integer, String> map1 = new HashMap<>();
//		HashMap<Integer, Integer> map2 = new HashMap<>();
//		Set<Integer> s = new HashSet<>();
//		s.add(1);
//		s.add(5);
//
//		map.put(1, "One");
//		map.put(2, "Two");
//		map1.put(3, "Three");
//		map1.put(8, "eight");
//		map.put(10, "ten");
//		map.put(4, "Four");
//		map.put(7, "Fiveer");
//		
//		
//		
//		map1.put(1, "One");
//		map1.put(2, "Two");
//		map1.put(3, null);
//		map1.put(9, "nine");
//		map1.put(8, "eigth");
//		
//		
//		map2.put(1, 1);
//		map2.put(2, 4);
//		map2.put(3, 9);
//		map2.put(1, 16);
//	//	System.out.println(map.size());
//		// map.removeAll(s);
//	//	System.out.println(map.size());
//
//		MapDifference<Integer, String> diff = Maps.difference(map1, map);
//		Set<Integer> keysOnlyInSource = diff.entriesOnlyOnLeft().keySet();
//		Set<Integer> keysOnlyInTarget = diff.entriesOnlyOnRight().keySet();
//		System.out.println("map 1 =:"+keysOnlyInSource.size());
//		System.out.println("map =:"+keysOnlyInTarget.size());
//		System.out.println("sdjndfjeds..");
//		for (Iterator iterator = keysOnlyInTarget.iterator(); iterator.hasNext();)
//		{
//			Integer integer = (Integer) iterator.next();
//			System.out.println(integer);
//			
//		}
//		System.out.println("djjsdn");
//		for (Integer integer : keysOnlyInSource)
//		{
//		
//			System.out.println(integer);
//		}
//	}
//
//}
