package sprint4_1.product;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleComputerGameLogic extends SimpleGameLogic{

    public SimpleComputerGameLogic(){
        super();
        redPlayerMode = PlayerMode.HUMAN;
        bluePlayerMode = PlayerMode.HUMAN;
    }
    public SimpleComputerGameLogic(GameLogic.Cell redPlayerMove, GameLogic.Cell bluePlayerMove, GameLogic.PlayerMode redPlayerMode, GameLogic.PlayerMode bluePlayerMode){
        super(redPlayerMove, bluePlayerMove);
        this.redPlayerMode = redPlayerMode;
        this.bluePlayerMode = bluePlayerMode;
    }
    @Override
    public boolean makeComputerMove() {
        int cellAmount = totalRows * totalColumns;
        int location = ThreadLocalRandom.current().nextInt(0,  cellAmount);
        if((bluePlayerTurn && (bluePlayerMode == PlayerMode.COMPUTER)) || (redPlayerTurn && (redPlayerMode == PlayerMode.COMPUTER))) {
            if (findSOSMove(location, cellAmount)) {
                return true;
            }
            else if (findDefensiveMove(location, cellAmount)) {
                return true;
            }
            else {
                findRandomMove(location, cellAmount);
                return true;
            }
        }
        return false;
    }
    public Boolean findDefensiveMove(int location, int cellAmount){

        int cellIndex = ThreadLocalRandom.current().nextInt(1,  3);
        Cell randomCell = Cell.values()[cellIndex];

        for(int i = 0; i < cellAmount; i++){
            int row = location / totalRows;
            int col = location % totalColumns;
            if(grid[row][col] == Cell.EMPTY && ((randomCell == Cell.O && CheckODefensiveMove(row, col)) || (randomCell == Cell.S && CheckSDefensiveMove(row, col)))) {
                if(bluePlayerTurn){
                    bluePlayerMove = randomCell;
                }
                else{
                    redPlayerMove = randomCell;
                }
                 return makeMove(row, col);
            }
            location++;
            if(location == cellAmount){
                location = 0;
            }
        }
        return false;
    }
    public Boolean findRandomMove(int location, int cellAmount){
        int cellIndex = ThreadLocalRandom.current().nextInt(1,  3);
        Cell randomCell = Cell.values()[cellIndex];

        for(int i = 0; i < cellAmount; i++){
            int row = location / totalRows;
            int col = location % totalColumns;

            if(grid[row][col] == Cell.EMPTY) {
                if(bluePlayerTurn){
                    bluePlayerMove = randomCell;
                }
                else{
                    redPlayerMove = randomCell;
                }
                makeMove(row, col);
                return true;
            }
            location++;
            if(location == cellAmount){
                location = 0;
            }
        }
        return false;
    }
    public Boolean CheckSDefensiveMove(int row, int column){
        int x, y;
        for (int i = 0; i < 8; i++) {
            x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
            y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
            try {
                if (grid[row + y][column + x] == Cell.O){
                    return false;
                }
                if (grid[row + 2 * y][column + 2 * x] == Cell.S){
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return true;
    }
    public Boolean CheckODefensiveMove(int row, int column){
        int x, y;
        for (int i = 0; i < 8; i++) {
            x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
            y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
            try {
                if (grid[row + y][column + x] == Cell.S){
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return true;
    }
    public Boolean findSOSMove(int location, int cellAmount){
        for(int i = 0; i < cellAmount; i++){
            int row = location / totalRows;
            int col = location % totalColumns;

            if(grid[row][col] == Cell.EMPTY) {
                if (CheckOCombination(row, col) == 1) {
                    if(bluePlayerTurn){
                        bluePlayerMove = Cell.O;
                    }
                    else{
                        redPlayerMove = Cell.O;
                    }
                    return(makeMove(row, col));
                }
                if (CheckSCombination(row, col) == 1) {
                    if(bluePlayerTurn){
                        bluePlayerMove = Cell.S;
                    }
                    else{
                        redPlayerMove = Cell.S;
                    }
                    return(makeMove(row, col));
                }
            }
            location++;
            if(location == cellAmount){
                location = 0;
            }
        }
        return false;
    }
    public int CheckSCombination(int row, int column){
        int x, y;
        for (int i = 0; i < 8; i++) {
            x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
            y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
            try {
                if (grid[row + y][column + x] == Cell.O && grid[row + 2 * y][column + 2 * x] == Cell.S){
                    return 1;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return 0;
    }
    public int CheckOCombination(int row, int column){
        int x, y;
        for(int i = 0; i < 4; i++){
            x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
            y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
            try {
                if (grid[row + y][column + x] == Cell.S && grid[row + -1 * y][column + -1 * x] == Cell.S){
                    return 1;
                }
            }
            catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return 0;
    }
}