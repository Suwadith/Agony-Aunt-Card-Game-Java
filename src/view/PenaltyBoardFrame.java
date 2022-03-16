package view;

import javax.swing.*;
import model.*;
import model.Penalties.DumpthTrick;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class PenaltyBoardFrame extends JFrame{

	public static JLabel pS1, pS2, pS3, pS4, pS5, pS6, pS7, pS8, pS9; 
	private JPanel mainPanel, subPanel1, subPanel2, subPanel3, subPanel4, subPanel5, subPanel6, subPanel7, subPanel8, subPanel9, subPanel10;
	public static Color color, colorAU, colorAG, colorQS, colorQD, colorQC, colorQH, colorDT, colorMT, colorLT;
	public int playerID;
	public ArrayList<String> penaltyCode;
	public String counterColor;
	public static int agonyAunt, agonyUncle, queenSpades, queenDiamond, queenHearts, queenClubs, dumpthTrick, mostTrick, omega;
	public static JButton jBS;
	public static Player[] players;
	public static DumpCard dumpCard;
	public static String dumpCardImage;
	public static Trick trick;
	public static Game game;
	public static PenaltyBoard penaltyboard;
	  
	public PenaltyBoardFrame(Player[] players, DumpCard dumpCard, String dumpCardImage, Trick trick, Game game, PenaltyBoard penaltyBoard){
		setTitle("Penalty Board");
		this.players = players;
		this.dumpCard = dumpCard;
		this.dumpCardImage = dumpCardImage;
		this.trick = trick;
		this.game = game;
		this.penaltyboard = penaltyBoard;
		mainPanel();
	}
	
	public PenaltyBoardFrame(int player_ID, ArrayList<String> penalty_code, String counter_color){
		this.playerID = player_ID;
		this.penaltyCode = penalty_code;
		this.counterColor = counter_color;
		setTitle("Penalty Board");
		mainPanel();
	}
	
	public void mainPanel() {
		mainPanel = new JPanel(); 
		mainPanel.setLayout(new GridLayout(4, 5));
		if(counterColor == CounterColor.RED.toString()) {
			color = Color.RED;
		}
		if(counterColor == CounterColor.YELLOW.toString()) {
			color = Color.YELLOW;
		}
		if(counterColor == CounterColor.GREEN.toString()) {
			color = Color.GREEN;
		}
		if(counterColor == CounterColor.BLUE.toString()) {
			color = Color.BLUE;
		}
		
		//Joker square
		subPanel1 = new JPanel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				if(agonyUncle==0) {
				if(playerID>0 && penaltyCode.contains("AU")) {
				super.paintComponent(g);
				g.setColor(color);
				colorAU = color;
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15);
				agonyUncle=1;
				} }
				if(agonyUncle==1) {
					super.paintComponent(g);
					g.setColor(colorAU);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
				} }
			};
		String jokerSquare = "src\\view\\PenaltyBoard\\jokersquare.jpg";
		ImageIcon j_icon = new ImageIcon(new ImageIcon(jokerSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
		subPanel1.add(new JLabel(j_icon, SwingConstants.CENTER));
		subPanel1.setBackground(Color.white);
		subPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
		//Spades square
	    subPanel2 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{	if(queenSpades==0) {
				if(playerID>0 && penaltyCode.contains("QP_SPADES")) {
				super.paintComponent(g);
				g.setColor(color);
				colorQS = color;
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15);
				queenSpades=1;
				} }
			if(queenSpades==1) {
				super.paintComponent(g);
				g.setColor(colorQS);
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15);
			} }
	    	};
	    String spadesSquare = "src\\view\\PenaltyBoard\\spadessquare.jpg";
	    ImageIcon s_icon = new ImageIcon(new ImageIcon(spadesSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel2.add(new JLabel(s_icon, SwingConstants.CENTER));
	    subPanel2.setBackground(Color.white);
	    subPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	      
	    //Omega square
	    subPanel3 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g) {
			if(omega==0) {
				if(playerID>0 && penaltyCode.contains("LT")) {
				super.paintComponent(g);
				g.setColor(color);
				colorLT = color;
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15);
				omega=1; 
				} }
			if(omega==1) {
				super.paintComponent(g);
				g.setColor(colorLT);
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15);
			} } 
			};	
	    String omegaSquare = "src\\view\\PenaltyBoard\\omegasquare.jpg";
	    ImageIcon o_icon = new ImageIcon(new ImageIcon(omegaSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel3.add(new JLabel(o_icon, SwingConstants.CENTER));
	    subPanel3.setBackground(Color.white);
	    subPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Heart square
	    subPanel4 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{	
				if(queenHearts==0) {
				if(playerID>0 && penaltyCode.contains("QP_HEARTS")) {
				super.paintComponent(g);
				colorQH = color;
				g.setColor(color);
				g.fillOval(8,20,15,15);
				g.drawOval(8,20,15,15);
				queenHearts=1;
				} }
				if(queenHearts==1) {
					super.paintComponent(g);
					g.setColor(colorQH);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);	
				}
			} };	
	    String heartSquare = "src\\view\\PenaltyBoard\\heartssquare.jpg";
	    ImageIcon h_icon = new ImageIcon(new ImageIcon(heartSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel4.add(new JLabel(h_icon, SwingConstants.CENTER));
	    subPanel4.setBackground(Color.white);
	    subPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Queen square
	    subPanel5 = new JPanel()
	    	{
	    	@Override
	    	public void paintComponent(Graphics g)
				{
	    		if(agonyAunt==0) {
	    		if(playerID>0 && penaltyCode.contains("AG")) {
					super.paintComponent(g);
					g.setColor(color);
					colorAG = color;
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15); 
					agonyAunt=1;
	    		} } 
	    		if(agonyAunt==1) {
	    			super.paintComponent(g);
					g.setColor(colorAG);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15); 
	    		}
	    		}
			};	
	    String queenSquare = "src\\view\\PenaltyBoard\\queensquare.jpg";
	    ImageIcon q_icon = new ImageIcon(new ImageIcon(queenSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel5.add(new JLabel(q_icon, SwingConstants.CENTER));
	    subPanel5.setBackground(Color.white);
	    subPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Diamond square
	    subPanel6 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{
				if(queenDiamond==0) {
				if(playerID>0 && penaltyCode.contains("QP_DIAMONDS")) {
					super.paintComponent(g);
					colorQD = color;
					g.setColor(color);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
					queenDiamond = 1;
			} }
				if(queenDiamond==1) {
					super.paintComponent(g);
					g.setColor(colorQD);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
				}
			} };	
	    String diamondSquare = "src\\view\\PenaltyBoard\\diamondsquare.jpg";
	    ImageIcon d_icon = new ImageIcon(new ImageIcon(diamondSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel6.add(new JLabel(d_icon, SwingConstants.CENTER));
	    subPanel6.setBackground(Color.white);
	    subPanel6.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Plus square
	    subPanel7 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{	if(mostTrick==0) {
				if(playerID>0 && penaltyCode.contains("MT")) {
					super.paintComponent(g);
					colorMT = color;
					g.setColor(color);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
					mostTrick=1;} }
				if(mostTrick==1) {
					super.paintComponent(g);
					g.setColor(colorMT);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
				}
			} };
	    String plusSquare = "src\\view\\PenaltyBoard\\plussquare.jpg";
	    ImageIcon p_icon = new ImageIcon(new ImageIcon(plusSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel7.add(new JLabel(p_icon, SwingConstants.CENTER));
	    subPanel7.setBackground(Color.white);
	    subPanel7.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Clubs square
	    subPanel8 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{	if(queenClubs==0) {
				if(playerID>0 && penaltyCode.contains("QP_CLUBS")) {
					super.paintComponent(g);
					colorQC=color;
					g.setColor(color);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
					queenClubs=1; 
				} }
				if(queenClubs==1) {
					super.paintComponent(g);
					g.setColor(colorQC);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
				}
			} };	
	    String clubsSquare = "src\\view\\PenaltyBoard\\clubssquare.jpg";
	    ImageIcon c_icon = new ImageIcon(new ImageIcon(clubsSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel8.add(new JLabel(c_icon, SwingConstants.CENTER));
	    subPanel8.setBackground(Color.white);
	    subPanel8.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    //Hash square
	    subPanel9 = new JPanel()
	    {
			@Override
			public void paintComponent(Graphics g)
			{	if(dumpthTrick==0) {
				if(playerID>0 && penaltyCode.contains("DT")) {
					super.paintComponent(g);
					colorDT = color;
					g.setColor(color);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
					dumpthTrick=1;
				} }
				if(dumpthTrick==1) {
					super.paintComponent(g);
					g.setColor(colorDT);
					g.fillOval(8,20,15,15);
					g.drawOval(8,20,15,15);
				}
			} };
	    String hashSquare = "src\\view\\PenaltyBoard\\hashsquare.jpg";
	    ImageIcon hash_icon = new ImageIcon(new ImageIcon(hashSquare).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
	    subPanel9.add(new JLabel(hash_icon, SwingConstants.CENTER));
	    subPanel9.setBackground(Color.white);
	    subPanel9.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    
	    subPanel10 = new JPanel();
	    subPanel10.add(new JLabel(""));
	    subPanel10.setBackground(Color.white);
	    
	    mainPanel.add(subPanel1);
	    mainPanel.add(subPanel2);
	    mainPanel.add(subPanel3);
	    mainPanel.add(subPanel4);
	    mainPanel.add(subPanel5);
	    mainPanel.add(subPanel6);
	    mainPanel.add(subPanel7);
	    mainPanel.add(subPanel8);
	    mainPanel.add(subPanel9);
	    mainPanel.add(subPanel10);
	    jBS = new JButton("Continue");
        jBS.setBounds(20, 20, 20, 10);
//        jBS.setBorder(BorderFactory.createEmptyBorder());
//        jBS.setBorderPainted(false);
//        jBS.setBackground(Color.WHITE);
        mainPanel.add(jBS);
        jBS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	/************************** MAIN FRAME ********************/
                MainFrame.turnCount = -1;
                new MainFrame(players, dumpCard, dumpCardImage, trick, game, penaltyboard);
            }
        });
	    mainPanel.setBackground(Color.WHITE);
	    add(mainPanel);
	    setSize(400, 400);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
}