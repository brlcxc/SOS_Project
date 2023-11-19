package sprint4_0.product;

import java.util.concurrent.ThreadLocalRandom;

public class GeneralComputerGameLogic extends GeneralGameLogic {
    GeneralComputerGameLogic(){
        super();
    }
    GeneralComputerGameLogic(GameLogic.Cell redPlayerMove, GameLogic.Cell bluePlayerMove, GameLogic.PlayerMode redPlayerMode, GameLogic.PlayerMode bluePlayerMode){
        super(redPlayerMove, bluePlayerMove);
        this.redPlayerMode = redPlayerMode;
        this.bluePlayerMode = bluePlayerMode;
    }

//    @Override
//    public boolean makeMove(int row, int column) {
//    }
    //should I start with balnk values and then check to change or should I start with
    //should I externally use player mode to see if I can use an if statement

    //maybe parent make move with no paremters that returns false
    //just having a player turn variable would make things mcuh easier for structure rather than boolean

/*    @Override
    public boolean makeComputerMove() {

        //Note: I want to find the one that makes the most completions
        //search s completions
        //search o completions

        //maybe I could find s or o combiantions at the same time?
        //might cause some issues though
        //block move - random for now
        //dont place two away from S with S
        //dont place next to S with O

        ///this will be much easier for simple game
        return false;
    }*/
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

        int maxRow = 0;
        int maxCol = 0;
        int maxCombinations = 0;

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
            location++;
            if(location == cellAmount){
                location = 0;
            }
        }
        return false;
    }
    //have a if max = 0 return false
}

//I need to pass who is the computer just like with the other game modes

//maybe lock s and o and chage them to what the computer is doing