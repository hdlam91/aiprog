package GPS;


import java.util.ArrayList;
import java.util.Arrays;


public class GraphManager extends StateManager{
	GraphReader gr;
	private boolean[][] matrix; 
	private int numOfNodes;
	private int maxNumberOfConflictsPossible;
	private boolean minimizedConflict;
	private int lastNodeChecked;
	
	public GraphManager(String file, GraphReader gr){
		super();
		this.gr = gr;
		numOfNodes = gr.getNodes();
		maxNumberOfConflictsPossible = gr.getNumberOfConflictsPossible();
		currentState = createInitState(new GraphState(numOfNodes));
		matrix = gr.getMatrix();
		this.name = "GraphManager: " + file;
		lastNodeChecked = -1;
		updateConflicts(currentState);

	}
	
	public State createInitState(State state) {
		GraphState current = (GraphState)state;
		int[] nodes = current.getNodes();
		for (int i = 0; i < gr.getNodes(); i++) {
			nodes[i] = (int)(Math.random()*4);
		}
		
		return current;	
	}
	@Override
	public ArrayList<State> createChildren(State state){
		ArrayList<State> children = new ArrayList<State>();
		for (int i = 0; i < 20; i++) {
			GraphState child = new GraphState(numOfNodes);
			int[] newRandomModifiedNodes = ((GraphState) state).getNodes().clone();
			for (int j = 0; j < Math.random()*3; j++) {
				
				newRandomModifiedNodes[(int)(Math.random()*numOfNodes)] = (int)(Math.random()*4);
			}
			child.setNodes(newRandomModifiedNodes);
			calculateF(child);
			
			children.add(child);
		}
		return children;
	}
	

	@Override
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
		int currentConflictF = gs.getCrashes();
		
		
		if(currentConflictF == 0)
			return gs;
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i  = 0; i < nodes.length; i++){
			if(conflicts[i] > 0)
				indexes.add(i);
		}
		
		
		
		
		
		//gets a random position to get an index from
		int indexPosInIndexes = (int)(Math.random()*indexes.size());
		//get the random index 
		int indexToCheck = indexes.get(indexPosInIndexes);
		//while there's nodes with conflicts
		
		
		if(indexToCheck == lastNodeChecked){
			indexes.remove(indexPosInIndexes);
			indexPosInIndexes = (int)(Math.random()*indexes.size());
			//get the random index 
			indexToCheck = indexes.get(indexPosInIndexes);
		}
		if(indexes.size() == 0)
			return gs;
		//get the original color and save it
		int originalColor = nodes[indexToCheck];
		//new color initialized
		int newColor = originalColor;
		//collisionlist
		ArrayList<Integer> colList = new ArrayList<Integer>();
		//make an array with all the collisions for each color
		int[] collisionGivenColor = new int[4];
		//sets up number of collisions for each color
		int lowestCollision = Integer.MAX_VALUE;
		int colorWithLowestCollision = -1;
		for (int i = 0; i<collisionGivenColor.length; i++) {
			collisionGivenColor[i] = getCollisionsForNode(gs, indexToCheck, i);
			if(collisionGivenColor[i] < lowestCollision){
				lowestCollision = collisionGivenColor[i];
				colorWithLowestCollision = i;
			}
		}
//		System.out.println("Node chosen: " + indexToCheck);
//		System.out.println("colors:"+Arrays.toString(collisionGivenColor));
//		System.out.println("nodes:"+Arrays.toString(nodes));
		for (int i = 0; i<collisionGivenColor.length; i++) {
			if(i == originalColor){
				continue;
			}
			else if(collisionGivenColor[i] == lowestCollision){
				colList.add(i);
			}

		}
		
		//if there's something to change, DO IT
		if(colList.size()>=1){
			nodes[indexToCheck] = colList.get((int)(Math.random()*colList.size()));
			lastNodeChecked = -1;
		}
		//no change.
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
	
	
//	public void updateLocalConflict(int index, State state, boolean updateNeighbours){
//		GraphState gs = (GraphState) state;
//		int[] nodes = gs.getNodes();
//		int[] conflicts = gs.getConflicts();
//		int oldCrashes = gs.getCrashes();
//		int oldConflict = conflicts[index];
//		int crashes = 0;
//		
//			for (int j = 0; j < nodes.length; j++) {
//				if(index != j && matrix[index][j] && nodes[index] == nodes[j]){
//					if(updateNeighbours){
//						updateLocalConflict(j, gs, false);
//					}
//					crashes+=1;
//					
//				}
//			}
//		conflicts[index] = crashes;
//		if(crashes < 0)
//			System.err.println("<0");
////		System.out.println(oldCrashes-oldConflict+crashes);
//		gs.setCrashes(oldCrashes-oldConflict+crashes);
//	}
	
	
	
	
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
