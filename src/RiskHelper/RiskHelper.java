/**
 * Author: Sean McKenna (https://github.com/realcake)
 *
 * Feel free to do whatever with this, I won't be responsible for it
 */

package RiskHelper;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class RiskHelper {

	public JFrame frame;
	private static JTextField attackField;
	private static JTextField defendField;
	private static JTextField attackRollField;
	private static JTextField defendRollField;

	// set default minimum to attack/defend
	public static int attkUnits;
	public static int dfndUnits;

	/**
	 * Initialize the contents of the frame.
	 */
	public RiskHelper() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 163);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		// TODO: Reuse the same GridBagConstraints
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 0;
		frame.getContentPane().add(separator, gbc_separator);

		JLabel attackLabel = new JLabel("Attacking Units");
		GridBagConstraints gbc_AttkLbl = new GridBagConstraints();
		gbc_AttkLbl.insets = new Insets(0, 0, 5, 5);
		gbc_AttkLbl.gridx = 1;
		gbc_AttkLbl.gridy = 0;
		frame.getContentPane().add(attackLabel, gbc_AttkLbl);

		JLabel defendLabel = new JLabel("Defending Units");
		GridBagConstraints gbc_DfndLbl = new GridBagConstraints();
		gbc_DfndLbl.insets = new Insets(0, 0, 5, 0);
		gbc_DfndLbl.gridx = 2;
		gbc_DfndLbl.gridy = 0;
		frame.getContentPane().add(defendLabel, gbc_DfndLbl);

		attackField = new JTextField();
		GridBagConstraints gbc_AttkTextField = new GridBagConstraints();
		gbc_AttkTextField.insets = new Insets(0, 0, 5, 5);
		gbc_AttkTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_AttkTextField.gridx = 1;
		gbc_AttkTextField.gridy = 1;
		frame.getContentPane().add(attackField, gbc_AttkTextField);
		attackField.setColumns(10);

		defendField = new JTextField();
		GridBagConstraints gbc_DfndTextField = new GridBagConstraints();
		gbc_DfndTextField.insets = new Insets(0, 0, 5, 0);
		gbc_DfndTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_DfndTextField.gridx = 2;
		gbc_DfndTextField.gridy = 1;
		frame.getContentPane().add(defendField, gbc_DfndTextField);
		defendField.setColumns(10);

		JButton rollButton = new JButton("Roll Once");
		rollButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rollOnce();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		frame.getContentPane().add(rollButton, gbc_btnNewButton);

		attackRollField = new JTextField();
		attackRollField.setEditable(false);
		GridBagConstraints gbc_AttkRollText = new GridBagConstraints();
		gbc_AttkRollText.insets = new Insets(0, 0, 0, 5);
		gbc_AttkRollText.fill = GridBagConstraints.HORIZONTAL;
		gbc_AttkRollText.gridx = 1;
		gbc_AttkRollText.gridy = 3;
		frame.getContentPane().add(attackRollField, gbc_AttkRollText);
		attackRollField.setColumns(10);

		defendRollField = new JTextField();
		defendRollField.setEditable(false);
		GridBagConstraints gbc_DfndRollText = new GridBagConstraints();
		gbc_DfndRollText.fill = GridBagConstraints.HORIZONTAL;
		gbc_DfndRollText.gridx = 2;
		gbc_DfndRollText.gridy = 3;
		frame.getContentPane().add(defendRollField, gbc_DfndRollText);
		defendRollField.setColumns(10);

		// TODO: Leave an explanation for commented out code or remove it
		// dfndUnits = Integer.parseInt(defendField.getText());
		// attkUnits = Integer.parseInt(attackField.getText());

		// defendField.setText("" + dfndUnits);
		// attackField.setText("" + attkUnits);

	}

	/** Code for rolling */
	public static void rollOnce() {
		// if the user tries to get tricky
		if (attkUnits < 1 || dfndUnits < 1) {
			attackField.setText("ERROR: not enough units!");
			defendField.setText("ERROR: not enough units!");
		}
		// extract the int values from the text fields
		attkUnits = Integer.parseInt(attackField.getText());

		dfndUnits = Integer.parseInt(defendField.getText());

		// the array that stores the dice roll
		int[] AttkRolls;
		int[] DfndRolls;

		// set the default values of dice to roll so eclipse doesn't get mad
		// (in case your forgot how this works in Risk)
		// the defender can have 1 die per unit up to 2 dice, the attacker can
		// have a die for every unit more they have, to a max of 3, for
		// instance, 3 attl units gets you 2 dice, 4 units for 3, 2 for 1
		int toAttk = 2;
		int toDfnd = 1;

		if (attkUnits == 1) {
			toAttk = 1;
		} else if (attkUnits == 2) {
			toAttk = 2;
		} else if (attkUnits >= 3) {
			toAttk = 3;
		}

		if (dfndUnits == 1) {
			toDfnd = 1;
		} else if (dfndUnits >= 2) {
			toDfnd = 2;
		}

		// set the arrays to the length of how many dice/die it may contain
		AttkRolls = new int[toAttk];
		DfndRolls = new int[toDfnd];

		// set it to the pseudo-random values
		for (int i = 0; i < toAttk; i++) {
			AttkRolls[i] = roll();
		}
		for (int i = 0; i < toDfnd; i++) {
			DfndRolls[i] = roll();
		}

		// sort and reverse array (why don't they make it descending order by
		// default?)
		Arrays.sort(AttkRolls);
		Arrays.sort(DfndRolls);

		// reversing the array to decending order
		for (int i = 0; i < AttkRolls.length / 2; i++) {
			int temp = AttkRolls[i];
			AttkRolls[i] = AttkRolls[AttkRolls.length - i - 1];
			AttkRolls[AttkRolls.length - i - 1] = temp;
		}

		for (int i = 0; i < DfndRolls.length / 2; i++) {
			int temp = DfndRolls[i];
			DfndRolls[i] = DfndRolls[DfndRolls.length - i - 1];
			DfndRolls[DfndRolls.length - i - 1] = temp;
		}

		// compare the rolls

		for (int i = 0; i <= toDfnd - 1; i++) {
			if (AttkRolls[i] > DfndRolls[i]) {
				// attkUnits -= 1;
				dfndUnits -= 1;
			} else {
				// dfndUnits -= 1;
				attkUnits -= 1;
			}
		}

		// set the text areas to the new values

		attackRollField.setText(Arrays.toString(AttkRolls));
		defendRollField.setText(Arrays.toString(DfndRolls));

		attackField.setText("" + attkUnits);
		defendField.setText("" + dfndUnits);

	}

	// creates a pseudo-random int between 1 and 6, virtual dice roll
	public static int roll() {
		Random rand = new Random();
		int randomNum = rand.nextInt(6 - 1 + 1) + 1;
		return randomNum;
	}

}
