package PSO;

public class Knapsack_Particle extends Particle{
	private double weight, volume, value;
	
	
	public Knapsack_Particle(int dimensions, double[] goal, double c1, double c2, boolean inertia, int maxIter, double lowerCap, double upperCap, int id, double value, double weight, double volume) {
		super(dimensions, goal, c1, c2, inertia, maxIter, lowerCap, upperCap, id);
		this.weight = weight;
		this.volume = volume;
		this.value = value;
	}
}
