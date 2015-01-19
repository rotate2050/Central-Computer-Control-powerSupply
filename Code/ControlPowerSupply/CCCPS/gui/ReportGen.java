package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import CCCPS.Dbcon;
import javax.swing.SwingConstants;

public class ReportGen extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox comboBox;

	public ReportGen() {
		setSize(new Dimension(301, 209));
		setTitle("Low Attendance List Generation");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblSemster = new JLabel("Semster:");
		lblSemster.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblSemster = new GridBagConstraints();
		gbc_lblSemster.anchor = GridBagConstraints.WEST;
		gbc_lblSemster.insets = new Insets(0, 0, 5, 5);
		gbc_lblSemster.gridx = 0;
		gbc_lblSemster.gridy = 0;
		panel.add(lblSemster, gbc_lblSemster);

		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel = new JLabel("Percentage(Less than):");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		Dbcon d = new Dbcon();
		d.open();
		ResultSet rs;
		rs = d.selquery("select distinct(sem) from sem_batch order by sem");

		try {
			while (rs.next())
				comboBox.addItem(rs.getObject(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JPanel btnPnl = new JPanel();
		getContentPane().add(btnPnl, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnPnl.add(btnOk);

		pack();
	}

	public void actionPerformed(ActionEvent ae) {
		// if(flg==0){
		String txt = textField.getText();
		String sem = comboBox.getSelectedItem().toString();
		Report p = new Report(this, sem, txt, "lslist");
		this.dispose();
		p.pdfgenerator();
		// }else if(flg==1){
		// code for report generation of class vise
		// }
	}
}