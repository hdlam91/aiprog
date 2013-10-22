package GPS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThirdPuzzleManager extends StateManager{
	private int k;
	private ThirdPuzzleTupple lastTupple;
	private double maxConflicts;
	private boolean moved;
	
	public ThirdPuzzleManager(int k){
		super();
		this.name = "ThirdPuzzle k="+k;
		this.k = k;
		this.lastTupple = new ThirdPuzzleTupple(-1, -1);
		this.moved = false;
		this.maxConflicts = k*k*k*k*k; //TODO
		currentState = createInitState(new ThirdPuzzleState(k));
		updateConflicts(currentState);
//		System.out.println(currentState);
//		currentState = findBestNeighbor(currentState);
//		System.out.println(currentState);
	}
	
	@Override //TODO
	public ArrayList<State> createChildren(State state) {
		ArrayList<State> children = new ArrayList<State>();
		for (int i = 0; i < 20; i++) {
			ThirdPuzzleState child = new ThirdPuzzleState((ThirdPuzzleState)state);
			int[][] newRandomModifiedBoard = ((ThirdPuzzleState) child).getBoard();
			int squareToSwapx = (int)(Math.random()*k);
			int squareToSwapy = (int)(Math.random()*k);
			int pos1x = (int)(Math.random()*k);
			int pos1y = (int)(Math.random()*k);
			int pos2x = (int)(Math.random()*k);
			int pos2y = (int)(Math.random()*k);
			while(pos1x == pos2x && pos1y == pos2y){
				pos1x = (int)(Math.random()*k);
				pos1y = (int)(Math.random()*k);
				pos2x = (int)(Math.random()*k);
				pos2y = (int)(Math.random()*k);
			}
			
			
			int numberAtPos1 = newRandomModifiedBoard[squareToSwapy*k + pos1y][squareToSwapx*k + pos1x];
			newRandomModifiedBoard[squareToSwapy*k + pos1y][squareToSwapx*k + pos1x] = newRandomModifiedBoard[squareToSwapy*k + pos2y][squareToSwapx*k + pos2x];
			newRandomModifiedBoard[squareToSwapy*k + pos2y][squareToSwapx*k + pos2x] = numberAtPos1;
				
			child.setBoard(newRandomModifiedBoard);
			calculateF(child);
			children.add(child);
		}
		return children;
	}
	
	@Override
	public void calculateF(State state) {
		ThirdPuzzleState tps = (ThirdPuzzleState)state;
		
		updateConflicts(tps);
		double crashes = tps.getCrashes();
		double e = Math.E;
		double f = 1-(crashes/((double)maxConflicts));
//		if(f <= 0)
//			gs.setF(0);
//		else
			tps.setF(f);
		
		
		
		
	}
	
	
	public State findBestNeighbor(State state){
		ThirdPuzzleState tps = (ThirdPuzzleState) state;
		ArrayList<ThirdPuzzleTupple> indexList = new ArrayList<ThirdPuzzleTupple>();
		int[][] conflicts = tps.getConflicts();
		int[][] board = tps.getBoard();
		for (int i = 0; i < k*k; i++) {
			for (int j = 0; j < k*k; j++) {
				if(conflicts[i][j]>0){
					indexList.add(new ThirdPuzzleTupple(i, j));
				}
			}
		}
		int randomIndexInList = (int)(Math.random()*indexList.size());
//		System.out.println(""+randomIndexInList + indexList.get(randomIndexInList));
//		for (int i = 0; i < indexList.size(); i++) {
//			System.out.println("y" + indexList.get(i).getY() + "x" + indexList.get(i).getX());
//		}
		ThirdPuzzleTupple chosenTupple = indexList.get(randomIndexInList);
		if(!moved){
			indexList.remove(lastTupple);
			randomIndexInList = (int)(Math.random()*indexList.size());
			chosenTupple = indexList.get(randomIndexInList);
		}
		int y = chosenTupple.getY();
		int x = chosenTupple.getX();
		int initialValue = board[y][x];
		
//		System.out.println("initial board");
//		for (int k = 0; k < board.length; k++) {
//			System.out.println(Arrays.toString(board[k]));
//		}
		
//		System.out.println(chosenTupple + " val: " + initialValue);
		
//		System.out.println((y/k)*k);
//		System.out.println((x/k)*k);
		int indexX = 0, indexY = 0;
		
		tps.resetSquareConflicts();
		int[][] squareConflicts = tps.getSquareConflicts();
		
		int lowestValue = Integer.MAX_VALUE;
		
		for (int i = (y/k)*k; i < (y/k)*k+k; i++) {
//			System.out.println("y"+i);
			indexX = 0;
			for (int j = (x/k)*k; j < (x/k)*k+k; j++) {
//				System.out.print("x"+j);
				if(i==y&&j==x){
					squareConflicts[indexY][indexX] = conflicts[i][j];
					if(lowestValue>conflicts[i][j])
						lowestValue = conflicts[i][j];
				}
				else{
					int initialValueForSwapPosition = board[i][j];
//					System.out.println("y" + i + "x" + j + " val: " + initialValueForSwapPosition);
					
					board[i][j] = initialValue;
					board[y][x] = initialValueForSwapPosition;
					
//					for (int k = 0; k < board.length; k++) {
//						System.out.println(Arrays.toString(board[k]));
//					}
					
					int squareConflictValue = squareConflictValue(y, x, initialValue, i, j, initialValueForSwapPosition, board);
					squareConflicts[indexY][indexX] = squareConflictValue;
					if(lowestValue>squareConflictValue)
						lowestValue = squareConflictValue;
					
					board[i][j] = initialValueForSwapPosition;
					board[y][x] = initialValue;
				}
				indexX+=1;
			}
			indexY +=1;
//			System.out.println();
		}
		
		ArrayList<ThirdPuzzleTupple> minimumConflictList = new ArrayList<ThirdPuzzleTupple>();
		for (int i = 0; i < squareConflicts.length; i++) {
			for (int j = 0; j < squareConflicts.length; j++) {
				if(squareConflicts[i][j] == lowestValue)
					minimumConflictList.add(new ThirdPuzzleTupple((y/k)*k+i, (x/k)*k+j));
			}
		}
		
		ThirdPuzzleTupple output = null;
//		System.out.println("size "+  minimumConflictList.size());
		if(minimumConflictList.size()>0){
			output = minimumConflictList.get((int)(Math.random()*minimumConflictList.size()));
		}
		
		while(minimumConflictList.size()>0 && (output.getX() == chosenTupple.getX() && output.getY() == chosenTupple.getY())){
			minimumConflictList.remove(output);
			if(minimumConflictList.size()>0)
				output = minimumConflictList.get((int)(Math.random()*minimumConflictList.size()));
			else
				output = null;
		}
		
		if(output == null){
			this.moved = false;
			this.lastTupple = chosenTupple;
		}
		else{
//			System.out.println("switch with: " +output);
			this.moved = true;
			int insY = output.getY();
			int insX = output.getX();
			int insValue = board[insY][insX];
			
			board[y][x] = insValue;
			board[insY][insX] = initialValue;
		}
		

//		for (ThirdPuzzleTupple thirdPuzzleTupple : minimumConflictList) {
//				System.out.println(thirdPuzzleTupple);
//		}
//		
//		for (int i = 0; i < squareConflicts.length; i++) {			
//			System.out.println(Arrays.toString(squareConflicts[i]));
//		}
//		System.out.println("lowest" + lowestValue);
//		
//		for (int i = 0; i < board.length; i++) {			
//			System.out.println(Arrays.toString(board[i]));
//		}
		
		updateConflicts(tps);
		return tps;
	}
	
	public int squareConflictValue(int y1, int x1, int initialValue, int y2, int x2, int initialValueForSwap, int[][] board){
		int crashes = 0;
		for (int i = 0; i < board.length; i++) {
			if(i!=x1 && board[y1][i]==initialValueForSwap){
				crashes+=1;
			}
			if(i!=x2 && board[y2][i]==initialValue){
				crashes+=1;
			}
			if(i!=y1 && board[i][x1]==initialValueForSwap){
				crashes+=1;
			}
			if(i!=y2 && board[i][x2]==initialValue){
				crashes+=1;
			}			
 		}
		return crashes;
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
						board[i*k+i2][j*k+j2] = numbers[dataList.get(i2*k+j2)];
					}
				}
			
			}
		}
		
		return current;
	}
	
	public void updateConflicts(State state){
		ThirdPuzzleState tps = (ThirdPuzzleState) state;
		tps.resetConflicts();
		int[][] conflicts = tps.getConflicts();
		int[][] board = tps.getBoard();
		int crashes = 0;
		for (int i = 0; i < k*k; i++) {
			for (int j = 0; j < k*k; j++) {
				for (int c = 0; c < k*k; c++) {
					if(board[i][j] == board[i][c] && c != j){
						conflicts[i][j]++;
						crashes++;
					}
					if(board[i][j] == board[c][j] && c != i){
						conflicts[i][j]++;
						crashes++;
					}
					
				}
			}
		}
		tps.setCrashes(crashes);
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
				if(j %k == 0 && j!= 0){
					sb.append("   ");
				}
				if(board[i][j]>=10)
					sb.append(board[i][j]+ " ");
				else
					sb.append(" "+board[i][j]+ " ");
			}
			if(i%k == k-1 && i != 0 && i!= board.length-1)
			sb.append("\n\n");
			else {
				sb.append("\n");
			}
		}
		
		sb.append("\n\n");
		int[][] confl = tps.getConflicts();
		for (int i = 0; i < confl.length; i++) {
			for (int j = 0; j < confl[0].length; j++) {
				if(j %k == 0 && j!= 0){
					sb.append("\t");
				}
				sb.append(confl[i][j]+ " ");
			}
			if(i%k == k-1 && i != 0)
				sb.append("\n\n");
			else {
				sb.append("\n");
			}
		}
		sb.append(tps.getCrashes());
		return sb.toString();
	}
	
//	public static void main(String[] args) {
//		ThirdPuzzleManager tpm = new ThirdPuzzleManager(3);
//		ThirdPuzzleState tps = new ThirdPuzzleState(2);
//		int[][] board = {{3,4,1,4},{1,2,2,3},{1,3,4,3},{2,4,1,2}};//{{3,4,4,3},{1,2,1,2},{1,2,3,4},{4,3,2,1}};
//		
//		tps.setBoard(board);
		//tpm.setCurrentState(tps);
		
//		tpm.createChildren(tpm.getCurrentState());
//		tpm.updateConflicts(tps);
//		System.out.println(tpm);
//	}
	
}	
