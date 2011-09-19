package edu.ncsu.csc216.fire;

import junit.framework.TestCase;

public class CellTest extends TestCase {
	
	/**
	 * 
	 */
	public static Cell c;

	/**
	 * 
	 */
	private static final int TESTSTATE = Cell.EMPTY;

	/**
	 * 
	 */
	private static final int NEWSTATE = Cell.TREE;

	/**
	 * 
	 */
	private static final int TIMESTOTEST = 1000;

	/**
	 * 
	 */
	private static final double PROBTEST = .55;

	/**
	 * 
	 */
	private static final double MARGIN = .2;

	/**
	 * 
	 */
	private static final int NUMOFADJACENTTREES = 4;
	
	/**
	 * 
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		c = new Cell(TESTSTATE);
	}
	
	/**
	 * 
	 */
	public void testGetState() {
		assertEquals (c.getState(), TESTSTATE);
	}
	
	/**
	 * 
	 */
	public void testSetState() {
		c.setState(NEWSTATE);
		
		assertEquals (c.getState(), NEWSTATE);
	}
	
	/**
	 * 
	 */
	public void testCopy() {
		assertEquals (c, c.copy());
	}
	
	/**
	 * 
	 */
	public void testSpread() {
		int counter = 0;
		for (int i = 0; i < TIMESTOTEST; i++) {
			Cell[] cells = new Cell[NUMOFADJACENTTREES];
			for (int j = 0; j < cells.length; j++) {
				cells[j] = new Cell(NEWSTATE);
			}
			c.spread(PROBTEST, cells[0], cells[1], cells[2], cells[NUMOFADJACENTTREES-1]);
			for (int j = 0; j < cells.length; j++) {
				if (cells[j].getState() == Cell.BURNING) {
					counter++;
				}
			}
		}
		assert (counter > PROBTEST - MARGIN && counter < PROBTEST + MARGIN);
	}

}
