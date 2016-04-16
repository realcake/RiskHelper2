package io.github.realcake.riskhelper;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	static RiskHelper riskHelperUI;

	public static void main(String[] args) {

		try {
			// Look as native as possible
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// Not needed on newer Java releases
			JFrame.setDefaultLookAndFeelDecorated(true);
			// For the Mac OS X menu bar
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("Failed to properly configure the UI");
			e.printStackTrace();
		}

		// Create the GUI once the program has loaded
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					riskHelperUI = new RiskHelper();
					riskHelperUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
