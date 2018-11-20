package mospan.tic_tac_toe;

import static mospan.tic_tac_toe.PlayerName.COMPUTER;
import static mospan.tic_tac_toe.PlayerName.HUMAN;

public class GameMain {
    public static void main(String[] args) {
        GameState gameState = new GameState(COMPUTER);
        System.out.println(gameState.makeNextMove());
    }
}
