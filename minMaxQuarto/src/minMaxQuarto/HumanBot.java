package minMaxQuarto;
import java.util.Scanner;



public class HumanBot extends Bot{

	Scanner in;
	
	public HumanBot(Board board) {
		super(board);
		this.name = "human";
		in = new Scanner(System.in);
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
		while(!b.placePieceOnBoard(x, y, b.getPieceFromRemaining(index),index)){
			System.out.println("choose a VALID X, and Y");
			
			
			String sX = in.next();
			String sY = in.next();
			
			try{
			    x = Integer.parseInt(sX);
			    y = Integer.parseInt(sY);
			}
			catch(NumberFormatException ex){
				System.out.println(sX + " or " + sY + " is not a valid number.");
			}
//			x = in.nextInt();
//			y = in.nextInt();
		}
	}

	@Override
	public int choosePiece() {
		System.out.println("choose piece:");
		int piece = -1;
		String sP = in.next();
		while(piece == -1){
			try{
			    piece = Integer.parseInt(sP);
			}
			catch(NumberFormatException ex){
				System.out.println(sP + " is not a valid number.");
				sP = in.next();
			}
		}
		while(b.getPieceFromRemaining(piece) == null){
			System.out.println("not a legal piece, choose again:");
			sP = in.next();
			try{
			    piece = Integer.parseInt(sP);
			}
			catch(NumberFormatException ex){
				System.out.println(sP + " is not a valid number.");
			}
		}
		return piece;
	}


	@Override
	public void choseWheretoPlacePiece(int index) {
		System.out.println("choose an X, and Y");
		int x = -1;
		int y = -1;
		
		String sX = in.next();
		String sY = in.next();
		try{
		    x = Integer.parseInt(sX);
		    y = Integer.parseInt(sY);
		}
		catch(NumberFormatException ex){
		    System.out.println(sX + " or " + sY + " is not a valid number.");
		}
		
		placePiece(x, y, index);
	}

}
