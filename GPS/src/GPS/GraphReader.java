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
	private int numberOfEdges;
	int numOfConflicts;
	public GraphReader(String file) throws Exception {
		st	= new StringTokenizer("");
		stdin = new BufferedReader(new FileReader(file));
		buildMatrix();
	}
	
	public void buildMatrix() throws Exception{
		nodes = readInt();
		numberOfEdges = readInt();
		for (int i = 0; i < nodes*3; i++) {
			readDouble();
		}
		System.out.println("make matrix");
		matrix = new boolean[nodes][nodes];
		numOfConflicts = 0;
		for(int i = 0; i < numberOfEdges; i++){
			int index1 = readInt();
			int index2 = readInt();
			matrix[index1][index2] = true;
			matrix[index2][index1] = true;
			numOfConflicts+=2;
		}
		stdin.close();
	}
	
	public int getNumberOfConflictsPossible(){
		return numOfConflicts;
	}
	
	public int getNodes(){
		return nodes;
	}
	public boolean[][] getMatrix(){
		return matrix;
	}
	
	public int getNumberOfEdges(){
		return numberOfEdges;
	}
		
	String readString() throws Exception {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(stdin.readLine());
		}
		return st.nextToken();
	}
	
	int readInt() throws Exception {
		return Integer.parseInt(readString());
	}
	double readDouble() throws Exception {
		return Double.parseDouble(readString());
	}
	
}
