package minMaxQuarto;

import minMaxQuarto.Enums.*;

public class Board {
	private Piece board[][];
	private Piece remaining[];
	private int counter;
	
	public Board(){
		this.board = new Piece[4][4];
		this.remaining = new Piece[16];
		this.counter = 0;
		
		populateRemaining();
	}
	
	public void populateRemaining(){
		boolean hole = false;
		Size size = Size.SMALL;
		Shape shape = Shape.SQUARE;
		Colour colour = Colour.RED;
		
		int length = remaining.length;
		
		for (int i = 0; i < length; i++) {
			
			if(i>=length/2)
				colour = Colour.BLUE;
			
			if(i%8<4)
				shape = Shape.SQUARE;
			else
				shape = Shape.ROUND;
			
			if((i%4)%2==0)
				size = (size==Size.BIG?Size.SMALL:Size.BIG);
			
			hole = !hole;
			
			remaining[i] = new Piece(hole,size,shape,colour);
		}
	}
	
	
	public Piece getPieceAtBoard(int x, int y){
		return board[y][x];		
	}
	
	public Piece getPieceFromRemaining(int index){
		return remaining[index];
	}
	
	public void removeFromRemaining(int index){
		this.remaining[index] = null;
	}
	
	public boolean placePieceOnBoard(int x, int y, Piece piece){
		if(this.board[y][x]==null){
			this.board[y][x] = piece;
			removeFromRemaining(x+y*4);
			counter++;
			return true;
		}
		else
			return false;
	}
	
	public boolean usedAllPieces(){
		return counter==16;
	}
	
	
	/**
	 * example shiet
	 */
	private void placeOnBoard(){
		int x=0, y = 0;
		for (int i = 0; i < remaining.length; i++) {
			if(x==4){
				x = 0; y++;
			}
			
			placePieceOnBoard(x, y, getPieceFromRemaining(i));
			x++;
		}
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(getPieceAtBoard(j, i)==null)
					buffer.append(" . ");
				else
					buffer.append(getPieceAtBoard(j, i));
				
				if(j<board[0].length-1)
					buffer.append("\t");
			}
			buffer.append('\n');
		}
		
		buffer.append("\n");
		buffer.append("Remaining:\n");
		
		for (int i = 0; i < remaining.length; i++) {
			if(getPieceFromRemaining(i)!=null){
				buffer.append(i+":" +getPieceFromRemaining(i));
				buffer.append("  ");
			}
		}
		
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		board.placeOnBoard();
		System.out.println(board);
		System.out.println(board.usedAllPieces());
	}	
}
