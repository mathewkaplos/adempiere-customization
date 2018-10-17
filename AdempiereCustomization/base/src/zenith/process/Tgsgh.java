package zenith.process;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tgsgh
{
	public static void main(String[] args)
	{
		// Math.min(Double.MIN_VALUE, 0.0d)
		// System.out.println(Math.max(Double.MAX_VALUE, 0.0d));
		char[] chars = new char[] { '\u00AE' };
		String str = new String(chars);
		byte[] bytes = str.getBytes();
		/// System.out.println(Arrays.toString(bytes));
		//for (int i = 0; i < bytes.length; i++)
		//{
		//	System.out.println(toHex(bytes[i]));
		//}
		byte b = '\u0012';
		//System.out.println(toHex(b));
		String fd="";
		StringBuilder sb = new StringBuilder();
		
		//System.out.println(sb.toString());
		System.out.println(fd);
	}

	static public String toHex(byte b)
	{
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(array);
	}
	void kkd(){
		Map<String , Integer> m = new HashMap<>();
	}
}
