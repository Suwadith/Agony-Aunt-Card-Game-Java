package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {

    private Suit suit;
    private Rank rank;
    private int number;

    public Card() {
    }

    public Suit getSuit() {
        return suit;
    }

    public Card(Suit suit, Rank rank, int number) {
        this.suit = suit;
        this.rank = rank;
        this.number = number;
    }
    
    public Card(Suit suit, int number) {
    	this.suit = suit;
    	this.number = number;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
    
    public int getNumber() {
    	return number;
    }

/*Create 53 cards. 13 of each suit*/
    public List<Card> createCards() {
		List<Card> cardList = new ArrayList();
		for(Suit suit : Suit.values()) {
			int i=0;
			if (suit.equals(Suit.JOKER)) {
				cardList.add(new Card(Suit.JOKER,0));
			}
			else {
				for(Rank rank: Rank.values()) {
				i++;	
				cardList.add(new Card(suit,rank,i));
			}
		}
		}

		Collections.shuffle(cardList);
		cardList = jokerCheck(cardList);
		return cardList;

    }

/*Rearrange if last card is joker card*/
public List<Card> jokerCheck(List<Card> cardList) {
		
		//Retrieve last card
		Card lastCard = cardList.get(cardList.size() - 1);	
		Suit cardSuit = lastCard.getSuit();
		//If last card is joker, re-arrange cards
		if(cardSuit == Suit.JOKER) {
			cardList.remove(52);	
			int pos = randomNumber(0, 53);		
			//Add last card in the middle of the list
			cardList.add(pos, lastCard);			
		} 
		return cardList;
	}

/*Generate Random Number between 1 and 54*/
public static int randomNumber(int min, int max) {
	return (int) ((Math.random() * (max - min)) + min);
}

}
