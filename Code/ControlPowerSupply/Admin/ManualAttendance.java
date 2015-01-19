import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualAttendance extends JDialog implements ActionListener {
	private static final long serialVersionUID = 5787017266981360770L;
	private JTextField txtFldStdId, txtFldSubId, txtFldDt;
	private Dbcon db;

	public ManualAttendance() {
		setTitle("Manual Attendance Mark");
		setLocation(100, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		btnPanel.add(btnInsert);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnPanel.add(btnCancel);

		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.1, 0.0 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblStdId = new JLabel("Std ID:");
		GridBagConstraints gbc_lblStdId = new GridBagConstraints();
		gbc_lblStdId.ipady = 1;
		gbc_lblStdId.ipadx = 1;
		gbc_lblStdId.insets = new Insets(5, 5, 5, 5);
		gbc_lblStdId.anchor = GridBagConstraints.WEST;
		gbc_lblStdId.gridx = 0;
		gbc_lblStdId.gridy = 0;
		contentPanel.add(lblStdId, gbc_lblStdId);

		txtFldStdId = new JTextField();
		GridBagConstraints gbc_txtFldStdId = new GridBagConstraints();
		gbc_txtFldStdId.gridwidth = 2;
		gbc_txtFldStdId.ipady = 1;
		gbc_txtFldStdId.ipadx = 1;
		gbc_txtFldStdId.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldStdId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldStdId.gridx = 1;
		gbc_txtFldStdId.gridy = 0;
		contentPanel.add(txtFldStdId, gbc_txtFldStdId);
		txtFldStdId.setColumns(10);

		JLabel lblSubjectId = new JLabel("Subject ID:");
		GridBagConstraints gbc_lblSubjectId = new GridBagConstraints();
		gbc_lblSubjectId.ipady = 1;
		gbc_lblSubjectId.ipadx = 1;
		gbc_lblSubjectId.insets = new Insets(5, 5, 5, 5);
		gbc_lblSubjectId.anchor = GridBagConstraints.WEST;
		gbc_lblSubjectId.gridx = 0;
		gbc_lblSubjectId.gridy = 1;
		contentPanel.add(lblSubjectId, gbc_lblSubjectId);

		txtFldSubId = new JTextField();
		GridBagConstraints gbc_txtFldSubId = new GridBagConstraints();
		gbc_txtFldSubId.gridwidth = 2;
		gbc_txtFldSubId.ipady = 1;
		gbc_txtFldSubId.ipadx = 1;
		gbc_txtFldSubId.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldSubId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldSubId.gridx = 1;
		gbc_txtFldSubId.gridy = 1;
		contentPanel.add(txtFldSubId, gbc_txtFldSubId);
		txtFldSubId.setColumns(10);

		JLabel lblDate = new JLabel("Date:");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.ipady = 1;
		gbc_lblDate.ipadx = 1;
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(5, 5, 5, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 2;
		contentPanel.add(lblDate, gbc_lblDate);

		txtFldDt = new JTextField();
		GridBagConstraints gbc_txtFldDt = new GridBagConstraints();
		gbc_txtFldDt.ipady = 1;
		gbc_txtFldDt.ipadx = 1;
		gbc_txtFldDt.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldDt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldDt.gridx = 1;
		gbc_txtFldDt.gridy = 2;
		contentPanel.add(txtFldDt, gbc_txtFldDt);
		txtFldDt.setColumns(10);

		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		GridBagConstraints gbc_lblddmmyyyy = new GridBagConstraints();
		gbc_lblddmmyyyy.ipady = 1;
		gbc_lblddmmyyyy.ipadx = 1;
		gbc_lblddmmyyyy.insets = new Insets(5, 5, 5, 5);
		gbc_lblddmmyyyy.gridx = 2;
		gbc_lblddmmyyyy.gridy = 2;
		contentPanel.add(lblddmmyyyy, gbc_lblddmmyyyy);

		pack();

		db = new Dbcon();
		db.open();
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Insert")) {
			if (db.dmlstmt("INSERT INTO ATTENDANCE (STD_ID, SUB_ID, DAT) VALUES('"
					+ txtFldStdId.getText()
					+ "','"
					+ txtFldSubId.getText()
					+ "','" + txtFldDt.getText() + "')") == 1){
				JOptionPane.showMessageDialog(rootPane,
						"One row is inserted successfully.",
						"Insertion Successfull",
						JOptionPane.INFORMATION_MESSAGE);
				txtFldStdId.setText(null);
				txtFldSubId.setText(null);
				txtFldDt.setText(null);
			}
			else
				JOptionPane.showMessageDialog(rootPane,
						"Error!!! Please check your data.",
						"Insertion Unsuccessfull", JOptionPane.ERROR_MESSAGE);
		} else if (ae.getActionCommand().equals("Cancel"))
			this.dispose();
	}
}