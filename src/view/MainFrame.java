package view;

import javax.swing.*;
import model.*;
import java.awt.*;

public class MainFrame extends JFrame{
//	DrawCircle circleCounter = new DrawCircle();
	public static JLabel jL, N1, p1N, N2, p2N, N3, p3N, N4, p4N, p1C, p2C, p3C, p4C, dumpCardTitle, dumpCard;
	public static JPanel jP, mainPanel, subPanel;
	public static Graphics g;
	public static Color c;
	
	public MainFrame(Player[] players, String dumpCardImage) {
		setTitle("Play Agony Aunt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//set frame size
		setSize(600,550);

		jP = new JPanel() {
		@Override
		public void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(500,20,15,15);
		g.drawOval(500,20,15,15);
				 
		g.setColor(Color.YELLOW);
		g.fillOval(500,40,15,15);
		g.drawOval(500,40,15,15);

		g.setColor(Color.GREEN);
		g.fillOval(500,60,15,15);
		g.drawOval(500,60,15,15);
				 
		g.setColor(Color.BLUE);
		g.fillOval(500,80,15,15);
		g.drawOval(500,80,15,15); }
				};
		jP.setLayout(null);
		
		//display dump card
		dumpCardTitle = new JLabel("Dump Card");
		dumpCardTitle.setBounds(16,1,80,20);
		jP.add(dumpCardTitle);
		
		String filePath = "src\\view\\Cards\\" + dumpCardImage + ".png";
		ImageIcon icon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
		dumpCard = new JLabel((icon));
		dumpCard.setIcon(icon);
		dumpCard.setBounds(8,1,100,130);
		jP.add(dumpCard);
			
		jL = new JLabel("Player information");
		jL.setBounds(400,1,200,15);
		jP.add(jL);		
		
		//Display Player1 name	
		N1 = new JLabel("1");
		N1.setBounds(400,12,300,30);
		jP.add(N1);
		p1N = new JLabel(players[0].getPlayerName());
		p1N.setBounds(420,12,300,30);
		jP.add(p1N);
			
		//Display Player2 name
		N2 = new JLabel("2");
		N2.setBounds(400,32,300,30);
		jP.add(N2);
		p2N = new JLabel(players[1].getPlayerName());
		p2N.setBounds(420,32,300,30);
		jP.add(p2N);
		
		//Display Player3 name
		N3 = new JLabel("3");
		N3.setBounds(400,52,300,30);
		jP.add(N3);
		p3N = new JLabel(players[2].getPlayerName());
		p3N.setBounds(420,52,300,30);
		jP.add(p3N);
		
		//Display Player4 name
		N4 = new JLabel("4");
		N4.setBounds(400,72,300,30);
		jP.add(N4);
		p4N = new JLabel(players[3].getPlayerName());
		p4N.setBounds(420,72,300,30);
		jP.add(p4N);

		//Display Available counters for player1
		p1C = new JLabel("");
		p1C.setBounds(520,16,20,20);
		jP.add(p1C);
		p1C.setText(String.valueOf(players[0].getCounters().size()));
		
		//Display Available counters for player2
		p2C = new JLabel("");
		p2C.setBounds(520,37,20,20);
		jP.add(p2C);
		p2C.setText(String.valueOf(players[1].getCounters().size()));
				
		//Display Available counters for player3
		p3C = new JLabel("");
		p3C.setBounds(520,58,20,20);
		jP.add(p3C);
		p3C.setText(String.valueOf(players[2].getCounters().size()));
				
		//Display Available counters for player1
		p4C = new JLabel("");
		p4C.setBounds(520,78,20,20);
		jP.add(p4C);
		p4C.setText(String.valueOf(players[3].getCounters().size()));
		
		//Display counters
		jP.repaint();
		
		add(jP);
		setVisible(true);		
	}
	
}
