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
	
	public static void main(String[] args) {
		try {
			TextReader i = new TextReader("pso-packages.txt");
//			int[] valIndexes = {9,33,36,103,143,144,151,190,199,205,240,274,298,310,326,336,337,406,430,460,487,552,563,577,604,606,617,620,688,697,736,853,880,915,919,939,977,1028,1031,1061,1082,1091,1144,1162,1265,1286,1337,1341,1367,1373,1388,1398,1475,1504,1516,1563,1592,1684,1688,1701,1704,1737,1744,1774,1798,1838,1853,1879,1882,1924,1937,1999};
//			int[] valIndexes = {20,49,95,138,209,212,219,235,240,263,298,326,337,347,350,353,375,407,555,582,588,606,620,629,638,662,672,697,740,741,743,786,841,867,977,1087,1136,1162,1169,1182,1196,1200,1221,1259,1299,1335,1374,1472,1515,1522,1563,1592,1639,1655,1675,1710,1715,1742,1748,1774,1847,1866,1906,1944,1965,1990,1998,1999};
//			int[] valIndexes = {38,88,105,131,151,167,221,347,362,542,574,582,594,762,836,853,1005,1018,1095,1114,1162,1177,1200,1204,1212,1222,1265,1337,1411,1446,1476,1531,1554,1587,1684,1697,1742,1940,1961,1975};
			int[] valIndexes = {22,33,92,120,131,138,160,200,282,326,399,402,407,444,483,548,552,563,569,580,599,626,628,655,736,743,777,848,867,871,967,971,988,1028,1096,1108,1144,1182,1212,1224,1242,1246,1273,1292,1385,1462,1516,1521,1630,1641,1699,1710,1811,1847,1848,1906,1924};
			System.out.println(valIndexes.length);
			double sum = 0, sum2 = 0, sum3 = 0;
			for (int j = 0; j < valIndexes.length; j++) {
				sum+=i.values.get(valIndexes[j]);
				sum2+=i.weight.get(valIndexes[j]);
				sum3+=i.volume.get(valIndexes[j]);
			}
			System.out.println(sum + " " + sum2 + " " + sum3);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
