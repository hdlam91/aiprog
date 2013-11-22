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
	
	public static void main(String[] args) {
		try {
			TextReader i = new TextReader("pso-packages.txt");
			int[] valIndexes = {9,33,36,103,143,144,151,190,199,205,240,274,298,310,326,336,337,406,430,460,487,552,563,577,604,606,617,620,688,697,736,853,880,915,919,939,977,1028,1031,1061,1082,1091,1144,1162,1265,1286,1337,1341,1367,1373,1388,1398,1475,1504,1516,1563,1592,1684,1688,1701,1704,1737,1744,1774,1798,1838,1853,1879,1882,1924,1937,1999};
			System.out.println(valIndexes.length);
			int sum = 0, sum2 = 0;
			for (int j = 0; j < valIndexes.length; j++) {
				sum+=i.values.get(valIndexes[j]);
				sum2+=i.weight.get(valIndexes[j]);
			}
			System.out.println(sum + " " + sum2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
