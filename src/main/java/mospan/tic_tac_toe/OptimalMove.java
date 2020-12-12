package mospan.tic_tac_toe;

public final class OptimalMove {
    private final int rowIdx;
    private final int columnIdx;
    private final int score;

    OptimalMove(int rowIdx, int columnIdx, int score) {
        this.rowIdx = rowIdx;
        this.columnIdx = columnIdx;
        this.score = score;
    }

    int getScore() {
        return score;
    }

    int getRowIdx() {
        return rowIdx;
    }

    int getColumnIdx() {
        return columnIdx;
    }

    @Override
    public String toString() {
        return "OptimalMove{" +
                "rowIdx=" + rowIdx +
                ", columnIdx=" + columnIdx +
                ", score=" + score +
                '}';
    }
}
