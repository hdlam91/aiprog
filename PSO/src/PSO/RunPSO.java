package PSO;

import java.util.Scanner;

public class RunPSO {
	Scanner in;
	
	
	public RunPSO() {
		in = new Scanner(System.in);
		System.out.println("choose 0 for circle, and 1 for knapsack problem");
		int problem = in.nextInt();
		if(problem == 0){
			System.out.println("dimensions (int):");
			int dimension = in.nextInt();
			System.out.println("lowerCap (double):");
			double lowerCap = in.nextDouble();
			System.out.println("upperCap (double):");
			double upperCap = in.nextDouble();
			System.out.println("use nearest neighbour topolgy? (true/false):");
			boolean neighbour = in.nextBoolean();
			System.out.println("use decreasing inertia weight? (true/false):");
			boolean inertia = in.nextBoolean();
			System.out.println("number of iterations? (int):");
			int maxIter = in.nextInt();
			System.out.println("c1? (double in range 0..2):");
			double c1 = in.nextDouble();
			System.out.println("c2? (double in range 0..2):");
			double c2 = in.nextDouble();
			new Circle(dimension, lowerCap, upperCap, neighbour, inertia, maxIter, c1, c2);
		}
		else{
			System.out.println("dimensions (int):");
			int dimension = in.nextInt();
			System.out.println("num of particles? (int):");
			int numParticles = in.nextInt();
			System.out.println("lowerCap (double):");
			double lowerCap = in.nextDouble();
			System.out.println("upperCap (double):");
			double upperCap = in.nextDouble();
			System.out.println("use nearest neighbour topolgy? (true/false):");
			boolean neighbour = in.nextBoolean();
			System.out.println("use decreasing inertia weight? (true/false):");
			boolean inertia = in.nextBoolean();
			System.out.println("number of iterations? (int):");
			int maxIter = in.nextInt();
			System.out.println("volume restiction? (true/false):");
			boolean volume = in.nextBoolean();
			
			System.out.println("c1? (double in range 0..2):");
			double c1 = in.nextDouble();
			System.out.println("c2? (double in range 0..2):");
			double c2 = in.nextDouble();
			new Knapsack_problem(dimension, numParticles, lowerCap, upperCap, inertia, maxIter, volume, c1 , c2);
		}
		
	}
	
	public static void main(String[] args) {
		new RunPSO();
	}
}
