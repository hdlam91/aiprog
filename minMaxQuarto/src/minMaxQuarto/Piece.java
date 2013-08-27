package minMaxQuarto;

import minMaxQuarto.Enums.*;

public class Piece {
	private boolean hole;
	private Shape shape;
	private Size size;
	private Colour col;
	private String out;
	
	Piece(boolean hole, Size size, Shape shape, Colour col){
		this.hole = hole;
		this.shape = shape;
		this.col = col;
		this.size = size;
		makeString();
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
	
	
	private void makeString(){
		StringBuffer output = new StringBuffer();
		char c;
		
		if(shape == Shape.ROUND){
			output.append("(");
		}
		else{
			output.append("|");
		}
		
		if(col == Colour.RED){
			c = 'r';
		}
		else{
			c = 'b';
		}
		
		if(size == Size.BIG){
			c = Character.toUpperCase(c);
		}
		
		output.append(c);
		
		if(hole){
			output.append("*");
		}
		
		if(shape == Shape.ROUND){
			output.append(")");
		}
		else{
			output.append("|");
		}
		
		out = output.toString();
		
		
	}
	@Override
	public String toString(){
		return out;
	}
	
	
}
