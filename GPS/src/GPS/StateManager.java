package GPS;

import java.util.ArrayList;

/**
 * This class defines all the general methods needed to solve a local search problem. I.e generating neighboring states and evaluation of these.
 * It also keeps track of the current State. 
 * @author Eivind
 *
 */

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
