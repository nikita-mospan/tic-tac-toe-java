package mospan.tic_tac_toe;

public enum Player {
    HUMAN(-1),
    COMPUTER(1);

    private int weight;

    Player(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
