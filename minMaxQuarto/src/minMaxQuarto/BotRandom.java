package minMaxQuarto;

public class BotRandom extends Bot{

	@Override
	public void placePiece(int x, int y, int index) {
		int randomX = (int)(Math.random()*4);
		int randomY = (int)(Math.random()*4);
		while(!b.placePieceOnBoard(randomX, randomY, b.getPieceFromRemaining(index))){
			randomX = (int)(Math.random()*4);
			randomY = (int)(Math.random()*4);
			
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

}
