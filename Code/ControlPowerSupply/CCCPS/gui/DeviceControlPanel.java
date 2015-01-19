package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;

import CCCPS.Dbcon;
import CCCPS.HandleException;

public class DeviceControlPanel extends JSplitPane implements ActionListener {
	private static final long serialVersionUID = 1L;

	String labname, labid;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
	private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lblAc1, lblAc2;
	private GridBagConstraints gbc_btn1, gbc_btn2, gbc_btn3, gbc_btn4,
			gbc_btn5, gbc_btn6, gbc_lbl1, gbc_lbl2, gbc_lbl3, gbc_lbl4,
			gbc_lbl5, gbc_lbl6, gbc_lblAc1, gbc_btn7, gbc_lblAc2, gbc_btn8;
	private JPanel l, r;
	private ImageIcon on, off;

	public DeviceControlPanel(String labname, String labid) {

		l = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		l.setLayout(gridBagLayout);

		off = new ImageIcon(
				DeviceControlPanel.class.getResource("/images/red.png"));
		on = new ImageIcon(
				DeviceControlPanel.class.getResource("/images/green.png"));

		this.labname = labname;
		this.labid = labid;

		btn1 = new JButton();
		gbc_btn1 = new GridBagConstraints();
		gbc_btn1.fill = GridBagConstraints.BOTH;
		gbc_btn1.ipady = 1;
		gbc_btn1.ipadx = 1;
		gbc_btn1.insets = new Insets(5, 5, 5, 5);
		gbc_btn1.gridwidth = 2;
		gbc_btn1.gridx = 0;
		gbc_btn1.gridy = 0;
		btn1.addActionListener(this);
		l.add(btn1, gbc_btn1);

		lbl1 = new JLabel("Light 1");
		gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.fill = GridBagConstraints.BOTH;
		gbc_lbl1.ipady = 1;
		gbc_lbl1.ipadx = 1;
		gbc_lbl1.insets = new Insets(5, 5, 5, 5);
		gbc_lbl1.gridx = 2;
		gbc_lbl1.gridy = 0;
		l.add(lbl1, gbc_lbl1);

		btn4 = new JButton();
		gbc_btn4 = new GridBagConstraints();
		gbc_btn4.ipady = 1;
		gbc_btn4.ipadx = 1;
		gbc_btn4.fill = GridBagConstraints.BOTH;
		gbc_btn4.gridwidth = 2;
		gbc_btn4.insets = new Insets(5, 5, 5, 5);
		gbc_btn4.gridx = 4;
		gbc_btn4.gridy = 0;
		btn4.addActionListener(this);
		l.add(btn4, gbc_btn4);

		lbl4 = new JLabel("Light 4");
		gbc_lbl4 = new GridBagConstraints();
		gbc_lbl4.fill = GridBagConstraints.BOTH;
		gbc_lbl4.ipady = 1;
		gbc_lbl4.ipadx = 1;
		gbc_lbl4.insets = new Insets(5, 5, 5, 5);
		gbc_lbl4.gridx = 6;
		gbc_lbl4.gridy = 0;
		l.add(lbl4, gbc_lbl4);

		btn2 = new JButton();
		gbc_btn2 = new GridBagConstraints();
		gbc_btn2.fill = GridBagConstraints.BOTH;
		gbc_btn2.ipady = 1;
		gbc_btn2.ipadx = 1;
		gbc_btn2.gridwidth = 2;
		gbc_btn2.insets = new Insets(5, 5, 5, 5);
		gbc_btn2.gridx = 0;
		gbc_btn2.gridy = 2;
		btn2.addActionListener(this);
		l.add(btn2, gbc_btn2);

		btn7 = new JButton();
		gbc_btn7 = new GridBagConstraints();
		gbc_btn7.ipady = 1;
		gbc_btn7.ipadx = 1;
		gbc_btn7.fill = GridBagConstraints.BOTH;
		gbc_btn7.gridwidth = 2;
		gbc_btn7.insets = new Insets(5, 5, 5, 5);
		gbc_btn7.gridx = 8;
		gbc_btn7.gridy = 0;
		btn7.addActionListener(this);
		l.add(btn7, gbc_btn7);

		lblAc1 = new JLabel("AC 1");
		gbc_lblAc1 = new GridBagConstraints();
		gbc_lblAc1.fill = GridBagConstraints.BOTH;
		gbc_lblAc1.ipady = 1;
		gbc_lblAc1.ipadx = 1;
		gbc_lblAc1.insets = new Insets(5, 5, 5, 5);
		gbc_lblAc1.gridx = 10;
		gbc_lblAc1.gridy = 0;
		l.add(lblAc1, gbc_lblAc1);

		lbl2 = new JLabel("Light 2");
		gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.ipady = 1;
		gbc_lbl2.ipadx = 1;
		gbc_lbl2.insets = new Insets(5, 5, 5, 5);
		gbc_lbl2.fill = GridBagConstraints.BOTH;
		gbc_lbl2.gridx = 2;
		gbc_lbl2.gridy = 2;
		l.add(lbl2, gbc_lbl2);

		btn5 = new JButton();
		gbc_btn5 = new GridBagConstraints();
		gbc_btn5.ipady = 1;
		gbc_btn5.ipadx = 1;
		gbc_btn5.fill = GridBagConstraints.BOTH;
		gbc_btn5.gridwidth = 2;
		gbc_btn5.insets = new Insets(5, 5, 5, 5);
		gbc_btn5.gridx = 4;
		gbc_btn5.gridy = 2;
		btn5.addActionListener(this);
		l.add(btn5, gbc_btn5);

		lbl5 = new JLabel("Light 5");
		gbc_lbl5 = new GridBagConstraints();
		gbc_lbl5.ipady = 1;
		gbc_lbl5.ipadx = 1;
		gbc_lbl5.fill = GridBagConstraints.BOTH;
		gbc_lbl5.insets = new Insets(5, 5, 5, 5);
		gbc_lbl5.gridx = 6;
		gbc_lbl5.gridy = 2;
		l.add(lbl5, gbc_lbl5);

		btn3 = new JButton();
		gbc_btn3 = new GridBagConstraints();
		gbc_btn3.fill = GridBagConstraints.BOTH;
		gbc_btn3.ipady = 1;
		gbc_btn3.ipadx = 1;
		gbc_btn3.gridwidth = 2;
		gbc_btn3.insets = new Insets(5, 5, 5, 5);
		gbc_btn3.gridx = 0;
		gbc_btn3.gridy = 4;
		btn3.addActionListener(this);
		l.add(btn3, gbc_btn3);

		btn8 = new JButton();
		gbc_btn8 = new GridBagConstraints();
		gbc_btn8.fill = GridBagConstraints.BOTH;
		gbc_btn8.ipady = 1;
		gbc_btn8.ipadx = 1;
		gbc_btn8.gridwidth = 2;
		gbc_btn8.insets = new Insets(5, 5, 5, 5);
		gbc_btn8.gridx = 8;
		gbc_btn8.gridy = 2;
		btn8.addActionListener(this);
		l.add(btn8, gbc_btn8);

		lblAc2 = new JLabel("AC 2");
		gbc_lblAc2 = new GridBagConstraints();
		gbc_lblAc2.ipady = 1;
		gbc_lblAc2.ipadx = 1;
		gbc_lblAc2.insets = new Insets(5, 5, 5, 5);
		gbc_lblAc2.fill = GridBagConstraints.BOTH;
		gbc_lblAc2.gridx = 10;
		gbc_lblAc2.gridy = 2;
		l.add(lblAc2, gbc_lblAc2);

		lbl3 = new JLabel("Light 3");
		gbc_lbl3 = new GridBagConstraints();
		gbc_lbl3.ipady = 1;
		gbc_lbl3.ipadx = 1;
		gbc_lbl3.insets = new Insets(5, 5, 5, 5);
		gbc_lbl3.fill = GridBagConstraints.BOTH;
		gbc_lbl3.gridx = 2;
		gbc_lbl3.gridy = 4;
		l.add(lbl3, gbc_lbl3);

		btn6 = new JButton();
		gbc_btn6 = new GridBagConstraints();
		gbc_btn6.fill = GridBagConstraints.BOTH;
		gbc_btn6.ipady = 1;
		gbc_btn6.ipadx = 1;
		gbc_btn6.gridwidth = 2;
		gbc_btn6.insets = new Insets(5, 5, 5, 5);
		gbc_btn6.gridx = 4;
		gbc_btn6.gridy = 4;
		btn6.addActionListener(this);
		l.add(btn6, gbc_btn6);

		lbl6 = new JLabel("Light 6");
		gbc_lbl6 = new GridBagConstraints();
		gbc_lbl6.fill = GridBagConstraints.BOTH;
		gbc_lbl6.ipady = 1;
		gbc_lbl6.ipadx = 1;
		gbc_lbl6.insets = new Insets(5, 5, 5, 5);
		gbc_lbl6.gridx = 6;
		gbc_lbl6.gridy = 4;
		l.add(lbl6, gbc_lbl6);

		GenerateTable gt = new GenerateTable();
		r = new JPanel(new BorderLayout());
		r.add(new JScrollPane(gt.populateTable(labname)), BorderLayout.CENTER);
		r.setMinimumSize(new Dimension(290, 240));

		setLeftComponent(l);
		setRightComponent(r);
	}

