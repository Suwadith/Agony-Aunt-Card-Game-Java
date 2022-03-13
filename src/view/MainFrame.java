package view;

import javax.swing.*;
import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static model.Suit.JOKER;

public class MainFrame extends JFrame{
	//	DrawCircle circleCounter = new DrawCircle();
	public static JLabel jL, N1, p1N, N2, p2N, N3, p3N, N4, p4N, p1C, p2C, p3C, p4C, dumpCardTitle, dumpCard, tmpCard;
	public static JPanel jP, mainPanel, subPanel;
	public static Graphics g;
	public static Color c;
	//	public ArrayList<JLabel> playingCards = new ArrayList<>();
	public ArrayList<JButton> playingCards = new ArrayList<>();
	public static int turnCount = 0;

	public MainFrame(Player[] players, String dumpCardImage, Trick trick, Game game) {
		setTitle("Play Agony Aunt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);

		//set frame size
		setSize(1300,550);

		jP = new JPanel() {
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.fillOval(1200,20,15,15);
				g.drawOval(1200,20,15,15);

				g.setColor(Color.YELLOW);
				g.fillOval(1200,40,15,15);
				g.drawOval(1200,40,15,15);

				g.setColor(Color.GREEN);
				g.fillOval(1200,60,15,15);
				g.drawOval(1200,60,15,15);

				g.setColor(Color.BLUE);
				g.fillOval(1200,80,15,15);
				g.drawOval(1200,80,15,15); }
		};
		jP.setLayout(null);

		//display dump card
		dumpCardTitle = new JLabel("Dump Card");
		dumpCardTitle.setBounds(20,1,80,20);
		jP.add(dumpCardTitle);

		String filePath = "src\\view\\Cards\\" + dumpCardImage + ".png";
		ImageIcon icon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(80,110, Image.SCALE_SMOOTH));
		dumpCard = new JLabel((icon));
//		dumpCard.setIcon(icon);
		dumpCard.setBounds(8,1,100,160);
		jP.add(dumpCard);

		jL = new JLabel("Player information");
		jL.setBounds(1100,1,200,15);
		jP.add(jL);

		//Display Player1 name	
		N1 = new JLabel("1");
		N1.setBounds(1100,12,300,30);
		jP.add(N1);
		p1N = new JLabel(players[0].getPlayerName());
		p1N.setBounds(1120,12,300,30);
		jP.add(p1N);

		//Display Player2 name
		N2 = new JLabel("2");
		N2.setBounds(1100,32,300,30);
		jP.add(N2);
		p2N = new JLabel(players[1].getPlayerName());
		p2N.setBounds(1120,32,300,30);
		jP.add(p2N);

		//Display Player3 name
		N3 = new JLabel("3");
		N3.setBounds(1100,52,300,30);
		jP.add(N3);
		p3N = new JLabel(players[2].getPlayerName());
		p3N.setBounds(1120,52,300,30);
		jP.add(p3N);

		//Display Player4 name
		N4 = new JLabel("4");
		N4.setBounds(1100,72,300,30);
		jP.add(N4);
		p4N = new JLabel(players[3].getPlayerName());
		p4N.setBounds(1120,72,300,30);
		jP.add(p4N);

		//Display Available counters for player1
		p1C = new JLabel("");
		p1C.setBounds(1220,16,20,20);
		jP.add(p1C);
		p1C.setText(String.valueOf(players[0].getCounters().size()));

		//Display Available counters for player2
		p2C = new JLabel("");
		p2C.setBounds(1220,37,20,20);
		jP.add(p2C);
		p2C.setText(String.valueOf(players[1].getCounters().size()));

		//Display Available counters for player3
		p3C = new JLabel("");
		p3C.setBounds(1220,58,20,20);
		jP.add(p3C);
		p3C.setText(String.valueOf(players[2].getCounters().size()));

		//Display Available counters for player1
		p4C = new JLabel("");
		p4C.setBounds(1220,78,20,20);
		jP.add(p4C);
		p4C.setText(String.valueOf(players[3].getCounters().size()));

		//Display counters
		jP.repaint();

		//calls the method to load the leader cards
		setupLeaderCards(trick, game);


