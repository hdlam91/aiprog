package GPS;

/**
 * This class is primarily here to easily be able to modularize the code.
 * It defines the methods required to run the SA and Minimum-Conflict algorithms as well as holding the information about the current StateManager and the maximum iterations for the current run.
 * @author Eivind
 *
 */

public abstract class LocalSearch {
	private int maxIterations;
	private StateManager manager;
	
	public LocalSearch(int maxiter, StateManager manager){
		this.maxIterations = maxiter;
		this.setManager(manager);
	}
	
	public abstract State getFinalState();
	public abstract void solve();
	
	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public StateManager getManager() {
		return manager;
	}

	public void setManager(StateManager manager) {
		this.manager = manager;
	}
}
