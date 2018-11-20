package mospan.tic_tac_toe;

import org.junit.Test;

import static mospan.tic_tac_toe.CellValue.CROSS;
import static mospan.tic_tac_toe.CellValue.EMPTY;
import static mospan.tic_tac_toe.CellValue.NAUGHT;
import static mospan.tic_tac_toe.Player.HUMAN;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GameTest {

    @Test
    public void finishGame1 () {
        GameState gameState = new GameState(HUMAN);
        gameState.setBoard(new CellValue[][]{
                {CROSS, CROSS, NAUGHT},
                {CROSS, CROSS, NAUGHT},
                {NAUGHT, EMPTY, EMPTY}
        });
        gameState.switchPlayers();
        final OptimalMove optimalMove = gameState.makeNextMove();
        assertEquals(2, optimalMove.getRowIdx());
        assertEquals(2, optimalMove.getColumnIdx());
    }

    @Test
    public void preventLoss1 () {
        GameState gameState = new GameState(HUMAN);
        gameState.setBoard(new CellValue[][]{
                {CROSS, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        });
        gameState.switchPlayers();
        final OptimalMove optimalMove = gameState.makeNextMove();
        assertEquals(1, optimalMove.getRowIdx());
        assertEquals(1, optimalMove.getColumnIdx());
    }

}
