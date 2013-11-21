package PSO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TextReader {
	BufferedReader stdin;
	StringTokenizer st;
	ArrayList<Double> values;
	ArrayList<Double> weight;

	public TextReader(String file) throws Exception{
		st	= new StringTokenizer("");
		stdin = new BufferedReader(new FileReader(file));
		
		values = new ArrayList<Double>();
		weight = new ArrayList<Double>();
		readFile();
	}
	
	
	
	public void readFile() throws Exception{
		double v, w;
		String line;
		String[] parts;
		
		while(true){
			try{
				line = readString();
				parts = line.split(",");
				v = Double.parseDouble(parts[0]);
				w = Double.parseDouble(parts[1]);
			}
			catch(Exception e){
				break;
			}
			values.add(v);
			weight.add(w);
			
			
		}
		stdin.close();
	}
	
	public ArrayList<Double> getParticlesValue(){
		return values;
	}
	public ArrayList<Double> getParticlesWeight(){
		return weight;
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
