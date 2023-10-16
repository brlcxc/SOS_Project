package sprint3_0.test;

import org.junit.Before;
import org.junit.Test;
import sprint2_4.product.GameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestGameStart {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new GameLogic();
        gameLogic.initGame();
    }

    //acceptance criteria 3.1
    @Test
    public void testSuccessfullyStartingGame() {
        int boardInput = 8;
        gameLogic.setGameMode(GameLogic.GameMode.SIMPLE);
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
        gameLogic.setGameMode(GameLogic.GameMode.SIMPLE);
        assertNotEquals("Column size should not match input",boardInput, gameLogic.getTotalColumns());
        assertNotEquals("Row size should not match input",boardInput, gameLogic.getTotalColumns());
        assertNotEquals("Game state should not match PLAYING",GameLogic.GameState.PLAYING, gameLogic.getGameState());
    }
}