package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;
	private int maxRows;
	private int maxCols;

	//Defines a board with BoardCell.EMPTY cells. Initializes passed in variables.
	public Game(int maxRows, int maxCols) {
		this.maxCols = maxCols;
		this.maxRows = maxRows;
		board = new BoardCell[maxRows][maxCols];
		setBoardWithColor(BoardCell.EMPTY);
	}

	//gets the max rows
	public int getMaxRows() {
		return maxRows;
	}

	//gets the max columns
	public int getMaxCols() {
		return maxCols;
	}

	//sets the boardcell with the rows and columns
	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		board[rowIndex][colIndex] = boardCell;
	}

	//gets the boardcell 
	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return board[rowIndex][colIndex];
	}

	//Initializes row with the specified color.
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for (int r = 0; r < board[rowIndex].length; r++) {
			board[rowIndex][r] = cell;
		}
	}
	
	//Initializes column with the specified color.
	public void setColWithColor(int colIndex, BoardCell cell) {
		for (int r = 0; r < getMaxRows(); r++) {
			board[r][colIndex] = cell;
		}
	}
	
	//Initializes the board with the specified color.
	public void setBoardWithColor(BoardCell cell) {
		for (int r = 0; r < maxRows; r++) {
			for (int c = 0; c < maxCols; c++) {
				board[r][c] = cell;
			}
		}
	}	
	
	//abstract method that checks if the game is over
	public abstract boolean isGameOver();

	//abstract method that gets the score
	public abstract int getScore();

	//abstract method that advances the animation one step
	public abstract void nextAnimationStep();

	
	//abstract method that adjust the board state according to the 
	//current board state and the selected cell.
	public abstract void processCell(int rowIndex, int colIndex);
}

