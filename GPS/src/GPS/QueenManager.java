package GPS;

import java.util.ArrayList;

public class QueenManager extends StateManager{
	private int k;
	private int[] board;
	
	public QueenManager(int k){
		super();
		this.k = k;
		this.board = new int[k];
	}
	
	public ArrayList<GeneralNode> createChildren(GeneralNode currentGeneralNode2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object findBestNeighbor(){
		return null;
	}
	
	public Object createInitState() {
		for (int i = 0; i < k; i++) {
			board[i] = (int)(Math.random()*k);
		}
		return board;
	}
	
}	
	
