package CCCPS;

import gui.Gui;
import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;
import Network.ServerCCCPS;

public class Main {

	public static void main(String[] s) {
		try {
			File fl = new File("D:\\prjdev\\MyJava\\Settings.conf");
			Scanner sc = new Scanner(fl);
			sc.nextLine();
			String line = sc.nextLine();
			SecureIt sit = new SecureIt("ABCEDFGH");
			String[] db = sit.decrypt(line).split(":");
			Dbcon.host = db[0];
			Dbcon.port = db[1];
			Dbcon.sid = db[2];
			Dbcon.uname = db[3];
			Dbcon.pass = db[4];
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread t = new Thread(new Runnable() {
			ServerCCCPS scccps;

			public void run() {
				scccps = new ServerCCCPS();
			}
		});
		t.setName("server_infinite");
		t.start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Thread.sleep(10000);
					new reDrawCaller(new Gui()).start();// .drawAgain();
				} catch (Exception e) {
					e.printStackTrace();
					// new HandleException(e.getMessage());
				}
			}
		});
	}
}

class reDrawCaller extends Thread {
	Gui g;

	public reDrawCaller(Gui g) {
		super("GUIRedrawer");
		this.g = g;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
				g.drawAgain();
			} catch (Exception e) {
				// new HandleException(e.getMessage());
			}
		}
	}
}
