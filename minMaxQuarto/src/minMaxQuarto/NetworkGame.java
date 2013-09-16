package minMaxQuarto;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: thaffe
 * Date: 11.09.13
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class NetworkGame implements MeteorGameObserver {
    private static final String PLAYER_NAME = "hdeivind";
    private static final String GAME_ID = "hd";

    private MeteorGame game;
    private Bot bot;
    private Board board;
    private int depth;
    private int currentPiece;
    private boolean newGame;
    private boolean test = true;
    
    public NetworkGame() {
		// TODO Auto-generated constructor stub
        //Oppretter et nytt spill og connecter til server
        game = new MeteorGame(this);
        game.connect();
        depth = 4;
        board = new Board();
        bot = getBot(board);
        newGame = true;
        
    }

    
    public Bot getBot(Board b){
    	if(test)
    		return new NoviceBot(b);
    	return new MinimaxBot(b, depth);
    }
    
    
    
    @Override
    public String getGameId() {
        return GAME_ID;
    }

    @Override
    public String getPlayerId() {
        return PLAYER_NAME;
    }

    @Override
    public void searchingOponent() {
        System.out.println("Searching for oponent");
    }

    @Override
    public void startGame() {
        System.out.println("Server sier fra at spillet kan startes, init spill brett her");
        newGame = true;
        board = new Board();
        bot = getBot(board);
    }

    @Override
    public void restart() {
        System.out.println("Server sier fra at spillet blir resatt n�, kaller start game strx");
        newGame = true;
        board = new Board();
        bot = getBot(board);
    }

    @Override
    public void doMove() {
    	System.out.println("Din tur til � gj�re et move");
    	
    	
//        System.out.println("Velg board position: ");
        int selectedPos = 0;
        if(newGame){
        	selectedPos = -1;
        	newGame = false;
        }
        else{
        	bot.choseWheretoPlacePiece(currentPiece);
        	selectedPos = bot.getNetworkPosition();
        	System.out.println("placed piece @ " +selectedPos+"x:"+selectedPos%4 + "\ty:"+selectedPos/4);
        }
        //System.out.println("Velg neste piece: ");
        int selectedPiece = bot.choosePiece();
        System.out.println("chosen piece: "+selectedPiece + ":  "+board.getPieceFromRemaining(selectedPiece));
        if(board.checkWin()){
        	selectedPiece = -1;
        	System.out.println("You win!");
        }
        currentPiece = selectedPiece;
        game.doMove(selectedPos,selectedPiece);
        if(!newGame)
        	System.out.println(board);
    }

    @Override
    public void moveDone(int positionIndex, int pieceIndex) {
        System.out.println("Du mottok dette movet:"+positionIndex+" og spiller valgte denne brikkken:"+pieceIndex);
        //System.out.println("x: " + positionIndex%4 + "\t y: " +positionIndex/4);
       if(!newGame){
    	   if(pieceIndex != -1)
    		   board.placePieceOnBoard(positionIndex%4, positionIndex/4, board.getPieceFromRemaining(currentPiece), currentPiece);
	       currentPiece = pieceIndex;
	       newGame = false;
	       System.out.println(board);
       }
       if(pieceIndex == -1)
    	   System.out.println("You lost, other player won");
        
        
    }
}
