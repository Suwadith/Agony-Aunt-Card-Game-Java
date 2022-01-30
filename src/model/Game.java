package model;

public class Game {

    private Player[] players;
    private DumpCard dumpCard;
    private Trick[] tricks;
    private Player winner;

    public Game(Player[] players, DumpCard dumpCard) {
        this.players = players;
        this.dumpCard = dumpCard;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public DumpCard getDumpCard() {
        return dumpCard;
    }

    public void setDumpCard(DumpCard dumpCard) {
        this.dumpCard = dumpCard;
    }

    public Trick[] getTricks() {
        return tricks;
    }

    public void setTricks(Trick[] tricks) {
        this.tricks = tricks;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
