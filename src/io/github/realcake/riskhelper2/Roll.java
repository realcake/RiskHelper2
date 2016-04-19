package io.github.realcake.riskhelper2;

import java.util.Arrays;
import java.util.Random;

public class Roll {

	public static Random rand = new Random();

	private int[] attackRolls;
	private int[] defendRolls;

	// TODO: Leaving these in as they may be useful in the future
	// private int attackingUnits;
	// private int defendingUnits;

	private int attackingDice;
	private int defendingDice;

	public Roll(int attackingUnits, int defendingUnits) {
		// this.attackingUnits = attackingUnits;
		// this.defendingUnits = defendingUnits;

		// set the default values of dice to roll so eclipse doesn't get mad
		// (in case your forgot how this works in Risk)
		// the defender can have 1 die per unit up to 2 dice, the attacker can
		// have a die for every unit more they have, to a max of 3, for
		// instance, 3 attl units gets you 2 dice, 4 units for 3, 2 for 1
		attackingDice = 2;
		defendingDice = 1;

		if (attackingUnits == 1) {
			attackingDice = 1;
		} else if (attackingUnits == 2) {
			attackingDice = 2;
		} else if (attackingUnits >= 3) {
			attackingDice = 3;
		}

		if (defendingUnits == 1) {
			defendingDice = 1;
		} else if (defendingUnits >= 2) {
			defendingDice = 2;
		}

		// set the arrays to the length of how many dice/die it may contain
		attackRolls = new int[attackingDice];
		defendRolls = new int[defendingDice];

		// set it to the pseudo-random values
		for (int i = 0; i < attackingDice; i++) {
			attackRolls[i] = roll();
		}
		for (int i = 0; i < defendingDice; i++) {
			defendRolls[i] = roll();
		}

		// sort and reverse array (why don't they make it descending order by
		// default?)
		Arrays.sort(attackRolls);
		Arrays.sort(defendRolls);

		// reversing the array to decending order
		for (int i = 0; i < attackRolls.length / 2; i++) {
			int temp = attackRolls[i];
			attackRolls[i] = attackRolls[attackRolls.length - i - 1];
			attackRolls[attackRolls.length - i - 1] = temp;
		}

		for (int i = 0; i < defendRolls.length / 2; i++) {
			int temp = defendRolls[i];
			defendRolls[i] = defendRolls[defendRolls.length - i - 1];
			defendRolls[defendRolls.length - i - 1] = temp;
		}
	}

	public String prettyAttackRolls() {
		return Arrays.toString(attackRolls);
	}

	public String prettyDefendRolls() {
		return Arrays.toString(defendRolls);
	}

	// creates a pseudo-random int between 1 and 6, virtual dice roll
	public static int roll() {
		int randomNum = rand.nextInt(6 - 1 + 1) + 1;
		return randomNum;
	}

	public int getDefendUnitLosses() {
		int losses = 0;
		for (int i = 0; i < defendingDice; i++) {
			if (attackRolls[i] > defendRolls[i]) {
				losses++;
			}
		}
		return losses;
	}

	public int getAttackUnitLosses() {
		int losses = 0;
		for (int i = 0; i < defendingDice; i++) {
			if (attackRolls[i] <= defendRolls[i]) {
				losses++;
			}
		}
		return losses;
	}

}
