package sprint5_0.test;

import org.junit.Before;
import org.junit.Test;
import sprint5_0.product.GameLogic;
import sprint5_0.product.SimpleComputerGameLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestComputerOpponent {
    private GameLogic gameLogic;
    @Before
    public void setUp() throws Exception{
        gameLogic = new SimpleComputerGameLogic();
        gameLogic.initGame();
    }
    //acceptance criteria 8.1
    @Test
    public void testApplyingComputerMode() {
        gameLogic.setBluePlayerMode(GameLogic.PlayerMode.COMPUTER);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertEquals("Player mode does not match Computer",GameLogic.PlayerMode.COMPUTER, gameLogic.getBluePlayerMode());
        assertTrue("Move unsuccessfully made", gameLogic.makeComputerMove());
    }

    //acceptance criteria 8.2
    @Test
    public void testSOSMove() {
        gameLogic.setBluePlayerMode(GameLogic.PlayerMode.COMPUTER);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        gameLogic.makeComputerMove();
        gameLogic.setRedPlayerMove(GameLogic.Cell.S);
        gameLogic.makeMove(0,0);
        gameLogic.makeComputerMove();
        gameLogic.setRedPlayerMove(GameLogic.Cell.O);
        gameLogic.makeMove(0,1);
        gameLogic.makeComputerMove();
        assertEquals("Game should be in state BLUE_WON", GameLogic.GameState.BLUE_WON, gameLogic.getGameState());
    }
    //acceptance criteria 8.3
    @Test
    public void testDefensiveMove() {
        gameLogic.setBluePlayerMode(GameLogic.PlayerMode.COMPUTER);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertTrue("Defensive move unsuccessfully made", gameLogic.findDefensiveMove(0, GameLogic.DEFAULT_DIMENSION * GameLogic.DEFAULT_DIMENSION));
    }
    //acceptance criteria 8.4
    @Test
    public void testRandomMove() {
        gameLogic.setBluePlayerMode(GameLogic.PlayerMode.COMPUTER);
        gameLogic.startGame(GameLogic.DEFAULT_DIMENSION);
        assertTrue("Defensive move unsuccessfully made", gameLogic.findRandomMove(0, GameLogic.DEFAULT_DIMENSION * GameLogic.DEFAULT_DIMENSION));
    }
}