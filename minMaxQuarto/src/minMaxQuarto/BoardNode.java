package minMaxQuarto;

import java.util.ArrayList;

public class BoardNode {
	ArrayList<BoardNode> children;
	Board currentState;
	int alpha, beta;
	int depth; // root = 0;
	int maxDepth;
	boolean ourTurn;
	int placedX, placedY;
	int heuristic;
	
	public BoardNode(Board board, int maxDepth) {
		currentState = new Board(board);
		this.maxDepth = maxDepth;
		addChildren();
	}
	public BoardNode(Board board, int depth, int maxDepth, int x, int y, int index) {
		currentState = new Board(board);
		this.maxDepth = maxDepth;
		currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(index), index);
		placedX = x;
		placedY = y;
		if(maxDepth > depth){
			addChildren();
		}
	}
	
	public void decreaseDepth(){
		depth--;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).decreaseDepth();
		}
	}
	
	public void addChildren(){
		//this method should add all the children that can be added.
		piece:
		for (int i = 0; i < 16; i++) {
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if(currentState.getPieceFromRemaining(i) != null){
						if(currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(i), i)){
							BoardNode child = new BoardNode(currentState, depth+1,maxDepth, x,y,i);
							children.add(child);
						}
						else 
							break;
					}
					else
						break piece;
				}
			}
		}
	}
	
	public void updateHeuristic(){
		if (depth%2 == 1 && currentState.checkWin()) {
			heuristic = 100;
			return;
		}
		else if (depth%2 == 0 && currentState.checkWin()) {
			heuristic = -100;
			return;
		}
		else if(currentState.usedAllPieces()){
			heuristic = 0;
			return;
		}
		//if(depth%2 == 0)
		heuristic = 0;
	}
	
	public int evaluatedValue(){
		return heuristic;
	}
	
	public int getPlacedX(){
		return placedX;
	}
	public int getPlacedY(){
		return placedY;
	}
	
}
