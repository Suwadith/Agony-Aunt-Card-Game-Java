package controller;

import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static model.CounterColor.*;
import static model.CounterColor.YELLOW;
import static model.Suit.JOKER;


public class GameController {

    int[] countersAvailable = new int[4];
    //Creating player array
    Player[] players = new Player[4];
    //Creating counter array with different colours
    Counter[] counter = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

    //Create deck of cards
    Deck deck = new Deck();

    //Retrieve dump card
    Card topCard = deck.getDeck().pop();
    DumpCard dumpCard = new DumpCard(topCard.getSuit(), topCard.getRank(), topCard.getNumber());
    String dumpCardImage = new String();
    
    public void handleGame() {
        Trick trick = new Trick();

        if(Trick.trickNumber<=13) {

            if (Trick.trickNumber == 1) {
                trick.setTrickLeader(players[0]);
                trick.setFollowingPlayers(new Player[]{players[1], players[2], players[3]});
            } else {
                Trick previousTrick = game.getTricks()[Trick.trickNumber-1];
                trick.setTrickLeader(previousTrick.getPreviousTrickWinner());
                List<Player> playerList = new ArrayList<>(Arrays.asList(players));
                List<Player> reOrderedPlayerList = new ArrayList<>();

                //Order of following players to be in sequence to leading players
                int leadPlayerIndex = playerList.indexOf(trick.getTrickLeader());

                for(int f=leadPlayerIndex+1; f<playerList.size(); f++) {
                    reOrderedPlayerList.add(playerList.get(f));
                }

                for(int f=0; f<leadPlayerIndex; f++) {
                    reOrderedPlayerList.add(playerList.get(f));
                }

//                playerList.remove(previousTrick.getPreviousTrickWinner());
                Player[] tempList = reOrderedPlayerList.toArray(new Player[0]);
                trick.setFollowingPlayers(tempList);
            }

            /************************** MAIN FRAME ********************/
            new MainFrame(players, dumpCardImage, trick);

            /******************PENALTY BOARD *************************/
            new PenaltyBoardFrame();
        }


    }

    public void createPlayers(String[] playerNames) {
        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            players[i] = new Player((i + 1), playerNames[i], counter[i]);
            countersAvailable[i] = players[i].getCounters().size();
        }
        assignCards(players, deck);
        getDumpCardImage();
        handleGame();

    }

    //method - get file name of card
    public void getDumpCardImage() {
        if(topCard.getNumber() > 1 && topCard.getNumber() <= 10)
        {
        	dumpCardImage = topCard.getNumber() + "_of_" + topCard.getSuit();
        } else {
        	dumpCardImage = topCard.getRank() + "_of_" + topCard.getSuit();
        }
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