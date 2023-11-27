package sprint5_0.product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class GameLogic {
    public static int DEFAULT_DIMENSION = 6;
    public static int BOARD_MIN = 3;
    public static int BOARD_MAX = 12;
    public enum Cell {
        EMPTY, S, O
    }
    public enum GameMode{
        SIMPLE, GENERAL
    }
    public enum PlayerMode{
        HUMAN, COMPUTER
    }
    public enum GameState {
        IDLE, PLAYING, DRAW, BLUE_WON, RED_WON
    }
    public enum CombinationDirection {
        E, NE, N, NW, W, SW, S, SE, EMPTY
    }
    protected Cell[][] grid;
    protected Cell bluePlayerMove;
    protected Cell redPlayerMove;
    protected GameMode selectedGameMode;
    protected PlayerMode bluePlayerMode;
    protected PlayerMode redPlayerMode;
    protected GameState currentGameState;
    protected CombinationDirection[][][] combinationGrid;
    protected int totalRows;
    protected int totalColumns;
    protected int turn;
    protected Boolean redPlayerTurn;
    protected Boolean bluePlayerTurn;
    protected int piecesPlaced;
    protected Boolean combinationMade;
    protected int[][] turnRecorder;
    protected FileWriter myWriter;
    protected Boolean recording;
    private File file;

    public GameLogic(){
        totalRows = DEFAULT_DIMENSION;
        totalColumns = DEFAULT_DIMENSION;
        bluePlayerMove = Cell.S;
        redPlayerMove = Cell.S;
        recording = false;
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
                for(int i = 0; i < 8; i++){
                    combinationGrid[row][col][i] = CombinationDirection.EMPTY;
                }
            }
        }
    }
    //returns boolean value indicating if the game was successfully started
    public Boolean startGame(int boardDimension) {
        if (currentGameState != GameState.PLAYING && verifyBoardInputSize(boardDimension)) {
            totalRows = boardDimension;
            totalColumns = boardDimension;
            grid = new Cell[totalRows][totalColumns];
            combinationGrid = new CombinationDirection[totalRows][totalColumns][8];
            turnRecorder = new int[totalRows][totalColumns];
            setupBoard();
            currentGameState = GameState.PLAYING;
            CreatingGameFile();
            turn = 1;
            piecesPlaced = 0;
            return true;
        }
        return false;
    }

    //returns boolean value indicating if the move was successfully made
    public boolean makeMove(int row, int column){
        if (row < totalRows && column < totalColumns && grid[row][column] == GameLogic.Cell.EMPTY && currentGameState == GameLogic.GameState.PLAYING) {
            piecesPlaced++;
            WriteMoveToFile(row, column);
            return true;
        }
        return false;
    }

    //This method is called to find if the size input is within the correct parameters
    private Boolean verifyBoardInputSize(int boardDimension){
        if (boardDimension >= BOARD_MIN && boardDimension <= BOARD_MAX) {
            return true;
        }
        return false;
    }
    private void CreatingGameFile(){
        if(recording) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String gameFile = "GameFiles/gameFile-" + sdf1.format(new Date()) + ".text";
            try {
                file = new File(gameFile);
                file.createNewFile();
                myWriter = new FileWriter(gameFile);
                myWriter.write(selectedGameMode + " " + bluePlayerMode + " " + redPlayerMode + " " + totalColumns + "\n");
            } catch (IOException e) {
                System.out.println("Unable to create new file.");
            }
        }
    }
    private void WriteMoveToFile(int row, int column){
        if(recording) {
            try {
                if (bluePlayerTurn) {
                    myWriter.write(bluePlayerMove + " " + row + " " + column + "\n");
                } else {
                    myWriter.write(redPlayerMove + " " + row + " " + column + "\n");
                }
            } catch (IOException e) {
                System.out.println("Writing failed");
            }
        }
    }
    protected void CloseFile(){
        if(recording) {
            try {
                myWriter.close();
            } catch (IOException e) {
                System.out.println("Closing failed");
            }
        }
    }
    protected void DeleteFile(){
        try {
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Closing failed");
        }
        file.delete();
    }
    public void setGameMode(GameMode selectedGameMode){
        if (currentGameState != GameState.PLAYING) {
            this.selectedGameMode = selectedGameMode;
        }
    }
    public void setBluePlayerMode(PlayerMode selectedPlayerMode){
        if (currentGameState != GameState.PLAYING) {
            bluePlayerMode = selectedPlayerMode;
        }
    }
    public void setRedPlayerMode(PlayerMode selectedPlayerMode){
        if (currentGameState != GameState.PLAYING) {
            redPlayerMode = selectedPlayerMode;
        }
    }
    public void setRedPlayerMove(Cell redPlayerMove){
        this.redPlayerMove = redPlayerMove;
    }
    public void setBluePlayerMove(Cell bluePlayerMove){
        this.bluePlayerMove = bluePlayerMove;
    }
    public void setRecording(Boolean recording){
        this.recording = recording;
    }
    public Boolean getRecording(){
        return recording;
    }
    public Cell getRedPlayerMove(){
        return redPlayerMove;
    }
    public Cell getBluePlayerMove(){
        return bluePlayerMove;
    }
    public PlayerMode getRedPlayerMode(){
        return redPlayerMode;
    }
    public PlayerMode getBluePlayerMode(){
        return bluePlayerMode;
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
    public CombinationDirection getCombinationDirection(int row, int column, int i) {
        return combinationGrid[row][column][i];
    }
    public int getTurnRecorder(int row, int column){
        return turnRecorder[row][column];
    }
    public int getTotalRows(){
        return totalRows;
    }
    public int getTotalColumns(){
        return totalColumns;
    }
    public GameState getGameState() {
        return currentGameState;
    }
    public GameMode getGameMode() {
        return selectedGameMode;
    }
    public abstract Boolean findCombination(int row, int column);
    public abstract boolean makeComputerMove();
    public abstract Boolean findRandomMove(int location, int cellAmount);
    public abstract Boolean findDefensiveMove(int location, int cellAmount);
    public abstract Boolean CheckSDefensiveMove(int row, int column);
    public abstract Boolean CheckODefensiveMove(int row, int column);
    public abstract Boolean findSOSMove(int location, int cellAmount);
    public abstract int CheckSCombination(int row, int column);
    public abstract int CheckOCombination(int row, int column);
}

//replay needs to be a gui function that just keeps calling make move with a thread
//I need to store info such as who is computer and who isnt
//the gui class needs to call a bunch of stters
//I also need to pass record state to new games