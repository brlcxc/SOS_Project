package sprint5_0.test;

import org.junit.Before;
import org.junit.Test;
import sprint4_1.product.GameLogic;
import sprint4_1.product.SimpleComputerGameLogic;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestSimpleGameMove {
    private GameLogic gameLogic;

    @Before
    public void setUp() throws Exception {
        gameLogic = new SimpleComputerGameLogic();
        gameLogic.initGame();
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
    }

    //acceptance criteria 4.1
    @Test
    public void testMakingSimpleSMove() {
        int row = 2;
        int column = 3;
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.makeMove(row,column);
        assertEquals("Incorrect piece placement", GameLogic.Cell.S, gameLogic.getCell(row,column));
        assertTrue("Turn did not properly switch", gameLogic.getRedPlayerTurn());
    }

    //acceptance criteria 4.2
    @Test
    public void testMakingSimpleOMove() {
        int row = 2;
        int column = 3;
        gameLogic.setBluePlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(row,column);
        assertEquals("Incorrect piece placement", GameLogic.Cell.O, gameLogic.getCell(row,column));
        assertTrue("Turn did not properly switch", gameLogic.getRedPlayerTurn());
    }
    //acceptance criteria 4.3
    @Test
    public void testSimpleOccupiedCell() {
        int row = 2;
        int column = 3;
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(row,column);
        gameLogic.makeMove(row,column);
        assertEquals("Incorrect piece placement", GameLogic.Cell.S, gameLogic.getCell(row,column));
        assertTrue("Turn did not properly switch", gameLogic.getRedPlayerTurn());
    }
    //acceptance criteria 4.4
    @Test
    public void testSimpleOutsideBoundMove() {
        int row = 7;
        int column = 7;
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        assertFalse("Move not properly validated", gameLogic.makeMove(row,column));
    }
}