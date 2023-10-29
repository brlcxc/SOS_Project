package sprint3_3.test;

import org.junit.Before;
import org.junit.Test;
import sprint3_3.product.GameLogic;
import sprint3_3.product.GeneralGameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGeneralGameOver {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new GeneralGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
    }

    //acceptance criteria 7.1
    @Test
    public void testGeneralGameWin() {
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
            for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                gameLogic.makeMove(row, col);
            }
        }
        assertEquals("General game should be in state BLUE_WON", GameLogic.GameState.BLUE_WON, gameLogic.getGameState());
    }

    //acceptance criteria 7.2
    @Test
    public void testGeneralGameDraw() {
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.S);
        for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
            for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                gameLogic.makeMove(row, col);
            }
        }
        assertEquals("General game should be in state DRAW", GameLogic.GameState.DRAW, gameLogic.getGameState());
    }

    //acceptance criteria 7.3
    @Test
    public void testGeneralGameOngoing() {
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(2,2);
        gameLogic.makeMove(2,3);
        assertEquals("General game should be in state PLAYING", GameLogic.GameState.PLAYING, gameLogic.getGameState());
        assertTrue("Player turn should be blue", gameLogic.getBluePlayerTurn());
    }
}
