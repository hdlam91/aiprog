package GPS;

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
