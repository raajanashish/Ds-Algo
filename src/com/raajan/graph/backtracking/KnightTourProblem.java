package com.raajan.graph.backtracking;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
 * 
 * Given a N*N board with the Knight placed on the first block of an empty
 * board. Moving according to the rules of chess knight must visit each square
 * exactly once. Print the order of each the cell in which they are visited.
 * 
 * @author raajan
 *
 */
public class KnightTourProblem {
	public static void main(String[] args) {
		int[][] board = new int[8][8];
		KnightTourProblem myObj = new KnightTourProblem();
		myObj.kinghtTour(board);
	}

	public boolean kinghtTour(int[][] board) {
		int[][] moves = { { 1, 2 }, { 1, -2 }, { 2, 1 }, { 2, -1 }, { -1, 2 }, { -1, -2 }, { -2, 1 }, { -2, -2 } };
		int[][] path = new int[8][8];
		for (int i = 0; i < path.length; i++) {
			Arrays.fill(path[i], -1);
		}

		int count = 0;
		if (knightTourUtil(board, moves, path, count, 0, 1)) {
			printPath(path);
			return true;
		} else {
			return false;
		}
	}

	private boolean knightTourUtil(int[][] board, int[][] moves, int[][] path, int count, int x, int y) {

		if (count == board.length * board[0].length) {
			return true;
		}
		for (int m = 0; m < moves.length; m++) {
			int[] move = moves[m];
			int xNext = x + move[0];
			int yNext = y + move[1];
			if (!isInValid(xNext, yNext, path)) {
				path[xNext][yNext] = count;
				if (knightTourUtil(board, moves, path, count + 1, xNext, yNext)) {
					return true;
				} else {
					path[xNext][yNext] = -1;
				}
			}

		}
		return false;
	}

	private boolean isInValid(int x, int y, int[][] path) {
		return (x < 0 || x > path.length - 1 || y < 0 || y > path[0].length - 1) ? true
				: path[x][y] != -1 ? true : false;

	}

	/* A utility function to print solution matrix sol[N][N] */
	void printPath(int[][] path) {
		for (int x = 0; x < path.length; x++) {
			for (int y = 0; y < path[0].length; y++)
				System.out.printf(" %2d ", path[x][y]);
			System.out.printf("\n");
		}
	}

}
