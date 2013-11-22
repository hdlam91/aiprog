package PSO;

import java.util.ArrayList;
import java.util.Arrays;

public class Knapsack_problem{
	private double maxWeight, maxVolume;
	private ArrayList<Double> w,val,vol;
	private Knapsack_Particle[] particles;
	private int dimensions;
	private int numberOfParticles, maxIter;
	
	public Knapsack_problem(int dimensions, int numParticles, double lowerCap, double upperCap, boolean inertia, int maxIter) {
		this.maxIter = maxIter;
		this.dimensions = dimensions;
		this.numberOfParticles = numParticles;
		double [] global = null;
		double [] current = null;
		this.particles = new Knapsack_Particle[numParticles];
		try {
			TextReader t = new TextReader("pso-packages.txt");
			vol = t.getParticlesVolume();
			w = t.getParticlesWeight();
			val = t.getParticlesValue();
		} catch (Exception e) {
			System.out.println("something wrong with reading file, exiting!!!");
			System.exit(0);
			e.printStackTrace();
		}
		particles[0] = new Knapsack_Particle(dimensions, 0.5, 0.5, inertia, maxIter, lowerCap, upperCap,0);
		particles[0].setValueList(val);
		particles[0].setWeightList(w);
		particles[0].setVolumeList(vol);
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i] = new Knapsack_Particle(dimensions, 0.5, 0.5, inertia, maxIter, lowerCap, upperCap,i);
			current = particles[i].initializeParticle();
			if(global == null){
				global = current;
			}
			else{
				if(getValue(current)> getValue(global)){
					global = current;					
				}
			}
			
		}
		
		particles[0].setGlobalPosition(global);
		
		for (int i = 0; i < dimensions; i++) {
			if(global[i]==1){
				
				System.out.print(i+  " ");
			}
		}
		System.out.println();
		System.out.println("Val " + getValue(particles[0].getGlobalPosition()));
		
		//set max
		maxWeight = 1000;
		maxVolume = 250;
	}
	
	public double getValue(double[] local){
		double totalValue = 0;
		for (int i = 0; i < dimensions; i++) {
			if(local[i] == 1){
				totalValue += val.get(i);
			}
		}
		return totalValue;
	}
	
	public void updateLocal(){
		double bestValue = particles[0].getBestValue();
		double[] bestPos = particles[0].getGlobalPosition();
		boolean updated = false;
		for (int i = 0; i < particles.length; i++) {
			if(particles[i].getValue() > particles[i].getBestLocalValue() && particles[i].getWeight() <= maxWeight){
				particles[i].setLocalPosition(particles[i].getPositionVector());
				if(particles[i].getValue() > bestValue){
					bestPos = particles[i].getLocalPosition();
					bestValue = particles[i].getValue(); 
					updated = true;
				}
			}
		}
		if(updated){
			(particles[0]).setGlobalPosition(bestPos);
		}
		
	}
	public void iter(){
		for (int i = 0; i < maxIter; i++) {
			for (int j = 0; j < particles.length; j++) {
				particles[j].nextIteration();
				updateLocal();
			}
			System.out.println("iter " + i);
			System.out.println(this);
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("Current packages: ");
		for (int i = 0; i < dimensions; i++) {
			if(particles[0].getGlobalPosition()[i]==1){
				
				sb.append(i + " ");
			}
		}
		//sb.append(Arrays.toString(particles[0].getGlobal()));
		double sum = 0;
		double weight  = 0;
		for (int i = 0; i < dimensions; i++) {
			if(particles[0].getGlobalPosition()[i]==1){
				sum+=val.get(i);
				weight += w.get(i);
			}
		}
		sb.append("\nval sum: " + sum + " wsum: " + weight);
		sb.append("\n");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Knapsack_problem kn = new Knapsack_problem(2001, 400, 0, 1, true, 500);
		kn.iter();
	}
}
