package minMaxQuarto;

public class RandomBot extends Bot{

	public RandomBot(Board board) {
		super(board);
		this.name = "random";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName(){
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
		networkPosition = 4*y+x;
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
		// TODO Auto-generated method stub
		int randomX = (int)(Math.random()*4);
		int randomY = (int)(Math.random()*4);
		placePiece(randomX, randomY, index);
	}

}
