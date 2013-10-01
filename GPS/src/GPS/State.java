package GPS;

public abstract class State {
	
	protected int F;
	
	public State(){
		
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		this.F = f;
	}
	
	public abstract String toString(); 
}
