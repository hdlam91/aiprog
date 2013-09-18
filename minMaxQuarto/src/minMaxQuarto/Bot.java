package minMaxQuarto;

/**
 * Abstract class to keep track of which methods each bot is required to have in order to participate in a game of quarto 
 * @author Eivind
 *
 */

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
