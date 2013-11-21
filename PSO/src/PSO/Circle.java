package PSO;

public class Circle extends PSO_problem{
	private static int numberOfParticles = 100;
	private static double f, bestF;
	int iter = 0;
	int goal;
	
	public Circle(int dimensions, double lowerCap, double upperCap, boolean neighbour, boolean inertia){
		super(dimensions, numberOfParticles, lowerCap, upperCap, inertia);
		f = 100000000.0;
		bestF = f;
		initializeParticles();
		iter(neighbour);
		goal= 0;
	}
	
	public void iter(boolean neighbour){
		while(iter < 1000 && f > 0.001){
			System.out.println("\niter: " + (iter+1));
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
			System.out.println("current F: "+f);
			System.out.println("best    F: "+bestF);
			iter+=1;
			System.out.println("iterations: " + iter);
		}
		if(iter == 1000)
		{
			goal = 1;
		}
		else
			goal = 2;
	}
	
	public void next(boolean neighbour){
		if(iter < 1000 && f > 0.001){
			System.out.println("\niter: " + (iter+1));
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
			System.out.println("current F: "+f);
			System.out.println("best    F: "+bestF);
			iter+=1;
			System.out.println("iterations: " + iter);
		}
		else if(iter == 1000)
		{
			goal = 1;
		}
		else
			goal = 2;
		
	}
	
	public int goalReached(){
		return goal;
		
	}
	
	public double f(boolean neighbour){

		if(!neighbour)
			return fValueOfArray(particles[0].getGlobal());
		else
		{
			double f = Double.MAX_VALUE;
			for (int i = 0; i < numberOfParticles; i++) {
				
				double[] g = particles[i].getGlobal();
				if(fValueOfArray(g)  < f){
					f = fValueOfArray(g);
				}
				
			}
		}
		return f;
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
			System.out.println("Init:");
			System.out.println(particles[i]);
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) {
		new Circle(3, 0, 10, false, true);
	}
}
