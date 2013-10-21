package GPS;


import java.util.ArrayList;
import java.util.Arrays;


public class GraphManager extends StateManager{
	GraphReader gr;
	private boolean[][] matrix; 
	private int numOfNodes;
	private int maxNumberOfConflictsPossible;
	private boolean minimizedConflict;
	
	public GraphManager(String file, GraphReader gr){
		super();
		this.gr = gr;
		numOfNodes = gr.getNodes();
		maxNumberOfConflictsPossible = gr.getNumberOfConflictsPossible();
		currentState = createInitState(new GraphState(numOfNodes));
		matrix = gr.getMatrix();
		this.name = "GraphManager: " + file; 
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
//		if(f <= 0)
//			gs.setF(0);
//		else
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
		while(indexes.size() > 0){
			//get the original color and save it
			int originalColor = nodes[indexToCheck];
			//new color initialized
			int newColor = originalColor;
			//collisionlist
			ArrayList<Integer> colList = new ArrayList<Integer>();
			
			//try all the colors
			for (int i = 0; i < 4; i++) {
				//set a new color for the specific node
				nodes[indexToCheck] = i;
				//update conflict locally for this node and it's neighbours
				updateLocalConflict(indexToCheck, gs,true);
				//checks if conflicts are less than the original one
				if(gs.getCrashes() < currentConflictF){
					//if it is, remove the pool
					colList.clear();
					//add new color to pool
					colList.add(i);
					//sets new lower conflict
					currentConflictF = gs.getCrashes();
					//sets the minimized to true
					minimizedConflict = true;
				}
				else if(gs.getCrashes() == currentConflictF)
				{
					//if conflicts number is the same as the previous one, add color to conflict.
					colList.add(i);
				}
			
			}
			//checked for all colors
			
			//if collisions >= 1 and the color it chose is the same as the one it started with
			while(colList.size()>= 1 && newColor==originalColor){
				//pick a random index from the collision list
				int index = (int)Math.random()*colList.size();
				//set new color to be this node's color
				newColor = colList.get(index);
				//remove that node from collision
				colList.remove(index);
				//say that the minimized conflict is false.
				minimizedConflict = false;
			}
			
			//if the colors are the same
			if(newColor == originalColor){
				//sets the color of the node to the original one
				nodes[indexToCheck] = originalColor;
//				and remove it from the list of nodes with conflicts
				indexes.remove(indexPosInIndexes);
				//if there's still other nodes with conflicts
				if(indexes.size()>0){
					//pick a new random index
					indexPosInIndexes = (int)(Math.random()*indexes.size());
					indexToCheck = indexes.get(indexPosInIndexes);
				}
				//if conflict wasn't minimized but a new color was selected
				else if(!minimizedConflict){
					//find all the neighbours of current node
					ArrayList<Integer> neigboursOfCurrentNode = new ArrayList<Integer>();
					for (int i = 0; i < nodes.length; i++) {
						if(matrix[indexToCheck][i]){
							neigboursOfCurrentNode.add(i);
						}
					}
					//choose one random of these
					int randomNeighbour = (int)(Math.random()*neigboursOfCurrentNode.size());
					//set it to be the color of "this" node
					nodes[randomNeighbour] = originalColor;
					break;
				}
				else{ 
					break;
				}
				updateLocalConflict(indexToCheck, gs,true);
				continue;
			}
			else{
				//if a new color made the conflicts minimized use this.
				nodes[indexToCheck] = newColor;
				
				break;
				
			}
		}
		updateConflicts(state);
		return gs;
	}
	
	
	public void updateLocalConflict(int index, State state, boolean updateNeighbours){
		GraphState gs = (GraphState) state;
		int[] nodes = gs.getNodes();
		int[] conflicts = gs.getConflicts();
		int oldCrashes = gs.getCrashes();
		int oldConflict = conflicts[index];
		int crashes = 0;
			for (int j = 0; j < nodes.length; j++) {
				if(index != j && matrix[index][j] && nodes[index] == nodes[j]){
					if(updateNeighbours){
						updateLocalConflict(j, gs, false);
					}
					crashes+=1;
					
				}
			}
		conflicts[index] = crashes;
		if(crashes < 0)
			System.err.println("<0");
//		System.out.println(oldCrashes-oldConflict+crashes);
		gs.setCrashes(oldCrashes-oldConflict+crashes);
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
