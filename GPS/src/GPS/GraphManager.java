package GPS;

import java.util.ArrayList;

public class GraphManager extends StateManager{
	public GraphManager(){
		super();
		this.name = "GraphManager "; //insert hard or whatever
	}
	
	public State createInitState(State state) {
		// TODO Auto-generated method stub
		return null;		
	}

	@Override
	public ArrayList<State> createChildren(State state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public State findBestNeighbor(){
		return null;
	}
	
	public boolean getGoalState(){
		return false;
	}
}
