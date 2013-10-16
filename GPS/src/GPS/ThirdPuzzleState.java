package GPS;

import java.util.Arrays;

public class ThirdPuzzleState extends State{
	private int k;
	private int[][] board;
	private int[] conflicts;
	private int[] nOfColConflicts;
	
	public ThirdPuzzleState(int k){
		super();
		this.k = k;
		this.setBoard(new int[k*k][k*k]);
		this.setConflicts(new int[k]);
		this.setnOfColConflicts(new int [k]);
	}

	public ThirdPuzzleState(ThirdPuzzleState state){
		super();
		this.k = state.k;
		this.setConflicts(state.conflicts.clone());
		this.setnOfColConflicts(state.nOfColConflicts.clone());
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("STFFU");
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
