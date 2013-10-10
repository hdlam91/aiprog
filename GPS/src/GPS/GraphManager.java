package GPS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.crypto.NodeSetData;

public class GraphManager extends StateManager{
	GraphReader gr;
	private boolean[][] matrix; 
	private int numOfNodes;
	private int lastNodeVisited;
	
	public GraphManager(String file){
		super();
		try {
			gr = new GraphReader(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		numOfNodes = gr.getNodes();
		currentState = createInitState(new GraphState(numOfNodes));
		matrix = gr.getMatrix();
		this.name = "GraphManager: " + file; //insert hard or whatever
		updateConflicts();
		lastNodeVisited = -1;

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
		return null;
	}
	
	//this should be used instead of the one over
	public ArrayList<GraphState> createChildren(GraphState state) {
		ArrayList<GraphState> children = new ArrayList<GraphState>();
		for (int i = 0; i < 20; i++) {
			GraphState child = new GraphState(numOfNodes);
			child = (GraphState) createInitState(child);
			children.add(child);
		}
		return children;
	}

	@Override
	public void calculateF() {
		
	}
	
	
	public void calculateF(GraphState state) {
		//do something.
	}
	
	public State findBestNeighbor(){
		GraphState gs = (GraphState)currentState;
		int[] conflicts = gs.getConflicts();
		int[] nodes = gs.getNodes();
		int currentConflictF = gs.getF();
		
		
		
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
			for (int i = 0; i < 4; i++) {
				nodes[indexToCheck] = i;
				updateConflicts();
				if(gs.getF() < currentConflictF)
				{
					newColor = i;
					currentConflictF = gs.getF();
				}
			
			}
			if(newColor == originalColor){
				nodes[indexToCheck] = originalColor;
				indexes.remove(indexPosInIndexes);
				if(indexes.size()>0){
					indexPosInIndexes = (int)(Math.random()*indexes.size());
					indexToCheck = indexes.get(indexPosInIndexes);
				}
				else
					break;
				updateConflicts();
				continue;
			}
			else{
//				System.out.println("old:" + originalColor + "\tnew: "+newColor);
				nodes[indexToCheck] = newColor;
				updateConflicts();
				break;
				
			}
		}
		updateConflicts();
		
		
		
		
		
		
//		while(conflicts[index] == 0)
//			index = (int)(Math.random()*numOfNodes);
//		int colorWithLeastConflict = nodes[index]; 
//		for (int i = 0; i < 4; i++) {
//			nodes[index] = i;
//			updateConflicts();
//			if(gs.getF() < currentConflict)
//				colorWithLeastConflict = i;
//		
//		}
//		if(colorWithLeastConflict == nodes[index])
//		nodes[index] = colorWithLeastConflict;
		return gs;
	}
	
	public void updateConflicts(){
		GraphState gs = (GraphState) currentState;
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
		
		
		
		gs.setF(crashes);
	
	}
	
	@Override
	public String toString() {
		
	if(false)	
		return ""+getF();
		return /*gr+""+*/getF()+"\nConfl:"+Arrays.toString(((GraphState)currentState).getConflicts()) +"\ncolors:"+Arrays.toString(((GraphState)currentState).getNodes());
	}
	public int getF(){
		return currentState.getF();
	}
	
	public boolean getGoalState(){
		return currentState.getF()==0;
	}
	
	
	public static void main(String[] args) {
		GraphManager t = new GraphManager("graph-color-test1.txt");
		System.out.println(t);
		int i = 0;
		while(t.getF() != 0 && i <1000){
			t.findBestNeighbor();
			System.out.println(t);
			i++;
		}
		System.out.println("end after "+i+" iterations");
		System.out.println(t);
		
		
	}
}
