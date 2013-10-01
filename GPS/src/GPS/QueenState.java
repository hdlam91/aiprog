package GPS;

public class QueenState extends State{
	private int k;
	private int[] board;
	private int[] conflicts;
	
	public QueenState(int k){
		this.k = k;
		this.setBoard(new int[k]);
		this.setConflicts(new int[k]);
	}

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

	public int[] getConflicts() {
		return conflicts;
	}

	public void setConflicts(int[] conflicts) {
		this.conflicts = conflicts;
	}
	
	public String toString(){
		int[][] printBoard = new int[k][k];
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < k; i++) {
			printBoard[i][board[i]] = 1;
			buffer.append(printBoard[i]+"\n");
		}
		return buffer.toString();
	}
}
