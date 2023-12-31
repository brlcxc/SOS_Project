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
    //create an enum for player that can be passed?
    //then modify the set move functions acordingly

    private GameState currentGameState;
    public enum GameMode{
        SIMPLE, GENERAL
    }
    private GameMode selectedGameMode;
    public enum CombinationDirection {
        E, NE, N, NW, W, SW, S, SE, EMPTY
        //info on other pieces does not matter
        //only final piece
        //maybe alter the s o piece placement to something that shows a win by a particular player so that it can be read back
    }
//    Vector<CombinationDirection> v = new Vector<CombinationDirection>();
//    public enum CombinationDirection {
//        B, R, BN, BE, BS, BW, BNE, BSE, BSW, BNW, RN, RE, RS, RW, RNE, RSE, RSW, RNW, EMPTY
//    }
    private Boolean combinationMade;
    private CombinationDirection[][] combinationGrid;
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
                combinationGrid[row][col] = CombinationDirection.EMPTY;
            }
        }
    }

    //returns boolean value indicating if the game was successfully started
    public Boolean startGame(int boardDimension) {
        if (currentGameState != GameState.PLAYING && verifyBoardInputSize(boardDimension)) {
            totalRows = boardDimension;
            totalColumns = boardDimension;
            grid = new Cell[totalRows][totalColumns];
            combinationGrid = new CombinationDirection[totalRows][totalColumns];
            setupBoard();
            currentGameState = GameState.PLAYING;
            turn = 1;
            return true;
        }
        return false;
    }

    //returns boolean value indicating if the move was successfully made
    public boolean makeMove(int row, int column){
        //I need to consider removing this function and just deciding outside?
        //I need to look at removing turn variable at some point
        if (row < totalRows && column < totalColumns && grid[row][column] == Cell.EMPTY && currentGameState == GameState.PLAYING) {
                if (selectedGameMode == GameMode.SIMPLE) {
                    makeSimpleMove(row, column);
                    return true;
                } else if (selectedGameMode == GameMode.GENERAL) {
                    makeGeneralMove(row, column);
                    return true;
                }
                return false;
        }
        return false;
    }
    public void makeSimpleMove(int row, int column){
        if (bluePlayerTurn) {
            grid[row][column] = bluePlayerMove;
            combinationMade = FindCombination(row, column);
            bluePlayerTurn = false;
            redPlayerTurn = true;
        } else {
            grid[row][column] = redPlayerMove;
            combinationMade = FindCombination(row, column);
            bluePlayerTurn = true;
            redPlayerTurn = false;
        }
        turn++;
        //maybe normally return false but return true if something is found?
    }
    public void makeGeneralMove(int row, int column){
        if (bluePlayerTurn) {
            grid[row][column] = bluePlayerMove;
            combinationMade = FindCombination(row, column);
            if(!combinationMade){
                bluePlayerTurn = false;
                redPlayerTurn = true;
                turn++;
                System.out.println("2");
            }
        }
        else if (redPlayerTurn) {
            grid[row][column] = redPlayerMove;
            combinationMade = FindCombination(row, column);
            if(!combinationMade){
                redPlayerTurn = false;
                bluePlayerTurn = true;
                turn++;
                System.out.println("3");
            }
        }
    }

    //This will be implemented in future sprints
    public Boolean FindCombination(int row, int column){
        int x = 0;
        int y = 0;
        if (grid[row][column] == Cell.S) {
            for (int i = 0; i < 8; i++) {
                x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
                y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
                try {
                    if (grid[row + y][column + x] == Cell.O && grid[row + 2 * y][column + 2 * x] == Cell.S){
                        combinationGrid[row][column] = CombinationDirection.values()[i];
                        System.out.println("player won s");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    i++;
                }
            }
        }
        else if (grid[row][column] == Cell.O){
            for(int i = 0; i < 4; i++){
                x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
                y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
                try {
                    System.out.println(grid[row + x][column + y] + " " + grid[row + -1 * x][column + -1 * y] + " " + 45 *i + " x:" + x + " y:" + y);
                    if (grid[row + y][column + x] == Cell.S && grid[row + -1 * y][column + -1 * x] == Cell.S){
                        combinationGrid[row][column] = CombinationDirection.values()[i];
//                        System.out.println(grid[row + x][column + y] + " " + grid[row + -1 * x][column + -1 * y]);
                        System.out.println(i + " player won o " + combinationGrid[row][column]);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
//                    i++;
                }
            }
        }
        return false;
        //how to prevent search a cell that doesn't exist
        //have a size verification when checking?

        //if s
        //search surrounding cells for o in clockwise direction
        //if o found search one more in the same direction for s
        //if s found mark original cell with flag

        //mark cell and include info on final cell?
        //or mark the cardinal direction
        //it should be able to differentiate where to go between s and o with cardinal

        //if o
        //search cells on outsides of the o in clockwise for half a circle
        //if the outer sides both equal s then mark the original cell

        //I shouldn't have to worry about duplicate flags since there is only a flag when a move is made
        //instead of hvaing both a flag syang move placement I will just have the directional flags and null
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
    public CombinationDirection getCombinationDirection(int row, int column) {
        return combinationGrid[row][column];
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