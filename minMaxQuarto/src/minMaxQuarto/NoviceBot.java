package minMaxQuarto;

public class NoviceBot extends Bot{

	public NoviceBot(Board board) {
		super(board);
		this.name = "novice";
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String getName() {
		return this.name;
	}
	
	public String toString(){
		return getName();
	}
	
	@Override
	public void placePiece(int x, int y, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int choosePiece() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void choseWheretoPlacePiece(int index) {
		// TODO Auto-generated method stub
		
	}

}
