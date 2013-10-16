package GPS;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	@Override
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
	
	@Override
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
//		System.out.println("index " + index + "moved "+ moved);
		if(!moved){
			while(indexes.size()>1 && indexes.get(chosenIndex)==lastRow){
//				System.out.println("lastrow" + lastRow + " i " + index);
				indexes.remove(chosenIndex);
				chosenIndex = (int)(Math.random()*indexes.size());
//				System.out.println("sup");
			}
		}
		int row = indexes.get(chosenIndex);
//		System.out.println("size: " +  indexes.size() + " row " + row + " index " + index);
//		System.out.println("Move on row " + row);
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
//		current.setBoard(board);
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
//		System.out.println(Arrays.toString(colConflicts));
		int board[] = qs.getBoard();
		int currentPos = board[index];
		ArrayList<Integer> counter = new ArrayList<Integer>();
		int nextPos = colConflicts[0];
//		if(nextPos != 0){ //small optimization that may be in conflict with the random choosing of best neighbor.
			//find lowest value index
			for (int i = 1; i < colConflicts.length; i++) {
				if(colConflicts[nextPos] >= colConflicts[i]){
					nextPos = i;
				}
			}
			//add all indices with the same value as the current index. 
			for (int j = 0; j < colConflicts.length; j++) {
				if(colConflicts[j] == colConflicts[nextPos]){
					counter.add(j);		
				}
			}
//		}
		//Get a random position if there are more than one viable spot.
		if(counter.size()>1){
			nextPos = counter.get((int) (Math.random()*counter.size()));
		}
		//If a queen is currently at the selected nextPos, and there are more than one viable spot to move to, get the first element
		//in the arrayList, and remove it from there to ensure that a move is enforced. 
		while(nextPos == currentPos && counter.size()>=1){
			nextPos = counter.get(0);
			counter.remove(0);
		}
		//if there is no more than one move viable, and that is where the queen currently is positioned,
		//increment the total of actions without moves, concurrent actions without moves and also which row was attempted to be 
		//changed. (To try and avoid an endless lock).
		if(nextPos==currentPos){
//			System.out.println("same");
//			noMoving ++;
			this.lastRow = index;
			this.moved = false;
//			System.out.println("lastRow in move " + lastRow);
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
