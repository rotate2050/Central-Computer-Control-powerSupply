package gui;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import report.Att_lesss;
import report.Attendance_report_irregular;
import report.ClassVise;
import report.New_attendance_report;

import CCCPS.Dbcon;

public class Report {
	private JFileChooser fc;
	JDialog ui;
	String path;
	String enrollnm;
	String cmd;
	String semester, percentage;

	Report(JDialog jm, String text, String rp) {
		enrollnm = text;
		ui = jm;
		cmd = rp;
		fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(ui);
		try {
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				path = file.getAbsolutePath();

			}
		} catch (Exception ev) {
			System.out.println("Generic Exception catch.");
		}
	}

	Report(JDialog jm, String sem, String per, String rp) {
		semester = sem;
		ui = jm;
		cmd = rp;
		percentage = per;
		fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(ui);
		try {
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				path = file.getAbsolutePath();

			}
		} catch (Exception ev) {
			System.out.println("Generic Exception catch.");
		}
	}

	void pdfgenerator() {
		// int i;
		if (cmd.equals("StdAttRpt")) {
			Dbcon d = new Dbcon();
			d.open();
			ResultSet rs = d
					.selquery("SELECT f.\"Present\" FROM (select s.std_id as \"P\",count(a.std_id) as \"Present\" from attendance a,student_detail s where a.std_id(+)=s.std_id group by s.std_id) f where f.\"P\"="
							+ enrollnm);
			// d.close();

			int i = 7;
			try {
				// while(rs.)
				rs.next();
				i = Integer.parseInt(rs.getObject("Present").toString());
				System.out.println(" " + i);
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i == 0) {
				new Attendance_report_irregular(path, enrollnm);
			} else {
				new New_attendance_report(path, enrollnm);
			}
			// new New_attendance_report(path,enrollnm);
		}
		if (cmd.equals("ClassVise")) {
			new ClassVise(path, enrollnm);
		}

		if (cmd.equals("lslist")) {
			new Att_lesss(path, semester, percentage);
		}

		// System.out.println(cmd);
	}

}
