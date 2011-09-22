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
	private static final double MARGIN = .05;

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
		g = new Grid(SIDELENGTH, PROBTEST);
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
		assertFalse (c == c.copy());
		assertEquals(c.getState(), c.copy().getState());
	}
	
	/**
	 * Tests the spreading of fire
	 */
	public void testSpread() {
		testProperProbability();
		//next test that spread works in all cells, even boundary cases
		for (int i = 1; i < ACTUALSIDELENGTH - 1; i++) {
			for (int j = 1; j < ACTUALSIDELENGTH - 1; j++) {
				int oldState = g.getGrid()[i][j].getState();
				g.getGrid()[i][j].spread(PROBTEST, g.getGrid()[i - 1][j], g.getGrid()[i][j + 1], g.getGrid()[i + 1][j], g.getGrid()[i][j - 1]);
				if (oldState == Cell.EMPTY) {
					assertEquals(g.getGrid()[i][j].getState(), oldState);
				} else if (oldState == Cell.TREE) {
					assertTrue(g.getGrid()[i][j].getState() == oldState || g.getGrid()[i][j].getState() == Cell.BURNING);
				} else {
					assertEquals(g.getGrid()[i][j].getState(), Cell.EMPTY);
				}
			}
		}
	}
	
	/**
	 * Tests for the proper probability in spreading
	 */
	public void testProperProbability() {
		//first test for proper probability usage
		int counter = 0;
		for (int i = 0; i < TIMESTOTEST; i++) {
			Cell[] cells = new Cell[NUMOFADJACENTTREES + 1];
			//filling arbitrary cells with tree in center and fire all around
			cells[0] = new Cell(Cell.TREE);
			for (int j = 1; j < cells.length; j++) {
				cells[j] = new Cell(Cell.BURNING);
			}
			//testing arbitrary cells for the correct probability that they burn
			cells[0].spread(PROBTEST, cells[1], cells[2], cells[NUMOFADJACENTTREES - 1], cells[NUMOFADJACENTTREES]);
			if (cells[0].getState() == Cell.BURNING) {
				counter++;
			}
		}
		double percentage = (double) counter / TIMESTOTEST;
		assertTrue (percentage > PROBTEST - MARGIN && percentage < PROBTEST + MARGIN);
	}
}
