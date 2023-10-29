package sprint3_2.product;

public class SimpleGameLogic extends GameLogic{
    SimpleGameLogic(Cell redPlayerMove, Cell bluePlayerMove){
        super();
        this.redPlayerMove = redPlayerMove;
        this.bluePlayerMove = bluePlayerMove;
        selectedGameMode = GameMode.SIMPLE;
        System.out.println("test1");
    }
    @Override
    public boolean makeMove(int row, int column) {
        System.out.println("help1");
        if (super.makeMove(row, column)) {
            if (bluePlayerTurn) {
                grid[row][column] = bluePlayerMove;
                turnRecorder[row][column] = turn;
                combinationMade = FindCombination(row, column);
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
                combinationMade = FindCombination(row, column);
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
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return flag;
    }
}
