package PSO;

import java.util.ArrayList;

/**
 * This class initializes the chosen number of Knapsak_Particles, and performs a search after a solution in the search space. 
 * Thus finding the best possible value (or fitness) without exceeding the weight or volume cap. 
 * @author Eivind
 *
 */

public class Knapsack_problem{
	private double maxWeight, maxVolume;
	private ArrayList<Double> w,val,vol;
	private Knapsack_Particle[] particles;
	private int dimensions;
	private int numberOfParticles, maxIter, numberOfPackages;
	
	private double valueOfGlob, weightOfGlob, volumeOfGlob;
	private static double[] glob;
	
	private long initTime;
	private boolean volume;
	
	public Knapsack_problem(int dimensions, int numParticles, double lowerCap, double upperCap, boolean inertia, int maxIter, boolean volume, double c1, double c2) {
		initTime = System.currentTimeMillis();
		this.maxIter = maxIter;
		this.dimensions = dimensions;
		this.numberOfParticles = numParticles;
		
		this.volume = volume;
		
		this.maxWeight = 1000;
		this.maxVolume = 250;
		
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
		particles[0] = new Knapsack_Particle(dimensions, c1, c2, inertia, maxIter, lowerCap, upperCap,0);
		particles[0].setValueList(val);
		particles[0].setWeightList(w);
		particles[0].setVolumeList(vol);
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i] = new Knapsack_Particle(dimensions, c1, c2, inertia, maxIter, lowerCap, upperCap,i);
			current = particles[i].initializeParticle(maxWeight,maxVolume,volume);
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
		setGlob(global);
		updateGlob();
		
		System.out.println("Number of packages: "+ getNumberOfPackagesGlob() + ", intial packages:");
		for (int i = 0; i < dimensions; i++) {
			if(global[i]==1){
				
				System.out.print(i+  " ");
			}
		}
		System.out.println();
		System.out.print("Val: " + getValueOfGlob()+ ", Weight: " + getWeightOfGlob());
		if(volume)
			System.out.print(", Volume: " + getVolumeOfGlob());
		System.out.print("\n");
		
		iter();
	}
	
	public void iter(){
		for (int i = 0; i < maxIter; i++) {
			for (int j = 0; j < particles.length; j++) {
				particles[j].nextIteration();
				updateParticles(particles[j]);
			}
//			Plot numbers:
//			System.out.println(getValueOfGlob());
//			end plot numbers
			System.out.print("iter " + i);
			System.out.print(" val: " + getValueOfGlob());
			System.out.print(" weight: " + getWeightOfGlob());
			if(volume){
				System.out.print(" volume: " + getVolumeOfGlob());
			}
			System.out.print(" number of packages: "+ getNumberOfPackagesGlob());
			System.out.print("\n");
		}
		System.out.println();
		System.out.println(this);
		System.out.println("time spent " + (System.currentTimeMillis()-this.initTime) +"ms");
	}
	
	public void updateParticles(Knapsack_Particle p){
		double bestValue = getValueOfGlob();
		double[] bestPos = getGlob();
		boolean updated = false;
		if(volume){
			if(p.getValue() > p.getBestLocalValue() && p.getWeight() <= maxWeight && p.getVolume() <= maxVolume){
				p.setLocalPosition(p.getPositionVector());
				p.updateLocalValue();
				if(p.getValue() > bestValue){
					bestPos = p.getLocalPosition();
					bestValue = p.getValue(); 
					updated = true;
				}
			}
		}
		else{
			if(p.getValue() > p.getBestLocalValue() && p.getWeight() <= maxWeight){
				p.setLocalPosition(p.getPositionVector());
				p.updateLocalValue();
				if(p.getValue() > bestValue){
					bestPos = p.getLocalPosition();
					bestValue = p.getValue(); 
					updated = true;
				}
			}
		}
		if(updated){
			p.setGlobalPosition(bestPos);
			setGlob(bestPos);
			updateGlob();
		}
	}
	
	
	public void updateGlob(){
		this.valueOfGlob = 0; this.weightOfGlob = 0; this.volumeOfGlob = 0; this.numberOfPackages = 0;
		for (int i = 0; i < dimensions; i++) {
			if(glob[i] == 1){
				this.valueOfGlob += val.get(i);
				this.weightOfGlob += w.get(i);
				this.volumeOfGlob += vol.get(i);
				this.numberOfPackages +=1;
			}
		}
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
	
	public static double[] getGlob() {
		return glob;
	}

	public static void setGlob(double[] glob) {
		Knapsack_problem.glob = glob.clone();
	}
	
	public double getValueOfGlob(){
		return valueOfGlob;
	}
	
	public double getWeightOfGlob(){
		return weightOfGlob;
	}
	
	public double getVolumeOfGlob(){
		return volumeOfGlob;
	}
	
	public int getNumberOfPackagesGlob(){
		return numberOfPackages;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("Number of packages: " + getNumberOfPackagesGlob());
		sb.append(", Current packages: \n");
		for (int i = 0; i < dimensions; i++) {
			if(getGlob()[i]==1){
				sb.append(i + " ");
			}
		}
		sb.append("\nval: " + getValueOfGlob() + ", weight: " + getWeightOfGlob());
		if(this.volume){
			sb.append(", volume: " + getVolumeOfGlob());
		}
		sb.append("\n");
		
		return sb.toString();
	}
}
