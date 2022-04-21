package view;

import javax.swing.*;

import controller.GameController;
import model.*;
import model.Penalties.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static model.Suit.JOKER;

public class MainFrame extends JFrame {
    public static JLabel jL, N1, p1N, N2, p2N, N3, p3N, N4, p4N, p1C, p2C, p3C, p4C,
            dumpCardTitle, dCard, leadingCard, trickNumber, currentPlayerName, roundNumber;
    public ArrayList<JLabel> followingCards = new ArrayList<>();
    public static JPanel jP;
    public static Graphics g;
    public static Color c;
    public ArrayList<JButton> playingCards = new ArrayList<>();
    public ArrayList<JButton> unPlayableCards = new ArrayList<>();
    public ArrayList<JButton> cardsWonButton = new ArrayList<>();
    public static int turnCount;
    public static DumpCard dump_Card;
    public static Map<Integer, Card> cardsWon;
    public static ArrayList<Integer> leadCardKeys = new ArrayList<>();
    public static ArrayList<Integer> followingCardKeys = new ArrayList<>();
    public static ArrayList<String> penaltyCode = new ArrayList<>();
    public static String counterColor, mostTrickColor;
    public String additionalPenalty = "";
    public String finalSummary = "";
    public Player tempGameWinner;
    private boolean flag = false;
    private static boolean endGame = false;

    public MainFrame(Player[] players, DumpCard dumpCard, String dumpCardImage, Trick trick, Game game, PenaltyBoard penaltyBoard) {
        setTitle("Play Agony Aunt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        counterColor = ""; mostTrickColor = "";
        if (game.getRoundNumber() > 1 && Game.getNewGame()) {
            penaltyCode.clear();
            cardsWon.clear();
            counterColor = null;
            mostTrickColor = null;
            for (int t = 0; t < 4; t++) {
                game.getPlayers()[t].trickRoundsWon = 0;
                game.getPlayers()[t].totalCardsWon.clear();
                game.getPlayers()[t].setPenaltyIncurred(false);
            }
        }
        //set frame size
        setSize(1300, 550);

        jP = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillOval(1000, 20, 15, 15);
                g.drawOval(1000, 20, 15, 15);

                g.setColor(Color.GREEN);
                g.fillOval(1000, 40, 15, 15);
                g.drawOval(1000, 40, 15, 15);

                g.setColor(Color.BLUE);
                g.fillOval(1000, 60, 15, 15);
                g.drawOval(1000, 60, 15, 15);

                g.setColor(Color.YELLOW);
                g.fillOval(1000, 80, 15, 15);
                g.drawOval(1000, 80, 15, 15);
            }
        };
        jP.setLayout(null);
        jP.setBackground(Color.WHITE);
        dump_Card = dumpCard;
        //display dump card
        dumpCardTitle = new JLabel("Dump Card");
        dumpCardTitle.setBounds(20, 1, 80, 20);
        jP.add(dumpCardTitle);

