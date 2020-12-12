package mospan.tic_tac_toe;

public enum CellValue {
    CROSS("X "),
    EMPTY("* "),
    NAUGHT("0 ");

    private final String value;

    CellValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
