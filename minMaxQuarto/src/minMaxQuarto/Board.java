package minMaxQuarto;

import minMaxQuarto.Enums.*;

public class Board{
	private Piece board[][];
	private Piece remaining[];
	private int counter;
	private boolean winState;
	private int[] rowCounter;
	private int[] colCounter;
	private int forwardDiagonal;
	private int backwardDiagonal;
	private boolean isInternal;
	
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
		isInternal = false;
	}
	
	public Board(Board originalBoard) {
		this.board = new Piece[4][4];
		this.remaining = new Piece[16];
		this.rowCounter = new int[4];
		this.colCounter = new int[4];
		this.forwardDiagonal = originalBoard.forwardDiagonal;
		this.backwardDiagonal = originalBoard.backwardDiagonal;
		this.counter = originalBoard.counter;
		
		for (int i = 0; i < board.length; i++) {
			this.board[i] = originalBoard.board[i].clone();
		}
		this.remaining = originalBoard.remaining.clone();
		this.rowCounter = originalBoard.rowCounter.clone();
		this.colCounter = originalBoard.colCounter.clone();
		isInternal = true;
	}
	
	//builds pieces and puts them into an array.
	public void populateRemaining(){
		boolean hole = true;
		Size size = Size.BIG;
		Shape shape = Shape.SQUARE;
		Colour colour = Colour.RED;
		
		int length = remaining.length;
		
		for (int i = 0; i < length; i++) {
			
			if(i>=length/2)
				colour = Colour.BLUE;
			
			if(i%8<4)
				shape = Shape.ROUND;
			else
				shape = Shape.SQUARE;
			
			if((i%4)%2==0)
				size = (size==Size.SMALL?Size.BIG:Size.SMALL);
			
			hole = !hole;
			
			remaining[i] = new Piece(hole,size,shape,colour);
		}
	}
	
	public Piece getPieceAtBoard(int x, int y){
		return board[y][x];		
	}
	
	public Piece getPieceFromRemaining(int index){
		if(index < 0  || index >= 16)
			return null;
		return remaining[index];
	}
	
	public void removeFromRemaining(int index){
		this.remaining[index] = null;
	}
	
	public void addPieceToRemaining(Piece pieceOnHand, int index) {
		this.remaining[index] = pieceOnHand;
	}

	
	public void removePieceOnBoard(int x, int y){
		this.board[y][x] = null;
		this.counter--;
		this.rowCounter[y]--;
		this.colCounter[x]--;
		if(x==y)
			this.backwardDiagonal-=1;
		else if(x == 0 && y == 3 || x == 1 && y == 2 || x == 2 && y == 1 || x == 3 && y == 0){
			this.forwardDiagonal -= 1;
		}
	}
	
	public boolean placePieceOnBoard(int x, int y, Piece piece, int index){
		if(x >= 4 || x < 0)
			return false;
		else if(y >= 4 || y < 0)
			return false;
		if(this.board[y][x]==null){
			this.board[y][x] = piece;
			removeFromRemaining(index);
			
			this.counter++;
			this.rowCounter[y]++;
			this.colCounter[x]++;
			if(x==y)
				this.backwardDiagonal+=1;
			else if(x == 0 && y == 3 || x == 1 && y == 2 || x == 2 && y == 1 || x == 3 && y == 0){
				this.forwardDiagonal += 1;
			}
			return true;
		}
		else
			return false;
	}
	
	public boolean usedAllPieces(){
		return counter==16;
	}
	
	public int piecePlayed(){
		return counter;
	}

	public int getRowCounterAt(int index){
		return this.rowCounter[index];
	}
	public int getColCounterAt(int index){
		return this.colCounter[index];
	}
	public int getForwardDiagonal(){
		return this.forwardDiagonal;
	}
	public int getBackwardDiagonal(){
		return this.backwardDiagonal;
	}
	
	public int[] getEqualOnRows(int index){
		int[] equals = new int[8];
		for (int i = 0; i < 4; i++) {
			if(getPieceAtBoard(i, index)!=null){
				if(getPieceAtBoard(i, index).isHole()==true) equals[0]++; else equals[1]++;
				if(getPieceAtBoard(i, index).getShape().ordinal()==0) equals[2]++; else equals[3]++;
				if(getPieceAtBoard(i, index).getSize().ordinal()==0) equals[4]++; else equals[5]++;
				if(getPieceAtBoard(i, index).getCol().ordinal()==0) equals[6]++; else equals[7]++;
			}
		}			
		return equals;
	}
	
	public int[] getEqualOnCol(int index){
		int[] equals = new int[8];
		for (int i = 0; i < 4; i++) {
			if(getPieceAtBoard(index, i)!=null){
				if(getPieceAtBoard(index, i).isHole()==true) equals[0]++; else equals[1]++;
				if(getPieceAtBoard(index, i).getShape().ordinal()==0) equals[2]++; else equals[3]++;
				if(getPieceAtBoard(index, i).getSize().ordinal()==0) equals[4]++; else equals[5]++;
				if(getPieceAtBoard(index, i).getCol().ordinal()==0) equals[6]++; else equals[7]++;
			}
		}			
		return equals;
	}
	
	public int[] getEqualOnBackwardDiagonal(){
		int[] equals = new int[8];
		for (int i = 0; i < 4; i++) {
			if(getPieceAtBoard(i, i)!=null){
				if(getPieceAtBoard(i, i).isHole()==true) equals[0]++; else equals[1]++;
				if(getPieceAtBoard(i, i).getShape().ordinal()==0) equals[2]++; else equals[3]++;
				if(getPieceAtBoard(i, i).getSize().ordinal()==0) equals[4]++; else equals[5]++;
				if(getPieceAtBoard(i, i).getCol().ordinal()==0) equals[6]++; else equals[7]++;
			}
		}			
		return equals;
	}
	
	public int[] getEqualOnForwardDiagonal(){
		int[] equals = new int[8];
		int size = 3;
		for (int i = 0; i < 4; i++) {
			if(getPieceAtBoard(i, size-i)!=null){
				if(getPieceAtBoard(i, size-i).isHole()==true) equals[0]++; else equals[1]++;
				if(getPieceAtBoard(i, size-i).getShape().ordinal()==0) equals[2]++; else equals[3]++;
				if(getPieceAtBoard(i, size-i).getSize().ordinal()==0) equals[4]++; else equals[5]++;
				if(getPieceAtBoard(i, size-i).getCol().ordinal()==0) equals[6]++; else equals[7]++;
			}
		}			
		return equals;
	}
	
	public int[] getEqualRemainings(){
		int[] equalsRemaining = new int[8];
		for (int i = 0; i < remaining.length; i++) {
			if(remaining[i]!=null){
				if(remaining[i].isHole()==true) equalsRemaining[0]++; else equalsRemaining[1]++;
				if(remaining[i].getShape().ordinal()==0) equalsRemaining[2]++; else equalsRemaining[3]++;
				if(remaining[i].getSize().ordinal()==0) equalsRemaining[4]++; else equalsRemaining[5]++;
				if(remaining[i].getCol().ordinal()==0) equalsRemaining[6]++; else equalsRemaining[7]++;
				
			}
		}
		return equalsRemaining;
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
		buffer.append("\n____________________________________________________________\n");
		
		return buffer.toString();
	}
	
	public boolean checkWin(){
		winState = false;
		if(checkCol() || checkRows() || checkDiagonal())
			winState = true;
		return winState;
	}
	
	private boolean checkRows(){
//		int sameHoles = 0;
//		int sameSize = 0;
//		int sameCol = 0;
//		int sameShape = 0;
//		for (int i = 0; i < rowCounter.length; i++) {
//			if(rowCounter[i] == 4){
//				sameHoles =  (getPieceAtBoard(0, i).isHole()?1:0) + (getPieceAtBoard(1, i).isHole()?1:0) + (getPieceAtBoard(2, i).isHole()?1:0) + (getPieceAtBoard(3, i).isHole()?1:0);
//				sameCol = getPieceAtBoard(0, i).getCol().ordinal() +getPieceAtBoard(1, i).getCol().ordinal() +getPieceAtBoard(2, i).getCol().ordinal() +getPieceAtBoard(3, i).getCol().ordinal();	
//				sameSize = getPieceAtBoard(0, i).getSize().ordinal() +getPieceAtBoard(1, i).getSize().ordinal() +getPieceAtBoard(2, i).getSize().ordinal() +getPieceAtBoard(3, i).getSize().ordinal();
//				sameShape = getPieceAtBoard(0, i).getShape().ordinal() +getPieceAtBoard(1, i).getShape().ordinal() +getPieceAtBoard(2, i).getShape().ordinal() +getPieceAtBoard(3, i).getShape().ordinal();
//				
//				if(sameCol == 4 || sameCol == 0){
//					return true;
//				}
//				if(sameShape == 4 || sameShape == 0){
//					return true;
//				}
//				if(sameSize == 4 || sameSize == 0){
//					return true;
//				}
//				if(sameHoles == 4 || sameHoles == 0){
//					return true;
//				}
//				if(!isInternal)
//					rowCounter[i] = 5;
//			}
//		}
		for (int j = 0; j < 4; j++) {
			if(rowCounter[j] == 4){
				int[] eqRow = getEqualOnRows(j);
				for (int i = 0; i < 4; i++) {
					if(eqRow[i*2]==4 || eqRow[i*2+1]==4)
						return true;
				}
				if(!isInternal)
					rowCounter[j] = 5;
			}
		}
		
		return false;
	}
	
	private boolean checkCol(){
//		int sameHoles = 0;
//		int sameSize = 0;
//		int sameCol = 0;
//		int sameShape = 0;
//		for (int i = 0; i < colCounter.length; i++) {
//			if(colCounter[i] == 4){
//				sameHoles =  (getPieceAtBoard(i, 0).isHole()?1:0) + (getPieceAtBoard(i,1).isHole()?1:0) + (getPieceAtBoard( i,2).isHole()?1:0) + (getPieceAtBoard(i,3).isHole()?1:0);
//				sameCol = getPieceAtBoard(i, 0).getCol().ordinal() +getPieceAtBoard(i,1).getCol().ordinal() +getPieceAtBoard(i,2).getCol().ordinal() +getPieceAtBoard(i,3).getCol().ordinal();	
//				sameSize = getPieceAtBoard(i, 0).getSize().ordinal() +getPieceAtBoard(i,1).getSize().ordinal() +getPieceAtBoard( i,2).getSize().ordinal() +getPieceAtBoard(i,3).getSize().ordinal();
//				sameShape = getPieceAtBoard(i, 0).getShape().ordinal() +getPieceAtBoard(i,1).getShape().ordinal() +getPieceAtBoard( i,2).getShape().ordinal() +getPieceAtBoard(i,3).getShape().ordinal();
//				
//				if(sameCol == 4 || sameCol == 0){
//					return true;
//				}
//				if(sameShape == 4 || sameShape == 0){
//					return true;
//				}
//				if(sameSize == 4 || sameSize == 0){
//					return true;
//				}
//				if(sameHoles == 4 || sameHoles == 0){
//					return true;
//				}
//				if(!isInternal)
//					colCounter[i] = 5;
//			}
//		}
		for (int j = 0; j < 4; j++) {
			if(colCounter[j] == 4){
				int[] eqCol = getEqualOnCol(j);
				for (int i = 0; i < 4; i++) {
					if(eqCol[i*2]==4 || eqCol[i*2+1]==4)
						return true;
				}
				if(!isInternal)
					colCounter[j] = 5;
			}
		}
		
		return false;
		
	}
	
	private boolean checkDiagonal(){
//		int sameHoles = 0;
//		int sameSize = 0;
//		int sameCol = 0;
//		int sameShape = 0;
//		if(backwardDiagonal == 4){
//			sameHoles =  (getPieceAtBoard(0, 0).isHole()?1:0) + (getPieceAtBoard(1,1).isHole()?1:0) + (getPieceAtBoard( 2,2).isHole()?1:0) + (getPieceAtBoard(3,3).isHole()?1:0);
//			sameCol = getPieceAtBoard(0,0).getCol().ordinal() +getPieceAtBoard(1,1).getCol().ordinal() +getPieceAtBoard(2,2).getCol().ordinal() +getPieceAtBoard(3,3).getCol().ordinal();	
//			sameSize = getPieceAtBoard(0,0).getSize().ordinal() +getPieceAtBoard(1,1).getSize().ordinal() +getPieceAtBoard( 2,2).getSize().ordinal() +getPieceAtBoard(3,3).getSize().ordinal();
//			sameShape = getPieceAtBoard(0,0).getShape().ordinal() +getPieceAtBoard(1,1).getShape().ordinal() +getPieceAtBoard( 2,2).getShape().ordinal() +getPieceAtBoard(3,3).getShape().ordinal();
//			
//			if(sameCol == 4 || sameCol == 0){
//				return true;
//			}
//			if(sameShape == 4 || sameShape == 0){
//				return true;
//			}
//			if(sameSize == 4 || sameSize == 0){
//				return true;
//			}
//			if(sameHoles == 4 || sameHoles == 0){
//				return true;
//			}
//			if(!isInternal)
//				backwardDiagonal = 5;
//		}
//		
		if(backwardDiagonal == 4){
				int[] eqBackDia = getEqualOnBackwardDiagonal();
				for (int i = 0; i < 4; i++) {
					if(eqBackDia[i*2]==4 || eqBackDia[i*2+1]==4)
						return true;
				}
				if(!isInternal)
					backwardDiagonal = 5;
		}
		if(forwardDiagonal == 4){
			int[] eqForDia = getEqualOnBackwardDiagonal();
			for (int i = 0; i < 4; i++) {
				if(eqForDia[i*2]==4 || eqForDia[i*2+1]==4)
					return true;
			}
			if(!isInternal)
				forwardDiagonal = 5;
		}
		return false;
//		if(forwardDiagonal == 4){
//			sameHoles =  (getPieceAtBoard(0, 3).isHole()?1:0) + (getPieceAtBoard(1,2).isHole()?1:0) + (getPieceAtBoard( 2,1).isHole()?1:0) + (getPieceAtBoard(3,0).isHole()?1:0);
//			sameCol = getPieceAtBoard(0,3).getCol().ordinal() +getPieceAtBoard(1,2).getCol().ordinal() +getPieceAtBoard(2,1).getCol().ordinal() +getPieceAtBoard(3,0).getCol().ordinal();	
//			sameSize = getPieceAtBoard(0,3).getSize().ordinal() +getPieceAtBoard(1,2).getSize().ordinal() +getPieceAtBoard( 2,1).getSize().ordinal() +getPieceAtBoard(3,0).getSize().ordinal();
//			sameShape = getPieceAtBoard(0,3).getShape().ordinal() +getPieceAtBoard(1,2).getShape().ordinal() +getPieceAtBoard( 2,1).getShape().ordinal() +getPieceAtBoard(3,0).getShape().ordinal();
//			
//			if(sameCol == 4 || sameCol == 0){
//				return true;
//			}
//			if(sameShape == 4 || sameShape == 0){
//				return true;
//			}
//			if(sameSize == 4 || sameSize == 0){
//				return true;
//			}
//			if(sameHoles == 4 || sameHoles == 0){
//				return true;
//			}
//			if(!isInternal)
//				forwardDiagonal = 5; 
//		}
//		return false;
	}
}
