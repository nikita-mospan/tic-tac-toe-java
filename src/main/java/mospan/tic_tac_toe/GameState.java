package mospan.tic_tac_toe;

import static mospan.tic_tac_toe.CellValue.CROSS;
import static mospan.tic_tac_toe.CellValue.EMPTY;
import static mospan.tic_tac_toe.CellValue.NAUGHT;
import static mospan.tic_tac_toe.PlayerName.COMPUTER;
import static mospan.tic_tac_toe.PlayerName.HUMAN;

public class GameState {
    private CellValue board[][];
    private Player currentPlayer;
    private Player opponentPlayer;
    private static final int BOARD_LENGTH = 3;

    GameState(PlayerName currentPlayerName) {
        this.board = new CellValue[][]{
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };

        currentPlayer = new Player(currentPlayerName, CROSS);
        opponentPlayer = new Player(currentPlayerName == HUMAN ? COMPUTER : HUMAN, NAUGHT);
    }

    void switchPlayers() {
        Player tempPlayer = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tempPlayer;
    }

    OptimalMove makeNextMove() {

        Player opponentWinner = getOpponentWinnerOrNull();

        if (opponentWinner != null) {
            switchPlayers();
            return new OptimalMove(-1, -1, opponentWinner.getPlayerName().getScore());
        }

        int score = 0;
        boolean firstComparison = true;
        int scoreForNextMove;
        OptimalMove optimalMove = new OptimalMove(-1, -1, 0);

        for (int rowIdx = 0; rowIdx < BOARD_LENGTH; rowIdx++) {
            for (int columnIdx = 0; columnIdx < BOARD_LENGTH; columnIdx++) {
                if (board[rowIdx][columnIdx] == EMPTY) {
                    board[rowIdx][columnIdx] = currentPlayer.getPlayerValue();
                    switchPlayers();
                    scoreForNextMove = makeNextMove().getScore();
                    if ((scoreForNextMove > score && currentPlayer.getPlayerName() == COMPUTER)
                            || (scoreForNextMove < score && currentPlayer.getPlayerName() == HUMAN)
                            || firstComparison) {
                        firstComparison = false;
                        score = scoreForNextMove;
                        optimalMove = new OptimalMove(rowIdx, columnIdx, score);
                    }
                    board[rowIdx][columnIdx] = EMPTY;
                }
            }
        }

        switchPlayers();
        return optimalMove;
    }

    private Player getOpponentWinnerOrNull() {
        CellValue opponentPlayerValue = opponentPlayer.getPlayerValue();
        if ((board[0][0] == opponentPlayerValue && board[0][1] == opponentPlayerValue && board[0][2] == opponentPlayerValue) ||
                (board[1][0] == opponentPlayerValue && board[1][1] == opponentPlayerValue && board[1][2] == opponentPlayerValue) ||
                (board[2][0] == opponentPlayerValue && board[2][1] == opponentPlayerValue && board[2][2] == opponentPlayerValue) ||
                (board[0][0] == opponentPlayerValue && board[1][0] == opponentPlayerValue && board[2][0] == opponentPlayerValue) ||
                (board[0][1] == opponentPlayerValue && board[1][1] == opponentPlayerValue && board[2][1] == opponentPlayerValue) ||
                (board[0][2] == opponentPlayerValue && board[1][2] == opponentPlayerValue && board[2][2] == opponentPlayerValue) ||
                (board[0][0] == opponentPlayerValue && board[1][1] == opponentPlayerValue && board[2][2] == opponentPlayerValue) ||
                (board[0][2] == opponentPlayerValue && board[1][1] == opponentPlayerValue && board[2][0] == opponentPlayerValue)) {
            return opponentPlayer;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(opponentPlayer);
        resultBuilder.append("\n");
        for (CellValue[] row : board) {
            for (CellValue cell : row) {
                switch (cell) {
                    case EMPTY:
                        resultBuilder.append("* ");
                        break;
                    case CROSS:
                        resultBuilder.append("X ");
                        break;
                    case NAUGHT:
                        resultBuilder.append("0 ");
                        break;
                }
            }
            resultBuilder.append("\n");
        }
        resultBuilder.append("------------------------------");
        return resultBuilder.toString();
    }


    void setBoard(CellValue[][] board) {
        this.board = board;
    }
}
