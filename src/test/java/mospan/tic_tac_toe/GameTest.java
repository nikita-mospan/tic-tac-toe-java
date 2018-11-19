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
        Board board = new Board(HUMAN);
        board.setBoard(new CellValue[][]{
                {CROSS, CROSS, NAUGHT},
                {CROSS, CROSS, NAUGHT},
                {NAUGHT, EMPTY, EMPTY}
        });
        board.switchPlayers();
        final OptimalMove optimalMove = board.makeNextMove();
        assertEquals(2, optimalMove.getRowIdx());
        assertEquals(2, optimalMove.getColumnIdx());
    }

    @Test
    public void preventLoss1 () {
        Board board = new Board(HUMAN);
        board.setBoard(new CellValue[][]{
                {CROSS, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        });
        board.switchPlayers();
        final OptimalMove optimalMove = board.makeNextMove();
        assertEquals(1, optimalMove.getRowIdx());
        assertEquals(1, optimalMove.getColumnIdx());
    }

}
