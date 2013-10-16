package GPS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.crypto.NodeSetData;

public class GraphManager extends StateManager{
	GraphReader gr;
	private boolean[][] matrix; 
	private int numOfNodes;
	private int maxNumberOfConflictsPossible;
	private boolean minimizedConflict;
	
	public GraphManager(String file){
		super();
		try {
			gr = new GraphReader(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		double currentConflictF = gs.getCrashes();
		
		
		
		if(currentConflictF == 0)
			return gs;
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i  = 0; i < nodes.length; i++){
			if(conflicts[i] > 0)
				indexes.add(i);
		}
		
		
		
		
		
		
		int indexPosInIndexes = (int)(Math.random()*indexes.size());
		int indexToCheck = indexes.get(indexPosInIndexes);
		while(indexes.size() > 0){
			int originalColor = nodes[indexToCheck];
			int newColor = originalColor;
			ArrayList<Integer> colList = new ArrayList<Integer>();
			for (int i = 0; i < 4; i++) {
				nodes[indexToCheck] = i;
				updateLocalConflict(indexToCheck, gs,true);
				if(gs.getCrashes() < currentConflictF){
					colList.clear();
					colList.add(i);
					currentConflictF = gs.getCrashes();
					minimizedConflict = true;
				}
				else if(gs.getCrashes() == currentConflictF)
				{
					colList.add(i);
				}
			
			}
			while(colList.size()>= 1 && newColor==originalColor){
				int index = (int)Math.random()*colList.size();
				newColor = colList.get(index);
				colList.remove(index);
				minimizedConflict = false;
			}
			if(newColor == originalColor){
				nodes[indexToCheck] = originalColor;
				indexes.remove(indexPosInIndexes);
				if(indexes.size()>0){
					indexPosInIndexes = (int)(Math.random()*indexes.size());
					indexToCheck = indexes.get(indexPosInIndexes);
				}
				else if(!minimizedConflict){
					ArrayList<Integer> neigboursOfCurrentNode = new ArrayList<Integer>();
					for (int i = 0; i < nodes.length; i++) {
						if(matrix[indexToCheck][i]){
							neigboursOfCurrentNode.add(i);
						}
					}
					int randomNeighbour = (int)(Math.random()*neigboursOfCurrentNode.size());
					nodes[randomNeighbour] = originalColor;
					break;
				}
				else 
					break;
				updateLocalConflict(indexToCheck, gs,true);
				continue;
			}
			else{
//				System.out.println("old:" + originalColor + "\tnew: "+newColor);
				nodes[indexToCheck] = newColor;
				updateLocalConflict(indexToCheck, gs, true);
				break;
				
			}
		}
		updateConflicts(gs);
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
