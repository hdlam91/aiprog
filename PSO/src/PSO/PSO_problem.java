package PSO;

import java.util.ArrayList;

/**
 * This class was constructed with generality in mind. But we found that we could not take advantage of it to the extent we hoped. 
 * Nevertheless, it is used in conjunction with Circle to perform a PSO search for the (dimension)Circle problem, as well as the K-nearest-neighbour
 * (dimension)Circle problem. 
 * @author Eivind
 *
 */

public abstract class PSO_problem {
	protected Particle[] particles;
	protected int dimensions;
	protected int numberOfParticles;
	protected double lowerCap,upperCap;
	
	public PSO_problem(int dimensions, int numParticles, double lowerCap, double upperCap, boolean inertia, double c1, double c2){
		this.dimensions = dimensions;
		this.numberOfParticles = numParticles;
		this.particles = new Particle[numParticles];
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i] = new Particle(dimensions, c1, c2, inertia, 1000, lowerCap, upperCap,i);
		}
		this.lowerCap = lowerCap;
		this.upperCap = upperCap;
	}
	
	public abstract double fValueOfArray(double[] arr);
	
	public void updateParticles(){
		for (int i = 0; i < particles.length; i++) {
			particles[i].nextIteration();
		}
		
		double[] bestForThisIteration = particles[0].getGlobalPosition();
		boolean changed = false;
		for (int i = 0; i < particles.length; i++) {
			double[] currentposition = particles[i].getPositionVector();
			double[] bestLocal = particles[i].getLocalPosition();
			if(fValueOfArray(currentposition)<fValueOfArray(bestLocal)){
				particles[i].setLocalPosition(currentposition);
				if(fValueOfArray(currentposition)<fValueOfArray(bestForThisIteration)){
					bestForThisIteration = currentposition.clone();
					changed = true;
				}
			}
		}
		if(changed){
			for (int i = 0; i < particles.length; i++) {
				particles[i].setGlobalPosition(bestForThisIteration);
			}
		}
		
	}
	
	public void updateLocal(Particle p){
		double[] bestLocal = p.getLocalPosition();
		double[] bestGlobal = p.getGlobalPosition();
		double[] currentPos = p.getPositionVector();
		if(fValueOfArray(currentPos)<fValueOfArray(bestLocal))
		{	
			p.setLocalPosition(currentPos);
			bestLocal = p.getLocalPosition();
			if(fValueOfArray(bestLocal)<fValueOfArray(bestGlobal)){
				p.setGlobalPosition(bestLocal);
			}
		}
	}
	
	public void findNeighboursBest(Particle p, Particle[] neighbours){
		double[] bestGlobal = p.getGlobalPosition();
		
		for (int i = 0; i < neighbours.length; i++) {
			double[] neighbourBestGlobal = neighbours[i].getGlobalPosition();
			if(fValueOfArray(neighbourBestGlobal)<fValueOfArray(bestGlobal)){
				p.setGlobalPosition(neighbourBestGlobal);
				p.getGlobalPosition();
			}
		}
	}
	
	public void KNN(int n){
		if(n>=numberOfParticles){
			n=numberOfParticles-1;
		}
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i].nextIteration();
			updateLocal(particles[i]);
		}
		
		
		for (int i = 0; i < numberOfParticles; i++) {
			Particle[] neighbours = new Particle[n];
			ArrayList<Integer> indexes = new ArrayList<Integer>(); 
			
			for (int j = 0; j < n; j++) {
				int currentIndex = 0;
				while(indexes.contains(currentIndex)){
					currentIndex++;
				}
				for (int j2 = 0; j2 < numberOfParticles; j2++) {
					if(!indexes.contains(j2)){
						if(j2!=i){
							if(particles[i].getDistance(particles[j2])<particles[i].getDistance(particles[currentIndex])){
								currentIndex = j2;
							}
						}
					}
				}
				if(!indexes.contains(currentIndex))
					indexes.add(currentIndex);
			}
			
//			System.out.println("Current particle:");
//			System.out.println(particles[i]);
			
//			for (int j = 0; j < numberOfParticles; j++) {
//				if(i!=j){
//					System.out.print("neighbor: " + j);
//					System.out.print(" val: " +particles[i].getDistance(particles[j]));
//					System.out.println();
//				}
//			}
			
//			System.out.println("Neighbors:" + indexes.size());
			for (int j = 0; j < indexes.size(); j++) {
//				System.out.println(particles[indexes.get(j)]);
//				System.out.println("val: " + particles[i].getDistance(particles[indexes.get(j)]));
//				System.out.println("\n");
				neighbours[j] = particles[indexes.get(j)];
			}
			
			findNeighboursBest(particles[i], neighbours);
			
			
//			System.out.println("Current: " + particles[i]);
//			for (int j = 0; j < neighbours.length; j++) {
//				System.out.println("Neighbours: " + neighbours[j]);
//			}
			
//			System.out.println("--------------");
		}
	}
}
