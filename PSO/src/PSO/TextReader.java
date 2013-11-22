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
	ArrayList<Double> volume;
	boolean random;

	public TextReader(String file) throws Exception{
		st	= new StringTokenizer("");
		stdin = new BufferedReader(new FileReader(file));
		
		values = new ArrayList<Double>();
		weight = new ArrayList<Double>();
		volume = new ArrayList<Double>();
		readFile();
		
		random = false;
		
		if(!random){
			st = new StringTokenizer("");
			stdin = new BufferedReader(new FileReader("random.txt"));
			readVolume();
		}
		else
		{
			for (int i = 0; i < values.size(); i++) {
				volume.add(Math.random()*4+1);
			}
		}
		stdin.close();
			
	}
	
	public void readVolume() throws Exception{
		double v;
		
		while(true){
			try{
				v = Double.parseDouble(readString());				
			}
			catch(Exception e){
				break;
			}
			volume.add(v);
		}
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
	}
	
	public ArrayList<Double> getParticlesValue(){
		return values;
	}
	public ArrayList<Double> getParticlesWeight(){
		return weight;
	}
		
	public ArrayList<Double> getParticlesVolume() {
		return volume;
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

	
//	public static void main(String[] args) {
//		try {
//			TextReader i = new TextReader("pso-packages.txt");
//			System.out.println(i.volume);
//			System.out.println(i.values);
//			System.out.println(i.weight);
//			System.out.println(i.volume.size());
//			System.out.println(i.values.size());
//			System.out.println(i.weight.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
