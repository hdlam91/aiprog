package PSO;

/**
 * This class handles the specific methods required to find a solution to the (dimension)Circle problem.
 * @author Eivind
 *
 */

public class Circle extends PSO_problem{
	private static int numberOfParticles = 20;
	private static double f, bestF;
	int iter;
	int maxIter;
	
	public Circle(int dimensions, double lowerCap, double upperCap, boolean neighbour, boolean inertia, int maxIter, double c1, double c2){
		super(dimensions, numberOfParticles, lowerCap, upperCap, inertia, c1, c2);
		f = Double.MAX_VALUE;
		iter = 0;
		bestF = f;
		this.maxIter = maxIter;
		initializeParticles();
		iter(neighbour);
	}
	
	public void iter(boolean neighbour){
		while(iter < maxIter && f > 0.001){
			if(!neighbour){
				updateParticles();
				f = f(false);
			}
			else{
				KNN(3);
				f = f(true);
			}
			
			if(f<bestF)
				bestF = f;
			iter+=1;
			System.out.print("iteration: " + iter);
			System.out.print(" F: "+bestF);
			System.out.print("\n");
		}
	}
	
	//this is used for exporting to matlab for plot creating
	public void plot(double f){
		System.out.println(f);
	}
	
		
	public double f(boolean neighbour){
		if(!neighbour)
			return fValueOfArray(particles[0].getGlobalPosition());
		else
		{
			double f = Double.MAX_VALUE;
			for (int i = 0; i < numberOfParticles; i++) {
				
				double[] g = particles[i].getGlobalPosition();
				if(fValueOfArray(g) < f){
					f = fValueOfArray(g);
				}
			}
			return f;
		}
	}
	
	public double fValueOfArray(double[] arr){
		double F = 0;
		for (int j = 0; j < dimensions; j++) {
			F += Math.pow(arr[j],2);
		}
		return F;
	}
	
	public Particle[] getParticles(){
		return particles;
		
	}
	
	public void initializeParticles(){
		double[] bestG = null;
		for (int i = 0; i < numberOfParticles; i++) {
			double[] temp = particles[i].initializeParticle();
			if(i==0)
				bestG = temp;
			else if(fValueOfArray(temp) < fValueOfArray(bestG))
				bestG = temp;
		}
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i].setGlobalPosition(bestG);
		}
	}
}
