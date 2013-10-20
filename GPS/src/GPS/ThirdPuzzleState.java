package GPS;

import java.util.Arrays;

public class ThirdPuzzleState extends State{
	private int k;
	private int[][] board;
	private int[][] conflicts;
	private int[][] squareConflicts;
//	private int[] nOfColConflicts, nOfColConflicts2, nOfRowConflicts, nOfRowConflicts2;
	
	public ThirdPuzzleState(int k){
		super();
		this.k = k;
		this.setBoard(new int[k*k][k*k]);
		this.setConflicts(new int[k*k][k*k]);
		this.setSquareConflicts(new int[k][k]);
//		this.setnOfColConflicts(new int [k*k]);
//		this.setnOfColConflicts2(new int [k*k]);
//		this.setnOfRowConflicts(new int [k*k]);
//		this.setnOfRowConflicts2(new int [k*k]);
	}

	public ThirdPuzzleState(ThirdPuzzleState state){
		super();
		this.k = state.k;
		this.setBoardClone(state.board);
		this.setConflictsClone(state.conflicts);  
//		this.setnOfColConflicts(state.nOfColConflicts.clone());
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
		//TODO
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
		buffer.append("STFFU");
		return buffer.toString();
	}

	public int[][] getSquareConflicts() {
		return squareConflicts;
	}

	public void setSquareConflicts(int[][] squareConflicts) {
		this.squareConflicts = squareConflicts;
	}

	public void resetSquareConflicts(){
		//TODO
		for (int i = 0; i < k*k; i++) {
			for (int j = 0; j < k*k; j++) {
				squareConflicts[i][j] = 0;
			}
		}
	}
	
	
	/*
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

	
	
	public int[] getnOfColConflicts2() {
		return nOfColConflicts2;
	}

	public void setnOfColConflicts2(int[] nOfColConflicts2) {
		this.nOfColConflicts2 = nOfColConflicts2;
	}
	
	public void resetNOfColConflicts2(){
		for (int i = 0; i < k; i++) {
			nOfColConflicts2[i] = 0;
		}
	}

	
	
	public int[] getnOfRowConflicts() {
		return nOfRowConflicts;
	}

	public void setnOfRowConflicts(int[] nOfRowConflcits) {
		this.nOfRowConflicts = nOfRowConflcits;
	}

	public void resetNOfRowConflicts(){
		for (int i = 0; i < k; i++) {
			nOfRowConflicts[i] = 0;
		}
	}
	
	
	
	public int[] getnOfRowConflicts2() {
		return nOfRowConflicts2;
	}

	public void setnOfRowConflicts2(int[] nOfRowConflicts2) {
		this.nOfRowConflicts2 = nOfRowConflicts2;
	}
	
	public void resetNOfRowConflicts2(){
		for (int i = 0; i < k; i++) {
			nOfRowConflicts2[i] = 0;
		}
	}
	*/
	
	
	//Testing deep vs shallow copy
//	public static void main(String[] args) {
//		ThirdPuzzleState tps = new ThirdPuzzleState(3);
//		ThirdPuzzleState tps2 = new ThirdPuzzleState(tps);
//		int[][] board1 = tps.getConflicts();
//		System.out.println("before");
//		for (int i = 0; i < tps.getBoard().length; i++) {
//			System.out.println(Arrays.toString(board1[i]));
//		}
//		int[][] board2 = tps2.getConflicts();
//
//		System.out.println("after");
//		for (int i = 0; i < tps.getBoard().length; i++) {
//			board1[i][0] = i;
//			System.out.println(Arrays.toString(board1[i]));
//		}
//		System.out.println("b2");
//		for (int i = 0; i < tps2.getBoard().length; i++) {
//			board2[i][i] = i;
//			System.out.println(Arrays.toString(board2[i]));
//		}
//		System.out.println(board1==board2);
//	}
}
