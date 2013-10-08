package GPS;

import java.util.Arrays;

public class QueenState extends State{
	private int k;
	private int[] board;
	private int[] conflicts;
	private int[] nOfColConflicts;
	
	public QueenState(int k){
		super();
		this.k = k;
		this.setBoard(new int[k]);
		this.setConflicts(new int[k]);
		this.setnOfColConflicts(new int [k]);
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
	
	public void resetConflicts(){
		for (int i = 0; i < k; i++) {
			conflicts[i] = 0;
		}
	}

	public void setConflicts(int[] conflicts) {
		this.conflicts = conflicts;
	}
	
	public String toString(){
		int[][] printBoard = new int[k][k];
		StringBuffer buffer = new StringBuffer();
		buffer.append("The board \n");
		for (int i = 0; i < k; i++) {
			printBoard[i][board[i]] = 1;
			buffer.append(Arrays.toString(printBoard[i])+"\n");
		}
		if(getF()>0){
			buffer.append("\n" + "Conflicts for each row\n" + Arrays.toString(conflicts)+"\n");
			buffer.append("Crashes: " +getF());
		}
		return buffer.toString();
	}

	public int[] getnOfColConflicts() {
		return nOfColConflicts;
	}

	public void setnOfColConflicts(int[] nOfColConflicts) {
		this.nOfColConflicts = nOfColConflicts;
	}
	
	public void resetNOfColConflicts(){
		for (int i = 0; i < k; i++) {
			nOfColConflicts[i] = 0;
		}
	}
}
