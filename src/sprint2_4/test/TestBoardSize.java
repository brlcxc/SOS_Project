package sprint2_4.test;

import org.junit.Before;
import org.junit.Test;
import sprint2_4.product.GameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestBoardSize {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new GameLogic();
        gameLogic.initGame();
    }

    //acceptance criteria 1.1
    @Test
    public void testBoardSizeSelection() {
        int boardInput = 8;
        gameLogic.startGame(boardInput);
        assertEquals("Column size does not match input",8, gameLogic.getTotalColumns());
        assertEquals("Row size does not match input",8, gameLogic.getTotalColumns());
    }

    //acceptance criteria 1.2
    @Test
    public void testOutsideRangeBoardSize() {
        int overMaxSize = 13;
        int underMinSize = 2;

        assertFalse("Illegal board size exceeds limit of 12", gameLogic.startGame(overMaxSize));
        assertFalse("Illegal board size under limit of 3", gameLogic.startGame(underMinSize));

    }

    //acceptance criteria 1.3
    @Test
    public void testChangingSizeDuringGame() {
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        //the size is not changed with the new start since the game is already in play
        assertFalse("Size illegally changed during game", gameLogic.startGame(GameLogic.DEFAULT_DIMENSION));
    }
}
