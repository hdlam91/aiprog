package GPS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThirdPuzzleManager extends StateManager{
	private int k;
	private int lastRow;
	private double maxConflicts;
	private boolean moved;
	
	public ThirdPuzzleManager(int k){
		super();
		this.name = "ThirdPuzzle k="+k;
		this.k = k;
		this.lastRow = -1;
		this.moved = false;
		this.maxConflicts = k*(k-1); //TODO
		currentState = createInitState(new ThirdPuzzleState(k));
		updateConflicts(currentState);
	}
	
	@Override //TODO
	public ArrayList<State> createChildren(State state) {
		ArrayList<State> children = new ArrayList<State>();
		for (int i = 0; i < 20; i++) {
			ThirdPuzzleState child = new ThirdPuzzleState(k);
			calculateF(child);
			children.add(child);
		}
		return children;
	}
	
	@Override
	public void calculateF(State state) {
		double crashes = state.getCrashes();
		state.setF((maxConflicts-crashes)/maxConflicts);
	}
	
	
	public State findBestNeighbor(State state){
		return null;
	}
	
	public State createInitState(State state) {
		ThirdPuzzleState current = (ThirdPuzzleState)state;
		int[][] board = current.getBoard();
		List<Integer> dataList = new ArrayList<Integer>();
		int[] numbers = new int[k*k];
	    for (int i = 0; i < k*k; i++) {
	    	numbers[i] = i+1;
	    	dataList.add(i);
	    }
		
		
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				Collections.shuffle(dataList);
				for (int i2 = 0; i2 < k; i2++) {
					for (int j2 = 0; j2 < k; j2++) {
						board[i*3+i2][j*3+j2] = numbers[dataList.get(i2*k+j2)];
					}
				}
			
			}
			//add to board.
		}
		
		
		
		return current;
	}
	
	public void updateConflicts(State state){
	}
	
	
	public boolean getGoalState(){
		return currentState.getCrashes()==0;
	}
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		ThirdPuzzleState tps = (ThirdPuzzleState) currentState;
		int[][] board = tps.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(j %3 == 0 && j!= 0){
					sb.append("\t");
				}
				sb.append(board[i][j]+ " ");
			}
			if(i%3 == 2 && i != 0)
			sb.append("\n\n");
			else {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ThirdPuzzleManager tpm = new ThirdPuzzleManager(3);
		System.out.println(tpm);
	}
}	
