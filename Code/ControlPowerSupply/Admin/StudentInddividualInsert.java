
import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

public class StudentInddividualInsert extends JDialog implements
		ActionListener, ItemListener {
	private static final long serialVersionUID = -2072067942179470115L;

	private static JPanel srch, body;
	private static JSplitPane sp;
	private JLabel lblEnrollmentNo, lblEnNo, lblBarcode, lblBatch;
	private JButton btnSrch, btnCancel, btnUpdate;
	private JTextField txtFldSrch, txtFldEnNo, txtFldBc;
	private JLabel lblSemester;
	private JComboBox cbSem, cbBatch;
	private Dbcon db;
	private ResultSet rs;
	private Vector<Integer> sem;
	private Vector<String> batch;

	public StudentInddividualInsert() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Insert Student");
		setLocation(100, 100);
		setSize(new Dimension(350, 320));
		getContentPane().setLayout(new BorderLayout(0, 0));

		srch = new JPanel();
		body = new JPanel();

		sem = new Vector<Integer>();
		batch = new Vector<String>();

		sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		GridBagLayout gbl_srch = new GridBagLayout();
		gbl_srch.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_srch.rowHeights = new int[] { 0, 0 };
		gbl_srch.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0 };
		gbl_srch.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		srch.setLayout(gbl_srch);

		lblEnrollmentNo = new JLabel("Enrollment No:");
		GridBagConstraints gbc_lblEnrollmentNo = new GridBagConstraints();
		gbc_lblEnrollmentNo.anchor = GridBagConstraints.EAST;
		gbc_lblEnrollmentNo.ipady = 1;
		gbc_lblEnrollmentNo.ipadx = 1;
		gbc_lblEnrollmentNo.insets = new Insets(5, 5, 5, 5);
		gbc_lblEnrollmentNo.gridx = 0;
		gbc_lblEnrollmentNo.gridy = 0;
		srch.add(lblEnrollmentNo, gbc_lblEnrollmentNo);

		txtFldSrch = new JTextField();
		GridBagConstraints gbc_txtFldSrch = new GridBagConstraints();
		gbc_txtFldSrch.ipady = 1;
		gbc_txtFldSrch.ipadx = 1;
		gbc_txtFldSrch.gridwidth = 2;
		gbc_txtFldSrch.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldSrch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldSrch.gridx = 1;
		gbc_txtFldSrch.gridy = 0;
		srch.add(txtFldSrch, gbc_txtFldSrch);
		txtFldSrch.setColumns(20);

		btnSrch = new JButton("Search");
		GridBagConstraints gbc_btnSrch = new GridBagConstraints();
		gbc_btnSrch.ipady = 1;
		gbc_btnSrch.ipadx = 1;
		gbc_btnSrch.insets = new Insets(5, 5, 5, 5);
		gbc_btnSrch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSrch.gridx = 3;
		gbc_btnSrch.gridy = 0;
		btnSrch.addActionListener(this);
		srch.add(btnSrch, gbc_btnSrch);

		sp.setTopComponent(srch);

		GridBagLayout gbl_body = new GridBagLayout();
		gbl_body.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_body.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_body.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.1 };
		gbl_body.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		body.setLayout(gbl_body);

		lblEnNo = new JLabel("Enrollment No:");
		GridBagConstraints gbc_lblEnNo = new GridBagConstraints();
		gbc_lblEnNo.ipady = 1;
		gbc_lblEnNo.ipadx = 1;
		gbc_lblEnNo.insets = new Insets(15, 5, 5, 5);
		gbc_lblEnNo.anchor = GridBagConstraints.EAST;
		gbc_lblEnNo.gridx = 0;
		gbc_lblEnNo.gridy = 0;
		body.add(lblEnNo, gbc_lblEnNo);

		txtFldEnNo = new JTextField();
		txtFldEnNo.setEnabled(false);
		GridBagConstraints gbc_txtFldEnNo = new GridBagConstraints();
		gbc_txtFldEnNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldEnNo.gridwidth = 3;
		gbc_txtFldEnNo.ipady = 1;
		gbc_txtFldEnNo.ipadx = 1;
		gbc_txtFldEnNo.insets = new Insets(15, 5, 5, 5);
		gbc_txtFldEnNo.anchor = GridBagConstraints.WEST;
		gbc_txtFldEnNo.gridx = 1;
		gbc_txtFldEnNo.gridy = 0;
		body.add(txtFldEnNo, gbc_txtFldEnNo);
		txtFldEnNo.setColumns(10);

		lblBarcode = new JLabel("Barcode:");
		GridBagConstraints gbc_lblBarcode = new GridBagConstraints();
		gbc_lblBarcode.ipady = 1;
		gbc_lblBarcode.ipadx = 1;
		gbc_lblBarcode.anchor = GridBagConstraints.WEST;
		gbc_lblBarcode.insets = new Insets(5, 5, 5, 5);
		gbc_lblBarcode.gridx = 0;
		gbc_lblBarcode.gridy = 1;
		body.add(lblBarcode, gbc_lblBarcode);

		txtFldBc = new JTextField();
		txtFldBc.setEnabled(false);
		GridBagConstraints gbc_txtFldBc = new GridBagConstraints();
		gbc_txtFldBc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldBc.gridwidth = 3;
		gbc_txtFldBc.ipadx = 1;
		gbc_txtFldBc.ipady = 1;
		gbc_txtFldBc.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldBc.anchor = GridBagConstraints.WEST;
		gbc_txtFldBc.gridx = 1;
		gbc_txtFldBc.gridy = 1;
		body.add(txtFldBc, gbc_txtFldBc);
		txtFldBc.setColumns(10);

		lblSemester = new JLabel("Semester:");
		GridBagConstraints gbc_lblSemester = new GridBagConstraints();
		gbc_lblSemester.ipady = 1;
		gbc_lblSemester.ipadx = 1;
		gbc_lblSemester.anchor = GridBagConstraints.WEST;
		gbc_lblSemester.insets = new Insets(5, 5, 5, 5);
		gbc_lblSemester.gridx = 0;
		gbc_lblSemester.gridy = 2;
		body.add(lblSemester, gbc_lblSemester);

		cbSem = new JComboBox(sem);
		// cbSem = new JComboBox<Integer>();
		cbSem.setEnabled(false);
		GridBagConstraints gbc_cbSem = new GridBagConstraints();
		gbc_cbSem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSem.gridwidth = 2;
		gbc_cbSem.ipady = 1;
		gbc_cbSem.ipadx = 1;
		gbc_cbSem.insets = new Insets(5, 5, 5, 5);
		gbc_cbSem.anchor = GridBagConstraints.WEST;
		gbc_cbSem.gridx = 1;
		gbc_cbSem.gridy = 2;
		cbSem.addItemListener(this);
		body.add(cbSem, gbc_cbSem);

		lblBatch = new JLabel("Batch:");
		GridBagConstraints gbc_lblBatch = new GridBagConstraints();
		gbc_lblBatch.ipady = 1;
		gbc_lblBatch.ipadx = 1;
		gbc_lblBatch.anchor = GridBagConstraints.WEST;
		gbc_lblBatch.insets = new Insets(5, 5, 5, 5);
		gbc_lblBatch.gridx = 0;
		gbc_lblBatch.gridy = 3;
		body.add(lblBatch, gbc_lblBatch);

		cbBatch = new JComboBox(batch);
		// cbBatch = new JComboBox<String>();
		cbBatch.setEnabled(false);
		GridBagConstraints gbc_cbBatch = new GridBagConstraints();
		gbc_cbBatch.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbBatch.gridwidth = 2;
		gbc_cbBatch.ipady = 1;
		gbc_cbBatch.ipadx = 1;
		gbc_cbBatch.insets = new Insets(5, 5, 5, 5);
		gbc_cbBatch.anchor = GridBagConstraints.WEST;
		gbc_cbBatch.gridx = 1;
		gbc_cbBatch.gridy = 3;
		body.add(cbBatch, gbc_cbBatch);

		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.ipady = 1;
		gbc_btnUpdate.ipadx = 1;
		gbc_btnUpdate.gridwidth = 2;
		gbc_btnUpdate.insets = new Insets(15, 5, 15, 5);
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 4;
		btnUpdate.addActionListener(this);
		body.add(btnUpdate, gbc_btnUpdate);

		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.ipady = 1;
		gbc_btnCancel.ipadx = 1;
		gbc_btnCancel.insets = new Insets(15, 5, 15, 5);
		gbc_btnCancel.gridwidth = 2;
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 4;
		btnCancel.addActionListener(this);
		body.add(btnCancel, gbc_btnCancel);

		sp.setBottomComponent(body);
		getContentPane().add(sp, BorderLayout.CENTER);

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		if (s.equals("Search")) {
			long enNo = Long.parseLong(txtFldSrch.getText().trim());
			rs = db.selquery("SELECT s.std_id, s.bc_id, b.sem, b.batch FROM student_detail s, sem_batch b WHERE b.batch_id = s.batch_id AND std_id ="
					+ enNo);
			try {
				rs.last();
				if (rs.getRow() == 1) {
					txtFldEnNo.setText(rs.getString(1));
					txtFldBc.setText(rs.getString(2));
					sem.clear();
					int i = rs.getInt(3);
					sem.add(i);
					if (i != 8)
						sem.add(i + 1);
					txtFldEnNo.setEnabled(false);
					txtFldBc.setEnabled(false);
				} else {
					txtFldEnNo.setText(txtFldSrch.getText());
					txtFldBc.setText(null);
					txtFldEnNo.setEnabled(true);
					txtFldBc.setEnabled(true);
					sem.clear();
					rs = db.selquery("SELECT DISTINCT sem FROM sem_batch");
					while (rs.next()) {
						sem.add(rs.getInt(1));
					}
				}
				cbSem.setSelectedIndex(0);
				cbSem.setEnabled(true);
				cbBatch.setEnabled(true);
				btnUpdate.setEnabled(true);
			} catch (Exception e) {
				new HandleException(e.getMessage());
			}
		} else if (s.equals("Update")) {
			long stdid = Long.parseLong(txtFldEnNo.getText());
			long bcid = Long.parseLong(txtFldBc.getText());
			String batchid = cbSem.getSelectedItem().toString()
					+ cbBatch.getSelectedItem().toString();
			if (txtFldEnNo.isEnabled()) {
				if (db.dmlstmt("INSERT INTO student_detail (std_id, bc_id, batch_id) VALUES ("
						+ stdid + "," + bcid + ",'" + batchid + "')") == 1)
					JOptionPane.showMessageDialog(rootPane,
							"Your data is inserted successfully.",
							"Insertion successful",
							JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane
							.showMessageDialog(rootPane,
									"Error!!! please check your data.",
									"Insertion unsuccessful",
									JOptionPane.ERROR_MESSAGE);
			} else {
				if (db.dmlstmt("UPDATE student_detail SET batch_id = '"
						+ batchid + "' WHERE std_id = " + stdid) == 1)
					JOptionPane.showMessageDialog(rootPane,
							"Your data is updated successfully.",
							"Updation successful",
							JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(rootPane,
							"Error!!! please check your data.",
							"Updation unsuccessful", JOptionPane.ERROR_MESSAGE);
			}

		} else if (s.equals("Cancel")) {
			try {
				rs.close();
			} catch (Exception e) {
				new HandleException(e.getMessage());
			}
			db.close();
			this.dispose();
		}
		SwingUtilities.updateComponentTreeUI(body);
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		int sem = Integer.parseInt(cbSem.getSelectedItem().toString());
		rs = db.selquery("SELECT DISTINCT batch FROM sem_batch WHERE sem = "
				+ sem + "ORDER BY batch");
		batch.clear();
		try {
			while (rs.next())
				batch.add(rs.getString(1));
		} catch (Exception e) {
			new HandleException(e.getMessage());
		}
		cbBatch.setSelectedIndex(0);
		SwingUtilities.updateComponentTreeUI(body);
	}
}
