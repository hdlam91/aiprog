package minMaxQuarto;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.util.StringUtils;
import me.kutrumbos.DdpClient;

import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: thaffe
 * Date: 11.09.13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class MeteorGame {
    private final String URL = "78.91.16.242";
    private final int PORT = 3000;


    private DdpClient ddp;
    private Observer observer;
    private MeteorGameObserver gameObserver;
    private Gson gson;
    private boolean hasStarted = false, requestedStart = false;
    private JsonPlayer round;
    private int playerIndex, startTurn;

    public MeteorGame(MeteorGameObserver gameObserver){
        this.gameObserver = gameObserver;
        this.gson = new Gson();
    }

    public static int getPieceIndex(boolean isDark, boolean isSquare, boolean isBig, boolean hasHole){
        int res = 0;
        if(isDark)res+=8;
        if(isSquare)res+=4;
        if(isBig)res+=2;
        if(hasHole)res+=1;
        return res;
    }

    public static int getBoardIndex(int row,int col){
        return row*4+col;
    }

    public void connect(){
        try{
            ddp = new DdpClient(URL,PORT);
            observer = new GameObserver();
            ddp.addObserver(observer);
            ddp.connect();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
    }

    public void doMove(int selectedBoardPos, int selectedPiece){
        if(hasStarted){
            ddp.call("doMove",getArgs(gameObserver.getPlayerId(),selectedBoardPos,selectedPiece));
        }
    }

    private Object[] getArgs(Object... objects){
        return objects;
    }


    private class GameObserver implements Observer{
        @Override
        public void update(Observable client, Object msg) {
            System.out.println(gameObserver.toString());
            if (msg instanceof String) {
                //System.out.println("Received response: "+msg);
                MeteorResult res = gson.fromJson((String)msg,MeteorResult.class);
                
                if(res.fields != null){
                	if(res.fields.index > -1) playerIndex = res.fields.index;
                	else res.fields.index = playerIndex;
                }
                
                
                if(res.fields != null && requestedStart){
                    round = res.fields;
                    round.index = 1;
                    System.out.println(gson.toJson(round));
                    

                    if(round.superRestart == 1){
                    	hasStarted = false;
                    	gameObserver.restart();
                    	System.out.println("super restart");
                	}if(round.searching == 1){
                        gameObserver.searchingOponent();
                    }else if(round.doRestart == 1){
                    	System.out.println("restart");
                        gameObserver.restart();
                        hasStarted = false;
                        round.selectedPos = -1;
                        round.selectedPiece = -1;
                        round.turn = -1;
                        ddp.call("restart",getArgs(gameObserver.getPlayerId()));
                    }else if(!hasStarted && round.turn > -1){
                    	System.out.println("start"+round.turn+" "+round.index);
                        hasStarted = true;
                        gameObserver.startGame();
                        System.out.println("Turn:"+round.turn+" round.Index:"+round.index);
                        if(round.selectedPiece > 0) gameObserver.moveDone(-1, round.selectedPiece);
                        if(round.turn == round.index){
                            gameObserver.doMove();
                        }
                    }else{
                        if(round.selectedPiece > -1 || round.selectedPos > -1){
                            gameObserver.moveDone(round.selectedPos, round.selectedPiece);
                            gameObserver.doMove();
                        }
                    }
                }

                if(res.msg != null && res.msg.length() > 0){
                    if(res.msg.equalsIgnoreCase("connected")){
                        System.out.println("Subscribing to the your player");
                        ddp.subscribe("player",getArgs(gameObserver.getPlayerId(),true));
                    }

                    if(res.msg.equalsIgnoreCase("added")){
                        System.out.println("Request oponent and starting game");
                        ddp.call("startGame",getArgs(gameObserver.getPlayerId(),gameObserver.getGameId()));
                        requestedStart = true;
                    }

                }
            }

        }
    }

    public class MeteorResult{
        public String msg;
        public JsonPlayer fields;
    }

    public class JsonPlayer{
        public int index = -1;
        public int turn = -1;
        public int doRestart;
        public int superRestart;
        public int searching;
        public int selectedPiece = -1;
        public int selectedPos = -1;
    }
}


