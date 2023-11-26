package sprint5_0.test;

import org.junit.Test;
import sprint4_1.product.GameLogic;
import sprint4_1.product.SimpleComputerGameLogic;
import sprint4_1.product.GeneralComputerGameLogic;

import static org.junit.Assert.assertEquals;

public class TestGameModeSelection {
    private GameLogic gameLogic;

    //acceptance criteria 2.1
    @Test
    public void testSimpleGameSelection() {
        gameLogic = new SimpleComputerGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertEquals("Game mode does not match Simple",GameLogic.GameMode.SIMPLE, gameLogic.getGameMode());
    }

    //acceptance criteria 2.2
    @Test
    public void testGeneralGameSelection() {
        gameLogic = new GeneralComputerGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertEquals("Game mode does not match General",GameLogic.GameMode.GENERAL, gameLogic.getGameMode());
    }
    //acceptance criteria 2.3
    @Test
    public void testApplyingGameModeDuringGame() {
        gameLogic = new SimpleComputerGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        assertEquals("Game mode illegally changed from SIMPLE to GENERAL",GameLogic.GameMode.SIMPLE, gameLogic.getGameMode());
    }
}