        String filePath = "/" + dumpCardImage + ".png";
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(filePath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
        dCard = new JLabel((icon));
        dCard.setBounds(8, 1, 100, 160);
        jP.add(dCard);

        //Display round number
        String round_no = "Round:" + String.valueOf(game.getRoundNumber());
        roundNumber = new JLabel(round_no);
        roundNumber.setFont(new Font("Arial", Font.BOLD, 14));
        roundNumber.setForeground(Color.RED);
        roundNumber.setBounds(500,200,80,20);
        jP.add(roundNumber);
        
        //Display trick number
        String trick_no = "Trick:" + String.valueOf(Trick.trickNumber);
        trickNumber = new JLabel(trick_no);
        trickNumber.setFont(new Font("Arial", Font.BOLD, 14));
        trickNumber.setForeground(Color.RED);
        trickNumber.setBounds(500,2, 80, 20);
        jP.add(trickNumber);

        jL = new JLabel("Player information");
        jL.setBounds(900, 1, 200, 15);
        jP.add(jL);

        //Display Player1 name
        N1 = new JLabel("1");
        N1.setBounds(900, 12, 300, 30);
        jP.add(N1);
        p1N = new JLabel(players[0].getPlayerName());
        p1N.setBounds(920, 12, 300, 30);
        jP.add(p1N);

        //Display Player2 name
        N2 = new JLabel("2");
        N2.setBounds(900, 32, 300, 30);
        jP.add(N2);
        p2N = new JLabel(players[1].getPlayerName());
        p2N.setBounds(920, 32, 300, 30);
        jP.add(p2N);

        //Display Player3 name
        N3 = new JLabel("3");
        N3.setBounds(900, 52, 300, 30);
        jP.add(N3);
        p3N = new JLabel(players[2].getPlayerName());
        p3N.setBounds(920, 52, 300, 30);
        jP.add(p3N);

        //Display Player4 name
        N4 = new JLabel("4");
        N4.setBounds(900, 72, 300, 30);
        jP.add(N4);
        p4N = new JLabel(players[3].getPlayerName());
        p4N.setBounds(920, 72, 300, 30);
        jP.add(p4N);

        //Display Available counters for player1
        p1C = new JLabel("");
        p1C.setBounds(1020, 16, 20, 20);
        jP.add(p1C);
        p1C.setText(String.valueOf(players[0].getCounters().size()));

        //Display Available counters for player2
        p2C = new JLabel("");
        p2C.setBounds(1020, 37, 20, 20);
        jP.add(p2C);
        p2C.setText(String.valueOf(players[1].getCounters().size()));

        //Display Available counters for player3
        p3C = new JLabel("");
        p3C.setBounds(1020, 58, 20, 20);
        jP.add(p3C);
        p3C.setText(String.valueOf(players[2].getCounters().size()));

        //Display Available counters for player1
        p4C = new JLabel("");
        p4C.setBounds(1020, 78, 20, 20);
        jP.add(p4C);
        p4C.setText(String.valueOf(players[3].getCounters().size()));

        //Button for viewing cards won
        for (int i = 0; i < 4; i++) {
            cardsWonButton.add(new JButton("Cards Won"));
        }

        int q = 18;
        for (int i = 0; i < cardsWonButton.size(); i++) {
            cardsWonButton.get(i).setBounds(1040, q, 100, 18);
            q += 20;
        }

        //Adding cards won button against every player name
        for (JButton j : cardsWonButton) {
            jP.add(j);
        }

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            //Cards won button click operation
            cardsWonButton.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CardsWonFrame(game.getPlayers()[finalI].getTotalCardsWon());
                }
            });
        }

        //Display counters
        jP.repaint();

        //calls the method to load the leader cards
        setupCardImages(trick, game, penaltyBoard);

        add(jP);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setVisible(true);
    }


    //get card image path
    public String returnCardImgPath(Card card) {
        if (card.getSuit() == JOKER) {
            return "/" + card.getSuit() + ".png";
        } else {
            if (card.getNumber() > 1 && card.getNumber() <= 10) {
                return "/" + card.getNumber() + "_of_" + card.getSuit() + ".png";
            } else {
                return "/" + card.getRank() + "_of_" + card.getSuit() + ".png";
            }
        }
    }


    //load images cards
    public void setupCardImages(Trick trick, Game game, PenaltyBoard penaltyBoard) {

        if (turnCount == -1) {

            displayCurrentPLayerName(trick.getTrickLeader().getPlayerName());

            //Leading a trick
            System.out.println(trick.getTrickLeader().getPlayerName() + "'s cards are as follows");
            System.out.println("-----------------------------");
            trick.getTrickLeader().getPlayingCards().forEach((key, value) -> {
                String imgPath = returnCardImgPath(value);
                ImageIcon iconImg = new ImageIcon(new ImageIcon(getClass().getResource(imgPath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                playingCards.add(new JButton(iconImg));
                leadCardKeys.add(key);
            });

            //following players
        } else if (turnCount > -1 && turnCount < 3) {
            jP.remove(currentPlayerName);
            displayCurrentPLayerName(trick.getFollowingPlayers()[turnCount].getPlayerName());

            if (Card.checkIfFollowingSuitPossible(trick.getFollowingPlayers()[turnCount].getPlayingCards(), trick.getLeadCard().getSuit())) {
                trick.getFollowingPlayers()[turnCount].getPlayingCards().forEach((key, value) -> {
                    if (value.getSuit() == JOKER && game.getDumpCard().getSuit() == trick.getLeadCard().getSuit()) {
                        String imgPath = returnCardImgPath(value);
                        ImageIcon iconImg = new ImageIcon(new ImageIcon(getClass().getResource(imgPath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                        playingCards.add(new JButton(iconImg));
                        followingCardKeys.add(key);
                    } else if (value.getSuit() == trick.getLeadCard().getSuit()) {
                        String imgPath = returnCardImgPath(value);
                        ImageIcon iconImg = new ImageIcon(new ImageIcon(getClass().getResource(imgPath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                        playingCards.add(new JButton(iconImg));
                        followingCardKeys.add(key);
                    } else {
                        String imgPath = returnCardImgPath(value);
                        ImageIcon iconImg = new ImageIcon(new ImageIcon(getClass().getResource(imgPath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                        unPlayableCards.add(new JButton(iconImg));
                    }
                });
            } else {
                trick.getFollowingPlayers()[turnCount].getPlayingCards().forEach((key, value) -> {
                    if (value.getSuit() == JOKER) {
                        String imgPath = returnCardImgPath(value);
                        ImageIcon iconImg = new ImageIcon(new ImageIcon(getClass().getResource(imgPath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                        playingCards.add(new JButton(iconImg));
                        followingCardKeys.add(key);
                    } else {
                        String imgPath = returnCardImgPath(value);
                        ImageIcon iconImg = new ImageIcon(new ImageIcon(getClass().getResource(imgPath)).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                        playingCards.add(new JButton(iconImg));
                        followingCardKeys.add(key);
                    }
                });

            }
        }

        int x = 50;
        for (int i = 0; i < playingCards.size(); i++) {
            playingCards.get(i).setBounds(x, 300, 80, 110);
            x += 85;
        }

        for (int i = 0; i < unPlayableCards.size(); i++) {
            unPlayableCards.get(i).setBounds(x, 300, 80, 110);
            x += 85;
        }

        for (JButton j : playingCards) {
            jP.add(j);
        }

        for (JButton j : unPlayableCards) {
            j.setEnabled(false);
            jP.add(j);
        }

        setUpCardChoiceListeners(trick, game, penaltyBoard);

    }

    //setup button click listeners for leader cards and handle card pick
    public void setUpCardChoiceListeners(Trick trick, Game game, PenaltyBoard penaltyBoard) {
        ActionListener cardListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                int playerID;
                if (turnCount == -1) {
                    System.out.println(trick.getTrickLeader().getPlayerName());
                    System.out.println(trick.getTrickLeader().getPlayingCards().get(leadCardKeys.get(playingCards.indexOf(o))));

                    if (trick.getTrickLeader().getPlayingCards().get(leadCardKeys.get(playingCards.indexOf(o))).getSuit() == JOKER) {
                        System.out.println("Joker picked");
                        trick.setLeadCard(game.getDumpCard());
                    } else {
                        trick.setLeadCard(trick.getTrickLeader().getPlayingCards().get(leadCardKeys.get(playingCards.indexOf(o))));
                    }
                    trick.getTrickLeader().removeCard(leadCardKeys.get(playingCards.indexOf(o)));
                    System.out.println("Card chosen by the leader");
                    System.out.println("--------------------------");
                    System.out.println(trick.getLeadCard());

                    //Add leading card to HashMap
                    cardsWon = new HashMap();
                    cardsWon.put(0, trick.getLeadCard());
                } else if (turnCount > -1 && turnCount < 3) {
                    System.out.println(turnCount);
                    System.out.println(trick.getFollowingPlayers()[turnCount].getPlayingCards().get(followingCardKeys.get(playingCards.indexOf(o))));
                    System.out.println("picked number: " + followingCardKeys.get(playingCards.indexOf(o)));
                    if (trick.getFollowingPlayers()[turnCount].getPlayingCards().get(followingCardKeys.get(playingCards.indexOf(o))).getSuit() == JOKER) {
                        System.out.println("Joker picked");
                        trick.setFollowingCards(game.getDumpCard(), turnCount);
                    } else {
                        trick.setFollowingCards(trick.getFollowingPlayers()[turnCount].getPlayingCards().get(followingCardKeys.get(playingCards.indexOf(o))), turnCount);
                        System.out.println(trick.getFollowingPlayers()[turnCount].getPlayingCards().get(followingCardKeys.get(playingCards.indexOf(o))));
                    }
                    trick.getFollowingPlayers()[turnCount].removeCard(followingCardKeys.get(playingCards.indexOf(o)));
                    System.out.println("Card chosen by the follower");
                    System.out.println("--------------------------");
                    System.out.println(trick.getFollowingCards()[turnCount]);

                    //Display following cards to all players on GUI
                    String followingcard = returnCardImgPath(trick.getFollowingCards()[turnCount]);
                    ImageIcon icon = new ImageIcon(new ImageIcon(followingcard).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                    followingCards.add(new JLabel((icon)));

                    //Add following cards to HashMap
                    cardsWon.put((turnCount + 1), trick.getFollowingCards()[turnCount]);
                }

                //Display leading card to all players  on GUI
                String leadingcard = returnCardImgPath(trick.getLeadCard());
                ImageIcon icon = new ImageIcon(new ImageIcon(leadingcard).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
                leadingCard = new JLabel((icon));
                leadingCard.setBounds(300, 20, 80, 110);
                jP.add(leadingCard);

                int y = 385;
                for (int i = 0; i < followingCards.size(); i++) {
                    followingCards.get(i).setBounds(y, 20, 80, 110);
                    y += 85;
                }

                for (JLabel cards : followingCards) {
                    jP.add(cards);
                }

                turnCount += 1;
                for (JButton j : playingCards) {
                    jP.remove(j);
                }

                for (JButton j : unPlayableCards) {
                    jP.remove(j);
                }

                playingCards.clear();
                unPlayableCards.clear();
                leadCardKeys.clear();
                followingCardKeys.clear();
                repaint();

                if (turnCount == 3) {
                    if (trick.getLeadCard().getRank() == Rank.ACE) {
                        trick.getTrickLeader().incrementTrickRoundsWon();
                        System.out.println("******************************");
                        System.out.println("Winner of the trick is: " + trick.getTrickLeader().getPlayerName());
                        System.out.println("******************************");
                        trick.setWinner(trick.getTrickLeader());
                    } else {
                        Player tempWinner = trick.getTrickLeader();
                        Card tempLeadCard = trick.getLeadCard();
                        /*Check if all players follow leading card suit*/
                        for (int j = 0; j < 3; j++) {
                            if (trick.getLeadCard().getSuit() == trick.getFollowingCards()[j].getSuit()) {
                                //Check if the player has ACE and announce winner
                                if (trick.getFollowingCards()[j].getRank() == Rank.ACE) {
                                    tempWinner = trick.getFollowingPlayers()[j];
                                    break;
                                }
                                //Compare rank of leading player and following player of the current loop
                                else {
                                    if (trick.getFollowingCards()[j].getNumber() > tempLeadCard.getNumber()) {
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

                    //Add the cards won by the Player
                    trick.getWinner().setCardsWon(cardsWon);
                    trick.getWinner().updateTotalCardsWon(trick.getLeadCard());
                    for (int d = 0; d < trick.getFollowingCards().length; d++) {
                        trick.getWinner().updateTotalCardsWon(trick.getFollowingCards()[d]);
                    }


                    /* Iterate at the cards won to check for penalty */
                    for (Map.Entry<Integer, Card> entry : cardsWon.entrySet()) {
                        Card cardPenalty = entry.getValue();

                        //Agony Aunt Penalty
                        if (!penaltyCode.contains("AG")) {
                            AgonyAunt agonyAunt = new AgonyAunt(cardPenalty, dump_Card, trick.getWinner().getCounters(), penaltyBoard);
                            penaltyCode.add(agonyAunt.getPenaltyCode());
                            if (agonyAunt.penaltyPresent) {
                                trick.getWinner().setPenaltyIncurred(true);
                            }
                            if (counterColor == null || counterColor.isEmpty()) {
                                counterColor = agonyAunt.getCounterColor();
                            }
                        }

                        //Agony Uncle Penalty
                        if (!penaltyCode.contains("AU")) {
                            AgonyUncle agonyUncle = new AgonyUncle(cardPenalty, dump_Card, trick.getWinner().getCounters(), penaltyBoard);
                            penaltyCode.add(agonyUncle.getPenaltyCode());
                            if (agonyUncle.penaltyPresent) {
                                trick.getWinner().setPenaltyIncurred(true);
                            }
                            if (counterColor == null || counterColor.isEmpty()) {
                                counterColor = agonyUncle.getCounterColor();
                            }
                        }

                        //Queen Penalty
                        if (!penaltyCode.contains("QP_DIAMONDS") && cardPenalty.getSuit() == Suit.DIAMONDS) {
                            Queen queenPenalty = new Queen(cardPenalty, trick.getWinner().getCounters(), penaltyBoard);
                            penaltyCode.add(queenPenalty.getPenaltyCode());
                            if (queenPenalty.penaltyPresent) {
                                trick.getWinner().setPenaltyIncurred(true);
                            }
                            if (counterColor == null || counterColor.isEmpty()) {
                                counterColor = queenPenalty.getCounterColor();
                            }
                        }

                        if (!penaltyCode.contains("QP_CLUBS") && cardPenalty.getSuit() == Suit.CLUBS) {
                            Queen queenPenalty = new Queen(cardPenalty, trick.getWinner().getCounters(), penaltyBoard);
                            penaltyCode.add(queenPenalty.getPenaltyCode());
                            if (queenPenalty.penaltyPresent) {
                                trick.getWinner().setPenaltyIncurred(true);
                            }
                            if (counterColor == null || counterColor.isEmpty()) {
                                counterColor = queenPenalty.getCounterColor();
                            }
                        }

                        if (!penaltyCode.contains("QP_SPADES") && cardPenalty.getSuit() == Suit.SPADES) {
                            Queen queenPenalty = new Queen(cardPenalty, trick.getWinner().getCounters(), penaltyBoard);
                            penaltyCode.add(queenPenalty.getPenaltyCode());
                            if (queenPenalty.penaltyPresent) {
                                trick.getWinner().setPenaltyIncurred(true);
                            }
                            if (counterColor == null || counterColor.isEmpty()) {
                                counterColor = queenPenalty.getCounterColor();
                            }
                        }

                        if (!penaltyCode.contains("QP_HEARTS") && cardPenalty.getSuit() == Suit.HEARTS) {
                            Queen queenPenalty = new Queen(cardPenalty, trick.getWinner().getCounters(), penaltyBoard);
                            penaltyCode.add(queenPenalty.getPenaltyCode());
                            if (queenPenalty.penaltyPresent) {
                                trick.getWinner().setPenaltyIncurred(true);
                            }
                            if (counterColor == null || counterColor.isEmpty()) {
                                counterColor = queenPenalty.getCounterColor();
                            }
                        }

                        //check if any player lost all token, announce winner and end game
                        if (game.getPlayers()[0].getCounters().size() == 0 ||
                                game.getPlayers()[1].getCounters().size() == 0 ||
                                game.getPlayers()[2].getCounters().size() == 0 ||
                                game.getPlayers()[3].getCounters().size() == 0) {
                            //Announce winner of the game and end game
                            //check which player has highest token
                            for (int t = 0; t < 4; t++) {
                                if (t == 0) {
                                    tempGameWinner = game.getPlayers()[t];
                                } else {
                                    if (game.getPlayers()[t].getCounters().size() > tempGameWinner.getCounters().size()) {
                                        tempGameWinner = game.getPlayers()[t];
                                    }
                                }
                            }
                            //Announce winner
                            if(!endGame) { 
                            JFrame gameWinnerFrame = new JFrame();
                            UIManager.put("OptionPane.background", Color.WHITE);
                            UIManager.put("Panel.background", Color.WHITE);
                            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
                            UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
                            JOptionPane.showMessageDialog(gameWinnerFrame,
                                    "Congratulations!!! Winner of the game: " + tempGameWinner.getPlayerName());
                            endGame = true;
                            //End game
                            JFrame gameOverFrame = new JFrame();
                            UIManager.put("OptionPane.background", Color.BLACK);
                            UIManager.put("Panel.background", Color.BLACK);
                            UIManager.put("OptionPane.errorDialog.titlePane.foreground", Color.BLACK);
                            UIManager.put("OptionPane.errorDialog.titlePane.background", Color.BLACK);
                            UIManager.put("OptionPane.errorDialog.border.background", Color.BLACK);
                            UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.BOLD, 30));
                            UIManager.put("OptionPane.messageForeground", Color.WHITE);
                            JOptionPane.showMessageDialog(gameOverFrame,
                                    "!!!!! GAME OVER !!!!!");
                            System.exit(0);}
                        }
                    }

                    if (!endGame) {
                        //Dumpth trick penalty
                        DumpthTrick dumpthTrick = new DumpthTrick(dump_Card, Trick.trickNumber, trick.getWinner().getCounters(), penaltyBoard);
                        penaltyCode.add(dumpthTrick.getPenaltyCode());
                        if (dumpthTrick.penaltyPresent) {
                            trick.getWinner().setPenaltyIncurred(true);
                        }
                        if (counterColor == null || counterColor.isEmpty()) {
                            counterColor = dumpthTrick.getCounterColor();
                        }

                        //Last trick penalty
                        LastTrick lastTrick = new LastTrick(Trick.trickNumber, trick.getWinner().getCounters(), penaltyBoard);
                        penaltyCode.add(lastTrick.getPenaltyCode());
                        if (lastTrick.penaltyPresent) {
                            trick.getWinner().setPenaltyIncurred(true);
                        }
                        if (counterColor == null || counterColor.isEmpty()) {
                            counterColor = lastTrick.getCounterColor();
                        }

                        //Most trick penalty
                        if(Trick.trickNumber == 13) {
                        MostTrick mostTrick = new MostTrick(game, penaltyBoard);
                        penaltyCode.add(mostTrick.getPenaltyCode());
                        if (mostTrickColor == null || mostTrickColor.isEmpty()) {
                        	mostTrickColor = mostTrick.getCounterColor();
                        } }

                        game.setTrick(Trick.trickNumber - 1, trick);


                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame,
                                "Winner of the trick: " + trick.getWinner().getPlayerName());

                        //check if any player lost all token, announce winner and end game
                        if (game.getPlayers()[0].getCounters().size() == 0 ||
                                game.getPlayers()[1].getCounters().size() == 0 ||
                                game.getPlayers()[2].getCounters().size() == 0 ||
                                game.getPlayers()[3].getCounters().size() == 0) {
                            //Announce winner of the game and end game
                            //check which player has highest token
                            for (int t = 0; t < 4; t++) {
                                if (t == 0) {
                                    tempGameWinner = game.getPlayers()[t];
                                } else {
                                    if (game.getPlayers()[t].getCounters().size() > tempGameWinner.getCounters().size()) {
                                        tempGameWinner = game.getPlayers()[t];
                                    }
                                }
                            }
                            //Announce winner
                            if(!endGame) {
                            JFrame gameWinnerFrame = new JFrame();
                            UIManager.put("OptionPane.background", Color.WHITE);
                            UIManager.put("Panel.background", Color.WHITE);
                            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
                            UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
                            JOptionPane.showMessageDialog(gameWinnerFrame,
                                    "Congratulations!!! Winner of the game: " + tempGameWinner.getPlayerName());
                            endGame = true; 
                            //End game
                            JFrame gameOverFrame = new JFrame();
                            UIManager.put("OptionPane.background", Color.BLACK);
                            UIManager.put("Panel.background", Color.BLACK);
                            UIManager.put("OptionPane.errorDialog.titlePane.foreground", Color.BLACK);
                            UIManager.put("OptionPane.errorDialog.titlePane.background", Color.BLACK);
                            UIManager.put("OptionPane.errorDialog.border.background", Color.BLACK);
                            UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.BOLD, 30));
                            UIManager.put("OptionPane.messageForeground", Color.WHITE);
                            JOptionPane.showMessageDialog(gameOverFrame,
                                    "!!!!! GAME OVER !!!!!");
                            System.exit(0);}
                        }
                    }

                    //Determine the ID of player won
                    playerID = trick.getWinner().getPlayerNumber();

                    //Score
                    if (!endGame) {
                        if (Trick.trickNumber == 13) {
                            System.out.println();
                            System.out.println("***********************************************************");
                            System.out.println("Additional score/counter removal (End of round (13 tricks))");
                            System.out.println("***********************************************************");
                            for (int e = 0; e < 4; e++) {
                                int count = 0;
                                //Horizontal penalty check and counter removal
                                for (int f = 0; f < 3; f++) {
                                    count = 0;
                                    for (int g = 0; g < 3; g++) {
                                        if (penaltyBoard.getPenaltyBoard()[f][g].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) + ')')) {
                                            count += 1;
                                            if (count == 3) {
                                                game.getPlayers()[e].getCounters().pop();
                                                System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters horizontally on the penalty board");
                                                additionalPenalty += game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters horizontally on the penalty board \n";
                                            }
                                        }
                                    }
                                }

                                //Vertical penalty check and counter removal
                                for (int f = 0; f < 3; f++) {
                                    count = 0;
                                    for (int g = 0; g < 3; g++) {
                                    	if(!game.getPlayers()[e].getCounters().isEmpty()) {
                                        if (penaltyBoard.getPenaltyBoard()[g][f].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) + ')')) {
                                            count += 1;
                                            if (count == 3) {
                                                game.getPlayers()[e].getCounters().pop();
                                                System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters vertically on the penalty board");
                                                additionalPenalty += game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters vertically on the penalty board \n";
                                            }
                                        } }
                                    }
                                }

                                count = 0;

                                //Diagonal penalty check and counter removal
                                for (int f = 0; f < 3; f++) {
                                	if(!game.getPlayers()[e].getCounters().isEmpty()) {
                                    if (penaltyBoard.getPenaltyBoard()[f][f].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) + ')')) {
                                        count += 1;
                                        if (count == 3) {
                                            game.getPlayers()[e].getCounters().pop();
                                            System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters diagonally on the penalty board");
                                            additionalPenalty += game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters diagonally on the penalty board \n";
                                        }
                                    } }
                                }

                                count = 0;
                                for (int f = 0; f < 3; f++) {
                                    for (int g = 0; g < 3; g++) {
                                        if (f + g == 2) {
                                        	if(!game.getPlayers()[e].getCounters().isEmpty()) {
                                            if (penaltyBoard.getPenaltyBoard()[f][g].getPenaltySquareName().endsWith("(" + game.getPlayers()[e].getCounters().peek().getCounterColor().toString().charAt(0) + ')')) {
                                                count += 1;
                                                if (count == 3) {
                                                    game.getPlayers()[e].getCounters().pop();
                                                    System.out.println(game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters in a row on the penalty board");
                                                    additionalPenalty += game.getPlayers()[e].getPlayerName() + " looses 1 counter for having 3 counters in a row on the penalty board \n";
                                                }
                                            } }
                                        }

                                    }
                                }

                            }
                            //Print remaining counters of each player
                            System.out.println();
                            System.out.println("Players => Remaining Counters");
                            System.out.println("-----------------------------");
                            for (int f = 0; f < 4; f++) {
                                System.out.println((f + 1) + ". " + game.getPlayers()[f].getPlayerName() + ": " + game.getPlayers()[f].getCounters().size());
                                finalSummary += (f + 1) + ". " + game.getPlayers()[f].getPlayerName() + ": " + game.getPlayers()[f].getCounters().size() + "\n";
                            }

                            JFrame jjFrame = new JFrame();
                            JOptionPane.showMessageDialog(jjFrame,
                                    "End of the round\n" +
                                            "================\n\n" +
                                            "Additional Penalties\n" +
                                            "====================\n" +
                                            additionalPenalty + "\n" +
                                            "Final Counter Summary\n" +
                                            "=====================\n" +
                                            finalSummary);

                            /*********** Calculate special scores **************/

                            //If no player lost all counters
                            if (game.getPlayers()[0].getCounters().size() != 0 &&
                                    game.getPlayers()[1].getCounters().size() != 0 &&
                                    game.getPlayers()[2].getCounters().size() != 0 &&
                                    game.getPlayers()[3].getCounters().size() != 0) {

                                //if player won most trick, return counters
                                for (int k = 0; k < 4; k++) {
                                    if (game.getPlayers()[k].getTrickRoundsWon() == 13) {
                                        int counter_left = 17 - game.getPlayers()[k].getCounters().size();
                                        for (int r = 0; r < counter_left; r++) {
                                            game.getPlayers()[k].setCounter(game.getPlayers()[k].getCounter());
                                        }
                                        flag = true;
                                    }
                                }

                                //if player lost all trick, return counters
                                if (!flag) {
                                    for (int k = 0; k < 4; k++) {
                                        if (game.getPlayers()[k].getTrickRoundsWon() == 0) {
                                            int counter_left = 17 - game.getPlayers()[k].getCounters().size();
                                            for (int r = 0; r < counter_left; r++) {
                                                game.getPlayers()[k].setCounter(game.getPlayers()[k].getCounter());
                                            }
                                        }
                                    }
                                }

                                //If player won at least one trick and lost no counter
                                for (int k = 0; k < 4; k++) {
                                    if ((game.getPlayers()[k].getCounters().size() < 17) &&
                                            (game.getPlayers()[k].getTrickRoundsWon() >= 1) &&
                                            (!game.getPlayers()[k].isPenaltyIncurred())) {
                                        int lost_counter = 17 - game.getPlayers()[k].getCounters().size();
                                        if (lost_counter % 2 == 0) { //even
                                            int counter_add = lost_counter / 2;
                                            for (int r = 0; r < counter_add; r++) {
                                                game.getPlayers()[k].setCounter(game.getPlayers()[k].getCounter());
                                            }
                                        } else { //odd
                                            int counter_add = (lost_counter / 2) - 1;
                                            for (int r = 0; r < counter_add; r++) {
                                                game.getPlayers()[k].setCounter(game.getPlayers()[k].getCounter());
                                            }
                                        }
                                    }
                                }
                                //Restart game if needed
                                game.setRoundNumber((game.getRoundNumber() + 1));
                                Trick.setTrickNumber(0);
                                Trick.setRefresh(true);
                            } else {
                                //Announce winner of the game and end game
                                //check which player has highest token
                                for (int t = 0; t < 4; t++) {
                                    if (t == 0) {
                                        tempGameWinner = game.getPlayers()[t];
                                    } else {
                                        if (game.getPlayers()[t].getCounters().size() > tempGameWinner.getCounters().size()) {
                                            tempGameWinner = game.getPlayers()[t];
                                        }
                                    }
                                }
                                //Announce winner
                                if(!endGame) {
                                JFrame gameWinnerFrame = new JFrame();
                                UIManager.put("OptionPane.background", Color.WHITE);
                                UIManager.put("Panel.background", Color.WHITE);
                                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
                                UIManager.put("OptionPane.messageForeground", Color.DARK_GRAY);
                                JOptionPane.showMessageDialog(gameWinnerFrame,
                                        "Congratulations!!! Winner of the game: " + tempGameWinner.getPlayerName());
                                endGame = true;
                                //End game
                                JFrame gameOverFrame = new JFrame();
                                UIManager.put("OptionPane.background", Color.BLACK);
                                UIManager.put("Panel.background", Color.BLACK);
                                UIManager.put("OptionPane.errorDialog.titlePane.foreground", Color.BLACK);
                                UIManager.put("OptionPane.errorDialog.titlePane.background", Color.BLACK);
                                UIManager.put("OptionPane.errorDialog.border.background", Color.BLACK);
                                UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.BOLD, 30));
                                UIManager.put("OptionPane.messageForeground", Color.WHITE);
                                JOptionPane.showMessageDialog(gameOverFrame,
                                        "!!!!! GAME OVER !!!!!");
                                System.exit(0);}
                            }
                        }
                    }

                    dispose();
                    if (!endGame) {
                        if (Trick.getTrickNumber() != 0) {
                            Game.setNewGame(false);
                        }
                        //Add counter to board
                        new PenaltyBoardFrame(playerID, penaltyCode, counterColor, mostTrickColor);
                    }
                } else {
                    setupCardImages(trick, game, penaltyBoard);
                }

            }
        };

        for (JButton j : playingCards) {
            j.addActionListener(cardListener);
        }
    }

    public void displayCurrentPLayerName(String playerName) {
        //Display trick number
        String player_name = "Current Player: " + playerName;
        currentPlayerName = new JLabel(player_name);
        currentPlayerName.setBounds(50, 250, 500, 20);
        jP.add(currentPlayerName);
    }

}
