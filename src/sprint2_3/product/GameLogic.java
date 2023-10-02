package sprint2_3.product;

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
    private Cell bluePlayerMove = Cell.S;
    private Cell redPlayerMove = Cell.S;
    public enum GameState {
        IDLE, PLAYING, DRAW, BLUE_WON, RED_WON
        //closing popup switches out of in state
    }
    //game board covered with who won after the game is complete

    private GameState currentGameState;
    public enum GameMode{
        SIMPLE, GENERAL
    }

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
        turn = 0;
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
    boolean makeMove(int row, int column){
        if (grid[row][column] != Cell.EMPTY){
            return false;
        }
        else if (currentGameState == GameState.PLAYING) {
            if(turn % 2 != 0) {
                grid[row][column] = bluePlayerMove;
            }
            else{
                grid[row][column] = redPlayerMove;
            }
//            if (type == Cell.S) {
//                grid[row][column] = Cell.S;
//            } else if (type == Cell.O) {
//                grid[row][column] = Cell.O;
//            }
            turn++;
            return true;
        }
        return false;
        //maybe a center popup saying select board size andgame type

    }
    //maybe have an internal function that does modulus 2 that then relates to a boolean function
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
    public void updateRedPlayerMove(Cell redPlayerMove){
        this.redPlayerMove = redPlayerMove;
    }
    public void updateBluePlayerMove(Cell bluePlayerMove){
        this.bluePlayerMove = bluePlayerMove;
    }

    public int getTurn(){
        return turn;
    }
    //seperate move and turn vairable might cause issues down the line
    //maybe just a boolean variable indicating turn?
    //seperate move or trun variable
//    public void incrementTurn(){
//        turn++;
//    }

}
//maybe change wording so each player has a turn 1

//marron and navy blue pallet