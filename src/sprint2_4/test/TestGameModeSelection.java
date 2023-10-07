package sprint2_4.test;

import org.junit.Before;
import org.junit.Test;
import sprint2_4.product.GameLogic;

import static org.junit.Assert.assertEquals;

public class TestGameModeSelection {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new GameLogic();
        gameLogic.initGame();
    }

    //acceptance criteria 2.1
    @Test
    public void testSimpleGameSelection() {
        gameLogic.setGameMode(GameLogic.GameMode.SIMPLE);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
    }

    //acceptance criteria 2.2
    @Test
    public void testGeneralGameSelection() {
        gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertEquals("Game mode does not match General",GameLogic.GameMode.GENERAL, gameLogic.getGameMode());
    }
    //acceptance criteria 2.3
    @Test
    public void testApplyingGameModeDuringGame() {
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        assertEquals("Game mode illegally changed from SIMPLE to GENERAL",GameLogic.GameMode.SIMPLE, gameLogic.getGameMode());
    }
}