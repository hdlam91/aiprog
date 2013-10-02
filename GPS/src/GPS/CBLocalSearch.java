package GPS;

import java.util.Scanner;

public class CBLocalSearch {
	private static StateManager manager;
	private static Scanner in;
	private static int type, numRuns;
	
	private static StateManager mode(int type, int k){
		switch(type){
		case 0:
			return new QueenManager(k);
		case 1:
			return new GraphManager();
		default:
			return null;
		}
	}
	
	private static void initializer(){
		in = new Scanner(System.in);
		
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
		
		
	}
	
	/*
	public CBLocalSearch(int type){
		this.manager = mode(type);
	}*/
	
	public static void main(String[] args) {
//		initializer();
		GeneralMinConflict csp = new GeneralMinConflict(mode(0,7),200);
		State winState = csp.getGoalState();
		
		System.out.println("Iterations:" + winState.getIterations());
		System.out.println(winState);
	}
}
