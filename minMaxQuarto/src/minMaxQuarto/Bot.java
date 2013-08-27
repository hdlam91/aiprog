package minMaxQuarto;

public abstract class Bot {
	protected Board b;
	
	public Bot(Board board) {
		this.b = board;
	}
	
	public abstract void placePiece(int x, int y, int index);
	public abstract int choosePiece();
	
}
