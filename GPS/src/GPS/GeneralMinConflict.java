package GPS;

/**
 * The Minimum-Conflict algorithm which is pretty much an exact implementation of the pseudo code 
 * @author Eivind
 *
 */

public class GeneralMinConflict extends LocalSearch{
	private boolean solved;
	
	public GeneralMinConflict(StateManager manager, int maxIterations){
		super(maxIterations,manager);
		this.solved = false;
		solve();
	}
	
	public void solve(){
		for (int i = 0; i < getMaxIterations(); i++) {
			if(getManager().getGoalState()){
				getManager().getCurrentState().setIterations(i);
				solved = true;
				break;
			}
//			System.out.println("\n\nIteration: " + i);
//			System.out.println(getManager().getCurrentState());
			getManager().setCurrentState(getManager().findBestNeighbor(getManager().getCurrentState()));
		}
		if(!solved)
			getManager().getCurrentState().setIterations(getMaxIterations());
	}
	
	public State getFinalState(){
		if(solved)
			System.out.println("\nGoal State!\n");
		else
			System.out.println("\nNot a goal state\n");
		System.out.println(getManager().getName());
		return getManager().getCurrentState();
	}
}
