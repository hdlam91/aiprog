package minMaxQuarto;

public abstract class Bot {
	protected Board b;
	protected String name;
	protected int networkPosition;
	
	public Bot(Board board) {
		this.b = board;
	}
	
	public abstract void placePiece(int x, int y, int index);
	public abstract void choseWheretoPlacePiece(int index);
	public abstract int choosePiece();
	public abstract String getName();
	public abstract String toString();

	public int getNetworkPosition() {
		return networkPosition;
	}
	
}
