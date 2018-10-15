package puzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


/**
 * @author Dillon Embreus and Tim Lawrence
 * Board class is used for making instances of Boards to use in the puzzle.
 * Boards are assumed to be 2x2, 3x3, 4x4, or 5x5 in majority cases.  Larger boards are
 * possible with slow solver results (not recommended).
 *
 */
public class Board {

	private int n;
	private int[][] b;

	private int pos;
	
	/**
	 * construct a board from an N-by-N array of blocks
	 * (where blocks[i][j] = block in row i, column j)
	 * @param blocks int[][] (anticipated size of 3 or 4, 2 for very simple puzzles)
	 */
	public Board(int[][] blocks) {
		n = blocks[0].length;
		this.b = new int [n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				this.b[i][j] = blocks[i][j];
			}
		}
	}
	// board size N
	public int size() {
		return n;
	} 
	/**
	 * @return number of blocks out of place in the puzzle
	 */
	public int hamming() {
		int distance = 0;
		for(int i = 0; i < size(); i++) {
			for(int j = 0; j < size(); j++) {
				pos = i * size() + j + 1;
				if(b[i][j] == 0) {
				}else
				if(b[i][j] != pos) {
					distance++;
				}
			}
		}
		return distance;
	}
	/**
	 * @return sum of Manhattan distances between blocks and goal
	 */
	public int manhattan() {
		int i2, j2, index;
		int manhattan = 0;
		int last = b.length * b.length;
		int distance;
		for(int i = 0; i < size(); i++) {
			for(int j = 0; j < size(); j++) {
				pos = i * size() + j + 1;
				if(b[i][j] == 0) {
				}else
				if(b[i][j] != pos) {
					index = b[i][j];
					i2 = (index - 1) / size();
					j2 = (index - 1) % size();
					distance = Math.abs(i-i2) + Math.abs(j-j2);
					manhattan += distance;
				}
			}
		}
		return manhattan;
	}
	/**
	 * @return is this board the goal board? True/False
	 */
	public boolean isGoal() {
		return hamming() == 0;
	}
	/**
	 * Intensive method to determine whether the puzzle can or can not be solved.
	 * Use sparingly.
	 * @return is this board solvable? True/False
	 */
	public boolean isSolvable() {
		int inversion = 0;
		int size = size() * size();
		int[] arr = new int[size];

		//odd size board (3x3)
		if((size() % 2) == 1) {
			for(int i = 0; i < size(); i++) {
				for(int j = 0; j < size(); j++) {
					pos = i * size() + j + 1;//one dimensional position in the puzzle
					arr[pos - 1] = b[i][j];
				}
			}
			for(int k = 0; k < size; k++) {
				for(int l = k; l < size; l++) {
					if(arr[k] > arr[l] && arr[l] != 0 && arr[k] != 1 && arr[k] != arr[size - 1]) {
						inversion++;
					}	
				}
			}
			if((inversion % 2) == 0) {//if the inversion is total even then return true

				return true;
			}
		}
		
		//even size board (2x2 || 4x4)
		else {
			int blankRow = 0;
			for(int i = 0; i < size(); i++) {
				for(int j = 0; j < size(); j++) {
					pos = i * size() + j + 1;//one dimensional position
					arr[pos - 1] = b[i][j];
					if(b[i][j] == 0) {//if we come across 0 then set blankRow to row i
						blankRow = i;
					}
				}
			}

			for(int k = 0; k < size; k++) {
				for(int l = k; l < size; l++) {
					if(arr[k] > arr[l] && arr[l] != 0 && arr[k] != 1 && arr[k] != arr[size - 1]) {
						inversion++;
					}	
				}
			}

			int sum = inversion + blankRow;//add the inversion total and blankRow
			if((sum % 2) == 1) {//if the sum is odd then the board is solvable
				return true;
			}
		}
		return false;	
	}
	// does this board equal y?
	public boolean equals(Object y) {
		if(y == this) return true;
		if(y == null) return false;
		if(y.getClass() != this.getClass()) return false;
		
		Board that = (Board)y;
		for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.b[i][j] != that.b[i][j]) {
                    return false;
                }
            }
        }
		if(this.size() != that.size()) return false;
		return true;
	}
	
	/**
	 * Swap position of two positions on the board (0 and a neighbor)
	 * Used in neighbors() method.
	 * @param i1 row location of first spot to swap
	 * @param j1 column location of first spot to swap
	 * @param i2 row location of second spot to swap to
	 * @param j2 column location of second spot to swap to
	 */
	public void swap(int i1, int j1, int i2, int j2) {
		int temp = b[i1][j1];
		b[i1][j1] = b[i2][j2];
		b[i2][j2] = temp;
	}
	/**
	 * Gives a Queue of all 'neighbor' boards.  In other words,
	 * gives all possible moves on the board.
	 * @return Queue of Boards, up to 4, of neighbors
	 */
	public Iterable<Board> neighbors(){
		Queue<Board> boardList = new Queue<Board>();
		
		for(int i = 0; i < size(); i++) {
			for(int j = 0; j < size(); j++) {
				
				if(b[i][j] == 0) {
					//if 0 is not in the left most row then we can swap 0 and left
					if(j != 0) {
						Board b1 = new Board(b);//create new neighbor
						b1.swap(i, j, i, j - 1);//swap 0 with the left site
						boardList.enqueue(b1);//add the neighbor to the queue
					}
					//if 0 is not in the right most row then we can swap 0 and right
					if(j != size() - 1) {
						Board b2 = new Board(b);//create the new neighbor
						b2.swap(i, j, i, j + 1);//swap 0 with the right site
						boardList.enqueue(b2);//add the neighbor to the queue
					}
					//if 0 is not in the top row then we can swap 0 and top
					if(i != 0) {
						Board b3 = new Board(b);//create the new neighbor
						b3.swap(i, j, i - 1, j);//swap 0 with the site above
						boardList.enqueue(b3);//add the neighbor to the queue
					}
					//if 0 is not in the bottom row the  we can swap 0 and bottom
					if(i != size() - 1) {
						Board b4 = new Board(b);//create new neighbor
						b4.swap(i, j, i + 1, j);//swap 0 with the site below
						boardList.enqueue(b4);//add the neighbor to the queue
					}
					return boardList;
				}
			}
		}
		return boardList;	
	}
	// string representation of this board (in the output format specified below)
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(b.equals(null)) {
			sb.append("null");
		}else {
			for(int i = 0; i < size(); i++) {
				for(int j = 0; j < size(); j++) {
					sb.append(b[i][j]).append(" ");
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
			return sb.toString();		
	}

	public static void main(String[] args) {
		int[][] bo = {{4,1,3},{0,2,6},{7,5,8}};
		int[][] bo0 = {{1,2,3},{4,5,6},{8,7,0}};
		int[][] bo1 = {{1,2,0},{4,8,3},{7,6,5}};
		int[][] bo2 = {{3,2,4,8},{1,6,0,12},{5,10,7,11},{9,13,14,15}};
		int[][] bo3 = {{1,2,4,12},{5,6,3,0},{9,10,8,7},{13,14,11,15}};
		int[][] bo4 = {{5,3,4,8},{2,1,0,7},{9,6,10,11},{13,14,15,12}};
		int[][] bo5 = {{1,2,7},{0,4,3},{6,5,8}};


		Board board = new Board(bo5);
		System.out.println(board);
		System.out.println(board.isSolvable());


	}
	public int tileAt(int row, int col) {
		return 	b[row][col];
	}

}
