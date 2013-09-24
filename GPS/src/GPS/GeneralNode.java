package GPS;

public class GeneralNode {
	public Object state; //The state of the node, i.e. the egg carton. 
	private double F; //The objective function value
	
	public GeneralNode(Object state) {
		this.state = state;
		F = calculateF();
	}
	
	//Calculates the objective function value for this state/node
	//Implementation depends on the problem.
	public double calculateF() {
		return 0;
	}
	
	public double getF() {
		return F;
	}
	
	public Object getState() {
		return state;
	}
}
