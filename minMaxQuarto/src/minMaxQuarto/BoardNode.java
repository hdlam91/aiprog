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
	
	public BoardNode(Board board, int maxDepth) {
		currentState = new Board(board);
		this.maxDepth = maxDepth; 
	}
	public BoardNode(Board board, int maxDepth, int x, int y, int index) {
		currentState = new Board(board);
		this.maxDepth = maxDepth;
		currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(index), index);
		placedX = x;
		placedY = y;
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
							BoardNode child = new BoardNode(currentState, depth+1,x,y,i);
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
	
	
	public int evaluatedValue(){
		if (depth%2 == 0 && currentState.checkWin()) {
			return 100;
		}
		else if (depth%2 == 1 && currentState.checkWin()) {
			return -100;
		}
		return 0;
	}
	
	public int getPlacedX(){
		return placedX;
	}
	public int getPlacedY(){
		return placedY;
	}
	
}
