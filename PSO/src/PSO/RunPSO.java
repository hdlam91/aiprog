package PSO;

import java.util.Scanner;

public class RunPSO {
	Scanner in;
	
	
	public RunPSO() {
		in = new Scanner(System.in);
		System.out.println("choose 0 for circle, and 1 for knapsack problem, 2 for automatic 2d circle, 3 for automatic knapsack, 4 for automatic volume knapsack");
		System.out.println("doubles might be in the format \"1,3\" or \"1.3\", try both.");
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
		else if(problem == 1){
			System.out.println("dimensions (int):");
			int dimension = in.nextInt();
			System.out.println("num of particles? (int):");
			int numParticles = in.nextInt();
			System.out.println("lowerCap (double):");
			double lowerCap = in.nextDouble();
			System.out.println("upperCap (double):");
			double upperCap = in.nextDouble();
			System.out.println("use decreasing inertia weight? (true/false):");
			boolean inertia = in.nextBoolean();
			System.out.println("number of iterations? (int):");
			int maxIter = in.nextInt();
			System.out.println("volume restriction? (true/false):");
			boolean volume = in.nextBoolean();
			
			System.out.println("c1? (double in range 0..2):");
			double c1 = in.nextDouble();
			System.out.println("c2? (double in range 0..2):");
			double c2 = in.nextDouble();
			new Knapsack_problem(dimension, numParticles, lowerCap, upperCap, inertia, maxIter, volume, c1 , c2);
		}
		else if(problem == 2){
			System.out.println("running: new Circle(2, 0, 1, false, false, 1000, 0.5, 0.5);");
			new Circle(2, 0, 1, false, false, 1000, 0.5, 0.5);
		}
		else if(problem == 3){
			System.out.println("running: new Knapsack_problem(2001, 4000, 0, 1, false, 500, false, 0.5, 0.5);");
			new Knapsack_problem(2001, 4000, 0, 1, false, 500, false, 0.5, 0.5);
			
		}
		else if(problem == 4){
			System.out.println("running: new Knapsack_problem(2001, 4000, 0, 1, false, 500, true, 0.5, 0.5);");
			new Knapsack_problem(2001, 4000, 0, 1, false, 500, true, 0.5, 0.5);
			
			
		}
		
		in.close();
		
	}
	
	public static void main(String[] args) {
		new RunPSO();
	}
}
