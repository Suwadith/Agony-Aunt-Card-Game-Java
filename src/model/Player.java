package model;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player implements Comparable<Player>{

    private int playerNumber;
    private String playerName;
    private Stack<Counter> counters = new Stack<>();
    private Counter counter;
    private Map<Integer, Card> playingCards;
    private Map<Integer, Card> cardsWon;
    private int trickRoundsWon;
    private List<Card> totalCardsWon = new ArrayList<>();

    public Player() {
    }
    
    public Player(int playerNumber, String playerName, Counter counter) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        assignCounters(counter);
        this.playingCards = new HashMap<>();
    }

    public void assignCounters(Counter counter) {
        for(int i=0; i<17; i++) {
            this.counters.push(counter);
        }
    }

	public void setCounters(Stack<Counter> counters) {
		this.counters = counters;
	}

    public Stack<Counter> getCounters() {
		return counters;
	}	
	
	public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
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

    public void removeCard(Integer cardNumber) {
        playingCards.remove(cardNumber);
    }

    public int getTrickRoundsWon() {
        return trickRoundsWon;
    }

    public void incrementTrickRoundsWon() {
        this.trickRoundsWon += 1;
    }

    public void updateTotalCardsWon(Card card) {
        totalCardsWon.add(card);
    }

    public List<Card> getTotalCardsWon() {
        return totalCardsWon;
    }

    @Override
    public int compareTo(Player o) {
        if (this.getTrickRoundsWon() > o.getTrickRoundsWon()) {
            return 1;
        } else if (this.getTrickRoundsWon() < o.getTrickRoundsWon()) {
            return -1;
        }
        return 0;
    }

}
