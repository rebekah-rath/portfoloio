package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {
	Random random;
	int strategy;
	int score;

	//Defines a board with empty cells. It relies on the super class constructor 
	//to define the board. The random parameter is used for the generation of 
	//random cells. The strategy parameter defines which clearing cell strategy to use 
	//(for this project it will be 1). 
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	//The game is over when the last board row (row with index board.length -1) 
	//is different from empty row.
	public boolean isGameOver() {
		int row = getMaxRows() - 1;
		for (int c = 0; c < getMaxCols(); c++) {
			if (board[row][c] != BoardCell.EMPTY) {
				return true;
			}
		}
		return false;
	}

	//gets the score
	public int getScore() {
		return score;
	}


	 //Advances the animation one step.
	public void nextAnimationStep() {
		if (!isGameOver()) {
			shiftRowsDown();
			for (int c = 0; c < getMaxCols(); c++) {
				board[0][c] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
		}
	}

	//shifts the rows down for a new row
	private void shiftRowsDown() {
		for (int r = getMaxRows() - 1; r > 0 ; r--) {
			for (int c = 0; c < getMaxCols(); c++) {
				board[r][c] = board[r - 1][c];
			}
		}
	}

	//This method will turn to BoardCell.EMPTY the cell selected and any adjacent 
//	surrounding cells in the vertical, horizontal, and diagonal directions that 
//	have the same color. The clearing of adjacent cells will continue as long as 
//	cells have a color that corresponds to the selected cell. Notice that the clearing
//	process does not clear every single cell that surrounds a cell selected (only those
//	found in the vertical, horizontal or diagonal directions). IMPORTANT: 
//	Clearing a cell adds one point to the game's score.
	public void processCell(int rowIndex, int colIndex) {
		checkRight(rowIndex, colIndex);
		checkLeft(rowIndex, colIndex);
		checkUp(rowIndex, colIndex);
		checkDown(rowIndex, colIndex);
		checkDiagonalDownAndRight(rowIndex, colIndex);
		checkDiagonalUpAndRight(rowIndex, colIndex);
		checkDiagonalUpAndLeft(rowIndex, colIndex);
		checkDiagonalDownAndLeft(rowIndex, colIndex);
		board[rowIndex][colIndex] = BoardCell.EMPTY;
		score++;
		//System.out.println("Score: " + score);
		collapseRows();
	}

	//If a whole row is cleared, collapse it up
	private void collapseRows() {
		for (int r = 0; r < getMaxRows() - 1; r++) {
			if (isRowEmpty(r)) {
				shiftRowsUp(r);
			}
		}
	}

	//shifts the rows up if a row is cleared
	private void shiftRowsUp(int rowIndex) {
		for (int r = rowIndex; r < getMaxRows() - 1; r++) {
			for (int c = 0; c < getMaxCols(); c++) {
				board[r][c] = board[r + 1][c];
			}
		}
		setRowWithColor(getMaxRows() - 1, BoardCell.EMPTY);
	}

	//checks to see if the row is empty
	private boolean isRowEmpty(int rowIndex) {
		for (int c = 0; c < getMaxCols(); c++) {
			if (board[rowIndex][c] != BoardCell.EMPTY) {
				return false;
			}
		}
		return true;
	}

	//checks anything to the right that may match the value that is going to be cleared.
	private void checkRight(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		for (int c = colIndex + 1; c < getMaxCols(); c++) {
			if (board[rowIndex][c] == answer) {
				board[rowIndex][c] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything to the left that may match the value that is going to be cleared.
	private void checkLeft(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		for (int c = colIndex - 1; c >= 0; c--) {
			if (board[rowIndex][c] == answer) {
				board[rowIndex][c] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything under that may match the value that is going to be cleared.
	private void checkDown(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		for (int r = rowIndex + 1; r < getMaxRows(); r++) {
			if (board[r][colIndex] == answer) {
				board[r][colIndex] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything above that may match the value that is going to be cleared.
	private void checkUp(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		for (int r = rowIndex - 1; r >= 0; r--) {
			if (board[r][colIndex] == answer) {
				board[r][colIndex] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything under and to the right that may match the value that 
	//is going to be cleared.
	private void checkDiagonalDownAndRight(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		int r = rowIndex + 1;
		for (int c = colIndex + 1; r < getMaxRows() && c < board[r].length; c++) {
			if (board[r][c] == answer) {
				board[r++][c] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything under and to the left that may match the value that 
	//is going to be cleared.
	private void checkDiagonalDownAndLeft(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		int r = rowIndex + 1;
		for (int c = colIndex - 1; c >= 0 && r < getMaxRows(); c--) {
			if (board[r][c] == answer) {
				board[r++][c] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything above and to the right that may match the value that 
	//is going to be cleared.
	private void checkDiagonalUpAndRight(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		int r = rowIndex - 1;
		for (int c = colIndex + 1; c < getMaxCols() && r >= 0; c++) {
			if (board[r][c] == answer) {
				board[r--][c] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

	//checks anything above and to the left that may match the value that 
	//is going to be cleared.
	private void checkDiagonalUpAndLeft(int rowIndex, int colIndex) {
		BoardCell answer = board[rowIndex][colIndex];
		int r = rowIndex - 1;
		for (int c = colIndex - 1; c >= 0 & r >= 0; c--) {
			if (board[r][c] == answer) {
				board[r--][c] = BoardCell.EMPTY;
				score++;
			} else {
				return;
			}
		}
	}

}



