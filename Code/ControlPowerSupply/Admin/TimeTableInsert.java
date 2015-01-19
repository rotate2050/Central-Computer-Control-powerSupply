import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class TimeTableInsert extends JDialog implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;

	private JTextField txtFldLab;
	private JButton btnUpdate, btnCancel;
	private JLabel lblLab, lblDay, lblTime, lblSemester, lblSubjectName,
			lblBatch;
	private JComboBox cbSubj, cbDay, cbTime, cbBatch, cbSem;
	private Dbcon db;
	private JPanel contentPanel, btnPanel;
	private ResultSet rs, rs1;
	private Vector<String> batch, subj, time;
	private Vector<Integer> sem;

	public TimeTableInsert() {
		setSize(new Dimension(500, 350));
		setLocation(100, 100);
		setTitle("Insert TimeTable");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.1 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0 };
		contentPanel = new JPanel(gridBagLayout);

		batch = new Vector<String>();
		sem = new Vector<Integer>();
		subj = new Vector<String>();
		time = new Vector<String>();

		lblLab = new JLabel("Lab:");
		GridBagConstraints gbc_lblLab = new GridBagConstraints();
		gbc_lblLab.ipady = 1;
		gbc_lblLab.ipadx = 1;
		gbc_lblLab.insets = new Insets(5, 5, 5, 5);
		gbc_lblLab.anchor = GridBagConstraints.WEST;
		gbc_lblLab.gridx = 0;
		gbc_lblLab.gridy = 0;
		contentPanel.add(lblLab, gbc_lblLab);

		txtFldLab = new JTextField();
		GridBagConstraints gbc_txtFldLab = new GridBagConstraints();
		gbc_txtFldLab.gridwidth = 2;
		gbc_txtFldLab.ipadx = 1;
		gbc_txtFldLab.ipady = 1;
		gbc_txtFldLab.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldLab.anchor = GridBagConstraints.WEST;
		gbc_txtFldLab.gridx = 1;
		gbc_txtFldLab.gridy = 0;
		contentPanel.add(txtFldLab, gbc_txtFldLab);
		txtFldLab.setColumns(10);
		// txtFldLab.addFocusListener(this);

		lblDay = new JLabel("Day:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.ipady = 1;
		gbc_label.ipadx = 1;
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(5, 5, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		contentPanel.add(lblDay, gbc_label);

		cbDay = new JComboBox();
		cbDay.addItem("Monday");
		cbDay.addItem("Tuesday");
		cbDay.addItem("Wednesday");
		cbDay.addItem("Thursday");
		cbDay.addItem("Friday");
		cbDay.addItem("Saturday");
		GridBagConstraints gbc_cbDay = new GridBagConstraints();
		gbc_cbDay.ipady = 1;
		gbc_cbDay.ipadx = 1;
		gbc_cbDay.gridwidth = 2;
		gbc_cbDay.anchor = GridBagConstraints.WEST;
		gbc_cbDay.insets = new Insets(5, 5, 5, 5);
		gbc_cbDay.gridx = 1;
		gbc_cbDay.gridy = 1;
		contentPanel.add(cbDay, gbc_cbDay);
		cbDay.setSelectedIndex(0);

		lblTime = new JLabel("Time:");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.ipady = 1;
		gbc_lblTime.ipadx = 1;
		gbc_lblTime.anchor = GridBagConstraints.WEST;
		gbc_lblTime.insets = new Insets(5, 5, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 2;
		contentPanel.add(lblTime, gbc_lblTime);

		cbTime = new JComboBox(time);
		GridBagConstraints gbc_cbTime = new GridBagConstraints();
		gbc_cbTime.ipady = 1;
		gbc_cbTime.ipadx = 1;
		gbc_cbTime.gridwidth = 2;
		gbc_cbTime.insets = new Insets(5, 5, 5, 5);
		gbc_cbTime.anchor = GridBagConstraints.WEST;
		gbc_cbTime.gridx = 1;
		gbc_cbTime.gridy = 2;
		contentPanel.add(cbTime, gbc_cbTime);

		lblSemester = new JLabel("Semester:");
		GridBagConstraints gbc_lblSemester = new GridBagConstraints();
		gbc_lblSemester.ipady = 1;
		gbc_lblSemester.ipadx = 1;
		gbc_lblSemester.anchor = GridBagConstraints.WEST;
		gbc_lblSemester.insets = new Insets(5, 5, 5, 5);
		gbc_lblSemester.gridx = 0;
		gbc_lblSemester.gridy = 3;
		contentPanel.add(lblSemester, gbc_lblSemester);

		cbSem = new JComboBox(sem);
		GridBagConstraints gbc_cbSem = new GridBagConstraints();
		gbc_cbSem.ipadx = 1;
		gbc_cbSem.ipady = 1;
		gbc_cbSem.gridwidth = 2;
		gbc_cbSem.insets = new Insets(5, 5, 5, 5);
		gbc_cbSem.anchor = GridBagConstraints.WEST;
		gbc_cbSem.gridx = 1;
		gbc_cbSem.gridy = 3;
		cbSem.addItemListener(this);
		contentPanel.add(cbSem, gbc_cbSem);

		lblBatch = new JLabel("Batch:");
		GridBagConstraints gbc_lblBatch = new GridBagConstraints();
		gbc_lblBatch.ipady = 1;
		gbc_lblBatch.ipadx = 1;
		gbc_lblBatch.anchor = GridBagConstraints.WEST;
		gbc_lblBatch.insets = new Insets(5, 5, 5, 5);
		gbc_lblBatch.gridx = 0;
		gbc_lblBatch.gridy = 4;
		contentPanel.add(lblBatch, gbc_lblBatch);

		cbBatch = new JComboBox(batch);
		GridBagConstraints gbc_cbBatch = new GridBagConstraints();
		gbc_cbBatch.ipady = 1;
		gbc_cbBatch.ipadx = 1;
		gbc_cbBatch.gridwidth = 2;
		gbc_cbBatch.insets = new Insets(5, 5, 5, 5);
		gbc_cbBatch.anchor = GridBagConstraints.WEST;
		gbc_cbBatch.gridx = 1;
		gbc_cbBatch.gridy = 4;
		contentPanel.add(cbBatch, gbc_cbBatch);

		lblSubjectName = new JLabel("Subject Name:");
		GridBagConstraints gbc_lblSubjectName = new GridBagConstraints();
		gbc_lblSubjectName.ipady = 1;
		gbc_lblSubjectName.ipadx = 1;
		gbc_lblSubjectName.anchor = GridBagConstraints.WEST;
		gbc_lblSubjectName.insets = new Insets(5, 5, 5, 5);
		gbc_lblSubjectName.gridx = 0;
		gbc_lblSubjectName.gridy = 5;
		contentPanel.add(lblSubjectName, gbc_lblSubjectName);

		cbSubj = new JComboBox(subj);
		GridBagConstraints gbc_cbSubj = new GridBagConstraints();
		gbc_cbSubj.ipady = 1;
		gbc_cbSubj.ipadx = 1;
		gbc_cbSubj.gridwidth = 3;
		gbc_cbSubj.insets = new Insets(5, 5, 5, 5);
		gbc_cbSubj.anchor = GridBagConstraints.WEST;
		gbc_cbSubj.gridx = 1;
		gbc_cbSubj.gridy = 5;
		contentPanel.add(cbSubj, gbc_cbSubj);

		btnPanel = new JPanel();
		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.ipadx = 1;
		gbc_btnUpdate.ipady = 1;
		gbc_btnUpdate.gridwidth = 2;
		gbc_btnUpdate.insets = new Insets(15, 5, 5, 15);
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 6;
		btnUpdate.addActionListener(this);
		btnPanel.add(btnUpdate, gbc_btnUpdate);

		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.ipady = 1;
		gbc_btnCancel.ipadx = 1;
		gbc_btnCancel.insets = new Insets(15, 5, 5, 15);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 6;
		btnCancel.addActionListener(this);
		btnPanel.add(btnCancel, gbc_btnCancel);

		setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		try {
			db = new Dbcon();
			// db.open("test", "tiger");
			db.open();
			rs = db.selquery("SELECT DISTINCT sem FROM sem_batch ORDER BY sem");
			while (rs.next()) {
				sem.add(rs.getInt(1));
			}
			cbSem.setSelectedIndex(0);
			rs = db.selquery("SELECT DISTINCT time FROM slot_map ORDER BY time");
			while (rs.next()) {
				time.add(rs.getString(1));
			}
			cbTime.setSelectedIndex(0);
		} catch (Exception e) {
			new HandleException(e.getMessage());
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		int sem = Integer.parseInt(cbSem.getSelectedItem().toString());
		try {
			rs = db.selquery("SELECT DISTINCT batch FROM sem_batch WHERE sem = "
					+ sem + "ORDER BY batch");
			batch.clear();
			subj.clear();
			while (rs.next()) {
				batch.add(rs.getString(1));
			}
			rs1 = db.selquery("SELECT sub_nm, sub_id FROM sub_detail WHERE sem = "
					+ sem);
			while (rs1.next()) {
				subj.add(rs1.getString(1) + "--" + rs1.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			new HandleException(e.getMessage());
		}
		cbBatch.setSelectedIndex(0);
		cbSubj.setSelectedIndex(0);
		SwingUtilities.updateComponentTreeUI(rootPane);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("Update")) {
			String day = cbDay.getSelectedItem().toString().substring(0, 3)
					.toUpperCase();
			String time = cbTime.getSelectedItem().toString();
			rs = db.selquery("SELECT slot_id FROM slot_map WHERE day='" + day
					+ "' AND time='" + time + "'");
			try {
				rs.last();
				if (rs.getRow() == 1) {
					int slot = rs.getInt(1);
					String lab = txtFldLab.getText();
					String s[] = cbSubj.getSelectedItem().toString()
							.split("--");
					String batchid = cbSem.getSelectedItem().toString()
							+ cbBatch.getSelectedItem().toString();
					int subid = Integer.parseInt(s[1]);

					rs1 = db.selquery("SELECT * FROM lab_tt WHERE slot_id="
							+ slot + "AND lab_nm='" + lab + "'");
					rs1.last();

					if (rs1.getRow() == 1) {
						if (db.dmlstmt("UPDATE lab_tt SET sub_id=" + subid
								+ ", batch_id = '" + batchid
								+ "' WHERE lab_nm='" + lab + "' AND slot_id ="
								+ slot) == 1)
							JOptionPane.showMessageDialog(rootPane,
									"Timetable is updated successfully.",
									"Updation Successful",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(rootPane,
									"Error!! please check your data.",
									"Updation Unsuccessful",
									JOptionPane.ERROR_MESSAGE);
					} else {
						if (db.dmlstmt("INSERT INTO lab_tt (lab_nm, slot_id, sub_id, batch_id) VALUES('"
								+ lab
								+ "',"
								+ slot
								+ ","
								+ subid
								+ ",'"
								+ batchid + "')") == 1)
							JOptionPane
									.showMessageDialog(
											rootPane,
											"New record is inserted in timetable successfully.",
											"Insertion Successful",
											JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(rootPane,
									"Error!! please check your data.",
									"Insertion Unsuccessful",
									JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(rootPane,
							"Slot is not availabe.", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				new HandleException(e.getMessage());
			}
		} else if (actcmd.equals("Cancel")) {
			db.close();
			this.dispose();
		}
		contentPanel.revalidate();
	}
}