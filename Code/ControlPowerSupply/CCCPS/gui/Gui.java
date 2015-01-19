package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import CCCPS.Dbcon;
import Network.lab;

public class Gui implements ActionListener {

	private JFrame frmCccpsControlpanel;
	public JTabbedPane tabbedPane;
	JPanel p1, p2, p3, p4, p5, p6;
	DeviceControlPanel lab0, lab1, lab2, lab3, lab4, lab5;

	public Gui() {
		initialize();
		frmCccpsControlpanel.setVisible(true);
	}

	private void initialize() {
		frmCccpsControlpanel = new JFrame();
		frmCccpsControlpanel.setTitle("CCCPS: ControlPanel");
		frmCccpsControlpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCccpsControlpanel.setLocation(80, 80);
		frmCccpsControlpanel.setMinimumSize(new Dimension(700, 240));

		JMenuBar menuBar = new JMenuBar();
		frmCccpsControlpanel.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);

		JMenu mnView_1 = new JMenu("view");
		menuBar.add(mnView_1);

		JMenuItem mntmLabStatus = new JMenuItem("Lab status");
		mntmLabStatus.addActionListener(this);
		mnView_1.add(mntmLabStatus);

		JMenu mnAttendance = new JMenu("Attendance");
		menuBar.add(mnAttendance);

		JMenu mnView = new JMenu("View");
		mnAttendance.add(mnView);

		JMenuItem mntmStudentViseView = new JMenuItem("Student Vise");
		mntmStudentViseView.setActionCommand("StdAttView");
		mntmStudentViseView.addActionListener(this);
		mnView.add(mntmStudentViseView);

		JMenu mnReport = new JMenu("Report");
		mnAttendance.add(mnReport);

		JMenuItem mntmStudentViseRpt = new JMenuItem("Student Vise");
		mntmStudentViseRpt.setActionCommand("StdAttRpt");
		mntmStudentViseRpt.addActionListener(this);
		mnReport.add(mntmStudentViseRpt);

		JMenuItem mntmClassVise = new JMenuItem("ClassVise");
		mntmClassVise.setActionCommand("ClassVise");
		mntmClassVise.addActionListener(this);
		mnReport.add(mntmClassVise);

