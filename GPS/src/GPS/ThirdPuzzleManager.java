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
		this.maxConflicts = k*k*k*k*k*3; //TODO
		currentState = createInitState(new ThirdPuzzleState(k));
		updateConflicts(currentState);
		findBestNeighbor(currentState);
		System.out.println(currentState);
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
			while(pos1x == pos2x || pos1y == pos2y){
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
			System.out.println(child.getF());
		}
		return children;
	}
	
	@Override
	public void calculateF(State state) {
		ThirdPuzzleState tps = (ThirdPuzzleState)state;
		
		updateConflicts(tps);
		double crashes = tps.getCrashes();
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
		int y = chosenTupple.getY();
		int x = chosenTupple.getX();
//		System.out.println((y/k)*k);
//		System.out.println((x/k)*k);
//		for (int i = (y/k)*k; i < (y/k)*k+k; i++) {
//			System.out.println("y"+i);
//			for (int j = (x/k)*k; j < (x/k)*k+k; j++) {
//				System.out.print("x"+j);
//			}
//			System.out.println();
//		}
				
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
						board[i*k+i2][j*k+j2] = numbers[dataList.get(i2*k+j2)];
					}
				}
			
			}
		}
		
		return current;
	}
	
	
	public void updateLocalConflict(int index, State state, boolean updateNeighbours){
//		ThirdPuzzleState tps = (ThirdPuzzleState) state;
//		int[][] board = tps.getBoard();
//		int[][] conflicts = tps.getConflicts();
//		
//		int crashes = 0;
//			for (int i = 0; i < k*k; i++) {
//				if(index != i && matrix[index][i] && nodes[index] == nodes[i]){
//					if(updateNeighbours){
//						updateLocalConflict(i, tps, false);
//					}
//					crashes+=1;
//					
//				}
//			}
//		conflicts[index] = crashes;
//		tps.setCrashes(oldCrashes-oldConflict+crashes);
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
//		ThirdPuzzleManager tpm = new ThirdPuzzleManager(2);
//		ThirdPuzzleState tps = new ThirdPuzzleState(2);
////		int[][] board = {{3,4,1,4},{1,2,2,3},{1,3,4,3},{2,4,1,2}};//{{3,4,4,3},{1,2,1,2},{1,2,3,4},{4,3,2,1}};
////		
////		tps.setBoard(board);
//		//tpm.setCurrentState(tps);
//		
//		tpm.createChildren(tpm.getCurrentState());
////		tpm.updateConflicts(tps);
//		System.out.println(tpm);
//	}
	
	
}	