	public void setInitActioncmd(String btn, String actcmd) {
		if (btn.equalsIgnoreCase("btn1")) {
			btn1.setActionCommand("btn1");
			btn1.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn1.setIcon(on);
			else
				btn1.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn2")) {
			btn2.setActionCommand("btn2");
			btn2.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn2.setIcon(on);
			else
				btn2.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn3")) {
			btn3.setActionCommand("btn3");
			btn3.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn3.setIcon(on);
			else
				btn3.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn4")) {
			btn4.setActionCommand("btn4");
			btn4.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn4.setIcon(on);
			else
				btn4.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn5")) {
			btn5.setActionCommand("btn5");
			btn5.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn5.setIcon(on);
			else
				btn5.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn6")) {
			btn6.setActionCommand("btn6");
			btn6.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn6.setIcon(on);
			else
				btn6.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn7")) {
			btn7.setActionCommand("btn7");
			btn7.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn7.setIcon(on);
			else
				btn7.setIcon(off);
		} else if (btn.equalsIgnoreCase("btn8")) {
			btn8.setActionCommand("btn8");
			btn8.setText(actcmd);
			if (actcmd.equalsIgnoreCase("on"))
				btn8.setIcon(on);
			else
				btn8.setIcon(off);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		String actcmd = ae.getActionCommand();
		String fb = Network.ServerCCCPS.getFeedbackWrapper(labid);
		if (actcmd.equals("btn1")) {
			if (fb.charAt(0) == '1')
				Network.ServerCCCPS.sendMessge(labid, 0, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 0, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(0) == '1')
				this.setInitActioncmd("btn1", "on");
			else
				this.setInitActioncmd("btn1", "off");
		} else if (actcmd.equals("btn2")) {
			if (fb.charAt(1) == '1')
				Network.ServerCCCPS.sendMessge(labid, 1, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 1, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(1) == '1')
				this.setInitActioncmd("btn2", "on");
			else
				this.setInitActioncmd("btn2", "off");
		} else if (actcmd.equals("btn3")) {
			if (fb.charAt(2) == '1')
				Network.ServerCCCPS.sendMessge(labid, 2, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 2, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(2) == '1')
				this.setInitActioncmd("btn3", "on");
			else
				this.setInitActioncmd("btn3", "off");
		} else if (actcmd.equals("btn4")) {
			if (fb.charAt(3) == '1')
				Network.ServerCCCPS.sendMessge(labid, 3, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 3, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(3) == '1')
				this.setInitActioncmd("btn4", "on");
			else
				this.setInitActioncmd("btn4", "off");
		} else if (actcmd.equals("btn5")) {
			if (fb.charAt(4) == '1')
				Network.ServerCCCPS.sendMessge(labid, 4, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 4, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(4) == '1')
				this.setInitActioncmd("btn5", "on");
			else
				this.setInitActioncmd("btn5", "off");
		} else if (actcmd.equals("btn6")) {
			if (fb.charAt(5) == '1')
				Network.ServerCCCPS.sendMessge(labid, 5, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 5, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(5) == '1')
				this.setInitActioncmd("btn6", "on");
			else
				this.setInitActioncmd("btn6", "off");
		} else if (actcmd.equals("btn7")) {
			if (fb.charAt(6) == '1')
				Network.ServerCCCPS.sendMessge(labid, 6, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 6, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(6) == '1')
				this.setInitActioncmd("btn7", "on");
			else
				this.setInitActioncmd("btn7", "off");
		} else if (actcmd.equals("btn8")) {
			if (fb.charAt(7) == '1')
				Network.ServerCCCPS.sendMessge(labid, 7, 0);
			else
				Network.ServerCCCPS.sendMessge(labid, 7, 1);
			if (Network.ServerCCCPS.getFeedbackWrapper(labid).charAt(7) == '1')
				this.setInitActioncmd("btn8", "on");
			else
				this.setInitActioncmd("btn8", "off");
		}
		SwingUtilities.updateComponentTreeUI(leftComponent);
	}
}

class GenerateTable {
	private Dbcon db;
	private ResultSet rs;
	JTable table;
	private Date dt;
	private Calendar cal;
	private Object column[], rows[][];