		JMenuItem mntmLessAttendanceList = new JMenuItem("Less Attendance List");
		mntmLessAttendanceList.setActionCommand("lslist");
		mntmLessAttendanceList.addActionListener(this);
		mnReport.add(mntmLessAttendanceList);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);
		frmCccpsControlpanel.getContentPane().setLayout(new BorderLayout(5, 5));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmCccpsControlpanel.getContentPane().add(tabbedPane);

		p1 = new JPanel(new BorderLayout());
		p2 = new JPanel(new BorderLayout());
		p3 = new JPanel(new BorderLayout());
		p4 = new JPanel(new BorderLayout());
		p5 = new JPanel(new BorderLayout());
		p6 = new JPanel(new BorderLayout());

		lab0 = new DeviceControlPanel("F1", "lab0");
		lab1 = new DeviceControlPanel("F2", "lab1");
		lab2 = new DeviceControlPanel("F3", "lab2");
		lab3 = new DeviceControlPanel("F4", "lab3");
		lab4 = new DeviceControlPanel("F5", "lab4");
		lab5 = new DeviceControlPanel("F6", "lab5");

		tabbedPane.add("F1", p1);
		tabbedPane.add("F2", p2);
		tabbedPane.add("F3", p3);
		tabbedPane.add("F4", p4);
		tabbedPane.add("F5", p5);
		tabbedPane.add("F6", p6);

	}

	public void drawAgain() {
		List<lab> labs;
		labs = Network.ServerCCCPS.labs_list;
		Iterator<lab> iterator = labs.iterator();
		boolean status;
		lab l;
		String feedback;
		while (iterator.hasNext()) {
			l = iterator.next();
			status = l.isAlive();
			if (l.getName().equals("lab0")) {
				if (status && l.getName().equals("lab0")) {
					feedback = Network.ServerCCCPS.getFeedbackWrapper("lab0");
					for (int i = 0; i < 8; i++)
						if (feedback.charAt(i) == '1')
							lab0.setInitActioncmd("btn" + (i + 1), "on");
						else
							lab0.setInitActioncmd("btn" + (i + 1), "off");
					p1.removeAll();
					p1.add(lab0, BorderLayout.CENTER);
				} else {
					p1.removeAll();
					p1.add(new JLabel("Wait for some time"),
							BorderLayout.CENTER);
				}
				p1.revalidate();
				// SwingUtilities.updateComponentTreeUI(p1);
			} else if (l.getName().equals("lab1")) {
				if (status && l.getName().equals("lab1")) {
					feedback = Network.ServerCCCPS.getFeedbackWrapper("lab1");
					for (int i = 0; i < 8; i++)
						if (feedback.charAt(i) == '1')
							lab1.setInitActioncmd("btn" + (i + 1), "on");
						else
							lab1.setInitActioncmd("btn" + (i + 1), "off");
					p2.removeAll();
					p2.add(lab1, BorderLayout.CENTER);
				} else {
					p2.removeAll();
					p2.add(new JLabel("Wait for some time"),
							BorderLayout.CENTER);
				}
				p2.revalidate();
				// SwingUtilities.updateComponentTreeUI(p2);
			} else if (l.getName().equals("lab2")) {
				if (status && l.getName().equals("lab2")) {
					feedback = Network.ServerCCCPS.getFeedbackWrapper("lab2");
					for (int i = 0; i < 8; i++)
						if (feedback.charAt(i) == '1')
							lab2.setInitActioncmd("btn" + (i + 1), "on");
						else
							lab2.setInitActioncmd("btn" + (i + 1), "off");
					p3.removeAll();
					p3.add(lab2, BorderLayout.CENTER);
				} else {
					p3.removeAll();
					p3.add(new JLabel("Wait for some time"),
							BorderLayout.CENTER);
				}
				p3.revalidate();
				// SwingUtilities.updateComponentTreeUI(p3);
			} else if (l.getName().equals("lab3")) {
				if (status && l.getName().equals("lab3")) {
					feedback = Network.ServerCCCPS.getFeedbackWrapper("lab3");
					for (int i = 0; i < 8; i++)
						if (feedback.charAt(i) == '1')
							lab3.setInitActioncmd("btn" + (i + 1), "on");
						else
							lab3.setInitActioncmd("btn" + (i + 1), "off");
					p4.removeAll();
					p4.add(lab3, BorderLayout.CENTER);
				} else {
					p4.removeAll();
					p4.add(new JLabel("Wait for some time"),
							BorderLayout.CENTER);
				}
				p4.revalidate();
				// SwingUtilities.updateComponentTreeUI(p4);
			} else if (l.getName().equals("lab4")) {
				if (status && l.getName().equals("lab4")) {
					feedback = Network.ServerCCCPS.getFeedbackWrapper("lab4");
					for (int i = 0; i < 8; i++)
						if (feedback.charAt(i) == '1')
							lab4.setInitActioncmd("btn" + (i + 1), "on");
						else
							lab4.setInitActioncmd("btn" + (i + 1), "off");
					p5.removeAll();
					p5.add(lab4, BorderLayout.CENTER);
				} else {
					p5.removeAll();
					p5.add(new JLabel("Wait for some time"),
							BorderLayout.CENTER);
				}
				p5.revalidate();
				// SwingUtilities.updateComponentTreeUI(p5);
			} else if (l.getName().equals("lab5")) {
				if (status && l.getName().equals("lab5")) {
					feedback = Network.ServerCCCPS.getFeedbackWrapper("lab5");
					for (int i = 0; i < 8; i++)
						if (feedback.charAt(i) == '1')
							lab5.setInitActioncmd("btn" + (i + 1), "on");
						else
							lab5.setInitActioncmd("btn" + (i + 1), "off");
					p6.removeAll();
					p6.add(lab5, BorderLayout.CENTER);
				} else {
					p6.removeAll();
					p6.add(new JLabel("Wait for some time"),
							BorderLayout.CENTER);
				}
				p6.revalidate();
				// SwingUtilities.updateComponentTreeUI(p6);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Exit")) {
			frmCccpsControlpanel.dispose();
		} else if (actcmd.equals("StdAttView")) {
			StdAttendanceView sav = new StdAttendanceView();
			sav.setVisible(true);
		} else if (actcmd.equals("ClassVise")) {
			Dbcon db = new Dbcon();
			db.open();
			ResultSet rs = db
					.selquery("SELECT DISTINCT SEM FROM SEM_BATCH ORDER BY SEM");
			Object[] sem = new Object[8];
			int i = 0;
			try {
				while (rs.next()) {
					sem[i] = rs.getObject(1);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
			String insem = JOptionPane.showInputDialog(frmCccpsControlpanel,
					"Semester", "Report ClassVise",
					JOptionPane.INFORMATION_MESSAGE, null, sem, sem[0])
					.toString();
			if (insem != null) {
				Report r = new Report(null, insem, actcmd);
				r.pdfgenerator();
			}
		} else if (actcmd.equals("StdAttRpt")) {
			String enno = JOptionPane.showInputDialog(frmCccpsControlpanel,
					"Enrollment No:", "Report fro individual Student",
					JOptionPane.INFORMATION_MESSAGE);
			if (enno != null) {
				Report r = new Report(null, enno, actcmd);
				r.pdfgenerator();
			}
		} else if (actcmd.equals("lslist")) {
			ReportGen rg = new ReportGen();
			rg.setVisible(true);
		} else if (actcmd.equals("Lab status")) {
			LabStatusView lsv = new LabStatusView();
			lsv.setVisible(true);
		}
	}
}