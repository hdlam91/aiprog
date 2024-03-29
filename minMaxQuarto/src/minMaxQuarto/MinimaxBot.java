package minMaxQuarto;

/**
 * The minimax bot that calls on alphabeta with a given depth 
 * @author Eivind
 *
 */

public class MinimaxBot extends NoviceBot{
	protected int depth;
	private int nextPiecetoGive;
	Board internalBoard;
	int numberOfMoves = 5;
	AlphaBeta minimax;
	
	public MinimaxBot(Board board, int depth) {
		super(board);
		this.depth = depth;
		this.name = "minimax_";
		minimax = null;
		internalBoard = new Board(board);
		networkPosition = -1;
		
	}

	@Override
	public String getName() {
		return this.name + this.depth;
	}
	
	public String toString(){
		return getName();
	}
	
	@Override
	public void placePiece(int x, int y, int index) {
		while(!b.placePieceOnBoard(x, y, b.getPieceFromRemaining(index),index)){
			x = (int)(Math.random()*4);
			y = (int)(Math.random()*4);
		}
		networkPosition = 4*y+x;
	}
	
	

	@Override
	public int choosePiece() {
		if(minimax == null || b.piecePlayed() < numberOfMoves){
			return super.choosePiece();
		}
		else
			return nextPiecetoGive;
	}

	@Override
	public void choseWheretoPlacePiece(int index) {
		if(b.piecePlayed()<numberOfMoves){
			super.choseWheretoPlacePiece(index);
		}
		else{
			minimax = new AlphaBeta(depth,b);
			State nextState = minimax.alphabeta(minimax.getTempBoard(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true,index);
			this.nextPiecetoGive = nextState.getBestNextPiece();
			placePiece(nextState.getBestX(),nextState.getBestY(),index);
		}
	}
}
