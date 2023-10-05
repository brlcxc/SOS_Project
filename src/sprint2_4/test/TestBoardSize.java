package sprint2_4.test;

import org.junit.Before;
import org.junit.Test;
import sprint2_3.product.GameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

//acceptance criterion 1.2
public class TestBoardSize {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new GameLogic();
        gameLogic.initGame();
    }

    //acceptance criteria 1.1
    @Test
    public void boardSizeSelection() {
        int boardInput = 8;
        gameLogic.startGame(boardInput);
        assertEquals("Column size does not match input",8, gameLogic.getTotalColumns());
        assertEquals("Row size does not match input",8, gameLogic.getTotalColumns());
    }

    //acceptance criteria 1.2
    @Test
    public void outsideRangeBoardSize() {
        int overMaxSize = 13;
        int underMinSize = 2;

        assertFalse("Illegal board size exceeds limit of 12", gameLogic.startGame(overMaxSize));
        assertFalse("Illegal board size under limit of 3", gameLogic.startGame(underMinSize));

    }

    //acceptance criteria 1.3
    @Test
    public void changingSizeDuringGame() {
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertFalse("Size illegally changed during game", gameLogic.startGame(GameLogic.DEFAULT_DIMENSION));
    }
}
