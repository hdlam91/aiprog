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
	public ArrayList<State> createChildren(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateF() {
		// TODO Auto-generated method stub
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
		int indexToCheck = (int)(Math.random()*indexes.size());
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
				indexes.remove(indexToCheck);
				nodes[indexToCheck] = originalColor;
				indexToCheck = (int)(Math.random()*indexes.size());
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
		//System.out.println("UPDATING\n"+Arrays.toString(((GraphState)currentState).getNodes()));
		for(int i  = 0; i < nodes.length; i++){
			for (int j = 0; j < nodes.length; j++) {
				if(i != j && matrix[i][j] && nodes[i] == nodes[j]){
//					if(i < 3 && j < 3){
//						System.out.println(i + ":" + j);
//						System.out.println(nodes[i]+"node"+nodes[j]);
//					}
					crashes+=1;
					conflicts[i]+=1;
				}
			}
		}
		
		
		
		gs.setF(crashes);
	
	}
	
	@Override
	public String toString() {
		
		
//		return ""+getF();
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