	public Component populateTable(String lab) {
		dt = new Date();
		cal = Calendar.getInstance();
		cal.setTime(dt);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();
		String query = "SELECT S.SUB_NM as subject, B.SEM as sem, B.BATCH as batch, M.TIME as slot FROM LAB_TT L, SUB_DETAIL S, SEM_BATCH B, SLOT_MAP M WHERE S.SUB_ID=L.SUB_ID AND B.BATCH_ID=L.BATCH_ID AND L.SLOT_ID=M.SLOT_ID AND L.LAB_NM ='"
				+ lab + "' AND L.SLOT_ID LIKE '" + day + "%'";
		rs = db.selquery(query);
		try {
			rs.last();
			ResultSetMetaData rsmd = rs.getMetaData();
			column = new Object[rsmd.getColumnCount()];
			rows = new Object[rs.getRow()][column.length];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				column[i] = rsmd.getColumnName(i + 1);
			}
			rs.beforeFirst();
			int r = 0;
			while (rs.next()) {
				for (int c = 0; c < rsmd.getColumnCount(); c++)
					rows[r][c] = rs.getObject(c + 1);
				r++;
			}

		} catch (Exception e) {
			new HandleException(e.getMessage());
		}
		table = new JTable(rows, column);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		db.close();
		return table;
	}

