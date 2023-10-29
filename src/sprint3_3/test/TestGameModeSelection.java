package sprint3_3.test;

import org.junit.Test;
import sprint3_3.product.GameLogic;
import sprint3_3.product.SimpleGameLogic;
import sprint3_3.product.GeneralGameLogic;

import static org.junit.Assert.assertEquals;

public class TestGameModeSelection {
    private GameLogic gameLogic;

    //acceptance criteria 2.1
    @Test
    public void testSimpleGameSelection() {
        gameLogic = new SimpleGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertEquals("Game mode does not match Simple",GameLogic.GameMode.SIMPLE, gameLogic.getGameMode());
    }

    //acceptance criteria 2.2
    @Test
    public void testGeneralGameSelection() {
        gameLogic = new GeneralGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertEquals("Game mode does not match General",GameLogic.GameMode.GENERAL, gameLogic.getGameMode());
    }
    //acceptance criteria 2.3
    @Test
    public void testApplyingGameModeDuringGame() {
        gameLogic = new SimpleGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        assertEquals("Game mode illegally changed from SIMPLE to GENERAL",GameLogic.GameMode.SIMPLE, gameLogic.getGameMode());
    }
}