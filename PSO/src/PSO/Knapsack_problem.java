package PSO;

import java.util.ArrayList;
import java.util.Arrays;

public class Knapsack_problem{
	double maxWeight, maxVolume;
	ArrayList<Double> w,val,vol;
	private Knapsack_Particle[] particles;
	private int dimensions;
	private int numberOfParticles;
	private double lowerCap,upperCap;
	
	
	public Knapsack_problem(int dimensions, int numParticles, double lowerCap, double upperCap, boolean inertia) {
		
		
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
		particles[0] = new Knapsack_Particle(dimensions, 0.5, 0.5, inertia, 1000, lowerCap, upperCap,0);
		(particles[0]).setValue(val);
		(particles[0]).setWeight(w);
		(particles[0]).setVolume(vol);
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i] = new Knapsack_Particle(dimensions, 0.5, 0.5, inertia, 1000, lowerCap, upperCap,i);
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
		
		particles[0].setGlobal(global);
//		System.out.println(Arrays.toString(global));
		
		for (int i = 0; i < dimensions; i++) {
			if(global[i]==1){
				
				System.out.print(i+  " ");
			}
		}
		this.lowerCap = lowerCap;
		this.upperCap = upperCap;
		
		
		
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
		//double currentLocalBestValue = (particles[0]).getBestLocalValue();
		double bestValue = (particles[0]).getBestValue();
		double[] bestPos = (particles[0]).getGlobal();
		boolean updated = false;
		for (int i = 0; i < particles.length; i++) {
			if((particles[i]).getValue() > (particles[i]).getBestLocalValue() &&(particles[i]).getWeight() <= maxWeight){
				(particles[i]).setLocalPosition((particles[i]).getPositionVector());
				if((particles[i]).getValue() > bestValue){
					bestPos = (particles[i]).getLocal();
					bestValue = (particles[i]).getValue(); 
					updated = true;
				}
			}
		}
		if(updated){
			(particles[0]).setGlobalPosition(bestPos);
		}
		
	}
	public void iter(){
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < particles.length; j++) {
				particles[j].nextIteration();
				updateLocal();
			}
		}
		
		
	}
	
	

	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < dimensions; i++) {
			if(particles[0].getGlobal()[i]==1){
				
				sb.append(i + " ");
			}
		}
		//sb.append(Arrays.toString(particles[0].getGlobal()));
		sb.append("\n");
		double sum =0;
		double weight  =0 ;
		for (int i = 0; i < dimensions; i++) {
			if(particles[0].getGlobal()[i]==1){
				sb.append(val.get(i)+",");
				sb.append(w.get(i) + "\t");
				sum+=val.get(i);
				weight += w.get(i);
				
			}
		}
		sb.append("\n val sum: " + sum + " wsum: " + weight);
		
		return sb.toString();
	}
	

	
	
	public static void main(String[] args) {
		Knapsack_problem kn = new Knapsack_problem(2001, 10, 0, 1, false);
		kn.iter();
		System.out.println("complete");
		System.out.println(kn);
	}
}
