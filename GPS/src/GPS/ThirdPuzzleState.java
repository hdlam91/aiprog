package GPS;

/**
 * This class stores all the specific information we require to differentiate between states of the Modified Sudoku problem.
 * As well as several methods to alter and acquire this information.
 * @author Eivind
 *
 */

public class ThirdPuzzleState extends State{
	private int k;
	private int[][] board;
	private int[][] conflicts;
	private int[][] squareConflicts;
	
	public ThirdPuzzleState(int k){
		super();
		this.k = k;
		this.setBoard(new int[k*k][k*k]);
		this.setConflicts(new int[k*k][k*k]);
		this.setSquareConflicts(new int[k][k]);
	}

	public ThirdPuzzleState(ThirdPuzzleState state){
		super();
		this.k = state.k;
		this.setBoardClone(state.board);
		this.setConflictsClone(state.conflicts);  
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public void setBoardClone(int[][] board){
		int[][] temp = board.clone();
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].clone();
		}
		this.board = temp;
	}
	
	public int[][] getConflicts() {
		return conflicts;
	}
	
	public void resetConflicts(){
		for (int i = 0; i < k*k; i++) {
			for (int j = 0; j < k*k; j++) {
				conflicts[i][j] = 0;
			}
		}
	}

	public void setConflicts(int[][] conflicts) {
		this.conflicts = conflicts;
	}
	
	public void setConflictsClone(int[][] conflicts){
		int[][] temp = conflicts.clone();
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].clone();
		}
		this.conflicts = temp;
	}
	
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		if(SA)
			buffer.append("\nF: "+getF());
		buffer.append("\nNum of conflicts in total: " 
				+ getCrashes()+"\n");//+"\nConfl:\n");
		ThirdPuzzleState tps =  this;
		int[][] board = tps.getBoard();
		
//		buffer.append("\n\n");
//		int[][] confl = tps.getConflicts();
//		for (int i = 0; i < confl.length; i++) {
//			for (int j = 0; j < confl[0].length; j++) {
//				if(j %k == 0 && j!= 0){
//					buffer.append("\t");
//				}
//				buffer.append(confl[i][j]+ " ");
//			}
//			if(i%k == k-1 && i != 0)
//				buffer.append("\n\n");
//			else {
//				buffer.append("\n");
//			}
//		}
		
		buffer.append("\nThe board\n\n");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(j %k == 0 && j!= 0){
					buffer.append("   ");
				}
				if(board[i][j]>=10)
					buffer.append(board[i][j]+ " ");
				else
					buffer.append(" "+board[i][j]+ " ");
			}
			if(i%k == k-1 && i != 0 && i!= board.length-1)
				buffer.append("\n\n");
			else {
				buffer.append("\n");
			}
		}
		buffer.append("\n");
		return buffer.toString();
	}

	public int[][] getSquareConflicts() {
		return squareConflicts;
	}

	public void setSquareConflicts(int[][] squareConflicts) {
		this.squareConflicts = squareConflicts;
	}

	public void resetSquareConflicts(){
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				squareConflicts[i][j] = 0;
			}
		}
	}
}
