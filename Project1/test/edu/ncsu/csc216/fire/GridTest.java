package edu.ncsu.csc216.fire;

import junit.framework.TestCase;

public class GridTest extends TestCase {

	/**
	 * 
	 */
	public static Grid g;
	
	/**
	 * 
	 */
	public static final int SIDELENGTH = 11;

	/**
	 * 
	 */
	public static final double PROBCATCH = 55;

	/**
	 * 
	 */
	public static final int CENTER = 5;
	
	/**
	 * 
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		g = new Grid(SIDELENGTH, PROBCATCH);
	}
	
	/**
	 * 
	 */
	public void testGetGrid() {
		for (int i = 0; i < SIDELENGTH; i++) {
			assertEquals (g.getGrid()[i][0].getState(), Cell.EMPTY);
			assertEquals (g.getGrid()[i][SIDELENGTH - 1].getState(), Cell.EMPTY);
		}
		
		for (int i = 0; i < SIDELENGTH; i++) {
			assertEquals (g.getGrid()[0][i].getState(), Cell.EMPTY);
			assertEquals (g.getGrid()[SIDELENGTH - 1][i].getState(), Cell.EMPTY);
		}
		
		for (int i = 1; i < SIDELENGTH - 1; i++) {
			for (int j = 1; j < SIDELENGTH - 1; j++) {
				if (i == CENTER && j == CENTER) {
					assertEquals (g.getGrid()[i][j].getState(), Cell.BURNING);
				} else {
					assertEquals (g.getGrid()[i][j].getState(), Cell.TREE);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void testDone() {
		g.getGrid()[CENTER][CENTER].setState(Cell.BURNING);
		assert (!g.done());
		
		for (int i = 0; i < SIDELENGTH; i++) {
			for (int j = 0; j < SIDELENGTH; j++) {
				g.getGrid()[i][j].setState(Cell.TREE);
			}
		}
		assert (g.done());
		
		for (int i = 0; i < SIDELENGTH; i++) {
			for (int j = 0; j < SIDELENGTH; j++) {
				g.getGrid()[i][j].setState(Cell.EMPTY);
			}
		}
		assert (g.done());
		
	}

	/**
	 * 
	 */
	public void testNextTimestep() {
		for (int i = 1; i < SIDELENGTH - 1; i++) {
			for (int j = 1; j < SIDELENGTH - 1; j++) {
				g.getGrid()[i][j].setState(Cell.BURNING);
				g.nextTimestep();
				assertEquals (g.getGrid()[i][j].getState(), Cell.EMPTY);
			}
		}
	}

}