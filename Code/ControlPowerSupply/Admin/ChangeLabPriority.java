import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ChangeLabPriority extends JPanel implements ActionListener,
		ItemListener {

	private static final long serialVersionUID = -5383815339418828830L;
	private JTextField textField;
	private JComboBox cbLab, cbIP;
	private Vector<Object> IP;
	private Dbcon db;
	private ResultSet rs;

	public ChangeLabPriority() {
		setLayout(new BorderLayout(0, 0));

		JPanel btnPanel = new JPanel();
		add(btnPanel, BorderLayout.SOUTH);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		btnPanel.add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnPanel.add(btnCancel);

		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.1 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblLab = new JLabel("Lab:");
		GridBagConstraints gbc_lblLab = new GridBagConstraints();
		gbc_lblLab.ipady = 1;
		gbc_lblLab.ipadx = 1;
		gbc_lblLab.anchor = GridBagConstraints.WEST;
		gbc_lblLab.insets = new Insets(5, 5, 5, 5);
		gbc_lblLab.gridx = 0;
		gbc_lblLab.gridy = 0;
		contentPanel.add(lblLab, gbc_lblLab);

		cbLab = new JComboBox();
		db = new Dbcon();
		// db.open("mitesh", "m1i0t2e9s3h8");
		db.open();
		try {
			rs = db.selquery("SELECT DISTINCT LAB_NM FROM CLIENT ORDER BY LAB_NM");
			rs.last();
			if (rs.getRow() > 0) {
				rs.beforeFirst();
				while (rs.next())
					cbLab.addItem(rs.getObject(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// new HandleException(e.getMessage());
		}
		cbLab.addItemListener(this);
		cbLab.setSelectedIndex(0);
		GridBagConstraints gbc_cbLab = new GridBagConstraints();
		gbc_cbLab.gridwidth = 2;
		gbc_cbLab.ipady = 1;
		gbc_cbLab.ipadx = 1;
		gbc_cbLab.insets = new Insets(5, 5, 5, 5);
		gbc_cbLab.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbLab.gridx = 1;
		gbc_cbLab.gridy = 0;
		contentPanel.add(cbLab, gbc_cbLab);

		JLabel lblIp = new JLabel("IP:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.ipady = 1;
		gbc_lblIp.ipadx = 1;
		gbc_lblIp.anchor = GridBagConstraints.WEST;
		gbc_lblIp.insets = new Insets(5, 5, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 1;
		contentPanel.add(lblIp, gbc_lblIp);

		IP = new Vector<Object>();
		cbIP = new JComboBox(IP);
		cbIP.addItemListener(this);
		GridBagConstraints gbc_cbIP = new GridBagConstraints();
		gbc_cbIP.gridwidth = 2;
		gbc_cbIP.ipady = 1;
		gbc_cbIP.ipadx = 1;
		gbc_cbIP.insets = new Insets(5, 5, 5, 5);
		gbc_cbIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbIP.gridx = 1;
		gbc_cbIP.gridy = 1;
		contentPanel.add(cbIP, gbc_cbIP);

		JLabel lblPriority = new JLabel("Priority:");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.ipady = 1;
		gbc_lblPriority.ipadx = 1;
		gbc_lblPriority.anchor = GridBagConstraints.WEST;
		gbc_lblPriority.insets = new Insets(5, 5, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 2;
		contentPanel.add(lblPriority, gbc_lblPriority);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.ipady = 1;
		gbc_textField.ipadx = 1;
		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPanel.add(textField, gbc_textField);
		textField.setColumns(10);
	}

	public void itemStateChanged(ItemEvent ie) {
		Object source = ie.getItemSelectable();
		if (source == cbLab) {
			rs = db.selquery("SELECT IP FROM CLIENT WHERE LAB_NM = '"
					+ cbLab.getSelectedItem().toString() + "'");
			try {
				cbIP.removeAllItems();
				IP.clear();
				while (rs.next()) {
					IP.add(rs.getObject(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// new HandleException(e.getMessage());
			}
		} else if (source == cbIP) {
			rs = db.selquery("SELECT PRIORITY FROM CLIENT WHERE IP = '"
					+ cbIP.getSelectedItem().toString() + "'");
			try {
				rs.last();
				if (rs.getRow() == 1) {
					textField.setText(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
				// new HandleException(e.getMessage());
			}
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Update")) {

		} else if (ae.getActionCommand().equals("Cancel")) {
			MakeGui.frmAdminPanel.removeAll();
			MakeGui.frmAdminPanel.updateUI();
		}
	}
}