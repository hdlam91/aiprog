package GPS;

import java.util.ArrayList;

/**
 * This class manages the different State(s) for the GraphColoring problem. It implements all of methods associated with managing states
 * (I.e all we have defined in StateManager) and it also implements some methods that are specific to this problem.   
 * @author Eivind
 *
 */

public class GraphManager extends StateManager{
	GraphReader gr;
	private boolean[][] matrix; 
	private int numOfNodes;
	private int lastNodeChecked;
	
	public GraphManager(String file, GraphReader gr){
		super();
		this.gr = gr;
		numOfNodes = gr.getNodes();
		currentState = createInitState(new GraphState(numOfNodes));
		matrix = gr.getMatrix();
		this.name = "GraphManager: " + file;
		lastNodeChecked = -1;
		updateConflicts(currentState);
		((GraphState) currentState).setMatrix(matrix);
	}
	
	public State createInitState(State state) {
		GraphState current = (GraphState)state;
		int[] nodes = current.getNodes();
		for (int i = 0; i < gr.getNodes(); i++) {
			nodes[i] = (int)(Math.random()*4);
		}
		return current;	
	}
	
	public ArrayList<State> createChildren(State state){
		ArrayList<State> children = new ArrayList<State>();
		for (int i = 0; i < 20; i++) {
			GraphState child = new GraphState(numOfNodes);
			int[] newRandomModifiedNodes = ((GraphState) state).getNodes().clone();
			newRandomModifiedNodes[(int)(Math.random()*numOfNodes)] = (int)(Math.random()*4);
			
			child.setNodes(newRandomModifiedNodes);
			calculateF(child);
			
			children.add(child);
		}
		return children;
	}
	

	public void calculateF(State state) {
		GraphState gs = (GraphState)state;
		updateConflicts(gs);
		double crashes = gs.getCrashes();
		double f = 1-(crashes/((double)numOfNodes));
		if(f <= 0)
			gs.setF(0);
		else
			gs.setF(f);
	}
	
	
	
	public State findBestNeighbor(State state){
		GraphState gs = (GraphState)state;
		int[] conflicts = gs.getConflicts();
		int[] nodes = gs.getNodes();
		int currentConflicts = gs.getCrashes();
		
		if(currentConflicts == 0)
			return gs;
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i  = 0; i < nodes.length; i++){
			if(conflicts[i] > 0)
				indexes.add(i);
		}
		
		int indexPosInIndexes = (int)(Math.random()*indexes.size());
		int indexToCheck = indexes.get(indexPosInIndexes);

		if(indexToCheck == lastNodeChecked){
			indexes.remove(indexPosInIndexes);
			indexPosInIndexes = (int)(Math.random()*indexes.size());
			indexToCheck = indexes.get(indexPosInIndexes);
		}
		if(indexes.size() == 0)
			return gs;
		int originalColor = nodes[indexToCheck];
		ArrayList<Integer> colList = new ArrayList<Integer>();
		int[] collisionGivenColor = new int[4];
		int lowestCollision = Integer.MAX_VALUE;
		for (int i = 0; i<collisionGivenColor.length; i++) {
			collisionGivenColor[i] = getCollisionsForNode(gs, indexToCheck, i);
			if(collisionGivenColor[i] < lowestCollision){
				lowestCollision = collisionGivenColor[i];
			}
		}
		for (int i = 0; i<collisionGivenColor.length; i++) {
			if(i == originalColor){
				continue;
			}
			else if(collisionGivenColor[i] == lowestCollision){
				colList.add(i);
			}

		}
		
		if(colList.size()>=1){
			nodes[indexToCheck] = colList.get((int)(Math.random()*colList.size()));
			lastNodeChecked = -1;
		}
		else{
			lastNodeChecked = indexToCheck;
		}
			
		updateConflicts(state);
		return gs;
	}
	
	
	public int getCollisionsForNode(State state, int indexToCheck, int c){
		GraphState gs = (GraphState)state;
		int[] nodes = gs.getNodes();
		int conflForThisNodeGivenColor = 0;
		for (int i = 0; i < nodes.length; i++) {
			if(matrix[indexToCheck][i] && nodes[i] == c && indexToCheck != i){
				conflForThisNodeGivenColor++;
			}		
		}
		return conflForThisNodeGivenColor;
	}
	
	public void updateConflicts(State state){
		GraphState gs = (GraphState) state;
		gs.resetConflicts();
		int[] nodes = gs.getNodes();
		int[] conflicts = gs.getConflicts();

		int crashes = 0;
		for(int i  = 0; i < nodes.length; i++){
			for (int j = 0; j < nodes.length; j++) {
				if(i != j && matrix[i][j] && nodes[i] == nodes[j]){
					crashes+=1;
					conflicts[i]+=1;
				}
			}
		}
		gs.setCrashes(crashes);
	}
	
	public double getCrashes(){
		return currentState.getCrashes();
	}
	
	public boolean getGoalState(){
		return currentState.getCrashes()==0;
	}
}
