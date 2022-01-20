package model;

import java.util.Stack;

public class Deck {

    private Stack<Card> deck = new Stack<>();

    public Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public void setDeck(Stack<Card> deck) {
        this.deck = deck;
    }
}
