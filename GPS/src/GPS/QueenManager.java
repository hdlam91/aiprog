package GPS;

import java.util.ArrayList;

public class QueenManager extends StateManager{
	public boolean [][] state; //The state of the node, i.e. the egg carton. 
	private double F; //The objective function value
	
	public QueenManager(){
		super();
	}
	
	public ArrayList<GeneralNode> createChildren(GeneralNode currentGeneralNode2) {
		// TODO Auto-generated method stub
		ArrayList<GeneralNode> children = new ArrayList<GeneralNode>();
		for (int i = 0; i < 20; i++) {
			
			children.add(new KQueenNode(state));
		}
		return null;
	}

	public Object createInitState() {
		// TODO Auto-generated method stub
		return null;		
	}
	
}	
	
//	
//	//Calculates the objective function value for this state/node
//	//Simply returns the number of eggs in the grid.
//	public double calculateF() {
//		double sum = 0;
//		int lengthX = state[0].length;
//		int lengthY = state.length;
//		for (int x = 0; x < lengthX; x++) {
//			for (int y = 0; y < lengthY; y++) {
//				if (state[y][x]) {
//					sum++;
//				}
//			}
//		}		
//		return sum;
//	}
//
//	
//	public double getF() {
//		return F;
//	}
//	
//	public Object getState() {
//		return state;
//	}
//	
//	//Returns a copy of the node's state. 
//	public boolean [][] getCopyState () {
//		boolean [][] copy = new boolean[state.length][state.length];
//		for (int i = 0; i < state.length; i++) {
//			for (int j = 0; j < state.length; j++) {
//				copy[i][j] = state[i][j];
//				
//			}
//		}
//		return copy;
//	}
//	
//	
//	
//	//CSP stuff
//	public int legalPos (boolean [][] array, int x, int y){
//		return checkDiagPos(array,x,y) + checkVerticalPos(array,x,y);
//	}
//	
//	
//	//checks number of horisontal attacks	
//	public int checkHorisontalPos(boolean[][] array, int x, int y) {
//		int numOfEggs = 0;
//		for (int i = 0; i < array.length; i++) {
//			if(array[i][x]){
//				numOfEggs++;
//			}
//		}
//		return numOfEggs;
//		
//		
//	}
//	//checks number of vertical attacks
//	public int checkVerticalPos(boolean[][] array, int x, int y) {
//		int numOfEggs = 0;
//		for (int i = 0; i < array[0].length; i++) {
//			if(array[y][i]){
//				numOfEggs++;
//			}
//		}
//		return numOfEggs;
//		
//		
//	}
//	
//	public int checkDiagPos(boolean[][] array, int x, int y) {
//		//sjekker diagonalt høyre nedover
//		
//		int bredde = x, hoyde = y;
//		int numOfEggsb = 0;
//		while(bredde < array.length && hoyde < array.length){
//			if(array[hoyde][bredde])
//				numOfEggsb++;
//			bredde++;
//			hoyde++;
//		}
//		
//		//sjekker diagonalt venstre oppover
//		bredde = x;
//		hoyde = y;
//		while(hoyde >=0 && bredde>=0){
//			if(array[hoyde][bredde])
//				numOfEggsb++;
//			bredde--;
//			hoyde--;
//		}
//		//sjekker diagonalt venstre nedover 
//		
//		bredde = x;
//		hoyde = y;
//		int numOfEggsf = 0;
//		while(hoyde >=0 && bredde<array.length){
//			if(array[hoyde][bredde])
//				numOfEggsf++;
//			hoyde--;
//			bredde++;
//		}
//		//sjekker diagonalt høyre oppover
//		bredde = x;
//		hoyde = y;
//		while(hoyde < array.length && bredde>=0){
//			if(array[hoyde][bredde])
//				numOfEggsf++;
//			hoyde++;
//			bredde--;
//		}
//		
//		
//		return numOfEggsb+numOfEggsf;
//	}
//	
//	
//	
//}
