package GPS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GraphReader {
	
	
	
	BufferedReader stdin;
	StringTokenizer st;
	private boolean matrix[][];
	private int nodes;
	public GraphReader(String file) throws Exception {
		st	= new StringTokenizer("");
		stdin = new BufferedReader(new FileReader(file));
		buildMatrix();
	}
	
	
	public void buildMatrix() throws Exception{
		int numberOfNodes = readInt();
		nodes =  numberOfNodes;
		int numberOfEdges = readInt();
		System.out.println(numberOfNodes);
		System.out.println(numberOfEdges);
		//multiplies with 3 because there's index, x,y per line.
		for (int i = 0; i < numberOfNodes*3; i++) {
			readString();
//			System.out.println("read string");
		}
		System.out.println("make matrix");
		matrix = new boolean[numberOfNodes][numberOfNodes];
		for(int i = 0; i < numberOfEdges; i++){
			int index1 = readInt();
			//System.out.println("index1: " + index1);
			int index2 = readInt();
			//System.out.println("index2: " + index2);
			matrix[index1][index2] = true;
			matrix[index2][index1] = true;
		}
	}
	
	public int getNodes(){
		return nodes;
	}
	public boolean[][] getMatrix(){
		return matrix;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < matrix.length; i++) {
			
			sb.append(Arrays.toString(matrix[i])+"\n");
		}
		
		return sb.toString();
		
		
	};
	
	String readString() throws Exception {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(stdin.readLine());
		}
		return st.nextToken();
	}
	
	// Read next input-token as integer.
	int readInt() throws Exception {
		return Integer.parseInt(readString());
	}
	
}
