package sprint5_0.product;

public abstract class SimpleGameLogic extends GameLogic{
    public SimpleGameLogic(){
        super();
        selectedGameMode = GameMode.SIMPLE;
    }
    public SimpleGameLogic(Cell redPlayerMove, Cell bluePlayerMove){
        super();
        this.redPlayerMove = redPlayerMove;
        this.bluePlayerMove = bluePlayerMove;
        selectedGameMode = GameMode.SIMPLE;
    }
    @Override
    public boolean makeMove(int row, int column) {
        if (super.makeMove(row, column)) {
            if (bluePlayerTurn) {
                grid[row][column] = bluePlayerMove;
                turnRecorder[row][column] = turn;
                combinationMade = findCombination(row, column);
                if(combinationMade){
                    currentGameState = GameState.BLUE_WON;
                }
                else if (piecesPlaced == totalColumns * totalColumns) {
                    currentGameState = GameState.DRAW;
                }
                else {
                    bluePlayerTurn = false;
                    redPlayerTurn = true;
                }
            } else {
                grid[row][column] = redPlayerMove;
                turnRecorder[row][column] = turn;
                combinationMade = findCombination(row, column);
                if(combinationMade){
                    currentGameState = GameState.RED_WON;
                }
                else if (piecesPlaced == totalColumns * totalColumns) {
                    currentGameState = GameState.DRAW;
                }
                else {
                    bluePlayerTurn = true;
                    redPlayerTurn = false;
                }
            }
            turn++;
            return true;
        }
        return false;
    }
    public Boolean findCombination(int row, int column){
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
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
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
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return flag;
    }
}

//I might just use a stack for the recorder that just runs through the make move function
//I need to account for location and type