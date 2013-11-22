package PSO;

import java.util.ArrayList;

public class Knapsack_Particle extends Particle{
	private static ArrayList<Double> weight, volume, value;
	private double u;
	private static double[] g;
	private double valueOfThis, weightOfThis, volumeOfThis;
	
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
		double totalValue = 0;
		for (int i = 0; i < dimensions; i++) {
			if(p[i] == 1){
				totalValue += value.get(i);
			}
		}
		return totalValue;
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
}
