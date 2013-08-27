package minMaxQuarto;

public abstract class Bot {
	protected Board b;
	
	public Bot() {
	}
	
	
	public abstract void placePiece(int x, int y, Piece piece);
	public abstract void choosePiece();
	
}
