package zenith.util;

import java.net.*;
import java.io.*;

public class smsSend
{
	public static void send(String messageBody, String telNo)
	{
		try
		{
			// Construct data
			String data = "";
			/*
			 * Note the suggested encoding for certain parameters, notably the
			 * username, password and especially the message. ISO-8859-1 is
			 * essentially the character set that we use for message bodies,
			 * with a few exceptions for e.g. Greek characters. For a full list,
			 * see:
			 * http://developer.bulksms.com/eapi/submission/character-encoding/
			 */
			data += "username=" + URLEncoder.encode("dasayantist", "ISO-8859-1");
			data += "&password=" + URLEncoder.encode("kip3018@@", "ISO-8859-1");
			data += "&message=" + URLEncoder
					.encode(messageBody, "ISO-8859-1");
			data += "&want_report=1";
			data += "&msisdn=+254"+telNo;

			// Send data
			// Please see the FAQ regarding HTTPS (port 443) and HTTP (port
			// 80/5567)
			URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
			// URL url = new
			// URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null)
			{
				// Print the response output...
				System.out.println(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static public void main(String[] args)
	{
		try
		{
			// Construct data
			String data = "";
			/*
			 * Note the suggested encoding for certain parameters, notably the
			 * username, password and especially the message. ISO-8859-1 is
			 * essentially the character set that we use for message bodies,
			 * with a few exceptions for e.g. Greek characters. For a full list,
			 * see:
			 * http://developer.bulksms.com/eapi/submission/character-encoding/
			 */
			data += "username=" + URLEncoder.encode("dasayantist", "ISO-8859-1");
			data += "&password=" + URLEncoder.encode("kip3018@@", "ISO-8859-1");
			data += "&message=" + URLEncoder
					.encode("Hello Mathew, Valued Customer,   From zenith Business Solution ltd", "ISO-8859-1");
			data += "&want_report=1";
			data += "&msisdn=+254710947709";

			// Send data
			// Please see the FAQ regarding HTTPS (port 443) and HTTP (port
			// 80/5567)
			URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
			// URL url = new
			// URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null)
			{
				// Print the response output...
				System.out.println(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}