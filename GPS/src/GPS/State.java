package GPS;

public abstract class State {
	
	protected int iterations;
	protected double F;
	
	public State(){
	}

	public double getF() {
		return F;
	}

	public void setF(double f) {
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
