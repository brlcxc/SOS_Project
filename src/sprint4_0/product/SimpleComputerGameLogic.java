package sprint4_0.product;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleComputerGameLogic extends SimpleGameLogic{

    SimpleComputerGameLogic(){
        super();
    }
    SimpleComputerGameLogic(GameLogic.Cell redPlayerMove, GameLogic.Cell bluePlayerMove, GameLogic.PlayerMode redPlayerMode, GameLogic.PlayerMode bluePlayerMode){
        super(redPlayerMove, bluePlayerMove);
        this.redPlayerMode = redPlayerMode;
        this.bluePlayerMode = bluePlayerMode;
    }
    @Override
    public boolean makeComputerMove() {
        int cellAmount = totalRows * totalColumns;
        int location = ThreadLocalRandom.current().nextInt(0,  cellAmount);
        if(Check(location, cellAmount)){
            return true;
        }
        findRandomMove(location, cellAmount);
        return false;
        //have similar to check where it checks if the random move is not for example two away from an s
    }

    //the reason the move is not staying is because mkae move is being made on a cell that is not empty
    public void findRandomMove(int location, int cellAmount){

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
                makeComputerMove(row, col);
                break;
            }
            location++;
            if(location == cellAmount){
                location = 0;
            }
        }

    }
    public Boolean CheckSCombination(int row, int column){
        Boolean flag = false;
        int x, y;
        for (int i = 0; i < 8; i++) {
            x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
            y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
            try {
                if (grid[row + y][column + x] == Cell.O && grid[row + 2 * y][column + 2 * x] == Cell.S){
                    flag = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return flag;
    }
    public Boolean CheckOCombination(int row, int column){
        Boolean flag = false;
        int x, y;
        for(int i = 0; i < 4; i++){
            x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
            y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
            try {
                if (grid[row + y][column + x] == Cell.S && grid[row + -1 * y][column + -1 * x] == Cell.S){
                    flag = true;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return flag;
    }


    //maybe an automate player move that just calls that if else statement and switches to the paramter whihc is s or o
    public Boolean Check(int location, int cellAmount){
        for(int i = 0; i < cellAmount; i++){
            int row = location / totalRows;
            int col = location % totalColumns;

            if(grid[row][col] == Cell.EMPTY) {
                if (CheckOCombination(row, col)) {
                    if(bluePlayerTurn){
                        bluePlayerMove = Cell.O;
                    }
                    else{
                        redPlayerMove = Cell.O;
                    }
                    return(makeComputerMove(row, col));
                }
                if (CheckSCombination(row, col)) {
                    if(bluePlayerTurn){
                        bluePlayerMove = Cell.S;
                    }
                    else{
                        redPlayerMove = Cell.S;
                    }
                    return(makeComputerMove(row, col));
                }
            }
            //one issue is that find combination actually stores value so that makes extending a bit more difficult
            //the cells are momentarly changed for checking
//            System.out.println(row + " " + col);
//
//            System.out.println(currentProcessAllocation[row][col]);

            location++;
            if(location == cellAmount){
                location = 0;
            }
        }
        return false;
    }
}
