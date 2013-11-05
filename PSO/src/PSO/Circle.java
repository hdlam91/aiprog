package PSO;

public class Circle extends PSO_problem{
	private static int numberOfParticles = 20;
	
	public Circle(int dimensions){
		super(dimensions, numberOfParticles);
	}
	
	public double f(){
		double f = 0;
		for (int i = 0; i < numberOfParticles; i++) {
			for (int j = 0; j < dimensions; j++) {
				f += Math.pow(particles[i].getPositionVector()[j],2);
			}
		}
		return f;
	}
}
