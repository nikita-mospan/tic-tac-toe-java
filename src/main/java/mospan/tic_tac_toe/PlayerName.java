package mospan.tic_tac_toe;

public enum PlayerName {
    HUMAN(-1),
    COMPUTER(1);

    private int score;

    PlayerName(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
