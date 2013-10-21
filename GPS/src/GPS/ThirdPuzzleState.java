package GPS;

import java.util.Arrays;

public class ThirdPuzzleState extends State{
	private int k;
	private int[][] board;
	private int[][] conflicts;
	private int[] nOfColConflicts;
	
	public ThirdPuzzleState(int k){
		super();
		this.k = k;
		this.setBoard(new int[k*k][k*k]);
		this.setConflicts(new int[k*k][k*k]);
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
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Temperature:\t"+getF()+"\nnum of conflicts in total: \t" 
				+ getCrashes()+"\nConfl:\n");
		ThirdPuzzleState tps =  this;
		int[][] board = tps.getBoard();
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
		
		buffer.append("\n\n");
		int[][] confl = tps.getConflicts();
		for (int i = 0; i < confl.length; i++) {
			for (int j = 0; j < confl[0].length; j++) {
				if(j %k == 0 && j!= 0){
					buffer.append("\t");
				}
				buffer.append(confl[i][j]+ " ");
			}
			if(i%k == k-1 && i != 0)
				buffer.append("\n\n");
			else {
				buffer.append("\n");
			}
		}
		buffer.append(tps.getCrashes());
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
