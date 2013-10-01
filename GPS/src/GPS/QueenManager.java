package GPS;

import java.util.ArrayList;

public class QueenManager extends StateManager{
	private int k;
	
	
	public QueenManager(int k){
		super();
		currentState = createInitState(new QueenState(k));
		this.k = k;
	}
	
	public ArrayList<GeneralNode> createChildren(GeneralNode currentGeneralNode2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public State findBestNeighbor(){
		
		return null;
	}
	
	public State createInitState(State state) {
		QueenState current = (QueenState)state;
		int[] board = current.getBoard();
		for (int i = 0; i < k; i++) {
			board[i] = (int)(Math.random()*k);
		}
		current.setBoard(board);
		return current;
	}
	
	public void updateConflicts(){
		
	}
}	
	
