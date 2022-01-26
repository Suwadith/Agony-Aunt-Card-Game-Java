package model;

import java.util.List;
import java.util.Stack;

public class Deck {

    private Stack<Card> deck = new Stack<>();
    
    public Deck() {
    }

    public Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public void setDeck(Stack<Card> deck) {
        this.deck = deck;
    }
    
/*Pass List of cards to Stack of Deck*/
	public static Stack<Card> addtoDeck(List<Card> cardList){
	
		Stack<Card> cardStack = new Stack<>();		
		cardStack.addAll(cardList);
		Deck cardDeck = new Deck(cardStack);		
		cardStack = cardDeck.getDeck();
		return cardStack;
	}

}
