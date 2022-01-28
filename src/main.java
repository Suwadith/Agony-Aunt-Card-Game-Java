import java.util.*;

import model.*;

import static model.CounterColor.*;
import static model.Suit.*;
import static model.Rank.*;

public class main {
    public static void main(String array[]) {
        Scanner sc = new Scanner(System.in);

        //Creating player array
        Player[] players = new Player[4];
        //Creating counter array with different colours
        Counter[] counters = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

        //Create deck of cards
        Deck deck = new Deck();

        //Display dump card
        Card topCard = deck.getDeck().pop();
        DumpCard dumpCard = new DumpCard(topCard.getSuit(), topCard.getRank(), topCard.getNumber());
        System.out.println("Dump card details");
        System.out.println("-----------------");
        System.out.println(dumpCard);

        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter Player " + (i + 1) + " name: ");
            String name = sc.next();
            players[i] = new Player((i + 1), name, 17, counters[i]);
        }

        //Assign 13 cards per player
        int x = 1;
        for(int i=0; i<13; i++) {
            players[0].updatePlayingCards(x, deck.getDeck().pop());
            players[1].updatePlayingCards(x, deck.getDeck().pop());
            players[2].updatePlayingCards(x, deck.getDeck().pop());
            players[3].updatePlayingCards(x, deck.getDeck().pop());
            x++;
        }

        //Start a trick
        //player 1 chooses the first card
        System.out.println("Player 1 cards are as follows");
        System.out.println("-----------------------------");
        players[0].getPlayingCards().forEach((key, value) -> {
            System.out.println(key+" -> "+ value.getRank() + " : " + value.getSuit());
        });
//        Trick trick = new Trick()

    }
}	
