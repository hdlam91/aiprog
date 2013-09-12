package minMaxQuarto;

public class MinimaxBot extends Bot{
	protected int depth;
	BoardNode miniMaxTree;
	
	public MinimaxBot(Board board, int depth) {
		super(board);
		this.depth = depth;
		this.name = "minimax ";
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int choosePiece() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void choseWheretoPlacePiece(int index) {
		miniMaxTree = new BoardNode(b, depth, index);
		BoardNode next = miniMaxTree.pickBestNode();
		placePiece(next.getPlacedX(), next.getPlacedY(), index);
		// TODO Auto-generated method stub
		
	}

}
