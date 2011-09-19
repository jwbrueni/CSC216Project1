package edu.ncsu.csc216.fire;

public class Grid {
	
	/**
	 * 
	 */
	private Cell[][] cells;
	
	/**
	 * 
	 */
	private  int sideLength;
	
	/**
	 * 
	 */
	private double probCatch;
	
	/**
	 * 
	 */
	private static final int CENTER = 5;
	
	/**
	 * 
	 *@param sideLength
	 *@param probCatch
	 */
	public Grid(int sideLength, double probCatch) {
		this.sideLength = sideLength;
		this.probCatch = probCatch;
		cells = new Cell[sideLength][sideLength];
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if (i == 0 || i == sideLength-1 || j == 0 || j == sideLength-1) {
					cells[i][j] = new Cell(Cell.EMPTY);
				} else if (i == CENTER && j == CENTER) {
					cells[i][j] = new Cell(Cell.BURNING);
				} else {
					cells[i][j] = new Cell(Cell.TREE);
				}
			}
		}
	}
	
	/**
	 * 
	 *@return
	 */
	public Cell[][] getGrid() {
		return cells;
	}
	
	/**
	 * 
	 *@return
	 */
	public boolean done() {
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if (cells[i][j].getState() == Cell.BURNING) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 */
	public void nextTimestep() {
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if (cells[i][j].getState() == Cell.BURNING) {
					cells[i][j].spread(probCatch, cells[i][j - 1], cells[i - 1][j], cells[i][j + 1], cells[i + 1][j]);
					cells[i][j].setState(Cell.EMPTY);
				}
			}
		}
	}
}
