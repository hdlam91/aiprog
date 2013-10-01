package GPS;

import java.util.ArrayList;
import java.util.Arrays;

public class QueenManager extends StateManager{
	private int k;
	
	
	public QueenManager(int k){
		super();
		this.k = k;
		currentState = createInitState(new QueenState(k));
		updateConflicts();
		for (int i = 0; i < k; i++) {
			System.out.println("Row "+i+":\n" + Arrays.toString(noFColConflicts(i)));
		}
	}
	
	@Override
	public ArrayList<State> createChildren(State state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public State findBestNeighbor(){
		QueenState qs = (QueenState)currentState;
		int[] conflicts = qs.getConflicts();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) {
			if(conflicts[i]>0)
				indexes.add(i);
		}
		return null;
	}
	
	public State createInitState(State state) {
		QueenState current = (QueenState)state;
		int[] board = current.getBoard();
		for (int i = 0; i < k; i++) {
			board[i] = (int)(Math.random()*k);
			}
//		current.setBoard(board);
		return current;
	}
	
	public void updateConflicts(){
		QueenState qs = (QueenState) currentState;
		int[] conflicts = qs.getConflicts();
		int[] board = qs.getBoard();
		int qi = 0;
		int qj = 0;
		int crashes = 0;
		for (int i = 0; i < k; i++) {
			qi = board[i]; 
			for (int j = 0; j < k; j++) {
				if(i!=j){
					qj = board[j];
					if(qi == qj){
						conflicts[i]+=1;
						crashes+=1;
					}
					else if(qj == qi + Math.abs(i-j)){
						conflicts[i]+=1;
						crashes+=1;
					}
					else if(qj == qi - Math.abs(i-j)){
						conflicts[i]+=1;
						crashes+=1;
					}
				}
			}
		qs.setF(crashes);
		}
	}
	
	public int[] noFColConflicts(int row){
		QueenState qs = (QueenState) currentState;
		int[] output = new int[k];
		int[] temp = qs.getBoard().clone();
		for (int i = 0; i < output.length; i++) {
			int largest = 0;
			temp[row] = i;
			for (int j = 0; j <output.length; j++) {
				if(row!=j){
					int qj = temp[j];
					largest += checkForQueens(i, qj, j, row);
				}
			}
			output[i] = largest;
		}
		return output;
	}
	
	public int checkForQueens(int qi, int qj, int j, int row){
		int counter = 0;
		if(qj == qi){
			counter ++;
		}
		else if(qj == qi+j - row){
			counter ++;
		}
		else if(qj == qi-j + row){
			counter ++;
		}
		return counter;
	}
}	
	
