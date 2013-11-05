package PSO;

import java.util.Arrays;

public abstract class PSO_problem {
	protected Particle[] particles;
	protected int dimensions;
	protected int numberOfParticles;
	protected int lowerCap,upperCap;
	
	public PSO_problem(int dimensions, int numParticles, int lowerCap, int upperCap){
		this.dimensions = dimensions;
		this.numberOfParticles = numParticles;
		this.particles = new Particle[numParticles];
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i] = new Particle(dimensions, null, 0.1, 0.5, false, 1000);
		}
		this.lowerCap = lowerCap;
		this.upperCap = upperCap;
	}
	
	public abstract double fValueOfArray(double[] arr);
	
	public void updateParticles(){
		boolean updatedGlobal = false;
		double[] bestGlobal = null;
		
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i].nextIteration();
			
			double[] x = particles[i].getPositionVector();
			double[] p = particles[i].getLocal();
			
			
			if(fValueOfArray(x)<fValueOfArray(p)){
				particles[i].setLocalPosition(x);
				p = x;
				
				double[] g = particles[i].getGlobal();
				
				if(!updatedGlobal){
					if(fValueOfArray(p) < fValueOfArray(g)){
						updatedGlobal = true;
						bestGlobal = p;
					}
				}
				else{ 
					if(fValueOfArray(p) < fValueOfArray(bestGlobal)){
						bestGlobal = p;
					}
				}
			}
		}
		
		if(updatedGlobal){
			for (int i = 0; i < numberOfParticles; i++) {
				particles[i].setGlobalPosition(bestGlobal);
//				System.out.println(particles[i]);
			}
		}
	}
}
