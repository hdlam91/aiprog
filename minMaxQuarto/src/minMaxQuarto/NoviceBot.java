package minMaxQuarto;

public class NoviceBot extends Bot{

	private Board internalBoard;

	public NoviceBot(Board board) {
		super(board);
		this.name = "novice";
		internalBoard = new Board(board);
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
	
		while(!b.placePieceOnBoard(x, y, b.getPieceFromRemaining(index),index)){
			x = (int)(Math.random()*4);
			y = (int)(Math.random()*4);
		}
	}

	@Override
	public int choosePiece() {
		int random = (int)(Math.random()*16);
		while(b.getPieceFromRemaining(random) == null){
			random = (int)(Math.random()*16);
		}
		return random;
	}


	@Override
	public void choseWheretoPlacePiece(int index) {
		internalBoard = new Board(b);
		Piece pieceOnHand = internalBoard.getPieceFromRemaining(index);
//		for (int i = 0; i < 4; i++) {
//			if(internalBoard.getColCounterAt(i) == 3){
//				
//			}
//		}
//			
		
		
				for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				
				if(internalBoard.placePieceOnBoard(i, j, pieceOnHand, index)){
					if(internalBoard.checkWin()){
						placePiece(i, j, index);
						return;
					}
					else{
						internalBoard.removePieceOnBoard(i, j);
						internalBoard.addPieceToRemaining(pieceOnHand, index);
					}
					
				}
			}
		}
		int randomX = (int)(Math.random()*4);
		int randomY = (int)(Math.random()*4);
		placePiece(randomX, randomY, index);
		
		
		
	}

}