	public Component populateTable() {
		dt = new Date();
		cal = Calendar.getInstance();
		cal.setTime(dt);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;

		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();
		String query = "SELECT L.LAB_NM AS \"lab name\", S.SUB_NM as subject, B.SEM as sem, B.BATCH as batch, M.TIME as slot FROM LAB_TT L, SUB_DETAIL S, SEM_BATCH B, SLOT_MAP M WHERE S.SUB_ID=L.SUB_ID AND B.BATCH_ID=L.BATCH_ID AND L.SLOT_ID=M.SLOT_ID "
				+ " AND L.SLOT_ID LIKE '" + day + "%'";
		rs = db.selquery(query);
		try {
			rs.last();
			ResultSetMetaData rsmd = rs.getMetaData();
			column = new Object[rsmd.getColumnCount()];
			rows = new Object[rs.getRow()][column.length];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				column[i] = rsmd.getColumnName(i + 1);
			}
			rs.beforeFirst();
			int r = 0;
			while (rs.next()) {
				for (int c = 0; c < rsmd.getColumnCount(); c++)
					rows[r][c] = rs.getObject(c + 1);
				r++;
			}

		} catch (Exception e) {
			new HandleException(e.getMessage());
		}
		table = new JTable(rows, column);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		db.close();
		return table;
	}
}