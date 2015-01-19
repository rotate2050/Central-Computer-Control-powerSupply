import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StudentMultiUpdate extends JSplitPane implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel t, b;
	private Dbcon db;
	private ResultSet rs;
	private JLabel lblSemeseter;
	private JButton btnView, btnUpdate;
	private JComboBox cbSem;
	private MyTableModle model;

	public StudentMultiUpdate() {
		super(VERTICAL_SPLIT);

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();

		t = new JPanel();
		GridBagLayout gbl_t = new GridBagLayout();
		gbl_t.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_t.rowHeights = new int[] { 0 };
		gbl_t.columnWeights = new double[] { 0.0, 0.0, 0.1, 0.0 };
		gbl_t.rowWeights = new double[] { 0.0 };
		t.setLayout(gbl_t);

		lblSemeseter = new JLabel("Semeseter:");
		GridBagConstraints gbc_lblSemeseter = new GridBagConstraints();
		gbc_lblSemeseter.anchor = GridBagConstraints.EAST;
		gbc_lblSemeseter.ipady = 1;
		gbc_lblSemeseter.ipadx = 1;
		gbc_lblSemeseter.insets = new Insets(5, 5, 5, 5);
		gbc_lblSemeseter.gridx = 0;
		gbc_lblSemeseter.gridy = 0;
		t.add(lblSemeseter, gbc_lblSemeseter);

		cbSem = new JComboBox();
		String query = "SELECT DISTINCT SEM FROM SEM_BATCH ORDER BY SEM";
		rs = db.selquery(query);
		try {
			while (rs.next())
				cbSem.addItem(rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			new HandleException(e.getMessage());
		}
		cbSem.setSelectedIndex(0);
		GridBagConstraints gbc_cbSem = new GridBagConstraints();
		gbc_cbSem.gridwidth = 2;
		gbc_cbSem.ipady = 1;
		gbc_cbSem.ipadx = 1;
		gbc_cbSem.insets = new Insets(5, 5, 5, 5);
		gbc_cbSem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSem.gridx = 1;
		gbc_cbSem.gridy = 0;
		t.add(cbSem, gbc_cbSem);

		btnView = new JButton("View");
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.ipady = 1;
		gbc_btnView.ipadx = 1;
		gbc_btnView.insets = new Insets(5, 5, 5, 5);
		gbc_btnView.gridx = 3;
		gbc_btnView.gridy = 0;
		btnView.addActionListener(this);
		t.add(btnView, gbc_btnView);

		b = new JPanel(new BorderLayout(10, 10));
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		b.add(new JScrollPane(table), BorderLayout.CENTER);
		setTopComponent(t);
		setBottomComponent(b);
	}

	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		if (actcmd.equals("View")) {
			int sem = Integer.parseInt(cbSem.getSelectedItem().toString());
			b.removeAll();
			populate(sem);
			b.revalidate();

		} else if (actcmd.equals("Update")) {
			if (table != null)
				makeUpdate();
		}
	}

	public void populate(int sem) {
		model = new MyTableModle(sem);
		table = new JTable(model);
		b.add(new JScrollPane(table), BorderLayout.CENTER);
		b.add(btnUpdate, BorderLayout.SOUTH);
	}

	void makeUpdate() {
		int colcount = model.getColumnCount();
		int rowcount = model.getRowCount();
		try {
			for (int i = 0; i < rowcount; i++) {
				if ((Boolean) model.getValueAt(i, colcount - 1)) {
					model.makeChange(model.getValueAt(i, colcount - 3), i + 1,
							colcount - 3);
				}
			}
			model.commitChanges();
		} catch (Exception e) {
			e.printStackTrace();
			// new HandleException(e.getMessage());
		}
	}
}

class MyTableModle extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Dbcon db;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private Object rows[][], columns[];

	public MyTableModle(int sem) {
		try {
			db = new Dbcon();
			db.open();
			String query = "SELECT S.STD_ID \"Enrollment No\", B.SEM \"Sem\", B.BATCH \"Batch\" FROM STUDENT_DETAIL S, SEM_BATCH B WHERE S.BATCH_ID = B.BATCH_ID AND B.SEM="
					+ sem + " ORDER BY 1";
			rs = db.selquery(query);
			rs.last();
			rsmd = rs.getMetaData();
			int ttlcolumn = rsmd.getColumnCount() + 1;
			columns = new Object[ttlcolumn];
			rows = new Object[rs.getRow()][columns.length];
			for (int i = 0; i < ttlcolumn - 1; i++) {
				columns[i] = rsmd.getColumnName(i + 1);
			}
			rs.beforeFirst();
			int r = 0;
			while (rs.next()) {
				for (int c = 0; c < ttlcolumn - 1; c++)
					rows[r][c] = rs.getObject(c + 1);
				r++;
			}
			columns[ttlcolumn - 1] = "Update It";
			rs.last();
			for (int i = 0; i < rs.getRow(); i++)
				rows[i][ttlcolumn - 1] = Boolean.FALSE;
			// db.close();
		} catch (Exception e) {
			e.printStackTrace();
			new HandleException(e.getMessage());
		}
	}

	public int getColumnCount() {
		return columns.length;
	}

	public String getColumnName(int column) {
		return columns[column].toString();
	}

	public int getRowCount() {
		return rows.length;
	}

	public Object getValueAt(int row, int column) {
		return rows[row][column];
	}

	public Class<? extends Object> getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}

	public void setValueAt(Object value, int row, int column) {
		rows[row][column] = value;
	}

	public boolean isCellEditable(int row, int column) {
		return (column != 0);
	}

	public void makeChange(Object val, int row, int column) throws SQLException {
		rs.absolute(row);
		System.out.println(rsmd.isReadOnly(2));
		rs.updateInt(column, Integer.parseInt(val.toString()));
	}

	public void commitChanges() throws SQLException {
		rs.updateRow();
	}
}