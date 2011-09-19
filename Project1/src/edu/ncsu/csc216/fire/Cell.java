package edu.ncsu.csc216.fire;

import java.util.Random;

/**
 * 
 * @author Andrew Kofink and James Bruening
 *
 */
public class Cell extends Grid {
	
	/**
	 * 
	 */
	public static final int EMPTY = 0;
	
	/**
	 * 
	 */
	public static final int TREE = 1;
	
	/**
	 * 
	 */
	public static final int BURNING = 2;
	
	/**
	 * 
	 */
	public static final double PROBCATCH = .55;
	
	/**
	 * 
	 */
	private int state;
	
	/**
	 * 
	 *@param state
	 */
	public Cell(int state) {
		super(state, PROBCATCH);
		this.state = state;
	}
	
	/**
	 * 
	 *@return state
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * 
	 *@param state
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * 
	 *@return newCell
	 */
	public Cell copy() {
		Cell newCell = new Cell(this.getState());
		newCell = this;
		
		return newCell;
	}
	
	/**
	 * 
	 *@param probCatch
	 *@param north
	 *@param east
	 *@param south
	 *@param west
	 */
	public void spread(double probCatch, Cell north, Cell east, Cell south, Cell west) {
		Cell[] cells = {north, east, south, west};
		for (Cell cell : cells) {
			if (cell.getState() == 1) {
				Random rand = new Random();
				double currentRand = rand.nextDouble();
				if (currentRand <= PROBCATCH) {
					cell.setState(Cell.BURNING);
				}
			}
		}
	}	
}
