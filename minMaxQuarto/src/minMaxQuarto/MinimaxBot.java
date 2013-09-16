package minMaxQuarto;

public class MinimaxBot extends NoviceBot{
	protected int depth;
	private int nextPiecetoGive;
//	BoardNode miniMaxTree;
	Board internalBoard;
	int numberOfMoves = 2;
	AlphaBeta minimax;
	
	public MinimaxBot(Board board, int depth) {
		super(board);
		this.depth = depth;
		this.name = "minimax_";
		minimax = null;
//		miniMaxTree = null;
		internalBoard = new Board(board);
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
	}

	@Override
	public int choosePiece() {
		if(minimax == null || b.piecePlayed() < numberOfMoves){
//			System.out.println("gave wrong piece");
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
			minimax = new AlphaBeta(/*index,*/depth,b);
			State nextState = minimax.alphabeta(minimax.getTempBoard(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true,index);
			this.nextPiecetoGive = nextState.getBestNextPiece();
			placePiece(nextState.getBestX(),nextState.getBestY(),index);
//			System.out.println(nextState.getValue() + "\nb: \n" + minimax.getTempBoard() + "p given: " + index + "p: " + nextPiecetoGive + "\n");
//			miniMaxTree = new BoardNode(b, depth, index);
//			BoardNode next = miniMaxTree.pickBestNode();
//			placePiece(next.getPlacedX(), next.getPlacedY(), index);
		}
	}

}
