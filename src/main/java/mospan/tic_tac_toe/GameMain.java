package mospan.tic_tac_toe;

import static mospan.tic_tac_toe.Player.COMPUTER;
import static mospan.tic_tac_toe.Player.HUMAN;

public class GameMain {
    public static void main(String[] args) {
        Board board = new Board(HUMAN);
        board.switchPlayers();
        System.out.println(board.makeNextMove());
    }
}
