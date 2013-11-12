package PSO;

public class Circle extends PSO_problem{
	private static int numberOfParticles = 100;
	private static double f, bestF;
	
	public Circle(int dimensions, int lowerCap, int upperCap){
		super(dimensions, numberOfParticles, lowerCap, upperCap);
		f = 100000000.0;
		bestF = f;
		initializeParticles();
		iter();
	}
	
	public void iter(){
		int iter = 0;
		while(iter < 1000 && f > 0.001){
			System.out.println("\niter: " + iter);
			updateParticles();
			f = f();
			if(f<bestF)
				bestF = f;
			System.out.println("current F: "+f);
			System.out.println("best    F: "+bestF);
			iter+=1;
		}
		System.out.println("iterations: " + iter);
	}
	
	public double f(){
		double f = 0;
		for (int i = 0; i < numberOfParticles; i++) {
			System.out.println(particles[i]);
			double[] x = particles[i].getPositionVector();
			f += fValueOfArray(x);
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
	
	public Particle[] getparticles(){
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
		new Circle(3, 0, 1);
	}
}
