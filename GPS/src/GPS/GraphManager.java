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
	private int maxNumberOfConflictsPossible;
	
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
		ArrayList<State> children = new ArrayList<State>();
		for (int i = 0; i < 20; i++) {
			GraphState child = new GraphState(numOfNodes);
			int[] newRandomModifiedNodes = ((GraphState) state).getNodes().clone();
			newRandomModifiedNodes[(int)(Math.random()*numOfNodes)] = (int)(Math.random()*4);
			child.setNodes(newRandomModifiedNodes);
			children.add(child);
		}
		return children;
	}
	

	@Override
	public void calculateF() {
		GraphState gs = (GraphState)this.currentState;
		updateConflicts();
		double f = gs.getF();
		gs.setF((maxNumberOfConflictsPossible-f)/((double)maxNumberOfConflictsPossible));
	}
	
	
	public State findBestNeighbor(){
		GraphState gs = (GraphState)currentState;
		int[] conflicts = gs.getConflicts();
		int[] nodes = gs.getNodes();
		double currentConflictF = gs.getF();
		
		
		
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
				updateConflicts();
				if(gs.getF() < currentConflictF){
					colList.clear();
					colList.add(i);
					currentConflictF = gs.getF();
				}
				else if(gs.getF() == currentConflictF)
				{
					colList.add(i);
				}
			
			}
			while(colList.size()>= 1 && newColor==originalColor){
				int index = (int)Math.random()*colList.size();
				newColor = colList.get(index);
				colList.remove(index);
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
	
	//used for debugging
	@Override
	public String toString() {
		
	if(false)	
		return ""+getF();
		return /*gr+""+*/getF()+"\nConfl:"+Arrays.toString(((GraphState)currentState).getConflicts()) +"\ncolors:"+Arrays.toString(((GraphState)currentState).getNodes());
	}
	//testing for Jezzzzzzuuuuus Messiah Abadah Christ
	public double getF(){
		return currentState.getF();
	}
	
	public boolean getGoalState(){
		return currentState.getF()==0;
	}
	
	//testing stuff
	public static void main(String[] args) {
		GraphManager t = new GraphManager("graph-color-3.txt");
		System.out.println(t);
		int i = 0;
		while(t.getF() != 0 && i <2000){
			t.findBestNeighbor();
			System.out.println(t);
			i++;
		}
		System.out.println("end after "+i+" iterations");
		System.out.println(t);
		
		
	}
}
