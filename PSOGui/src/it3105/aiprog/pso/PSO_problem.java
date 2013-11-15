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
		for (int i = 0; i < particles.length; i++) {
			particles[i].nextIteration();
		}
		
		double[] bestForThisIteration = particles[0].getGlobal();
		for (int i = 0; i < particles.length; i++) {
			double[] currentposition = particles[i].getPositionVector();
			double[] bestLocal = particles[i].getLocal();
			//double[] bestGlobal = particles[i].getGlobal();
			if(fValueOfArray(currentposition)<fValueOfArray(bestLocal)){
				particles[i].setLocalPosition(currentposition);
				if(fValueOfArray(currentposition)<fValueOfArray(bestForThisIteration)){
					bestForThisIteration = currentposition.clone();
				}
			}
		}
		for (int i = 0; i < particles.length; i++) {
			particles[i].setGlobalPosition(bestForThisIteration);
		}
		
		
	}
	
	public void updateLocal(Particle p){
		double[] bestLocal = p.getLocal();
		double[] bestGlobal = p.getGlobal();
		
		if(fValueOfArray(bestLocal)<fValueOfArray(bestGlobal)){
			p.setGlobalPosition(bestLocal);
		}
	}
	
	public void findNeighboursBest(Particle p, Particle[] neighbour){
		double[] bestGlobal = p.getGlobal();
		
		for (int i = 0; i < neighbour.length; i++) {
			double[] neighbourBestGlobal = neighbour[i].getGlobal();
			if(fValueOfArray(bestGlobal)>fValueOfArray(neighbourBestGlobal)){
				p.setGlobalPosition(neighbourBestGlobal);
				p.getGlobal();
			}
		}
		
		
	}
	
	public void KNN(int n){
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i].nextIteration();
		}
		
		for (int i = 0; i < numberOfParticles; i++) {
			Particle[] neighbours = new Particle[n];
			Particle[] toCheck = particles.clone();
			toCheck[i] = null;
			for (int j = 0; j < n; j++) {
				int minIndex = 0;
				for(int j2 = 0; j2 < numberOfParticles; j2++){
					if(toCheck[j2] == null)
						break;
					if(particles[i].getDistance(toCheck[minIndex]) > particles[i].getDistance(toCheck[j2]) && minIndex != i){
						minIndex = j2;
					}
				}
				neighbours[j] = toCheck[minIndex];
				toCheck[minIndex] = null;
			}
			
			System.out.println("------------------------------------------------------------------");
			
		}
	
	}
}
