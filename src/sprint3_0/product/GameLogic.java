package sprint3_0.product;

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
    }

    private GameState currentGameState;
    public enum GameMode{
        SIMPLE, GENERAL
    }
    private GameMode selectedGameMode;

    public GameLogic(){
        selectedGameMode = GameMode.SIMPLE;
        totalRows = DEFAULT_DIMENSION;
        totalColumns = DEFAULT_DIMENSION;
    }

    //Idle is the state used both before and after a game
    //Note: Might need to be refactored
    public void initGame() {
        currentGameState = GameState.IDLE;
        bluePlayerTurn = true;
        redPlayerTurn = false;
        turn = 0;
    }
    //empties the board
    public void setupBoard(){
        for (int row = 0; row < totalRows; ++row) {
            for (int col = 0; col < totalColumns; ++col) {
                grid[row][col] = Cell.EMPTY;
            }
        }
    }

    //returns boolean value indicating if the game was successfully started
    public Boolean startGame(int boardDimension) {
        if (currentGameState != GameState.PLAYING && verifyBoardInputSize(boardDimension)) {
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

    //returns boolean value indicating if the move was successfully made
    public boolean makeMove(int row, int column){
        if (row < totalRows && column < totalColumns) {
            if (grid[row][column] == Cell.EMPTY && currentGameState == GameState.PLAYING) {
                //Different rules applied based on game mode
                if (selectedGameMode == GameMode.SIMPLE) {
                    if (bluePlayerTurn) {
                        grid[row][column] = bluePlayerMove;
                        bluePlayerTurn = false;
                        redPlayerTurn = true;
                    } else {
                        grid[row][column] = redPlayerMove;
                        bluePlayerTurn = true;
                        redPlayerTurn = false;
                    }
                    turn++;
                    return true;
                } else if (selectedGameMode == GameMode.GENERAL) {
                    if (bluePlayerTurn && CombinationMade()) {
                        grid[row][column] = bluePlayerMove;
                    } else if (bluePlayerTurn) {
                        grid[row][column] = bluePlayerMove;
                        bluePlayerTurn = false;
                        redPlayerTurn = true;
                        turn++;
                    } else if (redPlayerTurn && CombinationMade()) {
                        grid[row][column] = bluePlayerMove;
                    } else if (redPlayerTurn) {
                        grid[row][column] = bluePlayerMove;
                        bluePlayerTurn = false;
                        redPlayerTurn = true;
                        turn++;
                    }
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    //This will be implemented in future sprints
    public Boolean CombinationMade(){
        return false;
    }
    private Boolean verifyBoardInputSize(int boardDimension){
        if (boardDimension >= BOARD_MIN && boardDimension <= BOARD_MAX) {
            return true;
        }
        return false;
    }
    public void setGameMode(GameMode selectedGameMode){
        if (currentGameState != GameState.PLAYING) {
            this.selectedGameMode = selectedGameMode;
        }
    }
    public void setRedPlayerMove(Cell redPlayerMove){
        this.redPlayerMove = redPlayerMove;
    }
    public void setBluePlayerMove(Cell bluePlayerMove){
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
    public GameMode getGameMode() {
        return selectedGameMode;
    }
}