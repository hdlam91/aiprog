package minMaxQuarto;

/**
 * Created with IntelliJ IDEA.
 * User: thaffe
 * Date: 11.09.13
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class NetworkGame implements MeteorGameObserver {
    private static final String PLAYER_NAME = "hdeivind";
    private static final String GAME_ID = "password";

    private MeteorGame game;
    private Bot bot;
    private Board board;
    private int depth;
    private int currentPiece;
    private boolean newGame;
    private boolean test = false;
    private int win, loss, tie;
    
    public NetworkGame() {
		// TODO Auto-generated constructor stub
        //Oppretter et nytt spill og connecter til server
        game = new MeteorGame(this);
        game.connect();
        depth = 2;
        board = new Board();
        bot = getBot(board);
        newGame = true;
        win = 0;
        loss = 0;
        tie = 0;
    }

    
    //creates a new bot for refresh
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
    
    //starts game, initiates new board and bot
    @Override
    public void startGame() {
        System.out.println("Server sier fra at spillet kan startes, init spill brett her");
        newGame = true;
        board = new Board();
        bot = getBot(board);
        System.out.println(board);
        System.out.println("________________________________________________");
    }

    @Override
    public void restart() {
        System.out.println("Server sier fra at spillet blir resatt nå, kaller start game strx");
    }
    //do a move.
    @Override
    public void doMove() {
        System.out.println("Din tur til å gjøre et move");
        
        int selectedPos = -1;
        if(newGame){
            selectedPos = -1;
            newGame = false;
            currentPiece = -2;
        }
        else if(currentPiece != -1){
            bot.choseWheretoPlacePiece(currentPiece);
            selectedPos = bot.getNetworkPosition();
            System.out.println("placed piece @ " +selectedPos+"x:"+selectedPos%4 + "\ty:"+selectedPos/4);
        }
        if(currentPiece == -1){
        	return;
        }
        int selectedPiece;
        if(board.checkWin()){
        	selectedPiece = -1;
        	System.out.println("You win!");
        	win++;
        }
        else if(board.usedAllPieces()){
        	selectedPiece = -1;
        	tie++;
        }
        else {
        	selectedPiece = bot.choosePiece();
        	if(selectedPiece == -1)
        		System.out.println("SOMETHING WENT HORRIBLY WRONG");
        }
        System.out.println("chosen piece: "+selectedPiece + ":  "+board.getPieceFromRemaining(selectedPiece));
        currentPiece = selectedPiece;
        game.doMove(selectedPos,currentPiece);
        if(!newGame)
        	System.out.println(board);
        
        
    }
    //opponent have done a move, gets it back
    @Override
    public void moveDone(int positionIndex, int pieceIndex) {
        System.out.println("Du mottok dette movet:"+positionIndex+" ("+positionIndex%4 +","+positionIndex/4 +") og spiller valgte denne brikkken:"+pieceIndex);
        if(newGame){
        	currentPiece = pieceIndex;
        	newGame = false;
        	return;
        }
        if(!newGame)
        	board.placePieceOnBoard(positionIndex%4, positionIndex/4, board.getPieceFromRemaining(currentPiece), currentPiece);
        if(pieceIndex != -1)
        {
           currentPiece = pieceIndex;
           System.out.println(board);
        }
       if(pieceIndex == -1){
    	   System.out.println(board);
    	   if(board.checkWin()){
    		   System.out.println("You lost, other player won");
    		   loss++;
    	   }
    	   else{
    		   System.out.println("Draw");
    		   tie++;
    	   }
           currentPiece = -1;
       }

    }
    
    public String toString(){
    	StringBuffer stats = new StringBuffer();
    	stats.append("wins: "+win);
    	stats.append("\tloss: "+loss);
    	stats.append("\ttie: "+tie+"\n");
    	
    	
    	return stats.toString();
    }

}
