package PSO;

import java.util.ArrayList;

public class Knapsack_Particle extends Particle{
	private static ArrayList<Double> weight, volume, value;
	private double u;
	private static double[] g;
	private double valueOfThis, weightOfThis, volumeOfThis, valueOfLocal;
	
	public Knapsack_Particle(int dimensions, double c1, double c2, boolean inertia, int maxIter, double lowerCap, double upperCap, int id) {
		super(dimensions, c1, c2, inertia, maxIter, lowerCap, upperCap, id);
	}
	
	public double[] initializeParticle(){
		double valueArea = upperCap-lowerCap;
		for (int i = 0; i < dimensions; i++) {
			x[i] = Math.random() > 0.985? 1 : 0;
			v[i] = Math.random()*valueArea;
		}
		while(getInitialWeight()>1000){
			for (int i = 0; i < dimensions; i++) {
				x[i] = Math.random() > 0.99? 1 : 0;
			}
		}
		setLocalPosition(x);
		updateMeasurements();
		updateLocalValue();
		return this.getLocalPosition();
	}
	
	public void updateMeasurements(){
		this.valueOfThis = 0; 
		this.weightOfThis = 0; 
		this.volumeOfThis = 0;
		for (int i = 0; i < dimensions; i++) {
			if(x[i] == 1){
				this.weightOfThis += weight.get(i);
				this.volumeOfThis += volume.get(i);
				this.valueOfThis  += value.get(i); 
			}
		}
	}
	
	public void updateLocalValue(){
		this.valueOfLocal = 0;
		for (int i = 0; i < dimensions; i++) {
			if(p[i] == 1){
				this.valueOfLocal += value.get(i);
			}
		}
	}
	
	public double getWeight() {
		return weightOfThis;
	}
	
	public double getInitialWeight(){
		double retweight = 0;
		for (int i = 0; i < dimensions; i++) {
			if(x[i] == 1){
				retweight += weight.get(i);
			}
		}
		return retweight;
	}
			
	public void setWeightList(ArrayList<Double> weight) {
		Knapsack_Particle.weight = weight;
	}

	public double getVolume() {
		return volumeOfThis;
	}

	public void setVolumeList(ArrayList<Double> volume) {
		Knapsack_Particle.volume = volume;
	}
	
	public double getBestValue(){
		double totalValue = 0;
		for (int i = 0; i < dimensions; i++) {
			if(g[i] == 1){
				totalValue += value.get(i);
			}
		}
		return totalValue;
	}
	
	public double getBestLocalValue(){
		return valueOfLocal;
	}

	public double getValue() {
		return valueOfThis;
	}

	public void setValueList(ArrayList<Double> value) {
		Knapsack_Particle.value = value;
	}
	
	@Override
	public void nextIteration(){
		for (int i = 0; i < v.length; i++) {
			double r1 = Math.random(), r2 = Math.random();
			v[i] = (w*v[i]) + (c_1 * r1 *(p[i]-x[i])) + (c_2 * r2 *(g[i]-x[i]));
			
			//clamping
			if(v[i]>upperCap)
				v[i] = upperCap;
			else if(v[i]<lowerCap)
				v[i] = lowerCap;
		}
		updatePosition();
		updateMeasurements();
		if(inertia)
			adjustInertia();
	}
	
	@Override
	public void updatePosition(){
		u = Math.random();
		for (int i = 0; i < v.length; i++) {
			if(u < sigmoid(v[i]))
				x[i] = 1;
			else
				x[i] = 0;
		}	
	}
	
	private double sigmoid(double v){
		double value = 1/(1+Math.pow(Math.E,-v));
		double sigmoidCap = 4.25;
		if(value>sigmoidCap){
			value = sigmoidCap;
		}
		else if(value < -sigmoidCap)
			value = -sigmoidCap;
		return value;
	}
	
	@Override
	public void setGlobalPosition(double[] glob){
		g = glob.clone();
	}
	
	@Override
	public double[] getGlobalPosition(){
		return g;
	}
	
//	public static void main(String[] args) {
//		Knapsack_Particle kp = new Knapsack_Particle(2001, 0.5, 0.5, true, 500, 0, 1, 1);
//		ArrayList<Double> w,val,vol;
//		try {
//			TextReader t = new TextReader("pso-packages.txt");
//			vol = t.getParticlesVolume();
//			w = t.getParticlesWeight();
//			val = t.getParticlesValue();
//			kp.setValueList(val);
//			kp.setWeightList(w);
//			kp.setVolumeList(vol);
//		} catch (Exception e) {
//			System.out.println("something wrong with reading file, exiting!!!");
//			System.exit(0);
//			e.printStackTrace();
//		}
//		int[] valIndexes = {9,33,36,103,143,144,151,190,199,205,240,274,298,310,326,336,337,406,430,460,487,552,563,577,604,606,617,620,688,697,736,853,880,915,919,939,977,1028,1031,1061,1082,1091,1144,1162,1265,1286,1337,1341,1367,1373,1388,1398,1475,1504,1516,1563,1592,1684,1688,1701,1704,1737,1744,1774,1798,1838,1853,1879,1882,1924,1937,1999};
//		for (int i = 0; i < valIndexes.length; i++) {
//			kp.x[valIndexes[i]] = 1;
//		}
//		double sum = 0, sum2 = 0;
//		for (int i = 0; i < 2000; i++) {
//			if(kp.x[i] == 1){
//				sum += kp.value.get(i);
//				sum2 += kp.weight.get(i);
//			}
//		}
//		System.out.println(sum);
//	}
}
