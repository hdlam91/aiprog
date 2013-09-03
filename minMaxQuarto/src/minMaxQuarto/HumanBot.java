package minMaxQuarto;

public class HumanBot extends Bot{

	public HumanBot(Board board) {
		super(board);
		this.name = "human";
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String getName() {
		return this.name;
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
