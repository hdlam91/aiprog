package minMaxQuarto;

public class MinimaxBot extends Bot{
	protected int depth;
	BoardNode miniMaxTree;
	
	
	public MinimaxBot(Board board, int depth) {
		super(board);
		this.depth = depth;
		this.name = "minimax ";
		miniMaxTree = null;
		// TODO Auto-generated constructor stub
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
		b.placePieceOnBoard(x, y, b.getPieceFromRemaining(index), index);
	}

	@Override
	public int choosePiece() {
		if(miniMaxTree == null){
			int random = (int)(Math.random()*16);
			while(b.getPieceFromRemaining(random) == null){
				random = (int)(Math.random()*16);
			}
			return random;
		}
		//int pieceIndex = 
		return miniMaxTree.pickWorstNode().getChosenPieceIndex();
		
	}

	@Override
	public void choseWheretoPlacePiece(int index) {
		miniMaxTree = new BoardNode(b, depth, index);
		BoardNode next = miniMaxTree.pickBestNode();
		placePiece(next.getPlacedX(), next.getPlacedY(), index);
		// TODO Auto-generated method stub
		
	}

}
