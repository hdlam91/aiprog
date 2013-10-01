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

	//Creates children. Implementation depends on the problem.
	public abstract ArrayList<GeneralNode> createChildren(GeneralNode currentGeneralNode2);
	
	public abstract State createInitState(State state);
}
