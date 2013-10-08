package GPS;

import java.util.Scanner;

public class CBLocalSearch {
//	private static StateManager manager;
	private static Scanner in;
	private static int type, searchType, numRuns,inputParam,maxIter;
	private static String showOutput;
	private static int maxK = 5000;
	private static boolean showWinState;
	
	private static StateManager mode(int type, int k){
		switch(type){
		case 0:
			return new QueenManager(k);
		case 1:
			return new GraphManager("file"); //fix stuff for this
		default:
			return null;
		}
	}
	
	private static LocalSearch searchMode(int type, StateManager manager,int maxIter){
		switch(type){
		case 0:
			return new GeneralMinConflict(manager,maxIter);
		case 1:
			return new GeneralSA(0,null,null,0,0,maxIter); //Fix stuff for this
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
			    System.out.println(type);
			}
			catch(NumberFormatException ex){
				System.out.println(typ + " is not a valid number.");
			}
		}
		if(type == 0){
			while(!(inputParam>=6 && inputParam<=maxK)){
				System.out.println("K (>=6 && <=5000):");
				String k = in.next();
				try{
				    inputParam = Integer.parseInt(k);
				}
				catch(NumberFormatException ex){
					System.out.println(k + " is not a valid number.");
				}
			}
		}
		if(type==1)
			System.out.println("stuff to do at some point");
		
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
			LocalSearch search = searchMode(searchType, mode(type, inputParam), maxIter);
			State winState = search.getCompletedState();
			System.out.println("Iterations:" + winState.getIterations());
			if(showWinState){
				System.out.println(winState);
			}
		}
	}
	
	public static void main(String[] args) {

//		try {
//			GraphReader gr = new GraphReader("graph-color-1.txt");
//			System.out.println(gr+"test");		
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
//		System.out.println("stuff");

		initializer();
	}
}
