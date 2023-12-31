package sprint2_3.product;

public class GameLogic {
    public static int DEFAULT_DIMENSION = 6;
    public static int BOARD_MIN = 3;
    public static int BOARD_MAX = 12;
    private int totalRows;
    private int totalColumns;
    private int turn;

    public enum Cell {
        EMPTY, S, O
    }
    private Cell[][] grid;
    private Boolean redPlayerTurn;
    private Boolean bluePlayerTurn;
    private Cell bluePlayerMove;
    private Cell redPlayerMove;
    public enum GameState {
        IDLE, PLAYING, DRAW, BLUE_WON, RED_WON
        //closing popup switches out of in state
    }
    //game board covered with who won after the game is complete

    private GameState currentGameState;
    public enum GameMode{
        SIMPLE, GENERAL
    }
    private GameMode selectedGameMode;

    public GameLogic(){
    }

    public void initGame() {
        currentGameState = GameState.IDLE;
        bluePlayerTurn = true;
        redPlayerTurn = false;
        turn = 0;
    }
    public void setupBoard(){
        for (int row = 0; row < totalRows; ++row) {
            for (int col = 0; col < totalColumns; ++col) {
                grid[row][col] = Cell.EMPTY;
            }
        }
    }
    public Boolean startGame(int boardDimension) {
        if (currentGameState != GameState.PLAYING && boardDimension >= BOARD_MIN && boardDimension <= BOARD_MAX) {
            totalRows = boardDimension;
            totalColumns = boardDimension;
            grid = new Cell[totalRows][totalColumns];
            setupBoard();
            currentGameState = GameState.PLAYING;
            turn = 1;
            return true;
        }
        return false;
    }
    boolean makeMove(int row, int column){
        if (grid[row][column] != Cell.EMPTY || row >= totalColumns || column >= totalColumns){
            return false;
        }
        else if (currentGameState == GameState.PLAYING) {
//            if(turn % 2 != 0) {
//                grid[row][column] = bluePlayerMove;
//            }
//            else{
//                grid[row][column] = redPlayerMove;
//            }
            if(bluePlayerTurn) {
                grid[row][column] = bluePlayerMove;
                bluePlayerTurn = false;
                redPlayerTurn = true;
            }
            else{
                grid[row][column] = redPlayerMove;
                bluePlayerTurn = true;
                redPlayerTurn = false;
            }
            turn++;
            return true;
        }
        return false;
        //maybe a center popup saying select board size and game type
    }
    public void updateGameMode(GameMode selectedGameMode){
        this.selectedGameMode = selectedGameMode;
    }
    public void updateRedPlayerMove(Cell redPlayerMove){
        this.redPlayerMove = redPlayerMove;
    }
    public void updateBluePlayerMove(Cell bluePlayerMove){
        this.bluePlayerMove = bluePlayerMove;
    }
    public int getTurn(){
        return turn;
    }
    public boolean getBluePlayerTurn(){
        return bluePlayerTurn;
    }
    public boolean getRedPlayerTurn(){
        return redPlayerTurn;
    }
    public Cell getCell(int row, int column) {
        return grid[row][column];
    }
    public int getTotalRows(){
        return totalRows;
    }
    public int getTotalColumns(){
        return totalColumns;
    }
    public int getBoardDimension(){
        return totalColumns;
    }
    public GameState getGameState() {
        return currentGameState;
    }
}
//marron and navy blue pallet