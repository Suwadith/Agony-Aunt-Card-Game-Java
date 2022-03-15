package model.Penalties;

import model.*;

import java.util.*;

public class MostTrick extends Penalty {
	public String penaltyCode;
	public String counterColor;
	
    public MostTrick(Game game, PenaltyBoard penaltyBoard) {
//        System.out.println(check(game).getPlayerName());

        if(Trick.trickNumber == 13) {
            Player player =check(game);
//            player.getCounters().pop();
            setPenalty();
            counterColor = player.getCounters().pop().getCounterColor().toString();
            char color = counterColor.charAt(0);
//            //Call method to place counter
            super.placeCounter(color, 2, 0, penaltyBoard);
        }

    }

    public Player check(Game game) {
        Player[] players = game.getPlayers();
        List<Player> playerList = new ArrayList<>(Arrays.asList(players));

        //With the use of Comparable this returns the player object with maximum wins (if equal the first one will be returned)
        Player maxPlayer = Collections.max(playerList);

        //get the total wins from player object
        int maxWins = maxPlayer.getTrickRoundsWon();

        //Add all the players with the highest wins in an arraylist
        List<Player> maxPlayers = new ArrayList<>();
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getTrickRoundsWon() == maxWins) {
                maxPlayers.add(playerList.get(i));
            }
        }

        //If there are multiple players with same highest wins
        if(maxPlayers.size() > 1) {

            DumpCard dumpCard = game.getDumpCard();

            //Map with players as keys and list of cards (Matching dump suit) as values
            Map<Player, List<Card>> playerDumpCards = new HashMap<>();
            //Map with players as keys and number of cards (Matching dump suit) as values
            Map<Player, Integer> playerDumpCount = new HashMap<>();


            for(int i=0; i<maxPlayers.size(); i++) {
                List<Card> dumpPlayerCards = new ArrayList<>();
                int count = 0;
                for(int m=0; m<maxPlayers.get(i).getTotalCardsWon().size(); m++) {
                    if(maxPlayers.get(i).getTotalCardsWon().get(m).getSuit() == dumpCard.getSuit()) {
                        dumpPlayerCards.add(maxPlayers.get(i).getTotalCardsWon().get(m));
                        count += 1;
                        playerDumpCount.put(maxPlayers.get(i), count);
                    }
                }
                playerDumpCards.put(maxPlayers.get(i), dumpPlayerCards);
            }

            //Stores the maximum dump suit followed cards won by players
            int maxDumpCards = Collections.max(playerDumpCount.values());

            //List with players who won maximum dump suit followed cards
            List<Player> maxDumpPlayerList = new ArrayList<>();

            for (Map.Entry<Player, List<Card>> entry : playerDumpCards.entrySet()) {
                Player player = entry.getKey();
                List<Card> cards = entry.getValue();

                if(cards.size() == maxDumpCards) {
                    maxDumpPlayerList.add(player);
                }

            }

            //If there are multiple players who won the same highest dump suit followed cards
            if(maxDumpPlayerList.size() > 1) {

                //Map with players as keys and highest ranked dump suit followed card won number as value
                Map<Player, Integer> maxPlayerDump = new HashMap<>();

                for(int i=0; i<maxDumpPlayerList.size(); i++) {
                    List<Integer> dumpNumber = new ArrayList<>();
                    for(int x=0; x<maxDumpPlayerList.get(i).getTotalCardsWon().size(); x++) {
//                        List<Integer> dumpNumber = new ArrayList<>();
                        if(maxDumpPlayerList.get(i).getTotalCardsWon().get(x).getSuit() == dumpCard.getSuit()) {
                            dumpNumber.add(maxDumpPlayerList.get(i).getTotalCardsWon().get(x).getNumber());
                        }
                    }
                    maxPlayerDump.put(maxDumpPlayerList.get(i), Collections.max(dumpNumber));
                }

                //Stores the highest rank card (based on number) won by the user which matches the dump suit
                int maxDumpNumber = Collections.max(maxPlayerDump.values());

                for (Map.Entry<Player, Integer> entry : maxPlayerDump.entrySet()) {
                    Player player = entry.getKey();
                    Integer count = entry.getValue();

                    if(count == maxDumpNumber) {
                        return player;
                    }
                }

            }

            //If there is only 1 player with the highest dump suit followed cards
            return maxDumpPlayerList.get(0);

        }

        //If there is only 1 player with the highest number of trick wins he'll be returned
        return maxPlayer;
    }

    @Override
    public boolean checkForPenalty(Card card) {
        return false;
    }

    @Override
    public boolean checkForPenalty(Card card, DumpCard dumpCard) {
        return false;
    }

	@Override
	public void setPenalty() {
		this.penaltyCode = "MT";
	}

	public String getPenaltyCode() {
		return penaltyCode;
	}

	public String getCounterColor() {
		return counterColor;
	}
}
