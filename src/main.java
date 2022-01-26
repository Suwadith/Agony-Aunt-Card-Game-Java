import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

import model.Card;
import model.Rank;
import model.Suit;
import model.Deck;
import model.DumpCard;

public class main {
	public static void main(String array[])
	{
		//Create List of Cards
		Card card = new Card();
		List<Card> cardList=card.createCards();
			
		//Shuffle the cards
		Collections.shuffle(cardList);
		
		//Joker card check
		cardList = card.jokerCheck(cardList);
		
		//Add them to deck		
		Deck deck = new Deck();
		Stack<Card> cardStack = deck.addtoDeck(cardList);
		
		//Display dump card
		Card topCard = cardStack.peek(); 
		DumpCard dumpCard = new DumpCard(topCard.getSuit(),topCard.getRank(),topCard.getNumber()); 
		System.out.println("Dump suit is: " + dumpCard.getSuit() + "\nDump "
				+ "rank is: " + dumpCard.getRank()+"\nDump Number is: "+dumpCard.getNumber());
}
}	
