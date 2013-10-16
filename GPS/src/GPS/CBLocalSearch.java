package GPS;

import java.util.Scanner;

public class CBLocalSearch {

	private static Scanner in;
	private static int type, searchType, numRuns,inputParam,maxIter;
	private static int iterationCount,fewestIterations,topNumberIterations;
	private static String showOutput;
	private static int maxK = 5000;
	private static boolean showWinState;
	private static long timeSpentTotal;
	private static double completedStateCount;
	private static GraphReader gr;
	
	
	private static StateManager mode(int type, int k,GraphReader gr){
		switch(type){
		case 0:
			return new QueenManager(k);
		case 1:		
			return new GraphManager("graph-color-"+k+".txt",gr);
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
		iterationCount = 0;
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
		
		while(!(type<2 && type>=0)){
			System.out.println("Type of problem (0:K-Queens), (1:Graph-Coloring) :");
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
		if(type==1){
			while(!(inputParam>=0 && inputParam<=3)){
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
			
			if(completedState){
				iterationCount += winState.getIterations();
				if(winState.getIterations()<fewestIterations)
					fewestIterations = winState.getIterations(); 
				if(winState.getIterations()>topNumberIterations)
					topNumberIterations = winState.getIterations();
				completedStateCount+=1;
			}
		}
		
		System.out.println("Total time spent: " + timeSpentTotal + "ms");
		System.out.println("Total iterations for all goal states: " + iterationCount);
		System.out.println("Runs which resulted in a goal state: " + (int)completedStateCount + " ouf of " + numRuns);
		
		if(completedStateCount != 0){
			System.out.println("Run reaching a goal state with the fewest iterations: " + fewestIterations);
			System.out.println("Run reaching a goal state with the most iterations: " + topNumberIterations);
			System.out.println("Average iterations per run reaching a goal state: " + ((iterationCount/completedStateCount)));
		}
		
		
	}
	
	public static void main(String[] args) {
		initializer();
	}
}
