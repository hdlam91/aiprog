package PSO;

public class Knapsack_Particle extends Particle{
	private double weight, volume, value;
	private double u = Math.random();
	
	
	public Knapsack_Particle(int dimensions, double[] goal, double c1, double c2, boolean inertia, int maxIter, double lowerCap, double upperCap, int id, double value, double weight, double volume) {
		super(dimensions, goal, c1, c2, inertia, maxIter, lowerCap, upperCap, id);
		this.weight = weight;
		this.volume = volume;
		this.value = value;
	}
	
	
	public void nextIteration(){
//		for (int i = 0; i < v.length; i++) {
//			double r1 = Math.random(), r2 = Math.random();
//			v[i] = (w*v[i]) + (c_1 * r1 *(p[i]-x[i])) + (c_2 * r2 *(g[i]-x[i]));
//			
//			//clamping
//			if(v[i]>upperCap)
//				v[i] = upperCap;
//			else if(v[i]<-upperCap)
//				v[i] = -upperCap;
//		}
		updatePosition();
		if(inertia)
			adjustInertia();
	}
	
	
	public void updatePosition(){
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
}
