package GPS;

import java.util.Arrays;

public class GraphState extends State{


	private int numOfNodes;
	private int[] nodes;
	private int[] conflicts;
	
	public GraphState(int numOfNodes){
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
		int[][] printBoard = new int[numOfNodes][numOfNodes];
		StringBuffer buffer = new StringBuffer();
		buffer.append("The board \n");
		for (int i = 0; i < numOfNodes; i++) {
			printBoard[i][nodes[i]] = 1;
			buffer.append(Arrays.toString(printBoard[i])+"\n");
		}
		buffer.append("\n" + "Conflicts for queen at row i\n" + Arrays.toString(conflicts)+"\n");
		buffer.append("Crashes: " +getF());
		return buffer.toString();
	}
	
}
