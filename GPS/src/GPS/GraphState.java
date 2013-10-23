package GPS;

/**
 * This class stores all the specific information we require to differentiate between states of the Graph Coloring problem.
 * As well as several methods to alter and acquire this information.
 * @author Eivind
 *
 */

public class GraphState extends State{

	private int[] nodes;
	private int[] conflicts;
	private static boolean[][] neighbourMatrix;
	
	public GraphState(int numOfNodes){
		super();
		this.setNodes(new int[numOfNodes]);
		this.setConflicts(new int[numOfNodes]);
	}
	
	
	public void setMatrix(boolean[][] matrix){
		neighbourMatrix = matrix;
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
		StringBuffer buf = new StringBuffer();
		if(SA)
			buf.append("F: "+getF());
		buf.append("\nNumber of conflicts in total: " +getCrashes()+"\n");
		buf.append("N = Node C = Color\n");
		for (int i = 0; i < nodes.length; i++) {
			buf.append("N:" +i + " C:" +nodes[i] + "| ");
			for (int j = 0; j < neighbourMatrix[0].length; j++) {
				if(neighbourMatrix[i][j]){
					buf.append("N:" +j +" C:"+nodes[j] + "  ");
				}
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	public void resetConflicts() {
		for (int i = 0; i < conflicts.length; i++) {
			conflicts[i] = 0;
		}		
	}
	
}
