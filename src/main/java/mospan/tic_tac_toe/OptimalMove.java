package mospan.tic_tac_toe;

public final class OptimalMove {
    private int rowIdx;
    private int columnIdx;
    private int score;

    OptimalMove(int rowIdx, int columnIdx, int score) {
        this.rowIdx = rowIdx;
        this.columnIdx = columnIdx;
        this.score = score;
    }

    int getScore() {
        return score;
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
