
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

class SlotInsert extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JComboBox cbDay, cbTime;
	private JLabel lblDay, lblStartTime, lblNote;
	private JButton btnUpdate, btnCancel;
	private Dbcon db;
	private ResultSet rs;
	private Vector<String> Vtime = new Vector<String>();

	public SlotInsert() {
		setSize(new Dimension(270, 180));
		setResizable(false);
		setLocation(100, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Insert Slot");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblDay = new JLabel("Day:");
		GridBagConstraints gbc_lblDay = new GridBagConstraints();
		gbc_lblDay.ipady = 1;
		gbc_lblDay.ipadx = 1;
		gbc_lblDay.insets = new Insets(5, 5, 5, 5);
		gbc_lblDay.anchor = GridBagConstraints.WEST;
		gbc_lblDay.gridx = 0;
		gbc_lblDay.gridy = 0;
		getContentPane().add(lblDay, gbc_lblDay);

		cbDay = new JComboBox();
		cbDay.addItem("Monday");
		cbDay.addItem("Tuesday");
		cbDay.addItem("Wednesday");
		cbDay.addItem("Thrusday");
		cbDay.addItem("Friday");
		cbDay.addItem("Saturday");
		GridBagConstraints gbc_cbDay = new GridBagConstraints();
		gbc_cbDay.ipady = 1;
		gbc_cbDay.ipadx = 1;
		gbc_cbDay.insets = new Insets(5, 5, 5, 5);
		gbc_cbDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDay.gridx = 1;
		gbc_cbDay.gridy = 0;
		getContentPane().add(cbDay, gbc_cbDay);

		lblStartTime = new JLabel("Start Time:");
		GridBagConstraints gbc_lblSlotTime = new GridBagConstraints();
		gbc_lblSlotTime.ipady = 1;
		gbc_lblSlotTime.ipadx = 1;
		gbc_lblSlotTime.anchor = GridBagConstraints.WEST;
		gbc_lblSlotTime.insets = new Insets(5, 5, 5, 5);
		gbc_lblSlotTime.gridx = 0;
		gbc_lblSlotTime.gridy = 1;
		getContentPane().add(lblStartTime, gbc_lblSlotTime);

		cbTime = new JComboBox(Vtime);
		cbTime.setEditable(true);
		GridBagConstraints gbc_cbTime = new GridBagConstraints();
		gbc_cbTime.ipady = 1;
		gbc_cbTime.ipadx = 1;
		gbc_cbTime.insets = new Insets(5, 5, 5, 5);
		gbc_cbTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTime.gridx = 1;
		gbc_cbTime.gridy = 1;
		getContentPane().add(cbTime, gbc_cbTime);

		lblNote = new JLabel("Note: Enter time in format like (10.00-12.00)");
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.ipady = 1;
		gbc_lblNote.ipadx = 1;
		gbc_lblNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNote.insets = new Insets(5, 5, 5, 5);
		gbc_lblNote.gridwidth = 2;
		gbc_lblNote.gridx = 0;
		gbc_lblNote.gridy = 2;
		getContentPane().add(lblNote, gbc_lblNote);

		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.ipadx = 1;
		gbc_btnUpdate.ipady = 1;
		gbc_btnUpdate.insets = new Insets(5, 5, 5, 5);
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 3;
		getContentPane().add(btnUpdate, gbc_btnUpdate);
		btnUpdate.addActionListener(this);

		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.ipady = 1;
		gbc_btnCancel.ipadx = 1;
		gbc_btnCancel.insets = new Insets(5, 5, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 3;
		getContentPane().add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(this);

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();
		rs = db.selquery("SELECT DISTINCT time FROM slot_map ORDER BY time");
		try {
			while (rs.next())
				Vtime.add(rs.getString(1));
		} catch (Exception e) {
			new HandleException(e.getMessage());
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Update")) {
			String day = cbDay.getSelectedItem().toString().substring(0, 3)
					.toUpperCase();
			int dayindex = cbDay.getSelectedIndex() + 1;
			String time = cbTime.getSelectedItem().toString();
			int timeindex;
			if (cbTime.getSelectedIndex() == -1) {
				timeindex = Vtime.size() + 1;
				Vtime.add(time);
			} else
				timeindex = Vtime.indexOf(time) + 1;
			if (db.dmlstmt("INSERT INTO slot_map (day, time, slot_id) VALUES ('"
					+ day + "','" + time + "'," + dayindex + timeindex + ")") == 1)
				JOptionPane
						.showMessageDialog(rootPane,
								"Slot is inserted successfully.",
								"Insertion Successful",
								JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(rootPane,
						"Error!! please check your data.",
						"Insertion Unsuccessful", JOptionPane.ERROR_MESSAGE);
		} else {
			db.close();
			this.dispose();
		}
		SwingUtilities.updateComponentTreeUI(cbTime);
	}

}