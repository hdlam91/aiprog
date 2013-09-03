package minMaxQuarto;

import java.util.Scanner;

public class Quarto {
	
	private static Player Player1, Player2;
	
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
	
	private static void StartGame(){
		// 0 = human, 1 = random, 2 = novice, 3 = minmax
		Player1 = new Player();
		Player2 = new Player();
		
		Scanner in = new Scanner(System.in);

		// chooses mode for p1
		while (!(Player1.getType() < 4 && Player1.getType() >= 0)) {
			System.out
					.println("choose mode for player 1:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
			Player1.setType(in.nextInt());
		}
		System.out.println("You chose " + mode(Player1.getType()) + " for player1");

		// chose depth
		if (Player1.getType() == 3) {
			System.out.println("choose depth for minimax");
			Player1.setDepth(in.nextInt());
		}
		System.out.println("\n-----------------------------");

		while (!(Player2.getType() < 4 && Player2.getType() >= 0)) {
			System.out
					.println("choose mode for player 2:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
			Player2.setType(in.nextInt());
		}
		System.out.println("You chose " + mode(Player2.getType()) + " for player2");
		if (Player2.getType() == 3) {
			System.out.println("choose depth for minimax");
			Player2.setDepth(in.nextInt());
		}
		System.out.println("\n-----------------------------");
		in.close();
			
	}
	
	public static void main(String[] args) {
		StartGame();
	}	
}