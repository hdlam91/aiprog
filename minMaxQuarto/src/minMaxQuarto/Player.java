package minMaxQuarto;

public class Player {
	private int type, depth, wincount;
	private Bot bot;
	
	public Player(){
		this.setDepth(-1);
		this.setType(-1);
		this.setWincount(0);
		this.setBot(null);
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

	public Bot getBot() {
		return bot;
	}

	public void setBot(Bot bot) {
		this.bot = bot;
	}
	
}
