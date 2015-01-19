import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class ChangePassword extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8546967766772004265L;
	private JPasswordField pwdFldOld;
	private JPasswordField pwdFldNew;
	private JPasswordField pwdFldConfirm;

	public ChangePassword() {
		setLayout(new BorderLayout(0, 0));

		JPanel btnPanel = new JPanel();
		add(btnPanel, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnPanel.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnPanel.add(btnCancel);

		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.1 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblOldPassword = new JLabel("Old Password:");
		GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
		gbc_lblOldPassword.ipady = 1;
		gbc_lblOldPassword.ipadx = 1;
		gbc_lblOldPassword.anchor = GridBagConstraints.WEST;
		gbc_lblOldPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblOldPassword.gridx = 0;
		gbc_lblOldPassword.gridy = 0;
		contentPanel.add(lblOldPassword, gbc_lblOldPassword);

		pwdFldOld = new JPasswordField();
		pwdFldOld.setEchoChar('*');
		GridBagConstraints gbc_pwdFldOld = new GridBagConstraints();
		gbc_pwdFldOld.ipady = 1;
		gbc_pwdFldOld.ipadx = 1;
		gbc_pwdFldOld.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFldOld.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFldOld.gridx = 1;
		gbc_pwdFldOld.gridy = 0;
		contentPanel.add(pwdFldOld, gbc_pwdFldOld);

		JLabel lblNewPassword = new JLabel("New Password:");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.ipady = 1;
		gbc_lblNewPassword.ipadx = 1;
		gbc_lblNewPassword.anchor = GridBagConstraints.WEST;
		gbc_lblNewPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewPassword.gridx = 0;
		gbc_lblNewPassword.gridy = 1;
		contentPanel.add(lblNewPassword, gbc_lblNewPassword);

		pwdFldNew = new JPasswordField();
		pwdFldNew.setEchoChar('*');
		GridBagConstraints gbc_pwdFldNew = new GridBagConstraints();
		gbc_pwdFldNew.ipady = 1;
		gbc_pwdFldNew.ipadx = 1;
		gbc_pwdFldNew.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFldNew.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFldNew.gridx = 1;
		gbc_pwdFldNew.gridy = 1;
		contentPanel.add(pwdFldNew, gbc_pwdFldNew);

		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password:");
		GridBagConstraints gbc_lblConfirmNewPassword = new GridBagConstraints();
		gbc_lblConfirmNewPassword.ipady = 1;
		gbc_lblConfirmNewPassword.ipadx = 1;
		gbc_lblConfirmNewPassword.anchor = GridBagConstraints.WEST;
		gbc_lblConfirmNewPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblConfirmNewPassword.gridx = 0;
		gbc_lblConfirmNewPassword.gridy = 2;
		contentPanel.add(lblConfirmNewPassword, gbc_lblConfirmNewPassword);

		pwdFldConfirm = new JPasswordField();
		pwdFldConfirm.setEchoChar('*');
		GridBagConstraints gbc_pwdFldConfirm = new GridBagConstraints();
		gbc_pwdFldConfirm.insets = new Insets(5, 5, 5, 5);
		gbc_pwdFldConfirm.ipadx = 1;
		gbc_pwdFldConfirm.ipady = 1;
		gbc_pwdFldConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdFldConfirm.gridx = 1;
		gbc_pwdFldConfirm.gridy = 2;
		contentPanel.add(pwdFldConfirm, gbc_pwdFldConfirm);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("OK")) {
		} else if (ae.getActionCommand().equals("Cancel")) {
			MakeGui.frmAdminPanel.removeAll();
			MakeGui.frmAdminPanel.updateUI();
		}
	}
}
