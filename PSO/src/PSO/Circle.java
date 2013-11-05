package PSO;

public class Circle extends PSO_problem{
	private static int numberOfParticles = 20;
	
	public Circle(int dimensions, int lowerCap, int upperCap){
		super(dimensions, numberOfParticles, lowerCap, upperCap);
	}
	
	public double f(){
		double f = 0;
		for (int i = 0; i < numberOfParticles; i++) {
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
	
	public void initializeParticles(){
		double[] bestG = null;
		for (int i = 0; i < numberOfParticles; i++) {
			double[] temp = particles[i].initializeParticle(lowerCap,upperCap);
			
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
