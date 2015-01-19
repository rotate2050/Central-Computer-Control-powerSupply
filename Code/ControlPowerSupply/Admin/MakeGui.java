import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MakeGui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected static JPanel frmAdminPanel;
	private JMenuItem mntmLogin;
	protected static byte login = 0;

	public MakeGui() {
		setTitle("Admin Panel");
		setSize(new Dimension(300, 300));
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(this);
		mnFile.add(mntmLogin);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.setActionCommand("chngpwd");
		mntmChangePassword.addActionListener(this);
		mnEdit.add(mntmChangePassword);

		JMenuItem mntmChangeLabpriority = new JMenuItem("Change Lab Priority");
		mntmChangeLabpriority.setActionCommand("chnglabpri");
		mntmChangeLabpriority.addActionListener(this);
		mnEdit.add(mntmChangeLabpriority);

		JMenu menu = new JMenu("Insert");
		menuBar.add(menu);

		JMenuItem mntmTimeTable = new JMenuItem("TimeTable");
		mntmTimeTable.addActionListener(this);
		menu.add(mntmTimeTable);

		JMenuItem mntmSlots = new JMenuItem("Slots");
		mntmSlots.addActionListener(this);
		menu.add(mntmSlots);

		JMenuItem mntmSubject = new JMenuItem("Subject");
		mntmSubject.addActionListener(this);
		menu.add(mntmSubject);

		JMenuItem mntmBatch = new JMenuItem("Batch");
		mntmBatch.addActionListener(this);
		menu.add(mntmBatch);

		JSeparator separator1 = new JSeparator();
		menu.add(separator1);

		JMenuItem mntmAttendance = new JMenuItem("Attendance");
		mntmAttendance.addActionListener(this);
		menu.add(mntmAttendance);

		JSeparator separator = new JSeparator();
		menu.add(separator);

		JMenuItem mntmClient = new JMenuItem("Client");
		mntmClient.addActionListener(this);
		menu.add(mntmClient);

		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);

		JMenuItem mntmIndividualStudent = new JMenuItem("Individual Student");
		mntmIndividualStudent.setActionCommand("IndiStd");
		mntmIndividualStudent.addActionListener(this);
		mnStudent.add(mntmIndividualStudent);

		JMenuItem mntmUpdateMultipleStudents = new JMenuItem(
				"Update Multiple Students");
		mntmUpdateMultipleStudents.setActionCommand("UpdtMltStd");
		mntmUpdateMultipleStudents.addActionListener(this);
		mnStudent.add(mntmUpdateMultipleStudents);

		JMenuItem mntmInsertMultipleStudents = new JMenuItem(
				"Insert Multiple Students");
		mntmInsertMultipleStudents.setActionCommand("InstMltStd");
		mntmInsertMultipleStudents.addActionListener(this);
		mnStudent.add(mntmInsertMultipleStudents);

		frmAdminPanel = new JPanel();
		frmAdminPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(frmAdminPanel);
	}

	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Exit")) {
			login = 0;
			this.dispose();
		} else if (actcmd.equals("Login")) {
			frmAdminPanel.removeAll();
			frmAdminPanel.revalidate();
			doLogin();
		} else if (actcmd.equals("Logout")) {
			frmAdminPanel.removeAll();
			frmAdminPanel.revalidate();
			login = 0;
			mntmLogin.setText("Login");
			mntmLogin.setActionCommand("Login");
			setTitle("Admin Panel");
		} else if (actcmd.equals("chngpwd")) {
			frmAdminPanel.removeAll();
			ChangePassword cp = new ChangePassword();
			frmAdminPanel.add(cp, BorderLayout.CENTER);
			frmAdminPanel.revalidate();
		} else if (actcmd.equals("chnglabpri") && login == 1) {
			frmAdminPanel.removeAll();
			ChangeLabPriority clp = new ChangeLabPriority();
			frmAdminPanel.add(clp, BorderLayout.CENTER);
			frmAdminPanel.revalidate();
		} else if (actcmd.equals("IndiStd") && login == 1) {
			StudentInddividualInsert stdindi = new StudentInddividualInsert();
			stdindi.setVisible(true);
		} else if (actcmd.equals("UpdtMltStd")) {
			StudentMultiUpdate stdmlt = new StudentMultiUpdate();
			frmAdminPanel.add(stdmlt);
			frmAdminPanel.revalidate();
		} else if (actcmd.equals("InstMltStd") && login == 1) {
			JFileChooser jfc = new JFileChooser();
			Dbcon db = new Dbcon();
			db.open();
			int i = jfc.showOpenDialog(frmAdminPanel);
			if (i == JFileChooser.APPROVE_OPTION) {
				File fl = jfc.getSelectedFile();
				try {
					Scanner sc = new Scanner(fl);
					i = 0;
					while (sc.hasNextLine()) {
						String str[] = sc.nextLine().split(",");
						i += db.dmlstmt("INSERT INTO STUDENT_DETAIL VALUES("
								+ Long.parseLong(str[0]) + ","
								+ Long.parseLong(str[1]) + ",'" + str[2] + "')");
					}
					JOptionPane.showMessageDialog(jfc, i
							+ " rows are inserted successfully.",
							"Insertion Complete",
							JOptionPane.INFORMATION_MESSAGE);
					db.close();
				} catch (Exception e) {
					e.printStackTrace();
					new HandleException(e.getMessage());
				}
			}
		} else if (actcmd.equals("TimeTable") && login == 1) {
			TimeTableInsert tti = new TimeTableInsert();
			tti.setVisible(true);
		} else if (actcmd.equals("Subject") && login == 1) {
			SubjectInsert si = new SubjectInsert();
			si.setVisible(true);
		} else if (actcmd.equals("Batch") && login == 1) {
			BatchInsert bi = new BatchInsert();
			bi.setVisible(true);
		} else if (actcmd.equals("Slots") && login == 1) {
			SlotInsert slt = new SlotInsert();
			slt.setVisible(true);
		} else if (actcmd.equals("Attendance") && login == 1) {
			ManualAttendance ma = new ManualAttendance();
			ma.setVisible(true);
		} else if (actcmd.equals("Client") && login == 1) {
			ClientInsert ci = new ClientInsert();
			ci.setVisible(true);
		}
	}

	public void doLogin() {
		JPanel p = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		gb.columnWidths = new int[] { 0, 0, 0 };
		gb.rowHeights = new int[] { 0, 0, 0 };
		gb.columnWeights = new double[] { 0.0, 0.1 };
		gb.rowWeights = new double[] { 0.0, 0.0 };
		p.setLayout(gb);

		JLabel lblUserName = new JLabel("User Name:");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(lblUserName, gbc);

		JLabel lblPassword = new JLabel("Password:");
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(lblPassword, gbc);

		final JTextField txtFldUserName = new JTextField(10);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(txtFldUserName, gbc);

		final JPasswordField pwdFld = new JPasswordField(20);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(pwdFld, gbc);

		JButton btnOk = new JButton("Login");
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 2;

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					File fl = new File("D:\\prjdev\\MyJava\\Settings.conf");
					Scanner sc = new Scanner(fl);
					String line = sc.nextLine();
					SecureIt sit = new SecureIt("ABCDEFGH");
					String[] s = sit.decrypt(line).split("-");
					if (s[0].equals(txtFldUserName.getText())
							&& s[1].equals(new String(pwdFld.getPassword())))
						login = 1;
				} catch (Exception e) {
					e.printStackTrace();
					new HandleException(e.getMessage());
				}
				if (login == 1) {
					mntmLogin.setText("Logout");
					mntmLogin.setActionCommand("Logout");
					setTitle("Admin Panel : " + txtFldUserName.getText()
							+ " : logged in");
					frmAdminPanel.removeAll();
				}
				SwingUtilities.updateComponentTreeUI(frmAdminPanel);
			}
		});
		p.add(btnOk, gbc);

		JButton btnCancel = new JButton("Cancel");
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 2;

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frmAdminPanel.removeAll();
				SwingUtilities.updateComponentTreeUI(frmAdminPanel);
			}
		});
		p.add(btnCancel, gbc);

		frmAdminPanel.add(p, BorderLayout.CENTER);
		frmAdminPanel.revalidate();
	}
}