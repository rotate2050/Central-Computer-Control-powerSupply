package Network;

import java.net.*;
import java.util.*;
import java.io.*;

public class Client {
	private static String inpacket, outpacket;
	private static final int port = 2323;
	public static InetAddress ip_client;
	public static String server = "", client;

	public static void main(String a[]) throws Exception {
		packetProcessor pproc = new packetProcessor();
		Socket serv = new Socket(server, port);
		ip_client = InetAddress.getLocalHost();
		client = ip_client.getHostAddress();
		System.loadLibrary("portcommlib");
		System.out.println(ip_client);
		Scanner sserv = new Scanner(serv.getInputStream());
		PrintWriter pserv = new PrintWriter(serv.getOutputStream(), true);
		pserv.println("lab0");
		for (;;) {
			inpacket = sserv.nextLine();
			System.out.println(inpacket);
			outpacket = pproc.processPacket(inpacket);
			pserv.println(outpacket);
		}
	}
}

class packetProcessor {
	String output, msg_parts[];
	int writeport = 0;

	String processPacket(String input) {
		msg_parts = input.split("_");
		if (msg_parts.length <= 2 || msg_parts.length > 4) {
			return ("invalid message");
		}
		if (!msg_parts[0].contains(Client.server)) {
			return ("invalid sender");
		} else if (msg_parts.length == 3) {
			if (msg_parts[2].equals("feedback")) {
				// jni call to the feedback method
				int i = new natives().readPort();
				System.out.println("::Testread::" + i);
				output = (Client.client + "_" + Client.server + "_" + i)
						.toString();
			}
			// else{
			// return("invalid message");
			// }
		} else {
			if (msg_parts[2].equals("write")) {
				System.out.println("::testwrite::");
				writeport = Integer.parseInt(msg_parts[3]);
				// jni call to write the no on port
				int i = new natives().writePort(writeport);
				output = (Client.client + "_" + Client.server + "_" + i)
						.toString();
			} else {
				return ("invalid message");
			}
		}
		return (output);
	}
}

class natives {
	public native int readPort();

	public native int writePort(int i);
}
