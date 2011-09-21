package edu.ncsu.csc216.fire;

import junit.framework.TestCase;

/**
 * A test for the Cell test
 * @author Andrew Kofink and James Bruening
 */
public class CellTest extends TestCase {
	
	/**
	 * A test Cell
	 */
	public static Cell c;
	
	/**
	 * A test Grid
	 */
	public static Grid g;

	/**
	 * Initial state
	 */
	private static final int TESTSTATE = Cell.EMPTY;

	/**
	 * New state
	 */
	private static final int NEWSTATE = Cell.TREE;

	/**
	 * Number of times to test probability
	 */
	private static final int TIMESTOTEST = 1000;

	/**
	 * Probability of adjacent trees burning
	 */
	private static final double PROBTEST = .55;
	
	/**
	 * Side length of the forest
	 */
	private static final int SIDELENGTH = 9;
	
	/**
	 * Side length of the grid
	 */
	private static final int ACTUALSIDELENGTH = 11;

	/**
	 * Margin of error for test
	 */
	private static final double MARGIN = .1;

	/**
	 * Number of adjacent trees (N, S, E, W)
	 */
	private static final int NUMOFADJACENTTREES = 4;
	
	/**
	 * Builds a cell, c and a grid, g
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		c = new Cell(TESTSTATE);
		g = new Grid(SIDELENGTH ,PROBTEST);
	}
	
	/**
	 * Tests whether the proper state is returned
	 */
	public void testGetState() {
		assertEquals (c.getState(), TESTSTATE);
	}
	
	/**
	 * Tests whether the state is successfully set
	 */
	public void testSetState() {
		c.setState(NEWSTATE);
		
		assertEquals (c.getState(), NEWSTATE);
	}
	
	/**
	 * Tests the copy method of a cell
	 */
	public void testCopy() {
		assertEquals (c, c.copy());
	}
	
	/**
	 * Tests the spreading of fire
	 */
	public void testSpread() {
		int counter = 0;
		for (int i = 0; i < TIMESTOTEST; i++) {
			Cell[] cells = new Cell[NUMOFADJACENTTREES];
			for (int j = 0; j < cells.length; j++) {
				cells[j] = new Cell(NEWSTATE);
			}
			c.spread(PROBTEST, cells[0], cells[1], cells[2], cells[NUMOFADJACENTTREES - 1]);
			for (int j = 0; j < cells.length; j++) {
				if (cells[j].getState() == Cell.BURNING) {
					counter++;
				}
			}
		}
		double percentage = (double) counter / (TIMESTOTEST * NUMOFADJACENTTREES);
		assertTrue (percentage > PROBTEST - MARGIN && percentage < PROBTEST + MARGIN);
		
		for (int i = 1; i < ACTUALSIDELENGTH - 1; i++) {
			for (int j = 1; j < ACTUALSIDELENGTH - 1; j++) {
				g.getGrid()[i][j].setState(Cell.BURNING);
				
				Cell[] cell = new Cell[NUMOFADJACENTTREES + 1];
				
				cell[0] = g.getGrid()[i][j];
				cell[1] = g.getGrid()[i + 1][j];
				cell[2] = g.getGrid()[i - 1][j];
				cell[3] = g.getGrid()[i][j + 1];
				cell[4] = g.getGrid()[i][j - 1];
				
				int[] oldState = new int[NUMOFADJACENTTREES + 1];
				int[] newState = new int[NUMOFADJACENTTREES + 1];
				
				for (int k = 0; k < NUMOFADJACENTTREES + 1; k++) {
					oldState[k] = cell[k].getState();
				}
				
				cell[0].spread(PROBTEST, cell[0], cell[1], cell[2], cell[3]);
					
				for (int k = 0; k < NUMOFADJACENTTREES + 1; k++) {
					newState[k] = cell[k].getState();
					
					System.out.println("k value: " + k + "\nOld: " + oldState[k] + "\nNew: " + newState[k]);
					if (oldState[k] == Cell.TREE) {
						assertTrue(newState[k] == Cell.BURNING || newState[k] == Cell.TREE);
					} else {
						assertEquals(newState[k], Cell.EMPTY);
					}
				}
			}
		}
	}
}
