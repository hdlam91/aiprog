package PSO;

import java.util.ArrayList;

public class Knapsack_Particle extends Particle{
	private static ArrayList<Double> weight, volume, value;
	private double u;
	private static double[] g;
	
	public Knapsack_Particle(int dimensions, double c1, double c2, boolean inertia, int maxIter, double lowerCap, double upperCap, int id) {
		super(dimensions, c1, c2, inertia, maxIter, lowerCap, upperCap, id);
	}
	
	
	public double[] initializeParticle(){
		double valueArea = upperCap-lowerCap;
		for (int i = 0; i < dimensions; i++) {
			x[i] = Math.random() > 0.985? 1 : 0;
			v[i] = Math.random()*valueArea;
		}
		while(getWeight()>1000){
			System.out.println("over weighted bastard!" + getWeight()  + " val: " + getValue());
			for (int i = 0; i < dimensions; i++) {
				x[i] = Math.random() > 0.99? 1 : 0;
			}
		}
		System.out.println("trimmed " + getWeight() + " val: " + getValue());
		setLocalPosition(x);
		return this.getLocal();
	}
	
	public double getWeight() {
		double totalWeight = 0;
		for (int i = 0; i < dimensions; i++) {
			if(x[i] == 1){
				totalWeight += weight.get(i);
			}
		}
		return totalWeight;
	}


	public void setWeight(ArrayList<Double> weight) {
		Knapsack_Particle.weight = weight;
	}



	public double getVolume() {
		double totalVolume = 0;
		for (int i = 0; i < dimensions; i++) {
			if(x[i] == 1){
				totalVolume += volume.get(i);
			}
		}
		return totalVolume;
	}



	public void setVolume(ArrayList<Double> volume) {
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
		double totalValue = 0;
		for (int i = 0; i < dimensions; i++) {
			if(x[i] == 1){
				totalValue += value.get(i);
			}
		}
		return totalValue;
	}

	

	public void setValue(ArrayList<Double> value) {
		Knapsack_Particle.value = value;
	}



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
		if(inertia)
			adjustInertia();
	}
	
	
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
		//is this correct?
		double value = 1/(1+Math.pow(Math.E,-v));
		double sigmoidCap = 4.25;
		if(value>sigmoidCap){
			value = sigmoidCap;
		}
		else if(value < -sigmoidCap)
			value = -sigmoidCap;
		return value;
		
	}
	
	public void setGlobal(double[] glob){
		g = glob.clone();
	}
	public double[] getGlobal(){
		return g.clone();
	}
	
	
}
