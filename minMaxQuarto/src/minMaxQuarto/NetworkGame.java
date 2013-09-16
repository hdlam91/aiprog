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
    private static final String GAME_ID = "testGame";

    private MeteorGame game;
    private Bot bot;
    private Board board;
    private int depth;
    private int currentPiece = -2;
    public NetworkGame() {
		// TODO Auto-generated constructor stub
        //Oppretter et nytt spill og connecter til server
        game = new MeteorGame(this);
        game.connect();
        depth = 4;
        board = new Board();
        bot = getBot(board);
        
    }

    
    public Bot getBot(Board b){
    	return new NoviceBot(b);
    	//return new MinimaxBot(b, depth);
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
        board = new Board();
        bot = getBot(board);
    }

    @Override
    public void restart() {
        System.out.println("Server sier fra at spillet blir resatt nå, kaller start game strx");
        board = new Board();
        bot = getBot(board);
    }

    @Override
    public void doMove() {
//    	System.out.println("Din tur til å gjøre et move");
    	
    	
        System.out.println("Velg board position: ");
        int selectedPos = 0;
        if(currentPiece == -2)
        	selectedPos = -1;
        else
        	bot.choseWheretoPlacePiece(currentPiece);
        selectedPos = bot.getNetworkPosition();
        
        //System.out.println("Velg neste piece: ");
        int selectedPiece = bot.choosePiece();
        if(board.checkWin()){
        	selectedPiece = -1;
        }
        game.doMove(selectedPos,selectedPiece);
    }

    @Override
    public void moveDone(int positionIndex, int pieceIndex) {
        System.out.println("Du mottok dette movet:"+positionIndex+" og spiller valgte denne brikkken:"+pieceIndex);
        currentPiece = pieceIndex;
        
        
    }
}
