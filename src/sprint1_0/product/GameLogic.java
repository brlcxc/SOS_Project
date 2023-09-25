package sprint1_0.product;

//maybe I dont have to reset the class itself but rather something within it
public class GameLogic {
    private int totalRows;
    private int totalColumns;

    public enum Cell {
        EMPTY, RS, RB, OS, OB
    }
    private Cell[][] grid;

    public GameLogic(){
    }

    public void initGame(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        grid = new Cell[totalRows][totalColumns];
        for (int row = 0; row < totalRows; ++row) {
            for (int col = 0; col < totalColumns; ++col) {
                grid[row][col] = Cell.EMPTY;
//                System.out.println("x");
            }
        }
//        currentGameState = GameState.PLAYING;
//        turn = 'X';
    }
    public void makeMove(){

    }
    public Cell getCell(int row, int column) {
//        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
        return grid[row][column];
//        System.out.println("u");
//        return grid[1][1];

//        } else {
//            return null;
//        }
    }
    public int getTotalRows(){
        return totalRows;
    }
    public int getTotalColumns(){
        return totalRows;
    }
}
