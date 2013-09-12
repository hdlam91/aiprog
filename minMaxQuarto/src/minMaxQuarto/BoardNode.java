package minMaxQuarto;

import java.util.ArrayList;

public class BoardNode {
	ArrayList<BoardNode> children;
	Board currentState;
//	int alpha, beta;
	int depth; // root = 0;
	int maxDepth;
//	boolean ourTurn;
	int placedX, placedY;
	int chosenPieceIndex;
	int heuristic;
	int value;
	boolean traversed;
	BoardNode bestChild;
	
	public BoardNode(Board board, int maxDepth, int pieceIndex) {
		currentState = new Board(board);
		this.maxDepth = maxDepth;
		this.depth = 0;
		chosenPieceIndex = pieceIndex;
		traversed = false;
		children = new ArrayList<BoardNode>();
		addChildren(true);
	}
	public BoardNode(Board board, int depth, int maxDepth, int x, int y, int index) {
		currentState = new Board(board);
		this.maxDepth = maxDepth;
		this.depth = depth;
		chosenPieceIndex = index;
		placedX = x;
		placedY = y;
		traversed = false;
		children = new ArrayList<BoardNode>();
		if(maxDepth >= depth){
			addChildren(false);
		}
		//currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(index), index);
		//System.out.println(children.size());
	}
	
//	public void decreaseDepth(){
//		depth--;
//		for (int i = 0; i < children.size(); i++) {
//			children.get(i).decreaseDepth();
//		}
//	}
	
	public void addChildren(boolean inputPiece){
		//this method should add all the children that can be added.
//		System.out.println("inputPieceBool: " + inputPiece );
		if(inputPiece){
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
						if(currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(chosenPieceIndex), chosenPieceIndex)){
//							System.out.println("depth root: " + depth + " x: " + x + " y: " + y);
							
							BoardNode child = new BoardNode(currentState, depth+1,maxDepth, x,y,chosenPieceIndex);
							children.add(child);
//							System.out.println("children added" + children.size());
						}
						else 
							break;
					
				}
			}
			
		}
		else{
			for (int i = 0; i < 16; i++) {
				piece:
				for (int y = 0; y < 4; y++) {
					for (int x = 0; x < 4; x++) {
						if(currentState.getPieceFromRemaining(i) != null){
							if(currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(i), i)){
//								System.out.println("depth: " + depth + " x: " + x + " y: " + y);
//								internalBoard.removePieceOnBoard(i, j);
//								internalBoard.addPieceToRemaining(pieceOnHand, index);
								BoardNode child = new BoardNode(currentState, depth+1,maxDepth, x,y,i);
								children.add(child);
//								System.out.println("children added" + children.size());
							}
							
						}
						
					}
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
	
	public int getHeuristic(){
		updateHeuristic();
		return heuristic;
	}
	
	public BoardNode pickBestNode(){
		for (int i = 0; i < children.size(); i++) {
			System.out.println("child root:" + i + " has: "+ children.get(i).children.size());
			for (int j = 0; j < children.get(i).children.size(); j++) {
				System.out.println("child :" + j + " has: "+ children.get(i).children.get(j).children.size());
			}
		}
		
		alphabeta(this, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		BoardNode best = children.get(0);
		for (BoardNode child: children) {
			if(child.getValue() > best.getValue()){
				best = child;
			}
				
		}
		bestChild = best;
		return best;
	}
	
	
	public BoardNode pickWorstNode(){
		BoardNode currentBest = bestChild;
		System.out.println("WORST:" + currentBest.children.size());
		BoardNode worst = currentBest.children.get(0);
		for (BoardNode child: currentBest.children) {
			if(child.getValue() < worst.getValue()){
				worst = child;
			}
				
		}
		return worst;
	}
	
	public int alphabeta(BoardNode state, int depth, int alpha, int beta, boolean maximizingPlayer){
		if(depth == maxDepth || state.currentState.checkWin())
			return getHeuristic();
		if(maximizingPlayer){
			for (BoardNode child: state.children) {
				alpha = Math.max(alpha, alphabeta(child, depth++, alpha, beta, !maximizingPlayer));
				value = alpha;
				traversed = true;
				if(beta <= alpha)
					break;
			}
			return alpha;
		}
		else{
			for (BoardNode child: state.children) {
				beta = Math.min(beta, alphabeta(child, depth++, alpha, beta, !maximizingPlayer));
				value = beta;
				traversed = true;
				if(beta <= alpha)
					break;
			}
			return beta;
		}
	}
	
	//Call alphabeta(root,0,Math.MinimumValue,Math.MaximumValue,true)
	
	
	/*
	public void miniMaxDecision(BoardNode state){
		
	}
	
	public int maxValue(BoardNode state, int alpha, int beta){
		if(currentState.checkWin())
			return heuristic;
		
		int value = Integer.MIN_VALUE;
		for (BoardNode c : children) {
			value = Math.max(value, minValue(state,alpha,beta));
//			if(c.getHeuristic() > value)
//				value = getHeuristic();
			if(value >= beta)
				return value;
			alpha = Math.max(alpha,value);
		}
		return value;
	}
	public int minValue(BoardNode state, int alpha, int beta){
		if(currentState.checkWin())
			return heuristic;
		
		int value = Integer.MAX_VALUE;
		for (BoardNode c : children) {
			value = Math.min(value, maxValue(state,alpha,beta));
//			if(c.getHeuristic() < value)
//				value = getHeuristic();
			if(value >= alpha)
				return value;
			beta = Math.min(beta,value);
		}
		return value;
	}
	*/

	public int getValue(){
		return value;
	}
	
	public int getPlacedX(){
		return placedX;
	}
	public int getPlacedY(){
		return placedY;
	}
	
	public int getChosenPieceIndex(){
		return chosenPieceIndex;
	}
}
