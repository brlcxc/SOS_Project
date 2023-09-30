package sprint2_1.product;

public class GameLogic {
    private int totalRows;
    private int totalColumns;
    public static int DEFAULT_DIMENSION = 6;
    public static int BOARD_MIN = 3;
    public static int BOARD_MAX = 12;

//    private int boardDimension;

    public enum Cell {
        EMPTY, S, O
    }
    private Cell[][] grid;
    private int turn;

    public enum GameState {
        IDLE, PLAYING, DRAW, BLUE_WON, RED_WON
        //closing popup switches out of in state
    }
    //game board covered with who won after the game is complete

    private GameState currentGameState;

    public GameLogic(){
    }
    //maybe just have a default size variable

    public void initGame(int boardDimension) {
        //maybe have a board size var and then set total rows and columns to it?
//        this.boardDimension = boardDimension;
        totalRows = boardDimension;
        totalColumns = boardDimension;
//        this.totalRows = 0;
//        this.totalColumns = 0;
//        grid = new Cell[totalRows][totalColumns];
//        for (int row = 0; row < totalRows; ++row) {
//            for (int col = 0; col < totalColumns; ++col) {
//                grid[row][col] = Cell.EMPTY;
//            }
//        }
        currentGameState = GameState.IDLE;
//        turn = 0;
//        turn = 'X';
    }
    public void clearBoard(){
//                grid = new Cell[totalRows][totalColumns];
        for (int row = 0; row < totalRows; ++row) {
            for (int col = 0; col < totalColumns; ++col) {
                grid[row][col] = Cell.EMPTY;
            }
        }
    }
//    public void sizeChange(int boardDimension){
//        totalRows = boardDimension;
//        totalColumns = boardDimension;
//    }
    public void startGame(int totalRows, int totalColumns) {
        //maybe have a board size var and then set total rows and columns to it?
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        grid = new Cell[totalRows][totalColumns];
//        for (int row = 0; row < totalRows; ++row) {
//            for (int col = 0; col < totalColumns; ++col) {
//                grid[row][col] = Cell.EMPTY;
//            }
//        }
        //can I have multiple point to the same data type?
        clearBoard();
        currentGameState = GameState.PLAYING;
        turn = 1;
        //always have turn going up
        //just change wether it is p1 or p2
//        turn = 'X';
    }
    boolean makeMove(int row, int column, Cell type){
        if (grid[row][column] != Cell.EMPTY){
            return false;
        }
        else if (currentGameState == GameState.PLAYING) {
            if (type == Cell.S) {
                grid[row][column] = Cell.S;
            } else if (type == Cell.O) {
                grid[row][column] = Cell.O;
            }
            turn++;
            return true;
        }
        return false;
        //maybe a center popup saying select board size andgame type

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

    public int getTurn(){
        return turn;
    }
//    public void incrementTurn(){
//        turn++;
//    }

}
