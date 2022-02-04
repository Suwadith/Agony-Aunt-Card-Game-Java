package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {

    private int playerNumber;
    private String playerName;
    private int counterBalance;
    private Counter counter;
    private Map<Integer, Card> playingCards;
    private Map<Integer, Card> cardsWon;
    private int[] trickRoundsWon;

    public Player() {
    }
    
    public Player(int playerNumber, String playerName, int counterBalance, Counter counter) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.counterBalance = counterBalance;
        this.counter = counter;
        this.playingCards = new HashMap<>();
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

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Map<Integer, Card> getPlayingCards() {
        return playingCards;
    }

    public void setPlayingCards(Map<Integer, Card> playingCards) {
        this.playingCards = playingCards;
    }

    public void updatePlayingCards(Integer x, Card card) {
        this.playingCards.put(x, card);
    }

    public Map<Integer, Card> getCardsWon() {
        return cardsWon;
    }

    public void setCardsWon(Map<Integer, Card> cardsWon) {
        this.cardsWon = cardsWon;
    }

    public int[] getTrickRoundsWon() {
        return trickRoundsWon;
    }

    public void setTrickRoundsWon(int[] trickRoundsWon) {
        this.trickRoundsWon = trickRoundsWon;
    }

    public void removeCard(Integer cardNumber) {
        playingCards.remove(cardNumber);
    }

}
