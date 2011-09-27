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
	private int sideLength;
	
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
	 * @param sideLength The length of the side of the grid
	 * @param probCatch The probability that the fire will spread
	 */
	public Grid(int sideLength, double probCatch) {
		this.sideLength = sideLength + 2;
		this.probCatch = probCatch;
		cells = new Cell[this.sideLength][this.sideLength];
		for (int i = 0; i < this.sideLength; i++) {
			for (int j = 0; j < this.sideLength; j++) {
				if (i == 0 || i == this.sideLength - 1 || j == 0 || j == this.sideLength - 1) {
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
	 * @return The cells in a 2D array
	 */
	public Cell[][] getGrid() {
		return cells;
	}
	
	/**
	 * Tests if burning is done
	 * @return whether fire is done spreading
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
		Cell[][] tempCells = new Cell[sideLength + 2][sideLength + 2];
		
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				tempCells[i][j] = this.getGrid()[i][j].copy();
			}
		}
		
		for (int i = 1; i < sideLength - 1; i++) {
			for (int j = 1; j < sideLength - 1; j++) {
				this.getGrid()[i][j].spread(probCatch, tempCells[i - 1][j], tempCells[i][j + 1], tempCells[i + 1][j], tempCells[i][j - 1]);
			}
		}
	}
}
