package model;

public class Game {

    private Player[] players;
    private DumpCard dumpCard;
    private Trick trick;

    public Game(Player[] players, DumpCard dumpCard, Trick trick) {
        this.players = players;
        this.dumpCard = dumpCard;
        this.trick = trick;
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

    public Trick getTrick() {
        return trick;
    }

    public void setTrick(Trick trick) {
        this.trick = trick;
    }
}
