package PSO;

public class Knapsack_problem extends PSO_problem{
	double maxWeight, maxVolume;
	
	public Knapsack_problem(int dimensions, int numParticles, double lowerCap, double upperCap, boolean inertia) {
		super(dimensions, numParticles, lowerCap, upperCap, inertia, true);
		try {
			TextReader t = new TextReader("pso-packages.txt");
			((Knapsack_Particle)particles[0]).setValue(t.getParticlesValue());
			((Knapsack_Particle)particles[0]).setWeight(t.getParticlesWeight());
			((Knapsack_Particle)particles[0]).setVolume(t.getParticlesVolume());
		} catch (Exception e) {
			System.out.println("something wrong with reading file, exiting!!!");
			System.exit(0);
			e.printStackTrace();
		}
		
		//set max
		maxWeight = 1000;
		maxVolume = 250;
		
		
	}

	
	
	
	
	
	
	
	@Override
	public double fValueOfArray(double[] arr) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
