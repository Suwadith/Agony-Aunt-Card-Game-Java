import java.util.*;

import model.*;

import static model.CounterColor.*;

public class main {
    public static void main(String array[]) {
        Scanner sc = new Scanner(System.in);

        //Creating player array
        Player[] players = new Player[4];
        //Creating counter array with different colours
        Counter[] counters = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

        //Create deck of cards
        Deck deck = new Deck();


        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter Player " + (i + 1) + " name: ");
            String name = sc.next();
            players[i] = new Player((i + 1), name, 17, counters[i]);
        }


        //Display dump card
        Card topCard = deck.getDeck().pop();
        DumpCard dumpCard = new DumpCard(topCard.getSuit(), topCard.getRank(), topCard.getNumber());
        System.out.println("Dump suit is: " + dumpCard.getSuit() + "\nDump "
                + "rank is: " + dumpCard.getRank() + "\nDump Number is: " + dumpCard.getNumber());


		for(int i=0; i<deck.getDeck().size(); i++) {
			System.out.println(deck.getDeck().pop().getSuit());
		}

    }
}	
