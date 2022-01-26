import java.util.*;

import model.*;

import static model.CounterColor.*;

public class main {
	public static void main(String array[])
	{
		Scanner sc = new Scanner(System.in);

		//Creating player array
		Player[] players = new Player[4];
		//Creating counter array with different colours
		Counter[] counters = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

		//Creating individual player objects
		for(int i=0; i<4; i++) {
			System.out.println("Enter Player " + (i+1) + " name: ");
			String name = sc.next();
			players[i] = new Player((i+1), name, 17, counters[i]);
		}

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
