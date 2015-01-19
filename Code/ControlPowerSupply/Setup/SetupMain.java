import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JButton;

public class SetupMain extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtFld, txtFld1, txtFld2, txtFld3;
	private JPasswordField pwdFld, pwdFld1;
	private JButton btnBack, btnNext, btnCancel;
	private Container c = getContentPane();
	private JPanel p;
	private File fl;
	private Writer wr;

	public static void main(String[] args) {
		try {
			SetupMain dialog = new SetupMain();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SetupMain() throws IOException {
		setSize(new Dimension(450, 400));
		setResizable(false);
		setLocation(120, 120);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JPanel panel = new JPanel();
		c.add(panel, BorderLayout.SOUTH);

		btnBack = new JButton("Back");
		btnBack.setVisible(false);
		btnBack.addActionListener(this);
		panel.add(btnBack);

		btnNext = new JButton("Next");
		btnNext.addActionListener(this);
		panel.add(btnNext);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		panel.add(btnCancel);

		p = new JPanel();
		c.add(p, BorderLayout.CENTER);

		Step1();
		fl = new File("D:/prjdev/MyJava/Settings.conf");
		wr = new FileWriter(fl);
	}

	public void Step1() {
		p.removeAll();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };
		p.setLayout(gridBagLayout);

		JLabel lblPleaseInsertThe = new JLabel(
				"Please Insert the following details:");
		GridBagConstraints gbc_lblPleaseInsertThe = new GridBagConstraints();
		gbc_lblPleaseInsertThe.ipady = 1;
		gbc_lblPleaseInsertThe.ipadx = 1;
		gbc_lblPleaseInsertThe.gridwidth = 4;
		gbc_lblPleaseInsertThe.insets = new Insets(5, 5, 5, 5);
		gbc_lblPleaseInsertThe.gridx = 0;
		gbc_lblPleaseInsertThe.gridy = 0;
		p.add(lblPleaseInsertThe, gbc_lblPleaseInsertThe);

		JLabel lblUserName = new JLabel("User Name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.ipady = 1;
		gbc_lblUserName.ipadx = 1;
		gbc_lblUserName.anchor = GridBagConstraints.WEST;
		gbc_lblUserName.insets = new Insets(5, 5, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 2;
		p.add(lblUserName, gbc_lblUserName);

		txtFld = new JTextField();
		GridBagConstraints gbc_txtFld = new GridBagConstraints();
		gbc_txtFld.ipady = 1;
		gbc_txtFld.ipadx = 1;
		gbc_txtFld.gridwidth = 2;
		gbc_txtFld.insets = new Insets(5, 5, 5, 5);
		gbc_txtFld.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld.gridx = 1;
		gbc_txtFld.gridy = 2;
		p.add(txtFld, gbc_txtFld);
		txtFld.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.ipady = 1;
		gbc_lblPassword.ipadx = 1;
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		p.add(lblPassword, gbc_lblPassword);

		pwdFld = new JPasswordField();
		GridBagConstraints gbc_pwdFld = new GridBagConstraints();
		gbc_pwdFld.ipady = 1;
		gbc_pwdFld.ipadx = 1;
		gbc_pwdFld.gridwidth = 2;
		gbc_pwdFld.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFld.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFld.gridx = 1;
		gbc_pwdFld.gridy = 3;
		p.add(pwdFld, gbc_pwdFld);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.ipady = 1;
		gbc_lblConfirmPassword.ipadx = 1;
		gbc_lblConfirmPassword.anchor = GridBagConstraints.WEST;
		gbc_lblConfirmPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblConfirmPassword.gridx = 0;
		gbc_lblConfirmPassword.gridy = 4;
		p.add(lblConfirmPassword, gbc_lblConfirmPassword);

		pwdFld1 = new JPasswordField();
		GridBagConstraints gbc_pwdFld1 = new GridBagConstraints();
		gbc_pwdFld1.ipady = 1;
		gbc_pwdFld1.ipadx = 1;
		gbc_pwdFld1.gridwidth = 2;
		gbc_pwdFld1.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFld1.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFld1.gridx = 1;
		gbc_pwdFld1.gridy = 4;
		p.add(pwdFld1, gbc_pwdFld1);

		JLabel lblSecurityQuestionWhat = new JLabel(
				"Security Question: What is your first mobile number?");
		GridBagConstraints gbc_lblSecurityQuestionWhat = new GridBagConstraints();
		gbc_lblSecurityQuestionWhat.ipady = 1;
		gbc_lblSecurityQuestionWhat.ipadx = 1;
		gbc_lblSecurityQuestionWhat.gridwidth = 2;
		gbc_lblSecurityQuestionWhat.insets = new Insets(5, 5, 5, 5);
		gbc_lblSecurityQuestionWhat.gridx = 0;
		gbc_lblSecurityQuestionWhat.gridy = 6;
		p.add(lblSecurityQuestionWhat, gbc_lblSecurityQuestionWhat);

		txtFld1 = new JTextField();
		GridBagConstraints gbc_txtFld1 = new GridBagConstraints();
		gbc_txtFld1.ipady = 1;
		gbc_txtFld1.ipadx = 1;
		gbc_txtFld1.insets = new Insets(5, 5, 5, 5);
		gbc_txtFld1.gridwidth = 2;
		gbc_txtFld1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld1.gridx = 2;
		gbc_txtFld1.gridy = 6;
		p.add(txtFld1, gbc_txtFld1);
		txtFld1.setColumns(10);

		p.revalidate();
	}

	public void Step2() {
		p.removeAll();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0 };
		p.setLayout(gridBagLayout);

		JLabel lblPleaseInsertFollowing = new JLabel(
				"Please Insert Following Details:");
		GridBagConstraints gbc_lblPleaseInsertFollowing = new GridBagConstraints();
		gbc_lblPleaseInsertFollowing.ipady = 1;
		gbc_lblPleaseInsertFollowing.ipadx = 1;
		gbc_lblPleaseInsertFollowing.gridwidth = 3;
		gbc_lblPleaseInsertFollowing.anchor = GridBagConstraints.WEST;
		gbc_lblPleaseInsertFollowing.insets = new Insets(5, 5, 5, 5);
		gbc_lblPleaseInsertFollowing.gridx = 0;
		gbc_lblPleaseInsertFollowing.gridy = 0;
		p.add(lblPleaseInsertFollowing, gbc_lblPleaseInsertFollowing);

		JLabel lblDatabaseHost = new JLabel("DataBase Host:");
		GridBagConstraints gbc_lblDatabaseHost = new GridBagConstraints();
		gbc_lblDatabaseHost.ipady = 1;
		gbc_lblDatabaseHost.ipadx = 1;
		gbc_lblDatabaseHost.anchor = GridBagConstraints.WEST;
		gbc_lblDatabaseHost.insets = new Insets(5, 5, 5, 5);
		gbc_lblDatabaseHost.gridx = 0;
		gbc_lblDatabaseHost.gridy = 2;
		p.add(lblDatabaseHost, gbc_lblDatabaseHost);

		txtFld = new JTextField();
		GridBagConstraints gbc_txtFld = new GridBagConstraints();
		gbc_txtFld.gridwidth = 2;
		gbc_txtFld.ipadx = 1;
		gbc_txtFld.ipady = 1;
		gbc_txtFld.insets = new Insets(5, 5, 5, 5);
		gbc_txtFld.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld.gridx = 1;
		gbc_txtFld.gridy = 2;
		p.add(txtFld, gbc_txtFld);
		txtFld.setColumns(10);

		JLabel lblDatabasePort = new JLabel("DataBase Port:");
		GridBagConstraints gbc_lblDatabasePort = new GridBagConstraints();
		gbc_lblDatabasePort.ipady = 1;
		gbc_lblDatabasePort.ipadx = 1;
		gbc_lblDatabasePort.anchor = GridBagConstraints.WEST;
		gbc_lblDatabasePort.insets = new Insets(5, 5, 5, 5);
		gbc_lblDatabasePort.gridx = 0;
		gbc_lblDatabasePort.gridy = 3;
		p.add(lblDatabasePort, gbc_lblDatabasePort);

		txtFld1 = new JTextField();
		GridBagConstraints gbc_txtFld1 = new GridBagConstraints();
		gbc_txtFld1.ipady = 1;
		gbc_txtFld1.ipadx = 1;
		gbc_txtFld1.gridwidth = 2;
		gbc_txtFld1.insets = new Insets(5, 5, 5, 5);
		gbc_txtFld1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld1.gridx = 1;
		gbc_txtFld1.gridy = 3;
		p.add(txtFld1, gbc_txtFld1);
		txtFld1.setColumns(10);

		JLabel lblDatabaseSid = new JLabel("DataBase SID:");
		GridBagConstraints gbc_lblDatabaseSid = new GridBagConstraints();
		gbc_lblDatabaseSid.ipady = 1;
		gbc_lblDatabaseSid.ipadx = 1;
		gbc_lblDatabaseSid.anchor = GridBagConstraints.WEST;
		gbc_lblDatabaseSid.insets = new Insets(5, 5, 5, 5);
		gbc_lblDatabaseSid.gridx = 0;
		gbc_lblDatabaseSid.gridy = 4;
		p.add(lblDatabaseSid, gbc_lblDatabaseSid);

		txtFld2 = new JTextField();
		GridBagConstraints gbc_txtFld2 = new GridBagConstraints();
		gbc_txtFld2.ipady = 1;
		gbc_txtFld2.ipadx = 1;
		gbc_txtFld2.gridwidth = 2;
		gbc_txtFld2.insets = new Insets(5, 5, 5, 5);
		gbc_txtFld2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld2.gridx = 1;
		gbc_txtFld2.gridy = 4;
		p.add(txtFld2, gbc_txtFld2);
		txtFld2.setColumns(10);

		JLabel lblDataBaseUserName = new JLabel("DataBase User Name:");
		GridBagConstraints gbc_lblDataBaseUserName = new GridBagConstraints();
		gbc_lblDataBaseUserName.ipady = 1;
		gbc_lblDataBaseUserName.ipadx = 1;
		gbc_lblDataBaseUserName.anchor = GridBagConstraints.WEST;
		gbc_lblDataBaseUserName.insets = new Insets(5, 5, 5, 5);
		gbc_lblDataBaseUserName.gridx = 0;
		gbc_lblDataBaseUserName.gridy = 5;
		p.add(lblDataBaseUserName, gbc_lblDataBaseUserName);

		txtFld3 = new JTextField();
		GridBagConstraints gbc_txtFld3 = new GridBagConstraints();
		gbc_txtFld3.ipady = 1;
		gbc_txtFld3.ipadx = 1;
		gbc_txtFld3.gridwidth = 2;
		gbc_txtFld3.insets = new Insets(5, 5, 5, 5);
		gbc_txtFld3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld3.gridx = 1;
		gbc_txtFld3.gridy = 5;
		p.add(txtFld3, gbc_txtFld3);
		txtFld3.setColumns(10);

		JLabel lblDatabasePassword = new JLabel("DataBase Password:");
		GridBagConstraints gbc_lblDatabasePassword = new GridBagConstraints();
		gbc_lblDatabasePassword.ipady = 1;
		gbc_lblDatabasePassword.ipadx = 1;
		gbc_lblDatabasePassword.anchor = GridBagConstraints.WEST;
		gbc_lblDatabasePassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblDatabasePassword.gridx = 0;
		gbc_lblDatabasePassword.gridy = 6;
		p.add(lblDatabasePassword, gbc_lblDatabasePassword);

		pwdFld = new JPasswordField();
		GridBagConstraints gbc_pwdFld = new GridBagConstraints();
		gbc_pwdFld.ipady = 1;
		gbc_pwdFld.ipadx = 1;
		gbc_pwdFld.gridwidth = 2;
		gbc_pwdFld.gridx = 1;
		gbc_pwdFld.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFld.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFld.gridy = 6;
		p.add(pwdFld, gbc_pwdFld);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.ipady = 1;
		gbc_lblConfirmPassword.ipadx = 1;
		gbc_lblConfirmPassword.anchor = GridBagConstraints.WEST;
		gbc_lblConfirmPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblConfirmPassword.gridx = 0;
		gbc_lblConfirmPassword.gridy = 7;
		p.add(lblConfirmPassword, gbc_lblConfirmPassword);

		pwdFld1 = new JPasswordField();
		GridBagConstraints gbc_pwdFld1 = new GridBagConstraints();
		gbc_pwdFld1.ipady = 1;
		gbc_pwdFld1.ipadx = 1;
		gbc_pwdFld1.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFld1.gridwidth = 2;
		gbc_pwdFld1.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFld1.gridx = 1;
		gbc_pwdFld1.gridy = 7;
		p.add(pwdFld1, gbc_pwdFld1);
		p.revalidate();
	}

	public void Write(int i) {
		try {
			SecureIt sit = new SecureIt("ABCDEFGH");
			if (i == 0) {
				String plaintext = txtFld.getText() + "-" + new String(pwdFld.getPassword()) + "-" + txtFld1.getText();
				wr.write(sit.encrypt(plaintext)+"\n");
				System.out.println(plaintext+"\t"+sit.encrypt(plaintext));
			} else if (i == 1) {
				String plaintext = txtFld.getText()+":"+txtFld1.getText()+":"+txtFld2.getText()+":"+txtFld3.getText()+":"+new String(pwdFld.getPassword());
				wr.append(sit.encrypt(plaintext));
				System.out.println(sit.encrypt(plaintext));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage(),
					"Exception Fired", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Cancel")) {
			int i = JOptionPane.showConfirmDialog(rootPane,
					"Do you really want to exit from  the setup?",
					"Setup Cancel", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION)
				this.dispose();
		} else if (actcmd.equals("Next")) {
			String p1 = new String(pwdFld.getPassword());
			String p2 = new String(pwdFld1.getPassword());
			if (p1.equals(p2) && p1 != null && txtFld.getText() != null
					&& txtFld1.getText() != null) {
				Write(0);
				Step2();
				btnNext.setText("Finish");
				btnNext.setActionCommand("Finish");
				btnBack.setVisible(true);
			}
		} else if (actcmd.equals("Back")) {
			Step1();
			btnBack.setVisible(false);
			btnNext.setText("Next");
			btnNext.setActionCommand("Next");
		} else if (actcmd.equals("Finish")) {
			String p1 = new String(pwdFld.getPassword());
			String p2 = new String(pwdFld1.getPassword());
			if (p1.equals(p2) && p1 != null && txtFld.getText() != null
					&& txtFld1.getText() != null && txtFld2.getText() != null
					&& txtFld3.getText() != null) {
				Write(1);
				try {
					wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(c, "Setup is done successfully.", "Successful", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		}
		rootPane.updateUI();
	}
}