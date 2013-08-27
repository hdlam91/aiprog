package minMaxQuarto;

public abstract class Bot {
	protected Board b;
	
	public Bot() {
	}
	
	
	public abstract void placePiece(int x, int y, int index);
	public abstract int choosePiece();
	
}
