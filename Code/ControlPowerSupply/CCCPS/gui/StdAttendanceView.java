package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import CCCPS.Dbcon;
import CCCPS.HandleException;

public class StdAttendanceView extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField txtFldEnNo;
	private JLabel lblEnrollmentNo;
	private JPanel panel, panel1, pnlView;
	private JButton btnView, btnOk;
	private JTable jtbl;
	private Object column[], rows[][];
	private Dbcon db;
	private ResultSet rs;
	private JScrollPane sp;
	private long stdid;

	public StdAttendanceView() {
		setSize(new Dimension(450, 250));
		setResizable(false);
		setTitle("View attendance of a student");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(100, 100);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblEnrollmentNo = new JLabel("Enrollment No:");
		GridBagConstraints gbc_lblEnrollmentNo = new GridBagConstraints();
		gbc_lblEnrollmentNo.ipady = 5;
		gbc_lblEnrollmentNo.ipadx = 5;
		gbc_lblEnrollmentNo.anchor = GridBagConstraints.EAST;
		gbc_lblEnrollmentNo.insets = new Insets(5, 5, 5, 5);
		gbc_lblEnrollmentNo.gridx = 0;
		gbc_lblEnrollmentNo.gridy = 0;
		panel.add(lblEnrollmentNo, gbc_lblEnrollmentNo);

		txtFldEnNo = new JTextField();
		GridBagConstraints gbc_txtFldEnNo = new GridBagConstraints();
		gbc_txtFldEnNo.ipady = 1;
		gbc_txtFldEnNo.ipadx = 1;
		gbc_txtFldEnNo.gridwidth = 2;
		gbc_txtFldEnNo.insets = new Insets(5, 5, 5, 5);
		gbc_txtFldEnNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldEnNo.gridx = 1;
		gbc_txtFldEnNo.gridy = 0;
		panel.add(txtFldEnNo, gbc_txtFldEnNo);
		txtFldEnNo.setColumns(10);

		btnView = new JButton("View");
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.ipady = 1;
		gbc_btnView.ipadx = 1;
		gbc_btnView.insets = new Insets(5, 5, 5, 5);
		gbc_btnView.gridx = 3;
		gbc_btnView.gridy = 0;
		btnView.addActionListener(this);
		panel.add(btnView, gbc_btnView);

		panel1 = new JPanel();
		getContentPane().add(panel1, BorderLayout.SOUTH);

		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		panel1.add(btnOk);

		pnlView = new JPanel();
		pnlView.add(new JScrollPane(jtbl));
		getContentPane().add(pnlView, BorderLayout.CENTER);

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Ok")) {
			db.close();
			this.dispose();
		} else {
			stdid = Long.parseLong(txtFldEnNo.getText());
			populate(stdid);
			SwingUtilities.updateComponentTreeUI(rootPane);
		}
	}

	public void populate(long id) {
		try {
			if (jtbl != null)
				pnlView.remove(0);
			String query = "SELECT L.\"SUBJECT NAME\", L.\"STUDENT PRESENT DAYS\", M.\"TOTAL PRESENT DAYS\" FROM (SELECT D.SUB_NM AS \"SUBJECT NAME\", COUNT(A.SUB_ID) AS \"STUDENT PRESENT DAYS\" FROM ATTENDANCE A, SUB_DETAIL D WHERE A.SUB_ID=D.SUB_ID AND A.STD_ID= "
					+ stdid
					+ " GROUP BY D.SUB_NM) L, (SELECT COUNT(B.SUB_ID) AS \"TOTAL PRESENT DAYS\", D.SUB_NM AS \"SUBJECT NAME\" FROM BATCH_WKDAY B, SUB_DETAIL D WHERE B.SUB_ID=D.SUB_ID AND B.BATCH_ID=(SELECT BATCH_ID FROM STUDENT_DETAIL WHERE STD_ID="
					+ stdid
					+ " ) GROUP BY D.SUB_NM) M WHERE L.\"SUBJECT NAME\" = M.\"SUBJECT NAME\"";
			rs = db.selquery(query);
			rs.last();
			ResultSetMetaData rsmd = rs.getMetaData();
			int ttlcolumn = rsmd.getColumnCount() + 1;
			column = new Object[ttlcolumn];
			rows = new Object[rs.getRow()][column.length];
			for (int i = 0; i < ttlcolumn - 1; i++) {
				column[i] = rsmd.getColumnName(i + 1);
			}
			rs.beforeFirst();
			int r = 0;
			while (rs.next()) {
				for (int c = 0; c < ttlcolumn - 1; c++)
					rows[r][c] = rs.getObject(c + 1);
				r++;
			}
			column[ttlcolumn - 1] = "PERSENTAGE";
			rs.last();
			for (int i = 0; i < rs.getRow(); i++)
				rows[i][ttlcolumn - 1] = (Integer.parseInt(rows[i][1]
						.toString()) * 100)
						/ Integer.parseInt(rows[i][2].toString());
		} catch (Exception e) {
			new HandleException(e.getMessage());
		}
		jtbl = new JTable(rows, column);
		sp = new JScrollPane(jtbl);
		pnlView.add(sp);
		SwingUtilities.updateComponentTreeUI(rootPane);
	}
}
