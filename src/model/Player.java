package model;

public class Player {

    private int playerNumber;
    private String playerName;
    private int counterBalance;
    private Counter counter;

    public Player(int playerNumber, String playerName, int counterBalance, Counter counter) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.counterBalance = counterBalance;
        this.counter = counter;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCounterBalance() {
        return counterBalance;
    }

    public void setCounterBalance(int counterBalance) {
        this.counterBalance = counterBalance;
    }

    public Counter getCounter() {
        return counter;
    }

}
