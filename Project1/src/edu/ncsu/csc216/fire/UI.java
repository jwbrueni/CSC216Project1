package edu.ncsu.csc216.fire;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class handles all the user interaction.
 * @author Andrew Kofink and James Bruening
 */
public class UI {
	
	private static final double INCREMENT = .05;
	
	/**
	 * The main control method of the program
	 * @param args
	 */
	public static void main(String[] args) {
		userInterface();
	}
	
	/**
	 * Controls the interaction with the user
	 */
	public static void userInterface() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the side length of the forest: ");
		int sideLength = -1;
		try {
			sideLength = in.nextInt();
			if (sideLength <= 0) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("\nThe side of a grid must be a positive integer.\n");
			System.exit(0);
		}
		
		Driver d = new Driver(sideLength);
		showTable(d.simulate());
	}
	
	/**
	 * 
	 * @param data
	 */
	public static void showTable(RunGroup[] data) {
		String tableHead = "" +
				"                     +=================+\n" +
				"                     + Simulation Data +\n" +
				"                     +=================+\n\n" +
				"       +--------------------------------------------Probability\n" +
				"       |          +---------------------------------Average Percentage of the Forest Burned\n" +
				"       |          |          +----------------------First Quartile\n" +
				"       |          |          |          +-----------Third Quartile\n" +
				"       |          |          |          |        +--Average Number of Timesteps\n" +
				"       |          |          |          |        |\n" +
				"+------------------------------------------------------+\n";
		
		System.out.print(tableHead);
		
		double j = 0;
		for(int i = 0; i < data.length; i++, j += INCREMENT) {
			System.out.printf("|%9.2f%%|%8.3f%%|%10.2f|%10.2f|%11.1f|\n", j * 100, data[i].getAvgPctBurned(), data[i].getFirstQuartile(), data[i].getThirdQuartile(), data[i].getAvgTimesteps()); 
		}
		
		String tableFoot = "+------------------------------------------------------+";
		System.out.println(tableFoot);
	}
}
