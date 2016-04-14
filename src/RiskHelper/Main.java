package RiskHelper;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import RiskHelper.RiskHelper;

public class Main {

	protected static RiskHelper rh;

	public static void main(String[] args) {

		// Use the native Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		// Create the GUI once the program has loaded
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					rh = new RiskHelper();
					rh.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
