package mospan.tic_tac_toe;

final class Player {
    private final PlayerName playerName;
    private final CellValue playerValue;

    Player(PlayerName playerName, CellValue playerValue) {
        this.playerName = playerName;
        this.playerValue = playerValue;
    }

    PlayerName getPlayerName() {
        return playerName;
    }

    CellValue getPlayerValue() {
        return playerValue;
    }
}
