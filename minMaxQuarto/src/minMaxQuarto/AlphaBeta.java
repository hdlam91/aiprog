package minMaxQuarto;

import java.util.ArrayList;
import java.util.List;

public class AlphaBeta {
	private int maxDepth; //givenPieceIndex; 
	private Board tempBoard;
	
	public AlphaBeta(/*int givenPieceIndex, */int maxDepth, Board board){
		//stuff
//		this.givenPieceIndex = givenPieceIndex;
		this.maxDepth = maxDepth;
		this.setTempBoard(new Board(board));
	}
	
	public State alphabeta(Board internalBoard, int depth, int alpha, int beta, boolean maximizingPlayer, int givenPieceIndex) {
//		System.out.println(internalBoard);
//		System.out.println("\na: "+ alpha + " b: " + beta + " depth: "+ depth);
		State bestState = new State();
		List<State> bestStates = new ArrayList<State>();
		if (depth == maxDepth || internalBoard.checkWin()){
			bestState.setValue(Heuristic.getHeuristic(internalBoard,depth));
//			System.out.println("Leaf or win: " + bestState.getValue() + " : " + internalBoard.checkWin());
			return bestState;
		}
		if (maximizingPlayer) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(internalBoard.getPieceFromRemaining(givenPieceIndex)!=null){
						Piece givenPiece = internalBoard.getPieceFromRemaining(givenPieceIndex);
						if(internalBoard.placePieceOnBoard(j, i, givenPiece, givenPieceIndex)){
							for (int k = 0; k < 16; k++) {
								if(internalBoard.getPieceFromRemaining(k)!=null){
//									System.out.println("k: " + k + " x, y " + j + " " + i);
									State nextState = alphabeta(internalBoard, depth+1, alpha, beta, false, k);
//									System.out.print("root: " + "x, y " + j +" " +i + " returned value: " + nextState.getValue());
									alpha = Math.max(alpha, nextState.getValue());
									if(nextState.getValue()>bestState.getValue()){
										bestStates.clear();
//										System.out.print(" more than "+ bestState.getValue());
										bestState.setValue(alpha);
										bestState.setBestX(j);
										bestState.setBestY(i);
										bestState.setBestNextPiece(k);
										bestStates.add(bestState);
									}
									else if(nextState.getValue()==bestState.getValue()){
//										System.out.print(" equal to "+ bestState.getValue());
										State addState = new State();
										addState.setValue(alpha);
										addState.setBestX(j);
										addState.setBestY(i);
										addState.setBestNextPiece(k);
										bestStates.add(addState);
									}
//									System.out.println("\n" + "root x, y :" + bestState.getBestX() + " " + bestState.getBestY() + " p: " + bestState.getBestNextPiece() + " b: " + bestState.getValue() + " size of beststates: " + bestStates.size());
									if (beta <= alpha) {
										internalBoard.removePieceOnBoard(j, i);
										internalBoard.addPieceToRemaining(givenPiece, givenPieceIndex);
										return bestStates.get((int)Math.random()*bestStates.size());
									}
								}
								if(k == 15 && bestStates.size() == 0)
									bestStates.add(alphabeta(internalBoard, maxDepth, alpha, beta, false, givenPieceIndex));
//								System.out.println("PieceIndex k:" + k);
							}
							internalBoard.removePieceOnBoard(j, i);
							internalBoard.addPieceToRemaining(givenPiece, givenPieceIndex);
						}
//						System.out.println(internalBoard);
					}
				}
			}
			return bestStates.get((int)Math.random()*bestStates.size());
		}
		else{
			bestState.setValue(500);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(internalBoard.getPieceFromRemaining(givenPieceIndex)!=null){
						Piece givenPiece = internalBoard.getPieceFromRemaining(givenPieceIndex);
						if(internalBoard.placePieceOnBoard(j, i, givenPiece, givenPieceIndex)){
							for (int k = 0; k < 16; k++) {
								if(internalBoard.getPieceFromRemaining(k)!=null){
//									System.out.println("hai");
									State nextState = alphabeta(internalBoard, depth+1, alpha, beta, true,k);
//									System.out.print("x, y " + j +" " +i + " p: " + k +" returned value: " + nextState.getValue());
									beta = Math.min(beta, nextState.getValue());
									if(nextState.getValue()<bestState.getValue()){
//										System.out.print(" less than "+ bestState.getValue());
										bestStates.clear();
										bestState.setValue(beta);
										bestState.setBestX(j);
										bestState.setBestY(i);
										bestState.setBestNextPiece(k);
										bestStates.add(bestState);
									}
									else if(nextState.getValue()==bestState.getValue()){
										State addState = new State();
//										System.out.println(" equal to " + bestState.getValue());
										addState.setValue(beta);
										addState.setBestX(j);
										addState.setBestY(i);
										addState.setBestNextPiece(k);
										bestStates.add(addState);
									}
//									System.out.println("\n" + "x, y :" + bestState.getBestX() + " " + bestState.getBestY() + " p: " + bestState.getBestNextPiece() + " best: " + bestState.getValue() + " alpha: " + alpha + " beta: " + beta + " size: " + bestStates.size());
									if (beta <= alpha) {
										internalBoard.removePieceOnBoard(j, i);
										internalBoard.addPieceToRemaining(givenPiece, givenPieceIndex);
										return bestStates.get((int)Math.random()*bestStates.size());
									}
								}
	//								System.out.println(internalBoard);
								if(k == 15 && bestStates.size() == 0)
									bestStates.add(alphabeta(internalBoard, maxDepth, alpha, beta, true, givenPieceIndex));
							}
							internalBoard.removePieceOnBoard(j, i);
							internalBoard.addPieceToRemaining(givenPiece, givenPieceIndex);
							
						}
					}
				}
			}
			return bestStates.get((int)Math.random()*bestStates.size());
		}
	}
	
	public Board getTempBoard() {
		return tempBoard;
	}

	public void setTempBoard(Board tempBoard) {
		this.tempBoard = tempBoard;
	}

	//Old attempt on alpha-beta pruning
