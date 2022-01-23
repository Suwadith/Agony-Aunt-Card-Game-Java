package model;

import java.util.Map;

public class Player {

    private int playerNumber;
    private String playerName;
    private int counterBalance;
    private Counter counter;
    private Map<Integer, Card> playerCards;

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

    public Map<Integer, Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(Map<Integer, Card> playerCards) {
        this.playerCards = playerCards;
    }
}
