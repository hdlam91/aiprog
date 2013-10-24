package GPS;

import java.util.Scanner;

/**
 * The main class to run the code. Handles I/O and delegates tasks to the StateManager and the chosen local search algorithm
 * @author Eivind
 *
 */

public class CBLocalSearch {

	private static Scanner in;
	private static int type, searchType, numRuns,inputParam,maxIter;
	private static int iterationCount, iterationCompletedCount,fewestIterations,topNumberIterations;
	private static String showOutput;
	private static int maxK = 5000, maxK2 = 10;
	private static boolean showWinState, SA;
	private static long timeSpentTotal;
	private static double completedStateCount, totalF, stdF;
	private static GraphReader gr;
	private static double[] stateFValues;
	private static int[] iterationsOnRun;
	
	
	private static StateManager mode(int type, int k,GraphReader gr){
		switch(type){
		case 0:
			return new QueenManager(k);
		case 1:		
			return new GraphManager("graph-color-"+k+".txt",gr);
		case 2:		
			return new ThirdPuzzleManager(k);
		default:
			return null;
		}
	}
	
	private static LocalSearch searchMode(int type, StateManager manager,int maxIter){
		switch(type){
		case 0:
			return new GeneralMinConflict(manager,maxIter);
		case 1:
			return new GeneralSA(manager,maxIter);
		default:
			return null;
		}
	}
	
	private static void initializer(){
		in = new Scanner(System.in);
		
		type = -1;
		inputParam = -1;
		searchType = -1;
		showOutput = "";
		timeSpentTotal = 0;
		
		fewestIterations = Integer.MAX_VALUE;
		topNumberIterations = 0;
		iterationCompletedCount = 0;
		completedStateCount = 0;
		
		
		while(numRuns<=0){
			System.out.println("Number of runs:");
			String num = in.next();
			try{
			    numRuns = Integer.parseInt(num);
			}
			catch(NumberFormatException ex){
				System.out.println(num + " is not a valid number.");
			}
		}
		
		while(maxIter<=0){
			System.out.println("Max iterations per run:");
			String iter = in.next();
			try{
			    maxIter = Integer.parseInt(iter);
			}
			catch(NumberFormatException ex){
				System.out.println(iter + " is not a valid number.");
			}
		}
		
		while(!(type<3 && type>=0)){
			System.out.println("Type of problem (0:K-Queens), (1:Graph-Coloring), (2: Modified Sudoku) :");
			String typ = in.next();
			try{
			    type = Integer.parseInt(typ);
			}
			catch(NumberFormatException ex){
				System.out.println(typ + " is not a valid number.");
			}
		}
		if(type == 0){
			while(!(inputParam>=6 && inputParam<=maxK)){
				System.out.println("K (>=6 && <="+maxK+"):");
				String k = in.next();
				try{
				    inputParam = Integer.parseInt(k);
				}
				catch(NumberFormatException ex){
					System.out.println(k + " is not a valid number.");
				}
			}
		}
		else if(type==1){
			while(!(inputParam>=1 && inputParam<=3)){
				System.out.println("K (>=1 && <=3):");
				String k = in.next();
				try{
				    inputParam = Integer.parseInt(k);
				}
				catch(NumberFormatException ex){
					System.out.println(k + " is not a valid number.");
				}
			}
			try {
				gr = new GraphReader("graph-color-"+inputParam+".txt");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(type == 2){
			while(!(inputParam>=2 && inputParam<=maxK2)){
				System.out.println("K (>=2 && <="+maxK2+"):");
				String k = in.next();
				try{
					inputParam = Integer.parseInt(k);
				}
				catch(NumberFormatException ex){
					System.out.println(k + " is not a valid number.");
				}
			}
		}
			
		while(!(searchType<2 && searchType>=0)){
			System.out.println("Type of problem (0:MinConflicts), (1:Simulated annealing) :");
			String typ = in.next();
			try{
			    searchType = Integer.parseInt(typ);
			}
			catch(NumberFormatException ex){
				System.out.println(typ + " is not a valid number.");
			}
		}
		
		if(searchType == 1)
			SA = true;
		else
			SA = false;
		
		if(SA)
			stateFValues = new double[numRuns];
		
		iterationsOnRun = new int[numRuns];
		
		while(!(showOutput.equals("y") || showOutput.equals("n"))){
			System.out.println("Show the winning state (not recommended for the big problems)? (y or n) ");
			showOutput = in.next();
		}
		if(showOutput.equals("y"))
			showWinState = true;
		else if(showOutput.equals("n"))
			showWinState = false;
		
		for (int i = 0; i < numRuns; i++) {
			System.out.println("\n\nRun number: " + (i+1));
			
			long startTime = System.currentTimeMillis();
			
			LocalSearch search = searchMode(searchType, mode(type, inputParam,gr), maxIter);
			State winState = search.getFinalState();
			boolean completedState = search.getManager().getGoalState();
			if(showWinState){
				System.out.println(winState);
			}
			
			long endTime = System.currentTimeMillis();
			
			System.out.println("Iterations for this run:" + winState.getIterations());
			System.out.println("Time spent on this run: " + (endTime-startTime) + "ms");
			timeSpentTotal+=(endTime-startTime);
			
			if(SA){
				totalF += winState.getF();
				stateFValues[i] = winState.getF();
			}
			
			iterationCount += winState.getIterations();
			iterationsOnRun[i] = winState.getIterations();
			
			if(completedState){
				iterationCompletedCount += winState.getIterations();
				if(winState.getIterations()<fewestIterations)
					fewestIterations = winState.getIterations(); 
				if(winState.getIterations()>topNumberIterations)
					topNumberIterations = winState.getIterations();
				completedStateCount+=1;
			}
		}
		
		System.out.println("Total time spent: " + timeSpentTotal + "ms");
		System.out.println("Total iterations: " + iterationCount);
		System.out.println("Total iterations for all goal states: " + iterationCompletedCount);
		System.out.println("Runs which resulted in a goal state: " + (int)completedStateCount + " out of " + numRuns);
		
		if(SA){
			double avgF = totalF/numRuns;
			System.out.println("Average F value " + avgF);
			
			double variance = 0;
			for (int i = 0; i < numRuns; i++) {
				variance += Math.pow(avgF - (stateFValues[i]),2);
			}
			variance = variance/numRuns;
			stdF = Math.sqrt(variance);
			System.out.println("Standard deviation of F: " + stdF);
		}
		
		if(completedStateCount != 0){
			System.out.println("Run reaching a goal state with the fewest iterations: " + fewestIterations);
			System.out.println("Run reaching a goal state with the most iterations: " + topNumberIterations);
			System.out.println("Average iterations per run reaching a goal state: " + ((iterationCompletedCount/completedStateCount)));
		}
		
		int avgIter = iterationCount/numRuns;
		double varianceIter = 0;
		for (int i = 0; i < numRuns; i++) {
			varianceIter+=iterationsOnRun[i];
		}
		varianceIter=varianceIter/numRuns;
		double stdIter = Math.sqrt(varianceIter);
		System.out.println("Average iterations per run: " + avgIter);
		System.out.println("Standard deviation for the iterations per run: " + stdIter);
	}
	
	public static void main(String[] args) {
		initializer();
	}
}
