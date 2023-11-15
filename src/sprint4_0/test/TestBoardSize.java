package sprint4_0.test;

import org.junit.Before;
import org.junit.Test;
import sprint3_3.product.GameLogic;
import sprint3_3.product.SimpleGameLogic;

import static org.junit.Assert.*;

public class TestBoardSize {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new SimpleGameLogic();
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

        gameLogic.startGame(overMaxSize);
        assertNotEquals("Illegal board size exceeds limit of 12", overMaxSize,gameLogic.getTotalRows());
        assertNotEquals("Illegal board size exceeds limit of 12", overMaxSize,gameLogic.getTotalColumns());
        gameLogic.startGame(underMinSize);
        assertNotEquals("Illegal board size under limit of 3", underMinSize,gameLogic.getTotalRows());
        assertNotEquals("Illegal board size under limit of 3", underMinSize,gameLogic.getTotalColumns());

    }

    //acceptance criteria 1.3
    @Test
    public void testChangingSizeDuringGame() {
        gameLogic.startGame(8);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);

        assertEquals("Column size does not match input",8, gameLogic.getTotalColumns());
        assertEquals("Row size does not match input",8, gameLogic.getTotalColumns());
    }
}