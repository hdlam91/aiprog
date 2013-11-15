package it3105.aiprog.pso;

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
			particles[i] = new Particle(dimensions, null, 0.5, 0.5, false, 1000, lowerCap, upperCap);
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
				p = particles[i].getLocal();
				
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
			}
		}
	}
	
	public void KNN(int n){
//		for (int i = 0; i < numberOfParticles; i++) {
//			particles[i].nextIteration();
//		}
//		
//		for (int i = 0; i < numberOfParticles; i++) {
//			Particle[] neighbours = new Particle[n];
//			Particle[] toCheck = particles.clone();
//			toCheck[i] = null;
//			for (int j = 0; j < n; j++) {
//				int minIndex = 0;
//				for(int j2 = 0; j2 < numberOfParticles; j2++){
//					if(toCheck[j2] == null)
//						break;
//					if(particles[i].getDistance(toCheck[minIndex]) > particles[i].getDistance(toCheck[j2]) && minIndex != i){
//						minIndex = j2;
//					}
//				}
//				neighbours[j] = toCheck[minIndex];
//				toCheck[minIndex] = null;
//			}
//			System.out.println(particles[i]+ "kneighbours");
//			for (int j = 0; j < n; j++) {
//				System.out.println(neighbours[j]);
//				if(neighbours[j]!= null){
//				System.out.println(particles[i].getDistance(neighbours[j]));
//				}
//			
//			}
//			System.out.println("------------------------------------------------------------------");
//			
//		}
	
	}
}
