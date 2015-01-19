
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class SubjectInsert extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtFldSubId, txtFldSubNm;
	private JButton btnUpdt;
	private JLabel lblSubId, lblSubNm, lblSem;
	private JComboBox cbSem;
	private JButton btnCancel;
	private ResultSet rs;
	private Dbcon db;

	public SubjectInsert() {
		setSize(new Dimension(320, 200));
		setLocation(100, 100);
		setTitle("Insert Subject");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.1 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblSubId = new JLabel("Subject Id:");
		GridBagConstraints gbc_lblSubId = new GridBagConstraints();
		gbc_lblSubId.ipady = 1;
		gbc_lblSubId.ipadx = 1;
		gbc_lblSubId.insets = new Insets(5, 5, 5, 5);
		gbc_lblSubId.anchor = GridBagConstraints.WEST;
		gbc_lblSubId.gridx = 0;
		gbc_lblSubId.gridy = 0;
		getContentPane().add(lblSubId, gbc_lblSubId);

		txtFldSubId = new JTextField();
		GridBagConstraints gbc_txtFldSubId = new GridBagConstraints();
		gbc_txtFldSubId.gridwidth = 4;
		gbc_txtFldSubId.ipady = 1;
		gbc_txtFldSubId.ipadx = 1;
		gbc_txtFldSubId.anchor = GridBagConstraints.WEST;
		gbc_txtFldSubId.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldSubId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldSubId.gridx = 1;
		gbc_txtFldSubId.gridy = 0;
		getContentPane().add(txtFldSubId, gbc_txtFldSubId);
		txtFldSubId.setColumns(10);

		lblSubNm = new JLabel("Subject Name:");
		GridBagConstraints gbc_lblSubNm = new GridBagConstraints();
		gbc_lblSubNm.ipady = 1;
		gbc_lblSubNm.ipadx = 1;
		gbc_lblSubNm.anchor = GridBagConstraints.WEST;
		gbc_lblSubNm.insets = new Insets(5, 5, 5, 5);
		gbc_lblSubNm.gridx = 0;
		gbc_lblSubNm.gridy = 1;
		getContentPane().add(lblSubNm, gbc_lblSubNm);

		txtFldSubNm = new JTextField();
		GridBagConstraints gbc_txtFldSubNm = new GridBagConstraints();
		gbc_txtFldSubNm.gridwidth = 4;
		gbc_txtFldSubNm.ipady = 1;
		gbc_txtFldSubNm.ipadx = 1;
		gbc_txtFldSubNm.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldSubNm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldSubNm.gridx = 1;
		gbc_txtFldSubNm.gridy = 1;
		getContentPane().add(txtFldSubNm, gbc_txtFldSubNm);
		txtFldSubNm.setColumns(60);

		lblSem = new JLabel("Semester:");
		GridBagConstraints gbc_lblSem = new GridBagConstraints();
		gbc_lblSem.ipady = 1;
		gbc_lblSem.ipadx = 1;
		gbc_lblSem.anchor = GridBagConstraints.WEST;
		gbc_lblSem.insets = new Insets(5, 5, 5, 5);
		gbc_lblSem.gridx = 0;
		gbc_lblSem.gridy = 2;
		getContentPane().add(lblSem, gbc_lblSem);

		cbSem = new JComboBox();
		try {
			db = new Dbcon();
			// db.open("mitesh", "m1i0t2e9s3h8");
			db.open();
			rs = db.selquery("SELECT DISTINCT sem FROM sem_batch ORDER BY sem");
			while (rs.next()) {
				cbSem.addItem(rs.getInt(1));
			}
			rs.close();
		} catch (Exception e) {
			new HandleException(e.getMessage());
		}

		GridBagConstraints gbc_cbSem = new GridBagConstraints();
		gbc_cbSem.ipady = 1;
		gbc_cbSem.ipadx = 1;
		gbc_cbSem.insets = new Insets(5, 5, 5, 5);
		gbc_cbSem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSem.gridx = 1;
		gbc_cbSem.gridy = 2;
		getContentPane().add(cbSem, gbc_cbSem);

		btnUpdt = new JButton("Update");
		GridBagConstraints gbc_btnUpdt = new GridBagConstraints();
		gbc_btnUpdt.ipady = 1;
		gbc_btnUpdt.ipadx = 1;
		gbc_btnUpdt.gridwidth = 3;
		gbc_btnUpdt.insets = new Insets(15, 5, 5, 5);
		gbc_btnUpdt.gridx = 0;
		gbc_btnUpdt.gridy = 3;
		btnUpdt.addActionListener(this);
		getContentPane().add(btnUpdt, gbc_btnUpdt);

		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridwidth = 2;
		gbc_btnCancel.ipady = 1;
		gbc_btnCancel.ipadx = 1;
		gbc_btnCancel.insets = new Insets(15, 5, 5, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 3;
		getContentPane().add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Update")) {
			int subid = Integer.parseInt(txtFldSubId.getText());
			int sem = (Integer) cbSem.getSelectedItem();
			int i = db
					.dmlstmt("INSERT INTO sub_detail (sub_id, sub_nm, sem) VALUES ("
							+ subid
							+ ",'"
							+ txtFldSubNm.getText()
							+ "',"
							+ sem
							+ ")");
			if (i == 1) {
				JOptionPane.showConfirmDialog(this, "Subject is inserted.",
						"Message", JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(this,
						"Error!!! Subject id already exists.", "Error",
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		} else if (actcmd.equals("Cancel")) {
			db.close();
			this.dispose();
		}
	}
}