/*
	public State alphabeta(Board internalBoard, int depth, int alpha, int beta, boolean maximizingPlayer) {
//		System.out.println(internalBoard);
		System.out.println("\na: "+ alpha + " b: " + beta + " depth: "+ depth);
		State bestState = new State();
		List<State> bestStates = new ArrayList<State>();
		if (depth == maxDepth || internalBoard.checkWin()){
			bestState.setValue(Heuristic.getHeuristic(internalBoard,depth));
			System.out.println("Leaf or win: " + bestState.getValue() + " : " + internalBoard.checkWin());
			return bestState;
		}
		if (maximizingPlayer) {
			if(depth == 0){
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						Piece givenPiece = internalBoard.getPieceFromRemaining(givenPieceIndex);
						if(internalBoard.placePieceOnBoard(j, i, givenPiece, givenPieceIndex)){
							State nextState = alphabeta(internalBoard, depth+1, alpha, beta, false);
							internalBoard.removePieceOnBoard(j, i);
							internalBoard.addPieceToRemaining(givenPiece, givenPieceIndex);
							System.out.print("root: " + "x, y " + j +" " +i + " returned value: " + nextState.getValue());
							alpha = Math.max(alpha, nextState.getValue());
							if(nextState.getValue()>bestState.getValue()){
								bestStates.clear();
								System.out.print(" more than "+ bestState.getValue());
								bestState.setValue(alpha);
								bestState.setBestX(j);
								bestState.setBestY(i);
								bestState.setBestNextPiece(nextState.getBestNextPiece());
								bestStates.add(bestState);
							}
							if(nextState.getValue()==bestState.getValue()){
								System.out.print(" equal to "+ bestState.getValue());
								State addState = new State();
								addState.setValue(alpha);
								addState.setBestX(j);
								addState.setBestY(i);
								addState.setBestNextPiece(nextState.getBestNextPiece());
								bestStates.add(bestState);
							}
							System.out.println("\n" + "root x, y :" + bestState.getBestX() + " " + bestState.getBestY() + " p: " + bestState.getBestNextPiece() + " b: " + bestState.getValue());
							if (beta <= alpha) {
								return bestStates.get((int)Math.random()*bestStates.size());
							}	
						}
					}
				}
				
			}
			else{
				for (int k = 0; k < 16; k++) {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 4; j++) {
							if(internalBoard.getPieceFromRemaining(k)!=null){
								Piece givenPiece = internalBoard.getPieceFromRemaining(k);
								if(internalBoard.placePieceOnBoard(j, i, givenPiece, k)){
									State nextState = alphabeta(internalBoard, depth+1, alpha, beta, false);
									internalBoard.removePieceOnBoard(j, i);
									internalBoard.addPieceToRemaining(givenPiece, k);
									alpha = Math.max(alpha, nextState.getValue());
									if(nextState.getValue()>bestState.getValue()){
										bestStates.clear();
										bestState.setValue(alpha);
										bestState.setBestX(j);
										bestState.setBestY(i);
										bestState.setBestNextPiece(k);
										bestStates.add(bestState);
									}
									if(nextState.getValue()==bestState.getValue()){
										State addState = new State();
										addState.setValue(alpha);
										addState.setBestX(j);
										addState.setBestY(i);
										addState.setBestNextPiece(k);
										bestStates.add(addState);
									}
									if (beta <= alpha) {
										return bestStates.get((int)Math.random()*bestStates.size());
									}
								}
							}
						}
					}
				}
			}
			return bestState;
		}
		else{
//			bestState.setValue(500);
			for (int k = 0; k < 16; k++) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if(internalBoard.getPieceFromRemaining(k)!=null){
							Piece givenPiece = internalBoard.getPieceFromRemaining(k);
							if(internalBoard.placePieceOnBoard(j, i, givenPiece, k)){
								State nextState = alphabeta(internalBoard, depth+1, alpha, beta, true);
								internalBoard.removePieceOnBoard(j, i);
								internalBoard.addPieceToRemaining(givenPiece, k);
								System.out.print("x, y " + j +" " +i + " p: " + k +" returned value: " + nextState.getValue());
								beta = Math.min(beta, nextState.getValue());
								if(nextState.getValue()>bestState.getValue()){
									System.out.print(" more than "+ bestState.getValue());
									bestStates.clear();
									bestState.setValue(beta);
									bestState.setBestX(j);
									bestState.setBestY(i);
									bestState.setBestNextPiece(k);
									bestStates.add(bestState);
								}
								if(nextState.getValue()==bestState.getValue()){
									State addState = new State();
									addState.setValue(alpha);
									addState.setBestX(j);
									addState.setBestY(i);
									addState.setBestNextPiece(k);
									bestStates.add(addState);
								}
								System.out.println("\n" + "x, y :" + bestState.getBestX() + " " + bestState.getBestY() + " p: " + bestState.getBestNextPiece() + " best: " + bestState.getValue() + " alpha: " + alpha + " beta: " + beta);
								if (beta <= alpha) {
									return bestStates.get((int)Math.random()*bestStates.size());
								}
//								System.out.println(internalBoard);
							}
						}
					}
				}
			}
			return bestState;
		}
	}
*/
}
