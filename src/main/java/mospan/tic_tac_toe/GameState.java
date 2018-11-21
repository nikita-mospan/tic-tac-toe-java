package mospan.tic_tac_toe;

import java.util.Arrays;
import static mospan.tic_tac_toe.CellValue.CROSS;
import static mospan.tic_tac_toe.CellValue.EMPTY;
import static mospan.tic_tac_toe.CellValue.NAUGHT;
import static mospan.tic_tac_toe.GameEndStatus.COMPUTER_WON;
import static mospan.tic_tac_toe.GameEndStatus.DRAW;
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

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    void switchPlayers() {
        Player tempPlayer = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tempPlayer;
    }

    OptimalMove calculateNextMove() {

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
                    scoreForNextMove = calculateNextMove().getScore();
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

    void makeNextMove() {
        final OptimalMove optimalMove = calculateNextMove();
        switchPlayers();
        updateBoard(optimalMove.getRowIdx(), optimalMove.getColumnIdx());
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
        resultBuilder.append(opponentPlayer.getPlayerName());
        resultBuilder.append("\n");
        for (CellValue[] row : board) {
            Arrays.stream(row).forEach(resultBuilder::append);
            resultBuilder.append("\n");
        }
        resultBuilder.append("------------------------------");
        return resultBuilder.toString();
    }

    void setBoard(CellValue[][] board) {
        this.board = board;
    }

    GameEndStatus getGameEndStatusOrNull() {
        if (currentPlayer.getPlayerName() == HUMAN && getOpponentWinnerOrNull() != null) {
            return COMPUTER_WON;
        } else {
            for (int rowIdx = 0; rowIdx < BOARD_LENGTH; rowIdx++) {
                for (int columnIdx = 0; columnIdx < BOARD_LENGTH; columnIdx++) {
                    if (board[rowIdx][columnIdx] == EMPTY) {
                        return null;
                    }
                }
            }
        }
        return DRAW;
    }

    void updateBoard (int rowIdx, int columnIdx) {
        if (rowIdx < 0 || rowIdx >= BOARD_LENGTH || columnIdx < 0 || columnIdx >= BOARD_LENGTH) {
            throw new IllegalStateException(String.format("RowIdx: %d, ColumnIdx: %d are out of range!", rowIdx, columnIdx));
        } else if (board[rowIdx][columnIdx] != EMPTY) {
            throw new IllegalStateException("Attempt to update non-empty field!");
        } else {
            board[rowIdx][columnIdx] = currentPlayer.getPlayerValue();
            switchPlayers();
        }
    }
}
