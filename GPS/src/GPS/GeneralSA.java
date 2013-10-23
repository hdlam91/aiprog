package GPS;
import java.util.ArrayList;

/**
 * The Simulated Annealing algorithm implemented as described in the pseudo code, with one small alteration. 
 * This alteration is a simple exit condition, which checks if either of the children created is a goal state. 
 * @author Eivind
 *
 */

public class GeneralSA extends LocalSearch{
	private double T; 
	private double dT; 
	private double fTarget;
	private boolean solved;

	
	public GeneralSA (StateManager manager,int maxIter) {
		super(maxIter,manager);			
		this.fTarget = 1.0;
		this.T = (double) 1.0;
		this.dT = 1.0/maxIter;
		this.solved = false;
		solve();
	}
	
	public void solve () {
		ArrayList<State> children;
		State newGeneralState;
		double q;
		double p;
		double x;
		while(T > 0) {
//			System.out.println("Iteration:" + (getMaxIterations() - (int)Math.ceil(T/dT)));
//			System.out.println("Crashes currently: " + getManager().getCurrentState().getCrashes());
			
			if (getManager().getCurrentState().getF() == fTarget) {
				System.out.println("Goal state");
				solved = true;
				getManager().getCurrentState().setIterations(getMaxIterations() - (int)Math.ceil(T/dT));
				break;
			}
			
			children = getManager().createChildren(getManager().getCurrentState());
			newGeneralState = getManager().findBestChild(children);
			
			
			//Exit if we find a goal state
			if (newGeneralState.getF() == fTarget) {
				System.out.println("Goal state");
				getManager().setCurrentState(newGeneralState);
				solved = true;
				getManager().getCurrentState().setIterations(getMaxIterations() - (int)Math.ceil(T/dT));
				break;
			}
			
			q = (newGeneralState.getF()-getManager().getCurrentState().getF())/getManager().getCurrentState().getF();
			p = Math.min(1.0, Math.pow(Math.E, (-q)/T));
			x = Math.random();
			
			if (x > p) {
				getManager().setCurrentState(newGeneralState);
			}
			else {
				int random = (int)(Math.random()*children.size());
				getManager().setCurrentState(children.get(random));
			}
			T  -= dT;
						
//			System.out.println(getManager().getCurrentState());
		}
		if(!solved){
			System.out.println("T is zero, no optimal solution found");
			getManager().getCurrentState().setIterations(getMaxIterations());
		}
	}
	
	public State getFinalState(){
		System.out.println(getManager().getName());
		return getManager().getCurrentState();
	}
}
