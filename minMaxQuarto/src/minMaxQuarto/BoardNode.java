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

	}
	

	
	public void addChildren(boolean inputPiece){
		//this method should add all the children that can be added.
//		System.out.println("inputPieceBool: " + inputPiece );
		if(inputPiece){
			Piece currentPiece = currentState.getPieceFromRemaining(chosenPieceIndex);
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if(currentState.placePieceOnBoard(x, y, currentState.getPieceFromRemaining(chosenPieceIndex), chosenPieceIndex)){
//						System.out.println("depth root: " + depth + " x: " + x + " y: " + y);
						BoardNode child = new BoardNode(currentState, depth+1,maxDepth, x,y,chosenPieceIndex);
						children.add(child);
						currentState.removePieceOnBoard(x, y);
						currentState.addPieceToRemaining(currentPiece, chosenPieceIndex);
//						System.out.println("children added" + children.size());
					}
				}
			}
		}
		else{
			for (int i = 0; i < 16; i++) {
				piece:
				if(currentState.getPieceFromRemaining(i) != null){
					Piece currentPiece = currentState.getPieceFromRemaining(i);
					for (int y = 0; y < 4; y++) {
						for (int x = 0; x < 4; x++) {						
							if(currentState.placePieceOnBoard(x, y, currentPiece, i)){
//								System.out.println("depth: " + depth + " x: " + x + " y: " + y);
								BoardNode child = new BoardNode(currentState, depth+1,maxDepth, x,y,i);
								children.add(child);
//								System.out.println(currentState);
								currentState.removePieceOnBoard(x, y);
//								System.out.println("children added" + children.size());
							}
						}
					}
					currentState.addPieceToRemaining(currentPiece, i);
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
		else
			heuristic = 0;
	}
	
	public int getHeuristic(){
		updateHeuristic();
		return heuristic;
	}
	
	public BoardNode pickBestNode(){
//		for (int i = 0; i < children.size(); i++) {
//			System.out.println("child root:" + i + " has: "+ children.get(i).children.size());
//			for (int j = 0; j < children.get(i).children.size(); j++) {
//				System.out.println("child :" + j + " has: "+ children.get(i).children.get(j).children.size());
//			}
//		}
		alphabeta(this, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		BoardNode best = children.get(0);
		for (BoardNode child: children) {
			if(child.traversed){
				if(child.getValue() > best.getValue()){
					best = child;
				}
			}
		}
		bestChild = best;
		return best;
	}
	
	
	public BoardNode pickNextBest(){
		BoardNode currentBest = bestChild;
		System.out.println("WORST:" + currentBest.children.size());
		BoardNode nextBest = currentBest.children.get(0);
		for (BoardNode child: currentBest.children) {
			if(child.traversed){
//				System.out.println(child.traversed + " " + child.getValue());
				if(child.getValue() > nextBest.getValue()){
					nextBest = child;
				}
			}	
		}
		return nextBest;
	}
	public BoardNode pickWorstNode(){
		BoardNode currentBest = bestChild;
		System.out.println("WORST:" + currentBest.children.size());
		BoardNode worst = currentBest.children.get(0);
		for (BoardNode child: currentBest.children) {
			if(child.traversed){
//				System.out.println(child.traversed + " " + child.getValue());
				if(child.getValue() < worst.getValue()){
					worst = child;
				}
			}	
		}
		return worst;
	}
	
	public int alphabeta(BoardNode state, int depth, int alpha, int beta, boolean maximizingPlayer){
//		System.out.println(state.currentState);
		if(depth == maxDepth|| state.currentState.checkWin())
			return getHeuristic();
		if(maximizingPlayer){
			for (BoardNode child: state.children) {
				alpha = Math.max(alpha, alphabeta(child, depth++, alpha, beta, !maximizingPlayer));
				child.value = alpha;
				child.traversed = true;
				if(beta <= alpha){
					break;
				}
			}
			return alpha;
		}
		else{
			for (BoardNode child: state.children) {
				beta = Math.min(beta, alphabeta(child, depth++, alpha, beta, !maximizingPlayer));
				child.value = beta;
				child.traversed = true;
				if(beta <= alpha){
					break;
				}
			}
			return beta;
		}
	}
	{
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
	*/}

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
