import java.util.*;


import model.*;
import model.Penalties.Queen;

import static model.CounterColor.*;
import static model.Suit.*;
import static model.Rank.*;

public class Main {
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

        //Display Penalty Board
        PenaltyBoard penaltyboard = new PenaltyBoard();
        PenaltySquares[][] penaltySquares = penaltyboard.getPenaltyBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.format("%-10s", penaltySquares[i][j].getPenaltySquareName());
            }
            System.out.println();
        }
        System.out.println();

        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter Player " + (i + 1) + " name: ");
            String name = sc.next();
            players[i] = new Player((i + 1), name, 17, counters[i]);
        }

        //Assign 13 cards per player
        int x = 1;
        for (int i = 0; i < 13; i++) {
            players[0].updatePlayingCards(x, deck.getDeck().pop());
            players[1].updatePlayingCards(x, deck.getDeck().pop());
            players[2].updatePlayingCards(x, deck.getDeck().pop());
            players[3].updatePlayingCards(x, deck.getDeck().pop());
            x++;
        }

        //Start a game
        Game game = new Game(players, dumpCard);

        
        //13 tricks in total
        for (int i = 0; i < 13; i++) {
        	//Start a trick
            Trick trick = new Trick();
        	System.out.println();
        	System.out.println( "Start of trick: "+ (i+1));
            //Check if this is the first trick
            if (Trick.trickNumber == 1) {
                trick.setTrickLeader(players[0]);
                trick.setFollowingPlayers(new Player[]{players[1], players[2], players[3]});
            } else {
            	Trick previousTrick = game.getTricks()[i-1]; 
            	trick.setTrickLeader(previousTrick.getPreviousTrickWinner());
                List<Player> playerList = new ArrayList<>(Arrays.asList(players));
                playerList.remove(previousTrick.getPreviousTrickWinner());
                Player[] tempList = playerList.toArray(new Player[0]);
                trick.setFollowingPlayers(tempList);
            }

            //Leading a trick
            System.out.println(trick.getTrickLeader().getPlayerName() + "'s cards are as follows");
            System.out.println("-----------------------------");
            trick.getTrickLeader().getPlayingCards().forEach((key, value) -> {
                if (value.getSuit() == JOKER) {
                    System.out.println(key + " -> " + value.getSuit());
                } else {
                    System.out.println(key + " -> " + value.getRank() + " : " + value.getSuit());
                }
            });
            System.out.println();
            System.out.println(trick.getTrickLeader().getPlayerName() + " picks a card(choose the card by typing a number from the previously displayed list)");
            int cardNumber = Integer.parseInt(sc.next());
            System.out.println();
            if(trick.getTrickLeader().getPlayingCards().get(cardNumber).getSuit() == JOKER) {
                System.out.println("Joker picked");
                trick.setLeadCard(dumpCard);
            } else {
                trick.setLeadCard(trick.getTrickLeader().getPlayingCards().get(cardNumber));
            }
            trick.getTrickLeader().removeCard(cardNumber);
            System.out.println("Card chosen by the leader");
            System.out.println("--------------------------");
            System.out.println(trick.getLeadCard());
           
            //Add leading card to HashMap
            Map<Integer, Card> cardsWon = new HashMap();
            cardsWon.put(i, trick.getLeadCard());

            //Following a trick
            for(int y=0; y<3; y++) {
                System.out.println(trick.getFollowingPlayers()[y].getPlayerName() + "'s cards are as follows");
                System.out.println("-----------------------------");
                if(Card.checkIfFollowingSuitPossible(trick.getFollowingPlayers()[y].getPlayingCards(), trick.getLeadCard().getSuit())) {
                    trick.getFollowingPlayers()[y].getPlayingCards().forEach((key, value) -> {
                        if (value.getSuit() == JOKER && dumpCard.getSuit() == trick.getLeadCard().getSuit()) {
                            System.out.println(key + " -> " + value.getSuit());
                        } else if(value.getSuit() == trick.getLeadCard().getSuit()) {
                            System.out.println(key + " -> " + value.getRank() + " : " + value.getSuit());
                        }
                    });
                } else {
                    trick.getFollowingPlayers()[y].getPlayingCards().forEach((key, value) -> {
                        if (value.getSuit() == JOKER) {
                            System.out.println(key + " -> " + value.getSuit());
                        } else {
                            System.out.println(key + " -> " + value.getRank() + " : " + value.getSuit());
                        }
                    });
                }
                System.out.println();

                System.out.println();
                System.out.println(trick.getFollowingPlayers()[y].getPlayerName() + " picks a card(choose the card by typing a number from the previously displayed list)");
                cardNumber = Integer.parseInt(sc.next());
                System.out.println();

                if(trick.getFollowingPlayers()[y].getPlayingCards().get(cardNumber).getSuit() == JOKER) {
                    System.out.println("Joker picked");
                    trick.setFollowingCards(dumpCard, y);
                } else {
                    trick.setFollowingCards(trick.getFollowingPlayers()[y].getPlayingCards().get(cardNumber), y);
                }

                //trick.setFollowingCards(trick.getFollowingPlayers()[y].getPlayingCards().get(cardNumber), y);

                trick.getFollowingPlayers()[y].removeCard(cardNumber);
                System.out.println("Card chosen by the follower");
                System.out.println("--------------------------");
                System.out.println(trick.getFollowingCards()[y]);
                
                //Add following cards to HashMap
                cardsWon.put((y+1), trick.getFollowingCards()[y]);
            }

/* Determine winner of the trick */
            /*If leading card is Ace*/
            if(trick.getLeadCard().getRank() == Rank.ACE) {
            	System.out.println("Winner of the trick is: " + trick.getTrickLeader().getPlayerName());
            	trick.setWinner(trick.getTrickLeader());
            }
            else {
            	Player tempWinner = trick.getTrickLeader();
            	Card tempLeadCard = trick.getLeadCard();
            	/*Check if all players follow leading card suit*/
            	for(int j=0;j<3;j++) {
            		if(trick.getLeadCard().getSuit() == trick.getFollowingCards()[j].getSuit()) {
            			//Check if the player has ACE and announce winner
            			if(trick.getFollowingCards()[j].getRank() == Rank.ACE ) {
            				tempWinner = trick.getFollowingPlayers()[j];
            				break;
            			}
            			//Compare rank of leading player and following player of the current loop
            			else{
            				if(trick.getFollowingCards()[j].getNumber() > tempLeadCard.getNumber()) {
            					tempWinner = trick.getFollowingPlayers()[j];
            					tempLeadCard = trick.getFollowingCards()[j];
            				}
            			}
            		}
            	}
            	trick.setWinner(tempWinner);
				System.out.println("Winner of the trick is: " + tempWinner.getPlayerName());
            }
            trick.setPreviousTrickWinner(trick.getWinner());
            System.out.println();
            game.setTrick(i, trick);
            
            //Add the cards won by the Player
            trick.getWinner().setCardsWon(cardsWon);

            //Iterate at the cards won to check for penalty
            for(Map.Entry<Integer, Card> entry: trick.getWinner().getCardsWon().entrySet()) {
            	Card cardPenalty = entry.getValue();
            	
            	//new Queen(cardPenalty);
            }
          
        }
    }
}	