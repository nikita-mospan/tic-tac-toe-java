package mospan.tic_tac_toe;

import java.util.Scanner;

import static mospan.tic_tac_toe.GameEndStatus.COMPUTER_WON;
import static mospan.tic_tac_toe.GameEndStatus.DRAW;
import static mospan.tic_tac_toe.PlayerName.COMPUTER;
import static mospan.tic_tac_toe.PlayerName.HUMAN;

public class GameMain {
    public static void main(String[] args) {
        GameState gameState = new GameState(HUMAN);
        Scanner scanner = new Scanner(System.in);
        int rowIdx ;
        int columnIdx;
        while (true) {
            if (gameState.getCurrentPlayer().getPlayerName() == COMPUTER) {
                if (gameState.getGameEndStatusOrNull() == DRAW) {
                    System.out.println("It is DRAW! End of game!");
                    break;
                } else {
                    gameState.makeNextMove();
                    System.out.println(gameState);
                }
            } else {
                if (gameState.getGameEndStatusOrNull() == COMPUTER_WON) {
                    System.out.println("Computer WON! End of game!");
                    break;
                } else {
                    while (true) {
                        try {
                            System.out.print("Enter rowIdx and columnIdx separated by space:");
                            final String[] splitInput = scanner.nextLine().split(" ");
                            rowIdx = Integer.parseInt(splitInput[0]);
                            columnIdx = Integer.parseInt(splitInput[1]);
                            gameState.updateBoard(rowIdx, columnIdx);
                            System.out.println(gameState);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                        }
                    }
                }
            }
        }
    }
}
