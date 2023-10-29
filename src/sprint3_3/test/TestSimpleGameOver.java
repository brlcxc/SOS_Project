package sprint3_3.test;

import org.junit.Before;
import org.junit.Test;
import sprint3_3.product.GameLogic;
import sprint3_3.product.SimpleGameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSimpleGameOver {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new SimpleGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
    }

    //acceptance criteria 5.1
    @Test
    public void testSimpleGameWin() {
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(2,2);
        gameLogic.makeMove(2,3);
        gameLogic.makeMove(2,4);
        assertEquals("Simple game should be in state BLUE_WON", GameLogic.GameState.BLUE_WON, gameLogic.getGameState());
    }

    //acceptance criteria 5.2
    @Test
    public void testSimpleGameDraw() {
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.S);
        for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
            for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                gameLogic.makeMove(row, col);
            }
        }
        assertEquals("Simple game should be in state DRAW", GameLogic.GameState.DRAW, gameLogic.getGameState());
    }

    //acceptance criteria 5.3
    @Test
    public void testSimpleGameOngoing() {
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(2,2);
        gameLogic.makeMove(2,3);
        assertEquals("Simple game should be in state PLAYING", GameLogic.GameState.PLAYING, gameLogic.getGameState());
        assertTrue("Player turn should be blue", gameLogic.getBluePlayerTurn());
    }
}