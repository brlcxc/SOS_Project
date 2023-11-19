package sprint4_1.product;
//note different turn variable utilization
public abstract class GeneralGameLogic extends GameLogic{
    private int redCombinations;
    private int blueCombinations;
    public GeneralGameLogic(){
        super();
        selectedGameMode = GameMode.GENERAL;
    }
    public GeneralGameLogic(Cell redPlayerMove, Cell bluePlayerMove){
        super();
        this.redPlayerMove = redPlayerMove;
        this.bluePlayerMove = bluePlayerMove;
        selectedGameMode = GameMode.GENERAL;
        blueCombinations = 0;
        redCombinations = 0;
    }

    @Override
    public boolean makeMove(int row, int column) {
        if(super.makeMove(row, column)){
            //blue player turn
            if (bluePlayerTurn) {
                grid[row][column] = bluePlayerMove;
                turnRecorder[row][column] = turn;
                combinationMade = findCombination(row, column);
                //turn only switches if there was no combination
                if(!combinationMade){
                    bluePlayerTurn = false;
                    redPlayerTurn = true;
                    turn++;
                }
            }
            //red player turn
            else if (redPlayerTurn) {
                grid[row][column] = redPlayerMove;
                turnRecorder[row][column] = turn;
                combinationMade = findCombination(row, column);
                //turn only switches if there was no combination
                if(!combinationMade){
                    redPlayerTurn = false;
                    bluePlayerTurn = true;
                    turn++;
                }
            }
            //The end of the game is signaled once the board has been filled
            if (piecesPlaced == totalColumns * totalColumns) {
                if(redCombinations < blueCombinations) {
                    currentGameState = GameState.BLUE_WON;
                }
                else if(redCombinations > blueCombinations) {
                    currentGameState = GameState.RED_WON;
                }
                //the last statement will be called if the same number of combinations were bade by both
                //or if there were no combinations
                else {
                    currentGameState = GameState.DRAW;
                }
            }
            return true;
        }
        return false;
    }
    public Boolean findCombination(int row, int column){
        Boolean flag = false;
        int x, y;
        //searching for combinations connected to an "S" move
        if (grid[row][column] == Cell.S) {
            //The loop iterates up to size 8 because there are 8 possible directions for a combination to form with an S placement
            //It is important to account for multiple combinations considering one piece placement can form multiple combinations at once
            for (int i = 0; i < 8; i++) {
                //The change in index for moving in a circular motion around a point in a grid mimics the values from sin and cos in the unit circle
                //45 is multiplied with the index because it makes up 1/8 of a circle in degrees
                //The number 8 again relates to the maximum possible SOS combinations from a single S piece
                //The degrees are converted to radians in accordance to the cos and sin function conditions
                //The sin and cos value is rounded to a whole number so that the final values together represents the quadrants and axis's in the unit circle
                //This provides the values -1, 0, and 1 to be added to the row and column
                x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
                y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
                //The try and catch block is used to catch any searches that are out of bounds
                try {
                    if (grid[row + y][column + x] == Cell.O && grid[row + 2 * y][column + 2 * x] == Cell.S){
                        //.values() is used with the type enum because there is a cardinal direction associated with each index
                        combinationGrid[row][column][i] = CombinationDirection.values()[i];
                        flag = true;
                        if(bluePlayerTurn){
                            blueCombinations++;
                        }
                        else if(redPlayerTurn){
                            redCombinations++;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        else if (grid[row][column] == Cell.O){
            //The methodology for finding combinations with an O placement is similar to that of an S placement
            //The primary difference is that pieces on either side in the same direction are looked at
            //This means that there are only four possible combinations
            for(int i = 0; i < 4; i++){
                x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
                y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
                try {
                    if (grid[row + y][column + x] == Cell.S && grid[row + -1 * y][column + -1 * x] == Cell.S){
                        combinationGrid[row][column][i] = CombinationDirection.values()[i];
                        flag = true;
                        if(bluePlayerTurn){
                            blueCombinations++;
                        }
                        else if(redPlayerTurn){
                            redCombinations++;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return flag;
    }
}