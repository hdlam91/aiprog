package GPS;

public class QueenState extends State{
	private int[] board;
	private int[] conflicts;
	
	public QueenState(int k){
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
}
