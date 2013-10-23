package GPS;

/**
 * This class stores all the general information required to solve a LocalSearch Problem with the algorithms specified. 
 * @author Eivind
 *
 */

public abstract class State {
	
	protected int iterations,crashes;
	protected double F;
	protected boolean SA;
	
	public State(){
		this.SA = false;
	}

	public double getF() {
		return F;
	}

	public void setF(double f) {
		this.F = f;
		this.SA = true;
	}
	
	public int getCrashes(){
		return crashes;
	}
	
	public void setCrashes(int crashes){
		this.crashes = crashes;
	}
	
	public abstract String toString();

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	} 
}
