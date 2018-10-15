package puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import java.lang.IllegalArgumentException;

/**
 * @author Dillon Embreus and Tim Lawrence
 * Solver class sets up the methods we need to determine which moves and
 * what order will give the quickest solution to the puzzle at hand.
 *
 */
public final class Solver {
	private MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
	private int moves;
	private int minMoves = -1;
	private Board board;
	private Queue<Board> boardQueue = new Queue<Board>();
	 
	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * @param initial Starting board to be solved
	 */
	public Solver(Board initial) {
		if(!initial.isSolvable()) {
			throw new IllegalArgumentException("Board is not solvable.");
		}
		if(initial.equals(null)) {
			throw new NullPointerException("The board is null.");
		}
		pq.insert(new SearchNode(initial, 0, null));
		while(!pq.isEmpty()) {
			SearchNode current = pq.delMin();
			boardQueue.enqueue(current.board);
			moves = current.moves;
			if(current.board.isGoal()) {
				SearchNode root = current;
				if(!root.board.equals(initial)) {
					break;
				}	
			}
			if(minMoves == -1 || current.priority < minMoves) {
				Iterable<Board> it = current.board.neighbors();
				for(Board b : it) {
					if(current.previous == null || !b.equals(current.previous.board)) {
						pq.insert(new SearchNode(b,current.moves + 1, current));
					}
				}
			} else {
				break;
			}
		}
	}
	/**
	 * @return min number of moves to solve initial board
	 */
	public int moves() {
		return moves;
	}
	/**
	 * @return sequence of boards in a shortest solution
	 */
	public Iterable<Board> solution(){
		if(boardQueue.peek().isSolvable()) {
			return boardQueue;
		}
		return null;
	}
	// solve a slider puzzle (given below) 
	public static void main(String[] args) {
	    // create initial board from file
//		String file = "src/a04/puzzle19.txt";
//		String file1 = "src/a04/puzzle37.txt";
		String file2 = "src/puzzle/puzzle3x3-00.txt";
		String file3 = "src/puzzle/puzzle3x3-03.txt";
		String file4 = "src/puzzle/puzzle3x3-31.txt";
		String file5 = "src/puzzle/puzzle05.txt";


	    In in = new In(file2); //Change file input to test other puzzles
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	    	
	        Solver solver = new Solver(initial);
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	}

	private class SearchNode implements Comparable<SearchNode>{
		private SearchNode previous;
		private Board board;
		private int moves;
		private int priority;
		
		/**
		 * SearchNodes are how we step through the puzzle, retaining the boards that
		 * we came from in order to find the shortest route to the solution
		 * @param board the board of this SearchNode
		 * @param moves how many moves we've made from initial to make it to this board
		 * @param previous board that we came from to reach this one
		 */
		public SearchNode(Board board, int moves, SearchNode previous) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
			this.priority = moves + board.manhattan();
		}
		@Override
		public int compareTo(SearchNode that) {
			return this.priority - that.priority;
		}
		
	}

}
