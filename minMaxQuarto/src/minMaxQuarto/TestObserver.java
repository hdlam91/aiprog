package minMaxQuarto;
import java.util.Scanner;

//EXAMPLE FILE


/**
 * Created with IntelliJ IDEA.
 * User: thaffe
 * Date: 11.09.13
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class TestObserver implements MeteorGameObserver {
    private static final String PLAYER_NAME = "unikIdForSpiller";
    private static final String GAME_ID = "testGame";

    private MeteorGame game;
    public TestObserver(){
        //Oppretter et nytt spill og connecter til server
        game = new MeteorGame(this);
        game.connect();
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
    }

    @Override
    public void restart() {
        System.out.println("Server sier fra at spillet blir resatt nå, kaller start game strx");
    }

    @Override
    public void doMove() {
        System.out.println("Din tur til å gjøre et move");
        Scanner s = new Scanner(System.in);
        System.out.println("Velg board position: ");
        int selectedPos = s.nextInt();
        System.out.println("Velg neste piece: ");
        int selectedPiece = s.nextInt();

        game.doMove(selectedPos,selectedPiece);
    }

    @Override
    public void moveDone(int positionIndex, int pieceIndex) {
        System.out.println("Du mottok dette movet:"+positionIndex+" og spiller valgte denne brikkken:"+pieceIndex);
    }
}
