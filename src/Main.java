import java.util.*;


import model.*;
import model.Penalties.*;

import static model.CounterColor.*;
import static model.Suit.*;
import static model.Rank.*;

public class Main {
    public static void main(String array[]) {
        Scanner sc = new Scanner(System.in);

        //Creating player array
        Player[] players = new Player[4];
        //Creating counter array with different colours
        Counter[] counter = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

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
        penaltyboard.displayBoard();

        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter Player " + (i + 1) + " name: ");
            String name = sc.next();
            players[i] = new Player((i + 1), name, counter[i]);
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
        	System.out.println("******************************");
        	System.out.println( "START OF TRICK: "+ (i+1));
        	System.out.println("******************************");
        	System.out.println();
            //Check if this is the first trick
            if (Trick.trickNumber == 1) {
                trick.setTrickLeader(players[0]);
                trick.setFollowingPlayers(new Player[]{players[1], players[2], players[3]});
            } else {
            	Trick previousTrick = game.getTricks()[i-1]; 
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
                trick.getTrickLeader().incrementTrickRoundsWon();
                System.out.println("******************************");
            	System.out.println("Winner of the trick is: " + trick.getTrickLeader().getPlayerName());
            	System.out.println("******************************");
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
                tempWinner.incrementTrickRoundsWon();
            	trick.setWinner(tempWinner);
            	System.out.println("******************************");
				System.out.println("Winner of the trick is: " + tempWinner.getPlayerName());
				System.out.println("******************************");
            }
            trick.setPreviousTrickWinner(trick.getWinner());
            System.out.println();
            game.setTrick(i, trick);
            
            //Add the cards won by the Player
            trick.getWinner().setCardsWon(cardsWon);
            trick.getWinner().updateTotalCardsWon(trick.getLeadCard());
            for(int d=0; d<trick.getFollowingCards().length; d++) {
                trick.getWinner().updateTotalCardsWon(trick.getFollowingCards()[d]);
            }
            System.out.println("******************************");
        	System.out.println("Updated Penalty Board:");
        	System.out.println("******************************");
        	
            /* Iterate at the cards won to check for penalty */
            for(Map.Entry<Integer, Card> entry: cardsWon.entrySet()) {
            	Card cardPenalty = entry.getValue();
            
                //Agony Aunt Penalty
                new AgonyAunt(cardPenalty, dumpCard, trick.getWinner().getCounters(),penaltyboard);	
            	
            	//Agony Uncle Penalty
            	new AgonyUncle(cardPenalty, dumpCard, trick.getWinner().getCounters(), penaltyboard);
           
            	//Queen Penalty
            	new Queen(cardPenalty, trick.getWinner().getCounters(), penaltyboard);

            }

            //Dumpth trick penalty
            new DumpthTrick(dumpCard, Trick.trickNumber, trick.getWinner().getCounters(), penaltyboard);

            //Last trick penalty
            new LastTrick(Trick.trickNumber, trick.getWinner().getCounters(), penaltyboard);

            //Most trick penalty
            new MostTrick(game, penaltyboard);
        	
            /* Display Penalty Board */
            penaltyboard.displayBoard();


            //Score
            if(Trick.trickNumber == 13) {
                System.out.println();
                System.out.println("***********************************************************");
                System.out.println("Additional score/counter removal (End of round (13 tricks))");
                System.out.println("***********************************************************");
                for(int e=0; e<4; e++) {
                    int count =0;
                    //Horizontal penalty check and counter removal
                    for(int f=0; f<3; f++) {
                        count = 0;
                        for(int g=0; g<3; g++) {
                            if(penaltyboard.getPenaltyBoard()[f][g].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) +')')) {
                                count+=1;
                                if(count == 3) {
                                    game.getPlayers()[e].getCounters().pop();
                                    System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters horizontally on the penalty board");
                                }
                            }
                        }
                    }

                    //Vertical penalty check and counter removal
                    for(int f=0; f<3; f++) {
                        count = 0;
                        for(int g=0; g<3; g++) {
                            if(penaltyboard.getPenaltyBoard()[g][f].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) +')')) {
                                count+=1;
                                if(count == 3) {
                                    game.getPlayers()[e].getCounters().pop();
                                    System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters vertically on the penalty board");
                                }
                            }
                        }
                    }

                    count =0;

                    //Diagonal penalty check and counter removal
                    for(int f=0; f<3; f++) {
                        if(penaltyboard.getPenaltyBoard()[f][f].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) +')')) {
                            count+=1;
                            if(count == 3) {
                                game.getPlayers()[e].getCounters().pop();
                                System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters diagonally on the penalty board");
                            }
                        }
                    }

                    count =0;
                    for(int f=0; f<3; f++) {
                        for(int g=0; g<3; g++) {
                            if(f+g == 2) {
                                if (penaltyboard.getPenaltyBoard()[f][g].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) + ')')) {
                                    count += 1;
                                    if (count == 3) {
                                        game.getPlayers()[e].getCounters().pop();
                                        System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters diagonally on the penalty board");
                                    }
                                }
                            }

                        }
                    }

                }

                //Print remaining counters each player has
                System.out.println();
                System.out.println("Players => Remaining Counters");
                System.out.println("-----------------------------");
                for(int f=0; f<4; f++) {
                    System.out.println((f+1) + ". " + game.getPlayers()[f].getPlayerName() + ": " + game.getPlayers()[f].getCounters().size());
                }

            }

        }
    }
}	