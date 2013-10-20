package GPS;

public class ThirdPuzzleTupple {
	private int x,y;
	
	public ThirdPuzzleTupple(int y, int x){
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
