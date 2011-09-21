package edu.ncsu.csc216.fire;

/**
 * Maintains the Grid and Display objects
 * @author Andrew Kofink and James Bruening
 */
public class Driver {

	/**
	 * The probability at which a tree will burn
	 * when next to a burning tree
	 */
	private static final double PROBCATCH = .55;
	
	/**
	 * The side length of the forest
	 */
	private static final int SIDELENGTH = 9;
	
	/**
	 * The main executing class of the program
	 * @param args Other arguments passed to main
	 */
	public static void main(String[] args) {
		Grid g = new Grid(SIDELENGTH, PROBCATCH);
		Display d = new Display(g);
		while(!g.done()) {
			g.nextTimestep();
			d.show();
		}
	}
}
