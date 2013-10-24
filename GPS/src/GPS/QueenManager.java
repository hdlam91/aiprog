package GPS;

import java.util.ArrayList;

/**
 * This class manages the State(s) for the K-Queen problem. It implements all of methods associated with managing states
 * (I.e all we have defined in StateManager) and it also implements some methods that are specific to this problem.
 * @author Eivind
 *
 */

public class QueenManager extends StateManager{
	private int k;
	private int lastRow;
	private double maxConflicts;
	private boolean moved;
	
	public QueenManager(int k){
		super();
		this.name = "QueenManager k="+k;
		this.k = k;
		this.lastRow = -1;
		this.moved = false;
		this.maxConflicts = (k*3);
		currentState = createInitState(new QueenState(k));
		updateConflicts(currentState);
	}
	
	public ArrayList<State> createChildren(State state) {
		ArrayList<State> children = new ArrayList<State>();
		for (int i = 0; i < 20; i++) {
			QueenState child = new QueenState((QueenState)state);
			int[] tempBoard =  child.getBoard();
			tempBoard[(int)(Math.random()*tempBoard.length)] = (int)(Math.random()*tempBoard.length);
			updateConflicts(child);
			calculateF(child);
			children.add(child);
		}
		return children;
	}
	
	public void calculateF(State state) {
		double crashes = state.getCrashes();
		double newF = 1-((crashes)/maxConflicts);
		if(newF<0) newF = 0.0;
		state.setF(newF);
	}
	
	public State findBestNeighbor(State state){
		QueenState qs = (QueenState)state;
		int[] conflicts = qs.getConflicts();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) {
			if(conflicts[i]>0)
				indexes.add(i);
		}
		int chosenIndex = (int)(Math.random()*indexes.size());
		if(!moved){
			while(indexes.size()>1 && indexes.get(chosenIndex)==lastRow){
				indexes.remove(chosenIndex);
				chosenIndex = (int)(Math.random()*indexes.size());
			}
		}
		int row = indexes.get(chosenIndex);
		moveQueen(noFColConflicts(row,currentState), row,currentState);
		updateConflicts(currentState);
		return qs;
	}
	
	public State createInitState(State state) {
		QueenState current = (QueenState)state;
		int[] board = current.getBoard();
		for (int i = 0; i < k; i++) {
			board[i] = (int)(Math.random()*k);
		}
		return current;
	}
	
	public void updateConflicts(State state){
		QueenState qs = (QueenState) state;
		qs.resetConflicts();
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
		}
		qs.setCrashes(crashes);
	}
	
	public int[] noFColConflicts(int row, State state){
		QueenState qs = (QueenState) state;
		qs.resetNOfColConflicts();
		int[] output = qs.getnOfColConflicts();
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

	
	public void moveQueen(int[] colConflicts,int index,State state){
		QueenState qs = (QueenState)state;
		int board[] = qs.getBoard();
		int currentPos = board[index];
		ArrayList<Integer> counter = new ArrayList<Integer>();
		int nextPos = colConflicts[0];
		for (int i = 1; i < colConflicts.length; i++) {
			if(colConflicts[nextPos] >= colConflicts[i]){
				nextPos = i;
			}
		}
		
		for (int j = 0; j < colConflicts.length; j++) {
			if(colConflicts[j] == colConflicts[nextPos]){
				counter.add(j);		
			}
		}
		
		if(counter.size()>1){
			nextPos = counter.get((int) (Math.random()*counter.size()));
		}
		while(nextPos == currentPos && counter.size()>=1){
			nextPos = counter.get(0);
			counter.remove(0);
		}
		
		if(nextPos==currentPos){
			this.lastRow = index;
			this.moved = false;
		}
		else{
			board[index] = nextPos;
			this.moved = true;
		}
	}
	
	public boolean getGoalState(){
		return currentState.getCrashes()==0;
	}
}	
