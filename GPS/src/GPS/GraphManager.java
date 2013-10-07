package GPS;

import java.util.ArrayList;

public class GraphManager extends StateManager{
	GraphReader gr;
	int nodes[];
	public GraphManager(String file){
		super();
		try {
			gr = new GraphReader(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		nodes =  new int[gr.getNodes()];
		
		this.name = "GraphManager "; //insert hard or whatever

	}
	
	public State createInitState(State state) {
		GraphState current = (GraphState)state;
		int[] board = current.getNodes();
		for (int i = 0; i < gr.getNodes(); i++) {
			
		}
//		current.setBoard(board);
		return current;	
	}

	@Override
	public ArrayList<State> createChildren(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateF() {
		// TODO Auto-generated method stub
	}
	
	public State findBestNeighbor(){
		return null;
	}
	
	public boolean getGoalState(){
		return false;
	}
}
