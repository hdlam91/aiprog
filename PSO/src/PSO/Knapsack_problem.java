package PSO;

import java.util.ArrayList;

public class Knapsack_problem extends PSO_problem{
	double maxWeight, maxVolume;
	ArrayList<Double> w,val,vol;
	
	public Knapsack_problem(int dimensions, int numParticles, double lowerCap, double upperCap, boolean inertia) {
		super(dimensions, numParticles, lowerCap, upperCap, inertia, true);
		try {
			TextReader t = new TextReader("pso-packages.txt");
			vol = t.getParticlesVolume();
			w = t.getParticlesWeight();
			val = t.getParticlesValue();
			((Knapsack_Particle)particles[0]).setValue(val);
			((Knapsack_Particle)particles[0]).setWeight(w);
			((Knapsack_Particle)particles[0]).setVolume(vol);
		} catch (Exception e) {
			System.out.println("something wrong with reading file, exiting!!!");
			System.exit(0);
			e.printStackTrace();
		}
		
		
		//set max
		maxWeight = 1000;
		maxVolume = 250;
		
		
	}
	
	public void updateLocal(){
		//double currentLocalBestValue = ((Knapsack_Particle)particles[0]).getBestLocalValue();
		double bestValue = ((Knapsack_Particle)particles[0]).getBestValue();
		double[] bestPos = null;
		boolean updated = false;
		for (int i = 0; i < particles.length; i++) {
			if(((Knapsack_Particle)particles[i]).getValue() > ((Knapsack_Particle)particles[i]).getBestLocalValue() &&((Knapsack_Particle)particles[i]).getWeight() <= maxWeight){
				((Knapsack_Particle)particles[i]).setLocalPosition(((Knapsack_Particle)particles[i]).getPositionVector());
				if(((Knapsack_Particle)particles[i]).getValue() > bestValue){
					bestPos = ((Knapsack_Particle)particles[i]).getPositionVector();
					bestValue = ((Knapsack_Particle)particles[i]).getValue(); 
					updated = true;
				}
			}
		}
		if(updated){
			for (int i = 0; i < particles.length; i++) {
				((Knapsack_Particle)particles[i]).setGlobalPosition(bestPos);
			}
		}
		
	}
	
	
//	public void updateGlobal(){
//		double currentBestValue = ((Knapsack_Particle)particles[0]).getBestValue();
//		double[] bestPos = null;
//		boolean updated = false;
//		for (int i = 0; i < particles.length; i++) {
//			if(((Knapsack_Particle)particles[i]).getValue() > currentBestValue &&((Knapsack_Particle)particles[i]).getWeight() < maxWeight){
//				currentBestValue = ((Knapsack_Particle)particles[i]).getValue();
//				bestPos = particles[i].getPositionVector();
//				updated = true;
//			}
//		}
//		if(updated){
//			for (int i = 0; i < particles.length; i++) {
//				((Knapsack_Particle)particles[i]).setGlobalPosition(bestPos);
//			}
//		}
//	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < particles[0].getDimensions(); i++) {
			sb.append(particles[0].getGlobal()[i]);
		}
		sb.append("\n");
		for (int i = 0; i < particles[0].getDimensions(); i++) {
			if(particles[0].getGlobal()[i]==1){
				sb.append(val.get(i) + "\t");
			}
		}
		
		
		return sb.toString();
	}
	
	
	
	
	
	
	
	@Override
	public double fValueOfArray(double[] arr) {
		//not used
		return 0;
	}
	
	
	public static void main(String[] args) {
		Knapsack_problem kn = new Knapsack_problem(2001, 1, 0, 1, false);
		kn.particles[0].initializeParticle();
	}
}
