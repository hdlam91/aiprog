package GPS;

/**
 * This class is merely a data container for positions in a two dimensional matrix. 
 * A tuple is a data structure Java has not defined so we opted to do it ourselves. This makes it much easier to handle positions on the board for the sudoku problem.
 * @author Eivind
 *
 */

public class ThirdPuzzleTuple {
	private int x,y;
	
	public ThirdPuzzleTuple(int y, int x){
		this.y = y;
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public String toString(){
		return "Y" + getY() + "X" + getX();
	}
}
