package GPS;

public class GraphState extends State{

	private int numOfNodes;
	private int[] nodes;
	private int[] conflicts;
	private static boolean[][] neighbourMatrix;
	
	public GraphState(int numOfNodes){
		super();
		this.numOfNodes = numOfNodes;
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
			buf.append("Temperature:\t"+getF());
		buf.append("\nnum of conflicts in total: \t" +getCrashes()+"\n");
		buf.append("N = Node C = Color\n");
		for (int i = 0; i < nodes.length; i++) {
			buf.append("N:" +i + " C:" +nodes[i] + "| ");
			for (int j = 0; j < neighbourMatrix[0].length; j++) {
				if(neighbourMatrix[i][j]){
					buf.append("N:" +j +" C:"+nodes[j] + " ");
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
