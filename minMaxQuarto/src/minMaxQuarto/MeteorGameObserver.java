package minMaxQuarto;
/***
 * Dette er klassen som observerer gamet
 */
public interface MeteorGameObserver{
    /**
     * Game id for spillet, så når du java spill er mot hverandre, så kan quarto.meteor.com observer spillet ved å lete etter denne iden.
     * Er forløpig ikke implementert helt ferdig.
     * @return
     */
    public String getGameId();

    /**
     * Din unike spiller id, må settes manuelt VIKTIG at den er unik
     * @return
     */
    public String getPlayerId();

    /***
     * Er egentlig ikke nødvendig, kalles når man har connected til serveren men har enda ikke funnet en motspiller
     */
    public void searchingOponent();

    /**
     * Kalles hver gang spillet starte
     */
    public void startGame();

    /***
     * Kalles når spillet gjøres klart for å resetes,
     * kaller startGame når spillet skal start igjen
     */
    public void restart();

    /**
     * Denne metoden kalles når det er denne spilleren sin tur til å gjøre et move
     * Kall på MeteorGame.doMove(int,int) for å gjennomføre ønsket move.
     * Brikker og posisjon kan konverteres gjennom MeteorGame.getPieceIndex, og MeteorGame.getBoardIndex
     */
    public void doMove();

    /***
     * Kalles hvergang motspiller har gjort et trekk
     *
     * @param positionIndex hvis første valgte brikke settes denne til -1 fordi den ikke er satt
     * @param pieceIndex  hvis et vinnen flytt blir gjort sett denne til -1 for å si fra at denne ikke er satt
     */
    public void moveDone(int positionIndex, int pieceIndex);
}
