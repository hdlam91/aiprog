package minMaxQuarto;

import java.util.Scanner;

public class Quarto {
	
	private static Player Player1, Player2;
	private static int ties;
	private static Scanner in;
	
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
		// 0 = human, 1 = random, 2 = novice, 3 = minimax
		Player1 = new Player();
		Player2 = new Player();
		
		in = new Scanner(System.in);

		// chooses mode for player 1
		while (!(Player1.getType() < 4 && Player1.getType() >= 0)) {
			System.out.println("choose mode for player 1:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
			String p1 = in.next();
			int type1 = -1;
			try{
			    type1 = Integer.parseInt(p1);
			}
			catch(NumberFormatException ex){
				System.out.println(p1 + " is not a valid number.");
			}
			Player1.setType(type1);
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
			System.out.println("choose mode for player 2:\n0 = human, 1 = random, 2 = novice, 3 = minimax");
			String p2 = in.next();
			int type2 = -1;
			try{
			    type2 = Integer.parseInt(p2);
			}
			catch(NumberFormatException ex){
				System.out.println(p2 + " is not a valid number.");
			}
			Player2.setType(type2);
		}
		System.out.println("You chose " + mode(Player2.getType()) + " for player2");
		if (Player2.getType() == 3) {
			while((Player2.getDepth()<2)){
				System.out.println("choose depth for minimax (>=2)");
				Player2.setDepth(in.nextInt());
			}
		}
		System.out.println("\n-----------------------------");
		//in.close();
		for (int i = 0; i < 1; i++) {			
			createGameSession();
		}
		System.out.println("Stats:");
		System.out.println("Player1 (" + Player1.getBot() + ") wins:" + Player1.getWincount());
		System.out.println("Player2 (" + Player2.getBot() + ") wins:" + Player2.getWincount());
		System.out.println("Ties:" + ties);
	}
	
	public static void createGameSession(){
		Board board = new Board();
		
		Player1.setBot(createBot(Player1.getType(), board, Player1.getDepth()));
		Player2.setBot(createBot(Player2.getType(), board, Player2.getDepth()));
		
		System.out.print(Player1.getBot() + " vs ");
		System.out.println(Player2.getBot() +"\n-----------------");
		

		int turn = 1;
		int chosenPiece = -1;
		boolean humanPlayer = false;
		if(Player1.getBot().getName().equals("human") || Player2.getBot().equals("human"))
			humanPlayer = true;
		
		if(humanPlayer)
			System.out.println(board);
		
		while(!board.checkWin() && !board.usedAllPieces()){
			if(humanPlayer)
				System.out.println("Turn " + turn);
			if(turn == 1){
				chosenPiece = Player2.getBot().choosePiece();
				if(humanPlayer)
					System.out.println("Chosen piece: " + chosenPiece);
				Player1.getBot().choseWheretoPlacePiece(chosenPiece);
				turn = 2;
			}
			else if(turn == 2){
				chosenPiece = Player1.getBot().choosePiece();
				if(humanPlayer)
					System.out.println("Chosen piece: " + chosenPiece);
				Player2.getBot().choseWheretoPlacePiece(chosenPiece);
				turn = 1;
			}
			if(humanPlayer)
				System.out.println(board);
			
		}
		if(!humanPlayer)
			System.out.println(board);
		if(board.checkWin() && turn ==2){
			Player1.setWincount(Player1.getWincount()+1);
			System.out.println("Player1 (" + Player1.getBot() + ") won");
		}
		else if(board.checkWin() && turn ==1){
			Player2.setWincount(Player2.getWincount()+1);
			System.out.println("Player2 (" + Player2.getBot() + ") won");
		}
		else if(board.usedAllPieces()){
			ties++;
			System.out.println("Tie");
		}
		System.out.println("____________________________________________________________");
	}
	
	public static void main(String[] args) {
		StartGame();
		in.close();
	}	
}