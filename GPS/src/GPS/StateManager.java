package GPS;

import java.util.ArrayList;

public abstract class StateManager {
	protected State currentState;
	
	public StateManager(){
		
	}

	//Finds child with the highest objective function value.
	protected State findBestChild(ArrayList<State> children) {
		State returnState = children.get(0);
		for (int i = 0; i < children.size(); i++) {
			if (returnState.getF() < children.get(i).getF()) returnState = children.get(i);
		}
		return returnState;
	}
	
	public abstract void calculateF();

	//Creates children. Implementation depends on the problem.
	public abstract ArrayList<State> createChildren(State state);
	
	public abstract State createInitState(State state);
}
