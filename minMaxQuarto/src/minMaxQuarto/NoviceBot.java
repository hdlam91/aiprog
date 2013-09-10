package minMaxQuarto;

public class NoviceBot extends Bot{

	private Piece[][] internalBoard;

	public NoviceBot(Board board) {
		super(board);
		this.name = "novice";
		internalBoard = new Piece[4][4]; 
	}

	
	private void updateBoard(){
		for (int i = 0; i < internalBoard.length; i++) {
			for (int j = 0; j < internalBoard[0].length; j++) {
				internalBoard[i][j] = b.getPieceAtBoard(j, i);
			}
		}
	}
	
	@Override
	public String getName() {
		return this.name;
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
		updateBoard();
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void choseWheretoPlacePiece(int index) {
		updateBoard();
		// TODO Auto-generated method stub
		
	}

}
