package minMaxQuarto;
/***
 * Dette er klassen som observerer gamet
 */
public interface MeteorGameObserver{
    /**
     * Game id for spillet, s� n�r du java spill er mot hverandre, s� kan quarto.meteor.com observer spillet ved � lete etter denne iden.
     * Er forl�pig ikke implementert helt ferdig.
     * @return
     */
    public String getGameId();

    /**
     * Din unike spiller id, m� settes manuelt VIKTIG at den er unik
     * @return
     */
    public String getPlayerId();

    /***
     * Er egentlig ikke n�dvendig, kalles n�r man har connected til serveren men har enda ikke funnet en motspiller
     */
    public void searchingOponent();

    /**
     * Kalles hver gang spillet starte
     */
    public void startGame();

    /***
     * Kalles n�r spillet gj�res klart for � resetes,
     * kaller startGame n�r spillet skal start igjen
     */
    public void restart();

    /**
     * Denne metoden kalles n�r det er denne spilleren sin tur til � gj�re et move
     * Kall p� MeteorGame.doMove(int,int) for � gjennomf�re �nsket move.
     * Brikker og posisjon kan konverteres gjennom MeteorGame.getPieceIndex, og MeteorGame.getBoardIndex
     */
    public void doMove();

    /***
     * Kalles hvergang motspiller har gjort et trekk
     *
     * @param positionIndex hvis f�rste valgte brikke settes denne til -1 fordi den ikke er satt
     * @param pieceIndex  hvis et vinnen flytt blir gjort sett denne til -1 for � si fra at denne ikke er satt
     */
    public void moveDone(int positionIndex, int pieceIndex);
}
