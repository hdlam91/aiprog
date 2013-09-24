package GPS;

public abstract class GeneralNode {
	public Object state; //The state of the node 
	private double F; //The objective function value
	
	public GeneralNode(Object state) {
		this.state = state;
		this.F = 0;
	}
	
	//Calculates the objective function value for this state/node
	//Implementation depends on the problem.
	public abstract double calculateF();
	
	public void SetF(double input){
		this.F = input;
	}
	
	public double getF() {
		return F;
	}
	
	public Object getState() {
		return state;
	}
}
