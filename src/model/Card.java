package model;

public class Card {

    private Suit suit;
    private Rank rank;
    private int number;

    public Card(Suit suit, Rank rank, int number) {
        this.suit = suit;
        this.rank = rank;
        this.number = number;
    }

    public Card(Suit suit) {
    	this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
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
}


