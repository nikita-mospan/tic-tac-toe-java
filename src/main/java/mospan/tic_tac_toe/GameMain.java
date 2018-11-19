package mospan.tic_tac_toe;

import static mospan.tic_tac_toe.Player.COMPUTER;

public class GameMain {
    public static void main(String[] args) {
        Board board = new Board(COMPUTER);
        System.out.println(board.makeNextMove());
    }
}
