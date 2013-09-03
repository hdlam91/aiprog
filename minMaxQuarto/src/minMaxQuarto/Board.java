package minMaxQuarto;

import minMaxQuarto.Enums.*;

public class Board {
	private Piece board[][];
	private Piece remaining[];
	private int counter;
	private boolean winState;
	private int[] rowCounter;
	private int[] colCounter;
	private int forwardDiagonal;
	private int backwardDiagonal;
	
	public Board(){
		this.board = new Piece[4][4];
		this.remaining = new Piece[16];
		this.counter = 0;
		this.winState = false;
		this.rowCounter = new int[4];
		this.colCounter = new int[4];
		this.forwardDiagonal = 0;
		this.backwardDiagonal = 0;
		populateRemaining();
	}
	
	//builds pieces and puts them into an array.
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
			rowCounter[y]++;
			colCounter[x]++;
			if(x==y)
				backwardDiagonal+=1;
			else if(x == 0 && y == 3 || x == 1 && y == 2 || x == 2 && y == 1 || x == 3 && y == 0){
				forwardDiagonal += 1;
			}
			
			return true;
		}
		else
			return false;
	}
	
	public boolean usedAllPieces(){
		return counter==16;
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
	
	public void checkWin(){
		if(checkCol() || checkRows() || checkDiagonal())
			winState = true;
		
	}
	
	
	
	private boolean checkRows(){
		int sameHoles = 0;
		int sameSize = 0;
		int sameCol = 0;
		int sameShape = 0;
		for (int i = 0; i < rowCounter.length; i++) {
			if(rowCounter[i] == 4){
				sameHoles =  (getPieceAtBoard(0, i).isHole()?1:0) + (getPieceAtBoard(1, i).isHole()?1:0) + (getPieceAtBoard(2, i).isHole()?1:0) + (getPieceAtBoard(3, i).isHole()?1:0);
				sameCol = getPieceAtBoard(0, i).getCol().ordinal() +getPieceAtBoard(1, i).getCol().ordinal() +getPieceAtBoard(2, i).getCol().ordinal() +getPieceAtBoard(3, i).getCol().ordinal();	
				sameSize = getPieceAtBoard(0, i).getSize().ordinal() +getPieceAtBoard(1, i).getSize().ordinal() +getPieceAtBoard(2, i).getSize().ordinal() +getPieceAtBoard(3, i).getSize().ordinal();
				sameShape = getPieceAtBoard(0, i).getShape().ordinal() +getPieceAtBoard(1, i).getShape().ordinal() +getPieceAtBoard(2, i).getShape().ordinal() +getPieceAtBoard(3, i).getShape().ordinal();
				
				if(sameCol == 4 || sameCol == 0){
					return true;
				}
				if(sameShape == 4 || sameShape == 0){
					return true;
				}
				if(sameSize == 4 || sameSize == 0){
					return true;
				}
				if(sameHoles == 4 || sameHoles == 0){
					return true;
				}
			}
		}
		
		return false;
	}
	private boolean checkCol(){
		int sameHoles = 0;
		int sameSize = 0;
		int sameCol = 0;
		int sameShape = 0;
		for (int i = 0; i < colCounter.length; i++) {
			if(colCounter[i] == 4){
				sameHoles =  (getPieceAtBoard(i, 0).isHole()?1:0) + (getPieceAtBoard(i,1).isHole()?1:0) + (getPieceAtBoard( i,2).isHole()?1:0) + (getPieceAtBoard(i,3).isHole()?1:0);
				sameCol = getPieceAtBoard(i, 0).getCol().ordinal() +getPieceAtBoard(i,1).getCol().ordinal() +getPieceAtBoard(i,2).getCol().ordinal() +getPieceAtBoard(i,3).getCol().ordinal();	
				sameSize = getPieceAtBoard(i, 0).getSize().ordinal() +getPieceAtBoard(i,1).getSize().ordinal() +getPieceAtBoard( i,2).getSize().ordinal() +getPieceAtBoard(i,3).getSize().ordinal();
				sameShape = getPieceAtBoard(i, 0).getShape().ordinal() +getPieceAtBoard(i,1).getShape().ordinal() +getPieceAtBoard( i,2).getShape().ordinal() +getPieceAtBoard(i,3).getShape().ordinal();
				
				if(sameCol == 4 || sameCol == 0){
					return true;
				}
				if(sameShape == 4 || sameShape == 0){
					return true;
				}
				if(sameSize == 4 || sameSize == 0){
					return true;
				}
				if(sameHoles == 4 || sameHoles == 0){
					return true;
				}
			}
		}
		
		return false;
	}
	private boolean checkDiagonal(){
		int sameHoles = 0;
		int sameSize = 0;
		int sameCol = 0;
		int sameShape = 0;
		if(backwardDiagonal == 4){
			sameHoles =  (getPieceAtBoard(0, 0).isHole()?1:0) + (getPieceAtBoard(1,1).isHole()?1:0) + (getPieceAtBoard( 2,2).isHole()?1:0) + (getPieceAtBoard(3,3).isHole()?1:0);
			sameCol = getPieceAtBoard(0,0).getCol().ordinal() +getPieceAtBoard(1,1).getCol().ordinal() +getPieceAtBoard(2,2).getCol().ordinal() +getPieceAtBoard(3,3).getCol().ordinal();	
			sameSize = getPieceAtBoard(0,0).getSize().ordinal() +getPieceAtBoard(1,1).getSize().ordinal() +getPieceAtBoard( 2,2).getSize().ordinal() +getPieceAtBoard(3,3).getSize().ordinal();
			sameShape = getPieceAtBoard(0,0).getShape().ordinal() +getPieceAtBoard(1,1).getShape().ordinal() +getPieceAtBoard( 2,2).getShape().ordinal() +getPieceAtBoard(3,3).getShape().ordinal();
			
			if(sameCol == 4 || sameCol == 0){
				return true;
			}
			if(sameShape == 4 || sameShape == 0){
				return true;
			}
			if(sameSize == 4 || sameSize == 0){
				return true;
			}
			if(sameHoles == 4 || sameHoles == 0){
				return true;
			}
		}
		
		
		if(forwardDiagonal == 4){
			sameHoles =  (getPieceAtBoard(0, 3).isHole()?1:0) + (getPieceAtBoard(1,2).isHole()?1:0) + (getPieceAtBoard( 2,1).isHole()?1:0) + (getPieceAtBoard(3,0).isHole()?1:0);
			sameCol = getPieceAtBoard(0,3).getCol().ordinal() +getPieceAtBoard(1,2).getCol().ordinal() +getPieceAtBoard(2,1).getCol().ordinal() +getPieceAtBoard(3,0).getCol().ordinal();	
			sameSize = getPieceAtBoard(0,3).getSize().ordinal() +getPieceAtBoard(1,2).getSize().ordinal() +getPieceAtBoard( 2,1).getSize().ordinal() +getPieceAtBoard(3,0).getSize().ordinal();
			sameShape = getPieceAtBoard(0,3).getShape().ordinal() +getPieceAtBoard(1,2).getShape().ordinal() +getPieceAtBoard( 2,1).getShape().ordinal() +getPieceAtBoard(3,0).getShape().ordinal();
			
			if(sameCol == 4 || sameCol == 0){
				return true;
			}
			if(sameShape == 4 || sameShape == 0){
				return true;
			}
			if(sameSize == 4 || sameSize == 0){
				return true;
			}
			if(sameHoles == 4 || sameHoles == 0){
				return true;
			}
		}
		
		
		return false;
	}
	
	
	
	/**
	 * will prob not be used, only for test.
	 * example shiet
	 */
	private void placeOnBoard(){
		int x=0, y = 0;
		for (int i = 0; i < remaining.length-12; i++) {
			if(x==4){
				x = 0; y++;
			}
			
			placePieceOnBoard(x, y, getPieceFromRemaining(i));
			x++;
		}
	}
	public int getCol(int i){
		return colCounter[i];
	}
	
	
	
	
	/**
	 * will prob not be used, only for test.
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board();
		board.placeOnBoard();
		System.out.println(board);

		System.out.println(board.usedAllPieces());
		

		System.out.println("used all pieces: " + board.usedAllPieces());
		for (int i = 0; i < 4; i++) {
			System.out.print(board.getCol(i));
		}
		System.out.println();
		for (int i = 0; i < 4; i++) {
			System.out.println(board.rowCounter[i]);
		}
		board.checkWin();
		System.out.println(board.winState);
	
	}
	

}
