package mospan.tic_tac_toe;

import static mospan.tic_tac_toe.Player.COMPUTER;
import static mospan.tic_tac_toe.Player.HUMAN;

public class GameMain {
    public static void main(String[] args) {
        GameState gameState = new GameState(COMPUTER);
        System.out.println(gameState.makeNextMove());
    }
}
