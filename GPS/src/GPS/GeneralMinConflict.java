package GPS;

public class GeneralMinConflict extends LocalSearch{
	private StateManager manager;
	private int maxIterations;
	private boolean solved;
	
	public GeneralMinConflict(StateManager manager, int maxIterations){
		this.manager = manager;
		this.maxIterations = maxIterations;
		this.solved = false;
		solve();
	}
	
	public void solve(){
		for (int i = 0; i < maxIterations; i++) {
			if(manager.getGoalState()){
				manager.getCurrentState().setIterations(i);
				solved = true;
				break;
			}
//			System.out.println("iteration: " + i);
//			System.out.println(manager.getCurrentState());
			manager.setCurrentState(manager.findBestNeighbor());
		}
		if(!solved)
			manager.getCurrentState().setIterations(maxIterations);
	}
	
	public State getGoalState(){
		if(solved)
			System.out.println("\nGoal State!\n");
		else
			System.out.println("\nNot a goal state\n");
		System.out.println(manager.getName());
		return manager.getCurrentState();
	}
}
