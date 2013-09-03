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
	
	private static Bot createBot(int type, Board board,int depth){
		switch(type){
		case 0:
			return new HumanBot(board);
		case 1:
			return new RandomBot(board);
		case 2:
			return new NoviceBot(board);
		case 3:
			return new MinimaxBot(board,depth);
		default:
			return null;
		}
	}
	
	private static void StartGame(){
		// 0 = human, 1 = random, 2 = novice, 3 = minmax
		Player1 = new Player();
		Player2 = new Player();
		
		Scanner in = new Scanner(System.in);

		// chooses mode for player 1
		while (!(Player1.getType() < 4 && Player1.getType() >= 0)) {
			System.out
					.println("choose mode for player 1:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
			Player1.setType(in.nextInt());
		}
		System.out.println("You chose " + mode(Player1.getType()) + " for player1");

		// chose depth
		if (Player1.getType() == 3) {
			while((Player1.getDepth()<2)){
				System.out.println("choose depth for minimax (>=2)");
				Player1.setDepth(in.nextInt());
			}
		}
		System.out.println("\n-----------------------------");

		//chose for player2 
		while (!(Player2.getType() < 4 && Player2.getType() >= 0)) {
			System.out
					.println("choose mode for player 2:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
			Player2.setType(in.nextInt());
		}
		System.out.println("You chose " + mode(Player2.getType()) + " for player2");
		if (Player2.getType() == 3) {
			while((Player2.getDepth()<2)){
				System.out.println("choose depth for minimax (>=2)");
				Player2.setDepth(in.nextInt());
			}
		}
		System.out.println("\n-----------------------------");
		in.close();
		for (int i = 0; i < 1; i++) {			
			createGameSession();
		}
	}
	
	public static void createGameSession(){
		Board board = new Board();
		
		Player1.setBot(createBot(Player1.getType(), board, Player1.getDepth()));
		Player2.setBot(createBot(Player2.getType(), board, Player2.getDepth()));
		
		System.out.println(Player1.getBot().getName());
		System.out.println(Player2.getBot().getName());
		
		int turn = 1;
		int chosenPiece = -1;
		
		while(!board.checkWin() && !board.usedAllPieces()){
			if(turn == 1){
				chosenPiece = Player2.getBot().choosePiece();
				Player1.getBot().choseWheretoPlacePiece(chosenPiece);
				turn = 2;
			}
			else if(turn == 2){
				chosenPiece = Player1.getBot().choosePiece();
				Player2.getBot().choseWheretoPlacePiece(chosenPiece);
				turn = 1;
			}
			System.out.println(board);
		}
	}
	
	public static void main(String[] args) {
		StartGame();
	}	
}