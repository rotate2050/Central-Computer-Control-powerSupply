package CCCPS;

import javax.swing.JOptionPane;

public class HandleException {
	public HandleException(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Exception Fired",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
