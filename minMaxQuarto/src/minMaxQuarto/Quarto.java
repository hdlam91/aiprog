package minMaxQuarto;

import java.util.Scanner;

public class Quarto {
	
	private static String mode(int type){
		switch(type){
			case 0:
				return "human";
			case 1:
				return "random";
			case 2:
				return "novice";
			case 3:
				return "minimax";
			default:
				return "";
		}
		
	}
	
	public static void main(String[] args) {
		{
			//0 = human, 1 = random, 2 = novice, 3 = minmax
		      int player1 = -1;
		      int player1depth = 2;
		      int player2 = -1;
		      int player2depth = 2;
		      int b,c;
		 
		      Scanner in = new Scanner(System.in);
		      
		      //chooses mode for p1
		      while(!(player1 < 4 && player1 >= 0)){
		    	  System.out.println("choose mode for player 1:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
		    	  player1 = in.nextInt();
		      }
		      System.out.println("You chose "+ mode(player1) + " for player1");
		      
		      //chose depth
		      if(player1 == 3){
		    	  System.out.println("choose depth for minimax");
		    	  player1depth = in.nextInt();
		    	  
		      }
		      System.out.println("\n-----------------------------");
		      
		      
		      while(!(player2 < 4 && player2 >= 0)){
		    	  System.out.println("choose mode for player 2:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
		    	  player2 = in.nextInt();
		      }
		      System.out.println("You chose "+ mode(player2) + " for player2");
		      if(player2 == 3){
		    	  System.out.println("choose depth for minimax");
		    	  player2depth = in.nextInt();
		    	  
		      }
		      System.out.println("\n-----------------------------");
		 
		         
		   }
	}
}