package minMaxQuarto;

import minMaxQuarto.Enums.*;

public class Piece {
	private boolean hole;
	private Shape shape;
	private Size size;
	private Colour col;
	
	
	Piece(boolean hole, Size size, Shape shape, Colour col){
		this.hole = hole;
		this.shape = shape;
		this.col = col;
		this.size = size;
	}


	public boolean isHole() {
		return hole;
	}


	public Shape getShape() {
		return shape;
	}


	public Size getSize() {
		return size;
	}


	public Colour getCol() {
		return col;
	}
	@Override
	public String toString(){
		StringBuffer output = new StringBuffer();
		
		//returnerer brikken.
		
		
		return output.toString();
	}
	
	
}
