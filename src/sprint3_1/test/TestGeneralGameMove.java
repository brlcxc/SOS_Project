package sprint3_1.test;

import org.junit.Before;
import org.junit.Test;
import sprint2_4.product.GameLogic;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestGeneralGameMove {
    private GameLogic gameLogic;

    @Before
    public void setUp() throws Exception {
        gameLogic = new GameLogic();
        gameLogic.initGame();
        gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
    }

    //acceptance criteria 6.1
    @Test
    public void testMakingGeneralSMove() {
        int row = 2;
        int column = 3;
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.makeMove(row,column);
        assertEquals("Incorrect piece placement", GameLogic.Cell.S, gameLogic.getCell(row,column));
        assertTrue("Turn did not properly switch", gameLogic.getRedPlayerTurn());
    }

    //acceptance criteria 6.2
    @Test
    public void testMakingGeneralOMove() {
        int row = 2;
        int column = 3;
        gameLogic.setBluePlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(row,column);
        assertEquals("Incorrect piece placement", GameLogic.Cell.O, gameLogic.getCell(row,column));
        assertTrue("Turn did not properly switch", gameLogic.getRedPlayerTurn());
    }
    //acceptance criteria 6.4
    @Test
    public void testGeneralOccupiedCell() {
        int row = 2;
        int column = 3;
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(row,column);
        gameLogic.makeMove(row,column);
        assertEquals("Incorrect piece placement", GameLogic.Cell.S, gameLogic.getCell(row,column));
        assertTrue("Turn did not properly switch", gameLogic.getRedPlayerTurn());
    }
    //acceptance criteria 6.5
    @Test
    public void testGeneralOutsideBoundMove() {
        int row = 7;
        int column = 7;
        gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        assertFalse("Move not properly validated", gameLogic.makeMove(row,column));
    }
}