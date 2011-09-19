package edu.ncsu.csc216.fire;

public class Grid {
	private Cell[][] cells;
	private int sideLength;
	private double probCatch;
	
	public Grid(int sideLength, double probCatch) {
		this.sideLength = sideLength;
		this.probCatch = probCatch;
		cells = new Cell[sideLength][sideLength];
		for(int i = 0; i < sideLength; i++) {
			for(int j = 0; j < sideLength; j++) {
				if(i == 0 || i == 10 || j == 0 || j == 10) {
					cells[i][j] = new Cell(Cell.EMPTY);
				}else if(i == 5 && j == 5) {
					cells[i][j] = new Cell(Cell.BURNING);
				}else {
					cells[i][j] = new Cell(Cell.TREE);
				}
			}
		}
	}
	
	public Cell[][] getGrid() {
		return cells;
	}
	
	public boolean done() {
		return true;
	}
	
	public void nextTimestep() {
		
	}
}
