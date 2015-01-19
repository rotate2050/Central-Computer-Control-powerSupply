import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;

class BatchInsert extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtFldBatch;
	private JButton btnInsert, btnCancel;
	private JLabel lblSemester, lblBatch;
	private Dbcon db;
	private JComboBox cbSem;
	private ResultSet rs;

	public BatchInsert() {
		setSize(new Dimension(200, 150));
		setTitle("Insert Batch");
		setLocation(100, 100);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblSemester = new JLabel("Semester:");
		GridBagConstraints gbc_lblSemester = new GridBagConstraints();
		gbc_lblSemester.ipady = 1;
		gbc_lblSemester.ipadx = 1;
		gbc_lblSemester.insets = new Insets(5, 5, 5, 5);
		gbc_lblSemester.anchor = GridBagConstraints.WEST;
		gbc_lblSemester.gridx = 0;
		gbc_lblSemester.gridy = 0;
		getContentPane().add(lblSemester, gbc_lblSemester);

		cbSem = new JComboBox();
		try {
			db = new Dbcon();
			// db.open("mitesh", "m1i0t2e9s3h8");
			db.open();
			rs = db.selquery("SELECT DISTINCT sem FROM sem_batch ORDER BY sem");
			while (rs.next())
				cbSem.addItem(rs.getInt(1));
			rs.close();
		} catch (Exception e) {
			new HandleException(e.getMessage());
		}

		cbSem.setEditable(true);
		GridBagConstraints gbc_cbSem = new GridBagConstraints();
		gbc_cbSem.ipady = 1;
		gbc_cbSem.ipadx = 1;
		gbc_cbSem.insets = new Insets(5, 5, 5, 5);
		gbc_cbSem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSem.gridx = 1;
		gbc_cbSem.gridy = 0;
		getContentPane().add(cbSem, gbc_cbSem);

		lblBatch = new JLabel("Batch:");
		GridBagConstraints gbc_lblBatch = new GridBagConstraints();
		gbc_lblBatch.ipady = 1;
		gbc_lblBatch.ipadx = 1;
		gbc_lblBatch.anchor = GridBagConstraints.WEST;
		gbc_lblBatch.insets = new Insets(5, 5, 5, 5);
		gbc_lblBatch.gridx = 0;
		gbc_lblBatch.gridy = 1;
		getContentPane().add(lblBatch, gbc_lblBatch);

		txtFldBatch = new JTextField();
		GridBagConstraints gbc_txtFldBatch = new GridBagConstraints();
		gbc_txtFldBatch.ipady = 1;
		gbc_txtFldBatch.ipadx = 1;
		gbc_txtFldBatch.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldBatch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldBatch.gridx = 1;
		gbc_txtFldBatch.gridy = 1;
		getContentPane().add(txtFldBatch, gbc_txtFldBatch);
		txtFldBatch.setColumns(5);

		btnInsert = new JButton("Insert");
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.ipady = 1;
		gbc_btnInsert.ipadx = 1;
		gbc_btnInsert.insets = new Insets(10, 5, 5, 10);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 2;
		getContentPane().add(btnInsert, gbc_btnInsert);
		btnInsert.addActionListener(this);

		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.ipady = 1;
		gbc_btnCancel.ipadx = 1;
		gbc_btnCancel.insets = new Insets(10, 5, 5, 10);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 2;
		getContentPane().add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Insert")) {
			int sem = Integer.parseInt(cbSem.getSelectedItem().toString());
			String batch = txtFldBatch.getText().toUpperCase();
			int i = db
					.dmlstmt("INSERT INTO sem_batch (batch_id, sem, batch) VALUES("
							+ "'"
							+ sem
							+ batch
							+ "',"
							+ sem
							+ ",'"
							+ batch
							+ "')");
			if (i == 1) {
				JOptionPane.showMessageDialog(rootPane,
						"Insertion is done Successfully", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(rootPane,
						"Error!!! please check your data", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (actcmd.equals("Cancel")) {
			db.close();
			this.dispose();
		}
	}
}