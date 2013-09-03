package minMaxQuarto;

public class Player {
	private int type, depth, wincount;
	
	public Player(){
		this.setDepth(2);
		this.setType(-1);
		this.setWincount(0);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getWincount() {
		return wincount;
	}

	public void setWincount(int wincount) {
		this.wincount = wincount;
	}
	
	
}
