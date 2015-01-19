package Network;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

public class lab extends Thread {
	Socket client;
	Scanner sclient;
	PrintWriter pclient;
	String from_client, msg_parts[];
	InetAddress client_ip;
	String name;
	public static volatile int feedback = 0;

	private int checkinthelist(String n) {
		Iterator<lab> it = ServerCCCPS.labs_list.iterator();
		lab l;
		while (it.hasNext()) {
			l = (lab) it.next();
			if (l.name.equalsIgnoreCase(n))
				return (ServerCCCPS.labs_list.indexOf(n));
		}
		return (-1);
	}

	public lab(Socket c) throws Exception {
		super();
		// name = ("lab"+ServerCCCPS.no_of_labs).toString();
		// System.out.println(name);
		this.client = c;
		sclient = new Scanner(client.getInputStream());
		name = sclient.nextLine();
		super.setName(name);
		pclient = new PrintWriter(client.getOutputStream(), true);

		if (checkinthelist(name) == -1)
			ServerCCCPS.labs_list.add(this);
		else {
			ServerCCCPS.labs_list.remove(checkinthelist(name));
			ServerCCCPS.labs_list.add(this);
		}

		client_ip = client.getInetAddress();
	}

	public void run() {

		while (true) {
			// System.out.println(feedback+":::"+this.name);
			// ServerCCCPS.labs_connected =
			// (lab[])ServerCCCPS.labs_list.toArray();
			// for(int i=0;i<ServerCCCPS.labs_connected.length;i++)
			// System.out.print("Labs connected:"+ServerCCCPS.labs_connected[i]);
			feedback = getFeedBack();
			try {
				Thread.sleep(10000);
			} catch (Exception e) {

			}
		}
	}

	synchronized public int getFeedBack() {// simple feedback getter
		String temp = generateMessage("feedback");
		pclient.println(temp);
		from_client = sclient.nextLine();
		System.out.println(from_client);
		try {
			if (from_client.contains("invalid")) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("invlid message::feedback");
			// call to generic exception handler.
		}
		// feedback = extractFeedback(from_client);
		return (extractFeedback(from_client));
	}

	private int extractFeedback(String msgin) {// just tear off all the
												// authentication content and
												// return the actual data
		msg_parts = msgin.split("_");
		System.out.println(this.client_ip.toString());
		if ((msg_parts[0]).contains(this.client_ip.getHostAddress())) {// check
																		// authenticity
																		// of
																		// sender
			try {
				if (Integer.parseInt(msg_parts[2]) < 0)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("invalid message::sendrequest");
			}
			System.out.println("feedback from " + this.name + msg_parts[2]);
			return (Integer.parseInt(msg_parts[2]));
		} else
			return (300);

	}

	synchronized public int sendRequest(int i) {// called from GUI ,will send
												// the message to the client
		String temp = generateMessage(("write_" + i).toString());
		pclient.println(temp);
		System.out.println("sending::" + temp);
		from_client = sclient.nextLine();
		System.out.println(from_client);
		try {
			if (from_client.contains("invalid")) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("invlid message::sender");
			// call to generic exception handler.
		}
		return (extractFeedback(from_client));
	}

	private String generateMessage(String msgout) {
		return (("100.100.122.190" + "_" + this.getName() + "_" + msgout)
				.toString());
	}

}