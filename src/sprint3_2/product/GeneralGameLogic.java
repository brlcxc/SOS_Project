package sprint3_2.product;
//note different turn variable utilization
public class GeneralGameLogic extends GameLogic{
    private int redCombinations;
    private int blueCombinations;
    GeneralGameLogic(Cell redPlayerMove, Cell bluePlayerMove){
        super();
        this.redPlayerMove = redPlayerMove;
        this.bluePlayerMove = bluePlayerMove;
        selectedGameMode = GameMode.GENERAL;
        blueCombinations = 0;
        redCombinations = 0;
        System.out.println("test");
    }

    @Override
    public boolean makeMove(int row, int column) {
        System.out.println("help");
        if(super.makeMove(row, column)){
            piecesPlaced++;
            if (bluePlayerTurn) {
                grid[row][column] = bluePlayerMove;
                turnRecorder[row][column] = turn;
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
                turnRecorder[row][column] = turn;
                combinationMade = FindCombination(row, column);
                if(!combinationMade){
                    redPlayerTurn = false;
                    bluePlayerTurn = true;
                    turn++;
                    System.out.println("3");
                }
            }
            if (piecesPlaced == totalColumns * totalColumns) {
                if(redCombinations < blueCombinations) {
                    currentGameState = GameState.BLUE_WON;
                }
                else if(redCombinations > blueCombinations) {
                    currentGameState = GameState.RED_WON;
                }
                else {
                    currentGameState = GameState.DRAW;
                }
            }
            return true;
        }
        return false;
    }
    public Boolean FindCombination(int row, int column){
        Boolean flag = false;
        int x, y;
        if (grid[row][column] == Cell.S) {
            for (int i = 0; i < 8; i++) {
                x = (int) Math.round(Math.cos(Math.toRadians(45 * i)));
                y = (int) Math.round(Math.sin(Math.toRadians(45 * i)));
                try {
                    if (grid[row + y][column + x] == Cell.O && grid[row + 2 * y][column + 2 * x] == Cell.S){
                        combinationGrid[row][column][i] = CombinationDirection.values()[i];
                        flag = true;
                        if(selectedGameMode == GameMode.GENERAL && bluePlayerTurn){
                            blueCombinations++;
                        }
                        else if(selectedGameMode == GameMode.GENERAL && redPlayerTurn){
                            redCombinations++;
                        }
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
                    if (grid[row + y][column + x] == Cell.S && grid[row + -1 * y][column + -1 * x] == Cell.S){
                        combinationGrid[row][column][i] = CombinationDirection.values()[i];
                        flag = true;
                        if(selectedGameMode == GameMode.GENERAL && bluePlayerTurn){
                            blueCombinations++;
                        }
                        else if(selectedGameMode == GameMode.GENERAL && redPlayerTurn){
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

//I might want code to just switch who current has the player turn to make if else statement smuch shorter
//maybe I could have a class player within gamelogic that hold all that necesary information
//within that could be the boolean value that turns off and on
//would I need setters and getters with that though?
//Maybe ask someone if they are necesary for that type of thing
//maybe it wouldnt be good because I would still need to switch off who was active in the if statement
//this might give me time to do a lot of other varibale cleaning in game logic