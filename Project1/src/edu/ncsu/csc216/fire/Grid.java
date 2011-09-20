package edu.ncsu.csc216.fire;

/**
 * A grid holding all the Cell objects
 * @author Andrew Kofink and James Bruening
 */
public class Grid {
	
	/**
	 * 2D array of cells creating the grid
	 */
	private Cell[][] cells;
	
	/**
	 * Holds the side length of the grid
	 */
	private  int sideLength;
	
	/**
	 * Holds the probability of the burning trees
	 */
	private double probCatch;
	
	/**
	 * The cell in the center of the grid
	 */
	private static final int CENTER = 5;
	
	/**
	 * Builds the grid of trees and empty spaces with burning
	 * center tree
	 * @param sideLength
	 * @param probCatch
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
	 * Gets the grid of cells
	 * @return
	 */
	public Cell[][] getGrid() {
		return cells;
	}
	
	/**
	 * Tests if burning is done
	 * @return
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
	 * Increments to the time step
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
