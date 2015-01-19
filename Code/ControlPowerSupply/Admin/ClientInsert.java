
import java.awt.Dimension;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

class ClientInsert extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtFldLab, txtFldClientIp, txtFldPriority;
	private JButton btnUpdate, btnCancel;
	private JLabel lblLab, lblClientIp, lblPriority;
	private Dbcon db;
	private ResultSet rs;

	public ClientInsert() {
		setSize(new Dimension(200, 180));
		setTitle("Insert Client");
		setLocation(100, 100);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblLab = new JLabel("Lab:");
		lblLab.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLab = new GridBagConstraints();
		gbc_lblLab.ipady = 1;
		gbc_lblLab.ipadx = 1;
		gbc_lblLab.insets = new Insets(5, 5, 5, 5);
		gbc_lblLab.anchor = GridBagConstraints.WEST;
		gbc_lblLab.gridx = 0;
		gbc_lblLab.gridy = 0;
		getContentPane().add(lblLab, gbc_lblLab);

		txtFldLab = new JTextField();
		GridBagConstraints gbc_txtFldLab = new GridBagConstraints();
		gbc_txtFldLab.ipady = 1;
		gbc_txtFldLab.ipadx = 1;
		gbc_txtFldLab.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldLab.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldLab.gridx = 1;
		gbc_txtFldLab.gridy = 0;
		getContentPane().add(txtFldLab, gbc_txtFldLab);
		txtFldLab.setColumns(10);

		lblClientIp = new JLabel("Client IP:");
		lblClientIp.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblClientIp = new GridBagConstraints();
		gbc_lblClientIp.ipady = 1;
		gbc_lblClientIp.ipadx = 1;
		gbc_lblClientIp.anchor = GridBagConstraints.WEST;
		gbc_lblClientIp.insets = new Insets(5, 5, 5, 5);
		gbc_lblClientIp.gridx = 0;
		gbc_lblClientIp.gridy = 1;
		getContentPane().add(lblClientIp, gbc_lblClientIp);

		txtFldClientIp = new JTextField();
		GridBagConstraints gbc_txtFldClientIp = new GridBagConstraints();
		gbc_txtFldClientIp.ipady = 1;
		gbc_txtFldClientIp.ipadx = 1;
		gbc_txtFldClientIp.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldClientIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldClientIp.gridx = 1;
		gbc_txtFldClientIp.gridy = 1;
		getContentPane().add(txtFldClientIp, gbc_txtFldClientIp);
		txtFldClientIp.setColumns(10);

		lblPriority = new JLabel("Priority:");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.ipady = 1;
		gbc_lblPriority.ipadx = 1;
		gbc_lblPriority.anchor = GridBagConstraints.WEST;
		gbc_lblPriority.insets = new Insets(5, 5, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 2;
		getContentPane().add(lblPriority, gbc_lblPriority);

		txtFldPriority = new JTextField();
		GridBagConstraints gbc_txtFldPriority = new GridBagConstraints();
		gbc_txtFldPriority.ipady = 1;
		gbc_txtFldPriority.ipadx = 1;
		gbc_txtFldPriority.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldPriority.gridx = 1;
		gbc_txtFldPriority.gridy = 2;
		getContentPane().add(txtFldPriority, gbc_txtFldPriority);
		txtFldPriority.setColumns(10);

		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.ipadx = 1;
		gbc_btnUpdate.ipady = 1;
		gbc_btnUpdate.insets = new Insets(5, 5, 5, 5);
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 3;
		btnUpdate.addActionListener(this);
		getContentPane().add(btnUpdate, gbc_btnUpdate);

		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.ipady = 1;
		gbc_btnCancel.ipadx = 1;
		gbc_btnCancel.insets = new Insets(10, 5, 10, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 3;
		btnCancel.addActionListener(this);
		getContentPane().add(btnCancel, gbc_btnCancel);

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();
	}

	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Update")) {
			String ip = txtFldClientIp.getText();
			String lab = txtFldLab.getText();
			int p = Integer.parseInt(txtFldPriority.getText());
			rs = db.selquery("SELECT ip, lab_nm, priority FROM client WHERE ip='"
					+ ip + "'");
			try {
				rs.last();
				if (rs.getRow() == 1) {
					if (db.dmlstmt("UPDATE client SET lab_nm='" + lab
							+ "', priority=" + p + "WHERE ip='" + ip + "'") == 1)
						JOptionPane.showMessageDialog(rootPane,
								"Client is updated successfully.",
								"Updation Successful",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(rootPane,
								"Error!! please check your data.",
								"Updation Unsuccessful",
								JOptionPane.ERROR_MESSAGE);
				} else {
					if (db.dmlstmt("INSERT INTO client(ip, lab_nm, priority) VALUES('"
							+ ip + "','" + lab + "'," + p + ")") == 1)
						JOptionPane.showMessageDialog(rootPane,
								"New client is iserted successfully.",
								"Insertion Successful",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(rootPane,
								"Error!! please check your data.",
								"Insertion Unsuccessful",
								JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				new HandleException(e.getMessage());
			}
		} else if (actcmd.equals("Cancel")) {
			db.close();
			this.dispose();
		}
	}
}