		add(jP);
		setVisible(true);
	}


	//get card image path
	public String returnCardImgPath(Card card) {
		if (card.getSuit() == JOKER) {
			return "src\\view\\Cards\\" + card.getSuit() + ".png";
		} else {
			if(card.getNumber() > 1 && card.getNumber() <= 10)
			{
				return "src\\view\\Cards\\" + card.getNumber() + "_of_" + card.getSuit() + ".png";
			} else {
				return "src\\view\\Cards\\" + card.getRank() + "_of_" + card.getSuit() + ".png";
			}
		}
	}

	//setup button click listeners for leader cards and handle card pick
	public void setUpCardChoiceListeners(Trick trick, Game game) {
		ActionListener cardListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				System.out.println(trick.getTrickLeader().getPlayingCards().get(playingCards.indexOf(o) + 1));

				if(trick.getTrickLeader().getPlayingCards().get(playingCards.indexOf(o)+1).getSuit() == JOKER) {
					System.out.println("Joker picked");
					trick.setLeadCard(game.getDumpCard());
				} else {
					trick.setLeadCard(trick.getTrickLeader().getPlayingCards().get(playingCards.indexOf(o)+1));
				}
				trick.getTrickLeader().removeCard(playingCards.indexOf(o)+1);
				System.out.println("Card chosen by the leader");
				System.out.println("--------------------------");
				System.out.println(trick.getLeadCard());

				//Add leading card to HashMap
				Map<Integer, Card> cardsWon = new HashMap();
				cardsWon.put(0, trick.getLeadCard());
				turnCount+=1;


				for (JButton j : playingCards) {
					jP.remove(j);
				}

				playingCards.clear();


				repaint();
				setupNextPlayerCards(trick, game);
			}
		};
		for (JButton j : playingCards) {
			j.addActionListener(cardListener);
		}
	}

	//load images for the leader cards
	public void setupLeaderCards(Trick trick, Game game) {

		if (turnCount == 0) {

			//Leading a trick
			System.out.println(trick.getTrickLeader().getPlayerName() + "'s cards are as follows");
			System.out.println("-----------------------------");
			trick.getTrickLeader().getPlayingCards().forEach((key, value) -> {
				String imgPath = returnCardImgPath(value);
				System.out.println(imgPath);
				ImageIcon iconImg = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(80, 110, Image.SCALE_SMOOTH));
				playingCards.add(new JButton(iconImg));
			});

			System.out.println(playingCards);
			int x = 50;
			for (int i = 0; i < playingCards.size(); i++) {
				playingCards.get(i).setBounds(x, 300, 80, 110);
				x += 85;
			}

			for (JButton j : playingCards) {
				jP.add(j);
			}

			setUpCardChoiceListeners(trick, game);
		}

	}























	public void setupNextPlayerCards(Trick trick, Game game) {
		System.out.println(trick.getFollowingPlayers()[turnCount].getPlayerName() + "'s cards are as follows");
		System.out.println("-----------------------------");
		if(Card.checkIfFollowingSuitPossible(trick.getFollowingPlayers()[turnCount].getPlayingCards(), trick.getLeadCard().getSuit())) {
			trick.getFollowingPlayers()[turnCount].getPlayingCards().forEach((key, value) -> {
				if (value.getSuit() == JOKER && game.getDumpCard().getSuit() == trick.getLeadCard().getSuit()) {
					System.out.println(key + " -> " + value.getSuit());
					String imgPath = returnCardImgPath(value);
					System.out.println(imgPath);
					ImageIcon iconImg = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(80,110, Image.SCALE_SMOOTH));
					playingCards.add(new JButton(iconImg));
				} else if(value.getSuit() == trick.getLeadCard().getSuit()) {
					String imgPath = returnCardImgPath(value);
					System.out.println(imgPath);
					ImageIcon iconImg = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(80,110, Image.SCALE_SMOOTH));
					playingCards.add(new JButton(iconImg));
				}
			});
		} else {
			trick.getFollowingPlayers()[turnCount].getPlayingCards().forEach((key, value) -> {
				if (value.getSuit() == JOKER) {
					String imgPath = returnCardImgPath(value);
					System.out.println(imgPath);
					ImageIcon iconImg = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(80,110, Image.SCALE_SMOOTH));
					playingCards.add(new JButton(iconImg));
				} else {
					String imgPath = returnCardImgPath(value);
					System.out.println(imgPath);
					ImageIcon iconImg = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(80,110, Image.SCALE_SMOOTH));
					playingCards.add(new JButton(iconImg));
				}
			});
		}


		System.out.println(playingCards);
		int x = 50;
		for (int i = 0; i < playingCards.size(); i++) {
			playingCards.get(i).setBounds(x, 300, 80, 110);
			x += 85;
		}

		for (JButton j : playingCards) {
			jP.add(j);
		}

	}

}
