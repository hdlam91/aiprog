package minMaxQuarto;

import java.util.ArrayList;

public class BoardNode {
	ArrayList<BoardNode> children;
	Board currentState;
	int alpha, beta;
	int depth; // root = 0;
	int maxDepth;
	boolean ourTurn;
	
	public BoardNode(Board board, int maxDepth) {
		currentState = new Board(board);
		this.maxDepth = maxDepth; 
	}
	
	public void decreaseDepth(){
		depth--;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).decreaseDepth();
		}
	}
	
	public void addChildren(){
		
	}
	
	
	public int evaluatedValue(){
		return 0;
	}
	
}
