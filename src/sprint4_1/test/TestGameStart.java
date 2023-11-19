package sprint4_1.test;

import org.junit.Before;
import org.junit.Test;
import sprint3_3.product.GameLogic;
import sprint3_3.product.SimpleGameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestGameStart {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new SimpleGameLogic();
        gameLogic.initGame();
    }

    //acceptance criteria 3.1
    @Test
    public void testSuccessfullyStartingGame() {
        int boardInput = 8;
        gameLogic.startGame(boardInput);
        assertEquals("Column size does not match input",boardInput, gameLogic.getTotalColumns());
        assertEquals("Row size does not match input",boardInput, gameLogic.getTotalColumns());
        assertEquals("Game mode does not match SIMPLE",GameLogic.GameMode.SIMPLE, gameLogic.getGameMode());
        assertEquals("Game state does not match PLAYING",GameLogic.GameState.PLAYING, gameLogic.getGameState());
    }

    //acceptance criteria 3.2
    @Test
    public void testUnsuccessfullyStartingGame() {
        int boardInput = 8;
        assertNotEquals("Column size should not match input",boardInput, gameLogic.getTotalColumns());
        assertNotEquals("Row size should not match input",boardInput, gameLogic.getTotalColumns());
        assertNotEquals("Game state should not match PLAYING",GameLogic.GameState.PLAYING, gameLogic.getGameState());
    }
}