//package tests;
//
//import static org.junit.Assert.*;
//
//import java.util.Random;
//
//import org.junit.Test;
//
//import model.BoardCell;
//import model.ClearCellGame;
//import model.Game;
//
//public class StudentTests {
//
//	/* We use this string to prevent any hardcoding of results. */
//	/* The submit server uses a different value for TESTS_TAG */
//	public static final String TESTS_TAG = "\nClearCellGameTest";
//
//	/* Support methods */
//	private static String getBoardStr(Game game) {
//		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();
//
//		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
//		for (int row = 0; row < maxRows; row++) {
//			for (int col = 0; col < maxCols; col++) {
//				answer += game.getBoardCell(row, col).getName();
//			}
//			answer += "\n";
//		}
//
//		return answer;
//	}
//
//	@Test
//	public void diagonalDownRight() {
//		int maxRows = 8, maxCols = 8, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setBoardCell(1, 4, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setBoardCell(2, 5, BoardCell.YELLOW);
//		ccGame.setBoardCell(3, 6, BoardCell.YELLOW);
//		ccGame.setBoardCell(4, maxCols - 1, BoardCell.RED);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(1, 4);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//
//	@Test
//	public void diagonalDownRight1() {
//		int maxRows = 8, maxCols = 8, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setBoardCell(1, 4, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setBoardCell(2, 5, BoardCell.YELLOW);
//		ccGame.setBoardCell(3, 6, BoardCell.YELLOW);
//		ccGame.setBoardCell(4, maxCols - 1, BoardCell.YELLOW);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(1, 4);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//
//	@Test
//	public void diagonalUpLeft() {
//		int maxRows = 8, maxCols = 8, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setBoardCell(3, 4, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setBoardCell(2, 3, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, 2, BoardCell.YELLOW);
//		ccGame.setBoardCell(4, 5, BoardCell.YELLOW);
//		ccGame.setBoardCell(0, 0, BoardCell.YELLOW);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(4, 5);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//
//	@Test
//	public void diagonalUpLeft1() {
//		int maxRows = 8, maxCols = 8, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setBoardCell(2, 3, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setBoardCell(2, 2, BoardCell.YELLOW);
//		ccGame.setBoardCell(0, 1, BoardCell.YELLOW);
//		ccGame.setBoardCell(3, 3, BoardCell.YELLOW);
//		ccGame.setBoardCell(0, 0, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, 1, BoardCell.YELLOW);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(3, 3);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//
//	@Test
//	public void diagonalUpRight() {
//		int maxRows = 8, maxCols = 8, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setBoardCell(2, 4, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setBoardCell(1, 1, BoardCell.YELLOW);
//		ccGame.setBoardCell(2, 2, BoardCell.YELLOW);
//		ccGame.setBoardCell(3, 3, BoardCell.YELLOW);
//		ccGame.setBoardCell(0, 6, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, 5, BoardCell.YELLOW);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(3, 3);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//
//	@Test
//	public void diagonalDownLeft() {
//		int maxRows = 8, maxCols = 8, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setBoardCell(4, 2, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setBoardCell(5, 1, BoardCell.YELLOW);
//		ccGame.setBoardCell(2, 2, BoardCell.YELLOW);
//		ccGame.setBoardCell(3, 3, BoardCell.YELLOW);
//		ccGame.setBoardCell(6, 0, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, 5, BoardCell.YELLOW);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(3, 3);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//
//	@Test
//	public void animationSteps() {
//		int maxRows = 4, maxCols = 5, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//		ccGame.nextAnimationStep();
//		String answer = getBoardStr(ccGame);
//		ccGame.nextAnimationStep();
//
//		answer = getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubAnimationSteps.txt", answer));
//	}
//
//	@Test
//	public void clearAllToRight() {
//		int maxRows = 2, maxCols = 5, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(0, BoardCell.YELLOW);
//
//		String answer = "Before processCell\n\n";
//		answer += getBoardStr(ccGame);
//		ccGame.processCell(1, 2);
//		answer += "\nAfter processCell\n";
//		answer += getBoardStr(ccGame);
//
//		// test right end
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(0, BoardCell.YELLOW);
//		ccGame.processCell(1, 4);
//		answer += getBoardStr(ccGame);
//
//		// test from 0
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(0, BoardCell.YELLOW);
//		ccGame.processCell(1, 0);
//		answer += getBoardStr(ccGame);
//
//		// test one already empty
//		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(0, BoardCell.YELLOW);
//		ccGame.processCell(1, 0);
//		answer += getBoardStr(ccGame);
//		//test empty one
//		ccGame.processCell(1, 0);
//		answer += getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubHorizontalCells.txt", answer));
//	}
//	
//	@Test
//	public void setColumnWithColor() {
//		int maxRows = 4, maxCols = 5, strategy = 1;
//		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
//		ccGame.setColWithColor(2, BoardCell.BLUE);
//		String answer = getBoardStr(ccGame);
//
//		answer += TESTS_TAG;
//		assertTrue(TestsSupport.isCorrect("pubAnimationSteps.txt", answer));
//	}
//
//
//}
//
//
