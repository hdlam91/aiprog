package GPS;

public abstract class State {
	
	protected int F, iterations;
	
	public State(){
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		this.F = f;
	}
	
	public abstract String toString();

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	} 
}
