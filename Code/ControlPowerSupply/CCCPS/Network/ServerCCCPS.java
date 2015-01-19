package Network;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// return bit pattern for all devices(labvise)

public class ServerCCCPS extends Thread {
	public static volatile List<lab> labs_list = new ArrayList<lab>();
	public static lab labs_connected[] = new lab[6];
	public static int no_of_labs = 0;
	static InetAddress server_ip;
	private Socket client;

	public ServerCCCPS() {
		super("deadRemover");
		this.start();
		try {

			ServerSocket sserv = new ServerSocket(2323);
			server_ip = sserv.getInetAddress();
			// Socket client;
			while ((client = sserv.accept()) != null) {
				lab l = new lab(client);
				l.start();
				synchronized (labs_list) {
					// labs_list.add(l);
				}
				// (labs_connected[no_of_labs] = new lab(client)).start();
				// System.out.println(labs_connected[no_of_labs].name);
				no_of_labs++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// showException(e.getMessage());
		}
	}

	public void run() {
		/*
		 * Iterator<lab> it;// = labs_list.iterator(); while(true){ try { it =
		 * labs_list.iterator();
		 * 
		 * Thread.currentThread().sleep(10000);
		 * 
		 * while (it.hasNext()) { if (!it.next().isAlive()) { it.remove(); } } }
		 * catch (Exception e) {
		 * 
		 * } //synchronized (labs_list){ //for(lab l : labs_list){ //
		 * if(!l.isAlive()){ // labs_list.remove(l); // } //} //} }
		 * 
		 * //t.setName("deadRemover"); //t.start();
		 */
	}

	public static String getFeedbackWrapper(String lab_id) {
		int feed = 0;
		String bin_feed;
		labs_connected = labs_list.toArray(labs_connected);
		for (int i = 0; i < no_of_labs; i++) {//
			if (lab_id.equals(labs_connected[i].name)) {
				// generate integer and call the sendRequest method of the
				// selected object
				feed = labs_connected[i].getFeedBack();
				bin_feed = Integer.toBinaryString(feed);
				while (bin_feed.length() < 8) {// convert into 8 bit
												// representsation
					bin_feed = "0".concat(bin_feed);
				}
				System.out.println(bin_feed);
				return (bin_feed);
			}
		}
		return ("-1");// indicates that either the lab object hans't been
						// initialized or it has been destroyed
	}

	public static void sendMessge(String lab_id, int dev_id, int status) {// status
																			// corresponds
																			// to
																			// the
																			// new
																			// or
																			// next
																			// status
																			// of
																			// device
		System.out.println("in the sender:::" + lab_id);
		labs_connected = labs_list.toArray(labs_connected);
		for (int i = 0; i < no_of_labs; i++) {
			if (lab_id.equals(labs_connected[i].name)) {
				// generate integer and call the sendRequest method of the
				// selected object
				labs_connected[i].sendRequest(generateInt(dev_id, status,
						labs_connected[i]));
				System.out.println("sending::"
						+ generateInt(dev_id, status, labs_connected[i]));
				break;
			}
		}
	}

	private static int generateInt(int i, int j, lab l) {
		String bin_feed = Integer.toBinaryString(l.feedback);
		while (bin_feed.length() < 8) {// convert into 8 bit representation
			bin_feed = "0".concat(bin_feed);
		}
		StringBuffer sbuff = new StringBuffer();
		if (Character.toString(bin_feed.charAt(i)).equals(Integer.toString(j))) {// no
																					// changes
																					// necessary
																					// return
																					// old
																					// feed
																					// back
			return (l.feedback);
		} else {
			for (int count = 0; count < 8; count++) {
				if (count == i) {// change the i'th bit to j
					sbuff.append(j);
				} else {// keep all other bits the same
					sbuff.append(bin_feed.charAt(count));
				}
			}

			// bin_feed.charAt(i) = 'p';
			return (Integer.parseInt(sbuff.toString(), 2));
		}
		// return(1);
	}
	/*
	 * public static void main(String a[])throws Exception{ Scanner sin = new
	 * Scanner(System.in);
	 * 
	 * Thread t = new Thread(new Runnable() { ServerCCCPS scccps ;
	 * 
	 * @Override public void run() { /*ServerCCCPS
	 *//*
		 * scccps = new ServerCCCPS(); //throw new
		 * UnsupportedOperationException("Not supported yet."); } }); t.start();
		 * //= new ServerCCCPS(); Thread.currentThread().sleep(10000);
		 * ServerCCCPS.sendMessge("lab0",3,1); System.out.println("ends"); }
		 */
}