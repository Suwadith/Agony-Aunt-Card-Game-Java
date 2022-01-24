import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

import model.Card;
import model.Rank;
import model.Suit;
import model.Deck;

public class main {
	public static void main(String array[])
	{
		//Create List of Cards
		List<Card> cardList = createCards();
	
		//Shuffle the cards
		Collections.shuffle(cardList);
		
		//Joker card Check
		cardList = jokerCheck(cardList);
		
		//Add them to deck		
		Stack<Card> cardStack = addtoDeck(cardList);
		
		//Display dump card
		Card dumpCard = cardStack.peek(); 
		System.out.println("Dump suit is: " + dumpCard.getSuit() + "\nDump number is: " + dumpCard.getNumber());
}

/*Create 53 cards. 13 of each suit*/
		public static List<Card> createCards() {
		
		List<Card> cardList = new ArrayList();
		for(Suit suit : Suit.values()) {
			if (suit.equals(Suit.JOKER)) {
				cardList.add(new Card(Suit.JOKER));
			}
			else {
				for(int i=1;i<14;i++) {
				cardList.add(new Card(suit, i));
			}
			}
		}
		return cardList;
	}

/*Pass List of cards to Stack of Deck*/
	public static Stack<Card> addtoDeck(List<Card> cardList){
	
		Stack<Card> cardStack = new Stack<>();		
		cardStack.addAll(cardList);
		Deck cardDeck = new Deck(cardStack);		
		cardStack = cardDeck.getDeck();
		return cardStack;
	}

/*Generate Random Number between 1 and 54*/
	public static int randomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
/*Rearrange if last card is joker card*/
	public static List<Card> jokerCheck(List<Card> cardList) {
		
		//Retrieve last card
		Card lastCard = cardList.get(cardList.size() - 1);
		cardList.remove(52);		
		Suit cardSuit = lastCard.getSuit();
		//If last card is joker, re-arrange cards
		if(cardSuit == Suit.JOKER) {
			int pos = randomNumber(0, 53);		
			//Add last card in the middle of the list
			cardList.add(pos, lastCard);			
		} 
		return cardList;

	}

}