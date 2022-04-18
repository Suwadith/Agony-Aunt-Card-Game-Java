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
    static Player[] players = new Player[4];
    //Creating counter array with different colours
    Counter[] counter = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

    //Create deck of cards
    static Deck deck = new Deck();

    //Retrieve dump card
    static Card topCard = deck.getDeck().pop();
    static DumpCard dumpCard = new DumpCard(topCard.getSuit(), topCard.getRank(), topCard.getNumber());
    static String dumpCardImage = new String();

    //Start a game
    static Game game = new Game(players, dumpCard);
    static PenaltyBoard penaltyboard = new PenaltyBoard();


    public static void handleGame() {
        if (game.getRoundNumber() == 1 && Game.newGame) {
            getDumpCardImage();
            assignCards(players, deck);
        }

        if (game.getRoundNumber() > 1 && Trick.isRefresh()) {

            //Create deck of cards
            deck = new Deck();
            assignCards(players, deck);
            //Retrieve dump card
            topCard = deck.getDeck().pop();
            dumpCard = new DumpCard(topCard.getSuit(), topCard.getRank(), topCard.getNumber());
            getDumpCardImage();
            Game.setNewGame(true);
            Trick.setRefresh(false);
            game.setDumpCard(dumpCard);
            //Start a game
            penaltyboard = new PenaltyBoard();
        }

        Trick trick = new Trick();

        if (Trick.trickNumber <= 13) {
            if (Trick.trickNumber == 1) {
                trick.setTrickLeader(players[0]);
                trick.setFollowingPlayers(new Player[]{players[1], players[2], players[3]});
            } else {
                Trick previousTrick = game.getTricks()[Trick.trickNumber - 2];
                trick.setTrickLeader(previousTrick.getPreviousTrickWinner());
                List<Player> playerList = new ArrayList<>(Arrays.asList(players));
                List<Player> reOrderedPlayerList = new ArrayList<>();

                //Order of following players to be in sequence to leading players
                int leadPlayerIndex = playerList.indexOf(trick.getTrickLeader());

                for (int f = leadPlayerIndex + 1; f < playerList.size(); f++) {
                    reOrderedPlayerList.add(playerList.get(f));
                }

                for (int f = 0; f < leadPlayerIndex; f++) {
                    reOrderedPlayerList.add(playerList.get(f));
                }

                Player[] tempList = reOrderedPlayerList.toArray(new Player[0]);
                trick.setFollowingPlayers(tempList);
            }

            /******************PENALTY BOARD *************************/
            if (Trick.trickNumber == 1) {
                new PenaltyBoardFrame(players, dumpCard, dumpCardImage, trick, game, penaltyboard);
            } else {

                /************************** MAIN FRAME ********************/
                MainFrame.turnCount = -1;
                new MainFrame(players, dumpCard, dumpCardImage, trick, game, penaltyboard);
            }
        }
        penaltyboard.displayBoard();

    }

    public void createPlayers(String[] playerNames) {
        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            players[i] = new Player((i + 1), playerNames[i], counter[i]);
            countersAvailable[i] = players[i].getCounters().size();
        }
        handleGame();
    }

    //method - get file name of card
    public static void getDumpCardImage() {
        if (topCard.getNumber() > 1 && topCard.getNumber() <= 10) {
            dumpCardImage = topCard.getNumber() + "_of_" + topCard.getSuit();
        } else {
            dumpCardImage = topCard.getRank() + "_of_" + topCard.getSuit();
        }
    }

    public static void assignCards(Player[] players, Deck deck) {
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


}