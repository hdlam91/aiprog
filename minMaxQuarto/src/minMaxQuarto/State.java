package minMaxQuarto;

public class State {
	private int bestX, bestY, bestNextPiece, value;
	
	public State(){
		this.setBestX(-1);
		this.setBestY(-1);
		this.setBestNextPiece(-1);
		this.value = -500;
	}

	public int getBestX() {
		return bestX;
	}

	public void setBestX(int bestX) {
		this.bestX = bestX;
	}

	public int getBestY() {
		return bestY;
	}

	public void setBestY(int bestY) {
		this.bestY = bestY;
	}

	public int getBestNextPiece() {
		return bestNextPiece;
	}

	public void setBestNextPiece(int bestNextPiece) {
		this.bestNextPiece = bestNextPiece;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
