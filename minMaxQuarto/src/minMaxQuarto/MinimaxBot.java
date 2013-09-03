package minMaxQuarto;

public class MinimaxBot extends Bot{
	protected int depth;
	
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
		// TODO Auto-generated method stub
		
	}

}
