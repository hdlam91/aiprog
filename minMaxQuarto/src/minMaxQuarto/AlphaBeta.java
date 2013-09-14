package minMaxQuarto;

public class AlphaBeta {
	private int maxDepth, givenPieceIndex; 
	private Board tempBoard;
	
	public AlphaBeta(int givenPieceIndex, int maxDepth, Board board){
		//stuff
		this.givenPieceIndex = givenPieceIndex;
		this.maxDepth = maxDepth;
		this.setTempBoard(new Board(board));
	}
	
	
	public State alphabeta(Board internalBoard, int depth, int alpha, int beta, boolean maximizingPlayer) {
		// System.out.println(state.currentState);
//		System.out.println(internalBoard);
		State bestState = new State();
		if (depth == maxDepth || internalBoard.checkWin()){
			bestState.setValue(Heuristic.getHeuristic(internalBoard,depth));
			return bestState;
		}
		if (maximizingPlayer) {
			if(depth == 0){
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						Piece givenPiece = internalBoard.getPieceFromRemaining(givenPieceIndex);
						if(internalBoard.placePieceOnBoard(j, i, givenPiece, givenPieceIndex)){
							State nextState = alphabeta(internalBoard, depth+1, alpha, beta, false);
							if(nextState.getValue()>bestState.getValue()){
								alpha = Math.max(alpha, nextState.getValue());
								bestState.setValue(alpha);
								bestState.setBestX(j);
								bestState.setBestY(i);
								bestState.setBestNextPiece(nextState.getBestNextPiece());
							}
							internalBoard.removePieceOnBoard(j, i);
							internalBoard.addPieceToRemaining(givenPiece, givenPieceIndex);
							if (beta <= alpha) {
								return bestState;
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
									if(nextState.getValue()<bestState.getValue()){
										alpha = Math.max(alpha, nextState.getValue());
										bestState.setValue(alpha);
										bestState.setBestX(j);
										bestState.setBestY(i);
										bestState.setBestNextPiece(k);
									}
									internalBoard.removePieceOnBoard(j, i);
									internalBoard.addPieceToRemaining(givenPiece, k);
									if (beta <= alpha) {
										return bestState;
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
			for (int k = 0; k < 16; k++) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if(internalBoard.getPieceFromRemaining(k)!=null){
							Piece givenPiece = internalBoard.getPieceFromRemaining(k);
							if(internalBoard.placePieceOnBoard(j, i, givenPiece, k)){
								State nextState = alphabeta(internalBoard, depth+1, alpha, beta, true);
								if(nextState.getValue()>bestState.getValue()){
									beta = Math.min(beta, nextState.getValue());
									bestState.setValue(beta);
									bestState.setBestX(j);
									bestState.setBestY(i);
									bestState.setBestNextPiece(k);
								}
//								System.out.println(internalBoard);
								internalBoard.removePieceOnBoard(j, i);
								internalBoard.addPieceToRemaining(givenPiece, k);
								if (beta <= alpha) {
									return bestState;
								}
							}
						}
					}
				}
			}
			return bestState;
		}
	}


	public Board getTempBoard() {
		return tempBoard;
	}


	public void setTempBoard(Board tempBoard) {
		this.tempBoard = tempBoard;
	}
}
