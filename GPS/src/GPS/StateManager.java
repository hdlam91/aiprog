package GPS;

import java.util.ArrayList;

public abstract class StateManager {
	protected State currentState;
	protected String name;
	
	public StateManager(){		
	}

	protected State findBestChild(ArrayList<State> children) {
		State returnState = children.get(0);
		for (int i = 1; i < children.size(); i++) {
			if (returnState.getF() < children.get(i).getF()) returnState = children.get(i);
		}
		return returnState;
	}
	
	public abstract void calculateF(State state);

	public abstract State findBestNeighbor(State state);
	
	public abstract ArrayList<State> createChildren(State state);
	
	public abstract State createInitState(State state);
	
	public abstract boolean getGoalState();
	
	public State getCurrentState(){
		return currentState;
	};
	
	public void setCurrentState(State state){
		this.currentState = state;
	}
	
	public String getName(){
		return name;
	}
}
