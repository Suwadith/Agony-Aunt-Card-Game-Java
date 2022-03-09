package controller;

import model.*;
import view.*;

import java.util.Arrays;
import java.util.Stack;

import static model.CounterColor.*;
import static model.CounterColor.YELLOW;


public class GameController {

    int[] countersAvailable = new int[4];
    //Creating player array
    Player[] players = new Player[4];
    //Creating counter array with different colours
    Counter[] counter = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

    //Create deck of cards
    Deck deck = new Deck();

    //Display dump card
    Card topCard = deck.getDeck().pop();
    DumpCard dumpCard = new DumpCard(topCard.getSuit(), topCard.getRank(), topCard.getNumber());

    public void handleGame() {
        Trick trick = new Trick();
        /**************************MAIN FRAME ********************/
        new MainFrame(players);
    }

    public void createPlayers(String[] playerNames) {
        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            players[i] = new Player((i + 1), playerNames[i], counter[i]);
            countersAvailable[i] = players[i].getCounters().size();
        }
        assignCards(players, deck);
        handleGame();

    }

    public void assignCards(Player[] players, Deck deck) {
        //Assign 13 cards per player
        int x = 1;
        for (int i = 0; i < 13; i++) {
            players[0].updatePlayingCards(x, deck.getDeck().pop());
            players[1].updatePlayingCards(x, deck.getDeck().pop());
            players[2].updatePlayingCards(x, deck.getDeck().pop());
            players[3].updatePlayingCards(x, deck.getDeck().pop());
            x++;
        }
    }



    //Start a game
    Game game = new Game(players, dumpCard);
}