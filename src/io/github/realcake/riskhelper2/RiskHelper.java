/**
 * Author: Sean McKenna (https://github.com/realcake)
 *
 * Feel free to do whatever with this, I won't be responsible for it
 */

package io.github.realcake.riskhelper2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class RiskHelper {

	private final String OUT_OF_UNITS_MESSAGE = "Too few units to roll";

	// Made with the package visibility level
	// Since it only needs to be seen by Main outside of this class
	JFrame frame;
	private JTextField attackField;
	private JTextField defendField;
	private JTextField attackRollField;
	private JTextField defendRollField;

	// set default minimum to attack/defend
	public int attackUnits;
	public int defendUnits;
	
	public int[] attackRolls;
	public int[] defendRolls;

	/**
	 * Initialize the contents of the frame.
	 */
	public RiskHelper() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 215);
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
		
		//lables for dice
		
		JLabel attackDice1 = new JLabel("A1");
		GridBagConstraints gbc_attackDice1 = new GridBagConstraints();
		gbc_attackDice1.insets = new Insets(0, 0, 5, 5);
		gbc_attackDice1.gridx = 1;
		gbc_attackDice1.gridy = 4;
		frame.getContentPane().add(attackDice1, gbc_attackDice1);
		
		
		JLabel attackDice2 = new JLabel("A2");
		GridBagConstraints gbc_attackDice2 = new GridBagConstraints();
		gbc_attackDice2.insets = new Insets(0, 0, 5, 5);
		gbc_attackDice2.gridx = 1;
		gbc_attackDice2.gridy = 5;
		frame.getContentPane().add(attackDice2, gbc_attackDice2);
		
		JLabel attackDice3 = new JLabel("A3");
		GridBagConstraints gbc_attackDice3 = new GridBagConstraints();
		gbc_attackDice3.insets = new Insets(0, 0, 5, 5);
		gbc_attackDice3.gridx = 1;
		gbc_attackDice3.gridy = 6;
		frame.getContentPane().add(attackDice3, gbc_attackDice3);
		
		JLabel defendDice1 = new JLabel("D1");
		GridBagConstraints gbc_defendDice1 = new GridBagConstraints();
		gbc_defendDice1.insets = new Insets(0, 0, 5, 5);
		gbc_defendDice1.gridx = 2;
		gbc_defendDice1.gridy = 4;
		frame.getContentPane().add(defendDice1, gbc_defendDice1);
		
		JLabel defendDice2 = new JLabel("D2");
		GridBagConstraints gbc_defendDice2 = new GridBagConstraints();
		gbc_defendDice2.insets = new Insets(0, 0, 5, 5);
		gbc_defendDice2.gridx = 2;
		gbc_defendDice2.gridy = 5;
		frame.getContentPane().add(defendDice2, gbc_defendDice2);

		
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
		// Make rollButton the enter key action
		frame.getRootPane().setDefaultButton(rollButton);

		JButton autoRollButton = new JButton("Auto-Roll");
		autoRollButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getUnitNumbers();
				autoRoll();
			}
		});
		GridBagConstraints gbc_autoRollButton = new GridBagConstraints();
		gbc_autoRollButton.insets = new Insets(0, 0, 5, 5);
		gbc_autoRollButton.gridx = 2;
		gbc_autoRollButton.gridy = 2;
		frame.getContentPane().add(autoRollButton, gbc_autoRollButton);
		// Make rollButton the enter key action

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
	}

	/**
	 * Code for rolling
	 */

	public void autoRoll() {
		getUnitNumbers();
		rollOnce();
		while (attackUnits > 1 && defendUnits > 0) {
			rollOnce();
		}
	}

	public void rollOnce() {
		// Update the unit numbers
		getUnitNumbers();
		if (attackUnits < 1 || defendUnits < 1) {
			// if the user tries to get tricky
			attackField.setText(OUT_OF_UNITS_MESSAGE);
			defendField.setText(OUT_OF_UNITS_MESSAGE);

		} else {
			Roll rollResult = new Roll(attackUnits, defendUnits);

			attackRollField.setText(rollResult.prettyAttackRolls());
			defendRollField.setText(rollResult.prettyDefendRolls());

			attackField.setText("" + attackUnits);
			defendField.setText("" + defendUnits);

			attackUnits -= rollResult.getAttackUnitLosses();
			defendUnits -= rollResult.getDefendUnitLosses();
			
			attackRolls = rollResult.attackRolls;
			defendRolls = rollResult.defendRolls;
			updateTextFields();
			setLabelIcons();
		}
	}

	public void setLabelIcons(){
		int numAttackDice = attackRolls.length;
		int numDefendDice = defendRolls.length;
		
		//attackDice1.setIcon(new ImageIcon("res/black_1.png"));
		
	}
	
	/**
	 * Fetch unit numbers and update attackUnits and defendUnits
	 */
	public void getUnitNumbers() {
		try {
			attackUnits = Integer.parseInt(attackField.getText());
		} catch (NumberFormatException e) {
			attackField.setText("");
			attackUnits = 0;
		}

		try {
			defendUnits = Integer.parseInt(defendField.getText());
		} catch (NumberFormatException e) {
			defendField.setText("");
			defendUnits = 0;
		}
	}

	/**
	 * Set the contents of the text fields to the unit variables
	 */
	public void updateTextFields() {
		attackField.setText("" + attackUnits);
		defendField.setText("" + defendUnits);
	}
}
