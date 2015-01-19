import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;

public class MainExe {

	public static void main(String[] args) {
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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeGui frame = new MakeGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}