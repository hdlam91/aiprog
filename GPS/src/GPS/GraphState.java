package GPS;

import java.util.Arrays;

public class GraphState extends State{


	private int numOfNodes;
	private int[] nodes;
	private int[] conflicts;
	
	
	public GraphState(int numOfNodes){
		super();
		this.numOfNodes = numOfNodes;
		this.setNodes(new int[numOfNodes]);
		this.setConflicts(new int[numOfNodes]);
	}

	public int[] getNodes() {
		return nodes;
	}

	public void setNodes(int[] nodes) {
		this.nodes = nodes;
	}

	public int[] getConflicts() {
		return conflicts;
	}

	public void setConflicts(int[] conflicts) {
		this.conflicts = conflicts;
	}
	
	public String toString(){
		
		return   /*gr+""+*/
				"Temperature:\t"+getF()+"\nnum of conflicts in total: \t" +getCrashes()+"\nConfl:"+Arrays.toString(getConflicts()) +"\ncolors:"+Arrays.toString(getNodes());
	}

	public void resetConflicts() {
		for (int i = 0; i < conflicts.length; i++) {
			conflicts[i] = 0;
		}		
	}
	
}
