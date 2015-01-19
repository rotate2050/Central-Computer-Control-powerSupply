package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class LabStatusView extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	public LabStatusView() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(400, 300));
		setTitle("Today's Timetable");
		setLocation(100, 100);
		setResizable(false);
		setLayout(new BorderLayout());

		GenerateTable gt = new GenerateTable();
		JPanel tablepane = new JPanel(new BorderLayout());
		tablepane.add(gt.populateTable());
		getContentPane().add(tablepane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		panel.add(btnOk);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		this.dispose();
	}

}
