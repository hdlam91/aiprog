package it3105.aiprog.pso;

import java.util.ArrayList;
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
			particles[i] = new Particle(dimensions, null, 0.5, 0.5, false, 1000, lowerCap, upperCap,i);
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
		double[] currentPos = p.getPositionVector();
		if(fValueOfArray(currentPos)<fValueOfArray(bestLocal))
		{	
			p.setLocalPosition(currentPos);
			bestLocal = p.getLocal();
			if(fValueOfArray(bestLocal)<fValueOfArray(bestGlobal)){
				p.setGlobalPosition(bestLocal);
			}
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
		if(n>=numberOfParticles){
			n=numberOfParticles-1;
		}
		for (int i = 0; i < numberOfParticles; i++) {
			particles[i].nextIteration();
			updateLocal(particles[i]);
		}
		
		
		for (int i = 0; i < numberOfParticles; i++) {
			double[] neighbors = new double[n];
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
			
			System.out.println("Current particle:");
			System.out.println(particles[i]);
			
			for (int j = 0; j < numberOfParticles; j++) {
				if(i!=j){
					System.out.print("neighbor: " + j);
					System.out.print(" val: " +particles[i].getDistance(particles[j]));
					System.out.println();
				}
			}
			
			System.out.println("Neighbors:" + indexes.size());
			for (int j = 0; j < indexes.size(); j++) {
				System.out.println(particles[indexes.get(j)]);
				System.out.println("val: " + particles[i].getDistance(particles[indexes.get(j)]));
				System.out.println("\n");
			}
			System.out.println("--------------");
		}
	}
}
