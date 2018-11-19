package mospan.tic_tac_toe;

import static mospan.tic_tac_toe.CellValue.CROSS;
import static mospan.tic_tac_toe.CellValue.EMPTY;
import static mospan.tic_tac_toe.CellValue.NAUGHT;
import static mospan.tic_tac_toe.Player.COMPUTER;
import static mospan.tic_tac_toe.Player.HUMAN;

public class Board {
    private CellValue board[][];
    private Player currentPlayer;
    private Player opponentPlayer;
    private CellValue currentPlayerValue;
    private CellValue opponentPlayerValue;
    private static final int BOARD_LENGTH = 3;

    private Player getOpponentPlayer() {
        return opponentPlayer;
    }

    private Player getCurrentPlayer() {
        return currentPlayer;
    }

    private CellValue getCurrentPlayerValue() {
        return currentPlayerValue;
    }

    private CellValue getOpponentPlayerValue() {
        return opponentPlayerValue;
    }


    Board(Player currentPlayer) {
        this.board = new CellValue[][]{
                {NAUGHT, EMPTY, CROSS},
                {CROSS, EMPTY, EMPTY},
                {CROSS, NAUGHT, NAUGHT}
        };
        this.currentPlayer = currentPlayer;
        this.currentPlayerValue = CROSS;
        this.opponentPlayer = currentPlayer == HUMAN ? COMPUTER : HUMAN;
        this.opponentPlayerValue = NAUGHT;
    }

    private Board(Board board) {
        this.board = new CellValue[BOARD_LENGTH][BOARD_LENGTH];
        for (int rowIdx = 0; rowIdx < BOARD_LENGTH; rowIdx++) {
            System.arraycopy(board.getBoard()[rowIdx], 0, this.board[rowIdx], 0, BOARD_LENGTH);
        }
        this.currentPlayer = board.getCurrentPlayer();
        this.currentPlayerValue = board.getCurrentPlayerValue();
        this.opponentPlayer = board.getOpponentPlayer();
        this.opponentPlayerValue = board.getOpponentPlayerValue();
    }

    private void switchPlayers() {
        Player tempPlayer = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tempPlayer;
        CellValue tempCellValue = currentPlayerValue;
        currentPlayerValue = opponentPlayerValue;
        opponentPlayerValue = tempCellValue;
    }

    OptimalMove makeNextMove() {

        Player opponentWinner = getWinnerAfterOpponentMove();

        if (opponentWinner != null) {
            return new OptimalMove(-1, -1, -1);
        }

        int score = HUMAN.getWeight() - 1;      //make score the lowest value
        int scoreForNextMove;
        OptimalMove nextOptimalMove;
        OptimalMove optimalMove = new OptimalMove(-1, -1, 0);

        for (int rowIdx = 0; rowIdx < BOARD_LENGTH; rowIdx++) {
            for (int columnIdx = 0; columnIdx < BOARD_LENGTH; columnIdx++) {
                if (board[rowIdx][columnIdx] == EMPTY) {
                    Board boardWithNewMove = new Board(this);
                    boardWithNewMove.getBoard()[rowIdx][columnIdx] = currentPlayerValue;
                    boardWithNewMove.switchPlayers();
                    nextOptimalMove = boardWithNewMove.makeNextMove();
                    scoreForNextMove = -nextOptimalMove.getScore();
                    if (scoreForNextMove > score) {
                        score = scoreForNextMove;
                        optimalMove = new OptimalMove(rowIdx, columnIdx, score);
                    }
                }
            }
        }
        return optimalMove;
    }

    private Player getWinnerAfterOpponentMove() {
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

    private CellValue[][] getBoard() {
        return board;
    }
}
