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
	
	@Override
	public void placePiece(int x, int y, int index) {
		while(!b.placePieceOnBoard(x, y, b.getPieceFromRemaining(index),index)){
			System.out.println("choose an X, and Y");
			int X = in.nextInt();
			int Y = in.nextInt();
		}
	}

	@Override
	public int choosePiece() {
		System.out.println(" choose piece:");
		int piece = in.nextInt();
		while(b.getPieceFromRemaining(piece) == null){
			System.out.println("\nnot a legal piece, choose again:\n");
			piece = in.nextInt();
		}
		return piece;
	}


	@Override
	public void choseWheretoPlacePiece(int index) {
		System.out.println("choose an X, and Y");
		int X = in.nextInt();
		int Y = in.nextInt();
		placePiece(X, Y, index);
	}



	

}
